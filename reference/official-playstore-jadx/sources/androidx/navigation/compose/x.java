package androidx.navigation.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.navigation.l0;
import androidx.tv.material3.NavigationDrawerKt;
import androidx.tv.material3.p1;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class x extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4629i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f4630l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4631m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f4632n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f4633o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f4634p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f4635q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ x6.o f4636r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(l0 l0Var, String str, Modifier modifier, String str2, r7.l lVar, int i10, int i11) {
        super(2);
        this.f4633o = l0Var;
        this.f4634p = str;
        this.f4630l = modifier;
        this.f4635q = str2;
        this.f4636r = lVar;
        this.f4631m = i10;
        this.f4632n = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f4629i) {
            case 0:
                ((Number) obj2).intValue();
                l0 l0Var = (l0) this.f4633o;
                String str = (String) this.f4634p;
                String str2 = (String) this.f4635q;
                r7.l lVar = (r7.l) this.f4636r;
                NavHostKt.NavHost(l0Var, str, this.f4630l, str2, lVar, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f4631m | 1), this.f4632n);
                break;
            default:
                ((Number) obj2).intValue();
                r7.r rVar = (r7.r) this.f4633o;
                p1 p1Var = (p1) this.f4634p;
                Brush brush = (Brush) this.f4635q;
                r7.p pVar = (r7.p) this.f4636r;
                NavigationDrawerKt.ModalNavigationDrawer(rVar, this.f4630l, p1Var, brush, pVar, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f4631m | 1), this.f4632n);
                break;
        }
        return t0.f22605a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(r7.r rVar, Modifier modifier, p1 p1Var, Brush brush, r7.p pVar, int i10, int i11) {
        super(2);
        this.f4633o = rVar;
        this.f4630l = modifier;
        this.f4634p = p1Var;
        this.f4635q = brush;
        this.f4636r = pVar;
        this.f4631m = i10;
        this.f4632n = i11;
    }
}
