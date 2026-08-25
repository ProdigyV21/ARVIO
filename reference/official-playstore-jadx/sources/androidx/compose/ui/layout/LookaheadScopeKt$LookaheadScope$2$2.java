package androidx.compose.ui.layout;

import androidx.compose.ui.node.LayoutNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/ui/layout/LookaheadScopeImpl;", "scope", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/layout/LookaheadScopeImpl;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class LookaheadScopeKt$LookaheadScope$2$2 extends r implements p<LayoutNode, LookaheadScopeImpl, t0> {
    public static final LookaheadScopeKt$LookaheadScope$2$2 INSTANCE = new LookaheadScopeKt$LookaheadScope$2$2();

    /* JADX INFO: renamed from: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$2$2$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/LayoutCoordinates;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements r7.a<LayoutCoordinates> {
        final /* synthetic */ LayoutNode $this_set;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LayoutNode layoutNode) {
            super(0);
            this.$this_set = layoutNode;
        }

        @Override // r7.a
        public final LayoutCoordinates invoke() {
            return this.$this_set.getParent$ui_release().getInnerCoordinator$ui_release().getCoordinates();
        }
    }

    public LookaheadScopeKt$LookaheadScope$2$2() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((LayoutNode) obj, (LookaheadScopeImpl) obj2);
        return t0.f22605a;
    }

    public final void invoke(LayoutNode layoutNode, LookaheadScopeImpl lookaheadScopeImpl) {
        lookaheadScopeImpl.setScopeCoordinates(new AnonymousClass1(layoutNode));
    }
}
