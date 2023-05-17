package com.example.homemissonandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemissonandroid.databinding.FragmentRegisterationEndBinding;

public class RegisterationEnd extends Fragment {

    FragmentRegisterationEndBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterationEndBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String userFullName = requireArguments().getString("fullName");
        binding.finishRegText.setText(getContext().getString(R.string.welcome) + userFullName + ",");
    }
}