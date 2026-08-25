package com.arflix.tv.ui.screens.player;

import androidx.compose.foundation.lazy.LazyListScope;
import com.arflix.tv.ui.screens.profile.ProfileDialogsKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9419i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f9420l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ List f9421m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f9422n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ x6.o f9423o;

    public /* synthetic */ c0(int i10, int i11, int i12, List list, r7.l lVar) {
        this.f9419i = i12;
        this.f9420l = i10;
        this.f9421m = list;
        this.f9422n = i11;
        this.f9423o = lVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9419i) {
            case 0:
                int i10 = this.f9420l;
                return PlayerScreenKt.SubtitleMenu$lambda$7$1$1$1$0(this.f9421m, i10, this.f9422n, (r7.l) this.f9423o, (LazyListScope) obj);
            case 1:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$1$1$0$0$0(this.f9420l, this.f9421m, this.f9422n, (r7.l) this.f9423o, (LazyListScope) obj);
            case 2:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$0$5$0$0(this.f9420l, this.f9421m, this.f9422n, (r7.l) this.f9423o, (LazyListScope) obj);
            default:
                return WatchlistScreenKt.LibrarySidebar$lambda$0$0$0(this.f9421m, this.f9420l, this.f9422n, (r7.p) this.f9423o, (LazyListScope) obj);
        }
    }

    public /* synthetic */ c0(List list, int i10, int i11, x6.o oVar, int i12) {
        this.f9419i = i12;
        this.f9421m = list;
        this.f9420l = i10;
        this.f9422n = i11;
        this.f9423o = oVar;
    }
}
