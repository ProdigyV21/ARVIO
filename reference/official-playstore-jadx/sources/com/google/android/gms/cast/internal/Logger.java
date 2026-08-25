package com.google.android.gms.cast.internal;

import a0.c;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.t;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class Logger {
    protected final String zza;
    private final boolean zzb;
    private final String zzc;
    private boolean zzd;

    public Logger(String str) {
        this(str, null);
    }

    private static final boolean zzd() {
        return !Build.TYPE.equals("user");
    }

    public void d(String str, Object... objArr) {
        if (zza()) {
            Log.d(this.zza, zzc(str, objArr));
        }
    }

    public void e(String str, Object... objArr) {
        Log.e(this.zza, zzc(str, objArr));
    }

    public void i(String str, Object... objArr) {
        Log.i(this.zza, zzc(str, objArr));
    }

    public void v(String str, Object... objArr) {
        if (zzd() && this.zzb) {
            String str2 = this.zza;
            if (Log.isLoggable(str2, 2)) {
                Log.v(str2, zzc(str, objArr));
            }
        }
    }

    public void w(String str, Object... objArr) {
        Log.w(this.zza, zzc(str, objArr));
    }

    public final boolean zza() {
        if (!zzd()) {
            return false;
        }
        if (this.zzd) {
            return true;
        }
        return this.zzb && Log.isLoggable(this.zza, 3);
    }

    public final void zzb(boolean z) {
        this.zzd = true;
    }

    public final String zzc(String str, Object... objArr) {
        if (objArr.length != 0) {
            str = String.format(Locale.ROOT, str, objArr);
        }
        String str2 = this.zzc;
        String strL = TextUtils.isEmpty(str2) ? "" : c.l("[", str2, "] ");
        return !TextUtils.isEmpty(strL) ? strL.concat(String.valueOf(str)) : str;
    }

    public Logger(String str, String str2) {
        t.g(str, "The log tag cannot be null or empty.");
        this.zza = str;
        this.zzc = str2;
        this.zzb = str.length() <= 23;
        this.zzd = false;
    }

    public void e(Throwable th, String str, Object... objArr) {
        Log.e(this.zza, zzc(str, objArr), th);
    }

    public void i(Throwable th, String str, Object... objArr) {
        Log.i(this.zza, zzc(str, objArr), th);
    }

    public void w(Throwable th, String str, Object... objArr) {
        Log.w(this.zza, zzc(str, objArr), th);
    }

    public void d(Throwable th, String str, Object... objArr) {
        if (zza()) {
            Log.d(this.zza, zzc(str, objArr), th);
        }
    }
}
