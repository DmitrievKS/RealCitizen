package kirdmt.com.realcitizen.ui.first;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kirdmt.com.realcitizen.R;

public class FirstFragment extends Fragment {

    ImageView imageViewRussianEmblem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_first, container, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        imageViewRussianEmblem = (ImageView) view.findViewById(R.id.emblem_image);

        //view.setBackgroundColor(getResources().getColor(R.color.lightGreen)); //change background color

    }
}