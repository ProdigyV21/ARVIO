package ob;

import java.io.IOException;
import kotlin.jvm.internal.k0;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends kb.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f20899e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f20900f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f20901g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(int i10, Object obj, Object obj2, String str) {
        super(str, true);
        this.f20899e = i10;
        this.f20900f = obj;
        this.f20901g = obj2;
    }

    @Override // kb.a
    public final long a() {
        int i10;
        long jA;
        x[] xVarArr;
        switch (this.f20899e) {
            case 0:
                ((o) this.f20900f).f20922i.a((b0) ((k0) this.f20901g).f19746i);
                return -1L;
            case 1:
                try {
                    ((o) this.f20900f).f20922i.b((x) this.f20901g);
                    break;
                } catch (IOException e5) {
                    qb.n nVar = qb.n.f21385a;
                    qb.n nVar2 = qb.n.f21385a;
                    String str = "Http2Connection.Listener failure for " + ((o) this.f20900f).f20924m;
                    nVar2.getClass();
                    qb.n.i(str, e5, 4);
                    try {
                        ((x) this.f20901g).c(e5, 2);
                        break;
                    } catch (IOException unused) {
                    }
                }
                return -1L;
            default:
                j8.h hVar = (j8.h) this.f20900f;
                b0 b0Var = (b0) this.f20901g;
                k0 k0Var = new k0();
                o oVar = (o) hVar.f19282m;
                synchronized (oVar.H) {
                    synchronized (oVar) {
                        try {
                            b0 b0Var2 = oVar.B;
                            b0 b0Var3 = new b0();
                            i10 = 0;
                            for (int i11 = 0; i11 < 10; i11++) {
                                if (((1 << i11) & b0Var2.f20882a) != 0) {
                                    b0Var3.b(i11, b0Var2.f20883b[i11]);
                                }
                            }
                            for (int i12 = 0; i12 < 10; i12++) {
                                if (((1 << i12) & b0Var.f20882a) != 0) {
                                    b0Var3.b(i12, b0Var.f20883b[i12]);
                                }
                            }
                            k0Var.f19746i = b0Var3;
                            jA = ((long) b0Var3.a()) - ((long) b0Var2.a());
                            xVarArr = (jA == 0 || oVar.f20923l.isEmpty()) ? null : (x[]) oVar.f20923l.values().toArray(new x[0]);
                            oVar.B = (b0) k0Var.f19746i;
                            oVar.f20931t.c(new h(i10, oVar, k0Var, oVar.f20924m + " onSettings"), 0L);
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    try {
                        oVar.H.i((b0) k0Var.f19746i);
                    } catch (IOException e6) {
                        oVar.i(2, 2, e6);
                    }
                    break;
                }
                if (xVarArr != null) {
                    int length = xVarArr.length;
                    while (i10 < length) {
                        x xVar = xVarArr[i10];
                        synchronized (xVar) {
                            xVar.f20970f += jA;
                            if (jA > 0) {
                                xVar.notifyAll();
                            }
                        }
                        i10++;
                    }
                }
                return -1L;
        }
    }
}
