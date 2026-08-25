package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.repository.HomeServerCatalogCandidate;
import com.arflix.tv.data.repository.HomeServerLibrarySort;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11279i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f11280l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f11281m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f11282n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f11283o;

    public /* synthetic */ a0(int i10, BoxScopeInstance boxScopeInstance, r7.a aVar, List list) {
        this.f11279i = 0;
        this.f11280l = i10;
        this.f11281m = boxScopeInstance;
        this.f11282n = aVar;
        this.f11283o = list;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f11279i) {
            case 0:
                return Boolean.valueOf(EpgGridKt.ProgramsRow_n3xTMnA$lambda$0$6$1$0(this.f11280l, (BoxScope) this.f11281m, (r7.a) this.f11282n, (List) this.f11283o));
            case 1:
                return WatchlistScreenKt.MobileLibrarySelector$lambda$3$3$0$1$0((r7.p) this.f11281m, this.f11280l, (HomeServerCatalogCandidate) this.f11282n, (MutableState) this.f11283o);
            default:
                return WatchlistScreenKt.SortSelectionOverlay$lambda$1$1$0$0$0((r7.l) this.f11281m, this.f11280l, (r7.l) this.f11282n, (HomeServerLibrarySort) this.f11283o);
        }
    }

    public /* synthetic */ a0(x6.o oVar, int i10, Object obj, Object obj2, int i11) {
        this.f11279i = i11;
        this.f11281m = oVar;
        this.f11280l = i10;
        this.f11282n = obj;
        this.f11283o = obj2;
    }
}
