package com.squareup.moshi;

import java.io.EOFException;
import java.lang.reflect.InvocationTargetException;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes4.dex */
public final class g extends l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f14435d = new a(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e0 f14436a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f[] f14437b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f14438c;

    public g(e0 e0Var, TreeMap treeMap) {
        this.f14436a = e0Var;
        this.f14437b = (f[]) treeMap.values().toArray(new f[treeMap.size()]);
        this.f14438c = io.sentry.internal.debugmeta.c.B((String[]) treeMap.keySet().toArray(new String[treeMap.size()]));
    }

    @Override // com.squareup.moshi.l
    public final Object a(p pVar) throws EOFException {
        try {
            Object objE = this.f14436a.e();
            try {
                pVar.beginObject();
                while (pVar.hasNext()) {
                    int iT0 = pVar.t0(this.f14438c);
                    if (iT0 == -1) {
                        pVar.u0();
                        pVar.skipValue();
                    } else {
                        f fVar = this.f14437b[iT0];
                        fVar.f14433b.set(objE, fVar.f14434c.a(pVar));
                    }
                }
                pVar.endObject();
                return objE;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        } catch (IllegalAccessException unused2) {
            throw new AssertionError();
        } catch (InstantiationException e5) {
            throw new RuntimeException(e5);
        } catch (InvocationTargetException e6) {
            j6.e.h(e6);
            throw null;
        }
    }

    @Override // com.squareup.moshi.l
    public final void e(q qVar, Object obj) {
        try {
            qVar.o();
            for (f fVar : this.f14437b) {
                qVar.y(fVar.f14432a);
                fVar.f14434c.e(qVar, fVar.f14433b.get(obj));
            }
            qVar.f14462o = false;
            qVar.x(3, 5, '}');
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        }
    }

    public final String toString() {
        return "JsonAdapter(" + this.f14436a + ")";
    }
}
