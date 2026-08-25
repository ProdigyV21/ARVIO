package xb;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
public abstract class k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j0 f22767a = new j0(new byte[0], 0, 0, false, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f22768b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AtomicReference[] f22769c;

    static {
        int iHighestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        f22768b = iHighestOneBit;
        AtomicReference[] atomicReferenceArr = new AtomicReference[iHighestOneBit];
        for (int i10 = 0; i10 < iHighestOneBit; i10++) {
            atomicReferenceArr[i10] = new AtomicReference();
        }
        f22769c = atomicReferenceArr;
    }

    public static final void a(j0 j0Var) {
        if (j0Var.f22765f != null || j0Var.f22766g != null) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (j0Var.f22763d) {
            return;
        }
        AtomicReference atomicReference = f22769c[(int) (Thread.currentThread().getId() & (((long) f22768b) - 1))];
        j0 j0Var2 = f22767a;
        j0 j0Var3 = (j0) atomicReference.getAndSet(j0Var2);
        if (j0Var3 == j0Var2) {
            return;
        }
        int i10 = j0Var3 != null ? j0Var3.f22762c : 0;
        if (i10 >= 65536) {
            atomicReference.set(j0Var3);
            return;
        }
        j0Var.f22765f = j0Var3;
        j0Var.f22761b = 0;
        j0Var.f22762c = i10 + 8192;
        atomicReference.set(j0Var);
    }

    public static final j0 b() {
        AtomicReference atomicReference = f22769c[(int) (Thread.currentThread().getId() & (((long) f22768b) - 1))];
        j0 j0Var = f22767a;
        j0 j0Var2 = (j0) atomicReference.getAndSet(j0Var);
        if (j0Var2 == j0Var) {
            return new j0();
        }
        if (j0Var2 == null) {
            atomicReference.set(null);
            return new j0();
        }
        atomicReference.set(j0Var2.f22765f);
        j0Var2.f22765f = null;
        j0Var2.f22762c = 0;
        return j0Var2;
    }
}
