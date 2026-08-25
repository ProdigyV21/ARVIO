package eb;

import a8.l0;
import cb.b1;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.collections.q0;

/* JADX INFO: loaded from: classes5.dex */
public class w extends b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final db.b0 f15185e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f15186f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ab.g f15187g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f15188h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f15189i;

    public w(db.b bVar, db.b0 b0Var, String str, ab.g gVar) {
        super(bVar);
        this.f15185e = b0Var;
        this.f15186f = str;
        this.f15187g = gVar;
    }

    @Override // eb.b
    public db.m G(String str) {
        return (db.m) kotlin.collections.h0.s0(S(), str);
    }

    @Override // eb.b
    public String P(ab.g gVar, int i10) {
        Object next;
        db.b bVar = this.f15109c;
        r.n(gVar, bVar);
        String strE = gVar.e(i10);
        if (this.f15110d.f14909i && !S().f14881i.keySet().contains(strE)) {
            Map map = (Map) bVar.f14880c.K(gVar, r.f15173a, new l0(gVar, bVar, 10));
            Iterator it = S().f14881i.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                Integer num = (Integer) map.get((String) next);
                if (num != null && num.intValue() == i10) {
                    break;
                }
            }
            String str = (String) next;
            if (str != null) {
                return str;
            }
        }
        return strE;
    }

    @Override // eb.b
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public db.b0 S() {
        return this.f15185e;
    }

    @Override // eb.b, bb.d
    public final bb.b b(ab.g gVar) {
        return gVar == this.f15187g ? this : super.b(gVar);
    }

    @Override // eb.b, bb.b
    public void u(ab.g gVar) {
        Set setB0;
        db.i iVar = this.f15110d;
        if (iVar.f14902b || (gVar.getKind() instanceof ab.d)) {
            return;
        }
        db.b bVar = this.f15109c;
        r.n(gVar, bVar);
        if (iVar.f14909i) {
            Set setB = b1.b(gVar);
            Map map = (Map) ((ConcurrentHashMap) bVar.f14880c.f183l).get(gVar);
            Object obj = map != null ? map.get(r.f15173a) : null;
            if (obj == null) {
                obj = null;
            }
            Map map2 = (Map) obj;
            Set setKeySet = map2 != null ? map2.keySet() : null;
            if (setKeySet == null) {
                setKeySet = kotlin.collections.b0.f19686i;
            }
            setB0 = q0.b0(setB, setKeySet);
        } else {
            setB0 = b1.b(gVar);
        }
        for (String str : S().f14881i.keySet()) {
            if (!setB0.contains(str) && !kotlin.jvm.internal.p.a(str, this.f15186f)) {
                String string = S().toString();
                StringBuilder sbT = a0.c.t("Encountered an unknown key '", str, "'.\nUse 'ignoreUnknownKeys = true' in 'Json {}' builder to ignore unknown keys.\nCurrent input: ");
                sbT.append((Object) r.m(string, -1));
                throw r.c(-1, sbT.toString());
            }
        }
    }

    @Override // bb.b
    public int w(ab.g gVar) {
        while (this.f15188h < gVar.d()) {
            int i10 = this.f15188h;
            this.f15188h = i10 + 1;
            String strR = R(gVar, i10);
            int i11 = this.f15188h - 1;
            this.f15189i = false;
            if (!S().containsKey(strR)) {
                boolean z = (this.f15109c.f14878a.f14905e || gVar.i(i11) || !gVar.g(i11).b()) ? false : true;
                this.f15189i = z;
                if (z) {
                }
            }
            this.f15110d.getClass();
            return i11;
        }
        return -1;
    }

    @Override // eb.b, bb.d
    public final boolean x() {
        return !this.f15189i && super.x();
    }
}
