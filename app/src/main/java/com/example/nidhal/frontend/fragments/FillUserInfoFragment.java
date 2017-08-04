package com.example.nidhal.frontend.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nidhal.frontend.R;

/**
 * Created by Nidhal on 19/07/2017.
 */

public class FillUserInfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fill_user_info_layout,container,false);
    }
}
