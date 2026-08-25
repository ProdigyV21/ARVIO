package com.google.android.gms.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.internal.a;
import com.google.android.gms.common.api.r;
import com.google.android.gms.tasks.h;

/* JADX INFO: loaded from: classes4.dex */
public interface zzq extends r {
    /* synthetic */ a getApiKey();

    boolean zza();

    void zzb(zzp zzpVar);

    h zzc();

    h zzd();

    h zze();

    h zzf(String str, String str2);

    h zzg(String str, LaunchOptions launchOptions);

    h zzh(String str);

    h zzi(double d4);

    double zzj();

    h zzk(boolean z);

    boolean zzl();

    int zzm();

    int zzn();

    ApplicationMetadata zzo();

    String zzp();

    h zzq(String str, Cast.MessageReceivedCallback messageReceivedCallback);

    h zzr(String str);
}
