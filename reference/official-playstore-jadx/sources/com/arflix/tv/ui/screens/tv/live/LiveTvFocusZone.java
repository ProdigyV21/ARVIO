package com.arflix.tv.ui.screens.tv.live;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveTvFocusZone;", "", "<init>", "(Ljava/lang/String;I)V", "TOPBAR", "PROVIDER_SWITCHER", "CATEGORY_LIST", "CHANNEL_LIST", "EPG", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class LiveTvFocusZone {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ LiveTvFocusZone[] $VALUES;
    public static final LiveTvFocusZone TOPBAR = new LiveTvFocusZone("TOPBAR", 0);
    public static final LiveTvFocusZone PROVIDER_SWITCHER = new LiveTvFocusZone("PROVIDER_SWITCHER", 1);
    public static final LiveTvFocusZone CATEGORY_LIST = new LiveTvFocusZone("CATEGORY_LIST", 2);
    public static final LiveTvFocusZone CHANNEL_LIST = new LiveTvFocusZone("CHANNEL_LIST", 3);
    public static final LiveTvFocusZone EPG = new LiveTvFocusZone("EPG", 4);

    private static final /* synthetic */ LiveTvFocusZone[] $values() {
        return new LiveTvFocusZone[]{TOPBAR, PROVIDER_SWITCHER, CATEGORY_LIST, CHANNEL_LIST, EPG};
    }

    static {
        LiveTvFocusZone[] liveTvFocusZoneArr$values = $values();
        $VALUES = liveTvFocusZoneArr$values;
        $ENTRIES = new g7.b(liveTvFocusZoneArr$values);
    }

    private LiveTvFocusZone(String str, int i10) {
    }

    public static g7.a<LiveTvFocusZone> getEntries() {
        return $ENTRIES;
    }

    public static LiveTvFocusZone valueOf(String str) {
        return (LiveTvFocusZone) Enum.valueOf(LiveTvFocusZone.class, str);
    }

    public static LiveTvFocusZone[] values() {
        return (LiveTvFocusZone[]) $VALUES.clone();
    }
}
