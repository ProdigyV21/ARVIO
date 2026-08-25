package com.google.android.gms.cast.framework;

import android.view.MenuItem;
import androidx.mediarouter.app.a;
import androidx.mediarouter.app.h;
import com.google.android.gms.tasks.e;
import com.google.android.gms.tasks.i;

/* JADX INFO: loaded from: classes4.dex */
final /* synthetic */ class zzb implements e {
    private final /* synthetic */ a zza;
    private final /* synthetic */ i zzb;
    private final /* synthetic */ MenuItem zzc;

    public /* synthetic */ zzb(a aVar, h hVar, i iVar, MenuItem menuItem) {
        this.zzb = iVar;
        this.zzc = menuItem;
    }

    @Override // com.google.android.gms.tasks.e
    public final /* synthetic */ void onSuccess(Object obj) {
        CastButtonFactory.zzd(null, null, this.zzb, this.zzc, (CastContext) obj);
    }
}
