package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.x;
import j$.util.Objects;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
abstract class zzbc extends BasePendingResult {
    private com.google.android.gms.cast.internal.zzat zza;
    private final boolean zzb;
    final /* synthetic */ RemoteMediaClient zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbc(RemoteMediaClient remoteMediaClient, boolean z) {
        super((q) null);
        Objects.requireNonNull(remoteMediaClient);
        this.zzg = remoteMediaClient;
        this.zzb = z;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ x createFailedResult(Status status) {
        return new zzbb(this, status);
    }

    public abstract void zza() throws com.google.android.gms.cast.internal.zzap;

    public final com.google.android.gms.cast.internal.zzat zzb() {
        if (this.zza == null) {
            this.zza = new zzba(this);
        }
        return this.zza;
    }

    public final void zzc() {
        if (!this.zzb) {
            RemoteMediaClient remoteMediaClient = this.zzg;
            Iterator it = remoteMediaClient.zzu().iterator();
            while (it.hasNext()) {
                ((RemoteMediaClient.Listener) it.next()).onSendingRemoteMediaRequest();
            }
            Iterator it2 = remoteMediaClient.zzv().iterator();
            while (it2.hasNext()) {
                ((RemoteMediaClient.Callback) it2.next()).onSendingRemoteMediaRequest();
            }
        }
        try {
            synchronized (this.zzg.zzr()) {
                zza();
            }
        } catch (com.google.android.gms.cast.internal.zzap unused) {
            setResult(new zzbb(this, new Status(2100, null, null, null)));
        }
    }
}
