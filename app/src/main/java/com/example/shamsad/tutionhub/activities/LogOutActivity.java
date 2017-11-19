package com.example.shamsad.tutionhub.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.shamsad.tutionhub.utilities.DrawerUtil;
import com.example.shamsad.tutionhub.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogOutActivity extends AppCompatActivity implements View.OnClickListener{

	// [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

	private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);

		Toolbar toolbar = findViewById(R.id.tbLogOut);
        toolbar.setTitle(R.string.logout);

        // Navigation Drawer
        DrawerUtil.getDrawer(this,toolbar);

		// get Instance
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


		TextView textView1 = findViewById(R.id.logout_dummy_text1);
    	textView1.setText(R.string.farewell1);

        TextView textView2 = findViewById(R.id.logout_dummy_text2);
        textView2.setText(R.string.farewell2);

        findViewById(R.id.sign_out).setVisibility(View.VISIBLE);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
    }

	@Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.sign_out_button) {
            signOut();
        }
    }

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
