package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import com.arvio.tv.R;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t.l0 f12833a = new t.l0(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Locale f12834b;

    public static String a(Context context, int i10) {
        Resources resources = context.getResources();
        switch (i10) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_title);
            case 2:
                return resources.getString(R.string.common_google_play_services_update_title);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return e(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return e(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 12:
            case 13:
            case 14:
            case 15:
            case 19:
            default:
                StringBuilder sb2 = new StringBuilder(String.valueOf(i10).length() + 22);
                sb2.append("Unexpected error code ");
                sb2.append(i10);
                Log.e("GoogleApiAvailability", sb2.toString());
                return null;
            case 16:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return e(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return e(context, "common_google_play_services_restricted_profile_title");
        }
    }

    public static String b(Context context, int i10) {
        Resources resources = context.getResources();
        String strC = c(context);
        if (i10 == 1) {
            return resources.getString(R.string.common_google_play_services_install_text, strC);
        }
        if (i10 == 2) {
            return c4.d.d(context) ? resources.getString(R.string.common_google_play_services_wear_update_text) : resources.getString(R.string.common_google_play_services_update_text, strC);
        }
        if (i10 == 3) {
            return resources.getString(R.string.common_google_play_services_enable_text, strC);
        }
        if (i10 == 5) {
            return d(context, "common_google_play_services_invalid_account_text", strC);
        }
        if (i10 == 7) {
            return d(context, "common_google_play_services_network_error_text", strC);
        }
        if (i10 == 9) {
            return resources.getString(R.string.common_google_play_services_unsupported_text, strC);
        }
        if (i10 == 20) {
            return d(context, "common_google_play_services_restricted_profile_text", strC);
        }
        switch (i10) {
            case 16:
                return d(context, "common_google_play_services_api_unavailable_text", strC);
            case 17:
                return d(context, "common_google_play_services_sign_in_failed_text", strC);
            case 18:
                return resources.getString(R.string.common_google_play_services_updating_text, strC);
            default:
                return resources.getString(R.string.common_google_play_services_unknown_issue, strC);
        }
    }

    public static String c(Context context) {
        String packageName = context.getPackageName();
        try {
            Context context2 = (Context) e4.c.a(context).f183l;
            return context2.getPackageManager().getApplicationLabel(context2.getPackageManager().getApplicationInfo(packageName, 0)).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            return TextUtils.isEmpty(str) ? packageName : str;
        }
    }

    public static String d(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String strE = e(context, str);
        if (strE == null) {
            strE = resources.getString(R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, strE, str2);
    }

    public static String e(Context context, String str) {
        Resources resourcesForApplication;
        t.l0 l0Var = f12833a;
        synchronized (l0Var) {
            try {
                Locale localeC = xc.d.m0(context.getResources().getConfiguration()).c(0);
                if (!localeC.equals(f12834b)) {
                    l0Var.clear();
                    f12834b = localeC;
                }
                String str2 = (String) l0Var.get(str);
                if (str2 != null) {
                    return str2;
                }
                int i10 = o3.g.f20748e;
                try {
                    resourcesForApplication = context.getPackageManager().getResourcesForApplication("com.google.android.gms");
                } catch (PackageManager.NameNotFoundException unused) {
                    resourcesForApplication = null;
                }
                if (resourcesForApplication != null) {
                    int identifier = resourcesForApplication.getIdentifier(str, "string", "com.google.android.gms");
                    if (identifier == 0) {
                        StringBuilder sb2 = new StringBuilder(str.length() + 18);
                        sb2.append("Missing resource: ");
                        sb2.append(str);
                        Log.w("GoogleApiAvailability", sb2.toString());
                    } else {
                        String string = resourcesForApplication.getString(identifier);
                        if (!TextUtils.isEmpty(string)) {
                            l0Var.put(str, string);
                            return string;
                        }
                        StringBuilder sb3 = new StringBuilder(str.length() + 20);
                        sb3.append("Got empty resource: ");
                        sb3.append(str);
                        Log.w("GoogleApiAvailability", sb3.toString());
                    }
                }
                return null;
            } finally {
            }
        }
    }
}
