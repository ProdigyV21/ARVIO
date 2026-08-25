package io.sentry.config;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f17197a;

    public b(ArrayList arrayList) {
        this.f17197a = arrayList;
    }

    @Override // io.sentry.config.f
    public final Map a() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Iterator it = this.f17197a.iterator();
        while (it.hasNext()) {
            concurrentHashMap.putAll(((f) it.next()).a());
        }
        return concurrentHashMap;
    }

    public final Boolean b(String str) {
        String property = getProperty(str);
        if (property != null) {
            return Boolean.valueOf(property);
        }
        return null;
    }

    public final List c(String str) {
        String property = getProperty(str);
        return property != null ? Arrays.asList(property.split(",")) : Collections.EMPTY_LIST;
    }

    public final Long d(String str) {
        String property = getProperty(str);
        if (property != null) {
            try {
                return Long.valueOf(property);
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    @Override // io.sentry.config.f
    public final String getProperty(String str) {
        Iterator it = this.f17197a.iterator();
        while (it.hasNext()) {
            String property = ((f) it.next()).getProperty(str);
            if (property != null) {
                return property;
            }
        }
        return null;
    }
}
