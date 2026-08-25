package com.arflix.tv.data.repository.sync;

import g7.b;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.text.u;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/repository/sync/TrackingReadMode;", "", "<init>", "(Ljava/lang/String;I)V", "AUTO", "TRAKT", "SIMKL", "BOTH", "MDBLIST", "toStorage", "", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TrackingReadMode {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ TrackingReadMode[] $VALUES;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final TrackingReadMode AUTO = new TrackingReadMode("AUTO", 0);
    public static final TrackingReadMode TRAKT = new TrackingReadMode("TRAKT", 1);
    public static final TrackingReadMode SIMKL = new TrackingReadMode("SIMKL", 2);
    public static final TrackingReadMode BOTH = new TrackingReadMode("BOTH", 3);
    public static final TrackingReadMode MDBLIST = new TrackingReadMode("MDBLIST", 4);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/data/repository/sync/TrackingReadMode$Companion;", "", "<init>", "()V", "fromStorage", "Lcom/arflix/tv/data/repository/sync/TrackingReadMode;", "value", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final TrackingReadMode fromStorage(String value) {
            TrackingReadMode next;
            Iterator<TrackingReadMode> it = TrackingReadMode.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (u.L(next.name(), value, true)) {
                    break;
                }
            }
            TrackingReadMode trackingReadMode = next;
            return trackingReadMode == null ? TrackingReadMode.AUTO : trackingReadMode;
        }

        private Companion() {
        }
    }

    private static final /* synthetic */ TrackingReadMode[] $values() {
        return new TrackingReadMode[]{AUTO, TRAKT, SIMKL, BOTH, MDBLIST};
    }

    static {
        TrackingReadMode[] trackingReadModeArr$values = $values();
        $VALUES = trackingReadModeArr$values;
        $ENTRIES = new b(trackingReadModeArr$values);
        INSTANCE = new Companion(null);
    }

    private TrackingReadMode(String str, int i10) {
    }

    public static g7.a<TrackingReadMode> getEntries() {
        return $ENTRIES;
    }

    public static TrackingReadMode valueOf(String str) {
        return (TrackingReadMode) Enum.valueOf(TrackingReadMode.class, str);
    }

    public static TrackingReadMode[] values() {
        return (TrackingReadMode[]) $VALUES.clone();
    }

    public final String toStorage() {
        return name().toLowerCase(Locale.ROOT);
    }
}
