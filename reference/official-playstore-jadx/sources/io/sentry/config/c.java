package io.sentry.config;

import io.sentry.util.s;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements f {
    public static String b(String str) {
        return "SENTRY_" + str.replace(".", "_").replace("-", "_").toUpperCase(Locale.ROOT);
    }

    @Override // io.sentry.config.f
    public final Map a() {
        String strB;
        String str = b("tags") + "_";
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
            String key = entry.getKey();
            if (key.startsWith(str) && (strB = s.b(entry.getValue())) != null) {
                concurrentHashMap.put(key.substring(str.length()).toLowerCase(Locale.ROOT), strB);
            }
        }
        return concurrentHashMap;
    }

    @Override // io.sentry.config.f
    public final String getProperty(String str) {
        return s.b(System.getenv(b(str)));
    }
}
