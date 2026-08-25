package androidx.compose.runtime.tooling;

import androidx.compose.runtime.RecomposeScope;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\b\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/tooling/RecomposeScopeObserver;", "", "Landroidx/compose/runtime/RecomposeScope;", "scope", "Lx6/t0;", "onBeginScopeComposition", "(Landroidx/compose/runtime/RecomposeScope;)V", "onEndScopeComposition", "onScopeDisposed", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface RecomposeScopeObserver {
    void onBeginScopeComposition(RecomposeScope scope);

    void onEndScopeComposition(RecomposeScope scope);

    void onScopeDisposed(RecomposeScope scope);
}
