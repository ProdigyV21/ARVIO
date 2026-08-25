package androidx.compose.runtime.collection;

import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import r7.l;
import t.j0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a%\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a8\u0010\t\u001a\u00020\u0007\"\b\b\u0000\u0010\u0000*\u00020\u0005*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006H\u0080\b¢\u0006\u0004\b\t\u0010\n\u001a.\u0010\f\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u00050\u00022\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\u0006H\u0080\b¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"T", "Lt/j0;", "", "wrapIntoSet", "(Lt/j0;)Ljava/util/Set;", "", "Lkotlin/Function1;", "Lx6/t0;", "block", "fastForEach", "(Ljava/util/Set;Lr7/l;)V", "", "fastAny", "(Ljava/util/Set;Lr7/l;)Z", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScatterSetWrapperKt {
    /* JADX WARN: Removed duplicated region for block: B:18:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean fastAny(java.util.Set<? extends java.lang.Object> r14, r7.l<java.lang.Object, java.lang.Boolean> r15) {
        /*
            boolean r0 = r14 instanceof androidx.compose.runtime.collection.ScatterSetWrapper
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L59
            androidx.compose.runtime.collection.ScatterSetWrapper r14 = (androidx.compose.runtime.collection.ScatterSetWrapper) r14
            t.j0 r14 = r14.getSet()
            java.lang.Object[] r0 = r14.f21836b
            long[] r14 = r14.f21835a
            int r3 = r14.length
            int r3 = r3 + (-2)
            if (r3 < 0) goto L58
            r4 = r2
        L16:
            r5 = r14[r4]
            long r7 = ~r5
            r9 = 7
            long r7 = r7 << r9
            long r7 = r7 & r5
            r9 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r7 = r7 & r9
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L53
            int r7 = r4 - r3
            int r7 = ~r7
            int r7 = r7 >>> 31
            r8 = 8
            int r7 = 8 - r7
            r9 = r2
        L30:
            if (r9 >= r7) goto L51
            r10 = 255(0xff, double:1.26E-321)
            long r10 = r10 & r5
            r12 = 128(0x80, double:6.3E-322)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L4d
            int r10 = r4 << 3
            int r10 = r10 + r9
            r10 = r0[r10]
            java.lang.Object r10 = r15.invoke(r10)
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L4d
            return r1
        L4d:
            long r5 = r5 >> r8
            int r9 = r9 + 1
            goto L30
        L51:
            if (r7 != r8) goto L58
        L53:
            if (r4 == r3) goto L58
            int r4 = r4 + 1
            goto L16
        L58:
            return r2
        L59:
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            boolean r0 = r14 instanceof java.util.Collection
            if (r0 == 0) goto L69
            r0 = r14
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L69
            return r2
        L69:
            java.util.Iterator r14 = r14.iterator()
        L6d:
            boolean r0 = r14.hasNext()
            if (r0 == 0) goto L84
            java.lang.Object r0 = r14.next()
            java.lang.Object r0 = r15.invoke(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L6d
            return r1
        L84:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.collection.ScatterSetWrapperKt.fastAny(java.util.Set, r7.l):boolean");
    }

    public static final <T> void fastForEach(Set<? extends T> set, l<? super T, t0> lVar) {
        if (!(set instanceof ScatterSetWrapper)) {
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                lVar.invoke(it.next());
            }
            return;
        }
        j0 set2 = ((ScatterSetWrapper) set).getSet();
        Object[] objArr = set2.f21836b;
        long[] jArr = set2.f21835a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i10 = 0;
        while (true) {
            long j10 = jArr[i10];
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i11 = 8 - ((~(i10 - length)) >>> 31);
                for (int i12 = 0; i12 < i11; i12++) {
                    if ((255 & j10) < 128) {
                        lVar.invoke(objArr[(i10 << 3) + i12]);
                    }
                    j10 >>= 8;
                }
                if (i11 != 8) {
                    return;
                }
            }
            if (i10 == length) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static final <T> Set<T> wrapIntoSet(j0 j0Var) {
        return new ScatterSetWrapper(j0Var);
    }
}
