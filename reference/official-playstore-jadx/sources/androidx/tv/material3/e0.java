package androidx.tv.material3;

import android.view.accessibility.AccessibilityManager;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProduceStateScope;
import androidx.compose.ui.focus.FocusRequester;

/* JADX INFO: loaded from: classes3.dex */
public final class e0 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5924i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5925l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f5926m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f5927n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f5928o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f5929p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f5930q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e0(AccessibilityManager accessibilityManager, FocusRequester focusRequester, AnimatedContentScope animatedContentScope, MutableState mutableState, MutableState mutableState2, d7.d dVar) {
        super(2, dVar);
        this.f5926m = accessibilityManager;
        this.f5927n = focusRequester;
        this.f5928o = animatedContentScope;
        this.f5929p = mutableState;
        this.f5930q = mutableState2;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f5924i) {
            case 0:
                return new e0((AccessibilityManager) this.f5926m, (FocusRequester) this.f5927n, (AnimatedContentScope) this.f5928o, (MutableState) this.f5929p, (MutableState) this.f5930q, dVar);
            default:
                e0 e0Var = new e0((androidx.lifecycle.r) this.f5927n, (androidx.lifecycle.q) this.f5928o, (d7.j) this.f5929p, (na.j) this.f5930q, dVar);
                e0Var.f5926m = obj;
                return e0Var;
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f5924i) {
            case 0:
                return ((e0) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
            default:
                return ((e0) create((ProduceStateScope) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
        }
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object objC;
        switch (this.f5924i) {
            case 0:
                FocusRequester focusRequester = (FocusRequester) this.f5927n;
                int i10 = this.f5925l;
                x6.t0 t0Var = x6.t0.f22605a;
                if (i10 != 0) {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return t0Var;
                }
                k2.c.G(obj);
                if (((AccessibilityManager) this.f5926m).isEnabled()) {
                    focusRequester.requestFocus();
                }
                d0 d0Var = new d0((MutableState) this.f5929p, (MutableState) this.f5930q, focusRequester, null);
                this.f5925l = 1;
                d0Var.invoke(this);
                e7.a aVar = e7.a.f15033i;
                return t0Var == aVar ? aVar : t0Var;
            default:
                int i11 = this.f5925l;
                x6.t0 t0Var2 = x6.t0.f22605a;
                if (i11 != 0) {
                    if (i11 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return t0Var2;
                }
                k2.c.G(obj);
                ProduceStateScope produceStateScope = (ProduceStateScope) this.f5926m;
                androidx.lifecycle.r rVar = (androidx.lifecycle.r) this.f5927n;
                androidx.lifecycle.q qVar = (androidx.lifecycle.q) this.f5928o;
                androidx.work.impl.constraints.l lVar = new androidx.work.impl.constraints.l((d7.j) this.f5929p, (na.j) this.f5930q, produceStateScope, null, 3);
                this.f5925l = 1;
                if (qVar == androidx.lifecycle.q.f3267l) {
                    throw new IllegalArgumentException("repeatOnLifecycle cannot start work with the INITIALIZED lifecycle state.");
                }
                androidx.lifecycle.q currentState = rVar.getCurrentState();
                androidx.lifecycle.q qVar2 = androidx.lifecycle.q.f3266i;
                e7.a aVar2 = e7.a.f15033i;
                if (currentState == qVar2 || (objC = ka.l0.c(new androidx.lifecycle.q0(rVar, qVar, lVar, null), this)) != aVar2) {
                    objC = t0Var2;
                }
                return objC == aVar2 ? aVar2 : t0Var2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e0(androidx.lifecycle.r rVar, androidx.lifecycle.q qVar, d7.j jVar, na.j jVar2, d7.d dVar) {
        super(2, dVar);
        this.f5927n = rVar;
        this.f5928o = qVar;
        this.f5929p = jVar;
        this.f5930q = jVar2;
    }
}
