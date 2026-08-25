package com.google.common.base;

import j$.util.Objects;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13911a;

    public o(String str) {
        str.getClass();
        this.f13911a = str;
    }

    public static CharSequence c(Object obj) {
        Objects.requireNonNull(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    public final void a(StringBuilder sb2, Iterator it) {
        try {
            if (it.hasNext()) {
                sb2.append(c(it.next()));
                while (it.hasNext()) {
                    sb2.append((CharSequence) this.f13911a);
                    sb2.append(c(it.next()));
                }
            }
        } catch (IOException e5) {
            throw new AssertionError(e5);
        }
    }

    public final String b(List list) {
        Iterator it = list.iterator();
        StringBuilder sb2 = new StringBuilder();
        a(sb2, it);
        return sb2.toString();
    }
}
