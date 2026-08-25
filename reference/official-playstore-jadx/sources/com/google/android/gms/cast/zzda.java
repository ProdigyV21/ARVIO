package com.google.android.gms.cast;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzda implements com.google.android.gms.cast.internal.zzat {
    final /* synthetic */ zzdc zza;

    public zzda(zzdc zzdcVar) {
        Objects.requireNonNull(zzdcVar);
        this.zza = zzdcVar;
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zza(String str, long j10, long j11, long j12) {
        try {
            zzdc zzdcVar = this.zza;
            zzdcVar.setResult(new zzdb(zzdcVar, new Status(2103, null, null, null)));
        } catch (IllegalStateException e5) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestReplaced", e5);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zzb(String str, long j10, int i10, Object obj, long j11, long j12) {
        com.google.android.gms.cast.internal.zzaq zzaqVar = obj instanceof com.google.android.gms.cast.internal.zzaq ? (com.google.android.gms.cast.internal.zzaq) obj : null;
        try {
            this.zza.setResult(new zzdd(new Status(i10, null, null, null), zzaqVar != null ? zzaqVar.zza : null));
        } catch (IllegalStateException e5) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestCompleted", e5);
        }
    }
}
