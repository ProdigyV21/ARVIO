package androidx.navigation.compose;

import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.navigation.d0;

/* JADX INFO: loaded from: classes3.dex */
public final class a0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4555i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f f4556l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.l f4557m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.l f4558n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a0(f fVar, r7.l lVar, r7.l lVar2, int i10) {
        super(1);
        this.f4555i = i10;
        this.f4556l = fVar;
        this.f4557m = lVar;
        this.f4558n = lVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // r7.l
    public final Object invoke(Object obj) {
        int i10 = this.f4555i;
        r7.l lVar = this.f4558n;
        r7.l lVar2 = this.f4557m;
        f fVar = this.f4556l;
        switch (i10) {
            case 0:
                AnimatedContentTransitionScope animatedContentTransitionScope = (AnimatedContentTransitionScope) obj;
                androidx.navigation.b bVar = androidx.navigation.b.f4525p;
                e eVar = (e) ((androidx.navigation.i) animatedContentTransitionScope.getTargetState()).f4681l;
                if (((Boolean) fVar.f4563c.getValue()).booleanValue()) {
                    int i11 = d0.f4656r;
                    for (d0 d0Var : ga.r.M(eVar, bVar)) {
                    }
                    return (EnterTransition) lVar2.invoke(animatedContentTransitionScope);
                }
                int i12 = d0.f4656r;
                for (d0 d0Var2 : ga.r.M(eVar, bVar)) {
                }
                return (EnterTransition) lVar.invoke(animatedContentTransitionScope);
            default:
                AnimatedContentTransitionScope animatedContentTransitionScope2 = (AnimatedContentTransitionScope) obj;
                androidx.navigation.b bVar2 = androidx.navigation.b.f4525p;
                e eVar2 = (e) ((androidx.navigation.i) animatedContentTransitionScope2.getInitialState()).f4681l;
                if (((Boolean) fVar.f4563c.getValue()).booleanValue()) {
                    int i13 = d0.f4656r;
                    for (d0 d0Var3 : ga.r.M(eVar2, bVar2)) {
                    }
                    return (ExitTransition) lVar2.invoke(animatedContentTransitionScope2);
                }
                int i14 = d0.f4656r;
                for (d0 d0Var4 : ga.r.M(eVar2, bVar2)) {
                }
                return (ExitTransition) lVar.invoke(animatedContentTransitionScope2);
        }
    }
}
