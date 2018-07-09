package com.app.teampayup.payuplah;

import android.animation.TypeConverter;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dx.dxloadingbutton.lib.LoadingButton;

import java.util.concurrent.TimeUnit;


public class AddProfile extends AppCompatActivity {
    public int mMorphCounter1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        final ProfileList profileList = MainActivity.getProfileList();

        //find controls
        final LoadingButton lb = (LoadingButton)findViewById(R.id.loading_btn);
        final EditText txtName = findViewById(R.id.txtName);
        final EditText txtBudget = findViewById(R.id.txtBudget);
        //set add button on click
        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lb.startLoading(); //start loading
                //check if Name and Budget is filled in
                if(txtBudget.getText().toString().trim().length()>0 && txtName.getText().toString().trim().length()>0 ) {
                    Profile newProfile = new Profile(txtName.getText().toString(),Integer.parseInt(txtBudget.getText().toString()));
                    lb.loadingSuccessful();//show success
                    //newProfile(view ,txtName.getText().toString(),Integer.parseInt(txtBudget.getText().toString()));
                    //show alert that profile is created
                    final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(AddProfile.this);
                    String message = String.format("%s's profile has been created with a budget of %s",txtName.getText().toString(), txtBudget.getText().toString());
                    alertBuilder.setTitle("Profile Created!").setMessage(message)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    lb.reset();
                                }
                            });
                    AlertDialog alert1 = alertBuilder.create();
                    alert1.show();;
                }
                else{
                    lb.loadingFailed(); //show fail
                    lb.isResetAfterFailed(); //reset to try again
                }
            }
        });

    }
/*
    public void newProfile (View view, String name, int budget){
        //create a new profile and add into sqllite
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Profile profile = new Profile(name, budget);
        dbHelper.addProfile(profile);

        //check if new profile is created
        Profile profile2 = dbHelper.findProfile(name);
        if (profile2 != null){
            Log.d("DEBUG", "newProfile: created");
        }
    }
*/
}
