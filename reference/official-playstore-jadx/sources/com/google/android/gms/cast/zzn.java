package com.google.android.gms.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzn implements Cast.ApplicationConnectionResult {
    final /* synthetic */ Status zza;

    public zzn(zzo zzoVar, Status status) {
        this.zza = status;
        Objects.requireNonNull(zzoVar);
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final ApplicationMetadata getApplicationMetadata() {
        return null;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final String getApplicationStatus() {
        return null;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final String getSessionId() {
        return null;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult, com.google.android.gms.common.api.x
    public final Status getStatus() {
        return this.zza;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final boolean getWasLaunched() {
        return false;
    }
}
