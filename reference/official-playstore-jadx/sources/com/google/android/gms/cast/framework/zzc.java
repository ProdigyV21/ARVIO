package com.google.android.gms.cast.framework;

import com.google.android.gms.tasks.d;
import com.google.android.gms.tasks.i;

/* JADX INFO: loaded from: classes4.dex */
final /* synthetic */ class zzc implements d {
    private final /* synthetic */ i zza;

    @Override // com.google.android.gms.tasks.d
    public final /* synthetic */ void onFailure(Exception exc) {
        int i10 = CastButtonFactory.zza;
        this.zza.a(exc);
    }
}
