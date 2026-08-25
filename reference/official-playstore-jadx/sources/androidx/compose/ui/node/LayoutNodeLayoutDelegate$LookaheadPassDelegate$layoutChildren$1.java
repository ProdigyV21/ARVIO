package androidx.compose.ui.node;

import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class LayoutNodeLayoutDelegate$LookaheadPassDelegate$layoutChildren$1 extends r implements r7.a<t0> {
    final /* synthetic */ LookaheadDelegate $lookaheadDelegate;
    final /* synthetic */ LayoutNodeLayoutDelegate.LookaheadPassDelegate this$0;
    final /* synthetic */ LayoutNodeLayoutDelegate this$1;

    /* JADX INFO: renamed from: androidx.compose.ui.node.LayoutNodeLayoutDelegate$LookaheadPassDelegate$layoutChildren$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/node/AlignmentLinesOwner;", "child", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/AlignmentLinesOwner;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<AlignmentLinesOwner, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AlignmentLinesOwner) obj);
            return t0.f22605a;
        }

        public final void invoke(AlignmentLinesOwner alignmentLinesOwner) {
            alignmentLinesOwner.getAlignmentLines().setUsedDuringParentLayout$ui_release(false);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.node.LayoutNodeLayoutDelegate$LookaheadPassDelegate$layoutChildren$1$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/node/AlignmentLinesOwner;", "child", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/AlignmentLinesOwner;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements l<AlignmentLinesOwner, t0> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AlignmentLinesOwner) obj);
            return t0.f22605a;
        }

        public final void invoke(AlignmentLinesOwner alignmentLinesOwner) {
            alignmentLinesOwner.getAlignmentLines().setPreviousUsedDuringParentLayout$ui_release(alignmentLinesOwner.getAlignmentLines().getUsedDuringParentLayout());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LayoutNodeLayoutDelegate$LookaheadPassDelegate$layoutChildren$1(LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate, LookaheadDelegate lookaheadDelegate, LayoutNodeLayoutDelegate layoutNodeLayoutDelegate) {
        super(0);
        this.this$0 = lookaheadPassDelegate;
        this.$lookaheadDelegate = lookaheadDelegate;
        this.this$1 = layoutNodeLayoutDelegate;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m4775invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m4775invoke() {
        this.this$0.clearPlaceOrder();
        this.this$0.forEachChildAlignmentLinesOwner(AnonymousClass1.INSTANCE);
        LookaheadDelegate lookaheadDelegate = this.this$0.getInnerCoordinator().getLookaheadDelegate();
        if (lookaheadDelegate != null) {
            boolean isPlacingForAlignment = lookaheadDelegate.getIsPlacingForAlignment();
            List<LayoutNode> children$ui_release = this.this$1.layoutNode.getChildren$ui_release();
            int size = children$ui_release.size();
            for (int i10 = 0; i10 < size; i10++) {
                LookaheadDelegate lookaheadDelegate2 = children$ui_release.get(i10).getOuterCoordinator$ui_release().getLookaheadDelegate();
                if (lookaheadDelegate2 != null) {
                    lookaheadDelegate2.setPlacingForAlignment$ui_release(isPlacingForAlignment);
                }
            }
        }
        this.$lookaheadDelegate.getMeasureResult$ui_release().placeChildren();
        LookaheadDelegate lookaheadDelegate3 = this.this$0.getInnerCoordinator().getLookaheadDelegate();
        if (lookaheadDelegate3 != null) {
            lookaheadDelegate3.getIsPlacingForAlignment();
            List<LayoutNode> children$ui_release2 = this.this$1.layoutNode.getChildren$ui_release();
            int size2 = children$ui_release2.size();
            for (int i11 = 0; i11 < size2; i11++) {
                LookaheadDelegate lookaheadDelegate4 = children$ui_release2.get(i11).getOuterCoordinator$ui_release().getLookaheadDelegate();
                if (lookaheadDelegate4 != null) {
                    lookaheadDelegate4.setPlacingForAlignment$ui_release(false);
                }
            }
        }
        this.this$0.checkChildrenPlaceOrderForUpdates();
        this.this$0.forEachChildAlignmentLinesOwner(AnonymousClass4.INSTANCE);
    }
}
