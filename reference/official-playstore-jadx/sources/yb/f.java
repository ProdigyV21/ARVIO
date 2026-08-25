package yb;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import kotlin.jvm.internal.p;
import xb.f0;
import xb.m;

/* JADX INFO: loaded from: classes5.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m f23028a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m f23029b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final m f23030c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final m f23031d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final m f23032e;

    static {
        m mVar = new m(DomExceptionUtils.SEPARATOR.getBytes(kotlin.text.a.f19924a));
        mVar.f22775m = DomExceptionUtils.SEPARATOR;
        f23028a = mVar;
        m mVar2 = new m("\\".getBytes(kotlin.text.a.f19924a));
        mVar2.f22775m = "\\";
        f23029b = mVar2;
        m mVar3 = new m("/\\".getBytes(kotlin.text.a.f19924a));
        mVar3.f22775m = "/\\";
        f23030c = mVar3;
        m mVar4 = new m(".".getBytes(kotlin.text.a.f19924a));
        mVar4.f22775m = ".";
        f23031d = mVar4;
        m mVar5 = new m("..".getBytes(kotlin.text.a.f19924a));
        mVar5.f22775m = "..";
        f23032e = mVar5;
    }

    public static final int a(f0 f0Var) {
        m mVar = f0Var.f22734i;
        if (mVar.f() != 0) {
            if (mVar.m(0) != 47) {
                if (mVar.m(0) == 92) {
                    if (mVar.f() > 2 && mVar.m(1) == 92) {
                        int iJ = mVar.j(2, f23029b.l());
                        return iJ == -1 ? mVar.f() : iJ;
                    }
                } else if (mVar.f() > 2 && mVar.m(1) == 58 && mVar.m(2) == 92) {
                    char cM = (char) mVar.m(0);
                    if ('a' <= cM && cM < '{') {
                        return 3;
                    }
                    if ('A' <= cM && cM < '[') {
                        return 3;
                    }
                }
            }
            return 1;
        }
        return -1;
    }

    public static final f0 b(f0 f0Var, f0 f0Var2, boolean z) {
        if (a(f0Var2) != -1 || f0Var2.k() != null) {
            return f0Var2;
        }
        m mVarC = c(f0Var);
        if (mVarC == null && (mVarC = c(f0Var2)) == null) {
            mVarC = f(f0.f22733l);
        }
        xb.j jVar = new xb.j();
        jVar.J0(f0Var.f22734i);
        if (jVar.f22759l > 0) {
            jVar.J0(mVarC);
        }
        jVar.J0(f0Var2.f22734i);
        return d(jVar, z);
    }

    public static final m c(f0 f0Var) {
        m mVar = f0Var.f22734i;
        m mVar2 = f23028a;
        if (m.k(mVar, mVar2) != -1) {
            return mVar2;
        }
        m mVar3 = f0Var.f22734i;
        m mVar4 = f23029b;
        if (m.k(mVar3, mVar4) != -1) {
            return mVar4;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0119 A[EDGE_INSN: B:101:0x0119->B:84:0x0119 BREAK  A[LOOP:1: B:53:0x00a9->B:116:0x00a9], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final xb.f0 d(xb.j r17, boolean r18) throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: yb.f.d(xb.j, boolean):xb.f0");
    }

    public static final m e(byte b10) {
        if (b10 == 47) {
            return f23028a;
        }
        if (b10 == 92) {
            return f23029b;
        }
        throw new IllegalArgumentException(a0.c.i(b10, "not a directory separator: "));
    }

    public static final m f(String str) {
        if (p.a(str, DomExceptionUtils.SEPARATOR)) {
            return f23028a;
        }
        if (p.a(str, "\\")) {
            return f23029b;
        }
        throw new IllegalArgumentException(androidx.compose.material3.d.C("not a directory separator: ", str));
    }
}
