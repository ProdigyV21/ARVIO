package com.arflix.tv.ui.screens.settings;

import com.arflix.tv.data.model.CatalogDiscoveryResult;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final /* synthetic */ class SettingsScreenKt$SettingsScreen$24$42$1 extends kotlin.jvm.internal.m implements r7.l<CatalogDiscoveryResult, x6.t0> {
    public SettingsScreenKt$SettingsScreen$24$42$1(Object obj) {
        super(1, 0, SettingsViewModel.class, obj, "addDiscoveredCatalog", "addDiscoveredCatalog(Lcom/arflix/tv/data/model/CatalogDiscoveryResult;)V");
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((CatalogDiscoveryResult) obj);
        return x6.t0.f22605a;
    }

    public final void invoke(CatalogDiscoveryResult catalogDiscoveryResult) {
        ((SettingsViewModel) this.receiver).addDiscoveredCatalog(catalogDiscoveryResult);
    }
}
