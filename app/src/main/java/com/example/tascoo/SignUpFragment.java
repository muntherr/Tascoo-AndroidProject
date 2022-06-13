package com.example.tascoo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUpFragment extends Fragment {
    EditText Email, Password, Name;
    Button signupButt;
    DbManager DB;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        DB = new DbManager(getActivity());

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        Button Returnbutt = view.findViewById(R.id.retbutt);
        Returnbutt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.loginFragment);

            }
        });

        signupButt = view.findViewById(R.id.SignUpBut);
        Name = (EditText) view.findViewById(R.id.Name);
        Email = (EditText) view.findViewById(R.id.Email);
        Password = (EditText) view.findViewById(R.id.Passwrod);

        signupButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                System.out.println("--------------------------------------------------------");


                System.out.println(name);

                String email = Email.getText().toString();
                System.out.println(email);
                String pass = Password.getText().toString();
                if (name.equals("") || email.equals("") || pass.equals(""))
                    Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuser = DB.checkEmail(name);
                    if (checkuser == false) {
                        Boolean insert = DB.InstertUserData(name, email, pass);
                        if (insert == true) {
                            Toast.makeText(getActivity(), "Registered successfully", Toast.LENGTH_SHORT).show();

                            Navigation.findNavController(view).navigate(R.id.loginFragment);
                            // Toast.makeText(getActivity(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                        } else {
                            //Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUp);
                            Toast.makeText(getActivity(), "Registration failed", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                    }


                }
            }

        });
        return view;
    }

}