package com.example.shamsad.tutionhub.utilities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.shamsad.tutionhub.R;
import com.example.shamsad.tutionhub.activities.AboutActivity;
import com.example.shamsad.tutionhub.activities.DonateActivity;
import com.example.shamsad.tutionhub.activities.FeedActivity;
import com.example.shamsad.tutionhub.activities.HelpActivity;
import com.example.shamsad.tutionhub.activities.LogOutActivity;
import com.example.shamsad.tutionhub.activities.ProfileActivity;
import com.example.shamsad.tutionhub.activities.SettingsActivity;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

/**
 * Created by shamsad on 11/13/17.
 */

public class DrawerUtil extends Activity{
    public static void getDrawer(final Activity activity, Toolbar toolbar) {

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header) // header is in drawable folder
                .addProfiles(
                        new ProfileDrawerItem().withName("Sajid Samsad").withEmail("samsadsajid@gmail.com").withIcon(R.drawable.ami) // profile is in drawable folder
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        // lagbe na. Omit it
//        PrimaryDrawerItem drawerEmptyItem= new PrimaryDrawerItem().withIdentifier(0).withName("");
//        drawerEmptyItem.withEnabled(false);

        // lagbe
        PrimaryDrawerItem profile = new PrimaryDrawerItem().withIdentifier(1)
                .withName(R.string.profile).withIcon(GoogleMaterial.Icon.gmd_account_circle);

        PrimaryDrawerItem feed = new PrimaryDrawerItem().withIdentifier(2)
                .withName(R.string.feed).withIcon(GoogleMaterial.Icon.gmd_rss_feed);

        SecondaryDrawerItem drawerItemSettings = new SecondaryDrawerItem().withIdentifier(3)
                .withName(R.string.settings).withIcon(GoogleMaterial.Icon.gmd_settings);

        SecondaryDrawerItem logout = new SecondaryDrawerItem().withIdentifier(4)
                .withName(R.string.logout).withIcon(GoogleMaterial.Icon.gmd_offline_pin);

        SecondaryDrawerItem drawerItemAbout = new SecondaryDrawerItem().withIdentifier(5)
                .withName(R.string.about).withIcon(GoogleMaterial.Icon.gmd_info);

        SecondaryDrawerItem drawerItemHelp = new SecondaryDrawerItem().withIdentifier(6)
                .withName(R.string.help).withIcon(GoogleMaterial.Icon.gmd_help);






        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                        profile,
                        feed,
                        new DividerDrawerItem(),
                        drawerItemSettings,
                        logout,
                        new DividerDrawerItem(),
                        drawerItemAbout,
                        drawerItemHelp
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = null;
                        if (drawerItem.getIdentifier() == 1) {
                            // load profile screen
                            intent = new Intent(activity, ProfileActivity.class);
                        }else if (drawerItem.getIdentifier() == 2) {
                            // load feed screen
                            intent = new Intent(activity, FeedActivity.class);
                        } else if (drawerItem.getIdentifier() == 3) {
                            // load settings screen
                            intent = new Intent(activity, SettingsActivity.class);
                        } else if (drawerItem.getIdentifier() == 4) {
                            // load logout screen
                            intent = new Intent(activity, LogOutActivity.class);
                        } else if (drawerItem.getIdentifier() == 5) {
                            // load about screen
                            intent = new Intent(activity, AboutActivity.class);
                        }else if (drawerItem.getIdentifier() == 6) {
                            // load help screen
                            intent = new Intent(activity, HelpActivity.class);
                        }

                        if (intent != null) {
                            activity.startActivity(intent);
                        }

                        return false; // or true?? not sure!!??
                    }
                })
                .build();
    }
}
