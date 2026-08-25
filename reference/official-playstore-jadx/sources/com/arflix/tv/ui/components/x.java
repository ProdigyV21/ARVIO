package com.arflix.tv.ui.components;

import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.data.api.InAppYouTubeExtractor;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.screens.home.HomeScreenKt;
import com.arflix.tv.ui.screens.player.PlayerScreenKt;
import com.arflix.tv.ui.screens.player.PlayerViewModel;
import com.arflix.tv.ui.screens.tv.TvScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class x implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8480i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8481l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8482m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8483n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8484o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8485p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8486q;

    public /* synthetic */ x(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, int i10) {
        this.f8480i = i10;
        this.f8481l = obj;
        this.f8482m = obj2;
        this.f8483n = obj3;
        this.f8484o = obj4;
        this.f8485p = obj5;
        this.f8486q = obj6;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8480i) {
            case 0:
                return ContextMenuKt.MediaContextMenu$lambda$0$0((r7.a) this.f8481l, (r7.a) this.f8482m, (r7.a) this.f8483n, (r7.a) this.f8484o, (r7.a) this.f8485p, (r7.a) this.f8486q, (ContextAction) obj);
            case 1:
                return TrailerPlayerKt.TrailerPlayer$lambda$14$3$0((ExoPlayer) this.f8481l, (androidx.lifecycle.y) this.f8482m, (InAppYouTubeExtractor) this.f8483n, (String) this.f8484o, (MutableState) this.f8485p, (State) this.f8486q, (DisposableEffectScope) obj);
            case 2:
                return HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$34$0$0((r7.l) this.f8481l, (r7.l) this.f8482m, (r7.p) this.f8483n, (r7.l) this.f8484o, (r7.l) this.f8485p, (r7.r) this.f8486q, (MediaItem) obj);
            case 3:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$11$1$0((PlayerViewModel) this.f8481l, (ka.k0) this.f8482m, (State) this.f8483n, (MutableState) this.f8484o, (MutableState) this.f8485p, (FocusRequester) this.f8486q, ((Integer) obj).intValue());
            default:
                return TvScreenKt.TvScreen$lambda$118$2$1$1$0((List) this.f8481l, (MutableIntState) this.f8482m, (MutableState) this.f8483n, (MutableState) this.f8484o, (MutableState) this.f8485p, (MutableState) this.f8486q, ((Integer) obj).intValue());
        }
    }
}
