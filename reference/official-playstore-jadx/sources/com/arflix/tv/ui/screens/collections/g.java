package com.arflix.tv.ui.screens.collections;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.focus.FocusRequester;
import androidx.tv.foundation.lazy.grid.q0;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8619i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f8620l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8621m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f8622n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.l f8623o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8624p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8625q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f8626r;

    public /* synthetic */ g(boolean z, boolean z5, CollectionTab collectionTab, FocusRequester focusRequester, FocusRequester focusRequester2, boolean z10, r7.l lVar) {
        this.f8620l = z;
        this.f8621m = z5;
        this.f8624p = collectionTab;
        this.f8625q = focusRequester;
        this.f8626r = focusRequester2;
        this.f8622n = z10;
        this.f8623o = lVar;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f8619i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return CollectionDetailsScreenKt.CollectionItemsGrid__F44eQ8$lambda$5$0$1(this.f8620l, this.f8621m, (CollectionTab) this.f8624p, (FocusRequester) this.f8625q, (FocusRequester) this.f8626r, this.f8622n, this.f8623o, (q0) obj, (Composer) obj2, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj3).intValue();
                return SettingsScreenKt.HomeServerSettings$lambda$0$0(this.f8620l, this.f8621m, (r7.a) this.f8624p, this.f8622n, (r7.a) this.f8625q, (List) this.f8626r, this.f8623o, (ColumnScope) obj, (Composer) obj2, iIntValue2);
        }
    }

    public /* synthetic */ g(boolean z, boolean z5, r7.a aVar, boolean z10, r7.a aVar2, List list, r7.l lVar) {
        this.f8620l = z;
        this.f8621m = z5;
        this.f8624p = aVar;
        this.f8622n = z10;
        this.f8625q = aVar2;
        this.f8626r = list;
        this.f8623o = lVar;
    }
}
