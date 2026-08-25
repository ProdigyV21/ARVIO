package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.Stack;

/* JADX INFO: loaded from: classes4.dex */
public final class h1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Stack f14548a = new Stack();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g1 f14549b;

    public final void a(g1 g1Var) {
        if (this.f14549b != null) {
            throw new ConfigException.BugOrBroken(null, "Adding to PathBuilder after getting result");
        }
        String str = g1Var.f14541a;
        g1 g1Var2 = g1Var.f14542b;
        while (true) {
            this.f14548a.push(str);
            if (g1Var2 == null) {
                return;
            }
            str = g1Var2.f14541a;
            g1Var2 = g1Var2.f14542b;
        }
    }

    public final g1 b() {
        if (this.f14549b == null) {
            g1 g1Var = null;
            while (true) {
                Stack stack = this.f14548a;
                if (stack.isEmpty()) {
                    break;
                }
                g1Var = new g1((String) stack.pop(), g1Var);
            }
            this.f14549b = g1Var;
        }
        return this.f14549b;
    }
}
