package com.google.android.gms.cast.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.g;
import com.google.android.gms.common.api.o;
import com.google.android.gms.common.api.p;
import com.google.android.gms.common.internal.h;

/* JADX INFO: loaded from: classes4.dex */
final class zze extends a {
    @Override // com.google.android.gms.common.api.a
    public final /* synthetic */ g buildClient(Context context, Looper looper, h hVar, Object obj, o oVar, p pVar) {
        return new zzo(context, looper, hVar, oVar, pVar);
    }
}
