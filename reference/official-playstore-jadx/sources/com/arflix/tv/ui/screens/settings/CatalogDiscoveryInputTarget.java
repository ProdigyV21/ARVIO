package com.arflix.tv.ui.screens.settings;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/ui/screens/settings/CatalogDiscoveryInputTarget;", "", "<init>", "(Ljava/lang/String;I)V", "Search", "ManualUrl", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class CatalogDiscoveryInputTarget {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ CatalogDiscoveryInputTarget[] $VALUES;
    public static final CatalogDiscoveryInputTarget Search = new CatalogDiscoveryInputTarget("Search", 0);
    public static final CatalogDiscoveryInputTarget ManualUrl = new CatalogDiscoveryInputTarget("ManualUrl", 1);

    private static final /* synthetic */ CatalogDiscoveryInputTarget[] $values() {
        return new CatalogDiscoveryInputTarget[]{Search, ManualUrl};
    }

    static {
        CatalogDiscoveryInputTarget[] catalogDiscoveryInputTargetArr$values = $values();
        $VALUES = catalogDiscoveryInputTargetArr$values;
        $ENTRIES = new g7.b(catalogDiscoveryInputTargetArr$values);
    }

    private CatalogDiscoveryInputTarget(String str, int i10) {
    }

    public static g7.a<CatalogDiscoveryInputTarget> getEntries() {
        return $ENTRIES;
    }

    public static CatalogDiscoveryInputTarget valueOf(String str) {
        return (CatalogDiscoveryInputTarget) Enum.valueOf(CatalogDiscoveryInputTarget.class, str);
    }

    public static CatalogDiscoveryInputTarget[] values() {
        return (CatalogDiscoveryInputTarget[]) $VALUES.clone();
    }
}
