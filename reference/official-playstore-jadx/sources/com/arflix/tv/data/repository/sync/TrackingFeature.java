package com.arflix.tv.data.repository.sync;

import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/arflix/tv/data/repository/sync/TrackingFeature;", "", "<init>", "(Ljava/lang/String;I)V", "WATCHLIST", "CONTINUE_WATCHING", "WATCHED", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TrackingFeature {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ TrackingFeature[] $VALUES;
    public static final TrackingFeature WATCHLIST = new TrackingFeature("WATCHLIST", 0);
    public static final TrackingFeature CONTINUE_WATCHING = new TrackingFeature("CONTINUE_WATCHING", 1);
    public static final TrackingFeature WATCHED = new TrackingFeature("WATCHED", 2);

    private static final /* synthetic */ TrackingFeature[] $values() {
        return new TrackingFeature[]{WATCHLIST, CONTINUE_WATCHING, WATCHED};
    }

    static {
        TrackingFeature[] trackingFeatureArr$values = $values();
        $VALUES = trackingFeatureArr$values;
        $ENTRIES = new b(trackingFeatureArr$values);
    }

    private TrackingFeature(String str, int i10) {
    }

    public static g7.a<TrackingFeature> getEntries() {
        return $ENTRIES;
    }

    public static TrackingFeature valueOf(String str) {
        return (TrackingFeature) Enum.valueOf(TrackingFeature.class, str);
    }

    public static TrackingFeature[] values() {
        return (TrackingFeature[]) $VALUES.clone();
    }
}
