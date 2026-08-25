package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/arflix/tv/data/repository/CloudSyncStatus;", "", "<init>", "(Ljava/lang/String;I)V", "CONNECTED", "RECONNECTING", "NOT_SIGNED_IN", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CloudSyncStatus {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ CloudSyncStatus[] $VALUES;
    public static final CloudSyncStatus CONNECTED = new CloudSyncStatus("CONNECTED", 0);
    public static final CloudSyncStatus RECONNECTING = new CloudSyncStatus("RECONNECTING", 1);
    public static final CloudSyncStatus NOT_SIGNED_IN = new CloudSyncStatus("NOT_SIGNED_IN", 2);

    private static final /* synthetic */ CloudSyncStatus[] $values() {
        return new CloudSyncStatus[]{CONNECTED, RECONNECTING, NOT_SIGNED_IN};
    }

    static {
        CloudSyncStatus[] cloudSyncStatusArr$values = $values();
        $VALUES = cloudSyncStatusArr$values;
        $ENTRIES = new g7.b(cloudSyncStatusArr$values);
    }

    private CloudSyncStatus(String str, int i10) {
    }

    public static g7.a<CloudSyncStatus> getEntries() {
        return $ENTRIES;
    }

    public static CloudSyncStatus valueOf(String str) {
        return (CloudSyncStatus) Enum.valueOf(CloudSyncStatus.class, str);
    }

    public static CloudSyncStatus[] values() {
        return (CloudSyncStatus[]) $VALUES.clone();
    }
}
