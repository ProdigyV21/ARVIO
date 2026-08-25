package t;

/* JADX INFO: loaded from: classes.dex */
public abstract class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long[] f21833a = {-9187201950435737345L, -1};

    static {
        new z(0);
    }

    public static final int a(int i10) {
        if (i10 == 7) {
            return 6;
        }
        return i10 - (i10 / 8);
    }

    public static final int b(int i10) {
        if (i10 == 0) {
            return 6;
        }
        return (i10 * 2) + 1;
    }

    public static final int c(int i10) {
        if (i10 > 0) {
            return (-1) >>> Integer.numberOfLeadingZeros(i10);
        }
        return 0;
    }

    public static final int d(int i10) {
        if (i10 == 7) {
            return 8;
        }
        return ((i10 - 1) / 7) + i10;
    }
}
