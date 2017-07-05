package com.oxilo.aurion.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import com.oxilo.aurion.R;
import com.oxilo.aurion.base.BaseDrawerActivity;

import static com.oxilo.aurion.R.id.send_email;
import static com.oxilo.aurion.R.id.webview;

public class Web extends BaseDrawerActivity {

    @Nullable
    @BindView(R.id.webview) WebView webView;

    @Nullable
    @BindView(R.id.login_progress)
    ProgressBar mProgressView;

    @Nullable
    @BindView(R.id.image_id)
    ImageView image;

    @Nullable
    @BindView(R.id.title)
    TextView title;

    @Nullable
    @BindView(R.id.office_one)
    Button office_1;

    @Nullable
    @BindView(R.id.office_two) Button office_2;

    @Nullable
    @BindView(R.id.email) Button email;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        if (getIntent()!=null){
            String url = getIntent().getStringExtra("WEB_URL");
            initWebVIew(url);
        }

    }

    private void initWebVIew(String url){
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showProgress(true);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
//                showProgress(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                try {
                    showProgress(false);
//                    webView.loadUrl("javascript:(function() { " +
//                            "document.getElementsByTagName('header')[0].style.display=\"none\"; " +
//                            "document.getElementsByTagName('footer')[0].style.display=\"none\";" +
//                            "document.getElementsByClassName('pg_title_box')[0].style.display = \"none\"; " +
//                            "document.getElementsByClassName('breadcrumb_con')[0].style.display=\"none\";"  +
//                            "document.getElementsByClassName('videos_con')[0].style.display=\"none\";"  +
//                            "document.getElementsByClassName('client_con ac')[0].style.display=\"none\";"  +
//                            "})()");

//                    mLoginFormView.loadUrl("javascript:window.HtmlViewer.showHTML" +
//                            "('<html>'+document.getElementsByClassName('popuphead')[0].innerHTML +'</html>');");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Log.e("PRINT", "" + error.getDescription().toString());
                }

            }


        });
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setBackgroundResource(R.drawable.bg);
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            webView.getSettings().setAllowFileAccessFromFileURLs(true);
            webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }

        /* Register a new JavaScript interface called HTMLOUT */
//        webView.addJavascriptInterface(new MyJavaScriptInterface(WebActivity.this), "HtmlViewer");





        // Other webview options
        /*
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        */


//         String summary = "<html><body>You scored <b>192</b> points.</body></html>";
        if (url.equals("1")) {
            webView.loadUrl("http://dubaifreezonecompany.com/mobapp/assets/about.html");
//            webView.loadDataWithBaseURL("file:///android_asset/", loadFromAsset("about.html").toString(), "text/html", "utf-8", null);
            image.setImageResource(R.drawable.ninth_item);
            title.setText("About");
        }
         else if (url.equals("2")) {
            webView.loadUrl("http://dubaifreezonecompany.com/mobapp/assets/FreeZone.html");
//            webView.loadDataWithBaseURL("file:///android_asset/", loadFromAsset("FreeZone.html").toString(), "text/html", "utf-8", null);
            image.setImageResource(R.drawable.building);
            title.setText("FreeZone");
         }
        else if (url.equals("3")) {
            webView.loadUrl("http://dubaifreezonecompany.com/mobapp/assets/Mainland.html");
//            webView.loadDataWithBaseURL("file:///android_asset/", loadFromAsset("Mainland.html").toString(), "text/html", "utf-8", null);
            image.setImageResource(R.drawable.third_item);
            title.setText("Mainland");
        }else if (url.equals("4")) {
            webView.loadUrl("http://dubaifreezonecompany.com/mobapp/assets/Offshore.html");
//            webView.loadDataWithBaseURL("file:///android_asset/", loadFromAsset("Offshore.html").toString(), "text/html", "utf-8", null);
            image.setImageResource(R.drawable.offshore);
            title.setText("Offshore");
        }else if (url.equals("5")) {
            webView.loadUrl("http://dubaifreezonecompany.com/mobapp/assets/Corporate.html");
//            webView.loadDataWithBaseURL("file:///android_asset/", loadFromAsset("Corporate.html").toString(), "text/html", "utf-8", null);
            image.setImageResource(R.drawable.fifth_item);
            title.setText("Corporate");
        }else if (url.equals("6")) {
            webView.loadUrl("http://dubaifreezonecompany.com/mobapp/assets/ISO.html");
//            webView.loadDataWithBaseURL("file:///android_asset/", loadFromAsset("ISO.html").toString(), "text/html", "utf-8", null);
            image.setImageResource(R.drawable.iso);
            title.setText("ISO");
        }else if (url.equals("7")) {
            webView.loadUrl("http://dubaifreezonecompany.com/mobapp/assets/Accounts.html");
//            webView.loadDataWithBaseURL("file:///android_asset/", loadFromAsset("Accounts.html").toString(), "text/html", "utf-8", null);
            image.setImageResource(R.drawable.second_item);
            title.setText("Accounts");
        }else if (url.equals("8")) {
            webView.loadUrl("http://dubaifreezonecompany.com/mobapp/assets/PRO.html");
//            webView.loadDataWithBaseURL("file:///android_asset/", loadFromAsset("PRO.html").toString(), "text/html", "utf-8", null);
            image.setImageResource(R.drawable.eighth_item);
            title.setText("PRO");
        }else {
            showContactUs(true);
//            webView.loadData(url, "text/html", null);
            image.setImageResource(R.drawable.first_item);
            title.setText("Contact US");
        }
        //Load url in webview
