package androidx.activity;

import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class f0 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f940i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l0 f941l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f0(l0 l0Var, int i10) {
        super(0);
        this.f940i = i10;
        this.f941l = l0Var;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f940i) {
            case 0:
                this.f941l.c();
                break;
            case 1:
                this.f941l.b();
                break;
            default:
                this.f941l.c();
                break;
        }
        return t0.f22605a;
    }
}
