package com.edufire.dic3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.edufire.dic3.Models.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupAddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button button;
    private EditText editText;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String username;

    public GroupAddFragment() {
        // Required empty public constructor
    }

    public GroupAddFragment(String username) {
        this.username = username;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupAddFragment newInstance(String param1, String param2) {
        GroupAddFragment fragment = new GroupAddFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_add, container, false);
        button = view.findViewById(R.id.button_to_invite_to_group);
        editText = view.findViewById(R.id.editText_send_request);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_to_send_request = editText.getText().toString();
                if(!username_to_send_request.isEmpty()){
                    insertTeammateRequest(username, username_to_send_request);
                }
            }
        });

        return view;
    }

    private void insertTeammateRequest(String sender_username, String receiver_username){
        if(User.getAllUsers().containsKey(receiver_username)){
            if(MainActivity.db.IsInSameGroup(sender_username, receiver_username)){
                Toast.makeText(getActivity(), "You are already in a group", Toast.LENGTH_SHORT).show();
            } else {
                if (MainActivity.db.isTeammateRequestExistsInDatabase(sender_username, receiver_username)) {
                    Toast.makeText(getActivity(), "You have already submitted a request", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Invite send", Toast.LENGTH_SHORT).show();
                    MainActivity.db.insertTeammateRequest(sender_username, receiver_username);
                }
            }
        } else {
            Toast.makeText(getActivity(), "No user found", Toast.LENGTH_SHORT).show();
        }
    }
}