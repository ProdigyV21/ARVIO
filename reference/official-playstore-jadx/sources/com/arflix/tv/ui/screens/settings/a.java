package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.CatalogDiscoveryResult;
import com.arflix.tv.data.repository.sync.SyncProvider;
import com.arflix.tv.data.repository.sync.TrackingFeature;
import com.arflix.tv.data.repository.sync.TrackingReadMode;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10265i;

    public /* synthetic */ a(int i10) {
        this.f10265i = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10265i) {
            case 0:
                return ComposableSingletons$SettingsScreenKt.lambda_1099712397$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 1:
                return ComposableSingletons$SettingsScreenKt.lambda__841563598$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 2:
                return ComposableSingletons$SettingsScreenKt.lambda_1713680901$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 3:
                return ComposableSingletons$SettingsScreenKt.lambda_1189184630$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 4:
                return ComposableSingletons$SettingsScreenKt.lambda_1003862489$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 5:
                return SettingsScreenKt.TrackingIntegrationsPage$lambda$2$0((TrackingFeature) obj, (TrackingReadMode) obj2);
            case 6:
                return SettingsScreenKt.TrackingIntegrationsPage$lambda$3$0((SyncProvider) obj, ((Boolean) obj2).booleanValue());
            default:
                return SettingsScreenKt.CatalogDiscoveryModal$lambda$7$0$0$0$4$2$0$0(((Integer) obj).intValue(), (CatalogDiscoveryResult) obj2);
        }
    }
}
