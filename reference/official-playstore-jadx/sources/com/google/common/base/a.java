package com.google.common.base;

/* JADX INFO: loaded from: classes4.dex */
public final class a extends i {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i f13886i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i f13887l;

    public a(i iVar, i iVar2) {
        iVar.getClass();
        this.f13886i = iVar;
        iVar2.getClass();
        this.f13887l = iVar2;
    }

    @Override // com.google.common.base.r
    public final boolean apply(Object obj) {
        return c(((Character) obj).charValue());
    }

    @Override // com.google.common.base.i
    public final boolean c(char c10) {
        return this.f13886i.c(c10) && this.f13887l.c(c10);
    }

    public final String toString() {
        return "CharMatcher.and(" + this.f13886i + ", " + this.f13887l + ")";
    }
}
