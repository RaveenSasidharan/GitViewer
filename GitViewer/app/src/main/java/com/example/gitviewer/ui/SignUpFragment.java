package com.example.gitviewer.ui;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gitviewer.R;
import com.example.gitviewer.utils.AnimationUtils;
import com.google.android.gms.plus.PlusOneButton;


public class SignUpFragment extends DialogFragment
{

    private EditText mUserNameEditText;
    private TextView mSignUpTextView;

    public SignUpFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        mUserNameEditText = (EditText) view.findViewById(R.id.user_name);
        mSignUpTextView   = (TextView) view.findViewById(R.id.sign_up);
        mSignUpTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final String userName = String.valueOf(mUserNameEditText.getText());
                Intent naviIntent = new Intent(getContext(), GitViewerHomeFeedActivtiy.class);
                naviIntent.putExtra("user_name",userName);
                startActivity(naviIntent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), new Pair(mSignUpTextView,"mSignUpTextView")).toBundle());
            }
        });

        RelativeLayout relay = (RelativeLayout) view;
        // getting the layoutparams might differ in your application, it depends on the parent layout
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) relay.getLayoutParams();

        ResizeAnimation a = new ResizeAnimation(relay);
        a.setDuration(500);

// set the starting height (the current height) and the new height that the view should have after the animation
        a.setParams(0,lp.WRAP_CONTENT);

        relay.startAnimation(a);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);

    }

    @Override
    public void onResume()
    {
        super.onResume();
        AnimationUtils.expandWidth(mSignUpTextView);
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

}
