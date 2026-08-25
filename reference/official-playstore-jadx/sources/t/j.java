package t;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f21834a = 0;

    static {
        long[] jArr = i0.f21833a;
        int iD = i0.d(0);
        int iMax = iD > 0 ? Math.max(7, i0.c(iD)) : 0;
        if (iMax != 0) {
            int i10 = ((iMax + 15) & (-8)) >> 3;
            long[] jArr2 = new long[i10];
            Arrays.fill(jArr2, 0, i10, -9187201950435737472L);
            jArr = jArr2;
        }
        int i11 = iMax >> 3;
        long j10 = 255 << ((iMax & 7) << 3);
        jArr[i11] = (jArr[i11] & (~j10)) | j10;
        float[] fArr = new float[iMax];
    }
}
