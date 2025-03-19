package com.datj.mobile.ui.customview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.datj.mobile.R;
import com.datj.mobile.data.remote.model.Product;
import com.datj.mobile.ui.viewmodel.ProductViewModel;

public class ProductDetailActivity extends AppCompatActivity {
    private ProductViewModel productViewModel;
    private ImageView productImage;
    private TextView productName, productPrice, productKarat, productWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
//        productKarat = findViewById(R.id.product_karat);
//        productWeight = findViewById(R.id.product_weight);
        TextView tvShippingInfo = findViewById(R.id.tv_shipping_info);
        TextView shippingContent = findViewById(R.id.shipping_info_content);
        TextView tvSupport = findViewById(R.id.tv_support);
        TextView supportContent = findViewById(R.id.support_content);

        tvShippingInfo.setOnClickListener(v -> {
            if (shippingContent.getVisibility() == View.GONE) {
                shippingContent.setVisibility(View.VISIBLE);
            } else {
                shippingContent.setVisibility(View.GONE);
            }
        });

        tvSupport.setOnClickListener(v -> {
            if (supportContent.getVisibility() == View.GONE) {
                supportContent.setVisibility(View.VISIBLE);
            } else {
                supportContent.setVisibility(View.GONE);
            }
        });



        int productId = getIntent().getIntExtra("PRODUCT_ID", -1);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        if (productId != -1) {
            productViewModel.getProductById(productId).observe(this, product -> {
                if (product != null) {
                    productName.setText(product.getName());
                    productPrice.setText("Processing Price: $" + product.getAccessoryType().getProcessingPrice());
                    productKarat.setText("Karat: " + product.getKarat());
                    productWeight.setText("Weight: " + product.getMaterialWeight() + "g");

                    if (!product.getAccessoryImages().isEmpty()) {
                        Glide.with(this)
                                .load(product.getAccessoryImages().get(0).getUrl())
                                .into(productImage);
                    }
                } else {
                    Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Invalid Product ID", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}


