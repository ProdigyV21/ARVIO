package com.arflix.tv.ui.screens.collections;

import androidx.compose.runtime.MutableState;
import androidx.tv.foundation.lazy.grid.b1;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8671i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8672l;

    public /* synthetic */ p(Object obj, int i10) {
        this.f8671i = i10;
        this.f8672l = obj;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f8671i) {
            case 0:
                return CollectionDetailsScreenKt$CollectionItemsGrid$1$1.invokeSuspend$lambda$0((b1) this.f8672l);
            case 1:
                return CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$5$0((CollectionTab) this.f8672l);
            case 2:
                return CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$29$0((r7.a) this.f8672l);
            default:
                return CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$31$0$0((MutableState) this.f8672l);
        }
    }
}
