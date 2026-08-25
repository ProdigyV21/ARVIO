package com.google.common.base;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends g {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final b f13888m = new b("CharMatcher.any()", 0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final b f13889n = new b("CharMatcher.ascii()", 1);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final b f13890o = new b("CharMatcher.javaIsoControl()", 2);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final b f13891p = new b("CharMatcher.none()", 3);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f13892l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(String str, int i10) {
        super(str);
        this.f13892l = i10;
    }

    @Override // com.google.common.base.i
    public final boolean c(char c10) {
        switch (this.f13892l) {
            case 0:
                return true;
            case 1:
                return c10 <= 127;
            case 2:
                return c10 <= 31 || (c10 >= 127 && c10 <= 159);
            default:
                return false;
        }
    }

    @Override // com.google.common.base.d, com.google.common.base.i
    public i d() {
        switch (this.f13892l) {
            case 0:
                return f13891p;
            case 3:
                return f13888m;
            default:
                return super.d();
        }
    }
}
