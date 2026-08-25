package io.sentry.protocol;

import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.h7;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.k7;
import io.sentry.l3;
import io.sentry.l7;
import io.sentry.v4;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 extends v4 implements k2, i2 {
    public Double A;
    public Double B;
    public final ArrayList C;
    public final HashMap D;
    public g0 E;
    public ConcurrentHashMap F;
    public String z;

    public e0(ArrayList arrayList, HashMap map, g0 g0Var) {
        Double dValueOf = Double.valueOf(0.0d);
        ArrayList arrayList2 = new ArrayList();
        this.C = arrayList2;
        HashMap map2 = new HashMap();
        this.D = map2;
        this.z = "";
        this.A = dValueOf;
        this.B = null;
        arrayList2.addAll(arrayList);
        map2.putAll(map);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.D.putAll(((y) it.next()).f17727v);
        }
        this.E = g0Var;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.z != null) {
            fVar.S("transaction");
            fVar.value(this.z);
        }
        fVar.S("start_timestamp");
        fVar.b0(iLogger, k2.c.j(this.A));
        if (this.B != null) {
            fVar.S("timestamp");
            fVar.b0(iLogger, k2.c.j(this.B));
        }
        ArrayList arrayList = this.C;
        if (!arrayList.isEmpty()) {
            fVar.S("spans");
            fVar.b0(iLogger, arrayList);
        }
        fVar.S(LinkHeader.Parameters.Type);
        fVar.value("transaction");
        HashMap map = this.D;
        if (!map.isEmpty()) {
            fVar.S("measurements");
            fVar.b0(iLogger, map);
        }
        fVar.S("transaction_info");
        fVar.b0(iLogger, this.E);
        m2.f0.K(this, fVar, iLogger);
        ConcurrentHashMap concurrentHashMap = this.F;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.F, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }

    public e0(h7 h7Var) {
        super(h7Var.f17323a);
        this.C = new ArrayList();
        this.D = new HashMap();
        k7 k7Var = h7Var.f17324b;
        this.A = Double.valueOf(k7Var.f17419a.i() / 1.0E9d);
        this.B = Double.valueOf(k7Var.f17419a.f(k7Var.f17420b) / 1.0E9d);
        this.z = h7Var.f17327e;
        for (k7 k7Var2 : h7Var.f17325c) {
            if (Boolean.TRUE.equals(k7Var2.s())) {
                this.C.add(new y(k7Var2));
            }
        }
        c cVar = this.f17985l;
        cVar.l(h7Var.f17337p);
        l7 l7Var = k7Var.f17421c;
        ConcurrentHashMap concurrentHashMap = k7Var.f17428j;
        l7 l7Var2 = new l7(l7Var.f17435i, l7Var.f17436l, l7Var.f17437m, l7Var.f17439o, l7Var.f17440p, l7Var.f17438n, l7Var.f17441q, l7Var.f17443s);
        Iterator it = l7Var.f17442r.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            b((String) entry.getKey(), (String) entry.getValue());
        }
        if (concurrentHashMap != null) {
            for (Map.Entry entry2 : concurrentHashMap.entrySet()) {
                String str = (String) entry2.getKey();
                Object value = entry2.getValue();
                if (str != null) {
                    if (value == null) {
                        l7Var2.f17444t.remove(str);
                    } else {
                        l7Var2.f17444t.put(str, value);
                    }
                }
            }
        }
        l7Var.x.n();
        cVar.v(l7Var2);
        this.E = new g0(h7Var.f17335n.apiName());
    }
}
