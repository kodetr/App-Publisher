package com.code.tanwir.menu.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import  com.code.tanwir.menu.R;

/**
 * Created by Tanwir on 17/03/2016.
 */
public class Fragmen_Chat extends Fragment {

    public Fragmen_Chat() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragmen_chat, container, false);
    }

}
