package io.sentry;

import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public final class e6 extends z4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Date f17236i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f17237l;

    public e6(Date date, long j10) {
        this.f17236i = date;
        this.f17237l = j10;
    }

    @Override // io.sentry.z4, java.lang.Comparable
    /* JADX INFO: renamed from: a */
    public final int compareTo(z4 z4Var) {
        if (!(z4Var instanceof e6)) {
            return super.compareTo(z4Var);
        }
        e6 e6Var = (e6) z4Var;
        long time = this.f17236i.getTime();
        long time2 = e6Var.f17236i.getTime();
        return time == time2 ? Long.valueOf(this.f17237l).compareTo(Long.valueOf(e6Var.f17237l)) : Long.valueOf(time).compareTo(Long.valueOf(time2));
    }

    @Override // io.sentry.z4
    public final long d(z4 z4Var) {
        return z4Var instanceof e6 ? this.f17237l - ((e6) z4Var).f17237l : super.d(z4Var);
    }

    @Override // io.sentry.z4
    public final long f(z4 z4Var) {
        if (z4Var == null || !(z4Var instanceof e6)) {
            return super.f(z4Var);
        }
        e6 e6Var = (e6) z4Var;
        long j10 = e6Var.f17237l;
        int iCompareTo = compareTo(z4Var);
        long j11 = this.f17237l;
        if (iCompareTo < 0) {
            return i() + (j10 - j11);
        }
        return e6Var.i() + (j11 - j10);
    }

    @Override // io.sentry.z4
    public final long i() {
        return this.f17236i.getTime() * 1000000;
    }

    public e6() {
        this(k2.c.m(), System.nanoTime());
    }
}
