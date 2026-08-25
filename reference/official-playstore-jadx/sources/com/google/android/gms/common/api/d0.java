package com.google.android.gms.common.api;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* JADX INFO: loaded from: classes4.dex */
public final class d0 extends BasePendingResult {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12683a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d0(q qVar, int i10) {
        super(qVar);
        this.f12683a = i10;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final x createFailedResult(Status status) {
        switch (this.f12683a) {
            case 0:
                throw new UnsupportedOperationException("Creating failed results is not supported");
            default:
                int i10 = CastSession.zza;
                return status;
        }
    }
}
