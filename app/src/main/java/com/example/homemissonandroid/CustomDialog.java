package com.example.homemissonandroid;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.homemissonandroid.databinding.CustomDialogLayoutBinding;

import java.util.Random;

public class CustomDialog extends Dialog {

    private CustomDialogLayoutBinding binding;

    private int randomNumber;
    private String fullName;

    private OnDialogResultListener dialogResultListener;

    public CustomDialog(Context context, OnDialogResultListener listener) {
        super(context);
        this.dialogResultListener = listener;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = CustomDialogLayoutBinding.inflate(LayoutInflater.from(getContext()));
        View rootView = binding.getRoot();
        setContentView(rootView);

        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
        }

        generateRandomNumber();
        binding.randomNumberText.setText(String.valueOf(randomNumber));

        binding.checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNumberInput = binding.userNumberEditText.getText().toString();
                if (userNumberInput.isEmpty()){
                    binding.userNumberEditText.setError(getContext().getString(R.string.plese_enter_code));

                }
                else {
                    int userNumber = Integer.parseInt(userNumberInput);
                    if(userNumber ==randomNumber){
                        dialogResultListener.onDialogResult(true, fullName);
                        dismiss();
                    }

                    else{
                        binding.userNumberEditText.setError(getContext().getString(R.string.check_if_code_valid));
                    }

                }

            }
        });
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(90) + 10; // Generate a random 2-digit number
    }

    public interface OnDialogResultListener {
        void onDialogResult(boolean isCorrect, String fullName);
    }

}
