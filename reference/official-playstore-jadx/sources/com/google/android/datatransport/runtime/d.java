package com.google.android.datatransport.runtime;

import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class d implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f12573a = new d();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12574b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12575c;

    static {
        j5.a aVar = new j5.a(1);
        HashMap map = new HashMap();
        map.put(j5.e.class, aVar);
        f12574b = new com.google.firebase.encoders.c("logSource", Collections.unmodifiableMap(new HashMap(map)));
        j5.a aVar2 = new j5.a(2);
        HashMap map2 = new HashMap();
        map2.put(j5.e.class, aVar2);
        f12575c = new com.google.firebase.encoders.c("logEventDropped", Collections.unmodifiableMap(new HashMap(map2)));
    }

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        g3.e eVar = (g3.e) obj;
        com.google.firebase.encoders.e eVar2 = (com.google.firebase.encoders.e) obj2;
        eVar2.f(f12574b, eVar.f15478a);
        eVar2.f(f12575c, eVar.f15479b);
    }
}
