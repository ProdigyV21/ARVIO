package com.arflix.tv.ui.screens.player;

import com.arflix.tv.data.model.Subtitle;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/Subtitle;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/Subtitle;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerViewModel$preloadSubtitles$1$localized$1$1", f = "PlayerViewModel.kt", l = {3703}, m = "invokeSuspend", v = 2)
public final class PlayerViewModel$preloadSubtitles$1$localized$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Subtitle>, Object> {
    final /* synthetic */ Subtitle $sub;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$preloadSubtitles$1$localized$1$1(Subtitle subtitle, PlayerViewModel playerViewModel, d7.d<? super PlayerViewModel$preloadSubtitles$1$localized$1$1> dVar) {
        super(2, dVar);
        this.$sub = subtitle;
        this.this$0 = playerViewModel;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerViewModel$preloadSubtitles$1$localized$1$1(this.$sub, this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Subtitle subtitleLocalizeSubtitle$default;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            SubtitleSyncMatcher subtitleSyncMatcher = SubtitleSyncMatcher.INSTANCE;
            String url = this.$sub.getUrl();
            this.label = 1;
            obj = subtitleSyncMatcher.loadRaw(url, this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        String str = (String) obj;
        if (str != null) {
            String str2 = !kotlin.text.o.h0(str) ? str : null;
            if (str2 != null && (subtitleLocalizeSubtitle$default = PlayerViewModel.localizeSubtitle$default(this.this$0, this.$sub, str2, 0L, 4, null)) != null && kotlin.text.u.P(subtitleLocalizeSubtitle$default.getUrl(), "file:", false)) {
                return subtitleLocalizeSubtitle$default;
            }
        }
        return null;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Subtitle> dVar) {
        return ((PlayerViewModel$preloadSubtitles$1$localized$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
