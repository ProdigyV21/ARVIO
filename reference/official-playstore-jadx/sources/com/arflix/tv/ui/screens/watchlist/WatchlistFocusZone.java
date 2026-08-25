package com.arflix.tv.ui.screens.watchlist;

import kotlin.Metadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/WatchlistFocusZone;", "", "<init>", "(Ljava/lang/String;I)V", "TOP_BAR", "PROVIDERS", "LIBRARIES", "FILTERS", "CONTENT", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class WatchlistFocusZone {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ WatchlistFocusZone[] $VALUES;
    public static final WatchlistFocusZone TOP_BAR = new WatchlistFocusZone("TOP_BAR", 0);
    public static final WatchlistFocusZone PROVIDERS = new WatchlistFocusZone("PROVIDERS", 1);
    public static final WatchlistFocusZone LIBRARIES = new WatchlistFocusZone("LIBRARIES", 2);
    public static final WatchlistFocusZone FILTERS = new WatchlistFocusZone("FILTERS", 3);
    public static final WatchlistFocusZone CONTENT = new WatchlistFocusZone("CONTENT", 4);

    private static final /* synthetic */ WatchlistFocusZone[] $values() {
        return new WatchlistFocusZone[]{TOP_BAR, PROVIDERS, LIBRARIES, FILTERS, CONTENT};
    }

    static {
        WatchlistFocusZone[] watchlistFocusZoneArr$values = $values();
        $VALUES = watchlistFocusZoneArr$values;
        $ENTRIES = new g7.b(watchlistFocusZoneArr$values);
    }

    private WatchlistFocusZone(String str, int i10) {
    }

    public static g7.a<WatchlistFocusZone> getEntries() {
        return $ENTRIES;
    }

    public static WatchlistFocusZone valueOf(String str) {
        return (WatchlistFocusZone) Enum.valueOf(WatchlistFocusZone.class, str);
    }

    public static WatchlistFocusZone[] values() {
        return (WatchlistFocusZone[]) $VALUES.clone();
    }
}
