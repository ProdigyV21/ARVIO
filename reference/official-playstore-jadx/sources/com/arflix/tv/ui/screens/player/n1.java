package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.State;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class n1 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9664i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9665l;

    public /* synthetic */ n1(Object obj, int i10) {
        this.f9664i = i10;
        this.f9665l = obj;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9664i) {
            case 0:
                return Boolean.valueOf(PlayerScreenKt$PlayerScreen$26$1$gateReady$1.invokeSuspend$lambda$0((State) this.f9665l));
            case 1:
                return AiSubtitleRenderersFactory.buildTextRenderers$lambda$0((AiSubtitleRenderersFactory) this.f9665l);
            case 2:
                return SubtitleTranslationManager.updateService$lambda$0((String) this.f9665l);
            default:
                return SubtitleTranslationManager.updateService$lambda$1((SubtitleAiModel) this.f9665l);
        }
    }
}
