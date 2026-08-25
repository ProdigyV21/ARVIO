package androidx.lifecycle.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/lifecycle/r;", "Landroidx/compose/runtime/State;", "Landroidx/lifecycle/q;", "currentStateAsState", "(Landroidx/lifecycle/r;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "lifecycle-runtime-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LifecycleExtKt {
    public static final State<q> currentStateAsState(r rVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-1892357376);
        State<q> stateCollectAsState = SnapshotStateKt.collectAsState(rVar.getCurrentStateFlow(), null, composer, 8, 1);
        composer.endReplaceableGroup();
        return stateCollectAsState;
    }
}
