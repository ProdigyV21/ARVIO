package com.arflix.tv.ui.screens.home;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.pager.PagerState;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$MobileHeroCarousel$4$1", f = "HomeScreen.kt", l = {2091, 2092}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$MobileHeroCarousel$4$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ List<MediaItem> $heroItems;
    final /* synthetic */ PagerState $pagerState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$MobileHeroCarousel$4$1(List<MediaItem> list, PagerState pagerState, d7.d<? super HomeScreenKt$MobileHeroCarousel$4$1> dVar) {
        super(2, dVar);
        this.$heroItems = list;
        this.$pagerState = pagerState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$MobileHeroCarousel$4$1(this.$heroItems, this.$pagerState, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        e7.a aVar = e7.a.f15033i;
        if (i10 == 0) {
            k2.c.G(obj);
            if (this.$heroItems.size() <= 1) {
                return t0Var;
            }
            this.label = 1;
            if (ka.s0.a(10000L, this) != aVar) {
            }
        }
        if (i10 != 1) {
            if (i10 == 2) {
                k2.c.G(obj);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        PagerState pagerState = this.$pagerState;
        int currentPage = pagerState.getCurrentPage() + 1;
        TweenSpec tweenSpecTween$default = AnimationSpecKt.tween$default(900, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
        this.label = 2;
        return PagerState.animateScrollToPage$default(pagerState, currentPage, 0.0f, tweenSpecTween$default, this, 2, null) == aVar ? aVar : t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$MobileHeroCarousel$4$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
