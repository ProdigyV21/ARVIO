package com.arflix.tv.ui.screens.settings;

import coil.ImageLoader;
import com.arflix.tv.network.OkHttpProvider;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcoil/ImageLoader;", "<anonymous>", "(Lka/k0;)Lcoil/ImageLoader;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setDnsProvider$1$imageLoader$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
public final class SettingsViewModel$setDnsProvider$1$imageLoader$1 extends f7.j implements r7.p<ka.k0, d7.d<? super ImageLoader>, Object> {
    int label;
    final /* synthetic */ SettingsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsViewModel$setDnsProvider$1$imageLoader$1(SettingsViewModel settingsViewModel, d7.d<? super SettingsViewModel$setDnsProvider$1$imageLoader$1> dVar) {
        super(2, dVar);
        this.this$0 = settingsViewModel;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new SettingsViewModel$setDnsProvider$1$imageLoader$1(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return OkHttpProvider.INSTANCE.createCoilImageLoader(this.this$0.context);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super ImageLoader> dVar) {
        return ((SettingsViewModel$setDnsProvider$1$imageLoader$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
