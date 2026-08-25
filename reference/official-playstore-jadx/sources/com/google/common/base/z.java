package com.google.common.base;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class z implements a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13923a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f13924b;

    public /* synthetic */ z(Object obj, int i10) {
        this.f13923a = i10;
        this.f13924b = obj;
    }

    @Override // com.google.common.base.a0
    public Iterator a(b0 b0Var, CharSequence charSequence) {
        switch (this.f13923a) {
            case 0:
                return new y(this, b0Var, charSequence, 0);
            default:
                return new y(this, b0Var, charSequence, 1);
        }
    }

    public void b(StringBuilder sb2, Iterator it) {
        o oVar = (o) this.f13924b;
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            sb2.append(o.c(entry.getKey()));
            sb2.append("=");
            sb2.append(o.c(entry.getValue()));
            while (it.hasNext()) {
                sb2.append((CharSequence) oVar.f13911a);
                Map.Entry entry2 = (Map.Entry) it.next();
                sb2.append(o.c(entry2.getKey()));
                sb2.append("=");
                sb2.append(o.c(entry2.getValue()));
            }
        }
    }
}
