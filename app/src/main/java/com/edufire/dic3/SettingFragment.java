package com.edufire.dic3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.edufire.dic3.Models.User;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String username;

    public SettingFragment() {
        // Required empty public constructor
    }

    public SettingFragment(String username) {
        this.username = username;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
        EditText oldPass, newPass;
        AppCompatButton changePass, delete;
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        oldPass = view.findViewById(R.id.pass_EditText);
        newPass = view.findViewById(R.id.newPass_EditText);
        changePass = view.findViewById(R.id.changePassword);
        delete = view.findViewById(R.id.deleteAccount);
        User user = User.getAllUsers().get(username);
        if (user != null) {
            changePass.setOnClickListener(view1 -> {
                if (user.getPassword() != null && user.getPassword().equals(oldPass.getText().toString())) {
                    if (newPass.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity(), "Enter new password", Toast.LENGTH_SHORT).show();
                    } else {
                        String nPass = newPass.getText().toString();
                        Toast.makeText(getActivity(), "Password Change successful", Toast.LENGTH_SHORT).show();
                        MainActivity.db.updatePassword(username, nPass);
                        user.setPassword(nPass);
                    }
                } else {
                    Toast.makeText(getActivity(), "Wrong Password entered", Toast.LENGTH_SHORT).show();
                }
            });
            delete.setOnClickListener(view12 -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setMessage("Are you sure ?");
                builder.setTitle("Delete Account");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    Toast.makeText(getActivity(), "Your account deleted", Toast.LENGTH_SHORT).show();
                    MainActivity.db.deleteUser(username);
                    User.deleteUser(username);
                    startActivity(new Intent(getActivity(), MainActivity.class));
                });
                builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            });
        }
        return view;
    }
}