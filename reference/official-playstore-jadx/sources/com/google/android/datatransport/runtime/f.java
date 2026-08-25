package com.google.android.datatransport.runtime;

import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class f implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f12577a = new f();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12578b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12579c;

    static {
        j5.a aVar = new j5.a(1);
        HashMap map = new HashMap();
        map.put(j5.e.class, aVar);
        f12578b = new com.google.firebase.encoders.c("currentCacheSizeBytes", Collections.unmodifiableMap(new HashMap(map)));
        j5.a aVar2 = new j5.a(2);
        HashMap map2 = new HashMap();
        map2.put(j5.e.class, aVar2);
        f12579c = new com.google.firebase.encoders.c("maxCacheSizeBytes", Collections.unmodifiableMap(new HashMap(map2)));
    }

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        g3.f fVar = (g3.f) obj;
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        eVar.b(f12578b, fVar.f15480a);
        eVar.b(f12579c, fVar.f15481b);
    }
}
