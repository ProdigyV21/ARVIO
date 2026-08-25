package com.google.common.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f13893a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a0 f13894b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f13895c;

    public b0(a0 a0Var) {
        b bVar = b.f13891p;
        this.f13894b = a0Var;
        this.f13893a = bVar;
        this.f13895c = Integer.MAX_VALUE;
    }

    public static b0 a(char c10) {
        return new b0(new z(new e(c10, 0), 0));
    }

    public static b0 b(String str) {
        ac.b.k(str.length() != 0, "The separator may not be the empty string.");
        return str.length() == 1 ? a(str.charAt(0)) : new b0(new z(str, 1));
    }

    public final List c(CharSequence charSequence) {
        charSequence.getClass();
        Iterator itA = this.f13894b.a(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (true) {
            y yVar = (y) itA;
            if (!yVar.hasNext()) {
                return Collections.unmodifiableList(arrayList);
            }
            arrayList.add((String) yVar.next());
        }
    }
}
