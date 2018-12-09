package com.example.dmonunu.parkinnantes.activities;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dmonunu.parkinnantes.auth.BaseActivity;
import com.example.dmonunu.parkinnantes.auth.ProfilActivity;
import com.example.dmonunu.parkinnantes.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;


    private static final int RC_SIGN_IN = 123;


    private void startSignInActivity(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(),
                                        new AuthUI.IdpConfig.GoogleBuilder().build(),
                                        new AuthUI.IdpConfig.FacebookBuilder().build()))
                        .setIsSmartLockEnabled(false, true)
                        //.setLogo(R.drawable.ic_logo_auth)
                        .build(),
                RC_SIGN_IN);
    }




    // 2 - Show Snack Bar with a message
    private void showSnackBar(CoordinatorLayout coordinatorLayout, String message){
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }


    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data) {

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) { // SUCCESS
                showSnackBar(this.coordinatorLayout, getString(R.string.connection_succeed));
                Intent changePage = new Intent(this, HomeActivity.class);
                startActivity(changePage);
            } else { // ERRORS
                if (response == null) {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_authentication_canceled));
                } else if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_no_internet));
                } else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_unknown_error));
                }
            }
        }
    }

    // 2 - Update UI when activity is resuming
    /*
    private void updateUIWhenResuming(){
        this.mbutton.setText(this.isCurrentUserLogged() ? getString(R.string.button_login_text_logged) : getString(R.string.button_login_text_not_logged));
    }
    */

    // 3 - Launching Profile Activity
    private void startProfileActivity(){
        Intent intent = new Intent(this, ProfilActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        this.startSignInActivity();


    }

    @Override
    public int getFragmentLayout() { return R.layout.activity_login; }

    @Override
    protected void onResume() {
        super.onResume();
     //   this.updateUIWhenResuming();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 4 - Handle SignIn Activity response on activity result
        this.handleResponseAfterSignIn(requestCode, resultCode, data);
    }
}