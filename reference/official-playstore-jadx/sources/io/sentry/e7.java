package io.sentry;

import java.util.ListIterator;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e7 implements n7, io.sentry.util.j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f17238i;

    public /* synthetic */ e7(Object obj) {
        this.f17238i = obj;
    }

    @Override // io.sentry.n7
    public void b(k7 k7Var) {
        h7 h7Var = (h7) this.f17238i;
        l lVar = h7Var.f17338q;
        if (lVar != null) {
            lVar.a(k7Var);
        }
        g7 g7Var = h7Var.f17328f;
        w7 w7Var = h7Var.f17339r;
        if (w7Var.f18042g == null) {
            if (g7Var.f17306a) {
                h7Var.q(g7Var.f17307b, null);
                return;
            }
            return;
        }
        if (w7Var.f18041f) {
            ListIterator listIterator = h7Var.f17325c.listIterator();
            while (listIterator.hasNext()) {
                k7 k7Var2 = (k7) listIterator.next();
                if (!k7Var2.f17424f && k7Var2.f17420b == null) {
                    return;
                }
            }
        }
        h7Var.m();
    }

    @Override // io.sentry.util.j
    public Object g() {
        return (String) this.f17238i;
    }
}
