package io.sentry.util;

import io.sentry.g0;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap f17973a = new ConcurrentHashMap();

    public static boolean a(String str, List list) {
        if (str != null && list != null && !list.isEmpty()) {
            ConcurrentHashMap concurrentHashMap = f17973a;
            if (concurrentHashMap.containsKey(str)) {
                return ((Boolean) concurrentHashMap.get(str)).booleanValue();
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((g0) it.next()).f17292a.equalsIgnoreCase(str)) {
                    concurrentHashMap.put(str, Boolean.TRUE);
                    return true;
                }
            }
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                try {
                    Pattern pattern = ((g0) it2.next()).f17293b;
                    if (pattern == null ? false : pattern.matcher(str).matches()) {
                        concurrentHashMap.put(str, Boolean.TRUE);
                        return true;
                    }
                    continue;
                } catch (Throwable unused) {
                }
            }
            concurrentHashMap.put(str, Boolean.FALSE);
        }
        return false;
    }
}
