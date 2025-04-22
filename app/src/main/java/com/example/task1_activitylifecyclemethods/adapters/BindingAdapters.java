package com.example.task1_activitylifecyclemethods.adapters;

import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;

import com.example.task1_activitylifecyclemethods.R;

public class BindingAdapters {
    @BindingAdapter("imageBitmap")
    public static void setImageBitmap(ImageView view, Bitmap bitmap) {
        if (bitmap != null) {
            view.setImageBitmap(bitmap);
        } else {
            view.setImageResource(R.drawable.ic_person);
        }
    }
}