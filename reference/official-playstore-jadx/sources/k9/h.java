package k9;

import g8.b0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public final class h {
    public static b a(List list, b0 b0Var, d8.m mVar) {
        List listC1 = kotlin.collections.x.c1(list);
        ArrayList arrayList = new ArrayList();
        Iterator it = listC1.iterator();
        while (it.hasNext()) {
            g gVarB = b(it.next(), null);
            if (gVarB != null) {
                arrayList.add(gVarB);
            }
        }
        return b0Var != null ? new x(arrayList, b0Var.j().q(mVar)) : new b(arrayList, new ab.h(mVar, 22));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [kotlin.collections.z] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v15, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v16, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v17, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v18, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v19, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v20, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v21, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.ArrayList] */
    public static g b(Object obj, b0 b0Var) {
        if (obj instanceof Byte) {
            return new d(((Number) obj).byteValue());
        }
        if (obj instanceof Short) {
            return new v(((Number) obj).shortValue());
        }
        if (obj instanceof Integer) {
            return new k(((Number) obj).intValue());
        }
        if (obj instanceof Long) {
            return new t(((Number) obj).longValue());
        }
        if (obj instanceof Character) {
            return new e((Character) obj);
        }
        if (obj instanceof Float) {
            return new c(((Number) obj).floatValue());
        }
        if (obj instanceof Double) {
            return new c(((Number) obj).doubleValue());
        }
        if (obj instanceof Boolean) {
            return new c((Boolean) obj);
        }
        if (obj instanceof String) {
            return new w((String) obj);
        }
        if (obj instanceof byte[]) {
            return a(kotlin.collections.r.n0((byte[]) obj), b0Var, d8.m.BYTE);
        }
        boolean z = obj instanceof short[];
        ?? SingletonList = z.f19728i;
        int i10 = 0;
        if (z) {
            short[] sArr = (short[]) obj;
            int length = sArr.length;
            if (length != 0) {
                if (length != 1) {
                    SingletonList = new ArrayList(sArr.length);
                    int length2 = sArr.length;
                    while (i10 < length2) {
                        SingletonList.add(Short.valueOf(sArr[i10]));
                        i10++;
                    }
                } else {
                    SingletonList = Collections.singletonList(Short.valueOf(sArr[0]));
                }
            }
            return a(SingletonList, b0Var, d8.m.SHORT);
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            int length3 = iArr.length;
            if (length3 != 0) {
                if (length3 != 1) {
                    SingletonList = new ArrayList(iArr.length);
                    int length4 = iArr.length;
                    while (i10 < length4) {
                        SingletonList.add(Integer.valueOf(iArr[i10]));
                        i10++;
                    }
                } else {
                    SingletonList = Collections.singletonList(Integer.valueOf(iArr[0]));
                }
            }
            return a(SingletonList, b0Var, d8.m.INT);
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            int length5 = jArr.length;
            if (length5 != 0) {
                if (length5 != 1) {
                    SingletonList = new ArrayList(jArr.length);
                    int length6 = jArr.length;
                    while (i10 < length6) {
                        SingletonList.add(Long.valueOf(jArr[i10]));
                        i10++;
                    }
                } else {
                    SingletonList = Collections.singletonList(Long.valueOf(jArr[0]));
                }
            }
            return a(SingletonList, b0Var, d8.m.LONG);
        }
        if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            int length7 = cArr.length;
            if (length7 != 0) {
                if (length7 != 1) {
                    SingletonList = new ArrayList(cArr.length);
                    int length8 = cArr.length;
                    while (i10 < length8) {
                        SingletonList.add(Character.valueOf(cArr[i10]));
                        i10++;
                    }
                } else {
                    SingletonList = Collections.singletonList(Character.valueOf(cArr[0]));
                }
            }
            return a(SingletonList, b0Var, d8.m.CHAR);
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            int length9 = fArr.length;
            if (length9 != 0) {
                if (length9 != 1) {
                    SingletonList = new ArrayList(fArr.length);
                    int length10 = fArr.length;
                    while (i10 < length10) {
                        SingletonList.add(Float.valueOf(fArr[i10]));
                        i10++;
                    }
                } else {
                    SingletonList = Collections.singletonList(Float.valueOf(fArr[0]));
                }
            }
            return a(SingletonList, b0Var, d8.m.FLOAT);
        }
        if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            int length11 = dArr.length;
            if (length11 != 0) {
                if (length11 != 1) {
                    SingletonList = new ArrayList(dArr.length);
                    int length12 = dArr.length;
                    while (i10 < length12) {
                        SingletonList.add(Double.valueOf(dArr[i10]));
                        i10++;
                    }
                } else {
                    SingletonList = Collections.singletonList(Double.valueOf(dArr[0]));
                }
            }
            return a(SingletonList, b0Var, d8.m.DOUBLE);
        }
        if (!(obj instanceof boolean[])) {
            if (obj == null) {
                return new u(null);
            }
            return null;
        }
        boolean[] zArr = (boolean[]) obj;
        int length13 = zArr.length;
        if (length13 != 0) {
            if (length13 != 1) {
                SingletonList = new ArrayList(zArr.length);
                int length14 = zArr.length;
                while (i10 < length14) {
                    SingletonList.add(Boolean.valueOf(zArr[i10]));
                    i10++;
                }
            } else {
                SingletonList = Collections.singletonList(Boolean.valueOf(zArr[0]));
            }
        }
        return a(SingletonList, b0Var, d8.m.BOOLEAN);
    }
}
