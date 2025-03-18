package com.datj.mobile.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.datj.mobile.R;
import com.datj.mobile.data.local.TokenManager;
import com.datj.mobile.databinding.FragmentProfileBinding;
import com.datj.mobile.ui.customview.LoginActivity;
import com.datj.mobile.viewmodel.AccountViewModel;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private AccountViewModel accountViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        accountViewModel.getAccount().observe(getViewLifecycleOwner(), account -> {
            if (account != null) {
                binding.setAccount(account);
                // Không có ảnh đại diện, giữ placeholder mặc định từ XML
                binding.profileImageView.setImageResource(R.drawable.ic_profile_placeholder);
            } else {
                Toast.makeText(getContext(), "Không tải được thông tin tài khoản", Toast.LENGTH_SHORT).show();
            }
        });

        binding.logoutButton.setOnClickListener(v -> handleLogout());
    }

    private void handleLogout() {
        TokenManager.getInstance().clearToken();
        Toast.makeText(getContext(), "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}