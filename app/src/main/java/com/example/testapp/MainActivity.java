package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.testapp.Utils.ModelClass;
import com.example.testapp.Utils.ObjectSharedPrefrences;

public class MainActivity extends AppCompatActivity {

    ObjectSharedPrefrences objectSharedPrefrences;


    ModelClass modelClass;
   // Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objectSharedPrefrences=new ObjectSharedPrefrences(this);
        ModelClass obj=objectSharedPrefrences.GetUser();
        //Toast.makeText(this, ""+obj.username, Toast.LENGTH_SHORT).show();

        if (obj!=null)
        {
            LoadFragment(new ProfileFragment());
        }
        else {
            LoadFragment(new SignInFragment());
        }

    }
    /*private void LoadSigninFragment()
    {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.FL_container_id, new SignInFragment());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();

    }


    private void LoadProfileFragment()
    {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.FL_container_id, new ProfileFragment());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();

    }
    */
    private void LoadFragment(Fragment fragment)
    {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.FL_container_id, fragment);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            //fm.popBackStack();
            super.onBackPressed();
            //this.finish();

        }

       /* else {
            //super.onBackPressed();
        }*/
       /* super.onBackPressed();
       // fm.popBackStack();
        this.finish();*/

    }

}