package com.google.android.gms.cast;

import android.os.Binder;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzaj extends Binder {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    public zzaj(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        Objects.requireNonNull(castRemoteDisplayLocalService);
        this.zza = castRemoteDisplayLocalService;
    }
}
