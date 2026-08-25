package com.arflix.tv.ui.screens.settings;

import com.arflix.tv.data.repository.sync.SyncProvider;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final /* synthetic */ class SettingsScreenKt$SettingsScreen$24$18$2$1$91$1 extends kotlin.jvm.internal.m implements r7.p<SyncProvider, Boolean, x6.t0> {
    public SettingsScreenKt$SettingsScreen$24$18$2$1$91$1(Object obj) {
        super(2, 0, SettingsViewModel.class, obj, "setTrackingWriteTarget", "setTrackingWriteTarget(Lcom/arflix/tv/data/repository/sync/SyncProvider;Z)V");
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((SyncProvider) obj, ((Boolean) obj2).booleanValue());
        return x6.t0.f22605a;
    }

    public final void invoke(SyncProvider syncProvider, boolean z) {
        ((SettingsViewModel) this.receiver).setTrackingWriteTarget(syncProvider, z);
    }
}
