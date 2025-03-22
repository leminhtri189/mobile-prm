package com.datj.mobile.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.datj.mobile.R;
import com.datj.mobile.data.remote.api.AccessoryApiService;
import com.datj.mobile.data.remote.model.Accessory;
import com.datj.mobile.data.remote.response.AccessoryResponse;
import com.datj.mobile.viewmodel.AccessoryViewModel;
import com.datj.mobile.viewmodel.AccountViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.datj.mobile.data.remote.RetrofitClient;
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private AccessoryAdapter adapter;
    private AccessoryViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.accessoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new AccessoryAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // ✅ Khởi tạo ViewModel
        viewModel = new ViewModelProvider(this).get(AccessoryViewModel.class);

        // ✅ Quan sát dữ liệu từ ViewModel
        viewModel.getAccessories().observe(getViewLifecycleOwner(), accessories -> {
            if (accessories != null) {
                adapter.setAccessoryList(accessories);
                adapter.notifyDataSetChanged();
                Log.d("TestUI", "Adapter item count: " + adapter.getItemCount());
            }
        });

        // ✅ Gọi API để lấy dữ liệu
        viewModel.fetchAccessories();

        return view;
    }
}
