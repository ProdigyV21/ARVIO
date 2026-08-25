package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class g1 extends i1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final g1 f13034i = new g1();

    @Override // com.google.android.gms.internal.auth.i1
    public final Object a() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.android.gms.internal.auth.i1
    public final boolean b() {
        return false;
    }

    public final boolean equals(Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return 2040732332;
    }

    public final String toString() {
        return "Optional.absent()";
    }
}
