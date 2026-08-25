package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.compose.material3.d;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.t;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

/* JADX INFO: loaded from: classes4.dex */
public class Storage {
    private static final Lock zaa = new ReentrantLock();
    private static Storage zab;
    private final Lock zac = new ReentrantLock();
    private final SharedPreferences zad;

    public Storage(Context context) {
        this.zad = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static Storage getInstance(Context context) {
        t.i(context);
        Lock lock = zaa;
        lock.lock();
        try {
            if (zab == null) {
                zab = new Storage(context.getApplicationContext());
            }
            Storage storage = zab;
            lock.unlock();
            return storage;
        } catch (Throwable th) {
            zaa.unlock();
            throw th;
        }
    }

    private static final String zae(String str, String str2) {
        return d.q(new StringBuilder(str.length() + 1 + String.valueOf(str2).length()), str, ":", str2);
    }

    public void clear() {
        this.zac.lock();
        try {
            this.zad.edit().clear().apply();
        } finally {
            this.zac.unlock();
        }
    }

    public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        String strZab;
        String strZab2 = zab("defaultGoogleSignInAccount");
        if (!TextUtils.isEmpty(strZab2) && (strZab = zab(zae("googleSignInAccount", strZab2))) != null) {
            try {
                return GoogleSignInAccount.zaa(strZab);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
        String strZab;
        String strZab2 = zab("defaultGoogleSignInAccount");
        if (!TextUtils.isEmpty(strZab2) && (strZab = zab(zae("googleSignInOptions", strZab2))) != null) {
            try {
                return GoogleSignInOptions.zaa(strZab);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public String getSavedRefreshToken() {
        return zab("refreshToken");
    }

    public void saveDefaultGoogleSignInAccount(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        t.i(googleSignInAccount);
        t.i(googleSignInOptions);
        zaa("defaultGoogleSignInAccount", googleSignInAccount.zac());
        String strZac = googleSignInAccount.zac();
        zaa(zae("googleSignInAccount", strZac), googleSignInAccount.zad());
        zaa(zae("googleSignInOptions", strZac), googleSignInOptions.zab());
    }

    public final void zaa(String str, String str2) {
        this.zac.lock();
        try {
            this.zad.edit().putString(str, str2).apply();
        } finally {
            this.zac.unlock();
        }
    }

    public final String zab(String str) {
        this.zac.lock();
        try {
            return this.zad.getString(str, null);
        } finally {
            this.zac.unlock();
        }
    }

    public final void zac() {
        String strZab = zab("defaultGoogleSignInAccount");
        zad("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(strZab)) {
            return;
        }
        zad(zae("googleSignInAccount", strZab));
        zad(zae("googleSignInOptions", strZab));
    }

    public final void zad(String str) {
        this.zac.lock();
        try {
            this.zad.edit().remove(str).apply();
        } finally {
            this.zac.unlock();
        }
    }
}
