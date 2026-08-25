package t;

import java.util.ConcurrentModificationException;

/* JADX INFO: loaded from: classes.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f21861a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f21862b = new Object();

    public static final void a(m0 m0Var) {
        int i10 = m0Var.f21854n;
        int[] iArr = m0Var.f21852l;
        Object[] objArr = m0Var.f21853m;
        int i11 = 0;
        for (int i12 = 0; i12 < i10; i12++) {
            Object obj = objArr[i12];
            if (obj != f21862b) {
                if (i12 != i11) {
                    iArr[i11] = iArr[i12];
                    objArr[i11] = obj;
                    objArr[i12] = null;
                }
                i11++;
            }
        }
        m0Var.f21851i = false;
        m0Var.f21854n = i11;
    }

    public static final int b(f fVar, Object obj, int i10) {
        int i11 = fVar.f21811m;
        if (i11 == 0) {
            return -1;
        }
        try {
            int iA = u.a.a(i11, i10, fVar.f21809i);
            if (iA < 0 || kotlin.jvm.internal.p.a(obj, fVar.f21810l[iA])) {
                return iA;
            }
            int i12 = iA + 1;
            while (i12 < i11 && fVar.f21809i[i12] == i10) {
                if (kotlin.jvm.internal.p.a(obj, fVar.f21810l[i12])) {
                    return i12;
                }
                i12++;
            }
            for (int i13 = iA - 1; i13 >= 0 && fVar.f21809i[i13] == i10; i13--) {
                if (kotlin.jvm.internal.p.a(obj, fVar.f21810l[i13])) {
                    return i13;
                }
            }
            return ~i12;
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }
}
