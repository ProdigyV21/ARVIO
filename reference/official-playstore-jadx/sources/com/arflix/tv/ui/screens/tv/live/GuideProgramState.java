package com.arflix.tv.ui.screens.tv.live;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/GuideProgramState;", "", "<init>", "(Ljava/lang/String;I)V", "PastPlayable", "PastUnavailable", "Live", "Future", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class GuideProgramState {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ GuideProgramState[] $VALUES;
    public static final GuideProgramState PastPlayable = new GuideProgramState("PastPlayable", 0);
    public static final GuideProgramState PastUnavailable = new GuideProgramState("PastUnavailable", 1);
    public static final GuideProgramState Live = new GuideProgramState("Live", 2);
    public static final GuideProgramState Future = new GuideProgramState("Future", 3);

    private static final /* synthetic */ GuideProgramState[] $values() {
        return new GuideProgramState[]{PastPlayable, PastUnavailable, Live, Future};
    }

    static {
        GuideProgramState[] guideProgramStateArr$values = $values();
        $VALUES = guideProgramStateArr$values;
        $ENTRIES = new g7.b(guideProgramStateArr$values);
    }

    private GuideProgramState(String str, int i10) {
    }

    public static g7.a<GuideProgramState> getEntries() {
        return $ENTRIES;
    }

    public static GuideProgramState valueOf(String str) {
        return (GuideProgramState) Enum.valueOf(GuideProgramState.class, str);
    }

    public static GuideProgramState[] values() {
        return (GuideProgramState[]) $VALUES.clone();
    }
}
