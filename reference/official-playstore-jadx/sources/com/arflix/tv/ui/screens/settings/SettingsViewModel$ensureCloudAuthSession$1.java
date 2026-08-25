package com.arflix.tv.ui.screens.settings;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel", f = "SettingsViewModel.kt", l = {2786}, m = "ensureCloudAuthSession-gIAlu-s", v = 2)
public final class SettingsViewModel$ensureCloudAuthSession$1 extends f7.c {
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SettingsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsViewModel$ensureCloudAuthSession$1(SettingsViewModel settingsViewModel, d7.d<? super SettingsViewModel$ensureCloudAuthSession$1> dVar) {
        super(dVar);
        this.this$0 = settingsViewModel;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6324ensureCloudAuthSessiongIAlus = this.this$0.m6324ensureCloudAuthSessiongIAlus(false, this);
        return objM6324ensureCloudAuthSessiongIAlus == e7.a.f15033i ? objM6324ensureCloudAuthSessiongIAlus : new x6.d0(objM6324ensureCloudAuthSessiongIAlus);
    }
}
