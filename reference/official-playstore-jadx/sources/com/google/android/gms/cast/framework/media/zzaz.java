package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.x;

/* JADX INFO: loaded from: classes4.dex */
final class zzaz extends BasePendingResult {
    public zzaz() {
        super((q) null);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ x createFailedResult(Status status) {
        return new zzay(this, status);
    }
}
