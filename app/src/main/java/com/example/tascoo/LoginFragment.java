package com.example.tascoo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;


public class LoginFragment extends Fragment {
    TextInputLayout Email ,Password;
    TextView Signup;
    Button Login;
    DbManager DB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DB = new DbManager(getActivity());

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button signupButt = view.findViewById(R.id.SignInBut);
        Email = (TextInputLayout) view.findViewById(R.id.EmailPlaintext);
        Password = (TextInputLayout) view.findViewById(R.id.PasswrodlPlaintext);
        Login = (Button) view.findViewById(R.id.SignInBut);
        Signup = (TextView) view.findViewById(R.id.SignUptxt);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getEditText().toString();
                String pass = Password.getEditText().toString();
                if (email.equals("") || pass.equals(""))
                    Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuser = DB.checkEmail(email);
                    if (checkuser == false) {
                        //  Boolean insert = DB.InstertLoginData()
                        Toast.makeText(getActivity(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                    } else {
                        //Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUp);


                    }
                }
            }


        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUp);
            }


        });


        return view;
//    }
    }
}