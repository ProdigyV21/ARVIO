package com.arflix.tv.data.repository;

import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/data/repository/TvDeviceAuthStatusType;", "", "<init>", "(Ljava/lang/String;I)V", "PENDING", "APPROVED", "EXPIRED", MediaError.ERROR_TYPE_ERROR, "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TvDeviceAuthStatusType {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ TvDeviceAuthStatusType[] $VALUES;
    public static final TvDeviceAuthStatusType PENDING = new TvDeviceAuthStatusType("PENDING", 0);
    public static final TvDeviceAuthStatusType APPROVED = new TvDeviceAuthStatusType("APPROVED", 1);
    public static final TvDeviceAuthStatusType EXPIRED = new TvDeviceAuthStatusType("EXPIRED", 2);
    public static final TvDeviceAuthStatusType ERROR = new TvDeviceAuthStatusType(MediaError.ERROR_TYPE_ERROR, 3);

    private static final /* synthetic */ TvDeviceAuthStatusType[] $values() {
        return new TvDeviceAuthStatusType[]{PENDING, APPROVED, EXPIRED, ERROR};
    }

    static {
        TvDeviceAuthStatusType[] tvDeviceAuthStatusTypeArr$values = $values();
        $VALUES = tvDeviceAuthStatusTypeArr$values;
        $ENTRIES = new g7.b(tvDeviceAuthStatusTypeArr$values);
    }

    private TvDeviceAuthStatusType(String str, int i10) {
    }

    public static g7.a<TvDeviceAuthStatusType> getEntries() {
        return $ENTRIES;
    }

    public static TvDeviceAuthStatusType valueOf(String str) {
        return (TvDeviceAuthStatusType) Enum.valueOf(TvDeviceAuthStatusType.class, str);
    }

    public static TvDeviceAuthStatusType[] values() {
        return (TvDeviceAuthStatusType[]) $VALUES.clone();
    }
}
