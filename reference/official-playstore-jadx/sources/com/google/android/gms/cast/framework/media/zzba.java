package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Status;
import j$.util.Objects;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
final class zzba implements com.google.android.gms.cast.internal.zzat {
    final /* synthetic */ zzbc zza;

    public zzba(zzbc zzbcVar) {
        Objects.requireNonNull(zzbcVar);
        this.zza = zzbcVar;
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zza(String str, long j10, long j11, long j12) {
        try {
            zzbc zzbcVar = this.zza;
            zzbcVar.setResult(new zzbb(zzbcVar, new Status(2103, null, null, null)));
        } catch (IllegalStateException e5) {
            RemoteMediaClient.zza.e(e5, "Result already set when calling onRequestReplaced", new Object[0]);
        }
        Iterator it = this.zza.zzg.zzv().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zza(str, j10, 2103, j11, j12);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zzb(String str, long j10, int i10, Object obj, long j11, long j12) {
        int i11;
        com.google.android.gms.cast.internal.zzaq zzaqVar = obj instanceof com.google.android.gms.cast.internal.zzaq ? (com.google.android.gms.cast.internal.zzaq) obj : null;
        try {
            i11 = i10;
        } catch (IllegalStateException e5) {
            e = e5;
            i11 = i10;
        }
        try {
            this.zza.setResult(new zzbd(new Status(i11, null, null, null), zzaqVar != null ? zzaqVar.zza : null, zzaqVar != null ? zzaqVar.zzb : null));
        } catch (IllegalStateException e6) {
            e = e6;
            RemoteMediaClient.zza.e(e, "Result already set when calling onRequestCompleted", new Object[0]);
        }
        Iterator it = this.zza.zzg.zzv().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zza(str, j10, i11, j11, j12);
            i11 = i10;
        }
    }
}
