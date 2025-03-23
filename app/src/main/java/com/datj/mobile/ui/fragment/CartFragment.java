package com.datj.mobile.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.datj.mobile.R;
import com.datj.mobile.data.local.CartManager;
import com.datj.mobile.data.remote.model.CartItem;

import java.util.List;

public class CartFragment extends Fragment {
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private TextView totalPriceText;
    private Button checkoutButton;

    public CartFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.cartRecyclerView);
        totalPriceText = view.findViewById(R.id.totalPriceText);
        checkoutButton = view.findViewById(R.id.checkoutButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<CartItem> cartItems = CartManager.getItems();
        adapter = new CartAdapter(requireContext(), cartItems, () -> {
            adapter.notifyDataSetChanged();
            double total = CartManager.calculateTotal();
            totalPriceText.setText("Total: $" + String.format("%.2f", total));
        });
        recyclerView.setAdapter(adapter);

        refreshCart();

        checkoutButton.setOnClickListener(v -> {
            // TODO: handle checkout logic here
        });

        return view;
    }

    private void refreshCart() {
        adapter.notifyDataSetChanged();
        double total = CartManager.calculateTotal();
        totalPriceText.setText("Total: $" + String.format("%.2f", total));
    }
}
