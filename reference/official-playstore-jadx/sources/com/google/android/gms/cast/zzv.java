package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.g;
import com.google.android.gms.common.api.o;
import com.google.android.gms.common.api.p;
import com.google.android.gms.common.internal.h;
import com.google.android.gms.internal.cast.y2;

/* JADX INFO: loaded from: classes4.dex */
final class zzv extends a {
    @Override // com.google.android.gms.common.api.a
    public final g buildClient(Context context, Looper looper, h hVar, Object obj, o oVar, p pVar) {
        return new y2(context, looper, 83, hVar, oVar, pVar);
    }
}
