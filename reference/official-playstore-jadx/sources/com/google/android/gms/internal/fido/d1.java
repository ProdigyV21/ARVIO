package com.google.android.gms.internal.fido;

import j$.util.Objects;
import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Comparator f13771a;

    static {
        Comparator comparator;
        try {
            Object[] enumConstants = Class.forName(d1.class.getName().concat("$UnsafeComparator")).getEnumConstants();
            Objects.requireNonNull(enumConstants);
            comparator = (Comparator) enumConstants[0];
        } catch (Throwable unused) {
            comparator = c1.f13767i;
        }
        f13771a = comparator;
    }
}
