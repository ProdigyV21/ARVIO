package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class i1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f14554b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final StringBuilder f14553a = new StringBuilder("");

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Element(");
        sb2.append(this.f14553a.toString());
        sb2.append(",");
        return a0.c.m(")", this.f14554b, sb2);
    }
}
