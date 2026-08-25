package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class f extends Exception {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f14529i;

    public f(m1 m1Var) {
        super("was not possible to resolve");
        StringBuilder sb2 = new StringBuilder();
        for (g gVar : m1Var.f14579d) {
            if (gVar instanceof p0) {
                sb2.append(((p0) gVar).f14607l.toString());
                sb2.append(", ");
            }
        }
        if (sb2.length() > 0) {
            sb2.setLength(sb2.length() - 2);
        }
        this.f14529i = sb2.toString();
    }
}
