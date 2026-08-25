package gb;

import java.util.List;
import java.util.regex.Pattern;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes5.dex */
public final class f0 extends n0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d0 f15653e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final d0 f15654f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final byte[] f15655g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final byte[] f15656h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final byte[] f15657i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final xb.m f15658a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f15659b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d0 f15660c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f15661d;

    static {
        Pattern pattern = d0.f15633d;
        f15653e = xc.d.i0("multipart/mixed");
        xc.d.i0("multipart/alternative");
        xc.d.i0("multipart/digest");
        xc.d.i0("multipart/parallel");
        f15654f = xc.d.i0(HttpConnection.MULTIPART_FORM_DATA);
        f15655g = new byte[]{58, 32};
        f15656h = new byte[]{13, 10};
        f15657i = new byte[]{45, 45};
    }

    public f0(xb.m mVar, d0 d0Var, List list) {
        this.f15658a = mVar;
        this.f15659b = list;
        Pattern pattern = d0.f15633d;
        this.f15660c = xc.d.i0(d0Var + "; boundary=" + mVar.t());
        this.f15661d = -1L;
    }

    @Override // gb.n0
    public final long a() {
        long j10 = this.f15661d;
        if (j10 != -1) {
            return j10;
        }
        long jD = d(null, true);
        this.f15661d = jD;
        return jD;
    }

    @Override // gb.n0
    public final d0 b() {
        return this.f15660c;
    }

    @Override // gb.n0
    public final void c(xb.k kVar) {
        d(kVar, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long d(xb.k kVar, boolean z) {
        xb.j jVar;
        xb.k jVar2;
        if (z) {
            jVar2 = new xb.j();
            jVar = jVar2;
        } else {
            jVar = 0;
            jVar2 = kVar;
        }
        List list = this.f15659b;
        int size = list.size();
        long j10 = 0;
        int i10 = 0;
        while (true) {
            xb.m mVar = this.f15658a;
            byte[] bArr = f15657i;
            byte[] bArr2 = f15656h;
            if (i10 >= size) {
                jVar2.write(bArr);
                jVar2.U(mVar);
                jVar2.write(bArr);
                jVar2.write(bArr2);
                if (!z) {
                    return j10;
                }
                long j11 = j10 + jVar.f22759l;
                jVar.i();
                return j11;
            }
            e0 e0Var = (e0) list.get(i10);
            a0 a0Var = e0Var.f15649a;
            n0 n0Var = e0Var.f15650b;
            jVar2.write(bArr);
            jVar2.U(mVar);
            jVar2.write(bArr2);
            int size2 = a0Var.size();
            for (int i11 = 0; i11 < size2; i11++) {
                jVar2.H(a0Var.b(i11)).write(f15655g).H(a0Var.e(i11)).write(bArr2);
            }
            d0 d0VarB = n0Var.b();
            if (d0VarB != null) {
                jVar2.H("Content-Type: ").H(d0VarB.f15635a).write(bArr2);
            }
            long jA = n0Var.a();
            if (jA != -1) {
                jVar2.H("Content-Length: ").Y(jA).write(bArr2);
            } else if (z) {
                jVar.i();
                return -1L;
            }
            jVar2.write(bArr2);
            if (z) {
                j10 += jA;
            } else {
                n0Var.c(jVar2);
            }
            jVar2.write(bArr2);
            i10++;
        }
    }
}
