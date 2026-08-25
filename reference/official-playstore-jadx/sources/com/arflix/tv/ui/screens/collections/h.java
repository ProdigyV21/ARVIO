package com.arflix.tv.ui.screens.collections;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8627i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f8628l;

    public /* synthetic */ h(MutableState mutableState, int i10) {
        this.f8627i = i10;
        this.f8628l = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8627i) {
            case 0:
                return CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$31$1$0(this.f8628l, (CollectionTab) obj);
            default:
                return CollectionDetailsScreenKt.CollectionTabChip$lambda$3$0(this.f8628l, (FocusState) obj);
        }
    }
}
