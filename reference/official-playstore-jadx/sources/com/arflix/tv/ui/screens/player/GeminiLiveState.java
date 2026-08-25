package com.arflix.tv.ui.screens.player;

import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/ui/screens/player/GeminiLiveState;", "", "<init>", "(Ljava/lang/String;I)V", "DISCONNECTED", "CONNECTING", "READY", MediaError.ERROR_TYPE_ERROR, "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class GeminiLiveState {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ GeminiLiveState[] $VALUES;
    public static final GeminiLiveState DISCONNECTED = new GeminiLiveState("DISCONNECTED", 0);
    public static final GeminiLiveState CONNECTING = new GeminiLiveState("CONNECTING", 1);
    public static final GeminiLiveState READY = new GeminiLiveState("READY", 2);
    public static final GeminiLiveState ERROR = new GeminiLiveState(MediaError.ERROR_TYPE_ERROR, 3);

    private static final /* synthetic */ GeminiLiveState[] $values() {
        return new GeminiLiveState[]{DISCONNECTED, CONNECTING, READY, ERROR};
    }

    static {
        GeminiLiveState[] geminiLiveStateArr$values = $values();
        $VALUES = geminiLiveStateArr$values;
        $ENTRIES = new g7.b(geminiLiveStateArr$values);
    }

    private GeminiLiveState(String str, int i10) {
    }

    public static g7.a<GeminiLiveState> getEntries() {
        return $ENTRIES;
    }

    public static GeminiLiveState valueOf(String str) {
        return (GeminiLiveState) Enum.valueOf(GeminiLiveState.class, str);
    }

    public static GeminiLiveState[] values() {
        return (GeminiLiveState[]) $VALUES.clone();
    }
}
