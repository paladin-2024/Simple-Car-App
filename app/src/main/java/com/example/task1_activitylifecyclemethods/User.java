package com.example.task1_activitylifecyclemethods;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.task1_activitylifecyclemethods.Utils.PermissionUtils;
import com.example.task1_activitylifecyclemethods.databinding.ActivityUserBinding;
import com.example.task1_activitylifecyclemethods.viewmodels.UserViewModel;
import java.io.IOException;
import java.util.Calendar;

public class User extends AppCompatActivity {
    private static final int PROFILE_IMAGE_REQUEST = 101;
    private ActivityUserBinding binding;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        setupObservers();
        setupImageHandling();
        setupDatePicker();
        setupValidationObservers();
    }

    private void setupObservers() {
        viewModel.getSaveState().observe(this, state -> {
            switch (state) {
                case LOADING:
                    Toast.makeText(this, "Saving...", Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    Toast.makeText(this, "Profile saved!", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(this, "Save failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        viewModel.getErrorMessage().observe(this, error -> {
            if (!TextUtils.isEmpty(error)) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupValidationObservers() {
        viewModel.getNameError().observe(this, error ->
                binding.tilName.setError(error));

        viewModel.getEmailError().observe(this, error ->
                binding.tilEmail.setError(error));

        viewModel.getPhoneError().observe(this, error ->
                binding.tilPhone.setError(error));

        viewModel.getDobError().observe(this, error ->
                binding.tilDob.setError(error));

        viewModel.getImageError().observe(this, error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show());
    }

    private void setupImageHandling() {
        binding.profileImage.setOnClickListener(v -> {
            if (PermissionUtils.checkReadStoragePermission(this)) {
                openImagePicker();
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PROFILE_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PROFILE_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    viewModel.setProfileImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void setupDatePicker() {
        binding.dobInput.setOnClickListener(v -> showDatePickerDialog());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, day) -> {
            String date = String.format("%02d/%02d/%d", day, (month + 1), year);
            viewModel.setDob(date);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}