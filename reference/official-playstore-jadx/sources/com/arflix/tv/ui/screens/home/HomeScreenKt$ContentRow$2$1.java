package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.api.InAppYouTubeExtractor;
import ka.x0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$ContentRow$2$1", f = "HomeScreen.kt", l = {3507}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$ContentRow$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ String $featuredTrailerKey;
    final /* synthetic */ int $focusedItemIndex;
    final /* synthetic */ boolean $hasFeaturedCard;
    final /* synthetic */ boolean $isCurrentRow;
    final /* synthetic */ InAppYouTubeExtractor $trailerExtractor;
    Object L$0;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.home.HomeScreenKt$ContentRow$2$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$ContentRow$2$1$1", f = "HomeScreen.kt", l = {3508}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $key;
        final /* synthetic */ InAppYouTubeExtractor $trailerExtractor;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(InAppYouTubeExtractor inAppYouTubeExtractor, String str, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$trailerExtractor = inAppYouTubeExtractor;
            this.$key = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$trailerExtractor, this.$key, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    InAppYouTubeExtractor inAppYouTubeExtractor = this.$trailerExtractor;
                    String str = "https://www.youtube.com/watch?v=" + this.$key;
                    this.label = 1;
                    Object objExtractPlaybackSource = inAppYouTubeExtractor.extractPlaybackSource(str, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objExtractPlaybackSource == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
            } catch (Exception unused) {
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$ContentRow$2$1(String str, boolean z, boolean z5, int i10, InAppYouTubeExtractor inAppYouTubeExtractor, d7.d<? super HomeScreenKt$ContentRow$2$1> dVar) {
        super(2, dVar);
        this.$featuredTrailerKey = str;
        this.$hasFeaturedCard = z;
        this.$isCurrentRow = z5;
        this.$focusedItemIndex = i10;
        this.$trailerExtractor = inAppYouTubeExtractor;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$ContentRow$2$1(this.$featuredTrailerKey, this.$hasFeaturedCard, this.$isCurrentRow, this.$focusedItemIndex, this.$trailerExtractor, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return t0Var;
        }
        k2.c.G(obj);
        String str = this.$featuredTrailerKey;
        if (str != null && this.$hasFeaturedCard && this.$isCurrentRow && this.$focusedItemIndex >= 0) {
            ra.c cVar = x0.f19655d;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$trailerExtractor, str, null);
            this.L$0 = null;
            this.label = 1;
            Object objY = ka.m0.y(cVar, anonymousClass1, this);
            e7.a aVar = e7.a.f15033i;
            if (objY == aVar) {
                return aVar;
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$ContentRow$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
