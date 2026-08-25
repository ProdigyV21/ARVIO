package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionData;
import java.util.Set;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b'\u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\f\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH ¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0010H ¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u001a\u001a\u00020\u00072\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0010¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001bH\u0010¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010!\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001bH\u0010¢\u0006\u0004\b \u0010\u001eJ\u0017\u0010#\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b\"\u0010\u000eJ\u0017\u0010%\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b$\u0010\u000eJ\u000f\u0010)\u001a\u00020&H\u0010¢\u0006\u0004\b'\u0010(J\u000f\u0010+\u001a\u00020\u0007H\u0010¢\u0006\u0004\b*\u0010\u0003J\u000f\u0010-\u001a\u00020\u0007H\u0010¢\u0006\u0004\b,\u0010\u0003J\u0017\u00102\u001a\u00020\u00072\u0006\u0010/\u001a\u00020.H ¢\u0006\u0004\b0\u00101J\u0017\u00104\u001a\u00020\u00072\u0006\u0010/\u001a\u00020.H ¢\u0006\u0004\b3\u00101J\u001f\u00109\u001a\u00020\u00072\u0006\u0010/\u001a\u00020.2\u0006\u00106\u001a\u000205H ¢\u0006\u0004\b7\u00108J\u0019\u0010<\u001a\u0004\u0018\u0001052\u0006\u0010/\u001a\u00020.H\u0010¢\u0006\u0004\b:\u0010;J\u0017\u0010>\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b=\u0010\u000eR\u0014\u0010B\u001a\u00020?8 X \u0004¢\u0006\u0006\u001a\u0004\b@\u0010AR\u0014\u0010F\u001a\u00020C8 X \u0004¢\u0006\u0006\u001a\u0004\bD\u0010ER\u0014\u0010H\u001a\u00020C8 X \u0004¢\u0006\u0006\u001a\u0004\bG\u0010ER\u0014\u0010J\u001a\u00020C8 X \u0004¢\u0006\u0006\u001a\u0004\bI\u0010ER\u0016\u0010N\u001a\u0004\u0018\u00010K8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0014\u0010R\u001a\u00020O8&X¦\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0014\u0010T\u001a\u00020O8 X \u0004¢\u0006\u0006\u001a\u0004\bS\u0010Q¨\u0006U"}, d2 = {"Landroidx/compose/runtime/CompositionContext;", "", "<init>", "()V", "Landroidx/compose/runtime/ControlledComposition;", "composition", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "composeInitial$runtime_release", "(Landroidx/compose/runtime/ControlledComposition;Lr7/p;)V", "composeInitial", "invalidate$runtime_release", "(Landroidx/compose/runtime/ControlledComposition;)V", "invalidate", "Landroidx/compose/runtime/RecomposeScopeImpl;", "scope", "invalidateScope$runtime_release", "(Landroidx/compose/runtime/RecomposeScopeImpl;)V", "invalidateScope", "", "Landroidx/compose/runtime/tooling/CompositionData;", "table", "recordInspectionTable$runtime_release", "(Ljava/util/Set;)V", "recordInspectionTable", "Landroidx/compose/runtime/Composer;", "composer", "registerComposer$runtime_release", "(Landroidx/compose/runtime/Composer;)V", "registerComposer", "unregisterComposer$runtime_release", "unregisterComposer", "registerComposition$runtime_release", "registerComposition", "unregisterComposition$runtime_release", "unregisterComposition", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "getCompositionLocalScope$runtime_release", "()Landroidx/compose/runtime/PersistentCompositionLocalMap;", "getCompositionLocalScope", "startComposing$runtime_release", "startComposing", "doneComposing$runtime_release", "doneComposing", "Landroidx/compose/runtime/MovableContentStateReference;", "reference", "insertMovableContent$runtime_release", "(Landroidx/compose/runtime/MovableContentStateReference;)V", "insertMovableContent", "deletedMovableContent$runtime_release", "deletedMovableContent", "Landroidx/compose/runtime/MovableContentState;", "data", "movableContentStateReleased$runtime_release", "(Landroidx/compose/runtime/MovableContentStateReference;Landroidx/compose/runtime/MovableContentState;)V", "movableContentStateReleased", "movableContentStateResolve$runtime_release", "(Landroidx/compose/runtime/MovableContentStateReference;)Landroidx/compose/runtime/MovableContentState;", "movableContentStateResolve", "reportRemovedComposition$runtime_release", "reportRemovedComposition", "", "getCompoundHashKey$runtime_release", "()I", "compoundHashKey", "", "getCollectingParameterInformation$runtime_release", "()Z", "collectingParameterInformation", "getCollectingSourceInformation$runtime_release", "collectingSourceInformation", "getCollectingCallByInformation$runtime_release", "collectingCallByInformation", "Landroidx/compose/runtime/CompositionObserverHolder;", "getObserverHolder$runtime_release", "()Landroidx/compose/runtime/CompositionObserverHolder;", "observerHolder", "Ld7/j;", "getEffectCoroutineContext", "()Ld7/j;", "effectCoroutineContext", "getRecomposeCoroutineContext$runtime_release", "recomposeCoroutineContext", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class CompositionContext {
    public static final int $stable = 0;

    public abstract void composeInitial$runtime_release(ControlledComposition composition, p<? super Composer, ? super Integer, t0> content);

    public abstract void deletedMovableContent$runtime_release(MovableContentStateReference reference);

    public void doneComposing$runtime_release() {
    }

    public abstract boolean getCollectingCallByInformation$runtime_release();

    public abstract boolean getCollectingParameterInformation$runtime_release();

    public abstract boolean getCollectingSourceInformation$runtime_release();

    public PersistentCompositionLocalMap getCompositionLocalScope$runtime_release() {
        return CompositionContextKt.EmptyPersistentCompositionLocalMap;
    }

    public abstract int getCompoundHashKey$runtime_release();

    public abstract d7.j getEffectCoroutineContext();

    public CompositionObserverHolder getObserverHolder$runtime_release() {
        return null;
    }

    public abstract d7.j getRecomposeCoroutineContext$runtime_release();

    public abstract void insertMovableContent$runtime_release(MovableContentStateReference reference);

    public abstract void invalidate$runtime_release(ControlledComposition composition);

    public abstract void invalidateScope$runtime_release(RecomposeScopeImpl scope);

    public abstract void movableContentStateReleased$runtime_release(MovableContentStateReference reference, MovableContentState data);

    public MovableContentState movableContentStateResolve$runtime_release(MovableContentStateReference reference) {
        return null;
    }

    public void recordInspectionTable$runtime_release(Set<CompositionData> table) {
    }

    public void registerComposer$runtime_release(Composer composer) {
    }

    public abstract void registerComposition$runtime_release(ControlledComposition composition);

    public abstract void reportRemovedComposition$runtime_release(ControlledComposition composition);

    public void startComposing$runtime_release() {
    }

    public void unregisterComposer$runtime_release(Composer composer) {
    }

    public abstract void unregisterComposition$runtime_release(ControlledComposition composition);
}
