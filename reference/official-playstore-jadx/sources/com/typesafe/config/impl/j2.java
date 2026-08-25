package com.typesafe.config.impl;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class j2 extends c2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f14562e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f14563f;

    public j2(w1 w1Var, boolean z, ArrayList arrayList) {
        super(14, w1Var, null, null);
        this.f14562e = z;
        this.f14563f = arrayList;
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean a(c2 c2Var) {
        return c2Var instanceof j2;
    }

    @Override // com.typesafe.config.impl.c2
    public final String e() {
        StringBuilder sb2 = new StringBuilder("${");
        sb2.append(this.f14562e ? "?" : "");
        Iterator it = this.f14563f.iterator();
        StringBuilder sb3 = new StringBuilder();
        while (it.hasNext()) {
            sb3.append(((c2) it.next()).e());
        }
        sb2.append(sb3.toString());
        sb2.append("}");
        return sb2.toString();
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean equals(Object obj) {
        return super.equals(obj) && ((j2) obj).f14563f.equals(this.f14563f);
    }

    @Override // com.typesafe.config.impl.c2
    public final int hashCode() {
        return this.f14563f.hashCode() + ((h.f0.c(this.f14513a) + 41) * 41);
    }

    @Override // com.typesafe.config.impl.c2
    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        Iterator it = this.f14563f.iterator();
        while (it.hasNext()) {
            sb2.append(((c2) it.next()).toString());
        }
        return "'${" + sb2.toString() + "}'";
    }
}
