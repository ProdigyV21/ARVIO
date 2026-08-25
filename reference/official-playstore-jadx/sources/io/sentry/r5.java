package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final class r5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f17777a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f17778b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f17779c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f17780d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f17781e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f17782f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f17783g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Runnable f17784h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public o5 f17785i;

    public final boolean a() {
        return this.f17779c;
    }

    public final boolean b() {
        return this.f17777a;
    }

    public final boolean c() {
        return this.f17782f;
    }

    public final boolean d() {
        return this.f17780d;
    }

    public final boolean e() {
        return this.f17778b;
    }

    public final boolean f() {
        return this.f17781e;
    }

    public final boolean g() {
        return this.f17783g;
    }

    public final void h(boolean z) {
        this.f17779c = z;
    }

    public final void i(boolean z) {
        this.f17777a = z;
    }

    public final void j(boolean z) {
        this.f17782f = z;
    }

    public final void k(boolean z) {
        this.f17780d = z;
    }

    public final void l(boolean z) {
        this.f17778b = z;
    }

    public final void m(boolean z) {
        this.f17781e = z;
    }

    public final void n(boolean z) {
        this.f17783g = z;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("SentryFeedbackOptions{isNameRequired=");
        sb2.append(this.f17777a);
        sb2.append(", showName=");
        sb2.append(this.f17778b);
        sb2.append(", isEmailRequired=");
        sb2.append(this.f17779c);
        sb2.append(", showEmail=");
        sb2.append(this.f17780d);
        sb2.append(", useSentryUser=");
        sb2.append(this.f17781e);
        sb2.append(", showBranding=");
        sb2.append(this.f17782f);
        sb2.append(", useShakeGesture=");
        return a0.c.m(", formTitle='Report a Bug', submitButtonLabel='Send Bug Report', cancelButtonLabel='Cancel', nameLabel='Name', namePlaceholder='Your Name', emailLabel='Email', emailPlaceholder='your.email@example.org', isRequiredLabel=' (Required)', messageLabel='Description', messagePlaceholder='What's the bug? What did you expect?'}", this.f17783g, sb2);
    }
}
