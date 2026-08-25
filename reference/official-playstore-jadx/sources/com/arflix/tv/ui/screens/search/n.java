package com.arflix.tv.ui.screens.search;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class n implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10176i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ List f10177l;

    public /* synthetic */ n(List list, int i10) {
        this.f10176i = i10;
        this.f10177l = list;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        int i10 = this.f10176i;
        int iIntValue = ((Integer) obj).intValue();
        switch (i10) {
            case 0:
                return SearchScreenKt.RowsLayer$lambda$4$0$0$0(this.f10177l, iIntValue);
            default:
                return SearchScreenKt.ContentGrid$lambda$1$0$0(this.f10177l, iIntValue);
        }
    }
}
