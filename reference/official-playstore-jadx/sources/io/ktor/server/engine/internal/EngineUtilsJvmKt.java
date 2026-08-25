package io.ktor.server.engine.internal;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0010\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"OS_NAME", "", "escapeHostname", "value", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EngineUtilsJvmKt {
    private static final String OS_NAME = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);

    public static final String escapeHostname(String str) {
        return (o.T(OS_NAME, "windows", false) && str.equals("0.0.0.0")) ? "127.0.0.1" : str;
    }
}
