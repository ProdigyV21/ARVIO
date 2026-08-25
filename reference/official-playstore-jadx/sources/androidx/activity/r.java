package androidx.activity;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.b1;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class r extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f988i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ t f989l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(t tVar, int i10) {
        super(0);
        this.f988i = i10;
        this.f989l = tVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f988i) {
            case 0:
                t tVar = this.f989l;
                return new b1(tVar.getApplication(), tVar, tVar.getIntent() != null ? tVar.getIntent().getExtras() : null);
            case 1:
                this.f989l.reportFullyDrawn();
                return t0.f22605a;
            case 2:
                t tVar2 = this.f989l;
                return new w(tVar2.reportFullyDrawnExecutor, new r(tVar2, 1));
            default:
                t tVar3 = this.f989l;
                l0 l0Var = new l0(new e(tVar3, 1));
                if (Build.VERSION.SDK_INT >= 33) {
                    if (kotlin.jvm.internal.p.a(Looper.myLooper(), Looper.getMainLooper())) {
                        tVar3.getLifecycle().addObserver(new i(l0Var, tVar3, 0));
                    } else {
                        new Handler(Looper.getMainLooper()).post(new s(tVar3, l0Var, 0));
                    }
                }
                return l0Var;
        }
    }
}
