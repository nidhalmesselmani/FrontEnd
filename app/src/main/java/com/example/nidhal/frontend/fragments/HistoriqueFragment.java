package com.example.nidhal.frontend.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.nidhal.frontend.R;

/**
 * Created by Nidhal on 10/07/2017.
 */

public class HistoriqueFragment extends Fragment {
    ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] prenoms = new String[]{
                "One", "Two", "Three", "Four", "Five", "Six"

        };
        View v = inflater.inflate(R.layout.historique,container,false);
        mListView = (ListView) v.findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, prenoms);
        mListView.setAdapter(adapter);

        return v;
    }

}
