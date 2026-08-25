package c6;

import a0.c;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.google.android.gms.cast.MediaError;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f7412g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f7413a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f7414b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f7415c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f7416d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f7417e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f7418f;

    static {
        new a(4201, 4096, 1);
        new a(AnalyticsListener.EVENT_RENDERER_READY_CHANGED, 1024, 1);
        new a(67, 64, 1);
        new a(19, 16, 1);
        f7412g = new a(285, 256, 0);
        new a(MediaError.DetailedErrorCode.SEGMENT_NETWORK, 256, 1);
    }

    public a(int i10, int i11, int i12) {
        this.f7417e = i10;
        this.f7416d = i11;
        this.f7418f = i12;
        this.f7413a = new int[i11];
        this.f7414b = new int[i11];
        int i13 = 1;
        for (int i14 = 0; i14 < i11; i14++) {
            this.f7413a[i14] = i13;
            i13 *= 2;
            if (i13 >= i11) {
                i13 = (i13 ^ i10) & (i11 - 1);
            }
        }
        for (int i15 = 0; i15 < i11 - 1; i15++) {
            this.f7414b[this.f7413a[i15]] = i15;
        }
        this.f7415c = new b(this, new int[]{0});
    }

    public final int a(int i10, int i11) {
        if (i10 == 0 || i11 == 0) {
            return 0;
        }
        int[] iArr = this.f7414b;
        return this.f7413a[(iArr[i10] + iArr[i11]) % (this.f7416d - 1)];
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("GF(0x");
        sb2.append(Integer.toHexString(this.f7417e));
        sb2.append(',');
        return c.o(sb2, this.f7416d, ')');
    }
}
