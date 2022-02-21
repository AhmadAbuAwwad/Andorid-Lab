package com.example.project.ui.Menu;

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
import com.example.project.databinding.FragmentGallery1Binding;
import com.example.project.databinding.FragmentGallery2Binding;

public class GalleryFragment1 extends Fragment {

    private Gallery1ViewModel galleryViewModel;
    private FragmentGallery1Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(Gallery1ViewModel.class);

        binding = FragmentGallery1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery1;
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