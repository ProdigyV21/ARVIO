package androidx.compose.runtime;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.List;
import kotlin.Metadata;
import t.u;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a)\u0010\u0004\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a!\u0010\n\u001a\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000b\u001a!\u0010\r\u001a\u00020\f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000e\u001a#\u0010\u0010\u001a\u00020\u000f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a+\u0010\n\u001a\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\n\u0010\u0014\u001a+\u0010\u0010\u001a\u00020\u000f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0010\u0010\u0015\u001a/\u0010\u001b\u001a\u00020\u001a\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u001b\u0010\u001c\u001a#\u0010\u001b\u001a\u00020\u001a*\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u001b\u0010\u001e\"\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!\" \u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u001e\u0010\u0013\u001a\u00020\u0012*\u00020\u000f8GX\u0087\u0004¢\u0006\f\u0012\u0004\b)\u0010*\u001a\u0004\b'\u0010(¨\u0006+"}, d2 = {"T", "Landroidx/compose/runtime/Composition;", "Landroidx/compose/runtime/CompositionServiceKey;", "key", "getCompositionService", "(Landroidx/compose/runtime/Composition;Landroidx/compose/runtime/CompositionServiceKey;)Ljava/lang/Object;", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/CompositionContext;", "parent", "Composition", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/CompositionContext;)Landroidx/compose/runtime/Composition;", "Landroidx/compose/runtime/ReusableComposition;", "ReusableComposition", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/CompositionContext;)Landroidx/compose/runtime/ReusableComposition;", "Landroidx/compose/runtime/ControlledComposition;", "ControlledComposition", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/CompositionContext;)Landroidx/compose/runtime/ControlledComposition;", "Ld7/j;", "recomposeCoroutineContext", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/CompositionContext;Ld7/j;)Landroidx/compose/runtime/Composition;", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/CompositionContext;Ld7/j;)Landroidx/compose/runtime/ControlledComposition;", "", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "Lx6/t0;", "swap", "(Ljava/util/List;II)V", "Lt/u;", "(Lt/u;II)V", "", "PendingApplyNoModifications", "Ljava/lang/Object;", "Landroidx/compose/runtime/CompositionImpl;", "CompositionImplServiceKey", "Landroidx/compose/runtime/CompositionServiceKey;", "getCompositionImplServiceKey", "()Landroidx/compose/runtime/CompositionServiceKey;", "getRecomposeCoroutineContext", "(Landroidx/compose/runtime/ControlledComposition;)Ld7/j;", "getRecomposeCoroutineContext$annotations", "(Landroidx/compose/runtime/ControlledComposition;)V", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CompositionKt {
    private static final Object PendingApplyNoModifications = new Object();
    private static final CompositionServiceKey<CompositionImpl> CompositionImplServiceKey = new CompositionServiceKey<CompositionImpl>() { // from class: androidx.compose.runtime.CompositionKt$CompositionImplServiceKey$1
    };

    public static final Composition Composition(Applier<?> applier, CompositionContext compositionContext) {
        return new CompositionImpl(compositionContext, applier, null, 4, null);
    }

    public static final ControlledComposition ControlledComposition(Applier<?> applier, CompositionContext compositionContext) {
        return new CompositionImpl(compositionContext, applier, null, 4, null);
    }

    public static final ReusableComposition ReusableComposition(Applier<?> applier, CompositionContext compositionContext) {
        return new CompositionImpl(compositionContext, applier, null, 4, null);
    }

    public static final CompositionServiceKey<CompositionImpl> getCompositionImplServiceKey() {
        return CompositionImplServiceKey;
    }

    public static final <T> T getCompositionService(Composition composition, CompositionServiceKey<T> compositionServiceKey) {
        CompositionServices compositionServices = composition instanceof CompositionServices ? (CompositionServices) composition : null;
        if (compositionServices != null) {
            return (T) compositionServices.getCompositionService(compositionServiceKey);
        }
        return null;
    }

    public static final d7.j getRecomposeCoroutineContext(ControlledComposition controlledComposition) {
        d7.j recomposeContext;
        CompositionImpl compositionImpl = controlledComposition instanceof CompositionImpl ? (CompositionImpl) controlledComposition : null;
        return (compositionImpl == null || (recomposeContext = compositionImpl.getRecomposeContext()) == null) ? d7.k.f14688i : recomposeContext;
    }

    public static /* synthetic */ void getRecomposeCoroutineContext$annotations(ControlledComposition controlledComposition) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> void swap(List<T> list, int i10, int i11) {
        T t2 = list.get(i10);
        list.set(i10, list.get(i11));
        list.set(i11, t2);
    }

    public static final Composition Composition(Applier<?> applier, CompositionContext compositionContext, d7.j jVar) {
        return new CompositionImpl(compositionContext, applier, jVar);
    }

    public static final ControlledComposition ControlledComposition(Applier<?> applier, CompositionContext compositionContext, d7.j jVar) {
        return new CompositionImpl(compositionContext, applier, jVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void swap(u uVar, int i10, int i11) {
        int iA = uVar.a(i10);
        uVar.d(i10, uVar.a(i11));
        uVar.d(i11, iA);
    }
}
