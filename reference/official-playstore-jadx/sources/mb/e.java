package mb;

import gb.p0;
import kotlin.jvm.internal.p;
import xb.m;

/* JADX INFO: loaded from: classes5.dex */
public abstract class e {
    static {
        new m("\"\\".getBytes(kotlin.text.a.f19924a)).f22775m = "\"\\";
        new m("\t ,=".getBytes(kotlin.text.a.f19924a)).f22775m = "\t ,=";
    }

    public static final boolean a(p0 p0Var) {
        if (p.a(p0Var.f15780i.f15731b, "HEAD")) {
            return false;
        }
        int i10 = p0Var.f15783n;
        if (((i10 < 100 || i10 >= 200) && i10 != 204 && i10 != 304) || ib.c.k(p0Var) != -1) {
            return true;
        }
        String strA = p0Var.f15785p.a("Transfer-Encoding");
        if (strA == null) {
            strA = null;
        }
        return "chunked".equalsIgnoreCase(strA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x016d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void b(gb.r r36, gb.b0 r37, gb.a0 r38) {
        /*
            Method dump skipped, instruction units count: 477
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: mb.e.b(gb.r, gb.b0, gb.a0):void");
    }
}
