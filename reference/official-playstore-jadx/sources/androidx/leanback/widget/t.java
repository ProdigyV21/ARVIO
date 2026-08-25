package androidx.leanback.widget;

/* JADX INFO: loaded from: classes3.dex */
public abstract class t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a8.e f3126b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3127c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3128d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3129e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public t.g[] f3132h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f3125a = new Object[1];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3130f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3131g = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3133i = -1;

    public final boolean a() {
        return b(this.f3127c ? Integer.MAX_VALUE : Integer.MIN_VALUE, true);
    }

    public abstract boolean b(int i10, boolean z);

    public final boolean c(int i10) {
        return this.f3131g >= 0 && (!this.f3127c ? g(false, null) < i10 - this.f3128d : i(true, null) > i10 + this.f3128d);
    }

    public final boolean d(int i10) {
        return this.f3131g >= 0 && (!this.f3127c ? i(true, null) > i10 + this.f3128d : g(false, null) < i10 - this.f3128d);
    }

    public abstract int f(int i10, int[] iArr, boolean z);

    public final int g(boolean z, int[] iArr) {
        return f(this.f3127c ? this.f3130f : this.f3131g, iArr, z);
    }

    public abstract int h(int i10, int[] iArr, boolean z);

    public final int i(boolean z, int[] iArr) {
        return h(this.f3127c ? this.f3131g : this.f3130f, iArr, z);
    }

    public abstract t.g[] j(int i10, int i11);

    public abstract r k(int i10);

    public void l(int i10) {
        int i11;
        if (i10 >= 0 && (i11 = this.f3131g) >= 0) {
            if (i11 >= i10) {
                this.f3131g = i10 - 1;
            }
            if (this.f3131g < this.f3130f) {
                this.f3131g = -1;
                this.f3130f = -1;
            }
            if (this.f3130f < 0) {
                this.f3133i = i10;
            }
        }
    }

    public abstract boolean m(int i10, boolean z);

    public final void n(int i10) {
        if (i10 <= 0) {
            throw new IllegalArgumentException();
        }
        if (this.f3129e == i10) {
            return;
        }
        this.f3129e = i10;
        this.f3132h = new t.g[i10];
        for (int i11 = 0; i11 < this.f3129e; i11++) {
            this.f3132h[i11] = new t.g();
        }
    }

    public void e(int i10, int i11, androidx.recyclerview.widget.s sVar) {
    }
}
