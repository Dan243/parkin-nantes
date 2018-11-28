package com.example.dmonunu.parkinnantes.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;

import com.example.dmonunu.parkinnantes.Activities.HomeActivity;
import com.example.dmonunu.parkinnantes.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import androidx.appcompat.widget.Toolbar;


public class DrawerUtil {
    public static void getDrawer(final Activity activity, Toolbar toolbar) {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem drawerEmptyItem= new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);

        PrimaryDrawerItem drawerItemManagePlayers = new PrimaryDrawerItem().withIdentifier(1)
                .withName(R.string.manage_player);
        PrimaryDrawerItem drawerItemManagePlayersTournaments = new PrimaryDrawerItem()
                .withIdentifier(2).withName(R.string.tournament);


        SecondaryDrawerItem drawerItemSettings = new SecondaryDrawerItem().withIdentifier(3)
                .withName(R.string.settings);
        SecondaryDrawerItem drawerItemAbout = new SecondaryDrawerItem().withIdentifier(4)
                .withName(R.string.about);
        SecondaryDrawerItem drawerItemHelp = new SecondaryDrawerItem().withIdentifier(5)
                .withName(R.string.help);
        SecondaryDrawerItem drawerItemDonate = new SecondaryDrawerItem().withIdentifier(6)
                .withName(R.string.donate);


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
                        drawerItemSettings,
                        drawerItemHelp,
                        drawerItemDonate
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 2 && !(activity instanceof HomeActivity)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, HomeActivity.class);
                            view.getContext().startActivity(intent);
                        }
                        return true;
                    }
                })
                .build();
    }
}
