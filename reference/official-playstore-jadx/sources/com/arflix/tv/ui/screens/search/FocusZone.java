package com.arflix.tv.ui.screens.search;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/ui/screens/search/FocusZone;", "", "<init>", "(Ljava/lang/String;I)V", "SIDEBAR", "SEARCH_INPUT", "FILTERS", "RESULTS", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class FocusZone {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ FocusZone[] $VALUES;
    public static final FocusZone SIDEBAR = new FocusZone("SIDEBAR", 0);
    public static final FocusZone SEARCH_INPUT = new FocusZone("SEARCH_INPUT", 1);
    public static final FocusZone FILTERS = new FocusZone("FILTERS", 2);
    public static final FocusZone RESULTS = new FocusZone("RESULTS", 3);

    private static final /* synthetic */ FocusZone[] $values() {
        return new FocusZone[]{SIDEBAR, SEARCH_INPUT, FILTERS, RESULTS};
    }

    static {
        FocusZone[] focusZoneArr$values = $values();
        $VALUES = focusZoneArr$values;
        $ENTRIES = new g7.b(focusZoneArr$values);
    }

    private FocusZone(String str, int i10) {
    }

    public static g7.a<FocusZone> getEntries() {
        return $ENTRIES;
    }

    public static FocusZone valueOf(String str) {
        return (FocusZone) Enum.valueOf(FocusZone.class, str);
    }

    public static FocusZone[] values() {
        return (FocusZone[]) $VALUES.clone();
    }
}
