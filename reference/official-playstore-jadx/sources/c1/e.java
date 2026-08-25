package c1;

import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7375a;

    public e(String str) {
        this.f7375a = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        return p.a(this.f7375a, ((e) obj).f7375a);
    }

    public final int hashCode() {
        return this.f7375a.hashCode();
    }

    public final String toString() {
        return this.f7375a;
    }
}
