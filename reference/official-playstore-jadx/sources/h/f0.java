package h;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f15837a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61};

    public static /* synthetic */ int a(int i10, int i11) {
        if (i10 == 0 || i11 == 0) {
            throw null;
        }
        return i10 - i11;
    }

    public static /* synthetic */ boolean b(int i10, int i11) {
        if (i10 != 0) {
            return i10 == i11;
        }
        throw null;
    }

    public static /* synthetic */ int c(int i10) {
        if (i10 != 0) {
            return i10 - 1;
        }
        throw null;
    }

    public static /* synthetic */ int[] d(int i10) {
        int[] iArr = new int[i10];
        System.arraycopy(f15837a, 0, iArr, 0, i10);
        return iArr;
    }
}
