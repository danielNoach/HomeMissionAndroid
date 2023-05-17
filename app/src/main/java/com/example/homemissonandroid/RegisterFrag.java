package com.example.homemissonandroid;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.homemissonandroid.databinding.FragmentRegisterBinding;
import java.util.regex.Pattern;

public class RegisterFrag extends Fragment implements CustomDialog.OnDialogResultListener {
    FragmentRegisterBinding binding;
    boolean isBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.ContinuedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVal();
            }
        });
    }

    private void checkVal() {
        String fullName = binding.fullNameText.getText().toString();
        String email = binding.emailText.getText().toString();
        String password = binding.passwordText.getText().toString();
        String passwordCon = binding.passwordvalText.getText().toString();

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || passwordCon.isEmpty()) {

            fullNameValid(fullName);
            emailValid(email);
            passwordValid(password);
            passwordConValid(passwordCon);

        } else {
            if (!EmailValidator.isValidEmail(email)) {
                emailValidatorFalse();
            } else {
                binding.emailHelper.setVisibility(View.GONE);
                if (password.equals(passwordCon)) {
                    openDialog(fullName);
                } else {
                    passwordEqualsFalse();
                }
            }
        }
    }

    private void passwordEqualsFalse() {
        binding.passwordHelper.setVisibility(View.GONE);
        binding.passwordvalHelper.setVisibility(View.VISIBLE);
        binding.passwordvalHelper.setText(R.string.check_password_equals);
    }

    private void openDialog(String fullname) {
        binding.passwordvalHelper.setVisibility(View.GONE);
        CustomDialog customDialog = new CustomDialog(requireContext(), this);
        customDialog.setFullName(fullname);
        customDialog.show();
    }

    private void emailValidatorFalse() {
        binding.fullNameHelper.setVisibility(View.GONE);
        binding.fullNameHelper.setVisibility(View.GONE);
        binding.passwordvalHelper.setVisibility(View.GONE);
        binding.passwordvalHelper.setVisibility(View.GONE);
        binding.emailHelper.setVisibility(View.VISIBLE);
        binding.emailHelper.setText(R.string.enter_correct_emailAddres);
    }

    private void passwordConValid(String passwordCon) {
        if (passwordCon.isEmpty()) {
            binding.passwordvalHelper.setVisibility(View.VISIBLE);
            binding.passwordvalHelper.setText(R.string.no_passwordCon);
        } else {
            binding.passwordvalHelper.setVisibility(View.GONE);
        }
    }

    private void passwordValid(String password) {
        if (password.isEmpty()) {
            binding.passwordHelper.setVisibility(View.VISIBLE);
            binding.passwordHelper.setText(R.string.no_password);
        } else {
            binding.passwordHelper.setVisibility(View.GONE);
        }
    }

    private void emailValid(String email) {
        if (email.isEmpty()) {
            binding.emailHelper.setVisibility(View.VISIBLE);
            binding.emailHelper.setText(R.string.no_email);
        } else {
            binding.emailHelper.setVisibility(View.GONE);
        }
    }

    private void fullNameValid(String fullname) {
        if (fullname.isEmpty()) {
            binding.fullNameHelper.setVisibility(View.VISIBLE);
            binding.fullNameHelper.setText(R.string.no_fullName);
        } else {
            binding.fullNameHelper.setVisibility(View.GONE);
        }
    }

    public void onDialogResult(boolean isCorrect, String fullName) {
        if (isCorrect) {
            Bundle bundle = new Bundle();
            bundle.putString("fullName", fullName);
            Navigation.findNavController(requireView()).navigate(R.id.action_registerFrag_to_registerationEnd, bundle);
        } else {
            // Handle incorrect dialog result
        }
    }



    private static class EmailValidator {
        private static final Pattern EMAIL_PATTERN = Pattern.compile(
                "^\\w+[.-]?\\w+@(?:\\w+\\.)+\\w+$"
        );

        public static boolean isValidEmail(String email) {
            return EMAIL_PATTERN.matcher(email).matches();
        }
    }
}


