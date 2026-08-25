package com.google.common.base;

/* JADX INFO: loaded from: classes4.dex */
public final class e extends d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13902i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final char f13903l;

    public /* synthetic */ e(char c10, int i10) {
        this.f13902i = i10;
        this.f13903l = c10;
    }

    @Override // com.google.common.base.i
    public final boolean c(char c10) {
        switch (this.f13902i) {
            case 0:
                if (c10 == this.f13903l) {
                }
                break;
            default:
                if (c10 != this.f13903l) {
                }
                break;
        }
        return false;
    }

    @Override // com.google.common.base.d, com.google.common.base.i
    public final i d() {
        switch (this.f13902i) {
            case 0:
                return new e(this.f13903l, 1);
            default:
                return new e(this.f13903l, 0);
        }
    }

    public final String toString() {
        switch (this.f13902i) {
            case 0:
                return "CharMatcher.is('" + i.a(this.f13903l) + "')";
            default:
                return "CharMatcher.isNot('" + i.a(this.f13903l) + "')";
        }
    }
}
