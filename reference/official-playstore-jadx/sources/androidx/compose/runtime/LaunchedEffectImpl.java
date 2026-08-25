package androidx.compose.runtime;

import java.util.concurrent.CancellationException;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.v1;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B8\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\t¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000f\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0010\u0010\u000eR5\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/LaunchedEffectImpl;", "Landroidx/compose/runtime/RememberObserver;", "Ld7/j;", "parentCoroutineContext", "Lkotlin/Function2;", "Lka/k0;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "task", "<init>", "(Ld7/j;Lr7/p;)V", "onRemembered", "()V", "onForgotten", "onAbandoned", "Lr7/p;", "scope", "Lka/k0;", "Lka/v1;", "job", "Lka/v1;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LaunchedEffectImpl implements RememberObserver {
    public static final int $stable = 8;
    private v1 job;
    private final k0 scope;
    private final p<k0, d7.d<? super t0>, Object> task;

    /* JADX WARN: Multi-variable type inference failed */
    public LaunchedEffectImpl(d7.j jVar, p<? super k0, ? super d7.d<? super t0>, ? extends Object> pVar) {
        this.task = pVar;
        this.scope = l0.a(jVar);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        v1 v1Var = this.job;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) new LeftCompositionCancellationException());
        }
        this.job = null;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        v1 v1Var = this.job;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) new LeftCompositionCancellationException());
        }
        this.job = null;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        v1 v1Var = this.job;
        if (v1Var != null) {
            v1Var.cancel(m0.a("Old job was still running!", null));
        }
        this.job = m0.p(this.scope, null, 0, this.task, 3);
    }
}
