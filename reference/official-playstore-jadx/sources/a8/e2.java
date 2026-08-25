package a8;

import androidx.compose.material3.TextFieldImplKt;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class e2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f9.c f188a = new f9.c("kotlin.jvm.JvmStatic");

    public static final u a(Object obj) {
        u uVar = obj instanceof u ? (u) obj : null;
        if (uVar != null) {
            return uVar;
        }
        m0 m0VarB = b(obj);
        return m0VarB != null ? m0VarB : c(obj);
    }

    public static final m0 b(Object obj) {
        m0 m0Var = obj instanceof m0 ? (m0) obj : null;
        if (m0Var != null) {
            return m0Var;
        }
        kotlin.jvm.internal.l lVar = obj instanceof kotlin.jvm.internal.l ? (kotlin.jvm.internal.l) obj : null;
        kotlin.reflect.c cVarCompute = lVar != null ? lVar.compute() : null;
        if (cVarCompute instanceof m0) {
            return (m0) cVarCompute;
        }
        return null;
    }

    public static final r1 c(Object obj) {
        r1 r1Var = obj instanceof r1 ? (r1) obj : null;
        if (r1Var != null) {
            return r1Var;
        }
        kotlin.jvm.internal.e0 e0Var = obj instanceof kotlin.jvm.internal.e0 ? (kotlin.jvm.internal.e0) obj : null;
        kotlin.reflect.c cVarCompute = e0Var != null ? e0Var.compute() : null;
        if (cVarCompute instanceof r1) {
            return (r1) cVarCompute;
        }
        return null;
    }

    public static final ArrayList d(h8.a aVar) {
        h8.h annotations = aVar.getAnnotations();
        ArrayList<Annotation> arrayList = new ArrayList();
        Iterator<h8.b> it = annotations.iterator();
        while (true) {
            Annotation annotationH = null;
            if (!it.hasNext()) {
                break;
            }
            h8.b next = it.next();
            g8.v0 v0VarF = next.f();
            if (v0VarF instanceof l8.a) {
                annotationH = ((l8.a) v0VarF).f20061i;
            } else if (v0VarF instanceof l8.i) {
                m8.s sVar = ((l8.i) v0VarF).f20070i;
                m8.d dVar = sVar instanceof m8.d ? (m8.d) sVar : null;
                if (dVar != null) {
                    annotationH = dVar.f20270a;
                }
            } else {
                annotationH = h(next);
            }
            if (annotationH != null) {
                arrayList.add(annotationH);
            }
        }
        if (!arrayList.isEmpty()) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                if (((kotlin.jvm.internal.f) com.google.common.util.concurrent.r0.k((Annotation) it2.next())).c().getSimpleName().equals(TextFieldImplKt.ContainerId)) {
                    ArrayList arrayList2 = new ArrayList();
                    for (Annotation annotation : arrayList) {
                        Class clsC = ((kotlin.jvm.internal.f) com.google.common.util.concurrent.r0.k(annotation)).c();
                        kotlin.collections.x.b0(arrayList2, (!clsC.getSimpleName().equals(TextFieldImplKt.ContainerId) || clsC.getAnnotation(kotlin.jvm.internal.n0.class) == null) ? Collections.singletonList(annotation) : Arrays.asList((Annotation[]) clsC.getDeclaredMethod("value", null).invoke(annotation, null)));
                    }
                    return arrayList2;
                }
            }
        }
        return arrayList;
    }

    public static final Object e(Type type) {
        if (!(type instanceof Class) || !((Class) type).isPrimitive()) {
            return null;
        }
        if (type.equals(Boolean.TYPE)) {
            return Boolean.FALSE;
        }
        if (type.equals(Character.TYPE)) {
            return (char) 0;
        }
        if (type.equals(Byte.TYPE)) {
            return (byte) 0;
        }
        if (type.equals(Short.TYPE)) {
            return (short) 0;
        }
        if (type.equals(Integer.TYPE)) {
            return 0;
        }
        if (type.equals(Float.TYPE)) {
            return Float.valueOf(0.0f);
        }
        if (type.equals(Long.TYPE)) {
            return 0L;
        }
        if (type.equals(Double.TYPE)) {
            return Double.valueOf(0.0d);
        }
        if (type.equals(Void.TYPE)) {
            throw new IllegalStateException("Parameter with void type is illegal");
        }
        throw new UnsupportedOperationException("Unknown primitive: " + type);
    }

    public static final g8.a f(Class cls, kotlin.reflect.jvm.internal.impl.protobuf.q qVar, c9.f fVar, androidx.work.impl.constraints.j jVar, c9.a aVar, r7.p pVar) {
        List list;
        l8.h hVarA = y1.a(cls);
        if (qVar instanceof a9.i0) {
            list = ((a9.i0) qVar).f492s;
        } else {
            if (!(qVar instanceof a9.t0)) {
                throw new IllegalStateException(("Unsupported message: " + qVar).toString());
            }
            list = ((a9.t0) qVar).f683s;
        }
        List list2 = list;
        s9.j jVar2 = hVarA.f20068a;
        return (g8.a) pVar.invoke(new s9.x(new s9.m(jVar2, fVar, jVar2.f21707b, jVar, c9.g.f7455b, aVar, null, null, list2)), qVar);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class g(ClassLoader classLoader, f9.b bVar, int i10) {
        String str = f8.d.f15297a;
        f9.b bVarE = f8.d.e(bVar.b().i());
        if (bVarE != null) {
            bVar = bVarE;
        }
        String strB = bVar.g().b();
        String strB2 = bVar.h().b();
        if (strB.equals("kotlin")) {
            switch (strB2.hashCode()) {
                case -901856463:
                    if (strB2.equals("BooleanArray")) {
                        return boolean[].class;
                    }
                    break;
                case -763279523:
                    if (strB2.equals("ShortArray")) {
                        return short[].class;
                    }
                    break;
                case -755911549:
                    if (strB2.equals("CharArray")) {
                        return char[].class;
                    }
                    break;
                case -74930671:
                    if (strB2.equals("ByteArray")) {
                        return byte[].class;
                    }
                    break;
                case 22374632:
                    if (strB2.equals("DoubleArray")) {
                        return double[].class;
                    }
                    break;
                case 63537721:
                    if (strB2.equals("Array")) {
                        return Object[].class;
                    }
                    break;
                case 601811914:
                    if (strB2.equals("IntArray")) {
                        return int[].class;
                    }
                    break;
                case 948852093:
                    if (strB2.equals("FloatArray")) {
                        return float[].class;
                    }
                    break;
                case 2104330525:
                    if (strB2.equals("LongArray")) {
                        return long[].class;
                    }
                    break;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        if (i10 > 0) {
            for (int i11 = 0; i11 < i10; i11++) {
                sb2.append("[");
            }
            sb2.append("L");
        }
        if (strB.length() > 0) {
            sb2.append(strB.concat("."));
        }
        sb2.append(strB2.replace('.', '$'));
        if (i10 > 0) {
            sb2.append(";");
        }
        try {
            return Class.forName(sb2.toString(), false, classLoader);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static final Annotation h(h8.b bVar) {
        g8.f fVarD = m9.d.d(bVar);
        Class clsI = fVarD != null ? i(fVarD) : null;
        if (clsI == null) {
            clsI = null;
        }
        if (clsI == null) {
            return null;
        }
        Set<Map.Entry> setEntrySet = bVar.a().entrySet();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : setEntrySet) {
            f9.f fVar = (f9.f) entry.getKey();
            Object objJ = j((k9.g) entry.getValue(), clsI.getClassLoader());
            x6.x xVar = objJ != null ? new x6.x(fVar.d(), objJ) : null;
            if (xVar != null) {
                arrayList.add(xVar);
            }
        }
        Map mapA0 = kotlin.collections.h0.A0(arrayList);
        Set setKeySet = mapA0.keySet();
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(setKeySet, 10));
        Iterator it = setKeySet.iterator();
        while (it.hasNext()) {
            arrayList2.add(clsI.getDeclaredMethod((String) it.next(), null));
        }
        return (Annotation) k2.c.f(clsI, mapA0, arrayList2);
    }

    public static final Class i(g8.f fVar) {
        g8.v0 v0VarF = fVar.f();
        if (v0VarF instanceof y8.z) {
            return ((l8.e) ((y8.z) v0VarF).f22986i).f20063a;
        }
        if (v0VarF instanceof l8.i) {
            return ((m8.o) ((l8.i) v0VarF).f20070i).f20288a;
        }
        f9.b bVarF = m9.d.f(fVar);
        if (bVarF == null) {
            return null;
        }
        Class<?> cls = fVar.getClass();
        List list = m8.c.f20266a;
        ClassLoader classLoader = cls.getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        return g(classLoader, bVarF, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Object j(k9.g gVar, ClassLoader classLoader) {
        v9.w wVar;
        Class clsG;
        if (gVar instanceof k9.a) {
            return h((h8.b) ((k9.a) gVar).f19530a);
        }
        int i10 = 0;
        if (gVar instanceof k9.b) {
            k9.b bVar = (k9.b) gVar;
            k9.x xVar = bVar instanceof k9.x ? (k9.x) bVar : null;
            if (xVar != null && (wVar = xVar.f19539c) != null) {
                Object obj = bVar.f19530a;
                Iterable iterable = (Iterable) obj;
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    arrayList.add(j((k9.g) it.next(), classLoader));
                }
                f9.f fVar = d8.k.f14705e;
                g8.h hVarE = wVar.I0().e();
                d8.m mVarR = hVarE == null ? null : d8.k.r(hVarE);
                switch (mVarR == null ? -1 : d2.f181a[mVarR.ordinal()]) {
                    case -1:
                        if (!d8.k.y(wVar)) {
                            throw new IllegalStateException(("Not an array type: " + wVar).toString());
                        }
                        v9.w type = ((v9.r0) kotlin.collections.x.R0(wVar.G0())).getType();
                        g8.h hVarE2 = type.I0().e();
                        g8.f fVar2 = hVarE2 instanceof g8.f ? (g8.f) hVarE2 : null;
                        if (fVar2 == null) {
                            throw new IllegalStateException(("Not a class type: " + type).toString());
                        }
                        if (d8.k.H(type)) {
                            int size = ((List) obj).size();
                            String[] strArr = new String[size];
                            while (i10 < size) {
                                strArr[i10] = arrayList.get(i10);
                                i10++;
                            }
                            return strArr;
                        }
                        if (d8.k.b(fVar2, d8.p.P)) {
                            int size2 = ((List) obj).size();
                            Class[] clsArr = new Class[size2];
                            while (i10 < size2) {
                                clsArr[i10] = arrayList.get(i10);
                                i10++;
                            }
                            return clsArr;
                        }
                        f9.b bVarF = m9.d.f(fVar2);
                        if (bVarF != null && (clsG = g(classLoader, bVarF, 0)) != null) {
                            Object[] objArr = (Object[]) Array.newInstance((Class<?>) clsG, ((List) obj).size());
                            int size3 = arrayList.size();
                            while (i10 < size3) {
                                objArr[i10] = arrayList.get(i10);
                                i10++;
                            }
                            return objArr;
                        }
                        break;
                    case 0:
                    default:
                        throw new NoWhenBranchMatchedException();
                    case 1:
                        int size4 = ((List) obj).size();
                        boolean[] zArr = new boolean[size4];
                        while (i10 < size4) {
                            zArr[i10] = ((Boolean) arrayList.get(i10)).booleanValue();
                            i10++;
                        }
                        return zArr;
                    case 2:
                        int size5 = ((List) obj).size();
                        char[] cArr = new char[size5];
                        while (i10 < size5) {
                            cArr[i10] = ((Character) arrayList.get(i10)).charValue();
                            i10++;
                        }
                        return cArr;
                    case 3:
                        int size6 = ((List) obj).size();
                        byte[] bArr = new byte[size6];
                        while (i10 < size6) {
                            bArr[i10] = ((Byte) arrayList.get(i10)).byteValue();
                            i10++;
                        }
                        return bArr;
                    case 4:
                        int size7 = ((List) obj).size();
                        short[] sArr = new short[size7];
                        while (i10 < size7) {
                            sArr[i10] = ((Short) arrayList.get(i10)).shortValue();
                            i10++;
                        }
                        return sArr;
                    case 5:
                        int size8 = ((List) obj).size();
                        int[] iArr = new int[size8];
                        while (i10 < size8) {
                            iArr[i10] = ((Integer) arrayList.get(i10)).intValue();
                            i10++;
                        }
                        return iArr;
                    case 6:
                        int size9 = ((List) obj).size();
                        float[] fArr = new float[size9];
                        while (i10 < size9) {
                            fArr[i10] = ((Float) arrayList.get(i10)).floatValue();
                            i10++;
                        }
                        return fArr;
                    case 7:
                        int size10 = ((List) obj).size();
                        long[] jArr = new long[size10];
                        while (i10 < size10) {
                            jArr[i10] = ((Long) arrayList.get(i10)).longValue();
                            i10++;
                        }
                        return jArr;
                    case 8:
                        int size11 = ((List) obj).size();
                        double[] dArr = new double[size11];
                        while (i10 < size11) {
                            dArr[i10] = ((Double) arrayList.get(i10)).doubleValue();
                            i10++;
                        }
                        return dArr;
                }
            }
        } else if (gVar instanceof k9.i) {
            x6.x xVar2 = (x6.x) ((k9.i) gVar).f19530a;
            f9.b bVar2 = (f9.b) xVar2.f22608i;
            f9.f fVar3 = (f9.f) xVar2.f22609l;
            Class clsG2 = g(classLoader, bVar2, 0);
            if (clsG2 != null) {
                return Enum.valueOf(clsG2, fVar3.d());
            }
        } else if (gVar instanceof k9.s) {
            k9.r rVar = (k9.r) ((k9.s) gVar).f19530a;
            if (rVar instanceof k9.q) {
                k9.f fVar4 = ((k9.q) rVar).f19538a;
                return g(classLoader, fVar4.f19528a, fVar4.f19529b);
            }
            if (!(rVar instanceof k9.p)) {
                throw new NoWhenBranchMatchedException();
            }
            g8.h hVarE3 = ((k9.p) rVar).f19537a.I0().e();
            g8.f fVar5 = hVarE3 instanceof g8.f ? (g8.f) hVarE3 : null;
            if (fVar5 != null) {
                return i(fVar5);
            }
        } else {
            if (!(gVar instanceof k9.j ? true : gVar instanceof k9.u)) {
                return gVar.b();
            }
        }
        return null;
    }
}
