package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/arflix/tv/data/repository/CloudSyncScope;", "", "<init>", "(Ljava/lang/String;I)V", "PROFILE_SETTINGS", "PROFILES", "ADDONS", "CATALOGS", "IPTV", "WATCHLIST", "LOCAL_HISTORY", "ACCOUNT", "PLUGINS", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CloudSyncScope {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ CloudSyncScope[] $VALUES;
    public static final CloudSyncScope PROFILE_SETTINGS = new CloudSyncScope("PROFILE_SETTINGS", 0);
    public static final CloudSyncScope PROFILES = new CloudSyncScope("PROFILES", 1);
    public static final CloudSyncScope ADDONS = new CloudSyncScope("ADDONS", 2);
    public static final CloudSyncScope CATALOGS = new CloudSyncScope("CATALOGS", 3);
    public static final CloudSyncScope IPTV = new CloudSyncScope("IPTV", 4);
    public static final CloudSyncScope WATCHLIST = new CloudSyncScope("WATCHLIST", 5);
    public static final CloudSyncScope LOCAL_HISTORY = new CloudSyncScope("LOCAL_HISTORY", 6);
    public static final CloudSyncScope ACCOUNT = new CloudSyncScope("ACCOUNT", 7);
    public static final CloudSyncScope PLUGINS = new CloudSyncScope("PLUGINS", 8);

    private static final /* synthetic */ CloudSyncScope[] $values() {
        return new CloudSyncScope[]{PROFILE_SETTINGS, PROFILES, ADDONS, CATALOGS, IPTV, WATCHLIST, LOCAL_HISTORY, ACCOUNT, PLUGINS};
    }

    static {
        CloudSyncScope[] cloudSyncScopeArr$values = $values();
        $VALUES = cloudSyncScopeArr$values;
        $ENTRIES = new g7.b(cloudSyncScopeArr$values);
    }

    private CloudSyncScope(String str, int i10) {
    }

    public static g7.a<CloudSyncScope> getEntries() {
        return $ENTRIES;
    }

    public static CloudSyncScope valueOf(String str) {
        return (CloudSyncScope) Enum.valueOf(CloudSyncScope.class, str);
    }

    public static CloudSyncScope[] values() {
        return (CloudSyncScope[]) $VALUES.clone();
    }
}
