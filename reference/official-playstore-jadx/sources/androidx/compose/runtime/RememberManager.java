package androidx.compose.runtime;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J/\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H&¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u000f\u001a\u00020\u00042\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\rH&¢\u0006\u0004\b\u000f\u0010\u0010J/\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H&¢\u0006\u0004\b\u0012\u0010\u0013J/\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H&¢\u0006\u0004\b\u0014\u0010\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/RememberManager;", "", "Landroidx/compose/runtime/RememberObserver;", "instance", "Lx6/t0;", "remembering", "(Landroidx/compose/runtime/RememberObserver;)V", "", "endRelativeOrder", "priority", "endRelativeAfter", "forgetting", "(Landroidx/compose/runtime/RememberObserver;III)V", "Lkotlin/Function0;", "effect", "sideEffect", "(Lr7/a;)V", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "deactivating", "(Landroidx/compose/runtime/ComposeNodeLifecycleCallback;III)V", "releasing", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface RememberManager {
    void deactivating(ComposeNodeLifecycleCallback instance, int endRelativeOrder, int priority, int endRelativeAfter);

    void forgetting(RememberObserver instance, int endRelativeOrder, int priority, int endRelativeAfter);

    void releasing(ComposeNodeLifecycleCallback instance, int endRelativeOrder, int priority, int endRelativeAfter);

    void remembering(RememberObserver instance);

    void sideEffect(r7.a<t0> effect);
}
