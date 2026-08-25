package com.google.android.datatransport.runtime;

import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class c implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f12570a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12571b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12572c;

    static {
        j5.a aVar = new j5.a(1);
        HashMap map = new HashMap();
        map.put(j5.e.class, aVar);
        f12571b = new com.google.firebase.encoders.c("eventsDroppedCount", Collections.unmodifiableMap(new HashMap(map)));
        j5.a aVar2 = new j5.a(3);
        HashMap map2 = new HashMap();
        map2.put(j5.e.class, aVar2);
        f12572c = new com.google.firebase.encoders.c("reason", Collections.unmodifiableMap(new HashMap(map2)));
    }

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        g3.d dVar = (g3.d) obj;
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        eVar.b(f12571b, dVar.f15475a);
        eVar.f(f12572c, dVar.f15476b);
    }
}
