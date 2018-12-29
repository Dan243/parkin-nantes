package com.example.dmonunu.parkinnantes.auth;

import androidx.appcompat.app.AlertDialog;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dmonunu.parkinnantes.activities.LoginActivity;
import com.example.dmonunu.parkinnantes.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;

import static com.example.dmonunu.parkinnantes.utilities.DrawerUtil.LOAD_METHOD_CODE;
import static com.example.dmonunu.parkinnantes.utilities.DrawerUtil.LOAD_METHOD_CODE2;
import static com.example.dmonunu.parkinnantes.utilities.DrawerUtil.LOAD_METHOD_ID;
import static com.example.dmonunu.parkinnantes.utilities.DrawerUtil.LOAD_METHOD_ID2;

public class ProfilActivity extends BaseActivity {

    //FOR DESIGN
    @BindView(R.id.profile_activity_imageview_profile)
    ImageView imageViewProfile;
    @BindView(R.id.profile_activity_edit_text_username)
    TextView textInputEditTextUsername;
    @BindView(R.id.profile_activity_text_view_email)
    TextView textViewEmail;

    private static final int SIGN_OUT_TASK = 10;
    private static final int DELETE_USER_TASK = 20;

    /*
    @BindView(R.id.profile_activity_button_update)
    Button update;
    @BindView(R.id.profile_activity_button_sign_out)
    Button signout;
    @BindView(R.id.profile_activity_button_delete)
    Button delete;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.updateUIWhenCreating();

        int code = getIntent().getIntExtra(LOAD_METHOD_ID, 0);
        if (code == LOAD_METHOD_CODE) {
            this.onClickSignOutButton();
        }

        int code2 = getIntent().getIntExtra(LOAD_METHOD_ID2, 0);
        if (code == LOAD_METHOD_CODE2) {
            this.onClickDeleteButton();
        }



    }

    @Override
    public int getFragmentLayout() { return R.layout.activity_profil; }


    private void updateUIWhenCreating(){

        if (this.getCurrentUser() != null){

            //Get picture URL from Firebase
            if (this.getCurrentUser().getPhotoUrl() != null) {
                Glide.with(this)
                        .load(this.getCurrentUser().getPhotoUrl())
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageViewProfile);
            }

            //Get email & username from Firebase
            String email = TextUtils.isEmpty(this.getCurrentUser().getEmail()) ? getString(R.string.info_no_email_found) : this.getCurrentUser().getEmail();
            String username = TextUtils.isEmpty(this.getCurrentUser().getDisplayName()) ? getString(R.string.info_no_username_found) : this.getCurrentUser().getDisplayName();

            //Update views with data
            this.textInputEditTextUsername.setText(username);
            this.textViewEmail.setText(email);
        }
    }

    private void signOutUserFromFirebase(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(SIGN_OUT_TASK));
    }

    private void deleteUserFromFirebase(){
        if (this.getCurrentUser() != null) {
            AuthUI.getInstance()
                    .delete(this)
                    .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(DELETE_USER_TASK));
        }
    }

    private OnSuccessListener<Void> updateUIAfterRESTRequestsCompleted(final int origin){
        return new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                switch (origin){
                    case SIGN_OUT_TASK:
                        finish();
                        break;
                    case DELETE_USER_TASK:
                        finish();
                        break;
                    default:
                        break;
                }
            }
        };
    }


    public void onClickSignOutButton() {
        this.signOutUserFromFirebase();
        Intent deconnexion = new Intent(this, LoginActivity.class);
        startActivity(deconnexion);
    }


    public void onClickDeleteButton() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.popup_message_confirmation_delete_account)
                .setPositiveButton(R.string.popup_message_choice_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteUserFromFirebase();
                    }
                })
                .setNegativeButton(R.string.popup_message_choice_no, null)
                .show();

        Intent deconnexion = new Intent(this, LoginActivity.class);
        startActivity(deconnexion);
    }

}