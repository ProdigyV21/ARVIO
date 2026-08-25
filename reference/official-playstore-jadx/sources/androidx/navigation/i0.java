package androidx.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a1 f4692a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4693b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f4694c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashMap f4695d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f4696e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LinkedHashMap f4697f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final b1 f4698g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f4699h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f4700i;

    public i0(b1 b1Var, String str, String str2) {
        b1Var.getClass();
        this.f4692a = b1Var.b(m2.f0.t(j0.class));
        this.f4693b = -1;
        this.f4694c = str2;
        this.f4695d = new LinkedHashMap();
        this.f4696e = new ArrayList();
        this.f4697f = new LinkedHashMap();
        this.f4700i = new ArrayList();
        this.f4698g = b1Var;
        this.f4699h = str;
    }

    public final h0 a() {
        int iHashCode;
        d0 d0VarA = this.f4692a.a();
        d0VarA.getClass();
        for (Map.Entry entry : this.f4695d.entrySet()) {
            d0VarA.f4661o.put((String) entry.getKey(), (g) entry.getValue());
        }
        Iterator it = this.f4696e.iterator();
        while (it.hasNext()) {
            d0VarA.a((y) it.next());
        }
        Iterator it2 = this.f4697f.entrySet().iterator();
        if (it2.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it2.next();
            ((Number) entry2.getKey()).intValue();
            entry2.getValue().getClass();
            throw new ClassCastException();
        }
        String str = this.f4694c;
        if (str != null) {
            d0VarA.e(str);
        }
        int i10 = this.f4693b;
        if (i10 != -1) {
            d0VarA.f4662p = i10;
        }
        h0 h0Var = (h0) d0VarA;
        for (d0 d0Var : this.f4700i) {
            if (d0Var != null) {
                t.m0 m0Var = h0Var.f4676s;
                int i11 = d0Var.f4662p;
                String str2 = d0Var.f4663q;
                if (i11 == 0 && str2 == null) {
                    throw new IllegalArgumentException("Destinations must have an id or route. Call setId(), setRoute(), or include an android:id or app:route in your navigation XML.");
                }
                String str3 = h0Var.f4663q;
                if (str3 != null && kotlin.jvm.internal.p.a(str2, str3)) {
                    throw new IllegalArgumentException(("Destination " + d0Var + " cannot have the same route as graph " + h0Var).toString());
                }
                if (i11 == h0Var.f4662p) {
                    throw new IllegalArgumentException(("Destination " + d0Var + " cannot have the same id as graph " + h0Var).toString());
                }
                d0 d0Var2 = (d0) m0Var.c(i11);
                if (d0Var2 == d0Var) {
                    continue;
                } else {
                    if (d0Var.f4658l != null) {
                        throw new IllegalStateException("Destination already has a parent set. Call NavGraph.remove() to remove the previous parent.");
                    }
                    if (d0Var2 != null) {
                        d0Var2.f4658l = null;
                    }
                    d0Var.f4658l = h0Var;
                    m0Var.e(d0Var.f4662p, d0Var);
                }
            }
        }
        String str4 = this.f4699h;
        if (str4 == null) {
            if (str != null) {
                throw new IllegalStateException("You must set a start destination route");
            }
            throw new IllegalStateException("You must set a start destination id");
        }
        if (str4 == null) {
            iHashCode = 0;
        } else {
            if (str4.equals(h0Var.f4663q)) {
                throw new IllegalArgumentException(("Start destination " + str4 + " cannot use the same route as the graph " + h0Var).toString());
            }
            if (kotlin.text.o.h0(str4)) {
                throw new IllegalArgumentException("Cannot have an empty start destination route");
            }
            iHashCode = "android-app://androidx.navigation/".concat(str4).hashCode();
        }
        h0Var.f4677t = iHashCode;
        h0Var.f4679v = str4;
        return h0Var;
    }
}
