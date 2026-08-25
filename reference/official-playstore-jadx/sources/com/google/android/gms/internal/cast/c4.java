package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.media.CastMediaOptions;

/* JADX INFO: loaded from: classes4.dex */
public final class c4 extends b4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13220i;

    public c4(CastMediaOptions castMediaOptions) {
        this.f13220i = castMediaOptions;
    }

    @Override // com.google.android.gms.internal.cast.b4
    public final Object a(CastMediaOptions castMediaOptions) {
        u0.p(castMediaOptions, "use Optional.orNull() instead of Optional.or(null)");
        return this.f13220i;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof c4) {
            return this.f13220i.equals(((c4) obj).f13220i);
        }
        return false;
    }

    public final int hashCode() {
        return this.f13220i.hashCode() + 1502476572;
    }

    public final String toString() {
        String string = this.f13220i.toString();
        return androidx.compose.material3.d.q(new StringBuilder(string.length() + 13), "Optional.of(", string, ")");
    }
}
