package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.search.SearchScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class z1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8553i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f8554l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8555m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f8556n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.a f8557o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8558p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8559q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ x6.o f8560r;

    public /* synthetic */ z1(List list, boolean z, boolean z5, boolean z10, r7.l lVar, r7.a aVar, int i10) {
        this.f8559q = list;
        this.f8554l = z;
        this.f8555m = z5;
        this.f8556n = z10;
        this.f8560r = lVar;
        this.f8557o = aVar;
        this.f8558p = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8553i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return QuickActionMenuKt.QuickActionMenu$lambda$10(this.f8554l, this.f8555m, this.f8556n, this.f8557o, (r7.a) this.f8559q, (r7.a) this.f8560r, this.f8558p, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SearchScreenKt.ContentGrid$lambda$2((List) this.f8559q, this.f8554l, this.f8555m, this.f8556n, (r7.l) this.f8560r, this.f8557o, this.f8558p, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ z1(boolean z, boolean z5, boolean z10, r7.a aVar, r7.a aVar2, r7.a aVar3, int i10) {
        this.f8554l = z;
        this.f8555m = z5;
        this.f8556n = z10;
        this.f8557o = aVar;
        this.f8559q = aVar2;
        this.f8560r = aVar3;
        this.f8558p = i10;
    }
}
