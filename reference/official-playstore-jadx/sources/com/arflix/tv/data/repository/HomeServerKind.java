package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerKind;", "", "<init>", "(Ljava/lang/String;I)V", "UNKNOWN", "JELLYFIN", "EMBY", "PLEX", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeServerKind {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ HomeServerKind[] $VALUES;
    public static final HomeServerKind UNKNOWN = new HomeServerKind("UNKNOWN", 0);
    public static final HomeServerKind JELLYFIN = new HomeServerKind("JELLYFIN", 1);
    public static final HomeServerKind EMBY = new HomeServerKind("EMBY", 2);
    public static final HomeServerKind PLEX = new HomeServerKind("PLEX", 3);

    private static final /* synthetic */ HomeServerKind[] $values() {
        return new HomeServerKind[]{UNKNOWN, JELLYFIN, EMBY, PLEX};
    }

    static {
        HomeServerKind[] homeServerKindArr$values = $values();
        $VALUES = homeServerKindArr$values;
        $ENTRIES = new g7.b(homeServerKindArr$values);
    }

    private HomeServerKind(String str, int i10) {
    }

    public static g7.a<HomeServerKind> getEntries() {
        return $ENTRIES;
    }

    public static HomeServerKind valueOf(String str) {
        return (HomeServerKind) Enum.valueOf(HomeServerKind.class, str);
    }

    public static HomeServerKind[] values() {
        return (HomeServerKind[]) $VALUES.clone();
    }
}
