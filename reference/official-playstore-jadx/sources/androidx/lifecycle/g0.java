package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public abstract class g0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final k0 f3235i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f3236l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3237m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ h0 f3238n;

    public g0(h0 h0Var, k0 k0Var) {
        this.f3238n = h0Var;
        this.f3235i = k0Var;
    }

    public final void a(boolean z) {
        if (z == this.f3236l) {
            return;
        }
        this.f3236l = z;
        int i10 = z ? 1 : -1;
        h0 h0Var = this.f3238n;
        int i11 = h0Var.f3241c;
        h0Var.f3241c = i10 + i11;
        if (!h0Var.f3242d) {
            h0Var.f3242d = true;
            while (true) {
                try {
                    int i12 = h0Var.f3241c;
                    if (i11 == i12) {
                        break;
                    }
                    boolean z5 = i11 == 0 && i12 > 0;
                    boolean z10 = i11 > 0 && i12 == 0;
                    if (z5) {
                        h0Var.f();
                    } else if (z10) {
                        h0Var.g();
                    }
                    i11 = i12;
                } catch (Throwable th) {
                    h0Var.f3242d = false;
                    throw th;
                }
            }
            h0Var.f3242d = false;
        }
        if (this.f3236l) {
            h0Var.d(this);
        }
    }

    public void b() {
    }

    public boolean c(y yVar) {
        return false;
    }

    public abstract boolean d();
}
