package androidx.activity;

import androidx.fragment.app.b1;

/* JADX INFO: loaded from: classes.dex */
public final class m0 extends d0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f970d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f971e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m0(Object obj, int i10) {
        super(false);
        this.f970d = i10;
        this.f971e = obj;
    }

    @Override // androidx.activity.d0
    public final void b() {
        switch (this.f970d) {
            case 0:
                ((r7.l) this.f971e).invoke(this);
                break;
            case 1:
                b1 b1Var = (b1) this.f971e;
                b1Var.x(true);
                if (!b1Var.f2674h.f931a) {
                    b1Var.f2673g.c();
                } else {
                    b1Var.M();
                }
                break;
            default:
                ((androidx.navigation.s) this.f971e).k();
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m0(r7.l lVar) {
        super(true);
        this.f970d = 0;
        this.f971e = lVar;
    }
}
