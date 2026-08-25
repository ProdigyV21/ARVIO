package com.squareup.moshi;

import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class l {
    public abstract Object a(p pVar);

    public final Object b(String str) {
        xb.j jVar = new xb.j();
        jVar.R0(0, str.length(), str);
        p pVar = new p(jVar);
        Object objA = a(pVar);
        if (pVar.b0() == 10) {
            return objA;
        }
        throw new JsonDataException("JSON document was not fully consumed.");
    }

    public final j6.a c() {
        return this instanceof j6.a ? (j6.a) this : new j6.a(this);
    }

    public final String d(Object obj) {
        xb.j jVar = new xb.j();
        try {
            e(new q(jVar), obj);
            return jVar.t0();
        } catch (IOException e5) {
            throw new AssertionError(e5);
        }
    }

    public abstract void e(q qVar, Object obj);
}
