package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.Cast;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzv implements Runnable {
    final /* synthetic */ zzx zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;

    public zzv(zzw zzwVar, zzx zzxVar, String str, String str2) {
        this.zza = zzxVar;
        this.zzb = str;
        this.zzc = str2;
        Objects.requireNonNull(zzwVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        Cast.MessageReceivedCallback messageReceivedCallback;
        zzx zzxVar = this.zza;
        synchronized (zzxVar.zzR()) {
            messageReceivedCallback = (Cast.MessageReceivedCallback) zzxVar.zzR().get(this.zzb);
        }
        if (messageReceivedCallback == null) {
            zzx.zzf.d("Discarded message for unknown namespace '%s'", this.zzb);
        } else {
            messageReceivedCallback.onMessageReceived(this.zza.zzP(), this.zzb, this.zzc);
        }
    }
}
