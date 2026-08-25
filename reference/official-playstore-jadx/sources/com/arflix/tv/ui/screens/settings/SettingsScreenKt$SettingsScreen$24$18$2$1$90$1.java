package com.arflix.tv.ui.screens.settings;

import com.arflix.tv.data.repository.sync.TrackingFeature;
import com.arflix.tv.data.repository.sync.TrackingReadMode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final /* synthetic */ class SettingsScreenKt$SettingsScreen$24$18$2$1$90$1 extends kotlin.jvm.internal.m implements r7.p<TrackingFeature, TrackingReadMode, x6.t0> {
    public SettingsScreenKt$SettingsScreen$24$18$2$1$90$1(Object obj) {
        super(2, 0, SettingsViewModel.class, obj, "setTrackingReadMode", "setTrackingReadMode(Lcom/arflix/tv/data/repository/sync/TrackingFeature;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;)V");
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((TrackingFeature) obj, (TrackingReadMode) obj2);
        return x6.t0.f22605a;
    }

    public final void invoke(TrackingFeature trackingFeature, TrackingReadMode trackingReadMode) {
        ((SettingsViewModel) this.receiver).setTrackingReadMode(trackingFeature, trackingReadMode);
    }
}
