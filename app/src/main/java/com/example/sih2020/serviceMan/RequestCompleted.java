package com.example.sih2020.serviceMan;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sih2020.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestCompleted extends Fragment {


    public RequestCompleted() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.serviceman_request_completed, container, false);
    }

}