package com.example.dmonunu.parkinnantes.utilities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.dmonunu.parkinnantes.activities.HomeActivity;
import com.example.dmonunu.parkinnantes.activities.ListFavoriteActivity;
import com.example.dmonunu.parkinnantes.activities.ListParkingActivity;
import com.example.dmonunu.parkinnantes.activities.LoginActivity;
import com.example.dmonunu.parkinnantes.activities.ParkingNotificationActivity;
import com.example.dmonunu.parkinnantes.activities.ResearchParkingActivity;
import com.example.dmonunu.parkinnantes.auth.ProfilActivity;
import com.example.dmonunu.parkinnantes.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import androidx.appcompat.widget.Toolbar;


public class DrawerUtil {
    public static final String LOAD_METHOD_ID = "load_method_id";
    public static final int LOAD_METHOD_CODE = 92840;

    public static final String LOAD_METHOD_ID2 = "load_method_id";

    public static final int LOAD_METHOD_CODE2 = 92842;

    public static void getDrawer(final Activity activity, Toolbar toolbar) {




        //if you want to update the items at a later time it is recommended to keep it in a variable
        final PrimaryDrawerItem drawerEmptyItem= new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);

        PrimaryDrawerItem login = new PrimaryDrawerItem().withIdentifier(1)
                .withName("SE CONNECTER").withIcon(R.drawable.baseline_forward_24);
        PrimaryDrawerItem accueil = new PrimaryDrawerItem().withIdentifier(2)
                .withName("CARTE DES PARKINGS").withIcon(R.drawable.baseline_place_24);
        PrimaryDrawerItem monprofil = new PrimaryDrawerItem().withIdentifier(3)
                .withName(R.string.manage_player).withIcon(R.drawable.outline_account_circle_24);
        PrimaryDrawerItem drawerItemManagePlayersTournaments = new PrimaryDrawerItem()
                .withIdentifier(4).withName(R.string.list_parking).withIcon(R.drawable.baseline_directions_car_24);
        PrimaryDrawerItem drawerItemSettings = new PrimaryDrawerItem()
                .withIdentifier(5).withName(R.string.favori).withIcon(R.drawable.baseline_favorite_border_24);
        PrimaryDrawerItem drawerItemAbout = new PrimaryDrawerItem().withIdentifier(6)
                .withName(R.string.rechercher).withIcon(R.drawable.baseline_search_24);
        PrimaryDrawerItem drawerItemAbout1 = new PrimaryDrawerItem().withIdentifier(7)
                .withName("GERER LES NOTIFICATIONS").withIcon(R.drawable.baseline_notifications_active_24);
        SecondaryDrawerItem drawerItemAbout2 = new SecondaryDrawerItem().withIdentifier(8);
        SecondaryDrawerItem drawerItemAbout3 = new SecondaryDrawerItem().withIdentifier(9);
        SecondaryDrawerItem drawerItemDelete = new SecondaryDrawerItem().withIdentifier(10)
                .withName("Supprimer mon compte").withIcon(R.drawable.outline_delete_forever_24);
        SecondaryDrawerItem drawerItemDeconnexion = new SecondaryDrawerItem().withIdentifier(11)
                .withName("Se déconnecter").withIcon(R.drawable.outline_exit_to_app_24);


        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                        drawerEmptyItem,
                        new DividerDrawerItem(),
                        login,
                        new DividerDrawerItem(),
                        accueil,
                        new DividerDrawerItem(),
                        monprofil,
                        new DividerDrawerItem(),
                        drawerItemManagePlayersTournaments,
                        new DividerDrawerItem(),
                        drawerItemAbout,
                        new DividerDrawerItem(),
                        drawerItemSettings,
                        new DividerDrawerItem(),
                        drawerItemAbout1,
                        new DividerDrawerItem(),
                        drawerItemAbout2,
                        drawerItemAbout3,

                        new DividerDrawerItem(),

                        drawerItemDelete,
                        drawerItemDeconnexion
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 1 && !(activity instanceof LoginActivity)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, LoginActivity.class);
                            view.getContext().startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 2 && !(activity instanceof HomeActivity)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, HomeActivity.class);
                            view.getContext().startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 3 && !(activity instanceof ProfilActivity)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, ProfilActivity.class);
                            view.getContext().startActivity(intent);
                        }

                        if (drawerItem.getIdentifier() == 4 && !(activity instanceof ListParkingActivity)) {
                            Intent intent = new Intent(activity, ListParkingActivity.class);
                            view.getContext().startActivity(intent);
                        }

                        if (drawerItem.getIdentifier() == 6 && !(activity instanceof ResearchParkingActivity)) {
                            Intent intent = new Intent(activity, ResearchParkingActivity.class);
                            view.getContext().startActivity(intent);
                        }

                        if (drawerItem.getIdentifier() == 7 && !(activity instanceof ParkingNotificationActivity)) {
                            Intent intent = new Intent(activity, ParkingNotificationActivity.class);
                            view.getContext().startActivity(intent);
                        }

                        if (drawerItem.getIdentifier() == 5 && !(activity instanceof ListFavoriteActivity)) {
                            Intent intent = new Intent(activity, ListFavoriteActivity.class);
                            view.getContext().startActivity(intent);
                        }

                        if (drawerItem.getIdentifier() == 10 && !(activity instanceof ProfilActivity)){
                            Intent intent = new Intent(activity, ProfilActivity.class);
                            intent.putExtra(LOAD_METHOD_ID, LOAD_METHOD_CODE);
                            view.getContext().startActivity(intent);
                        }

                        if (drawerItem.getIdentifier() == 11 && !(activity instanceof ProfilActivity)){
                            Intent intent = new Intent(activity, ProfilActivity.class);
                            intent.putExtra(LOAD_METHOD_ID2, LOAD_METHOD_CODE2);
                            view.getContext().startActivity(intent);
                        }
                        return true;
                    }
                })
                .build();
    }
}