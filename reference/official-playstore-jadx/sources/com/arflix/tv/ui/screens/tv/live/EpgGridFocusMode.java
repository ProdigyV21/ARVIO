package com.arflix.tv.ui.screens.tv.live;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/EpgGridFocusMode;", "", "<init>", "(Ljava/lang/String;I)V", "ChannelList", "Epg", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class EpgGridFocusMode {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ EpgGridFocusMode[] $VALUES;
    public static final EpgGridFocusMode ChannelList = new EpgGridFocusMode("ChannelList", 0);
    public static final EpgGridFocusMode Epg = new EpgGridFocusMode("Epg", 1);

    private static final /* synthetic */ EpgGridFocusMode[] $values() {
        return new EpgGridFocusMode[]{ChannelList, Epg};
    }

    static {
        EpgGridFocusMode[] epgGridFocusModeArr$values = $values();
        $VALUES = epgGridFocusModeArr$values;
        $ENTRIES = new g7.b(epgGridFocusModeArr$values);
    }

    private EpgGridFocusMode(String str, int i10) {
    }

    public static g7.a<EpgGridFocusMode> getEntries() {
        return $ENTRIES;
    }

    public static EpgGridFocusMode valueOf(String str) {
        return (EpgGridFocusMode) Enum.valueOf(EpgGridFocusMode.class, str);
    }

    public static EpgGridFocusMode[] values() {
        return (EpgGridFocusMode[]) $VALUES.clone();
    }
}
