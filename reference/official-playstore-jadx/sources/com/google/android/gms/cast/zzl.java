package com.google.android.gms.cast;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.q;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzl extends com.google.android.gms.cast.internal.zzae {
    final /* synthetic */ String zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzl(zzm zzmVar, q qVar, String str) {
        super(qVar);
        this.zza = str;
        Objects.requireNonNull(zzmVar);
    }

    @Override // com.google.android.gms.cast.internal.zzae, com.google.android.gms.common.api.internal.d
    /* JADX INFO: renamed from: zza */
    public final void doExecute(com.google.android.gms.cast.internal.zzx zzxVar) throws RemoteException {
        String str = this.zza;
        if (TextUtils.isEmpty(str)) {
            setResult(new Status(2001, "IllegalArgument: sessionId cannot be null or empty", null, null));
            return;
        }
        try {
            zzxVar.zzt(str, this);
        } catch (IllegalStateException unused) {
            zzc(2001);
        }
    }
}
