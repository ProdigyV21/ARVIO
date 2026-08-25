package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.internal.cast.a4;
import com.google.android.gms.internal.cast.b4;
import com.google.android.gms.internal.cast.c4;
import com.google.android.gms.internal.cast.u0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class CastOptions extends a {
    public static final Parcelable.Creator<CastOptions> CREATOR;
    static final zzj zza = new zzj(false);
    static final zzl zzb = new zzl(0);
    static final CastMediaOptions zzc;
    private String zzd;
    private final List zze;
    private final boolean zzf;
    private LaunchOptions zzg;
    private final boolean zzh;
    private final CastMediaOptions zzi;
    private final boolean zzj;
    private final double zzk;
    private final boolean zzl;
    private final boolean zzm;
    private final boolean zzn;
    private final List zzo;
    private final boolean zzp;
    private final boolean zzq;
    private final zzj zzr;
    private zzl zzs;
    private final boolean zzt;
    private final boolean zzu;

    public static final class Builder {
        private String zza;
        private boolean zzc;
        private List zzb = new ArrayList();
        private LaunchOptions zzd = new LaunchOptions();
        private boolean zze = true;
        private b4 zzf = a4.f13168i;
        private boolean zzg = true;
        private double zzh = 0.05000000074505806d;
        private boolean zzi = false;
        private final List zzj = new ArrayList();
        private boolean zzk = true;
        private boolean zzl = false;

        public CastOptions build() {
            CastMediaOptions castMediaOptions = (CastMediaOptions) this.zzf.a(CastOptions.zzc);
            zzj zzjVar = CastOptions.zza;
            u0.p(zzjVar, "use Optional.orNull() instead of Optional.or(null)");
            zzl zzlVar = CastOptions.zzb;
            u0.p(zzlVar, "use Optional.orNull() instead of Optional.or(null)");
            return new CastOptions(this.zza, this.zzb, this.zzc, this.zzd, this.zze, castMediaOptions, this.zzg, this.zzh, false, false, this.zzi, this.zzj, this.zzk, 0, false, zzjVar, zzlVar, false, this.zzl);
        }

        public Builder setCastMediaOptions(CastMediaOptions castMediaOptions) {
            this.zzf = castMediaOptions == null ? a4.f13168i : new c4(castMediaOptions);
            return this;
        }

        public Builder setEnableReconnectionService(boolean z) {
            this.zzg = z;
            return this;
        }

        public Builder setLaunchOptions(LaunchOptions launchOptions) {
            this.zzd = launchOptions;
            return this;
        }

        public Builder setMediaTransferRestrictedToSelfProviders(boolean z) {
            this.zzl = z;
            return this;
        }

        public Builder setReceiverApplicationId(String str) {
            this.zza = str;
            return this;
        }

        public Builder setRemoteToLocalEnabled(boolean z) {
            this.zzi = z;
            return this;
        }

        public Builder setResumeSavedSession(boolean z) {
            this.zze = z;
            return this;
        }

        public Builder setSessionTransferEnabled(boolean z) {
            this.zzk = z;
            return this;
        }

        public Builder setStopReceiverApplicationWhenEndingSession(boolean z) {
            this.zzc = z;
            return this;
        }

        public Builder setSupportedNamespaces(List<String> list) {
            this.zzb = list;
            return this;
        }

        @Deprecated
        public Builder setVolumeDeltaBeforeIceCreamSandwich(double d4) throws IllegalArgumentException {
            if (d4 <= 0.0d || d4 > 0.5d) {
                throw new IllegalArgumentException("volumeDelta must be greater than 0 and less or equal to 0.5");
            }
            this.zzh = d4;
            return this;
        }
    }

    static {
        CastMediaOptions.Builder builder = new CastMediaOptions.Builder();
        builder.setMediaSessionEnabled(false);
        builder.setNotificationOptions(null);
        zzc = builder.build();
        CREATOR = new zzn();
    }

    public CastOptions(String str, List list, boolean z, LaunchOptions launchOptions, boolean z5, CastMediaOptions castMediaOptions, boolean z10, double d4, boolean z11, boolean z12, boolean z13, List list2, boolean z14, int i10, boolean z15, zzj zzjVar, zzl zzlVar, boolean z16, boolean z17) {
        this.zzd = true == TextUtils.isEmpty(str) ? "" : str;
        int size = list == null ? 0 : list.size();
        ArrayList arrayList = new ArrayList(size);
        this.zze = arrayList;
        if (size > 0) {
            arrayList.addAll(list);
        }
        this.zzf = z;
        this.zzg = launchOptions == null ? new LaunchOptions() : launchOptions;
        this.zzh = z5;
        this.zzi = castMediaOptions;
        this.zzj = z10;
        this.zzk = d4;
        this.zzl = z11;
        this.zzm = z12;
        this.zzn = z13;
        this.zzo = list2;
        this.zzp = z14;
        this.zzq = z15;
        this.zzr = zzjVar;
        this.zzs = zzlVar;
        this.zzt = z16;
        this.zzu = z17;
    }

    public CastMediaOptions getCastMediaOptions() {
        return this.zzi;
    }

    public boolean getEnableReconnectionService() {
        return this.zzj;
    }

    public LaunchOptions getLaunchOptions() {
        return this.zzg;
    }

    public String getReceiverApplicationId() {
        return this.zzd;
    }

    public boolean getResumeSavedSession() {
        return this.zzh;
    }

    public boolean getStopReceiverApplicationWhenEndingSession() {
        return this.zzf;
    }

    public List<String> getSupportedNamespaces() {
        return Collections.unmodifiableList(this.zze);
    }

    @Deprecated
    public double getVolumeDeltaBeforeIceCreamSandwich() {
        return this.zzk;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, getReceiverApplicationId(), false);
        a.a.i0(parcel, getSupportedNamespaces(), 3);
        boolean stopReceiverApplicationWhenEndingSession = getStopReceiverApplicationWhenEndingSession();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(stopReceiverApplicationWhenEndingSession ? 1 : 0);
        a.a.f0(parcel, 5, getLaunchOptions(), i10, false);
        boolean resumeSavedSession = getResumeSavedSession();
        a.a.l0(parcel, 6, 4);
        parcel.writeInt(resumeSavedSession ? 1 : 0);
        a.a.f0(parcel, 7, getCastMediaOptions(), i10, false);
        boolean enableReconnectionService = getEnableReconnectionService();
        a.a.l0(parcel, 8, 4);
        parcel.writeInt(enableReconnectionService ? 1 : 0);
        double volumeDeltaBeforeIceCreamSandwich = getVolumeDeltaBeforeIceCreamSandwich();
        a.a.l0(parcel, 9, 8);
        parcel.writeDouble(volumeDeltaBeforeIceCreamSandwich);
        boolean z = this.zzl;
        a.a.l0(parcel, 10, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z5 = this.zzm;
        a.a.l0(parcel, 11, 4);
        parcel.writeInt(z5 ? 1 : 0);
        boolean z10 = this.zzn;
        a.a.l0(parcel, 12, 4);
        parcel.writeInt(z10 ? 1 : 0);
        a.a.i0(parcel, Collections.unmodifiableList(this.zzo), 13);
        boolean z11 = this.zzp;
        a.a.l0(parcel, 14, 4);
        parcel.writeInt(z11 ? 1 : 0);
        a.a.l0(parcel, 15, 4);
        parcel.writeInt(0);
        boolean z12 = this.zzq;
        a.a.l0(parcel, 16, 4);
        parcel.writeInt(z12 ? 1 : 0);
        a.a.f0(parcel, 17, this.zzr, i10, false);
        a.a.f0(parcel, 18, this.zzs, i10, false);
        boolean z13 = this.zzt;
        a.a.l0(parcel, 19, 4);
        parcel.writeInt(z13 ? 1 : 0);
        boolean z14 = this.zzu;
        a.a.l0(parcel, 20, 4);
        parcel.writeInt(z14 ? 1 : 0);
        a.a.n0(iM0, parcel);
    }

    public final void zza(String str) {
        this.zzd = str;
    }

    public final void zzb(LaunchOptions launchOptions) {
        this.zzg = launchOptions;
    }

    public final boolean zzc() {
        return this.zzm;
    }

    public final boolean zzd() {
        return this.zzn;
    }

    public final List zze() {
        return Collections.unmodifiableList(this.zzo);
    }

    public final boolean zzf() {
        return this.zzp;
    }

    public final void zzg(zzl zzlVar) {
        this.zzs = zzlVar;
    }

    public final boolean zzh() {
        return this.zzt;
    }

    public final boolean zzi() {
        return this.zzu;
    }
}
