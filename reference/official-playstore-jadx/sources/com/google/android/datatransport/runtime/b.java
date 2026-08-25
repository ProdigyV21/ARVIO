package com.google.android.datatransport.runtime;

import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class b implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f12568a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f12569b;

    static {
        j5.a aVar = new j5.a(1);
        HashMap map = new HashMap();
        map.put(j5.e.class, aVar);
        f12569b = new com.google.firebase.encoders.c("storageMetrics", Collections.unmodifiableMap(new HashMap(map)));
    }

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        ((com.google.firebase.encoders.e) obj2).f(f12569b, ((g3.b) obj).f15465a);
    }
}
