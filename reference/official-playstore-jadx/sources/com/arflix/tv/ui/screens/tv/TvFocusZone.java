package com.arflix.tv.ui.screens.tv;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/TvFocusZone;", "", "<init>", "(Ljava/lang/String;I)V", "SIDEBAR", "GROUPS", "GUIDE", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class TvFocusZone {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ TvFocusZone[] $VALUES;
    public static final TvFocusZone SIDEBAR = new TvFocusZone("SIDEBAR", 0);
    public static final TvFocusZone GROUPS = new TvFocusZone("GROUPS", 1);
    public static final TvFocusZone GUIDE = new TvFocusZone("GUIDE", 2);

    private static final /* synthetic */ TvFocusZone[] $values() {
        return new TvFocusZone[]{SIDEBAR, GROUPS, GUIDE};
    }

    static {
        TvFocusZone[] tvFocusZoneArr$values = $values();
        $VALUES = tvFocusZoneArr$values;
        $ENTRIES = new g7.b(tvFocusZoneArr$values);
    }

    private TvFocusZone(String str, int i10) {
    }

    public static g7.a<TvFocusZone> getEntries() {
        return $ENTRIES;
    }

    public static TvFocusZone valueOf(String str) {
        return (TvFocusZone) Enum.valueOf(TvFocusZone.class, str);
    }

    public static TvFocusZone[] values() {
        return (TvFocusZone[]) $VALUES.clone();
    }
}
