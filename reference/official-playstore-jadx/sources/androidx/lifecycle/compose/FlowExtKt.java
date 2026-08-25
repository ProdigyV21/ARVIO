package androidx.lifecycle.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import androidx.lifecycle.y;
import androidx.tv.material3.e0;
import d7.j;
import d7.k;
import kotlin.Metadata;
import na.h1;
import r7.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aC\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\n\u001aA\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\r\u001aK\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u000f\u001a\u00028\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\u0010\u001aI\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\u0011¨\u0006\u0012"}, d2 = {"T", "Lna/h1;", "Landroidx/lifecycle/y;", "lifecycleOwner", "Landroidx/lifecycle/q;", "minActiveState", "Ld7/j;", "context", "Landroidx/compose/runtime/State;", "collectAsStateWithLifecycle", "(Lna/h1;Landroidx/lifecycle/y;Landroidx/lifecycle/q;Ld7/j;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "Landroidx/lifecycle/r;", "lifecycle", "(Lna/h1;Landroidx/lifecycle/r;Landroidx/lifecycle/q;Ld7/j;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "Lna/j;", "initialValue", "(Lna/j;Ljava/lang/Object;Landroidx/lifecycle/y;Landroidx/lifecycle/q;Ld7/j;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "(Lna/j;Ljava/lang/Object;Landroidx/lifecycle/r;Landroidx/lifecycle/q;Ld7/j;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "lifecycle-runtime-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlowExtKt {
    public static final <T> State<T> collectAsStateWithLifecycle(h1<? extends T> h1Var, y yVar, q qVar, j jVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(743249048);
        if ((i11 & 1) != 0) {
            yVar = (y) composer.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
        }
        if ((i11 & 2) != 0) {
            qVar = q.f3269n;
        }
        q qVar2 = qVar;
        if ((i11 & 4) != 0) {
            jVar = k.f14688i;
        }
        State<T> stateCollectAsStateWithLifecycle = collectAsStateWithLifecycle(h1Var, h1Var.getValue(), yVar.getLifecycle(), qVar2, jVar, composer, ((i10 << 3) & 7168) | 33288, 0);
        composer.endReplaceableGroup();
        return stateCollectAsStateWithLifecycle;
    }

    public static final <T> State<T> collectAsStateWithLifecycle(h1<? extends T> h1Var, r rVar, q qVar, j jVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1858162195);
        if ((i11 & 2) != 0) {
            qVar = q.f3269n;
        }
        q qVar2 = qVar;
        if ((i11 & 4) != 0) {
            jVar = k.f14688i;
        }
        State<T> stateCollectAsStateWithLifecycle = collectAsStateWithLifecycle(h1Var, h1Var.getValue(), rVar, qVar2, jVar, composer, ((i10 << 3) & 7168) | 33288, 0);
        composer.endReplaceableGroup();
        return stateCollectAsStateWithLifecycle;
    }

    public static final <T> State<T> collectAsStateWithLifecycle(na.j<? extends T> jVar, T t2, y yVar, q qVar, j jVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1485997211);
        if ((i11 & 2) != 0) {
            yVar = (y) composer.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
        }
        if ((i11 & 4) != 0) {
            qVar = q.f3269n;
        }
        q qVar2 = qVar;
        if ((i11 & 8) != 0) {
            jVar2 = k.f14688i;
        }
        State<T> stateCollectAsStateWithLifecycle = collectAsStateWithLifecycle(jVar, t2, yVar.getLifecycle(), qVar2, jVar2, composer, (((i10 >> 3) & 8) << 3) | 33288 | (i10 & 112) | (i10 & 7168), 0);
        composer.endReplaceableGroup();
        return stateCollectAsStateWithLifecycle;
    }

    public static final <T> State<T> collectAsStateWithLifecycle(na.j<? extends T> jVar, T t2, r rVar, q qVar, j jVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1977777920);
        if ((i11 & 4) != 0) {
            qVar = q.f3269n;
        }
        q qVar2 = qVar;
        if ((i11 & 8) != 0) {
            jVar2 = k.f14688i;
        }
        j jVar3 = jVar2;
        Object[] objArr = {jVar, rVar, qVar2, jVar3};
        e0 e0Var = new e0(rVar, qVar2, jVar3, jVar, null);
        int i12 = i10 >> 3;
        State<T> stateProduceState = SnapshotStateKt.produceState((Object) t2, objArr, (p) e0Var, composer, (i12 & 14) | (i12 & 8) | 576);
        composer.endReplaceableGroup();
        return stateProduceState;
    }
}
