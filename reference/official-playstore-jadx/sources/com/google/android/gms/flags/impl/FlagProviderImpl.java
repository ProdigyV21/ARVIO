package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.d;
import com.google.common.util.concurrent.p0;
import k4.c;
import l4.a;
import qb.l;

/* JADX INFO: loaded from: classes4.dex */
public class FlagProviderImpl extends c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f12977c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SharedPreferences f12978d;

    public FlagProviderImpl() {
        attachInterface(this, "com.google.android.gms.flags.IFlagProvider");
        this.f12977c = false;
    }

    @Override // k4.d
    public boolean getBooleanFlagValue(String str, boolean z, int i10) {
        if (!this.f12977c) {
            return z;
        }
        SharedPreferences sharedPreferences = this.f12978d;
        Boolean boolValueOf = Boolean.valueOf(z);
        try {
            boolValueOf = (Boolean) p0.F(new a(sharedPreferences, str, boolValueOf, 0));
        } catch (Exception e5) {
            String strValueOf = String.valueOf(e5.getMessage());
            Log.w("FlagDataUtils", strValueOf.length() != 0 ? "Flag value not available, returning default: ".concat(strValueOf) : new String("Flag value not available, returning default: "));
        }
        return boolValueOf.booleanValue();
    }

    @Override // k4.d
    public int getIntFlagValue(String str, int i10, int i11) {
        if (!this.f12977c) {
            return i10;
        }
        SharedPreferences sharedPreferences = this.f12978d;
        Integer numValueOf = Integer.valueOf(i10);
        try {
            numValueOf = (Integer) p0.F(new a(sharedPreferences, str, numValueOf, 1));
        } catch (Exception e5) {
            String strValueOf = String.valueOf(e5.getMessage());
            Log.w("FlagDataUtils", strValueOf.length() != 0 ? "Flag value not available, returning default: ".concat(strValueOf) : new String("Flag value not available, returning default: "));
        }
        return numValueOf.intValue();
    }

    @Override // k4.d
    public long getLongFlagValue(String str, long j10, int i10) {
        if (!this.f12977c) {
            return j10;
        }
        SharedPreferences sharedPreferences = this.f12978d;
        Long lValueOf = Long.valueOf(j10);
        try {
            lValueOf = (Long) p0.F(new a(sharedPreferences, str, lValueOf, 2));
        } catch (Exception e5) {
            String strValueOf = String.valueOf(e5.getMessage());
            Log.w("FlagDataUtils", strValueOf.length() != 0 ? "Flag value not available, returning default: ".concat(strValueOf) : new String("Flag value not available, returning default: "));
        }
        return lValueOf.longValue();
    }

    @Override // k4.d
    public String getStringFlagValue(String str, String str2, int i10) {
        if (!this.f12977c) {
            return str2;
        }
        try {
            return (String) p0.F(new a(this.f12978d, str, str2, 3));
        } catch (Exception e5) {
            String strValueOf = String.valueOf(e5.getMessage());
            Log.w("FlagDataUtils", strValueOf.length() != 0 ? "Flag value not available, returning default: ".concat(strValueOf) : new String("Flag value not available, returning default: "));
            return str2;
        }
    }

    @Override // k4.d
    public void init(b bVar) {
        Context context = (Context) d.g0(bVar);
        if (this.f12977c) {
            return;
        }
        try {
            this.f12978d = l.E(context.createPackageContext("com.google.android.gms", 0));
            this.f12977c = true;
        } catch (PackageManager.NameNotFoundException unused) {
        } catch (Exception e5) {
            String strValueOf = String.valueOf(e5.getMessage());
            Log.w("FlagProviderImpl", strValueOf.length() != 0 ? "Could not retrieve sdk flags, continuing with defaults: ".concat(strValueOf) : new String("Could not retrieve sdk flags, continuing with defaults: "));
        }
    }
}
