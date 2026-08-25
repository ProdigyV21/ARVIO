package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.api.InAppYouTubeExtractor;
import com.arflix.tv.data.api.TrailerPlaybackSource;
import com.arflix.tv.ui.theme.AnimationConstants;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.TrailerPlayerKt$TrailerPlayer$2$1", f = "TrailerPlayer.kt", l = {79, AnimationConstants.STAGGER_SECTION}, m = "invokeSuspend", v = 2)
public final class TrailerPlayerKt$TrailerPlayer$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<String> $audioUrl$delegate;
    final /* synthetic */ State<r7.l<Boolean, x6.t0>> $currentOnPlayingChanged$delegate;
    final /* synthetic */ long $delayMs;
    final /* synthetic */ InAppYouTubeExtractor $extractor;
    final /* synthetic */ MutableState<Boolean> $shouldPlay$delegate;
    final /* synthetic */ MutableState<String> $videoUrl$delegate;
    final /* synthetic */ String $youtubeKey;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.components.TrailerPlayerKt$TrailerPlayer$2$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.components.TrailerPlayerKt$TrailerPlayer$2$1$1", f = "TrailerPlayer.kt", l = {82}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ MutableState<String> $audioUrl$delegate;
        final /* synthetic */ InAppYouTubeExtractor $extractor;
        final /* synthetic */ MutableState<String> $videoUrl$delegate;
        final /* synthetic */ String $youtubeKey;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(InAppYouTubeExtractor inAppYouTubeExtractor, String str, MutableState<String> mutableState, MutableState<String> mutableState2, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$extractor = inAppYouTubeExtractor;
            this.$youtubeKey = str;
            this.$videoUrl$delegate = mutableState;
            this.$audioUrl$delegate = mutableState2;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$extractor, this.$youtubeKey, this.$videoUrl$delegate, this.$audioUrl$delegate, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    InAppYouTubeExtractor inAppYouTubeExtractor = this.$extractor;
                    String str = "https://www.youtube.com/watch?v=" + this.$youtubeKey;
                    this.label = 1;
                    obj = inAppYouTubeExtractor.extractPlaybackSource(str, this);
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
                TrailerPlaybackSource trailerPlaybackSource = (TrailerPlaybackSource) obj;
                if (trailerPlaybackSource != null) {
                    this.$videoUrl$delegate.setValue(trailerPlaybackSource.getVideoUrl());
                    this.$audioUrl$delegate.setValue(trailerPlaybackSource.getAudioUrl());
                }
            } catch (Exception unused) {
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TrailerPlayerKt$TrailerPlayer$2$1(long j10, MutableState<Boolean> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, InAppYouTubeExtractor inAppYouTubeExtractor, String str, State<? extends r7.l<? super Boolean, x6.t0>> state, d7.d<? super TrailerPlayerKt$TrailerPlayer$2$1> dVar) {
        super(2, dVar);
        this.$delayMs = j10;
        this.$shouldPlay$delegate = mutableState;
        this.$videoUrl$delegate = mutableState2;
        this.$audioUrl$delegate = mutableState3;
        this.$extractor = inAppYouTubeExtractor;
        this.$youtubeKey = str;
        this.$currentOnPlayingChanged$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new TrailerPlayerKt$TrailerPlayer$2$1(this.$delayMs, this.$shouldPlay$delegate, this.$videoUrl$delegate, this.$audioUrl$delegate, this.$extractor, this.$youtubeKey, this.$currentOnPlayingChanged$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0051, code lost:
    
        if (ka.m0.y(r11, r4, r10) == r3) goto L15;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            int r0 = r10.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L1c
            if (r0 == r2) goto L18
            if (r0 != r1) goto L10
            k2.c.G(r11)
            goto L54
        L10:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L18:
            k2.c.G(r11)
            goto L3b
        L1c:
            k2.c.G(r11)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r11 = r10.$shouldPlay$delegate
            r0 = 0
            com.arflix.tv.ui.components.TrailerPlayerKt.access$TrailerPlayer$lambda$4(r11, r0)
            androidx.compose.runtime.MutableState<java.lang.String> r11 = r10.$videoUrl$delegate
            r0 = 0
            com.arflix.tv.ui.components.TrailerPlayerKt.access$TrailerPlayer$lambda$7(r11, r0)
            androidx.compose.runtime.MutableState<java.lang.String> r11 = r10.$audioUrl$delegate
            com.arflix.tv.ui.components.TrailerPlayerKt.access$TrailerPlayer$lambda$10(r11, r0)
            long r4 = r10.$delayMs
            r10.label = r2
            java.lang.Object r11 = ka.s0.a(r4, r10)
            if (r11 != r3) goto L3b
            goto L53
        L3b:
            ra.c r11 = ka.x0.f19655d
            com.arflix.tv.ui.components.TrailerPlayerKt$TrailerPlayer$2$1$1 r4 = new com.arflix.tv.ui.components.TrailerPlayerKt$TrailerPlayer$2$1$1
            com.arflix.tv.data.api.InAppYouTubeExtractor r5 = r10.$extractor
            java.lang.String r6 = r10.$youtubeKey
            androidx.compose.runtime.MutableState<java.lang.String> r7 = r10.$videoUrl$delegate
            androidx.compose.runtime.MutableState<java.lang.String> r8 = r10.$audioUrl$delegate
            r9 = 0
            r4.<init>(r5, r6, r7, r8, r9)
            r10.label = r1
            java.lang.Object r11 = ka.m0.y(r11, r4, r10)
            if (r11 != r3) goto L54
        L53:
            return r3
        L54:
            androidx.compose.runtime.MutableState<java.lang.String> r11 = r10.$videoUrl$delegate
            java.lang.String r11 = com.arflix.tv.ui.components.TrailerPlayerKt.access$TrailerPlayer$lambda$6(r11)
            if (r11 == 0) goto L6d
            androidx.compose.runtime.MutableState<java.lang.Boolean> r11 = r10.$shouldPlay$delegate
            com.arflix.tv.ui.components.TrailerPlayerKt.access$TrailerPlayer$lambda$4(r11, r2)
            androidx.compose.runtime.State<r7.l<java.lang.Boolean, x6.t0>> r11 = r10.$currentOnPlayingChanged$delegate
            r7.l r11 = com.arflix.tv.ui.components.TrailerPlayerKt.access$TrailerPlayer$lambda$1(r11)
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r11.invoke(r0)
            goto L78
        L6d:
            androidx.compose.runtime.State<r7.l<java.lang.Boolean, x6.t0>> r11 = r10.$currentOnPlayingChanged$delegate
            r7.l r11 = com.arflix.tv.ui.components.TrailerPlayerKt.access$TrailerPlayer$lambda$1(r11)
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r11.invoke(r0)
        L78:
            x6.t0 r11 = x6.t0.f22605a
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.TrailerPlayerKt$TrailerPlayer$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((TrailerPlayerKt$TrailerPlayer$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
