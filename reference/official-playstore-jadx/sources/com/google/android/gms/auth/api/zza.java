package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.g;
import com.google.android.gms.common.api.internal.o;
import com.google.android.gms.common.internal.h;
import com.google.android.gms.internal.auth.z;

/* JADX INFO: loaded from: classes4.dex */
final class zza extends a {
    @Override // com.google.android.gms.common.api.a
    public final /* synthetic */ g buildClient(Context context, Looper looper, h hVar, Object obj, com.google.android.gms.common.api.internal.g gVar, o oVar) {
        return new z(context, looper, hVar, (AuthProxyOptions) obj, gVar, oVar);
    }
}
