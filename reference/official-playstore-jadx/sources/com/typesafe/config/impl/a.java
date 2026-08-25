package com.typesafe.config.impl;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public abstract class a implements m6.b {
    public final String a() {
        StringBuilder sb2 = new StringBuilder();
        Iterator it = b().iterator();
        while (it.hasNext()) {
            sb2.append(((c2) it.next()).e());
        }
        return sb2.toString();
    }

    public abstract Collection b();

    public final boolean equals(Object obj) {
        return (obj instanceof a) && a().equals(((a) obj).a());
    }

    public final int hashCode() {
        return a().hashCode();
    }
}
