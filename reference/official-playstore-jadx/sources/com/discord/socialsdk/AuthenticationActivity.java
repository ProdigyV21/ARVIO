package com.discord.socialsdk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import androidx.core.view.r3;
import androidx.core.view.s3;
import androidx.core.view.t3;
import androidx.core.view.x0;
import com.google.common.util.concurrent.r0;
import java.util.ArrayList;
import java.util.Iterator;
import k2.c;
import l6.l;

/* JADX INFO: loaded from: classes4.dex */
public class AuthenticationActivity extends Activity {
    private static final String CALLBACK_PATH = "/authorize/callback";
    private static final String CALLBACK_SCHEME_PREFIX = "discord-";
    private static AuthenticationClientCallback currentCallback;
    private boolean completed = false;
    private boolean hasResumedBefore = false;
    private AuthenticationClientCallback nativeCallback;

    public static void authorize(Activity activity, String str, AuthenticationClientCallback authenticationClientCallback) {
        AuthenticationClientCallback authenticationClientCallback2 = currentCallback;
        if (authenticationClientCallback2 != null) {
            authenticationClientCallback2.onAuthorizationComplete("canceled", "", "");
        }
        currentCallback = authenticationClientCallback;
        Intent intent = new Intent(activity, (Class<?>) AuthenticationActivity.class);
        intent.setData(Uri.parse(str));
        activity.startActivity(intent);
    }

    private boolean handleIntentReceived(Intent intent) {
        Uri data = intent.getData();
        if (data == null || this.completed || data.getScheme() == null || !data.getScheme().startsWith(CALLBACK_SCHEME_PREFIX) || data.getPath() == null || !data.getPath().equals(CALLBACK_PATH)) {
            return false;
        }
        String queryParameter = data.getQueryParameter("code");
        String queryParameter2 = data.getQueryParameter("state");
        String queryParameter3 = data.getQueryParameter("error");
        if (queryParameter3 == null) {
            queryParameter3 = "";
        }
        if (queryParameter == null) {
            queryParameter = "";
        }
        if (queryParameter2 == null) {
            queryParameter2 = "";
        }
        onAuthorizationComplete(queryParameter3, queryParameter, queryParameter2);
        this.completed = true;
        return true;
    }

    private boolean launch(Uri uri) {
        String str;
        PackageManager packageManager = getPackageManager();
        ArrayList arrayList = new ArrayList();
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://")), 0);
        if (resolveInfoResolveActivity != null) {
            String str2 = resolveInfoResolveActivity.activityInfo.packageName;
            ArrayList arrayList2 = new ArrayList(arrayList.size() + 1);
            arrayList2.add(str2);
            arrayList = arrayList2;
        }
        Intent intent = new Intent("android.support.customtabs.action.CustomTabsService");
        Iterator it = arrayList.iterator();
        while (true) {
            if (it.hasNext()) {
                str = (String) it.next();
                intent.setPackage(str);
                if (packageManager.resolveService(intent, 0) != null) {
                    break;
                }
            } else {
                if (Build.VERSION.SDK_INT >= 30) {
                    Log.w("CustomTabsClient", "Unable to find any Custom Tabs packages, you may need to add a <queries> element to your manifest. See the docs for CustomTabsClient#getPackageName.");
                }
                str = null;
            }
        }
        if (str == null) {
            return false;
        }
        int i10 = Resources.getSystem().getDisplayMetrics().heightPixels;
        l lVar = new l();
        Intent intent2 = (Intent) lVar.f20040c;
        intent2.putExtra("org.chromium.chrome.browser.customtabs.EXTRA_DISABLE_STAR_BUTTON", true);
        intent2.putExtra("org.chromium.chrome.browser.customtabs.EXTRA_DISABLE_DOWNLOAD_BUTTON", true);
        lVar.f20038a = 2;
        intent2.putExtra("android.support.customtabs.extra.SHARE_MENU_ITEM", false);
        intent2.putExtra("android.support.customtabs.extra.SEND_TO_EXTERNAL_HANDLER", true);
        intent2.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", 0);
        int i11 = (int) (i10 * 0.9f);
        if (i11 <= 0) {
            throw new IllegalArgumentException("Invalid value for the initialHeightPx argument");
        }
        intent2.putExtra("androidx.browser.customtabs.extra.INITIAL_ACTIVITY_HEIGHT_PX", i11);
        intent2.putExtra("androidx.browser.customtabs.extra.ACTIVITY_HEIGHT_RESIZE_BEHAVIOR", 2);
        Intent intent3 = (Intent) lVar.a().f15416i;
        intent3.setData(uri);
        startActivityForResult(intent3, 0);
        return true;
    }

    private void onAuthorizationComplete(String str, String str2, String str3) {
        currentCallback = null;
        AuthenticationClientCallback authenticationClientCallback = this.nativeCallback;
        if (authenticationClientCallback != null) {
            authenticationClientCallback.onAuthorizationComplete(str, str2, str3);
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        Window window = getWindow();
        View decorView = window.getDecorView();
        boolean z = false;
        r0.D(window, false);
        x0 x0Var = new x0(decorView);
        int i10 = Build.VERSION.SDK_INT;
        c t3Var = i10 >= 30 ? new t3(window, x0Var) : i10 >= 26 ? new s3(window, x0Var) : new r3(window, x0Var);
        t3Var.u();
        t3Var.D();
        if (Build.VERSION.SDK_INT >= 28) {
            window.getAttributes().layoutInDisplayCutoutMode = 1;
        }
        super.onCreate(bundle);
        if (bundle != null) {
            this.hasResumedBefore = bundle.getBoolean("has-resumed-before", false);
            this.completed = bundle.getBoolean("completed", false);
            z = true;
        }
        this.nativeCallback = currentCallback;
        if (this.completed) {
            finish();
            return;
        }
        if (z) {
            return;
        }
        Uri data = getIntent().getData();
        try {
            Intent intent = new Intent("android.intent.action.VIEW", data);
            if (data.toString().startsWith("https://discord.com/oauth2/authorize")) {
                intent.setData(Uri.parse(data.toString().replace("https://discord.com", "discord://action")));
            }
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            if (launch(data)) {
                return;
            }
            startActivity(new Intent("android.intent.action.VIEW", data));
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        if (handleIntentReceived(getIntent())) {
            finish();
        } else if (this.hasResumedBefore) {
            finish();
        } else {
            this.hasResumedBefore = true;
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("has-resumed-before", this.hasResumedBefore);
        bundle.putBoolean("completed", this.completed);
        super.onSaveInstanceState(bundle);
    }
}
