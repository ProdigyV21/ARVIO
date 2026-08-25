package com.google.android.datatransport.runtime;

import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class a implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f12563a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12564b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12565c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12566d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12567e;

    static {
        j5.a aVar = new j5.a(1);
        HashMap map = new HashMap();
        map.put(j5.e.class, aVar);
        f12564b = new com.google.firebase.encoders.c("window", Collections.unmodifiableMap(new HashMap(map)));
        j5.a aVar2 = new j5.a(2);
        HashMap map2 = new HashMap();
        map2.put(j5.e.class, aVar2);
        f12565c = new com.google.firebase.encoders.c("logSourceMetrics", Collections.unmodifiableMap(new HashMap(map2)));
        j5.a aVar3 = new j5.a(3);
        HashMap map3 = new HashMap();
        map3.put(j5.e.class, aVar3);
        f12566d = new com.google.firebase.encoders.c("globalMetrics", Collections.unmodifiableMap(new HashMap(map3)));
        j5.a aVar4 = new j5.a(4);
        HashMap map4 = new HashMap();
        map4.put(j5.e.class, aVar4);
        f12567e = new com.google.firebase.encoders.c("appNamespace", Collections.unmodifiableMap(new HashMap(map4)));
    }

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        g3.a aVar = (g3.a) obj;
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        eVar.f(f12564b, aVar.f15461a);
        eVar.f(f12565c, aVar.f15462b);
        eVar.f(f12566d, aVar.f15463c);
        eVar.f(f12567e, aVar.f15464d);
    }
}
