package x5;

import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22575a;

    public e(String str) {
        this.f22575a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof e) && p.a(this.f22575a, ((e) obj).f22575a);
    }

    public final int hashCode() {
        return this.f22575a.hashCode();
    }

    public final String toString() {
        return androidx.compose.foundation.c.u(new StringBuilder("SessionDetails(sessionId="), this.f22575a, ')');
    }
}
