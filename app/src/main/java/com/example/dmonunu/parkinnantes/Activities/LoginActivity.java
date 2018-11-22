package com.example.dmonunu.parkinnantes.Activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;

import com.example.dmonunu.parkinnantes.R;
import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    //FOR DATA
    // 1 - Identifier for Sign-In Activity
    private static final int RC_SIGN_IN = 123;

    // --------------------
    // ACTIONS
    // --------------------

    @OnClick(R.id.button)
    public void onClickLoginButton() {
        // 3 - Launch Sign-In Activity when user clicked on Login Button
        this.startSignInActivity();
    }

    // 2 - Launch Sign-In Activity
    private void startSignInActivity(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                        .setIsSmartLockEnabled(false, true)
                        //.setLogo(R.drawable.ic_logo_auth)
                        .build(),
                RC_SIGN_IN);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
}