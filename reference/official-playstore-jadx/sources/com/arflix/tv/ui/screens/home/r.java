package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9288i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9289l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9290m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9291n;

    public /* synthetic */ r(Object obj, Object obj2, Object obj3, int i10) {
        this.f9288i = i10;
        this.f9290m = obj;
        this.f9291n = obj2;
        this.f9289l = obj3;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9288i) {
            case 0:
                return HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$34$1$0((r7.l) this.f9290m, (r7.l) this.f9291n, (r7.p) this.f9289l, (MediaItem) obj, ((Boolean) obj2).booleanValue());
            case 1:
                return HomeScreenKt.HomeRowsLayer_HGFtVhc$lambda$8$0((r7.p) this.f9289l, (List) this.f9291n, (r7.l) this.f9290m, (String) obj, ((Integer) obj2).intValue());
            default:
                return HomeScreenKt.HomeScreen$lambda$77$8$8$0((MutableState) this.f9290m, (MutableState) this.f9291n, (MutableState) this.f9289l, (MediaItem) obj, ((Boolean) obj2).booleanValue());
        }
    }

    public /* synthetic */ r(List list, r7.l lVar, r7.p pVar) {
        this.f9288i = 1;
        this.f9289l = pVar;
        this.f9291n = list;
        this.f9290m = lVar;
    }
}
