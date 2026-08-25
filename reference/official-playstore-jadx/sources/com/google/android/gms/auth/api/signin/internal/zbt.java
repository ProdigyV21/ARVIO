package com.google.android.gms.auth.api.signin.internal;

import a8.e;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import e4.c;
import o3.g;
import o3.h;

/* JADX INFO: loaded from: classes4.dex */
public final class zbt extends zbo {
    private final Context zba;

    public zbt(Context context) {
        this.zba = context;
    }

    private final void zbd() {
        AppOpsManager appOpsManager;
        Context context = this.zba;
        int callingUid = Binder.getCallingUid();
        e eVarA = c.a(context);
        eVarA.getClass();
        try {
            appOpsManager = (AppOpsManager) ((Context) eVarA.f183l).getSystemService("appops");
        } catch (SecurityException unused) {
        }
        if (appOpsManager == null) {
            throw new NullPointerException("context.getSystemService(Context.APP_OPS_SERVICE) is null");
        }
        appOpsManager.checkPackage(callingUid, "com.google.android.gms");
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.google.android.gms", 64);
            h hVarA = h.a(context);
            hVarA.getClass();
            if (packageInfo != null) {
                if (h.b(packageInfo, false)) {
                    return;
                }
                if (h.b(packageInfo, true)) {
                    Context context2 = hVarA.f20750a;
                    try {
                        if (!g.f20746c) {
                            try {
                                PackageInfo packageInfo2 = ((Context) c.a(context2).f183l).getPackageManager().getPackageInfo("com.google.android.gms", Build.VERSION.SDK_INT >= 28 ? 134217792 : 64);
                                h.a(context2);
                                if (packageInfo2 == null || h.b(packageInfo2, false) || !h.b(packageInfo2, true)) {
                                    g.f20745b = false;
                                } else {
                                    g.f20745b = true;
                                }
                                g.f20746c = true;
                            } catch (PackageManager.NameNotFoundException e5) {
                                Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", e5);
                                g.f20746c = true;
                            }
                        }
                        if (g.f20745b || !"user".equals(Build.TYPE)) {
                            return;
                        } else {
                            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
                        }
                    } catch (Throwable th) {
                        g.f20746c = true;
                        throw th;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            if (Log.isLoggable("UidVerifier", 3)) {
                Log.d("UidVerifier", "Package manager can't find google play services package, defaulting to false");
            }
        }
        throw new SecurityException(androidx.compose.foundation.c.o(Binder.getCallingUid(), "Calling UID ", " is not Google Play services."));
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zbp
    public final void zbb() {
        zbd();
        zbn.zbc(this.zba).zbd();
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zbp
    public final void zbc() {
        zbd();
        Storage storage = Storage.getInstance(this.zba);
        GoogleSignInAccount savedDefaultGoogleSignInAccount = storage.getSavedDefaultGoogleSignInAccount();
        GoogleSignInOptions savedDefaultGoogleSignInOptions = GoogleSignInOptions.DEFAULT_SIGN_IN;
        if (savedDefaultGoogleSignInAccount != null) {
            savedDefaultGoogleSignInOptions = storage.getSavedDefaultGoogleSignInOptions();
        }
        GoogleSignInClient client = GoogleSignIn.getClient(this.zba, savedDefaultGoogleSignInOptions);
        if (savedDefaultGoogleSignInAccount != null) {
            client.revokeAccess();
        } else {
            client.signOut();
        }
    }
}
