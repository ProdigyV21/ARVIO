package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.media.CastMediaOptions;

/* JADX INFO: loaded from: classes4.dex */
public final class a4 extends b4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a4 f13168i = new a4();

    @Override // com.google.android.gms.internal.cast.b4
    public final Object a(CastMediaOptions castMediaOptions) {
        u0.p(castMediaOptions, "use Optional.orNull() instead of Optional.or(null)");
        return castMediaOptions;
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final int hashCode() {
        return 2040732332;
    }

    public final String toString() {
        return "Optional.absent()";
    }
}
