package com.oxilo.aurion.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;


import butterknife.BindView;
import com.oxilo.aurion.R;
import com.oxilo.aurion.activity.MainActivity;
import com.oxilo.aurion.activity.Web;

/**
 * Created by Nirmal Kumar on 1.09.16.
 */
public class BaseDrawerActivity extends BaseActivity {

    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.vNavigation)
    NavigationView vNavigation;

    private ActionBarDrawerToggle mDrawerToggle;
    private float lastScale = 1.0f;

    //Cannot be bound via Butterknife, hosting view is initialized later (see setupHeader() method)
    private ImageView ivMenuUserProfilePhoto;
    FrameLayout viewGroup;
    ProgressDialog progressDialog;
    @Override
    public void setContentView(int layoutResID) {
        super.setContentViewWithoutInject(R.layout.activity_drawer);
         viewGroup = (FrameLayout) findViewById(R.id.flContentRoot);
        LayoutInflater.from(this).inflate(layoutResID, viewGroup, true);
        bindViews();
//      setupHeader();
        setUpDrawable();
        onGlobalMenuItemClick();
    }

    @Override
    protected void setupToolbar() {
        super.setupToolbar();
        if (getToolbar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
//            getToolbar().setNavigationIcon(R.drawable.vector_drawable_ic_menu_white___px);
//            getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    drawerLayout.openDrawer(Gravity.RIGHT);
//                }
//            });
        }
    }




    public void onGlobalMenuItemClick(){
        vNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                int id = item.getItemId();
                switch (id){
                    case R.id.about:
                        clickAction("1");
                        break;
                    case R.id.free_zone:
                        clickAction("2");
                        break;
                    case R.id.id_main_land_company:
                        clickAction("3");
                        break;
                    case R.id.offshore_company:
                        clickAction("4");
                        break;
                    case R.id.coorporate_identity:
                        clickAction("5");
                        break;
                    case R.id.iso_consultation:
                        clickAction("6");
                        break;
                    case R.id.account_auditing:
                        clickAction("7");
                        break;
                    case R.id.pro_service:
                        clickAction("8");
                        break;
                    case R.id.contact_us:
                        clickAction("http://www.aurionuae.com/contact.html");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void clickAction(final String url){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(BaseDrawerActivity.this,Web.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("WEB_URL",url);
                startActivity(i);
                finish();
            }
        }, 300);
//        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }


    private void showDialog(){
        progressDialog = new ProgressDialog(BaseDrawerActivity.this);
        progressDialog.setMessage("Please wait while we are signing you out...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }




    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void startLoginActivity(){
        Intent i = new Intent(BaseDrawerActivity.this, MainActivity.class);
        startActivity(i);
        finishAffinity();
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }


//    private void setupHeader() {
//        View headerView = vNavigation.getHeaderView(0);
//        ivMenuUserProfilePhoto = (ImageView) headerView.findViewById(R.id.ivMenuUserProfilePhoto);
//        headerView.findViewById(R.id.vGlobalMenuHeader).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onGlobalMenuHeaderClick(v);
//            }
//        });
//
//        ivMenuUserProfilePhoto.setImageResource(R.drawable.ic_profile);
//
//    }

//    public void onGlobalMenuHeaderClick(final View v) {
//        drawerLayout.closeDrawer(Gravity.LEFT);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 200);
//    }

    private void setUpDrawable(){
        mDrawerToggle = new ActionBarDrawerToggle(BaseDrawerActivity.this,drawerLayout,R.string.app_name,R.string.app_name){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
            }

//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                super.onDrawerSlide(drawerView, slideOffset);
//                float min = 0.85f;
//                float max = 1.0f;
//                float scaleFactor = (max - ((max - min) * slideOffset));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
//                {
//                    viewGroup.setScaleX(scaleFactor);
//                    viewGroup.setScaleY(scaleFactor);
//                }
//                else
//                {
//                    ScaleAnimation anim = new ScaleAnimation(lastScale, scaleFactor, lastScale, scaleFactor, viewGroup.getWidth()/2, viewGroup.getHeight()/2);
//                    anim.setDuration(0);
//                    anim.setFillAfter(true);
//                    viewGroup.startAnimation(anim);
//
//                    lastScale = scaleFactor;
//                }
//                viewGroup.setTranslationX(slideOffset * drawerView.getWidth());
//                drawerLayout.bringChildToFront(drawerView);
//                drawerLayout.requestLayout();
//            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                supportInvalidateOptionsMenu();
            }
        };
        try {
            drawerLayout.addDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        }catch (Exception ex){
            ex.printStackTrace();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuToUse = R.menu.right_side_menu;

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(menuToUse, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnMyMenu:
                openDrawer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }





    public void openDrawer(){
        drawerLayout.openDrawer(Gravity.RIGHT);
    }
    public void closeDrawer(){
        drawerLayout.closeDrawers();
    }
}
