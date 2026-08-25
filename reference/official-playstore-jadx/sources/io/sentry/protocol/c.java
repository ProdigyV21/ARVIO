package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.l3;
import io.sentry.l7;
import io.sentry.r3;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes5.dex */
public class c implements i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ConcurrentHashMap f17550i = new ConcurrentHashMap();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.util.b f17551l = new io.sentry.util.b();

    public c() {
    }

    public boolean a(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.f17550i.containsKey(obj);
    }

    public Set b() {
        return this.f17550i.entrySet();
    }

    public Object c(Object obj) {
        if (obj == null) {
            return null;
        }
        return this.f17550i.get(obj);
    }

    public a d() {
        return (a) w(a.class, "app");
    }

    public f e() {
        return (f) w(f.class, "device");
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof c)) {
            return false;
        }
        return this.f17550i.equals(((c) obj).f17550i);
    }

    public h f() {
        return (h) w(h.class, "flags");
    }

    public o g() {
        return (o) w(o.class, "os");
    }

    public x h() {
        return (x) w(x.class, "runtime");
    }

    public final int hashCode() {
        return this.f17550i.hashCode();
    }

    public l7 i() {
        return (l7) w(l7.class, "trace");
    }

    public Enumeration j() {
        return this.f17550i.keys();
    }

    public Object k(String str, Object obj) {
        if (str == null) {
            return null;
        }
        ConcurrentHashMap concurrentHashMap = this.f17550i;
        return obj == null ? concurrentHashMap.remove(str) : concurrentHashMap.put(str, obj);
    }

    public void l(c cVar) {
        if (cVar == null) {
            return;
        }
        this.f17550i.putAll(cVar.f17550i);
    }

    public void m(a aVar) {
        k("app", aVar);
    }

    public void n(b bVar) {
        k("browser", bVar);
    }

    public void o(f fVar) {
        k("device", fVar);
    }

    public void p(h hVar) {
        k("flags", hVar);
    }

    public void q(k kVar) {
        k("gpu", kVar);
    }

    public void r(o oVar) {
        k("os", oVar);
    }

    public void s(r rVar) {
        io.sentry.util.a aVarA = this.f17551l.a();
        try {
            k("response", rVar);
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.i2
    public void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        ArrayList<String> list = Collections.list(j());
        Collections.sort(list);
        for (String str : list) {
            Object objC = c(str);
            if (objC != null) {
                fVar.S(str);
                fVar.b0(iLogger, objC);
            }
        }
        fVar.K();
    }

    public void t(x xVar) {
        k("runtime", xVar);
    }

    public void u(f0 f0Var) {
        k("spring", f0Var);
    }

    public void v(l7 l7Var) {
        ac.b.V(l7Var, "traceContext is required");
        k("trace", l7Var);
    }

    public final Object w(Class cls, String str) {
        Object objC = c(str);
        if (cls.isInstance(objC)) {
            return cls.cast(objC);
        }
        return null;
    }

    public c(c cVar) {
        for (Map.Entry entry : cVar.b()) {
            if (entry != null) {
                Object value = entry.getValue();
                if ("app".equals(entry.getKey()) && (value instanceof a)) {
                    a aVar = (a) value;
                    a aVar2 = new a();
                    aVar2.f17535q = aVar.f17535q;
                    aVar2.f17529i = aVar.f17529i;
                    aVar2.f17533o = aVar.f17533o;
                    aVar2.f17530l = aVar.f17530l;
                    aVar2.f17534p = aVar.f17534p;
                    aVar2.f17532n = aVar.f17532n;
                    aVar2.f17531m = aVar.f17531m;
                    aVar2.f17536r = k2.c.w(aVar.f17536r);
                    aVar2.f17539u = aVar.f17539u;
                    List list = aVar.f17537s;
                    aVar2.f17537s = list != null ? new ArrayList(list) : null;
                    aVar2.f17538t = aVar.f17538t;
                    aVar2.f17540v = aVar.f17540v;
                    aVar2.f17541w = aVar.f17541w;
                    aVar2.x = k2.c.w(aVar.x);
                    m(aVar2);
                } else if ("browser".equals(entry.getKey()) && (value instanceof b)) {
                    b bVar = (b) value;
                    b bVar2 = new b();
                    bVar2.f17542i = bVar.f17542i;
                    bVar2.f17543l = bVar.f17543l;
                    bVar2.f17544m = k2.c.w(bVar.f17544m);
                    n(bVar2);
                } else if ("device".equals(entry.getKey()) && (value instanceof f)) {
                    f fVar = (f) value;
                    f fVar2 = new f();
                    fVar2.f17567i = fVar.f17567i;
                    fVar2.f17568l = fVar.f17568l;
                    fVar2.f17569m = fVar.f17569m;
                    fVar2.f17570n = fVar.f17570n;
                    fVar2.f17571o = fVar.f17571o;
                    fVar2.f17572p = fVar.f17572p;
                    fVar2.f17575s = fVar.f17575s;
                    fVar2.f17576t = fVar.f17576t;
                    fVar2.f17577u = fVar.f17577u;
                    fVar2.f17578v = fVar.f17578v;
                    fVar2.f17579w = fVar.f17579w;
                    fVar2.x = fVar.x;
                    fVar2.f17580y = fVar.f17580y;
                    fVar2.z = fVar.z;
                    fVar2.A = fVar.A;
                    fVar2.B = fVar.B;
                    fVar2.C = fVar.C;
                    fVar2.D = fVar.D;
                    fVar2.E = fVar.E;
                    fVar2.F = fVar.F;
                    fVar2.G = fVar.G;
                    fVar2.H = fVar.H;
                    fVar2.I = fVar.I;
                    fVar2.K = fVar.K;
                    fVar2.M = fVar.M;
                    fVar2.N = fVar.N;
                    fVar2.f17574r = fVar.f17574r;
                    String[] strArr = fVar.f17573q;
                    fVar2.f17573q = strArr != null ? (String[]) strArr.clone() : null;
                    fVar2.L = fVar.L;
                    TimeZone timeZone = fVar.J;
                    fVar2.J = timeZone != null ? (TimeZone) timeZone.clone() : null;
                    fVar2.O = fVar.O;
                    fVar2.P = fVar.P;
                    fVar2.Q = fVar.Q;
                    fVar2.R = fVar.R;
                    fVar2.S = k2.c.w(fVar.S);
                    o(fVar2);
                } else if ("os".equals(entry.getKey()) && (value instanceof o)) {
                    o oVar = (o) value;
                    o oVar2 = new o();
                    oVar2.f17653i = oVar.f17653i;
                    oVar2.f17654l = oVar.f17654l;
                    oVar2.f17655m = oVar.f17655m;
                    oVar2.f17656n = oVar.f17656n;
                    oVar2.f17657o = oVar.f17657o;
                    oVar2.f17658p = oVar.f17658p;
                    oVar2.f17659q = k2.c.w(oVar.f17659q);
                    r(oVar2);
                } else if ("runtime".equals(entry.getKey()) && (value instanceof x)) {
                    x xVar = (x) value;
                    x xVar2 = new x();
                    xVar2.f17712i = xVar.f17712i;
                    xVar2.f17713l = xVar.f17713l;
                    xVar2.f17714m = xVar.f17714m;
                    xVar2.f17715n = k2.c.w(xVar.f17715n);
                    t(xVar2);
                } else if ("feedback".equals(entry.getKey()) && (value instanceof i)) {
                    i iVar = (i) value;
                    i iVar2 = new i();
                    iVar2.f17590i = iVar.f17590i;
                    iVar2.f17591l = iVar.f17591l;
                    iVar2.f17592m = iVar.f17592m;
                    iVar2.f17593n = iVar.f17593n;
                    iVar2.f17594o = iVar.f17594o;
                    iVar2.f17595p = iVar.f17595p;
                    iVar2.f17596q = k2.c.w(iVar.f17596q);
                    k("feedback", iVar2);
                } else if ("gpu".equals(entry.getKey()) && (value instanceof k)) {
                    k kVar = (k) value;
                    k kVar2 = new k();
                    kVar2.f17612i = kVar.f17612i;
                    kVar2.f17613l = kVar.f17613l;
                    kVar2.f17614m = kVar.f17614m;
                    kVar2.f17615n = kVar.f17615n;
                    kVar2.f17616o = kVar.f17616o;
                    kVar2.f17617p = kVar.f17617p;
                    kVar2.f17618q = kVar.f17618q;
                    kVar2.f17619r = kVar.f17619r;
                    kVar2.f17620s = kVar.f17620s;
                    kVar2.f17621t = k2.c.w(kVar.f17621t);
                    q(kVar2);
                } else if ("trace".equals(entry.getKey()) && (value instanceof l7)) {
                    v(new l7((l7) value));
                } else if ("profile".equals(entry.getKey()) && (value instanceof r3)) {
                    r3 r3Var = (r3) value;
                    r3 r3Var2 = new r3();
                    r3Var2.f17775i = r3Var.f17775i;
                    ConcurrentHashMap concurrentHashMapW = k2.c.w(r3Var.f17776l);
                    if (concurrentHashMapW != null) {
                        r3Var2.f17776l = concurrentHashMapW;
                    }
                    k("profile", r3Var2);
                } else if ("response".equals(entry.getKey()) && (value instanceof r)) {
                    r rVar = (r) value;
                    r rVar2 = new r();
                    rVar2.f17684i = rVar.f17684i;
                    rVar2.f17685l = k2.c.w(rVar.f17685l);
                    rVar2.f17689p = k2.c.w(rVar.f17689p);
                    rVar2.f17686m = rVar.f17686m;
                    rVar2.f17687n = rVar.f17687n;
                    rVar2.f17688o = rVar.f17688o;
                    s(rVar2);
                } else if ("spring".equals(entry.getKey()) && (value instanceof f0)) {
                    f0 f0Var = (f0) value;
                    f0 f0Var2 = new f0();
                    f0Var2.f17581i = f0Var.f17581i;
                    f0Var2.f17582l = k2.c.w(f0Var.f17582l);
                    u(f0Var2);
                } else {
                    k((String) entry.getKey(), value);
                }
            }
        }
    }
}
