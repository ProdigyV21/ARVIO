package com.arflix.tv.data.repository;

import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/arflix/tv/data/repository/SyncStatus;", "", "<init>", "(Ljava/lang/String;I)V", "IDLE", "STARTING", "SYNCING_MOVIES", "SYNCING_EPISODES", "SYNCING_PROGRESS", "COMPLETED", MediaError.ERROR_TYPE_ERROR, "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SyncStatus {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ SyncStatus[] $VALUES;
    public static final SyncStatus IDLE = new SyncStatus("IDLE", 0);
    public static final SyncStatus STARTING = new SyncStatus("STARTING", 1);
    public static final SyncStatus SYNCING_MOVIES = new SyncStatus("SYNCING_MOVIES", 2);
    public static final SyncStatus SYNCING_EPISODES = new SyncStatus("SYNCING_EPISODES", 3);
    public static final SyncStatus SYNCING_PROGRESS = new SyncStatus("SYNCING_PROGRESS", 4);
    public static final SyncStatus COMPLETED = new SyncStatus("COMPLETED", 5);
    public static final SyncStatus ERROR = new SyncStatus(MediaError.ERROR_TYPE_ERROR, 6);

    private static final /* synthetic */ SyncStatus[] $values() {
        return new SyncStatus[]{IDLE, STARTING, SYNCING_MOVIES, SYNCING_EPISODES, SYNCING_PROGRESS, COMPLETED, ERROR};
    }

    static {
        SyncStatus[] syncStatusArr$values = $values();
        $VALUES = syncStatusArr$values;
        $ENTRIES = new g7.b(syncStatusArr$values);
    }

    private SyncStatus(String str, int i10) {
    }

    public static g7.a<SyncStatus> getEntries() {
        return $ENTRIES;
    }

    public static SyncStatus valueOf(String str) {
        return (SyncStatus) Enum.valueOf(SyncStatus.class, str);
    }

    public static SyncStatus[] values() {
        return (SyncStatus[]) $VALUES.clone();
    }
}
