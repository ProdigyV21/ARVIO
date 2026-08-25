package mb;

import gb.a0;
import gb.b0;
import gb.c0;
import gb.d0;
import gb.j0;
import gb.k0;
import gb.n0;
import gb.o0;
import gb.p0;
import gb.q;
import gb.r;
import gb.r0;
import gb.s0;
import gb.z;
import java.util.List;
import org.jsoup.helper.HttpConnection;
import xb.i0;
import xb.x;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r f20357a;

    public a(r rVar) {
        this.f20357a = rVar;
    }

    @Override // gb.c0
    public final p0 intercept(c0.a aVar) {
        boolean z;
        s0 s0Var;
        f fVar = (f) aVar;
        k0 k0Var = fVar.f20366e;
        j0 j0VarB = k0Var.b();
        b0 b0Var = k0Var.f15730a;
        a0 a0Var = k0Var.f15732c;
        n0 n0Var = k0Var.f15733d;
        if (n0Var != null) {
            d0 d0VarB = n0Var.b();
            if (d0VarB != null) {
                j0VarB.f15724c.h(HttpConnection.CONTENT_TYPE, d0VarB.f15635a);
            }
            long jA = n0Var.a();
            if (jA != -1) {
                j0VarB.f15724c.h("Content-Length", String.valueOf(jA));
                j0VarB.g("Transfer-Encoding");
            } else {
                j0VarB.f15724c.h("Transfer-Encoding", "chunked");
                j0VarB.g("Content-Length");
            }
        }
        int i10 = 0;
        if (a0Var.a("Host") == null) {
            j0VarB.f15724c.h("Host", ib.c.w(b0Var, false));
        }
        if (a0Var.a("Connection") == null) {
            j0VarB.f15724c.h("Connection", "Keep-Alive");
        }
        if (a0Var.a("Accept-Encoding") == null && a0Var.a("Range") == null) {
            j0VarB.f15724c.h("Accept-Encoding", "gzip");
            z = true;
        } else {
            z = false;
        }
        r rVar = this.f20357a;
        List listLoadForRequest = rVar.loadForRequest(b0Var);
        if (!listLoadForRequest.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            for (Object obj : listLoadForRequest) {
                int i11 = i10 + 1;
                if (i10 < 0) {
                    t7.a.Q();
                    throw null;
                }
                q qVar = (q) obj;
                if (i10 > 0) {
                    sb2.append("; ");
                }
                sb2.append(qVar.f15796a);
                sb2.append('=');
                sb2.append(qVar.f15797b);
                i10 = i11;
            }
            j0VarB.f15724c.h("Cookie", sb2.toString());
        }
        if (a0Var.a("User-Agent") == null) {
            j0VarB.f15724c.h("User-Agent", "okhttp/4.12.0");
        }
        p0 p0VarA = fVar.a(j0VarB.b());
        a0 a0Var2 = p0VarA.f15785p;
        e.b(rVar, b0Var, a0Var2);
        o0 o0VarL = p0VarA.l();
        o0VarL.f15762a = k0Var;
        if (z) {
            String strA = a0Var2.a(HttpConnection.CONTENT_ENCODING);
            if (strA == null) {
                strA = null;
            }
            if ("gzip".equalsIgnoreCase(strA) && e.a(p0VarA) && (s0Var = p0VarA.f15786q) != null) {
                x xVar = new x(s0Var.o());
                z zVarC = a0Var2.c();
                zVarC.g(HttpConnection.CONTENT_ENCODING);
                zVarC.g("Content-Length");
                o0VarL.f15767f = zVarC.e().c();
                String strA2 = a0Var2.a(HttpConnection.CONTENT_TYPE);
                o0VarL.f15768g = new r0(strA2 == null ? null : strA2, -1L, new i0(xVar), 1);
            }
        }
        return o0VarL.a();
    }
}
