package io.sentry.config;

import io.sentry.util.s;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f17201a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Properties f17202b;

    public g(String str, Properties properties) {
        this.f17201a = str;
        ac.b.V(properties, "properties are required");
        this.f17202b = properties;
    }

    @Override // io.sentry.config.f
    public final Map a() {
        String strP = a0.c.p(new StringBuilder(), this.f17201a, "tags.");
        HashMap map = new HashMap();
        for (Map.Entry entry : this.f17202b.entrySet()) {
            if ((entry.getKey() instanceof String) && (entry.getValue() instanceof String)) {
                String str = (String) entry.getKey();
                if (str.startsWith(strP)) {
                    map.put(str.substring(strP.length()), s.b((String) entry.getValue()));
                }
            }
        }
        return map;
    }

    @Override // io.sentry.config.f
    public final String getProperty(String str) {
        return s.b(this.f17202b.getProperty(this.f17201a + str));
    }

    public g(Properties properties) {
        this("", properties);
    }
}
