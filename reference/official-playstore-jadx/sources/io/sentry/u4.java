package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final class u4 implements a5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a5 f17951a;

    public u4() {
        if (io.sentry.util.o.f17967a || !io.sentry.util.o.f17968b) {
            this.f17951a = new t5(1);
        } else {
            this.f17951a = new t5(0);
        }
    }

    @Override // io.sentry.a5
    public final z4 a() {
        return this.f17951a.a();
    }
}
