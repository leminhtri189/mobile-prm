package com.datj.mobile.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.datj.mobile.R;
import com.datj.mobile.data.remote.model.Accessory;
import com.datj.mobile.data.remote.model.AccessoryImage;
import com.datj.mobile.data.remote.model.CartItem;
import com.datj.mobile.data.remote.model.CartManager;
import com.datj.mobile.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class AccessoryDetailFragment extends Fragment {

    private Accessory accessory;

    public AccessoryDetailFragment() {}

    public static AccessoryDetailFragment newInstance(Accessory accessory) {
        AccessoryDetailFragment fragment = new AccessoryDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("accessory", accessory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accessory_detail, container, false);

        accessory = (Accessory) getArguments().getSerializable("accessory");
        Button buttonAddToCart = view.findViewById(R.id.addToCartButton);
        Spinner spinnerSize = view.findViewById(R.id.sizeSpinner);
        ViewPager2 imagePager = view.findViewById(R.id.imagePager);
        imagePager.setAdapter(new AccessoryImageAdapter(accessory.getAccessoryImages()));

        ((TextView) view.findViewById(R.id.detailName)).setText(accessory.getName());
        ((TextView) view.findViewById(R.id.detailKarat)).setText("Karat: " + accessory.getKarat());
        ((TextView) view.findViewById(R.id.detailWeight)).setText("Gold weight: " + accessory.getMaterialWeight() + "g");
        ((TextView) view.findViewById(R.id.detailQuantity)).setText("Available: " + accessory.getQuantity());
        ((TextView) view.findViewById(R.id.detailType)).setText("Type: " + accessory.getAccessoryType().getName());
        ((TextView) view.findViewById(R.id.detailPrice)).setText("Wage Price: " + accessory.getAccessoryType().getProcessingPrice() + "$");
        ((TextView) view.findViewById(R.id.detailShape)).setText("Shape: " + accessory.getShape().getName());

        view.findViewById(R.id.backButton).setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack()
        );

        buttonAddToCart.setOnClickListener(v -> {
            String selectedSize = spinnerSize.getSelectedItem().toString(); // nếu dùng Spinner
            CartManager.addItem(new CartItem(accessory, selectedSize, 1));
            Toast.makeText(getContext(), "Add to cart successfully", Toast.LENGTH_SHORT).show();

            // Cập nhật số lượng trên icon cart
            ((MainActivity) requireActivity()).updateCartBadge();
        });

        return view;
    }
}