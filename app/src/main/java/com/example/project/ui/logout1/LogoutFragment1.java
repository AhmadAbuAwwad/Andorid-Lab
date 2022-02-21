package com.example.project.ui.logout1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project.SignIn;
import com.example.project.databinding.FragmentLogout1Binding;

public class LogoutFragment1 extends Fragment {

    private FragmentLogout1Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Intent intent = new Intent(getContext(), SignIn.class);
        getContext().startActivity(intent);


        View root = null;
        return root;
    }
}