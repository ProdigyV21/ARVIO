package com.arflix.tv.data.repository;

import j$.util.function.IntUnaryOperator$CC;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements IntUnaryOperator {
    public /* synthetic */ IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator) {
        return IntUnaryOperator$CC.$default$andThen(this, intUnaryOperator);
    }

    @Override // java.util.function.IntUnaryOperator
    public final int applyAsInt(int i10) {
        return CloudSyncInvalidationBus.suppressDuringRemoteApply$lambda$0(i10);
    }

    public /* synthetic */ IntUnaryOperator compose(IntUnaryOperator intUnaryOperator) {
        return IntUnaryOperator$CC.$default$compose(this, intUnaryOperator);
    }
}
