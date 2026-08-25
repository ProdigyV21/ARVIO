package com.google.android.datatransport.runtime;

import android.content.Context;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class w implements v {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile k f12654e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m3.a f12655a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m3.a f12656b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j3.c f12657c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final k3.f f12658d;

    public w(m3.a aVar, m3.a aVar2, j3.c cVar, k3.f fVar, k3.h hVar) {
        this.f12655a = aVar;
        this.f12656b = aVar2;
        this.f12657c = cVar;
        this.f12658d = fVar;
        hVar.f19504a.execute(new androidx.activity.n(hVar, 29));
    }

    public static w a() {
        k kVar = f12654e;
        if (kVar != null) {
            return (w) kVar.f12599q.get();
        }
        throw new IllegalStateException("Not initialized!");
    }

    public static void b(Context context) {
        if (f12654e == null) {
            synchronized (w.class) {
                try {
                    if (f12654e == null) {
                        j jVar = new j();
                        context.getClass();
                        jVar.f12592a = context;
                        f12654e = jVar.a();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public final t c(l lVar) {
        Set setUnmodifiableSet = lVar instanceof m ? Collections.unmodifiableSet(((m) lVar).a()) : Collections.singleton(new z2.b("proto"));
        lVar.getClass();
        return new t(setUnmodifiableSet, new i("cct", lVar.getExtras(), z2.c.f23164i), this);
    }
}
