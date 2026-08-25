package com.google.android.gms.auth.api.phone;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.g;
import com.google.android.gms.common.api.internal.o;
import com.google.android.gms.common.internal.h;
import n4.n;

/* JADX INFO: loaded from: classes4.dex */
final class zza extends a {
    @Override // com.google.android.gms.common.api.a
    public final /* synthetic */ g buildClient(Context context, Looper looper, h hVar, Object obj, com.google.android.gms.common.api.internal.g gVar, o oVar) {
        return new n(context, looper, hVar, gVar, oVar);
    }
}
