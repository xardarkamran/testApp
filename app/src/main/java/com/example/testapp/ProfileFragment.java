package com.example.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testapp.Utils.ModelClass;
import com.example.testapp.Utils.ObjectSharedPrefrences;


public class ProfileFragment extends Fragment {

    private String strUserName, strPassword;
    private TextView edUser, edPassword;

    private Button btnLogout;

    ObjectSharedPrefrences objectPrefrence;
    ModelClass modelClass;
    ModelClass m1;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /*//parcelabel data
        Bundle bundle = getArguments();
        if (bundle!=null) {
            //strUserName = (String) bundle.getCharSequence("username");
            //strPassword = (String) bundle.getCharSequence("passworrd");
            m1=bundle.getParcelable("modelclass");
        }*/


        //Bundle bundle=getArguments();
        /*if (bundle!=null) {
            m1=bundle.getSerializable("modelclass");
        }*/

        modelClass= (ModelClass) getArguments().getSerializable("modelclass");
        //Toast.makeText(getContext(), ""+m1.username+" "+m1.password, Toast.LENGTH_SHORT).show();
        objectPrefrence=new ObjectSharedPrefrences(getContext());
        modelClass=objectPrefrence.GetUser();
       }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setListeners();

    }

    //get all view ids here
    private void initViews(View view) {
        edUser = (TextView) view.findViewById(R.id.tv_username_id);
        edPassword = (TextView) view.findViewById(R.id.tv_passwordprofile_id);
        btnLogout = (Button) view.findViewById(R.id.btn_logout);
       /* edUser.setText(strUserName);
        edPassword.setText(strPassword);*/
        edUser.setText(modelClass.username);
        edPassword.setText(modelClass.password);
    }

    //clicklistiner
    private void setListeners() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objectPrefrence.SaveUser(null);
                Toast.makeText(getContext(), "Logout", Toast.LENGTH_SHORT).show();
               /* if (getFragmentManager().getBackStackEntryCount() > 0) {
                    FragmentManager fm = view.getSupportFragmentManager();
                    fm.popBackStack();
                }*/

                LoadSigninFragment();
            }
        });
    }


    //load signin fragment
    private void LoadSigninFragment()
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SignInFragment signinfrgment=new SignInFragment();
       /* Bundle bundle=new Bundle();
        bundle.putCharSequence("username",strUserName);
        bundle.putCharSequence("passworrd",strPssword);
        scndfrgment.setArguments(bundle);*/
        fragmentTransaction.replace(R.id.FL_container_id, signinfrgment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }




}