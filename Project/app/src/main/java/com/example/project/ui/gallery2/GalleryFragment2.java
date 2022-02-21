package com.example.project.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.R;
import com.example.project.databinding.FragmentGallery2Binding;

public class GalleryFragment2 extends Fragment {

    private Gallery2ViewModel galleryViewModel;
    private FragmentGallery2Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(Gallery2ViewModel.class);

        binding = FragmentGallery2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery2;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}