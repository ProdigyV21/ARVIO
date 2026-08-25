package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Looper;

/* JADX INFO: loaded from: classes4.dex */
public abstract class a extends f {
    @Deprecated
    public g buildClient(Context context, Looper looper, com.google.android.gms.common.internal.h hVar, Object obj, o oVar, p pVar) {
        return buildClient(context, looper, hVar, obj, (com.google.android.gms.common.api.internal.g) oVar, (com.google.android.gms.common.api.internal.o) pVar);
    }

    public g buildClient(Context context, Looper looper, com.google.android.gms.common.internal.h hVar, Object obj, com.google.android.gms.common.api.internal.g gVar, com.google.android.gms.common.api.internal.o oVar) {
        throw new UnsupportedOperationException("buildClient must be implemented");
    }
}
