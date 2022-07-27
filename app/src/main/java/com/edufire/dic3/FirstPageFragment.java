package com.edufire.dic3;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edufire.dic3.Models.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String username;
    private TextView score, connectStatus, premiumStatus;

    public FirstPageFragment() {
        // Required empty public constructor
    }

    public FirstPageFragment(String username) {
        this.username = username;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstPageFragment newInstance(String param1, String param2) {
        FirstPageFragment fragment = new FirstPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_page, container, false);
        score = view.findViewById(R.id.user_score);
        connectStatus = view.findViewById(R.id.checkInternetTextView);
        premiumStatus = view.findViewById(R.id.user_premium);
        User user = User.getAllUsers().get(username);
        if(user != null){
            score.setText("You have " + user.getScore() + " score");
            if(user.isPremium()){
                premiumStatus.setText("You have premium account");
            } else {
                premiumStatus.setText("account not premium");
            }
        }
        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            connectStatus.setText("Online");
        } else {
            connectStatus.setText("No internet is detected for a better experience please connect to internet");
        }
        return view;
    }


}