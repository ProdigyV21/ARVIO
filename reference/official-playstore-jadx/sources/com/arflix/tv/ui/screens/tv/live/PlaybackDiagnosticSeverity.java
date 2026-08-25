package com.arflix.tv.ui.screens.tv.live;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/PlaybackDiagnosticSeverity;", "", "<init>", "(Ljava/lang/String;I)V", "Info", "Warning", "Error", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PlaybackDiagnosticSeverity {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ PlaybackDiagnosticSeverity[] $VALUES;
    public static final PlaybackDiagnosticSeverity Info = new PlaybackDiagnosticSeverity("Info", 0);
    public static final PlaybackDiagnosticSeverity Warning = new PlaybackDiagnosticSeverity("Warning", 1);
    public static final PlaybackDiagnosticSeverity Error = new PlaybackDiagnosticSeverity("Error", 2);

    private static final /* synthetic */ PlaybackDiagnosticSeverity[] $values() {
        return new PlaybackDiagnosticSeverity[]{Info, Warning, Error};
    }

    static {
        PlaybackDiagnosticSeverity[] playbackDiagnosticSeverityArr$values = $values();
        $VALUES = playbackDiagnosticSeverityArr$values;
        $ENTRIES = new g7.b(playbackDiagnosticSeverityArr$values);
    }

    private PlaybackDiagnosticSeverity(String str, int i10) {
    }

    public static g7.a<PlaybackDiagnosticSeverity> getEntries() {
        return $ENTRIES;
    }

    public static PlaybackDiagnosticSeverity valueOf(String str) {
        return (PlaybackDiagnosticSeverity) Enum.valueOf(PlaybackDiagnosticSeverity.class, str);
    }

    public static PlaybackDiagnosticSeverity[] values() {
        return (PlaybackDiagnosticSeverity[]) $VALUES.clone();
    }
}
