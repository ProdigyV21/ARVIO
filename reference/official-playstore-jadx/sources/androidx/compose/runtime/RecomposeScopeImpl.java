package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionObserverHandle;
import androidx.compose.runtime.tooling.RecomposeScopeObserver;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import r7.p;
import t.y;
import t.z;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\"\b\u0000\u0018\u0000 e2\u00020\u00012\u00020\u0002:\u0001eB\u0011\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\t¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0019\u0010\u0006J\u000f\u0010\u001a\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001a\u0010\u0018J)\u0010\u001e\u001a\u00020\t2\u0018\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\t0\u001bH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010!\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001c¢\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\t¢\u0006\u0004\b#\u0010\u0018J\u0015\u0010&\u001a\u00020%2\u0006\u0010$\u001a\u00020\u0012¢\u0006\u0004\b&\u0010'J#\u0010)\u001a\u00020\t2\n\u0010$\u001a\u0006\u0012\u0002\b\u00030(2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b)\u0010*J\u0017\u0010,\u001a\u00020%2\b\u0010+\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b,\u0010'J\r\u0010-\u001a\u00020\t¢\u0006\u0004\b-\u0010\u0018J#\u00100\u001a\u0010\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\t\u0018\u00010.2\u0006\u0010 \u001a\u00020\u001c¢\u0006\u0004\b0\u00101J1\u00104\u001a\u00020%*\u0006\u0012\u0002\b\u00030(2\u0018\u00103\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030(\u0012\u0006\u0012\u0004\u0018\u00010\u001202H\u0002¢\u0006\u0004\b4\u00105R\u0016\u00106\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0004\u00108R$\u0010:\u001a\u0004\u0018\u0001098\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R*\u0010\u001d\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\t\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010@R\u001e\u0010\r\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0083\u000e¢\u0006\f\n\u0004\b\r\u0010A\u0012\u0004\bB\u0010\u0018R\u0016\u0010C\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bC\u00107R\u001e\u0010E\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010D8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u0010FR*\u0010G\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030(\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u0001028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u0011\u0010K\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\bI\u0010JR\u0011\u0010M\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\bL\u0010JR$\u0010Q\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020%8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bN\u0010J\"\u0004\bO\u0010PR$\u0010T\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020%8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bR\u0010J\"\u0004\bS\u0010PR$\u0010W\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020%8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bU\u0010J\"\u0004\bV\u0010PR$\u0010Z\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020%8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bX\u0010J\"\u0004\bY\u0010PR$\u0010]\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020%8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b[\u0010J\"\u0004\b\\\u0010PR$\u0010`\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020%8@@BX\u0080\u000e¢\u0006\f\u001a\u0004\b^\u0010J\"\u0004\b_\u0010PR\u0011\u0010a\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\ba\u0010JR$\u0010d\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020%8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\bb\u0010J\"\u0004\bc\u0010P¨\u0006f"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/compose/runtime/ScopeUpdateScope;", "Landroidx/compose/runtime/RecomposeScope;", "Landroidx/compose/runtime/RecomposeScopeOwner;", "owner", "<init>", "(Landroidx/compose/runtime/RecomposeScopeOwner;)V", "Landroidx/compose/runtime/Composer;", "composer", "Lx6/t0;", "compose", "(Landroidx/compose/runtime/Composer;)V", "Landroidx/compose/runtime/tooling/RecomposeScopeObserver;", "observer", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observe$runtime_release", "(Landroidx/compose/runtime/tooling/RecomposeScopeObserver;)Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observe", "", "value", "Landroidx/compose/runtime/InvalidationResult;", "invalidateForResult", "(Ljava/lang/Object;)Landroidx/compose/runtime/InvalidationResult;", "release", "()V", "adoptedBy", "invalidate", "Lkotlin/Function2;", "", "block", "updateScope", "(Lr7/p;)V", "token", TtmlNode.START, "(I)V", "scopeSkipped", "instance", "", "recordRead", "(Ljava/lang/Object;)Z", "Landroidx/compose/runtime/DerivedState;", "recordDerivedStateValue", "(Landroidx/compose/runtime/DerivedState;Ljava/lang/Object;)V", "instances", "isInvalidFor", "rereadTrackedInstances", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", TtmlNode.END, "(I)Lr7/l;", "Lt/z;", "dependencies", "checkDerivedStateChanged", "(Landroidx/compose/runtime/DerivedState;Lt/z;)Z", "flags", "I", "Landroidx/compose/runtime/RecomposeScopeOwner;", "Landroidx/compose/runtime/Anchor;", LinkHeader.Parameters.Anchor, "Landroidx/compose/runtime/Anchor;", "getAnchor", "()Landroidx/compose/runtime/Anchor;", "setAnchor", "(Landroidx/compose/runtime/Anchor;)V", "Lr7/p;", "Landroidx/compose/runtime/tooling/RecomposeScopeObserver;", "getObserver$annotations", "currentToken", "Lt/y;", "trackedInstances", "Lt/y;", "trackedDependencies", "Lt/z;", "getValid", "()Z", "valid", "getCanRecompose", "canRecompose", "getUsed", "setUsed", "(Z)V", "used", "getDefaultsInScope", "setDefaultsInScope", "defaultsInScope", "getDefaultsInvalid", "setDefaultsInvalid", "defaultsInvalid", "getRequiresRecompose", "setRequiresRecompose", "requiresRecompose", "getForcedRecompose", "setForcedRecompose", "forcedRecompose", "getSkipped$runtime_release", "setSkipped", "skipped", "isConditional", "getRereading", "setRereading", "rereading", "Companion", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RecomposeScopeImpl implements ScopeUpdateScope, RecomposeScope {
    private Anchor anchor;
    private p<? super Composer, ? super Integer, t0> block;
    private int currentToken;
    private int flags;
    private RecomposeScopeObserver observer;
    private RecomposeScopeOwner owner;
    private z trackedDependencies;
    private y trackedInstances;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0004\b\f\u0010\rJ%\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u000f2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0000¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl$Companion;", "", "<init>", "()V", "Landroidx/compose/runtime/SlotWriter;", "slots", "", "Landroidx/compose/runtime/Anchor;", "anchors", "Landroidx/compose/runtime/RecomposeScopeOwner;", "newOwner", "Lx6/t0;", "adoptAnchoredScopes$runtime_release", "(Landroidx/compose/runtime/SlotWriter;Ljava/util/List;Landroidx/compose/runtime/RecomposeScopeOwner;)V", "adoptAnchoredScopes", "Landroidx/compose/runtime/SlotTable;", "", "hasAnchoredRecomposeScopes$runtime_release", "(Landroidx/compose/runtime/SlotTable;Ljava/util/List;)Z", "hasAnchoredRecomposeScopes", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final void adoptAnchoredScopes$runtime_release(SlotWriter slots, List<Anchor> anchors, RecomposeScopeOwner newOwner) {
            if (anchors.isEmpty()) {
                return;
            }
            int size = anchors.size();
            for (int i10 = 0; i10 < size; i10++) {
                Object objSlot = slots.slot(anchors.get(i10), 0);
                RecomposeScopeImpl recomposeScopeImpl = objSlot instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) objSlot : null;
                if (recomposeScopeImpl != null) {
                    recomposeScopeImpl.adoptedBy(newOwner);
                }
            }
        }

        public final boolean hasAnchoredRecomposeScopes$runtime_release(SlotTable slots, List<Anchor> anchors) {
            if (!anchors.isEmpty()) {
                int size = anchors.size();
                for (int i10 = 0; i10 < size; i10++) {
                    Anchor anchor = anchors.get(i10);
                    if (slots.ownsAnchor(anchor) && (slots.slot$runtime_release(slots.anchorIndex(anchor), 0) instanceof RecomposeScopeImpl)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private Companion() {
        }
    }

    public RecomposeScopeImpl(RecomposeScopeOwner recomposeScopeOwner) {
        this.owner = recomposeScopeOwner;
    }

    private final boolean checkDerivedStateChanged(DerivedState<?> derivedState, z zVar) {
        SnapshotMutationPolicy<?> policy = derivedState.getPolicy();
        if (policy == null) {
            policy = SnapshotStateKt.structuralEqualityPolicy();
        }
        return !policy.equivalent(derivedState.getCurrentRecord().getCurrentValue(), zVar.e(derivedState));
    }

    private static /* synthetic */ void getObserver$annotations() {
    }

    private final boolean getRereading() {
        return (this.flags & 32) != 0;
    }

    private final void setRereading(boolean z) {
        if (z) {
            this.flags |= 32;
        } else {
            this.flags &= -33;
        }
    }

    private final void setSkipped(boolean z) {
        if (z) {
            this.flags |= 16;
        } else {
            this.flags &= -17;
        }
    }

    public final void adoptedBy(RecomposeScopeOwner owner) {
        this.owner = owner;
    }

    public final void compose(Composer composer) {
        t0 t0Var;
        p<? super Composer, ? super Integer, t0> pVar = this.block;
        RecomposeScopeObserver recomposeScopeObserver = this.observer;
        if (recomposeScopeObserver != null && pVar != null) {
            recomposeScopeObserver.onBeginScopeComposition(this);
            try {
                pVar.invoke(composer, 1);
                return;
            } finally {
                recomposeScopeObserver.onEndScopeComposition(this);
            }
        }
        if (pVar != null) {
            pVar.invoke(composer, 1);
            t0Var = t0.f22605a;
        } else {
            t0Var = null;
        }
        if (t0Var == null) {
            throw new IllegalStateException("Invalid restart scope");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final r7.l<androidx.compose.runtime.Composition, x6.t0> end(int r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            t.y r2 = r0.trackedInstances
            r3 = 0
            if (r2 == 0) goto L5b
            boolean r4 = r0.getSkipped$runtime_release()
            if (r4 != 0) goto L5b
            java.lang.Object[] r4 = r2.f21805b
            int[] r5 = r2.f21806c
            long[] r6 = r2.f21804a
            int r7 = r6.length
            int r7 = r7 + (-2)
            if (r7 < 0) goto L5b
            r8 = 0
            r9 = r8
        L1c:
            r10 = r6[r9]
            long r12 = ~r10
            r14 = 7
            long r12 = r12 << r14
            long r12 = r12 & r10
            r14 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r12 = r12 & r14
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 == 0) goto L56
            int r12 = r9 - r7
            int r12 = ~r12
            int r12 = r12 >>> 31
            r13 = 8
            int r12 = 8 - r12
            r14 = r8
        L36:
            if (r14 >= r12) goto L54
            r15 = 255(0xff, double:1.26E-321)
            long r15 = r15 & r10
            r17 = 128(0x80, double:6.3E-322)
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 >= 0) goto L50
            int r15 = r9 << 3
            int r15 = r15 + r14
            r16 = r4[r15]
            r15 = r5[r15]
            if (r15 == r1) goto L50
            androidx.compose.runtime.RecomposeScopeImpl$end$1$2 r3 = new androidx.compose.runtime.RecomposeScopeImpl$end$1$2
            r3.<init>(r0, r1, r2)
            return r3
        L50:
            long r10 = r10 >> r13
            int r14 = r14 + 1
            goto L36
        L54:
            if (r12 != r13) goto L5b
        L56:
            if (r9 == r7) goto L5b
            int r9 = r9 + 1
            goto L1c
        L5b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.end(int):r7.l");
    }

    public final Anchor getAnchor() {
        return this.anchor;
    }

    public final boolean getCanRecompose() {
        return this.block != null;
    }

    public final boolean getDefaultsInScope() {
        return (this.flags & 2) != 0;
    }

    public final boolean getDefaultsInvalid() {
        return (this.flags & 4) != 0;
    }

    public final boolean getForcedRecompose() {
        return (this.flags & 64) != 0;
    }

    public final boolean getRequiresRecompose() {
        return (this.flags & 8) != 0;
    }

    public final boolean getSkipped$runtime_release() {
        return (this.flags & 16) != 0;
    }

    public final boolean getUsed() {
        return (this.flags & 1) != 0;
    }

    public final boolean getValid() {
        if (this.owner != null) {
            Anchor anchor = this.anchor;
            if (anchor != null ? anchor.getValid() : false) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.runtime.RecomposeScope
    public void invalidate() {
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        if (recomposeScopeOwner != null) {
            recomposeScopeOwner.invalidate(this, null);
        }
    }

    public final InvalidationResult invalidateForResult(Object value) {
        InvalidationResult invalidationResultInvalidate;
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        return (recomposeScopeOwner == null || (invalidationResultInvalidate = recomposeScopeOwner.invalidate(this, value)) == null) ? InvalidationResult.IGNORED : invalidationResultInvalidate;
    }

    public final boolean isConditional() {
        return this.trackedDependencies != null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0065, code lost:
    
        return true;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isInvalidFor(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 1
            if (r1 != 0) goto L8
            return r2
        L8:
            t.z r3 = r0.trackedDependencies
            if (r3 != 0) goto Ld
            return r2
        Ld:
            boolean r4 = r1 instanceof androidx.compose.runtime.DerivedState
            if (r4 == 0) goto L18
            androidx.compose.runtime.DerivedState r1 = (androidx.compose.runtime.DerivedState) r1
            boolean r1 = r0.checkDerivedStateChanged(r1, r3)
            return r1
        L18:
            boolean r4 = r1 instanceof t.j0
            if (r4 == 0) goto L72
            t.j0 r1 = (t.j0) r1
            boolean r4 = r1.c()
            r5 = 0
            if (r4 == 0) goto L71
            java.lang.Object[] r4 = r1.f21836b
            long[] r1 = r1.f21835a
            int r6 = r1.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L71
            r7 = r5
        L2f:
            r8 = r1[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L6c
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r5
        L49:
            if (r12 >= r10) goto L6a
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L66
            int r13 = r7 << 3
            int r13 = r13 + r12
            r13 = r4[r13]
            boolean r14 = r13 instanceof androidx.compose.runtime.DerivedState
            if (r14 == 0) goto L65
            androidx.compose.runtime.DerivedState r13 = (androidx.compose.runtime.DerivedState) r13
            boolean r13 = r0.checkDerivedStateChanged(r13, r3)
            if (r13 == 0) goto L66
        L65:
            return r2
        L66:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L49
        L6a:
            if (r10 != r11) goto L71
        L6c:
            if (r7 == r6) goto L71
            int r7 = r7 + 1
            goto L2f
        L71:
            return r5
        L72:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.isInvalidFor(java.lang.Object):boolean");
    }

    public final CompositionObserverHandle observe$runtime_release(final RecomposeScopeObserver observer) {
        synchronized (RecomposeScopeImplKt.callbackLock) {
            this.observer = observer;
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.RecomposeScopeImpl$observe$2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object obj = RecomposeScopeImplKt.callbackLock;
                RecomposeScopeImpl recomposeScopeImpl = this.this$0;
                RecomposeScopeObserver recomposeScopeObserver = observer;
                synchronized (obj) {
                    if (kotlin.jvm.internal.p.a(recomposeScopeImpl.observer, recomposeScopeObserver)) {
                        recomposeScopeImpl.observer = null;
                    }
                }
            }
        };
    }

    public final void recordDerivedStateValue(DerivedState<?> instance, Object value) {
        z zVar = this.trackedDependencies;
        if (zVar == null) {
            zVar = new z();
            this.trackedDependencies = zVar;
        }
        zVar.k(instance, value);
    }

    public final boolean recordRead(Object instance) {
        int i10;
        if (getRereading()) {
            return false;
        }
        y yVar = this.trackedInstances;
        if (yVar == null) {
            yVar = new y();
            this.trackedInstances = yVar;
        }
        int i11 = this.currentToken;
        int iC = yVar.c(instance);
        if (iC < 0) {
            iC = ~iC;
            i10 = -1;
        } else {
            i10 = yVar.f21806c[iC];
        }
        yVar.f21805b[iC] = instance;
        yVar.f21806c[iC] = i11;
        return i10 == this.currentToken;
    }

    public final void release() {
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        if (recomposeScopeOwner != null) {
            recomposeScopeOwner.recomposeScopeReleased(this);
        }
        this.owner = null;
        this.trackedInstances = null;
        this.trackedDependencies = null;
        RecomposeScopeObserver recomposeScopeObserver = this.observer;
        if (recomposeScopeObserver != null) {
            recomposeScopeObserver.onScopeDisposed(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void rereadTrackedInstances() {
        /*
            r17 = this;
            r1 = r17
            androidx.compose.runtime.RecomposeScopeOwner r0 = r1.owner
            if (r0 == 0) goto L60
            t.y r2 = r1.trackedInstances
            if (r2 == 0) goto L60
            r3 = 1
            r1.setRereading(r3)
            r3 = 0
            java.lang.Object[] r4 = r2.f21805b     // Catch: java.lang.Throwable -> L4b
            int[] r5 = r2.f21806c     // Catch: java.lang.Throwable -> L4b
            long[] r2 = r2.f21804a     // Catch: java.lang.Throwable -> L4b
            int r6 = r2.length     // Catch: java.lang.Throwable -> L4b
            int r6 = r6 + (-2)
            if (r6 < 0) goto L58
            r7 = r3
        L1b:
            r8 = r2[r7]     // Catch: java.lang.Throwable -> L4b
            long r10 = ~r8     // Catch: java.lang.Throwable -> L4b
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L53
            int r10 = r7 - r6
            int r10 = ~r10     // Catch: java.lang.Throwable -> L4b
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r3
        L35:
            if (r12 >= r10) goto L51
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L4d
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r4[r13]     // Catch: java.lang.Throwable -> L4b
            r13 = r5[r13]     // Catch: java.lang.Throwable -> L4b
            r0.recordReadOf(r14)     // Catch: java.lang.Throwable -> L4b
            goto L4d
        L4b:
            r0 = move-exception
            goto L5c
        L4d:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L35
        L51:
            if (r10 != r11) goto L58
        L53:
            if (r7 == r6) goto L58
            int r7 = r7 + 1
            goto L1b
        L58:
            r1.setRereading(r3)
            return
        L5c:
            r1.setRereading(r3)
            throw r0
        L60:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.rereadTrackedInstances():void");
    }

    public final void scopeSkipped() {
        setSkipped(true);
    }

    public final void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public final void setDefaultsInScope(boolean z) {
        if (z) {
            this.flags |= 2;
        } else {
            this.flags &= -3;
        }
    }

    public final void setDefaultsInvalid(boolean z) {
        if (z) {
            this.flags |= 4;
        } else {
            this.flags &= -5;
        }
    }

    public final void setForcedRecompose(boolean z) {
        if (z) {
            this.flags |= 64;
        } else {
            this.flags &= -65;
        }
    }

    public final void setRequiresRecompose(boolean z) {
        if (z) {
            this.flags |= 8;
        } else {
            this.flags &= -9;
        }
    }

    public final void setUsed(boolean z) {
        if (z) {
            this.flags |= 1;
        } else {
            this.flags &= -2;
        }
    }

    public final void start(int token) {
        this.currentToken = token;
        setSkipped(false);
    }

    @Override // androidx.compose.runtime.ScopeUpdateScope
    public void updateScope(p<? super Composer, ? super Integer, t0> block) {
        this.block = block;
    }
}
