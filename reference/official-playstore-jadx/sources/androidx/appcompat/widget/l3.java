package androidx.appcompat.widget;

/* JADX INFO: loaded from: classes.dex */
public final class l3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1648a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1649b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1650c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1651d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1652e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1653f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1654g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1655h;

    public final void a(int i10, int i11) {
        this.f1650c = i10;
        this.f1651d = i11;
        this.f1655h = true;
        if (this.f1654g) {
            if (i11 != Integer.MIN_VALUE) {
                this.f1648a = i11;
            }
            if (i10 != Integer.MIN_VALUE) {
                this.f1649b = i10;
                return;
            }
            return;
        }
        if (i10 != Integer.MIN_VALUE) {
            this.f1648a = i10;
        }
        if (i11 != Integer.MIN_VALUE) {
            this.f1649b = i11;
        }
    }
}
