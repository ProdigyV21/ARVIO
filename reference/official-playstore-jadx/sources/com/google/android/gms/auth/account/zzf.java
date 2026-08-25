package com.google.android.gms.auth.account;

import android.content.Context;
import android.os.Looper;
import androidx.compose.material3.MenuKt;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.g;
import com.google.android.gms.common.api.o;
import com.google.android.gms.common.api.p;
import com.google.android.gms.common.internal.h;
import com.google.android.gms.internal.auth.k;

/* JADX INFO: loaded from: classes4.dex */
final class zzf extends a {
    @Override // com.google.android.gms.common.api.a
    public final g buildClient(Context context, Looper looper, h hVar, Object obj, o oVar, p pVar) {
        return new k(context, looper, MenuKt.InTransitionDuration, hVar, oVar, pVar);
    }
}
