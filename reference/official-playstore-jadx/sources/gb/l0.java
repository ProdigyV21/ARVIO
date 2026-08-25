package gb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes5.dex */
public final class l0 extends n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15736a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d0 f15737b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f15738c;

    public /* synthetic */ l0(d0 d0Var, Object obj, int i10) {
        this.f15736a = i10;
        this.f15737b = d0Var;
        this.f15738c = obj;
    }

    @Override // gb.n0
    public final long a() {
        switch (this.f15736a) {
            case 0:
                return ((File) this.f15738c).length();
            case 1:
                return ((xb.m) this.f15738c).f();
            default:
                return ((n0) this.f15738c).a();
        }
    }

    @Override // gb.n0
    public final d0 b() {
        switch (this.f15736a) {
        }
        return this.f15737b;
    }

    @Override // gb.n0
    public final void c(xb.k kVar) throws IOException {
        int i10 = this.f15736a;
        Object obj = this.f15738c;
        switch (i10) {
            case 0:
                Logger logger = xb.d0.f22723a;
                xb.d dVar = new xb.d(new FileInputStream((File) obj), xb.q0.f22791d, 1);
                try {
                    kVar.z0(dVar);
                    dVar.close();
                    return;
                } finally {
                }
            case 1:
                kVar.U((xb.m) obj);
                return;
            default:
                ((n0) obj).c(kVar);
                return;
        }
    }

    public l0(n0 n0Var, d0 d0Var) {
        this.f15736a = 2;
        this.f15738c = n0Var;
        this.f15737b = d0Var;
    }
}
