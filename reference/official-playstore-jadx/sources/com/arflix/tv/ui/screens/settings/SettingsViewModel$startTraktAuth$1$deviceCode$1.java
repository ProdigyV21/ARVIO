package com.arflix.tv.ui.screens.settings;

import com.arflix.tv.data.api.TraktDeviceCode;
import com.arflix.tv.data.repository.TraktRepository;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/api/TraktDeviceCode;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/api/TraktDeviceCode;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$startTraktAuth$1$deviceCode$1", f = "SettingsViewModel.kt", l = {3445}, m = "invokeSuspend", v = 2)
public final class SettingsViewModel$startTraktAuth$1$deviceCode$1 extends f7.j implements r7.p<ka.k0, d7.d<? super TraktDeviceCode>, Object> {
    int label;
    final /* synthetic */ SettingsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsViewModel$startTraktAuth$1$deviceCode$1(SettingsViewModel settingsViewModel, d7.d<? super SettingsViewModel$startTraktAuth$1$deviceCode$1> dVar) {
        super(2, dVar);
        this.this$0 = settingsViewModel;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new SettingsViewModel$startTraktAuth$1$deviceCode$1(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        TraktRepository traktRepository = this.this$0.traktRepository;
        this.label = 1;
        Object deviceCode = traktRepository.getDeviceCode(this);
        e7.a aVar = e7.a.f15033i;
        return deviceCode == aVar ? aVar : deviceCode;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super TraktDeviceCode> dVar) {
        return ((SettingsViewModel$startTraktAuth$1$deviceCode$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
