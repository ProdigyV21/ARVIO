package androidx.compose.runtime;

import androidx.compose.runtime.Recomposer;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import t.d0;
import t.j0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0010\"\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u00052\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "", "changed", "Landroidx/compose/runtime/snapshots/Snapshot;", "<anonymous parameter 1>", "Lx6/t0;", "invoke", "(Ljava/util/Set;Landroidx/compose/runtime/snapshots/Snapshot;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class Recomposer$recompositionRunner$2$unregisterApplyObserver$1 extends r implements p<Set<? extends Object>, Snapshot, t0> {
    final /* synthetic */ Recomposer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Recomposer$recompositionRunner$2$unregisterApplyObserver$1(Recomposer recomposer) {
        super(2);
        this.this$0 = recomposer;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Set<? extends Object>) obj, (Snapshot) obj2);
        return t0.f22605a;
    }

    public final void invoke(Set<? extends Object> set, Snapshot snapshot) {
        ka.k kVarDeriveStateLocked;
        Object obj = this.this$0.stateLock;
        Recomposer recomposer = this.this$0;
        synchronized (obj) {
            try {
                if (((Recomposer.State) recomposer._state.getValue()).compareTo(Recomposer.State.Idle) >= 0) {
                    d0 d0Var = recomposer.snapshotInvalidations;
                    if (set instanceof ScatterSetWrapper) {
                        j0 set2 = ((ScatterSetWrapper) set).getSet();
                        Object[] objArr = set2.f21836b;
                        long[] jArr = set2.f21835a;
                        int length = jArr.length - 2;
                        if (length >= 0) {
                            int i10 = 0;
                            while (true) {
                                long j10 = jArr[i10];
                                if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                                    int i11 = 8 - ((~(i10 - length)) >>> 31);
                                    for (int i12 = 0; i12 < i11; i12++) {
                                        if ((255 & j10) < 128) {
                                            Object obj2 = objArr[(i10 << 3) + i12];
                                            if (!(obj2 instanceof StateObjectImpl) || ((StateObjectImpl) obj2).m3107isReadInh_f27i8$runtime_release(ReaderKind.m3091constructorimpl(1))) {
                                                d0Var.d(obj2);
                                            }
                                        }
                                        j10 >>= 8;
                                    }
                                    if (i11 != 8) {
                                        break;
                                    }
                                }
                                if (i10 == length) {
                                    break;
                                } else {
                                    i10++;
                                }
                            }
                        }
                    } else {
                        for (Object obj3 : set) {
                            if (!(obj3 instanceof StateObjectImpl) || ((StateObjectImpl) obj3).m3107isReadInh_f27i8$runtime_release(ReaderKind.m3091constructorimpl(1))) {
                                d0Var.d(obj3);
                            }
                        }
                    }
                    kVarDeriveStateLocked = recomposer.deriveStateLocked();
                } else {
                    kVarDeriveStateLocked = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (kVarDeriveStateLocked != null) {
            kVarDeriveStateLocked.resumeWith(t0.f22605a);
        }
    }
}
