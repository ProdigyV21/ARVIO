package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.d;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.x;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzc extends d {
    public zzc(q qVar) {
        super(Cast.API, qVar);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult, com.google.android.gms.common.api.internal.e
    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        setResult((x) obj);
    }

    public final void zzc(int i10) {
        setResult(createFailedResult(new Status(2001, null, null, null)));
    }
}
