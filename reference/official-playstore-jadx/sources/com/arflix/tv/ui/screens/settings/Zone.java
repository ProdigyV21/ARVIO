package com.arflix.tv.ui.screens.settings;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/arflix/tv/ui/screens/settings/Zone;", "", "<init>", "(Ljava/lang/String;I)V", "SIDEBAR", "SECTION", "CONTENT", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class Zone {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ Zone[] $VALUES;
    public static final Zone SIDEBAR = new Zone("SIDEBAR", 0);
    public static final Zone SECTION = new Zone("SECTION", 1);
    public static final Zone CONTENT = new Zone("CONTENT", 2);

    private static final /* synthetic */ Zone[] $values() {
        return new Zone[]{SIDEBAR, SECTION, CONTENT};
    }

    static {
        Zone[] zoneArr$values = $values();
        $VALUES = zoneArr$values;
        $ENTRIES = new g7.b(zoneArr$values);
    }

    private Zone(String str, int i10) {
    }

    public static g7.a<Zone> getEntries() {
        return $ENTRIES;
    }

    public static Zone valueOf(String str) {
        return (Zone) Enum.valueOf(Zone.class, str);
    }

    public static Zone[] values() {
        return (Zone[]) $VALUES.clone();
    }
}
