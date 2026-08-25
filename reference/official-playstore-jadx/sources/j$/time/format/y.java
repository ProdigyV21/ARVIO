package j$.time.format;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f18302a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f18303b;

    public y(Map map) {
        this.f18302a = map;
        HashMap map2 = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            HashMap map3 = new HashMap();
            for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                String str = (String) entry2.getValue();
                String str2 = (String) entry2.getValue();
                Long l10 = (Long) entry2.getKey();
                ConcurrentHashMap concurrentHashMap = z.f18304a;
                map3.put(str, new AbstractMap.SimpleImmutableEntry(str2, l10));
            }
            ArrayList arrayList2 = new ArrayList(map3.values());
            Collections.sort(arrayList2, z.f18305b);
            map2.put((TextStyle) entry.getKey(), arrayList2);
            arrayList.addAll(arrayList2);
            map2.put(null, arrayList);
        }
        Collections.sort(arrayList, z.f18305b);
        this.f18303b = map2;
    }

    public final String a(long j10, TextStyle textStyle) {
        Map map = (Map) this.f18302a.get(textStyle);
        if (map != null) {
            return (String) map.get(Long.valueOf(j10));
        }
        return null;
    }
}
