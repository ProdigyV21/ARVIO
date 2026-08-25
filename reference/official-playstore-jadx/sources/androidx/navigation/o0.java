package androidx.navigation;

/* JADX INFO: loaded from: classes3.dex */
public final class o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m0 f4741a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4742b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4743c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4744d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4745e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4746f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4747g;

    public o0() {
        m0 m0Var = new m0();
        m0Var.f4719a = -1;
        m0Var.f4723e = -1;
        m0Var.f4724f = -1;
        this.f4741a = m0Var;
        this.f4744d = -1;
    }

    public final void a(int i10) {
        this.f4744d = i10;
        this.f4746f = false;
    }

    public final void b(String str) {
        if (str != null) {
            if (kotlin.text.o.h0(str)) {
                throw new IllegalArgumentException("Cannot pop up to an empty route");
            }
            this.f4745e = str;
            this.f4746f = false;
        }
    }
}
