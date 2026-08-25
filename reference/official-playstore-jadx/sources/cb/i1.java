package cb;

import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public abstract class i1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f7517a;

    static {
        kotlin.jvm.internal.m0 m0Var = kotlin.jvm.internal.l0.f19747a;
        x6.x xVar = new x6.x(m0Var.b(String.class), r1.f7553a);
        x6.x xVar2 = new x6.x(m0Var.b(Character.TYPE), p.f7541a);
        x6.x xVar3 = new x6.x(m0Var.b(char[].class), o.f7536c);
        x6.x xVar4 = new x6.x(m0Var.b(Double.TYPE), w.f7577a);
        x6.x xVar5 = new x6.x(m0Var.b(double[].class), v.f7573c);
        x6.x xVar6 = new x6.x(m0Var.b(Float.TYPE), d0.f7475a);
        x6.x xVar7 = new x6.x(m0Var.b(float[].class), c0.f7469c);
        x6.x xVar8 = new x6.x(m0Var.b(Long.TYPE), q0.f7547a);
        x6.x xVar9 = new x6.x(m0Var.b(long[].class), p0.f7543c);
        x6.x xVar10 = new x6.x(m0Var.b(x6.o0.class), d2.f7487a);
        x6.x xVar11 = new x6.x(m0Var.b(x6.p0.class), c2.f7472c);
        x6.x xVar12 = new x6.x(m0Var.b(Integer.TYPE), l0.f7529a);
        x6.x xVar13 = new x6.x(m0Var.b(int[].class), k0.f7525c);
        x6.x xVar14 = new x6.x(m0Var.b(x6.m0.class), a2.f7459a);
        x6.x xVar15 = new x6.x(m0Var.b(x6.n0.class), z1.f7599c);
        x6.x xVar16 = new x6.x(m0Var.b(Short.TYPE), q1.f7549a);
        x6.x xVar17 = new x6.x(m0Var.b(short[].class), p1.f7544c);
        x6.x xVar18 = new x6.x(m0Var.b(x6.r0.class), g2.f7506a);
        x6.x xVar19 = new x6.x(m0Var.b(x6.s0.class), f2.f7498c);
        x6.x xVar20 = new x6.x(m0Var.b(Byte.TYPE), j.f7518a);
        x6.x xVar21 = new x6.x(m0Var.b(byte[].class), i.f7515c);
        x6.x xVar22 = new x6.x(m0Var.b(x6.k0.class), x1.f7586a);
        x6.x xVar23 = new x6.x(m0Var.b(x6.l0.class), w1.f7581c);
        x6.x xVar24 = new x6.x(m0Var.b(Boolean.TYPE), g.f7499a);
        x6.x xVar25 = new x6.x(m0Var.b(boolean[].class), f.f7493c);
        x6.x xVar26 = new x6.x(m0Var.b(x6.t0.class), h2.f7513b);
        x6.x xVar27 = new x6.x(m0Var.b(Void.class), w0.f7579a);
        kotlin.reflect.d dVarB = m0Var.b(kotlin.time.c.class);
        io.sentry.util.l lVar = kotlin.time.c.f19956l;
        f7517a = kotlin.collections.h0.t0(xVar, xVar2, xVar3, xVar4, xVar5, xVar6, xVar7, xVar8, xVar9, xVar10, xVar11, xVar12, xVar13, xVar14, xVar15, xVar16, xVar17, xVar18, xVar19, xVar20, xVar21, xVar22, xVar23, xVar24, xVar25, xVar26, xVar27, new x6.x(dVarB, x.f7582a));
    }

    public static final String a(String str) {
        String strValueOf;
        if (str.length() <= 0) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder();
        char cCharAt = str.charAt(0);
        if (Character.isLowerCase(cCharAt)) {
            String strValueOf2 = String.valueOf(cCharAt);
            Locale locale = Locale.ROOT;
            strValueOf = strValueOf2.toUpperCase(locale);
            if (strValueOf.length() <= 1) {
                strValueOf = String.valueOf(Character.toTitleCase(cCharAt));
            } else if (cCharAt != 329) {
                strValueOf = strValueOf.charAt(0) + strValueOf.substring(1).toLowerCase(locale);
            }
        } else {
            strValueOf = String.valueOf(cCharAt);
        }
        sb2.append((Object) strValueOf);
        sb2.append(str.substring(1));
        return sb2.toString();
    }
}
