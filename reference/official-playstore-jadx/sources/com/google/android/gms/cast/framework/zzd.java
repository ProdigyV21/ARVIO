package com.google.android.gms.cast.framework;

import androidx.mediarouter.app.b;
import com.google.android.gms.tasks.e;
import com.google.android.gms.tasks.i;

/* JADX INFO: loaded from: classes4.dex */
final /* synthetic */ class zzd implements e {
    private final /* synthetic */ b zza;
    private final /* synthetic */ i zzb;

    @Override // com.google.android.gms.tasks.e
    public final /* synthetic */ void onSuccess(Object obj) {
        CastButtonFactory.zze(null, null, this.zzb, (CastContext) obj);
    }
}
