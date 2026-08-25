package io.ktor.server.engine;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u001a\f\u0010\u0004\u001a\u00020\u0002*\u00020\u0005H\u0000\"\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"longStrings", "", "", "[Ljava/lang/String;", "toStringFast", "", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LongKt {
    private static final String[] longStrings;

    static {
        String[] strArr = new String[1024];
        for (int i10 = 0; i10 < 1024; i10++) {
            strArr[i10] = String.valueOf(i10);
        }
        longStrings = strArr;
    }

    public static final String toStringFast(long j10) {
        return (0 > j10 || j10 >= 1024) ? String.valueOf(j10) : longStrings[(int) j10];
    }
}
