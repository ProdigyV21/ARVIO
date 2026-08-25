package g6;

import androidx.core.provider.j;
import androidx.recyclerview.widget.t0;
import h.f0;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes4.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f15488a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f15489b = StandardCharsets.ISO_8859_1;

    public static boolean a(int i10, f6.b bVar, int i11) {
        int i12 = bVar.f15288c;
        j jVar = bVar.f15287b[f0.c(i11)];
        int i13 = jVar.f2159b;
        int i14 = 0;
        for (t0 t0Var : (t0[]) jVar.f2160c) {
            i14 += t0Var.f5049a;
        }
        return i12 - (i14 * i13) >= (i10 + 7) / 8;
    }
}
