package yb;

import java.io.IOException;
import xb.o0;
import xb.v;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends v {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f23035i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f23036l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f23037m;

    public h(o0 o0Var, long j10, boolean z) {
        super(o0Var);
        this.f23035i = j10;
        this.f23036l = z;
    }

    @Override // xb.v, xb.o0
    public final long read(xb.j jVar, long j10) throws IOException {
        long j11 = this.f23037m;
        long j12 = this.f23035i;
        if (j11 > j12) {
            j10 = 0;
        } else if (this.f23036l) {
            long j13 = j12 - j11;
            if (j13 == 0) {
                return -1L;
            }
            j10 = Math.min(j10, j13);
        }
        long j14 = super.read(jVar, j10);
        if (j14 != -1) {
            this.f23037m += j14;
        }
        long j15 = this.f23037m;
        if ((j15 >= j12 || j14 != -1) && j15 <= j12) {
            return j14;
        }
        if (j14 > 0 && j15 > j12) {
            long j16 = jVar.f22759l - (j15 - j12);
            xb.j jVar2 = new xb.j();
            jVar2.z0(jVar);
            jVar.write(jVar2, j16);
            jVar2.i();
        }
        StringBuilder sbR = androidx.compose.material3.d.r(j12, "expected ", " bytes but got ");
        sbR.append(this.f23037m);
        throw new IOException(sbR.toString());
    }
}
