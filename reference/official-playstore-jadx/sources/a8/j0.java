package a8;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class j0 implements kotlin.jvm.internal.f {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final kotlin.text.m f204i = new kotlin.text.m("<v#(\\d+)>");

    public static Method A(Class cls, String str, Class[] clsArr, Class cls2, boolean z) {
        Method methodA;
        if (z) {
            clsArr[0] = cls;
        }
        Method methodC = C(cls, str, clsArr, cls2);
        if (methodC != null) {
            return methodC;
        }
        Class superclass = cls.getSuperclass();
        if (superclass != null && (methodA = A(superclass, str, clsArr, cls2, z)) != null) {
            return methodA;
        }
        Class<?>[] interfaces = cls.getInterfaces();
        int length = interfaces.length;
        int i10 = 0;
        while (true) {
            Class<?> cls3 = null;
            if (i10 >= length) {
                return null;
            }
            Class<?> cls4 = interfaces[i10];
            Method methodA2 = A(cls4, str, clsArr, cls2, z);
            if (methodA2 != null) {
                return methodA2;
            }
            if (z) {
                List list = m8.c.f20266a;
                ClassLoader classLoader = cls4.getClassLoader();
                if (classLoader == null) {
                    classLoader = ClassLoader.getSystemClassLoader();
                }
                try {
                    cls3 = Class.forName(cls4.getName().concat("$DefaultImpls"), false, classLoader);
                } catch (ClassNotFoundException unused) {
                }
                if (cls3 != null) {
                    clsArr[0] = cls4;
                    Method methodC2 = C(cls3, str, clsArr, cls2);
                    if (methodC2 != null) {
                        return methodC2;
                    }
                } else {
                    continue;
                }
            }
            i10++;
        }
    }

    public static Method C(Class cls, String str, Class[] clsArr, Class cls2) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
            if (kotlin.jvm.internal.p.a(declaredMethod.getReturnType(), cls2)) {
                return declaredMethod;
            }
            for (Method method : cls.getDeclaredMethods()) {
                if (kotlin.jvm.internal.p.a(method.getName(), str) && kotlin.jvm.internal.p.a(method.getReturnType(), cls2) && Arrays.equals(method.getParameterTypes(), clsArr)) {
                    return method;
                }
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public final Class B(int i10, int i11, String str) {
        char cCharAt = str.charAt(i10);
        if (cCharAt == 'L') {
            Class clsC = c();
            List list = m8.c.f20266a;
            ClassLoader classLoader = clsC.getClassLoader();
            if (classLoader == null) {
                classLoader = ClassLoader.getSystemClassLoader();
            }
            return classLoader.loadClass(str.substring(i10 + 1, i11 - 1).replace('/', '.'));
        }
        if (cCharAt == '[') {
            Class clsB = B(i10 + 1, i11, str);
            f9.c cVar = e2.f188a;
            return Array.newInstance((Class<?>) clsB, 0).getClass();
        }
        if (cCharAt == 'V') {
            return Void.TYPE;
        }
        if (cCharAt == 'Z') {
            return Boolean.TYPE;
        }
        if (cCharAt == 'C') {
            return Character.TYPE;
        }
        if (cCharAt == 'B') {
            return Byte.TYPE;
        }
        if (cCharAt == 'S') {
            return Short.TYPE;
        }
        if (cCharAt == 'I') {
            return Integer.TYPE;
        }
        if (cCharAt == 'F') {
            return Float.TYPE;
        }
        if (cCharAt == 'J') {
            return Long.TYPE;
        }
        if (cCharAt == 'D') {
            return Double.TYPE;
        }
        throw new x1("Unknown type prefix in the method signature: ".concat(str));
    }

    public final void m(String str, ArrayList arrayList, boolean z) {
        ArrayList arrayListZ = z(str);
        arrayList.addAll(arrayListZ);
        int size = (arrayListZ.size() + 31) / 32;
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(Integer.TYPE);
        }
        if (!z) {
            arrayList.add(Object.class);
        } else {
            arrayList.remove(kotlin.jvm.internal.h.class);
            arrayList.add(kotlin.jvm.internal.h.class);
        }
    }

    public final Method p(String str, String str2) {
        Method methodA;
        if (str.equals("<init>")) {
            return null;
        }
        Class[] clsArr = (Class[]) z(str2).toArray(new Class[0]);
        Class clsB = B(kotlin.text.o.e0(str2, ')', 0, false, 6) + 1, str2.length(), str2);
        Method methodA2 = A(x(), str, clsArr, clsB, false);
        if (methodA2 != null) {
            return methodA2;
        }
        if (!x().isInterface() || (methodA = A(Object.class, str, clsArr, clsB, false)) == null) {
            return null;
        }
        return methodA;
    }

    public abstract Collection t();

    public abstract Collection u(f9.f fVar);

    public abstract g8.p0 v(int i10);

    /* JADX WARN: Removed duplicated region for block: B:17:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List w(p9.n r9, int r10) {
        /*
            r8 = this;
            a8.i0 r0 = new a8.i0
            r1 = 0
            r0.<init>(r8, r1)
            r1 = 3
            r2 = 0
            java.util.Collection r9 = a.a.P(r9, r2, r1)
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r9 = r9.iterator()
        L17:
            boolean r3 = r9.hasNext()
            if (r3 == 0) goto L57
            java.lang.Object r3 = r9.next()
            g8.k r3 = (g8.k) r3
            boolean r4 = r3 instanceof g8.c
            if (r4 == 0) goto L50
            r4 = r3
            g8.c r4 = (g8.c) r4
            g8.p r5 = r4.getVisibility()
            g8.p r6 = g8.q.f15531h
            boolean r5 = kotlin.jvm.internal.p.a(r5, r6)
            if (r5 != 0) goto L50
            int r4 = r4.getKind()
            r5 = 2
            r6 = 0
            r7 = 1
            if (r4 == r5) goto L41
            r4 = r7
            goto L42
        L41:
            r4 = r6
        L42:
            if (r10 != r7) goto L45
            r6 = r7
        L45:
            if (r4 != r6) goto L50
            x6.t0 r4 = x6.t0.f22605a
            java.lang.Object r3 = r3.L(r0, r4)
            a8.u r3 = (a8.u) r3
            goto L51
        L50:
            r3 = r2
        L51:
            if (r3 == 0) goto L17
            r1.add(r3)
            goto L17
        L57:
            java.util.List r9 = kotlin.collections.x.c1(r1)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: a8.j0.w(p9.n, int):java.util.List");
    }

    public Class x() {
        Class cls = (Class) m8.c.f20268c.get(c());
        return cls == null ? c() : cls;
    }

    public abstract Collection y(f9.f fVar);

    public final ArrayList z(String str) {
        int iE0;
        ArrayList arrayList = new ArrayList();
        int i10 = 1;
        while (str.charAt(i10) != ')') {
            int i11 = i10;
            while (str.charAt(i11) == '[') {
                i11++;
            }
            char cCharAt = str.charAt(i11);
            if (kotlin.text.o.U("VZCBSIFJD", cCharAt)) {
                iE0 = i11 + 1;
            } else {
                if (cCharAt != 'L') {
                    throw new x1("Unknown type prefix in the method signature: ".concat(str));
                }
                iE0 = kotlin.text.o.e0(str, ';', i10, false, 4) + 1;
            }
            arrayList.add(B(i10, iE0, str));
            i10 = iE0;
        }
        return arrayList;
    }
}
