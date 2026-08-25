package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.b;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzbh extends zzaz {
    final /* synthetic */ SessionProvider zza;

    public /* synthetic */ zzbh(SessionProvider sessionProvider, byte[] bArr) {
        Objects.requireNonNull(sessionProvider);
        this.zza = sessionProvider;
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final b zzb(String str) {
        Session sessionCreateSession = this.zza.createSession(str);
        if (sessionCreateSession == null) {
            return null;
        }
        return sessionCreateSession.zzn();
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final boolean zzc() {
        return this.zza.isSessionRecoverable();
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final String zzd() {
        return this.zza.getCategory();
    }
}
