package io.sentry.android.core;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 implements io.sentry.hints.a, io.sentry.hints.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f16395i;

    public a0(boolean z) {
        this.f16395i = z;
    }

    @Override // io.sentry.hints.a
    public final Long a() {
        return null;
    }

    @Override // io.sentry.hints.a
    public final boolean f() {
        return true;
    }

    @Override // io.sentry.hints.a
    public final String h() {
        return this.f16395i ? "anr_background" : "anr_foreground";
    }
}
