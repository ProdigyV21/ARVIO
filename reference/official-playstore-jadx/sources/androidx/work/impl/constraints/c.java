package androidx.work.impl.constraints;

/* JADX INFO: loaded from: classes3.dex */
public final class c extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f6790a;

    public c(int i10) {
        this.f6790a = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof c) && this.f6790a == ((c) obj).f6790a;
    }

    public final int hashCode() {
        return this.f6790a;
    }

    public final String toString() {
        return a0.c.o(new StringBuilder("ConstraintsNotMet(reason="), this.f6790a, ')');
    }
}
