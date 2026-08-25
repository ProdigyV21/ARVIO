package com.google.android.gms.cast;

import a0.c;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.Arrays;
import java.util.Locale;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class LaunchOptions extends a {
    public static final Parcelable.Creator<LaunchOptions> CREATOR = new zzbp();
    private boolean zza;
    private String zzb;
    private boolean zzc;
    private CredentialsData zzd;

    public static final class Builder {
        private final LaunchOptions zza;

        public Builder() {
            this.zza = new LaunchOptions();
        }

        public LaunchOptions build() {
            return this.zza;
        }

        public Builder setAndroidReceiverCompatible(boolean z) {
            this.zza.zza(z);
            return this;
        }

        public Builder setCredentialsData(CredentialsData credentialsData) {
            this.zza.zzb(credentialsData);
            return this;
        }

        public Builder setLocale(Locale locale) {
            this.zza.setLanguage(CastUtils.zzd(locale));
            return this;
        }

        public Builder setRelaunchIfRunning(boolean z) {
            this.zza.setRelaunchIfRunning(z);
            return this;
        }

        public Builder(LaunchOptions launchOptions) {
            this.zza = new LaunchOptions(launchOptions.getRelaunchIfRunning(), launchOptions.getLanguage(), launchOptions.getAndroidReceiverCompatible(), launchOptions.getCredentialsData());
        }
    }

    public LaunchOptions(boolean z, String str, boolean z5, CredentialsData credentialsData) {
        this.zza = z;
        this.zzb = str;
        this.zzc = z5;
        this.zzd = credentialsData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LaunchOptions)) {
            return false;
        }
        LaunchOptions launchOptions = (LaunchOptions) obj;
        return this.zza == launchOptions.zza && CastUtils.zza(this.zzb, launchOptions.zzb) && this.zzc == launchOptions.zzc && CastUtils.zza(this.zzd, launchOptions.zzd);
    }

    public boolean getAndroidReceiverCompatible() {
        return this.zzc;
    }

    public CredentialsData getCredentialsData() {
        return this.zzd;
    }

    public String getLanguage() {
        return this.zzb;
    }

    public boolean getRelaunchIfRunning() {
        return this.zza;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.zza), this.zzb, Boolean.valueOf(this.zzc), this.zzd});
    }

    public void setLanguage(String str) {
        this.zzb = str;
    }

    public void setRelaunchIfRunning(boolean z) {
        this.zza = z;
    }

    public String toString() {
        boolean z = this.zza;
        String str = this.zzb;
        boolean z5 = this.zzc;
        StringBuilder sb2 = new StringBuilder("LaunchOptions(relaunchIfRunning=");
        sb2.append(z);
        sb2.append(", language=");
        sb2.append(str);
        sb2.append(", androidReceiverCompatible: ");
        return c.m(")", z5, sb2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        boolean relaunchIfRunning = getRelaunchIfRunning();
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(relaunchIfRunning ? 1 : 0);
        a.a.g0(parcel, 3, getLanguage(), false);
        boolean androidReceiverCompatible = getAndroidReceiverCompatible();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(androidReceiverCompatible ? 1 : 0);
        a.a.f0(parcel, 5, getCredentialsData(), i10, false);
        a.a.n0(iM0, parcel);
    }

    public final void zza(boolean z) {
        this.zzc = z;
    }

    public final /* synthetic */ void zzb(CredentialsData credentialsData) {
        this.zzd = credentialsData;
    }

    public LaunchOptions() {
        this(false, CastUtils.zzd(Locale.getDefault()), false, null);
    }
}
