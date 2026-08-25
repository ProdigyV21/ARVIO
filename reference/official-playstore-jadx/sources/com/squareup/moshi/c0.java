package com.squareup.moshi;

import androidx.fragment.app.a2;
import java.io.EOFException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class c0 extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14407a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f14408b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f14409c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f14410d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f14411e;

    public c0(Class cls) {
        int i10 = 0;
        this.f14409c = cls;
        try {
            Enum[] enumArr = (Enum[]) cls.getEnumConstants();
            this.f14411e = enumArr;
            this.f14410d = new String[enumArr.length];
            while (true) {
                Enum[] enumArr2 = (Enum[]) this.f14411e;
                if (i10 >= enumArr2.length) {
                    this.f14408b = io.sentry.internal.debugmeta.c.B((String[]) this.f14410d);
                    return;
                }
                String strName = enumArr2[i10].name();
                String[] strArr = (String[]) this.f14410d;
                Field field = cls.getField(strName);
                Set set = j6.e.f19235a;
                j jVar = (j) field.getAnnotation(j.class);
                if (jVar != null) {
                    String strName2 = jVar.name();
                    if (!"\u0000".equals(strName2)) {
                        strName = strName2;
                    }
                }
                strArr[i10] = strName;
                i10++;
            }
        } catch (NoSuchFieldException e5) {
            throw new AssertionError("Missing field in ".concat(cls.getName()), e5);
        }
    }

    @Override // com.squareup.moshi.l
    public final Object a(p pVar) throws EOFException {
        int iD0;
        String string;
        String string2;
        int i10 = this.f14407a;
        Object obj = this.f14411e;
        io.sentry.internal.debugmeta.c cVar = this.f14408b;
        Object obj2 = this.f14410d;
        switch (i10) {
            case 0:
                int iO = pVar.f14451q;
                if (iO == 0) {
                    iO = pVar.o();
                }
                if (iO < 8 || iO > 11) {
                    iD0 = -1;
                } else if (iO == 11) {
                    iD0 = pVar.y(pVar.f14454t, cVar);
                } else {
                    iD0 = pVar.f14449o.d0((xb.e0) cVar.f17368m);
                    if (iD0 != -1) {
                        pVar.f14451q = 0;
                        int[] iArr = pVar.f14445n;
                        int i11 = pVar.f14442i - 1;
                        iArr[i11] = iArr[i11] + 1;
                    } else {
                        String strNextString = pVar.nextString();
                        int iY = pVar.y(strNextString, cVar);
                        if (iY == -1) {
                            pVar.f14451q = 11;
                            pVar.f14454t = strNextString;
                            pVar.f14445n[pVar.f14442i - 1] = r2[r7] - 1;
                        }
                        iD0 = iY;
                    }
                }
                if (iD0 != -1) {
                    return ((Enum[]) obj)[iD0];
                }
                String strI = pVar.i();
                throw new JsonDataException("Expected one of " + Arrays.asList((String[]) obj2) + " but was " + pVar.nextString() + " at path " + strI);
            default:
                kotlin.reflect.g gVar = (kotlin.reflect.g) this.f14409c;
                int size = gVar.getParameters().size();
                ArrayList arrayList = (ArrayList) obj2;
                int size2 = arrayList.size();
                Object[] objArr = new Object[size2];
                int i12 = 0;
                while (true) {
                    Object obj3 = k6.c.f19520a;
                    if (i12 >= size2) {
                        pVar.beginObject();
                        while (pVar.hasNext()) {
                            int iT0 = pVar.t0(cVar);
                            if (iT0 == -1) {
                                pVar.u0();
                                pVar.skipValue();
                            } else {
                                k6.a aVar = (k6.a) ((ArrayList) obj).get(iT0);
                                int i13 = aVar.f19517e;
                                kotlin.reflect.o oVar = aVar.f19515c;
                                if (objArr[i13] != obj3) {
                                    throw new JsonDataException("Multiple values for '" + oVar.getName() + "' at " + pVar.i());
                                }
                                Object objA = aVar.f19514b.a(pVar);
                                objArr[i13] = objA;
                                if (objA == null && !oVar.getReturnType().j()) {
                                    String name = oVar.getName();
                                    String str = aVar.f19513a;
                                    Set set = j6.e.f19235a;
                                    String strI2 = pVar.i();
                                    if (str.equals(name)) {
                                        string2 = androidx.compose.material3.d.n("Non-null value '", name, "' was null at ", strI2);
                                    } else {
                                        StringBuilder sbR = a2.r("Non-null value '", name, "' (JSON name '", str, "') was null at ");
                                        sbR.append(strI2);
                                        string2 = sbR.toString();
                                    }
                                    throw new JsonDataException(string2);
                                }
                            }
                        }
                        pVar.endObject();
                        boolean z = arrayList.size() == size;
                        for (int i14 = 0; i14 < size; i14++) {
                            if (objArr[i14] == obj3) {
                                if (((kotlin.reflect.l) gVar.getParameters().get(i14)).s()) {
                                    z = false;
                                } else {
                                    if (!((kotlin.reflect.l) gVar.getParameters().get(i14)).getType().f282i.J0()) {
                                        String name2 = ((kotlin.reflect.l) gVar.getParameters().get(i14)).getName();
                                        k6.a aVar2 = (k6.a) arrayList.get(i14);
                                        String str2 = aVar2 != null ? aVar2.f19513a : null;
                                        Set set2 = j6.e.f19235a;
                                        String strI3 = pVar.i();
                                        if (str2.equals(name2)) {
                                            string = androidx.compose.material3.d.n("Required value '", name2, "' missing at ", strI3);
                                        } else {
                                            StringBuilder sbR2 = a2.r("Required value '", name2, "' (JSON name '", str2, "') missing at ");
                                            sbR2.append(strI3);
                                            string = sbR2.toString();
                                        }
                                        throw new JsonDataException(string);
                                    }
                                    objArr[i14] = null;
                                }
                            }
                        }
                        Object objCall = z ? gVar.call(Arrays.copyOf(objArr, size2)) : gVar.callBy(new k6.b(gVar.getParameters(), objArr));
                        int size3 = arrayList.size();
                        while (size < size3) {
                            k6.a aVar3 = (k6.a) arrayList.get(size);
                            Object obj4 = objArr[size];
                            if (obj4 != obj3) {
                                ((kotlin.reflect.j) aVar3.f19515c).set(objCall, obj4);
                            }
                            size++;
                        }
                        return objCall;
                    }
                    objArr[i12] = obj3;
                    i12++;
                }
                break;
        }
    }

    @Override // com.squareup.moshi.l
    public final void e(q qVar, Object obj) {
        switch (this.f14407a) {
            case 0:
                qVar.V(((String[]) this.f14410d)[((Enum) obj).ordinal()]);
                return;
            default:
                if (obj == null) {
                    throw new NullPointerException("value == null");
                }
                qVar.o();
                for (k6.a aVar : (ArrayList) this.f14410d) {
                    if (aVar != null) {
                        qVar.y(aVar.f19513a);
                        aVar.f19514b.e(qVar, aVar.f19515c.get(obj));
                    }
                }
                qVar.f14462o = false;
                qVar.x(3, 5, '}');
                return;
        }
    }

    public final String toString() {
        switch (this.f14407a) {
            case 0:
                return "JsonAdapter(" + ((Class) this.f14409c).getName() + ")";
            default:
                return "KotlinJsonAdapter(" + ((kotlin.reflect.g) this.f14409c).getReturnType() + ')';
        }
    }

    public c0(kotlin.reflect.g gVar, ArrayList arrayList, ArrayList arrayList2, io.sentry.internal.debugmeta.c cVar) {
        this.f14409c = gVar;
        this.f14410d = arrayList;
        this.f14411e = arrayList2;
        this.f14408b = cVar;
    }
}
