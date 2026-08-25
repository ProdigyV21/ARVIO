package wa;

import io.sentry.util.l;
import j$.time.DateTimeException;
import j$.time.Instant;
import kotlin.jvm.internal.p;
import kotlin.time.e;
import ya.r;

/* JADX INFO: loaded from: classes5.dex */
@r(with = xa.a.class)
public final class c implements Comparable<c> {
    public static final b Companion = new b();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final c f22537l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c f22538m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Instant f22539i;

    static {
        Instant.ofEpochSecond(-3217862419201L, 999999999L);
        Instant.ofEpochSecond(3093527980800L, 0L);
        f22537l = new c(Instant.MIN);
        f22538m = new c(Instant.MAX);
    }

    public c(Instant instant) {
        this.f22539i = instant;
    }

    public final c a(long j10) {
        l lVar = kotlin.time.c.f19956l;
        try {
            return new c(this.f22539i.plusSeconds(kotlin.time.c.m(j10, e.SECONDS)).plusNanos(kotlin.time.c.j(j10)));
        } catch (Exception e5) {
            if ((e5 instanceof ArithmeticException) || (e5 instanceof DateTimeException)) {
                return j10 > 0 ? f22538m : f22537l;
            }
            throw e5;
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(c cVar) {
        return this.f22539i.compareTo(cVar.f22539i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            return p.a(this.f22539i, ((c) obj).f22539i);
        }
        return false;
    }

    public final int hashCode() {
        return this.f22539i.hashCode();
    }

    public final String toString() {
        return this.f22539i.toString();
    }
}
