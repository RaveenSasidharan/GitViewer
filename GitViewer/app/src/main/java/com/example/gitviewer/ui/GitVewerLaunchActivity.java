package com.example.gitviewer.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.gitviewer.R;
import com.example.gitviewer.controller.GitViewerSignUpControlller;



public class GitVewerLaunchActivity extends AppCompatActivity
{
    private TextView mSignUpButtonTV;
    private GitViewerSignUpControlller mSignUpControlller;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_vewer_aplash);

        mSignUpControlller = new GitViewerSignUpControlller(this);

        mSignUpButtonTV = (TextView) findViewById(R.id.sign_up);
        mSignUpButtonTV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                SignUpFragment fragment = new SignUpFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                mSignUpButtonTV.setTransitionName("signUpFragment");
                ft.addSharedElement(mSignUpButtonTV,"signUpFragment");
                fragment.show(ft,"SignUpDialog");
            }
        });

    }
}
