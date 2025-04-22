package com.example.task1_activitylifecyclemethods.viewmodels;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Patterns;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.task1_activitylifecyclemethods.models.UserProfile;
import java.io.ByteArrayOutputStream;

public class UserViewModel extends ViewModel {
    public final MutableLiveData<String> name = new MutableLiveData<>();
    public final MutableLiveData<String> email = new MutableLiveData<>();
    public final MutableLiveData<String> phone = new MutableLiveData<>();

    private final MutableLiveData<UserProfile> userProfile = new MutableLiveData<>(new UserProfile());
    private final MutableLiveData<Bitmap> profileImage = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<SaveState> saveState = new MutableLiveData<>(SaveState.IDLE);
    private final MutableLiveData<String> nameError = new MutableLiveData<>();
    private final MutableLiveData<String> emailError = new MutableLiveData<>();
    private final MutableLiveData<String> phoneError = new MutableLiveData<>();
    private final MutableLiveData<String> dobError = new MutableLiveData<>();
    private final MutableLiveData<String> imageError = new MutableLiveData<>();

    private static final int MAX_IMAGE_SIZE_KB = 5120;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MIN_NAME_LENGTH = 2;

    public enum SaveState { IDLE, LOADING, SUCCESS, ERROR }

    public UserViewModel() {
        userProfile.setValue(new UserProfile());
    }

    public void saveProfile() {
        if (!validateAll()) return;

        saveState.setValue(SaveState.LOADING);
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                persistProfile();
                saveState.postValue(SaveState.SUCCESS);
            } catch (Exception e) {
                errorMessage.postValue("Save failed: " + e.getMessage());
                saveState.postValue(SaveState.ERROR);
            }
        }).start();
    }

    public void setProfileImage(Bitmap bitmap) {
        if (bitmap == null) {
            imageError.setValue("Profile image required");
            return;
        }

        if (bitmap.getByteCount() > MAX_IMAGE_SIZE_KB * 1024) {
            imageError.setValue("Image too large (max 5MB)");
            return;
        }

        profileImage.setValue(bitmap);
        updateProfile(p -> p.setProfileImage(bitmap));
    }

    public void setDob(String dob) {
        updateProfile(p -> p.setDob(dob));
    }

    private boolean validateAll() {
        boolean valid = true;
        if (!validateName(name.getValue())) valid = false;
        if (!validateEmail(email.getValue())) valid = false;
        if (!validatePhone(phone.getValue())) valid = false;
        if (!validateDob(userProfile.getValue().getDob())) valid = false;
        if (!validateImage(profileImage.getValue())) valid = false;
        return valid;
    }

    private boolean validateName(String name) {
        if (TextUtils.isEmpty(name)) {
            this.nameError.setValue("Name required");
            return false;
        }
        if (name.length() < MIN_NAME_LENGTH) {
            this.nameError.setValue("Minimum 2 characters");
            return false;
        }
        this.nameError.setValue(null);
        return true;
    }

    private boolean validateEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            this.emailError.setValue("Email required");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.emailError.setValue("Invalid email");
            return false;
        }
        this.emailError.setValue(null);
        return true;
    }

    private boolean validatePhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            this.phoneError.setValue("Phone required");
            return false;
        }
        String cleaned = phone.replaceAll("[^0-9]", "");
        if (cleaned.length() < 9 || cleaned.length() > 15) {
            this.phoneError.setValue("Invalid phone");
            return false;
        }
        this.phoneError.setValue(null);
        return true;
    }

    private boolean validateDob(String dob) {
        if (TextUtils.isEmpty(dob)) {
            this.dobError.setValue("Date required");
            return false;
        }
        if (!dob.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            this.dobError.setValue("DD/MM/YYYY format");
            return false;
        }
        this.dobError.setValue(null);
        return true;
    }

    private boolean validateImage(Bitmap image) {
        if (image == null) {
            this.imageError.setValue("Image required");
            return false;
        }
        this.imageError.setValue(null);
        return true;
    }

    private void updateProfile(ProfileUpdater updater) {
        UserProfile profile = userProfile.getValue();
        if (profile != null) {
            updater.update(profile);
            userProfile.setValue(profile);
        }
    }

    private void persistProfile() {
        UserProfile profile = new UserProfile();
        profile.setName(name.getValue());
        profile.setEmail(email.getValue());
        profile.setPhone(phone.getValue());
        profile.setDob(userProfile.getValue().getDob());
        profile.setProfileImage(profileImage.getValue());
        userProfile.postValue(profile);
    }

    // Getters
    public LiveData<UserProfile> getUserProfile() { return userProfile; }
    public LiveData<Bitmap> getProfileImage() { return profileImage; }
    public LiveData<String> getErrorMessage() { return errorMessage; }
    public LiveData<SaveState> getSaveState() { return saveState; }
    public LiveData<String> getNameError() { return nameError; }
    public LiveData<String> getEmailError() { return emailError; }
    public LiveData<String> getPhoneError() { return phoneError; }
    public LiveData<String> getDobError() { return dobError; }
    public LiveData<String> getImageError() { return imageError; }

    interface ProfileUpdater {
        void update(UserProfile profile);
    }
}