//        webView.loadUrl(url);
    }

    private StringBuilder loadFromAsset(String html){
        BufferedReader in = null;
        StringBuilder buffer = new StringBuilder();

        String assetFile = html;

        try {

            in = new BufferedReader(new InputStreamReader(getAssets().open( assetFile ),"utf-8"));
            String line;

    /* line by line read in the file */
            while ((line = in.readLine()) != null) buffer.append(line);

        } catch (IOException e) {
        } finally {
            try { in.close(); } catch (Exception e) {}
        }

        return buffer;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public  void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

                webView.setVisibility(show ? View.GONE : View.VISIBLE);
                webView.animate().setDuration(shortAnimTime).alpha(
                        show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        webView.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                });

                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                mProgressView.animate().setDuration(shortAnimTime).alpha(
                        show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                    }
                });
            } else {
                // The ViewPropertyAnimator APIs are not available, so simply show
                // and hide the relevant UI components.
                webView.setVisibility(show ? View.VISIBLE : View.GONE);
                webView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @OnClick(R.id.fb)
    public void fbClik(View v){
        boolean facebookAppFound = false;
        String url= "http://www.aurionuae.com/";
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, url);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
        for (final ResolveInfo app : activityList) {
            if ((app.activityInfo.name).contains("facebook")) {
                final ActivityInfo activity = app.activityInfo;
                final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                shareIntent.setComponent(name);
                facebookAppFound = true;
                break;
            }
        }

        if (!facebookAppFound){
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + url;
            shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }

        startActivity(shareIntent);
    }

    @OnClick(R.id.whatapp)
    public void whatsAppClik(View v){
        String url= "http://www.aurionuae.com/";
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(android.content.Intent.EXTRA_TEXT, url);

        PackageManager pm = getApplicationContext().getPackageManager();
        final List<ResolveInfo> matches = pm.queryIntentActivities(sendIntent, 0);
        boolean temWhatsApp = false;
        for (final ResolveInfo info : matches) {
            if (info.activityInfo.packageName.startsWith("com.whatsapp"))  {
                final ComponentName name = new ComponentName(info.activityInfo.applicationInfo.packageName, info.activityInfo.name);
                sendIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                sendIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
                sendIntent.setComponent(name);
                temWhatsApp = true;
                break;
            }
        }

        if(!temWhatsApp) {
            //abre whatsapp
            Toast.makeText(this, "WhatsApp not Installed",
                    Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goToMarket);
        } else {
            startActivity(sendIntent);
        }
    }

    @OnClick(R.id.office_one)
    public void navigateToAddress1(){
        navigateMap(25.326774f,55.496015f,"Office # 15, L2 Bldg - Entrance to Administration Rd - Sharjah - United Arab Emirates");
    }
    @OnClick(R.id.office_two)
    public void navigateToAddress2(){
        navigateMap(25.258960f,55.373561f,"Room No. 213, 6EA, Dubai Airport Freezone - Dubai - United Arab Emirates");
    }
    @OnClick(R.id.email)
    public void contactUs(){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "contact@aurionuae.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private void showContactUs(boolean isOpen){
        if (isOpen){
            office_1.setVisibility(View.VISIBLE);
            office_2.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
        }
        else{
            office_1.setVisibility(View.GONE);
            office_2.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
    }

    private void navigateMap(float lat,float lng,String address){
        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", lat, lng, address);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        try
        {
            startActivity(intent);
        }
        catch(ActivityNotFoundException ex)
        {
            try
            {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(unrestrictedIntent);
            }
            catch(ActivityNotFoundException innerEx)
            {
                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
                Uri uri1 = Uri.parse("market://details?id=com.google.android.apps.maps");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(goToMarket);
            }
        }
    }

    private void openEmailIntent(){

    }
}
