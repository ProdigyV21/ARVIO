package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import j$.util.Objects;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
final class zzv extends Cast.Listener {
    final /* synthetic */ CastSession zza;

    public /* synthetic */ zzv(CastSession castSession, byte[] bArr) {
        Objects.requireNonNull(castSession);
        this.zza = castSession;
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onActiveInputStateChanged(int i10) {
        Iterator it = new HashSet(this.zza.zzh()).iterator();
        while (it.hasNext()) {
            ((Cast.Listener) it.next()).onActiveInputStateChanged(i10);
        }
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onApplicationDisconnected(int i10) {
        CastSession castSession = this.zza;
        castSession.zzf(i10);
        castSession.notifySessionEnded(i10);
        Iterator it = new HashSet(castSession.zzh()).iterator();
        while (it.hasNext()) {
            ((Cast.Listener) it.next()).onApplicationDisconnected(i10);
        }
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onApplicationMetadataChanged(ApplicationMetadata applicationMetadata) {
        Iterator it = new HashSet(this.zza.zzh()).iterator();
        while (it.hasNext()) {
            ((Cast.Listener) it.next()).onApplicationMetadataChanged(applicationMetadata);
        }
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onApplicationStatusChanged() {
        Iterator it = new HashSet(this.zza.zzh()).iterator();
        while (it.hasNext()) {
            ((Cast.Listener) it.next()).onApplicationStatusChanged();
        }
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onStandbyStateChanged(int i10) {
        Iterator it = new HashSet(this.zza.zzh()).iterator();
        while (it.hasNext()) {
            ((Cast.Listener) it.next()).onStandbyStateChanged(i10);
        }
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onVolumeChanged() {
        Iterator it = new HashSet(this.zza.zzh()).iterator();
        while (it.hasNext()) {
            ((Cast.Listener) it.next()).onVolumeChanged();
        }
    }
}
