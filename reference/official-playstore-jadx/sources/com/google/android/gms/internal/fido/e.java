package com.google.android.gms.internal.fido;

/* JADX INFO: loaded from: classes4.dex */
public final class e extends g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e f13774i = new e();

    @Override // com.google.android.gms.internal.fido.g
    public final Object a() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.android.gms.internal.fido.g
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
