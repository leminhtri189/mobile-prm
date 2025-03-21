package com.datj.mobile.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.widget.TextView;
import com.datj.mobile.R;
import com.datj.mobile.data.remote.model.Product;
import com.datj.mobile.ui.viewmodel.ProductViewModel;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ProductViewModel productViewModel;
    private TextView productInfoTextView;
    private List<Product> productList = new ArrayList<>();

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        productInfoTextView = view.findViewById(R.id.productInfoTextView);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // Lấy danh sách sản phẩm từ ViewModel
        productViewModel.getAllProducts().observe(getViewLifecycleOwner(), products -> {
            productList.clear();
            if (products != null) {
                productList.addAll(products);
            } else {
                // Xử lý trường hợp API không có dữ liệu trả về
                productInfoTextView.setText("Không có sản phẩm nào.");
            }
        });


        return view;
    }

    // 🎯 Thêm phương thức này để tìm kiếm sản phẩm theo AccessoryId
    private void searchProduct(String query) {
        try {
            int accessoryId = Integer.parseInt(query); // Đổi thành accessoryId
            productViewModel.getProductByAccessoryId(accessoryId).observe(getViewLifecycleOwner(), product -> {
                if (product != null) {
                    if (product.getAccessoryType() != null) {
                        productInfoTextView.setText("Tên: " + product.getName() +
                                "\nGiá: " + product.getAccessoryType().getProcessingPrice());
                    } else {
                        productInfoTextView.setText("Tên: " + product.getName() + "\nGiá: Không xác định");
                    }
                } else {
                    productInfoTextView.setText("Không tìm thấy sản phẩm.");
                }
            });
        } catch (NumberFormatException e) {
            productInfoTextView.setText("Vui lòng nhập ID hợp lệ.");
        }
    }
}
