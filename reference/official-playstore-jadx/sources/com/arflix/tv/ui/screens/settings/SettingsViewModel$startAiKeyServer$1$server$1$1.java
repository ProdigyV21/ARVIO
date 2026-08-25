package com.arflix.tv.ui.screens.settings;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$startAiKeyServer$1$server$1$1", f = "SettingsViewModel.kt", l = {1563}, m = "invokeSuspend", v = 2)
public final class SettingsViewModel$startAiKeyServer$1$server$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ String $key;
    int label;
    final /* synthetic */ SettingsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsViewModel$startAiKeyServer$1$server$1$1(SettingsViewModel settingsViewModel, String str, d7.d<? super SettingsViewModel$startAiKeyServer$1$server$1$1> dVar) {
        super(2, dVar);
        this.this$0 = settingsViewModel;
        this.$key = str;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new SettingsViewModel$startAiKeyServer$1$server$1$1(this.this$0, this.$key, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            this.this$0.saveSubtitleAiApiKey(this.$key);
            this.this$0._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) this.this$0._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, AiKeyServerState.copy$default(((SettingsUiState) this.this$0._uiState.getValue()).getAiKeyServerState(), false, null, null, true, 7, null), false, -1, -1, -1, 805306367, null));
            this.label = 1;
            Object objA = ka.s0.a(2500L, this);
            e7.a aVar = e7.a.f15033i;
            if (objA == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        this.this$0.stopAiKeyServerInternal();
        this.this$0._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) this.this$0._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, new AiKeyServerState(false, null, null, false, 15, null), false, -1, -1, -1, 805306367, null));
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((SettingsViewModel$startAiKeyServer$1$server$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
