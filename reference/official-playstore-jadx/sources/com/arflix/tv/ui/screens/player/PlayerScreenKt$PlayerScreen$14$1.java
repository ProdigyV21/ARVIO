package com.arflix.tv.ui.screens.player;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$14$1", f = "PlayerScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$14$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ AiSubtitleRenderersFactory $aiRenderersFactory;
    final /* synthetic */ PlayerViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$14$1(PlayerViewModel playerViewModel, AiSubtitleRenderersFactory aiSubtitleRenderersFactory, d7.d<? super PlayerScreenKt$PlayerScreen$14$1> dVar) {
        super(2, dVar);
        this.$viewModel = playerViewModel;
        this.$aiRenderersFactory = aiSubtitleRenderersFactory;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$14$1(this.$viewModel, this.$aiRenderersFactory, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        PlayerViewModel playerViewModel = this.$viewModel;
        final AiSubtitleRenderersFactory aiSubtitleRenderersFactory = this.$aiRenderersFactory;
        final int i10 = 0;
        playerViewModel.setBufferedReferenceIntervalsProvider(new r7.l() { // from class: com.arflix.tv.ui.screens.player.m1
            @Override // r7.l
            public final Object invoke(Object obj2) {
                int i11 = i10;
                int iIntValue = ((Integer) obj2).intValue();
                switch (i11) {
                    case 0:
                        return aiSubtitleRenderersFactory.extractBufferedReferenceIntervals(iIntValue);
                    default:
                        return aiSubtitleRenderersFactory.extractBufferedCueTexts(iIntValue);
                }
            }
        });
        PlayerViewModel playerViewModel2 = this.$viewModel;
        final AiSubtitleRenderersFactory aiSubtitleRenderersFactory2 = this.$aiRenderersFactory;
        final int i11 = 1;
        playerViewModel2.setBufferedCueTextsProvider(new r7.l() { // from class: com.arflix.tv.ui.screens.player.m1
            @Override // r7.l
            public final Object invoke(Object obj2) {
                int i112 = i11;
                int iIntValue = ((Integer) obj2).intValue();
                switch (i112) {
                    case 0:
                        return aiSubtitleRenderersFactory2.extractBufferedReferenceIntervals(iIntValue);
                    default:
                        return aiSubtitleRenderersFactory2.extractBufferedCueTexts(iIntValue);
                }
            }
        });
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$14$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
