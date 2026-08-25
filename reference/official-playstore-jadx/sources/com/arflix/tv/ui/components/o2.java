package com.arflix.tv.ui.components;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o2 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8230i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ androidx.tv.foundation.lazy.list.m0 f8231l;

    public /* synthetic */ o2(androidx.tv.foundation.lazy.list.m0 m0Var, int i10) {
        this.f8230i = i10;
        this.f8231l = m0Var;
    }

    @Override // r7.a
    public final Object invoke() {
        boolean canScrollBackward;
        switch (this.f8230i) {
            case 0:
                canScrollBackward = this.f8231l.getCanScrollBackward();
                break;
            case 1:
                canScrollBackward = this.f8231l.getCanScrollForward();
                break;
            case 2:
                canScrollBackward = this.f8231l.getCanScrollBackward();
                break;
            default:
                canScrollBackward = this.f8231l.getCanScrollForward();
                break;
        }
        return Boolean.valueOf(canScrollBackward);
    }
}
