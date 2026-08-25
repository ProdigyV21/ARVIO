package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;

/* JADX INFO: loaded from: classes4.dex */
public final class c0 extends l0 {
    public c0(c2 c2Var) {
        super(c2Var);
        c2 c2Var2 = m2.f14581a;
        if (!(c2Var instanceof f2)) {
            throw new ConfigException.BugOrBroken(null, "Tried to create a ConfigNodeComment from a non-comment token");
        }
    }

    public final String c() {
        c2 c2Var = m2.f14581a;
        c2 c2Var2 = this.f14568a;
        if (c2Var2 instanceof f2) {
            return ((f2) c2Var2).f14535e;
        }
        throw new ConfigException.BugOrBroken(null, "tried to get comment text from " + c2Var2);
    }
}
