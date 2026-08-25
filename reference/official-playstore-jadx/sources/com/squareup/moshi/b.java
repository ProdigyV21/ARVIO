package com.squareup.moshi;

import java.io.EOFException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f14399d = new a(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f14400e = new a(3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14401a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l f14402b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f14403c;

    public b(z zVar, Type type, Type type2) {
        Set set = j6.e.f19235a;
        this.f14402b = zVar.a(type, set, null);
        this.f14403c = zVar.a(type2, set, null);
    }

    @Override // com.squareup.moshi.l
    public final Object a(p pVar) throws EOFException {
        switch (this.f14401a) {
            case 0:
                ArrayList arrayList = new ArrayList();
                pVar.beginArray();
                while (pVar.hasNext()) {
                    arrayList.add(this.f14402b.a(pVar));
                }
                pVar.endArray();
                Object objNewInstance = Array.newInstance((Class<?>) this.f14403c, arrayList.size());
                for (int i10 = 0; i10 < arrayList.size(); i10++) {
                    Array.set(objNewInstance, i10, arrayList.get(i10));
                }
                return objNewInstance;
            default:
                v vVar = new v();
                pVar.beginObject();
                while (pVar.hasNext()) {
                    if (pVar.hasNext()) {
                        pVar.f14454t = pVar.nextName();
                        pVar.f14451q = 11;
                    }
                    Object objA = this.f14402b.a(pVar);
                    Object objA2 = ((l) this.f14403c).a(pVar);
                    Object objPut = vVar.put(objA, objA2);
                    if (objPut != null) {
                        throw new JsonDataException("Map key '" + objA + "' has multiple values at path " + pVar.i() + ": " + objPut + " and " + objA2);
                    }
                }
                pVar.endObject();
                return vVar;
        }
    }

    @Override // com.squareup.moshi.l
    public final void e(q qVar, Object obj) {
        switch (this.f14401a) {
            case 0:
                qVar.l();
                int length = Array.getLength(obj);
                for (int i10 = 0; i10 < length; i10++) {
                    this.f14402b.e(qVar, Array.get(obj, i10));
                }
                qVar.x(1, 2, ']');
                return;
            default:
                qVar.o();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    if (entry.getKey() == null) {
                        throw new JsonDataException("Map key is null at " + qVar.i());
                    }
                    int iJ = qVar.j();
                    if (iJ != 5 && iJ != 3) {
                        throw new IllegalStateException("Nesting problem.");
                    }
                    qVar.f14462o = true;
                    this.f14402b.e(qVar, entry.getKey());
                    ((l) this.f14403c).e(qVar, entry.getValue());
                }
                qVar.f14462o = false;
                qVar.x(3, 5, '}');
                return;
        }
    }

    public final String toString() {
        switch (this.f14401a) {
            case 0:
                return this.f14402b + ".array()";
            default:
                return "JsonAdapter(" + this.f14402b + "=" + ((l) this.f14403c) + ")";
        }
    }

    public b(Class cls, l lVar) {
        this.f14403c = cls;
        this.f14402b = lVar;
    }
}
