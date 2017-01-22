package com.example.gitviewer.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gitviewer.R;
import com.example.gitviewer.controller.GitViewerRepoDetailController;
import com.example.gitviewer.controller.GitViewerRepoDetailController.RepoDetailControllerListener;
import com.example.gitviewer.model.GitViewerUserRepoModel;
import com.squareup.picasso.Picasso;

public class GitViewerRepoDetailsActivty extends AppCompatActivity {

    private String mRepoLink;
    private GitViewerUserRepoModel mRepoDetails;
    private GitViewerRepoDetailController mRepoDetailController;
    private TextView mRepoNmaeTV;
    private TextView mRepoDescTV;
    private ImageView mRepoOwnerProfileView;
    private TextView mRepoOwnerTV;
    private TextView mRepoStarCountTV;
    private TextView mRepoViewCountTV;



    RepoDetailControllerListener mListener = new RepoDetailControllerListener()
    {

        @Override
        public void onRepoDetailReceived(GitViewerUserRepoModel repo)
        {
            Toast.makeText(getApplicationContext(),"list recvvd",Toast.LENGTH_SHORT);
            mRepoDetails = repo;
            initRepoDetails();

        }

    } ;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_viewer_repo_details_activty);
        mRepoDetailController = new GitViewerRepoDetailController(this,mListener);
        mRepoLink = getIntent().getStringExtra("repo_link");
        mRepoDetailController.hitRepoDetailApi(mRepoLink);

    }


    private void initRepoDetails()
    {
        mRepoNmaeTV           =   (TextView) findViewById(R.id.repoName);
        mRepoDescTV           =   (TextView) findViewById(R.id.repoDesc);
        mRepoOwnerTV          =   (TextView) findViewById(R.id.ownerName);
        mRepoStarCountTV      =   (TextView) findViewById(R.id.starCount);
        mRepoViewCountTV      =   (TextView) findViewById(R.id.viewCount);
        mRepoOwnerProfileView =   (ImageView) findViewById(R.id.ownerImg);

        mRepoNmaeTV.setText(mRepoDetails.mName);
        mRepoDescTV.setText(mRepoDetails.mDescription);
        mRepoOwnerTV.setText(mRepoDetails.mOwnerName);
        mRepoStarCountTV.setText(mRepoDetails.mStarCount);
        mRepoViewCountTV.setText(mRepoDetails.mViewCount);

        //loading circular image view
        Picasso.with(this).load(mRepoDetails.mProfileImageLink)
                .transform(new CircularTransform()).into(mRepoOwnerProfileView);
    }

}
