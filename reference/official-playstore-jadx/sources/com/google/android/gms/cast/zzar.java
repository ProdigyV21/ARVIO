package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.g;
import com.google.android.gms.common.api.o;
import com.google.android.gms.common.api.p;
import com.google.android.gms.common.internal.h;
import com.google.android.gms.common.internal.t;

/* JADX INFO: loaded from: classes4.dex */
final class zzar extends a {
    @Override // com.google.android.gms.common.api.a
    public final /* bridge */ /* synthetic */ g buildClient(Context context, Looper looper, h hVar, Object obj, o oVar, p pVar) {
        Cast.CastOptions castOptions = (Cast.CastOptions) obj;
        t.j(castOptions, "Setting the API options is required.");
        return new com.google.android.gms.cast.internal.zzy(context, looper, hVar, castOptions.zza, castOptions.zzd, castOptions.zzc, castOptions.zze, oVar, pVar);
    }
}
