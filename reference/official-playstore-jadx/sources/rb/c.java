package rb;

import gb.h0;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final CopyOnWriteArraySet f21448a = new CopyOnWriteArraySet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f21449b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Package r22 = h0.class.getPackage();
        String name = r22 != null ? r22.getName() : null;
        if (name != null) {
            linkedHashMap.put(name, "OkHttp");
        }
        linkedHashMap.put(h0.class.getName(), "okhttp.OkHttpClient");
        linkedHashMap.put(ob.e.class.getName(), "okhttp.Http2");
        linkedHashMap.put(kb.e.class.getName(), "okhttp.TaskRunner");
        linkedHashMap.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        f21449b = kotlin.collections.h0.B0(linkedHashMap);
    }
}
