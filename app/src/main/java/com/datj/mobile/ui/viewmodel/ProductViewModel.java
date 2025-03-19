package com.datj.mobile.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.datj.mobile.data.remote.model.Product;

public class ProductViewModel extends ViewModel {
    private final MutableLiveData<Product> product = new MutableLiveData<>();

    public void setProduct(Product productData) {
        product.setValue(productData);
    }

    public LiveData<Product> getProductById(int productId) {
        // TODO: Fetch product from API or database
        return product;
    }
}
