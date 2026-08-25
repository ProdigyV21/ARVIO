package com.google.android.gms.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.g;
import com.google.android.gms.common.api.o;
import com.google.android.gms.common.api.p;
import com.google.android.gms.common.internal.h;
import com.google.android.gms.internal.cast.a3;

/* JADX INFO: loaded from: classes4.dex */
final class zzu extends a {
    @Override // com.google.android.gms.common.api.a
    public final /* bridge */ /* synthetic */ g buildClient(Context context, Looper looper, h hVar, Object obj, o oVar, p pVar) {
        CastRemoteDisplay.CastRemoteDisplayOptions castRemoteDisplayOptions = (CastRemoteDisplay.CastRemoteDisplayOptions) obj;
        Bundle bundle = new Bundle();
        bundle.putInt("configuration", castRemoteDisplayOptions.zzc);
        return new a3(context, looper, hVar, castRemoteDisplayOptions.zza, bundle, castRemoteDisplayOptions.zzb, oVar, pVar);
    }
}
