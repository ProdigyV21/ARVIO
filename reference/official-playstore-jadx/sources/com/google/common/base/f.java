package com.google.common.base;

/* JADX INFO: loaded from: classes4.dex */
public final class f extends d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final char f13908i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final char f13909l;

    public f(char c10, char c11) {
        this.f13908i = c10;
        this.f13909l = c11;
    }

    @Override // com.google.common.base.i
    public final boolean c(char c10) {
        return c10 == this.f13908i || c10 == this.f13909l;
    }

    public final String toString() {
        return "CharMatcher.anyOf(\"" + i.a(this.f13908i) + i.a(this.f13909l) + "\")";
    }
}
