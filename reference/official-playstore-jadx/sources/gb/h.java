package gb;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class h {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final h f15686n = new h(true, false, -1, -1, false, false, false, -1, -1, false, false, false, null);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final h f15687o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f15688a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f15689b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f15690c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f15691d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f15692e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f15693f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f15694g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f15695h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f15696i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f15697j;
    public final boolean k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f15698l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f15699m;

    static {
        long seconds = TimeUnit.SECONDS.toSeconds(Integer.MAX_VALUE);
        f15687o = new h(false, false, -1, -1, false, false, false, seconds <= 2147483647L ? (int) seconds : Integer.MAX_VALUE, -1, true, false, false, null);
    }

    public h(boolean z, boolean z5, int i10, int i11, boolean z10, boolean z11, boolean z12, int i12, int i13, boolean z13, boolean z14, boolean z15, String str) {
        this.f15688a = z;
        this.f15689b = z5;
        this.f15690c = i10;
        this.f15691d = i11;
        this.f15692e = z10;
        this.f15693f = z11;
        this.f15694g = z12;
        this.f15695h = i12;
        this.f15696i = i13;
        this.f15697j = z13;
        this.k = z14;
        this.f15698l = z15;
        this.f15699m = str;
    }

    public final String toString() {
        String str = this.f15699m;
        if (str != null) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder();
        if (this.f15688a) {
            sb2.append("no-cache, ");
        }
        if (this.f15689b) {
            sb2.append("no-store, ");
        }
        int i10 = this.f15690c;
        if (i10 != -1) {
            sb2.append("max-age=");
            sb2.append(i10);
            sb2.append(", ");
        }
        int i11 = this.f15691d;
        if (i11 != -1) {
            sb2.append("s-maxage=");
            sb2.append(i11);
            sb2.append(", ");
        }
        if (this.f15692e) {
            sb2.append("private, ");
        }
        if (this.f15693f) {
            sb2.append("public, ");
        }
        if (this.f15694g) {
            sb2.append("must-revalidate, ");
        }
        int i12 = this.f15695h;
        if (i12 != -1) {
            sb2.append("max-stale=");
            sb2.append(i12);
            sb2.append(", ");
        }
        int i13 = this.f15696i;
        if (i13 != -1) {
            sb2.append("min-fresh=");
            sb2.append(i13);
            sb2.append(", ");
        }
        if (this.f15697j) {
            sb2.append("only-if-cached, ");
        }
        if (this.k) {
            sb2.append("no-transform, ");
        }
        if (this.f15698l) {
            sb2.append("immutable, ");
        }
        if (sb2.length() == 0) {
            return "";
        }
        sb2.delete(sb2.length() - 2, sb2.length());
        String string = sb2.toString();
        this.f15699m = string;
        return string;
    }
}
