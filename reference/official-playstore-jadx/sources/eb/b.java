package eb;

import cb.f1;
import cb.t1;
import java.util.ArrayList;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b implements db.j, bb.d, bb.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f15107a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f15108b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final db.b f15109c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final db.i f15110d;

    public b(db.b bVar) {
        this.f15109c = bVar;
        this.f15110d = bVar.f14878a;
    }

    public static db.u F(db.g0 g0Var, String str) {
        db.u uVar = g0Var instanceof db.u ? (db.u) g0Var : null;
        if (uVar != null) {
            return uVar;
        }
        throw r.c(-1, "Unexpected 'null' when " + str + " was expected");
    }

    @Override // bb.b
    public final double A(f1 f1Var, int i10) {
        return K(R(f1Var, i10));
    }

    @Override // bb.b
    public final short B(f1 f1Var, int i10) {
        return N(R(f1Var, i10));
    }

    @Override // db.j
    public final db.b C() {
        return this.f15109c;
    }

    @Override // bb.d
    public final byte D() {
        return J(T());
    }

    @Override // bb.b
    public final byte E(f1 f1Var, int i10) {
        return J(R(f1Var, i10));
    }

    public abstract db.m G(String str);

    public final db.m H() {
        db.m mVarG;
        String str = (String) kotlin.collections.x.y0(this.f15107a);
        return (str == null || (mVarG = G(str)) == null) ? S() : mVarG;
    }

    public final boolean I(Object obj) {
        String str = (String) obj;
        db.g0 g0VarQ = Q(str);
        if (!this.f15109c.f14878a.f14903c && F(g0VarQ, "boolean").f14924i) {
            throw r.d(a0.c.l("Boolean literal for key '", str, "' should be unquoted.\nUse 'isLenient = true' in 'Json {}` builder to accept non-compliant JSON."), H().toString(), -1);
        }
        try {
            cb.h0 h0Var = db.n.f14910a;
            String strB = g0VarQ.b();
            String[] strArr = j0.f15157a;
            Boolean bool = strB.equalsIgnoreCase("true") ? Boolean.TRUE : strB.equalsIgnoreCase("false") ? Boolean.FALSE : null;
            if (bool != null) {
                return bool.booleanValue();
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException unused) {
            U("boolean");
            throw null;
        }
    }

    public final byte J(Object obj) {
        db.g0 g0VarQ = Q((String) obj);
        try {
            cb.h0 h0Var = db.n.f14910a;
            int i10 = Integer.parseInt(g0VarQ.b());
            Byte bValueOf = (-128 > i10 || i10 > 127) ? null : Byte.valueOf((byte) i10);
            if (bValueOf != null) {
                return bValueOf.byteValue();
            }
            U("byte");
            throw null;
        } catch (IllegalArgumentException unused) {
            U("byte");
            throw null;
        }
    }

    public final double K(Object obj) {
        String str = (String) obj;
        db.g0 g0VarQ = Q(str);
        try {
            cb.h0 h0Var = db.n.f14910a;
            double d4 = Double.parseDouble(g0VarQ.b());
            if (this.f15109c.f14878a.f14908h || !(Double.isInfinite(d4) || Double.isNaN(d4))) {
                return d4;
            }
            throw r.c(-1, r.p(Double.valueOf(d4), str, H().toString()));
        } catch (IllegalArgumentException unused) {
            U("double");
            throw null;
        }
    }

    public final float L(Object obj) {
        String str = (String) obj;
        db.g0 g0VarQ = Q(str);
        try {
            cb.h0 h0Var = db.n.f14910a;
            float f10 = Float.parseFloat(g0VarQ.b());
            if (this.f15109c.f14878a.f14908h || !(Float.isInfinite(f10) || Float.isNaN(f10))) {
                return f10;
            }
            throw r.c(-1, r.p(Float.valueOf(f10), str, H().toString()));
        } catch (IllegalArgumentException unused) {
            U("float");
            throw null;
        }
    }

    public final bb.d M(Object obj, ab.g gVar) {
        String str = (String) obj;
        if (h0.a(gVar)) {
            return new m(new i0(Q(str).b()), this.f15109c);
        }
        this.f15107a.add(str);
        return this;
    }

    public final short N(Object obj) {
        db.g0 g0VarQ = Q((String) obj);
        try {
            cb.h0 h0Var = db.n.f14910a;
            int i10 = Integer.parseInt(g0VarQ.b());
            Short shValueOf = (-32768 > i10 || i10 > 32767) ? null : Short.valueOf((short) i10);
            if (shValueOf != null) {
                return shValueOf.shortValue();
            }
            U("short");
            throw null;
        } catch (IllegalArgumentException unused) {
            U("short");
            throw null;
        }
    }

    public final String O(Object obj) {
        String str = (String) obj;
        db.g0 g0VarQ = Q(str);
        if (!this.f15109c.f14878a.f14903c && !F(g0VarQ, "string").f14924i) {
            throw r.d(a0.c.l("String literal for key '", str, "' should be quoted.\nUse 'isLenient = true' in 'Json {}` builder to accept non-compliant JSON."), H().toString(), -1);
        }
        if (g0VarQ instanceof db.y) {
            throw r.d("Unexpected 'null' value instead of string literal", H().toString(), -1);
        }
        return g0VarQ.b();
    }

    public String P(ab.g gVar, int i10) {
        return gVar.e(i10);
    }

    public final db.g0 Q(String str) {
        db.m mVarG = G(str);
        db.g0 g0Var = mVarG instanceof db.g0 ? (db.g0) mVarG : null;
        if (g0Var != null) {
            return g0Var;
        }
        throw r.d("Expected JsonPrimitive at " + str + ", found " + mVarG, H().toString(), -1);
    }

    public final String R(ab.g gVar, int i10) {
        String strP = P(gVar, i10);
        return strP;
    }

    public abstract db.m S();

    public final Object T() {
        ArrayList arrayList = this.f15107a;
        Object objRemove = arrayList.remove(t7.a.w(arrayList));
        this.f15108b = true;
        return objRemove;
    }

    public final void U(String str) {
        throw r.d(androidx.compose.material3.d.i('\'', "Failed to parse '", str), H().toString(), -1);
    }

    @Override // bb.b
    public final fb.b a() {
        return this.f15109c.f14879b;
    }

    @Override // bb.d
    public bb.b b(ab.g gVar) {
        db.m mVarH = H();
        a.a kind = gVar.getKind();
        boolean z = kotlin.jvm.internal.p.a(kind, ab.n.f819c) ? true : kind instanceof ab.d;
        db.b bVar = this.f15109c;
        if (z) {
            if (mVarH instanceof db.d) {
                return new x(bVar, (db.d) mVarH);
            }
            StringBuilder sb2 = new StringBuilder("Expected ");
            m0 m0Var = l0.f19747a;
            sb2.append(m0Var.b(db.d.class));
            sb2.append(" as the serialized body of ");
            sb2.append(gVar.h());
            sb2.append(", but had ");
            sb2.append(m0Var.b(mVarH.getClass()));
            throw r.c(-1, sb2.toString());
        }
        if (!kotlin.jvm.internal.p.a(kind, ab.n.f820d)) {
            if (mVarH instanceof db.b0) {
                return new w(bVar, (db.b0) mVarH, null, null);
            }
            StringBuilder sb3 = new StringBuilder("Expected ");
            m0 m0Var2 = l0.f19747a;
            sb3.append(m0Var2.b(db.b0.class));
            sb3.append(" as the serialized body of ");
            sb3.append(gVar.h());
            sb3.append(", but had ");
            sb3.append(m0Var2.b(mVarH.getClass()));
            throw r.c(-1, sb3.toString());
        }
        ab.g gVarF = r.f(gVar.g(0), bVar.f14879b);
        a.a kind2 = gVarF.getKind();
        if ((kind2 instanceof ab.f) || kotlin.jvm.internal.p.a(kind2, ab.m.f817b)) {
            if (mVarH instanceof db.b0) {
                return new y(bVar, (db.b0) mVarH);
            }
            StringBuilder sb4 = new StringBuilder("Expected ");
            m0 m0Var3 = l0.f19747a;
            sb4.append(m0Var3.b(db.b0.class));
            sb4.append(" as the serialized body of ");
            sb4.append(gVar.h());
            sb4.append(", but had ");
            sb4.append(m0Var3.b(mVarH.getClass()));
            throw r.c(-1, sb4.toString());
        }
        if (!bVar.f14878a.f14904d) {
            throw r.b(gVarF);
        }
        if (mVarH instanceof db.d) {
            return new x(bVar, (db.d) mVarH);
        }
        StringBuilder sb5 = new StringBuilder("Expected ");
        m0 m0Var4 = l0.f19747a;
        sb5.append(m0Var4.b(db.d.class));
        sb5.append(" as the serialized body of ");
        sb5.append(gVar.h());
        sb5.append(", but had ");
        sb5.append(m0Var4.b(mVarH.getClass()));
        throw r.c(-1, sb5.toString());
    }

    @Override // bb.b
    public final int c(ab.g gVar, int i10) {
        db.g0 g0VarQ = Q(R(gVar, i10));
        try {
            cb.h0 h0Var = db.n.f14910a;
            return Integer.parseInt(g0VarQ.b());
        } catch (IllegalArgumentException unused) {
            U("int");
            throw null;
        }
    }

    @Override // bb.b
    public final Object d(ab.g gVar, int i10, ya.c cVar, Object obj) {
        String strR = R(gVar, i10);
        t1 t1Var = new t1(this, cVar, obj, 1);
        this.f15107a.add(strR);
        Object objInvoke = t1Var.invoke();
        if (!this.f15108b) {
            T();
        }
        this.f15108b = false;
        return objInvoke;
    }

    @Override // db.j
    public final db.m e() {
        return H();
    }

    @Override // bb.b
    public final String f(ab.g gVar, int i10) {
        return O(R(gVar, i10));
    }

    @Override // bb.d
    public final int g() {
        db.g0 g0VarQ = Q((String) T());
        try {
            cb.h0 h0Var = db.n.f14910a;
            return Integer.parseInt(g0VarQ.b());
        } catch (IllegalArgumentException unused) {
            U("int");
            throw null;
        }
    }

    @Override // bb.d
    public final bb.d h(ab.g gVar) {
        return kotlin.collections.x.y0(this.f15107a) != null ? M(T(), gVar) : new t(this.f15109c, S()).h(gVar);
    }

    @Override // bb.d
    public final long i() {
        db.g0 g0VarQ = Q((String) T());
        try {
            cb.h0 h0Var = db.n.f14910a;
            return Long.parseLong(g0VarQ.b());
        } catch (IllegalArgumentException unused) {
            U("long");
            throw null;
        }
    }

    @Override // bb.b
    public final Object j(ab.g gVar, int i10, ya.h hVar, Object obj) {
        String strR = R(gVar, i10);
        t1 t1Var = new t1(this, hVar, obj, 0);
        this.f15107a.add(strR);
        Object objInvoke = t1Var.invoke();
        if (!this.f15108b) {
            T();
        }
        this.f15108b = false;
        return objInvoke;
    }

    @Override // bb.b
    public final long k(ab.g gVar, int i10) {
        db.g0 g0VarQ = Q(R(gVar, i10));
        try {
            cb.h0 h0Var = db.n.f14910a;
            return Long.parseLong(g0VarQ.b());
        } catch (IllegalArgumentException unused) {
            U("long");
            throw null;
        }
    }

    @Override // bb.b
    public final float l(ab.g gVar, int i10) {
        return L(R(gVar, i10));
    }

    @Override // bb.d
    public final short m() {
        return N(T());
    }

    @Override // bb.d
    public final float n() {
        return L(T());
    }

    @Override // bb.b
    public final bb.d o(f1 f1Var, int i10) {
        return M(R(f1Var, i10), f1Var.g(i10));
    }

    @Override // bb.d
    public final double p() {
        return K(T());
    }

    @Override // bb.d
    public final Object q(ya.c cVar) {
        return r.j(this, cVar);
    }

    @Override // bb.d
    public final boolean r() {
        return I(T());
    }

    @Override // bb.d
    public final char s() {
        try {
            return kotlin.text.o.v0(Q((String) T()).b());
        } catch (IllegalArgumentException unused) {
            U("char");
            throw null;
        }
    }

    @Override // bb.b
    public final boolean t(ab.g gVar, int i10) {
        return I(R(gVar, i10));
    }

    @Override // bb.d
    public final String v() {
        return O(T());
    }

    @Override // bb.d
    public boolean x() {
        return !(H() instanceof db.y);
    }

    @Override // bb.b
    public final char y(f1 f1Var, int i10) {
        try {
            return kotlin.text.o.v0(Q(R(f1Var, i10)).b());
        } catch (IllegalArgumentException unused) {
            U("char");
            throw null;
        }
    }

    @Override // bb.d
    public final int z(ab.g gVar) {
        return r.l(gVar, this.f15109c, Q((String) T()).b(), "");
    }

    @Override // bb.b
    public void u(ab.g gVar) {
    }
}
