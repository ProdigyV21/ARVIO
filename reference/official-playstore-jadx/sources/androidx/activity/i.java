package androidx.activity;

import androidx.compose.runtime.State;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.ui.screens.collections.VideoHeroKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements androidx.lifecycle.w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f951i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f952l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f953m;

    public /* synthetic */ i(Object obj, Object obj2, int i10) {
        this.f951i = i10;
        this.f952l = obj;
        this.f953m = obj2;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        switch (this.f951i) {
            case 0:
                l0 l0Var = (l0) this.f952l;
                t tVar = (t) this.f953m;
                if (pVar == androidx.lifecycle.p.ON_CREATE) {
                    l0Var.f967e = j.b(tVar);
                    l0Var.d(l0Var.f969g);
                }
                break;
            case 1:
                androidx.core.view.f0 f0Var = (androidx.core.view.f0) this.f952l;
                androidx.core.view.i0 i0Var = (androidx.core.view.i0) this.f953m;
                if (pVar != androidx.lifecycle.p.ON_DESTROY) {
                    f0Var.getClass();
                } else {
                    f0Var.a(i0Var);
                }
                break;
            case 2:
                VideoHeroKt.VideoHero$lambda$3$0$0((ExoPlayer) this.f952l, (State) this.f953m, yVar, pVar);
                break;
            case 3:
                WatchlistScreenKt.WatchlistScreen$lambda$62$0$0((kotlin.jvm.internal.f0) this.f952l, (WatchlistViewModel) this.f953m, yVar, pVar);
                break;
            default:
                androidx.lifecycle.p pVar2 = (androidx.lifecycle.p) this.f952l;
                State state = (State) this.f953m;
                if (pVar == pVar2) {
                    ((r7.a) state.getValue()).invoke();
                }
                break;
        }
    }
}
