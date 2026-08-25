package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import t.y;
import t.z;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/runtime/Composition;", "composition", "Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composition;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class RecomposeScopeImpl$end$1$2 extends r implements r7.l<Composition, t0> {
    final /* synthetic */ y $instances;
    final /* synthetic */ int $token;
    final /* synthetic */ RecomposeScopeImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecomposeScopeImpl$end$1$2(RecomposeScopeImpl recomposeScopeImpl, int i10, y yVar) {
        super(1);
        this.this$0 = recomposeScopeImpl;
        this.$token = i10;
        this.$instances = yVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Composition) obj);
        return t0.f22605a;
    }

    public final void invoke(Composition composition) {
        int i10;
        if (this.this$0.currentToken != this.$token || !p.a(this.$instances, this.this$0.trackedInstances) || !(composition instanceof CompositionImpl)) {
            return;
        }
        y yVar = this.$instances;
        int i11 = this.$token;
        RecomposeScopeImpl recomposeScopeImpl = this.this$0;
        long[] jArr = yVar.f21804a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i12 = 0;
        while (true) {
            long j10 = jArr[i12];
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i13 = 8;
                int i14 = 8 - ((~(i12 - length)) >>> 31);
                int i15 = 0;
                while (i15 < i14) {
                    if ((255 & j10) < 128) {
                        int i16 = (i12 << 3) + i15;
                        Object obj = yVar.f21805b[i16];
                        boolean z = yVar.f21806c[i16] != i11;
                        i10 = i13;
                        if (z) {
                            CompositionImpl compositionImpl = (CompositionImpl) composition;
                            compositionImpl.removeObservation$runtime_release(obj, recomposeScopeImpl);
                            if (obj instanceof DerivedState) {
                                compositionImpl.removeDerivedStateObservation$runtime_release((DerivedState) obj);
                                z zVar = recomposeScopeImpl.trackedDependencies;
                                if (zVar != null) {
                                    zVar.h(obj);
                                }
                            }
                        }
                        if (z) {
                            yVar.e(i16);
                        }
                    } else {
                        i10 = i13;
                    }
                    j10 >>= i10;
                    i15++;
                    i13 = i10;
                }
                if (i14 != i13) {
                    return;
                }
            }
            if (i12 == length) {
                return;
            } else {
                i12++;
            }
        }
    }
}
