package com.arflix.tv.ui.screens.watchlist;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/TrackerLibraryProvider;", "", "displayName", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getDisplayName", "()Ljava/lang/String;", "TRAKT", "SIMKL", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TrackerLibraryProvider {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ TrackerLibraryProvider[] $VALUES;
    private final String displayName;
    public static final TrackerLibraryProvider TRAKT = new TrackerLibraryProvider("TRAKT", 0, "Trakt");
    public static final TrackerLibraryProvider SIMKL = new TrackerLibraryProvider("SIMKL", 1, "Simkl");

    private static final /* synthetic */ TrackerLibraryProvider[] $values() {
        return new TrackerLibraryProvider[]{TRAKT, SIMKL};
    }

    static {
        TrackerLibraryProvider[] trackerLibraryProviderArr$values = $values();
        $VALUES = trackerLibraryProviderArr$values;
        $ENTRIES = new g7.b(trackerLibraryProviderArr$values);
    }

    private TrackerLibraryProvider(String str, int i10, String str2) {
        this.displayName = str2;
    }

    public static g7.a<TrackerLibraryProvider> getEntries() {
        return $ENTRIES;
    }

    public static TrackerLibraryProvider valueOf(String str) {
        return (TrackerLibraryProvider) Enum.valueOf(TrackerLibraryProvider.class, str);
    }

    public static TrackerLibraryProvider[] values() {
        return (TrackerLibraryProvider[]) $VALUES.clone();
    }

    public final String getDisplayName() {
        return this.displayName;
    }
}
