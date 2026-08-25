package com.google.android.gms.cast.framework;

import android.os.Bundle;
import com.google.android.gms.internal.cast.u0;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class CastReasonCodes {
    public static final int APPLICATION_LAUNCH_ERROR = 10;
    public static final int CASTING_ROUTE_CHANGED = 9;
    public static final int CASTING_STOPPED = 2;
    public static final int CAST_CANCELLED = 11;
    public static final int CAST_INTERNAL_ERROR = 1;
    public static final int CAST_SOCKET_ERROR = 6;
    public static final int CAST_TIMEOUT = 7;
    public static final int NETWORK_ERROR = 8;
    public static final int RECEIVER_APP_NOT_RUNNING = 3;
    public static final int SESSION_RESUME_FAILED = 5;
    public static final int SESSION_START_FAILED = 4;
    public static final int UNKNOWN_REASON = 0;
    private final Map zza;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CastReasonType {
    }

    public CastReasonCodes(Bundle bundle) {
        this.zza = u0.n(bundle, "com.google.android.gms.cast.MAP_CAST_STATUS_CODES_TO_CAST_REASON_CODES");
    }

    public final int zza(int i10) {
        Integer num;
        Map map = this.zza;
        if (map == null) {
            return 0;
        }
        Integer numValueOf = Integer.valueOf(i10);
        if (map.containsKey(numValueOf) && (num = (Integer) map.get(numValueOf)) != null) {
            return num.intValue();
        }
        return 0;
    }
}
