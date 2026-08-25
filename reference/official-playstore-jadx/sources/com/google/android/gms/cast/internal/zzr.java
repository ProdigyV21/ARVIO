package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class zzr implements Cast.ApplicationConnectionResult {
    private final Status zza;
    private final ApplicationMetadata zzb;
    private final String zzc;
    private final String zzd;
    private final boolean zze;

    public zzr(Status status, ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        this.zza = status;
        this.zzb = applicationMetadata;
        this.zzc = str;
        this.zzd = str2;
        this.zze = z;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final ApplicationMetadata getApplicationMetadata() {
        return this.zzb;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final String getApplicationStatus() {
        return this.zzc;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final String getSessionId() {
        return this.zzd;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult, com.google.android.gms.common.api.x
    public final Status getStatus() {
        return this.zza;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final boolean getWasLaunched() {
        return this.zze;
    }
}
