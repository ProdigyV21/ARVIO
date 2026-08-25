package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.PinnableContainer;
import androidx.compose.ui.layout.PinnableContainerKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u0005J\u000f\u0010\u000f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000f\u0010\u0005R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/FocusablePinnableContainerNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "<init>", "()V", "Landroidx/compose/ui/layout/PinnableContainer;", "retrievePinnableContainer", "()Landroidx/compose/ui/layout/PinnableContainer;", "", "focused", "Lx6/t0;", "setFocus", "(Z)V", "onReset", "onObservedReadsChanged", "Landroidx/compose/ui/layout/PinnableContainer$PinnedHandle;", "pinnedHandle", "Landroidx/compose/ui/layout/PinnableContainer$PinnedHandle;", "isFocused", "Z", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class FocusablePinnableContainerNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, ObserverModifierNode {
    private boolean isFocused;
    private PinnableContainer.PinnedHandle pinnedHandle;

    /* JADX INFO: renamed from: androidx.compose.foundation.FocusablePinnableContainerNode$retrievePinnableContainer$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.a<t0> {
        final /* synthetic */ k0 $container;
        final /* synthetic */ FocusablePinnableContainerNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(k0 k0Var, FocusablePinnableContainerNode focusablePinnableContainerNode) {
            super(0);
            this.$container = k0Var;
            this.this$0 = focusablePinnableContainerNode;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m237invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m237invoke() {
            this.$container.f19746i = CompositionLocalConsumerModifierNodeKt.currentValueOf(this.this$0, PinnableContainerKt.getLocalPinnableContainer());
        }
    }

    private final PinnableContainer retrievePinnableContainer() {
        k0 k0Var = new k0();
        ObserverModifierNodeKt.observeReads(this, new AnonymousClass1(k0Var, this));
        return (PinnableContainer) k0Var.f19746i;
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        PinnableContainer pinnableContainerRetrievePinnableContainer = retrievePinnableContainer();
        if (this.isFocused) {
            PinnableContainer.PinnedHandle pinnedHandle = this.pinnedHandle;
            if (pinnedHandle != null) {
                pinnedHandle.release();
            }
            this.pinnedHandle = pinnableContainerRetrievePinnableContainer != null ? pinnableContainerRetrievePinnableContainer.pin() : null;
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onReset() {
        PinnableContainer.PinnedHandle pinnedHandle = this.pinnedHandle;
        if (pinnedHandle != null) {
            pinnedHandle.release();
        }
        this.pinnedHandle = null;
    }

    public final void setFocus(boolean focused) {
        if (focused) {
            PinnableContainer pinnableContainerRetrievePinnableContainer = retrievePinnableContainer();
            this.pinnedHandle = pinnableContainerRetrievePinnableContainer != null ? pinnableContainerRetrievePinnableContainer.pin() : null;
        } else {
            PinnableContainer.PinnedHandle pinnedHandle = this.pinnedHandle;
            if (pinnedHandle != null) {
                pinnedHandle.release();
            }
            this.pinnedHandle = null;
        }
        this.isFocused = focused;
    }
}
