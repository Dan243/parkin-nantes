package com.example.dmonunu.parkinnantes.utilities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

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

        PrimaryDrawerItem drawerItemManagePlayers = new PrimaryDrawerItem().withIdentifier(1)
                .withName(R.string.manage_player).withIcon(R.drawable.outline_account_circle_24);
        PrimaryDrawerItem drawerItemManagePlayersTournaments = new PrimaryDrawerItem()
                .withIdentifier(2).withName(R.string.tournament);


        SecondaryDrawerItem drawerItemSettings = new SecondaryDrawerItem().withIdentifier(3);

        SecondaryDrawerItem drawerItemAbout = new SecondaryDrawerItem().withIdentifier(4);
        SecondaryDrawerItem drawerItemAbout1 = new SecondaryDrawerItem().withIdentifier(5);
        SecondaryDrawerItem drawerItemAbout2 = new SecondaryDrawerItem().withIdentifier(6);
        SecondaryDrawerItem drawerItemAbout3 = new SecondaryDrawerItem().withIdentifier(7);
        SecondaryDrawerItem drawerItemAbout4 = new SecondaryDrawerItem().withIdentifier(8);
        SecondaryDrawerItem drawerItemAbout5 = new SecondaryDrawerItem().withIdentifier(9);
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
                        drawerEmptyItem,drawerEmptyItem,drawerEmptyItem,
                        drawerItemManagePlayers,
                        drawerItemManagePlayersTournaments,
                        new DividerDrawerItem(),
                        drawerItemAbout,
                        drawerItemAbout1,
                        drawerItemAbout2,
                        drawerItemAbout3,
                        drawerItemAbout4,
                        drawerItemAbout5,
                        drawerItemSettings,
                        new DividerDrawerItem(),
                        drawerItemDelete,
                        drawerItemDeconnexion
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 1 && !(activity instanceof ProfilActivity)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, ProfilActivity.class);
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
