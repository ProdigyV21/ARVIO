package androidx.compose.material3;

import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.lifecycle.p;
import androidx.lifecycle.w;
import androidx.lifecycle.y;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TouchExplorationStateProvider_androidKt$ObserveState$3$1 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
    final /* synthetic */ l<p, t0> $handleEvent;
    final /* synthetic */ r7.a<t0> $onDispose;
    final /* synthetic */ androidx.lifecycle.r $this_ObserveState;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TouchExplorationStateProvider_androidKt$ObserveState$3$1(androidx.lifecycle.r rVar, l<? super p, t0> lVar, r7.a<t0> aVar) {
        super(1);
        this.$this_ObserveState = rVar;
        this.$handleEvent = lVar;
        this.$onDispose = aVar;
    }

    @Override // r7.l
    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
        final l<p, t0> lVar = this.$handleEvent;
        final w wVar = new w() { // from class: androidx.compose.material3.h
            @Override // androidx.lifecycle.w
            public final void onStateChanged(y yVar, p pVar) {
                lVar.invoke(pVar);
            }
        };
        this.$this_ObserveState.addObserver(wVar);
        final r7.a<t0> aVar = this.$onDispose;
        final androidx.lifecycle.r rVar = this.$this_ObserveState;
        return new DisposableEffectResult() { // from class: androidx.compose.material3.TouchExplorationStateProvider_androidKt$ObserveState$3$1$invoke$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                aVar.invoke();
                rVar.removeObserver(wVar);
            }
        };
    }
}
