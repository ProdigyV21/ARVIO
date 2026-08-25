package androidx.compose.ui.node;

import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B0\u0012'\u0010\b\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0004\b\t\u0010\nJ/\u0010\u0012\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000¢\u0006\u0004\b\u0010\u0010\u0011J/\u0010\u0014\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000¢\u0006\u0004\b\u0013\u0010\u0011J/\u0010\u0016\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000¢\u0006\u0004\b\u0015\u0010\u0011J%\u0010\u0019\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000¢\u0006\u0004\b\u0017\u0010\u0018JC\u0010 \u001a\u00020\u0004\"\b\b\u0000\u0010\u001b*\u00020\u001a2\u0006\u0010\u001c\u001a\u00028\u00002\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u00022\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010#\u001a\u00020\u0004H\u0000¢\u0006\u0004\b!\u0010\"J\u0017\u0010&\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0001H\u0000¢\u0006\u0004\b$\u0010%J\u000f\u0010(\u001a\u00020\u0004H\u0000¢\u0006\u0004\b'\u0010\"J\u000f\u0010*\u001a\u00020\u0004H\u0000¢\u0006\u0004\b)\u0010\"R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R \u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R \u00100\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u0010/R \u00101\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u0010/R \u00102\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u0010/R \u00103\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u0010/R \u00104\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u0010/R \u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u0010/¨\u00066"}, d2 = {"Landroidx/compose/ui/node/OwnerSnapshotObserver;", "", "Lkotlin/Function1;", "Lkotlin/Function0;", "Lx6/t0;", "Lx6/y;", ContentDisposition.Parameters.Name, "callback", "onChangedExecutor", "<init>", "(Lr7/l;)V", "Landroidx/compose/ui/node/LayoutNode;", "node", "", "affectsLookahead", "block", "observeLayoutSnapshotReads$ui_release", "(Landroidx/compose/ui/node/LayoutNode;ZLr7/a;)V", "observeLayoutSnapshotReads", "observeLayoutModifierSnapshotReads$ui_release", "observeLayoutModifierSnapshotReads", "observeMeasureSnapshotReads$ui_release", "observeMeasureSnapshotReads", "observeSemanticsReads$ui_release", "(Landroidx/compose/ui/node/LayoutNode;Lr7/a;)V", "observeSemanticsReads", "Landroidx/compose/ui/node/OwnerScope;", "T", "target", "onChanged", "observeReads$ui_release", "(Landroidx/compose/ui/node/OwnerScope;Lr7/l;Lr7/a;)V", "observeReads", "clearInvalidObservations$ui_release", "()V", "clearInvalidObservations", "clear$ui_release", "(Ljava/lang/Object;)V", "clear", "startObserving$ui_release", "startObserving", "stopObserving$ui_release", "stopObserving", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "observer", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "onCommitAffectingLookaheadMeasure", "Lr7/l;", "onCommitAffectingMeasure", "onCommitAffectingSemantics", "onCommitAffectingLayout", "onCommitAffectingLayoutModifier", "onCommitAffectingLayoutModifierInLookahead", "onCommitAffectingLookahead", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OwnerSnapshotObserver {
    public static final int $stable = SnapshotStateObserver.$stable;
    private final SnapshotStateObserver observer;
    private final l<LayoutNode, t0> onCommitAffectingLookaheadMeasure = OwnerSnapshotObserver$onCommitAffectingLookaheadMeasure$1.INSTANCE;
    private final l<LayoutNode, t0> onCommitAffectingMeasure = OwnerSnapshotObserver$onCommitAffectingMeasure$1.INSTANCE;
    private final l<LayoutNode, t0> onCommitAffectingSemantics = OwnerSnapshotObserver$onCommitAffectingSemantics$1.INSTANCE;
    private final l<LayoutNode, t0> onCommitAffectingLayout = OwnerSnapshotObserver$onCommitAffectingLayout$1.INSTANCE;
    private final l<LayoutNode, t0> onCommitAffectingLayoutModifier = OwnerSnapshotObserver$onCommitAffectingLayoutModifier$1.INSTANCE;
    private final l<LayoutNode, t0> onCommitAffectingLayoutModifierInLookahead = OwnerSnapshotObserver$onCommitAffectingLayoutModifierInLookahead$1.INSTANCE;
    private final l<LayoutNode, t0> onCommitAffectingLookahead = OwnerSnapshotObserver$onCommitAffectingLookahead$1.INSTANCE;

    public OwnerSnapshotObserver(l<? super r7.a<t0>, t0> lVar) {
        this.observer = new SnapshotStateObserver(lVar);
    }

    public static /* synthetic */ void observeLayoutModifierSnapshotReads$ui_release$default(OwnerSnapshotObserver ownerSnapshotObserver, LayoutNode layoutNode, boolean z, r7.a aVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        ownerSnapshotObserver.observeLayoutModifierSnapshotReads$ui_release(layoutNode, z, aVar);
    }

    public static /* synthetic */ void observeLayoutSnapshotReads$ui_release$default(OwnerSnapshotObserver ownerSnapshotObserver, LayoutNode layoutNode, boolean z, r7.a aVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        ownerSnapshotObserver.observeLayoutSnapshotReads$ui_release(layoutNode, z, aVar);
    }

    public static /* synthetic */ void observeMeasureSnapshotReads$ui_release$default(OwnerSnapshotObserver ownerSnapshotObserver, LayoutNode layoutNode, boolean z, r7.a aVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        ownerSnapshotObserver.observeMeasureSnapshotReads$ui_release(layoutNode, z, aVar);
    }

    public final void clear$ui_release(Object target) {
        this.observer.clear(target);
    }

    public final void clearInvalidObservations$ui_release() {
        this.observer.clearIf(OwnerSnapshotObserver$clearInvalidObservations$1.INSTANCE);
    }

    public final void observeLayoutModifierSnapshotReads$ui_release(LayoutNode node, boolean affectsLookahead, r7.a<t0> block) {
        if (!affectsLookahead || node.getLookaheadRoot() == null) {
            observeReads$ui_release(node, this.onCommitAffectingLayoutModifier, block);
        } else {
            observeReads$ui_release(node, this.onCommitAffectingLayoutModifierInLookahead, block);
        }
    }

    public final void observeLayoutSnapshotReads$ui_release(LayoutNode node, boolean affectsLookahead, r7.a<t0> block) {
        if (!affectsLookahead || node.getLookaheadRoot() == null) {
            observeReads$ui_release(node, this.onCommitAffectingLayout, block);
        } else {
            observeReads$ui_release(node, this.onCommitAffectingLookahead, block);
        }
    }

    public final void observeMeasureSnapshotReads$ui_release(LayoutNode node, boolean affectsLookahead, r7.a<t0> block) {
        if (!affectsLookahead || node.getLookaheadRoot() == null) {
            observeReads$ui_release(node, this.onCommitAffectingMeasure, block);
        } else {
            observeReads$ui_release(node, this.onCommitAffectingLookaheadMeasure, block);
        }
    }

    public final <T extends OwnerScope> void observeReads$ui_release(T target, l<? super T, t0> onChanged, r7.a<t0> block) {
        this.observer.observeReads(target, onChanged, block);
    }

    public final void observeSemanticsReads$ui_release(LayoutNode node, r7.a<t0> block) {
        observeReads$ui_release(node, this.onCommitAffectingSemantics, block);
    }

    public final void startObserving$ui_release() {
        this.observer.start();
    }

    public final void stopObserving$ui_release() {
        this.observer.stop();
        this.observer.clear();
    }
}
