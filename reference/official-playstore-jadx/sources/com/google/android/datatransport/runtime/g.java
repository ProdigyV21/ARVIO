package com.google.android.datatransport.runtime;

import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class g implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f12580a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12581b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12582c;

    static {
        j5.a aVar = new j5.a(1);
        HashMap map = new HashMap();
        map.put(j5.e.class, aVar);
        f12581b = new com.google.firebase.encoders.c("startMs", Collections.unmodifiableMap(new HashMap(map)));
        j5.a aVar2 = new j5.a(2);
        HashMap map2 = new HashMap();
        map2.put(j5.e.class, aVar2);
        f12582c = new com.google.firebase.encoders.c("endMs", Collections.unmodifiableMap(new HashMap(map2)));
    }

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        g3.g gVar = (g3.g) obj;
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        eVar.b(f12581b, gVar.f15482a);
        eVar.b(f12582c, gVar.f15483b);
    }
}
