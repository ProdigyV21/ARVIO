package com.google.android.gms.cast;

import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
final class zzs {
    private String zza = "com.google.android.gms.cast.CATEGORY_CAST";
    private String zzb;
    private Collection zzc;

    private zzs() {
    }

    public final /* synthetic */ zzs zza(String str) {
        this.zza = "com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK";
        return this;
    }

    public final /* synthetic */ zzs zzb(String str) {
        this.zzb = str;
        return this;
    }

    public final /* synthetic */ zzs zzc(Collection collection) {
        this.zzc = collection;
        return this;
    }

    public final /* synthetic */ zzt zzd() {
        return new zzt(this.zza, this.zzb, this.zzc, false, true, null);
    }

    public /* synthetic */ zzs(byte[] bArr) {
    }
}
