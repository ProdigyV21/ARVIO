package e9;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends c9.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final f f15073g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final f f15074h;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f15075f;

    static {
        f fVar = new f(false, new int[]{1, 9, 0});
        f15073g = fVar;
        int i10 = fVar.f7426c;
        int i11 = fVar.f7425b;
        f15074h = (i11 == 1 && i10 == 9) ? new f(false, new int[]{2, 0, 0}) : new f(false, new int[]{i11, i10 + 1, 0});
        new f(false, new int[0]);
    }

    public f(boolean z, int[] iArr) {
        super(Arrays.copyOf(iArr, iArr.length));
        this.f15075f = z;
    }

    public final boolean b(f fVar) {
        f fVar2 = f15073g;
        int i10 = this.f7426c;
        int i11 = this.f7425b;
        if (i11 == 2 && i10 == 0 && fVar2.f7425b == 1 && fVar2.f7426c == 8) {
            return true;
        }
        fVar.getClass();
        if (!this.f15075f) {
            fVar2 = f15074h;
        }
        int i12 = fVar2.f7425b;
        int i13 = fVar.f7425b;
        if (i12 > i13 || (i12 >= i13 && fVar2.f7426c > fVar.f7426c)) {
            fVar = fVar2;
        }
        boolean z = false;
        if ((i11 == 1 && i10 == 0) || i11 == 0) {
            return false;
        }
        int i14 = fVar.f7425b;
        if (i11 > i14 || (i11 >= i14 && i10 > fVar.f7426c)) {
            z = true;
        }
        return !z;
    }
}
