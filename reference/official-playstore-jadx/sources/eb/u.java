package eb;

import cb.f1;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.json.internal.JsonEncodingException;

/* JADX INFO: loaded from: classes5.dex */
public class u implements db.s, bb.e, bb.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f15176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final db.b f15177b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final r7.l f15178c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final db.i f15179d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f15180e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f15181f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f15182g;

    public u(db.b bVar, r7.l lVar, char c10) {
        this.f15176a = new ArrayList();
        this.f15177b = bVar;
        this.f15178c = lVar;
        this.f15179d = bVar.f14878a;
    }

    @Override // bb.e
    public final void A(int i10) {
        L(db.n.b(Integer.valueOf(i10)), (String) K());
    }

    @Override // bb.c
    public final void B(ab.g gVar, int i10, float f10) {
        H(J(gVar, i10), f10);
    }

    @Override // bb.c
    public final boolean C() {
        return this.f15179d.f14901a;
    }

    @Override // bb.c
    public final void D(f1 f1Var, int i10, short s10) {
        L(db.n.b(Short.valueOf(s10)), J(f1Var, i10));
    }

    @Override // bb.e
    public final void E(String str) {
        L(db.n.c(str), (String) K());
    }

    public final void F(ab.g gVar, int i10, ya.s sVar, Object obj) {
        this.f15176a.add(J(gVar, i10));
        if (sVar.getDescriptor().b()) {
            z(sVar, obj);
        } else if (obj == null) {
            q();
        } else {
            z(sVar, obj);
        }
    }

    public final void G(Object obj, double d4) {
        String str = (String) obj;
        L(db.n.b(Double.valueOf(d4)), str);
        if (this.f15179d.f14908h) {
            return;
        }
        if (Double.isInfinite(d4) || Double.isNaN(d4)) {
            throw new JsonEncodingException(r.p(Double.valueOf(d4), str, I().toString()));
        }
    }

    public final void H(Object obj, float f10) {
        String str = (String) obj;
        L(db.n.b(Float.valueOf(f10)), str);
        if (this.f15179d.f14908h) {
            return;
        }
        if (Float.isInfinite(f10) || Float.isNaN(f10)) {
            throw new JsonEncodingException(r.p(Float.valueOf(f10), str, I().toString()));
        }
    }

    public db.m I() {
        switch (this.f15181f) {
            case 0:
                db.m mVar = (db.m) this.f15182g;
                if (mVar != null) {
                    return mVar;
                }
                throw new IllegalArgumentException("Primitive element has not been recorded. Is call to .encodeXxx is missing in serializer?");
            case 1:
                return new db.b0((LinkedHashMap) this.f15182g);
            default:
                return new db.d((ArrayList) this.f15182g);
        }
    }

    public final String J(ab.g gVar, int i10) {
        String strValueOf;
        switch (this.f15181f) {
            case 2:
                strValueOf = String.valueOf(i10);
                break;
            default:
                r.n(gVar, this.f15177b);
                strValueOf = gVar.e(i10);
                break;
        }
        return strValueOf;
    }

    public final Object K() {
        ArrayList arrayList = this.f15176a;
        if (arrayList.isEmpty()) {
            throw new SerializationException("No tag in stack for requested element");
        }
        return arrayList.remove(t7.a.w(arrayList));
    }

    public void L(db.m mVar, String str) {
        switch (this.f15181f) {
            case 0:
                if (str != "primitive") {
                    throw new IllegalArgumentException("This output can only consume primitives with 'primitive' tag");
                }
                if (((db.m) this.f15182g) != null) {
                    throw new IllegalArgumentException("Primitive element was already recorded. Does call to .encodeXxx happen more than once?");
                }
                this.f15182g = mVar;
                this.f15178c.invoke(mVar);
                return;
            case 1:
                ((LinkedHashMap) this.f15182g).put(str, mVar);
                return;
            default:
                ((ArrayList) this.f15182g).add(Integer.parseInt(str), mVar);
                return;
        }
    }

    @Override // bb.e
    public final fb.b a() {
        return this.f15177b.f14879b;
    }

    @Override // bb.e
    public final bb.c b(ab.g gVar) {
        u uVar;
        r7.l hVar = kotlin.collections.x.y0(this.f15176a) == null ? this.f15178c : new ab.h(this, 12);
        a.a kind = gVar.getKind();
        boolean z = kotlin.jvm.internal.p.a(kind, ab.n.f819c) ? true : kind instanceof ab.d;
        db.b bVar = this.f15177b;
        if (z) {
            uVar = new u(bVar, hVar, 2);
        } else if (kotlin.jvm.internal.p.a(kind, ab.n.f820d)) {
            ab.g gVarF = r.f(gVar.g(0), bVar.f14879b);
            a.a kind2 = gVarF.getKind();
            if ((kind2 instanceof ab.f) || kotlin.jvm.internal.p.a(kind2, ab.m.f817b)) {
                z zVar = new z(bVar, hVar, 1);
                zVar.f15197i = true;
                uVar = zVar;
            } else {
                if (!bVar.f14878a.f14904d) {
                    throw r.b(gVarF);
                }
                uVar = new u(bVar, hVar, 2);
            }
        } else {
            uVar = new u(bVar, hVar, 1);
        }
        String str = this.f15180e;
        if (str != null) {
            uVar.L(db.n.c(gVar.h()), str);
            this.f15180e = null;
        }
        return uVar;
    }

    @Override // bb.c
    public final bb.e c(f1 f1Var, int i10) {
        String strJ = J(f1Var, i10);
        ab.g gVarG = f1Var.g(i10);
        if (h0.a(gVarG)) {
            return new c(this, strJ);
        }
        if (gVarG.isInline() && gVarG.equals(db.n.f14910a)) {
            return new c(this, strJ, gVarG);
        }
        this.f15176a.add(strJ);
        return this;
    }

    @Override // bb.c
    public final void d(ab.g gVar, int i10, boolean z) {
        L(db.n.a(Boolean.valueOf(z)), J(gVar, i10));
    }

    @Override // bb.c
    public final void e() {
        if (!this.f15176a.isEmpty()) {
            K();
        }
        this.f15178c.invoke(I());
    }

    @Override // bb.e
    public final void f(double d4) {
        G(K(), d4);
    }

    @Override // bb.c
    public final void g(ab.g gVar, int i10, long j10) {
        L(db.n.b(Long.valueOf(j10)), J(gVar, i10));
    }

    @Override // bb.e
    public final void h(byte b10) {
        L(db.n.b(Byte.valueOf(b10)), (String) K());
    }

    @Override // bb.e
    public final void i(ab.g gVar, int i10) {
        L(db.n.c(gVar.e(i10)), (String) K());
    }

    @Override // bb.c
    public final void j(ab.g gVar, int i10, ya.s sVar, Object obj) {
        this.f15176a.add(J(gVar, i10));
        z(sVar, obj);
    }

    @Override // bb.e
    public final bb.e k(ab.g gVar) {
        ArrayList arrayList = this.f15176a;
        if (kotlin.collections.x.y0(arrayList) == null) {
            return new u(this.f15177b, this.f15178c, 0).k(gVar);
        }
        String str = (String) K();
        if (h0.a(gVar)) {
            return new c(this, str);
        }
        if (gVar.isInline() && gVar.equals(db.n.f14910a)) {
            return new c(this, str, gVar);
        }
        arrayList.add(str);
        return this;
    }

    @Override // db.s
    public final void l(db.b0 b0Var) {
        z(db.q.f14921a, b0Var);
    }

    @Override // bb.c
    public void m(ab.g gVar, int i10, ya.s sVar, Object obj) {
        switch (this.f15181f) {
            case 1:
                if (obj != null || this.f15179d.f14905e) {
                    F(gVar, i10, sVar, obj);
                }
                break;
            default:
                F(gVar, i10, sVar, obj);
                break;
        }
    }

    @Override // bb.c
    public final void n(f1 f1Var, int i10, byte b10) {
        L(db.n.b(Byte.valueOf(b10)), J(f1Var, i10));
    }

    @Override // bb.c
    public final void o(f1 f1Var, int i10, char c10) {
        L(db.n.c(String.valueOf(c10)), J(f1Var, i10));
    }

    @Override // bb.e
    public final void p(long j10) {
        L(db.n.b(Long.valueOf(j10)), (String) K());
    }

    @Override // bb.e
    public final void q() {
        String str = (String) kotlin.collections.x.y0(this.f15176a);
        if (str == null) {
            this.f15178c.invoke(db.y.INSTANCE);
        } else {
            L(db.y.INSTANCE, str);
        }
    }

    @Override // bb.c
    public final void r(int i10, int i11, ab.g gVar) {
        L(db.n.b(Integer.valueOf(i11)), J(gVar, i10));
    }

    @Override // bb.e
    public final void s(short s10) {
        L(db.n.b(Short.valueOf(s10)), (String) K());
    }

    @Override // bb.e
    public final void t(boolean z) {
        L(db.n.a(Boolean.valueOf(z)), (String) K());
    }

    @Override // bb.c
    public final void u(f1 f1Var, int i10, double d4) {
        G(J(f1Var, i10), d4);
    }

    @Override // bb.e
    public final bb.c v(ab.g gVar) {
        return b(gVar);
    }

    @Override // bb.e
    public final void w(float f10) {
        H(K(), f10);
    }

    @Override // bb.c
    public final void x(ab.g gVar, int i10, String str) {
        L(db.n.c(str), J(gVar, i10));
    }

    @Override // bb.e
    public final void y(char c10) {
        L(db.n.c(String.valueOf(c10)), (String) K());
    }

    @Override // bb.e
    public final void z(ya.s sVar, Object obj) {
        Object objY0 = kotlin.collections.x.y0(this.f15176a);
        db.b bVar = this.f15177b;
        if (objY0 == null) {
            ab.g gVarF = r.f(sVar.getDescriptor(), bVar.f14879b);
            if ((gVarF.getKind() instanceof ab.f) || gVarF.getKind() == ab.m.f817b) {
                new u(bVar, this.f15178c, 0).z(sVar, obj);
                return;
            }
        }
        if (!(sVar instanceof cb.b)) {
            sVar.serialize(this, obj);
            return;
        }
        db.i iVar = bVar.f14878a;
        cb.b bVar2 = (cb.b) sVar;
        String strI = r.i(sVar.getDescriptor(), bVar);
        ya.s sVarK = k2.c.k(bVar2, this, obj);
        r.e(bVar2, sVarK, strI);
        r.h(sVarK.getDescriptor().getKind());
        this.f15180e = strI;
        sVarK.serialize(this, obj);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public u(db.b bVar, r7.l lVar, int i10) {
        this(bVar, lVar, (char) 0);
        this.f15181f = i10;
        switch (i10) {
            case 1:
                this(bVar, lVar, (char) 0);
                this.f15182g = new LinkedHashMap();
                break;
            case 2:
                this(bVar, lVar, (char) 0);
                this.f15182g = new ArrayList();
                break;
            default:
                this.f15176a.add("primitive");
                break;
        }
    }
}
