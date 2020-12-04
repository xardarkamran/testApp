package com.example.testapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.testapp.Utils.ModelClass;
import com.example.testapp.Utils.ObjectSharedPrefrences;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignInFragment extends Fragment {
   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }*/
 private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

  /*  //final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
    final String PASSWORD_PATTERN = "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}";
    Pattern pattern1 = Pattern.compile(PASSWORD_PATTERN);
    Matcher matcher1;
*/
    //object of shared prefrences class
    Button btnLogin;
    EditText edEmail,edPassword;
    //input type text object
    TextInputLayout UserNameWrapper;
    TextInputLayout PasswordWrapper;
    //variables values and initialization
    String strUserName,strPssword;
    ObjectSharedPrefrences objectSharedPrefrences;
    ModelClass modelClass;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objectSharedPrefrences=new ObjectSharedPrefrences(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_sign_in_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setLIstiners();

    }

    //all views find id
    private void initViews(View view)
    {

        // all buttons ids
        btnLogin=(Button) view.findViewById(R.id.btn_signin_id);
        edEmail=(EditText) view.findViewById(R.id.ed_username_id);
        edPassword=(EditText) view.findViewById(R.id.ed_password_id);
        //input type text find ids
        UserNameWrapper =(TextInputLayout)  view.findViewById(R.id.TIL_userName_id);
        PasswordWrapper = (TextInputLayout) view.findViewById(R.id.TIL_password_id);
        //set hint animation text
        UserNameWrapper.setHint("Username");
        PasswordWrapper.setHint("Password");


    }


    //all set listiners
    private void setLIstiners()
    {

        /*click on button*/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onClick(View view) {
                strUserName = UserNameWrapper.getEditText().getText().toString();
                strPssword = PasswordWrapper.getEditText().getText().toString();
                if (!validateEmail(strUserName)) {
                    UserNameWrapper.setError("Not a valid email address!");
                }
                /*else if (!validatePassword(password)) {
                    passwordWrapper.setError("Not a valid password!");
                }*/
                else if(PasswordWrapper.getEditText().getText().toString().isEmpty()) {
                    // editText is empty
                    PasswordWrapper.setError("not valid password");
                }

                else {
                    UserNameWrapper.setErrorEnabled(false);
                    PasswordWrapper.setErrorEnabled(false);

                    edEmail.setText("");
                    edPassword.setText("");

                    modelClass=new ModelClass(strUserName,strPssword);
                    objectSharedPrefrences.SaveUser(modelClass);
                    LoadProfileFragment();
                }

            }
        });

    }

    //load profile fragment and send data also
    private void LoadProfileFragment()
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ProfileFragment scndfrgment=new ProfileFragment();
/*
        Bundle bundle=new Bundle();
        bundle.putParcelable("modelclass",modelClass);
        scndfrgment.setArguments(bundle);*/

        Bundle bundle=new Bundle();
        bundle.putSerializable("modelclass",modelClass);
        scndfrgment.setArguments(bundle);

        fragmentTransaction.replace(R.id.FL_container_id, scndfrgment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    //email validation
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    /*public boolean validatePassword(String password) {
        matcher1 = pattern1.matcher(password);
        return matcher1.matches();
    }*/

    //password validation
    public boolean validatePassword(String password) {
        return password.length() > 5;
    }


}