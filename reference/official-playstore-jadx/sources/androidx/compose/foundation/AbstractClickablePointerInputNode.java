package androidx.compose.foundation;

import androidx.compose.foundation.AbstractClickableNode;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.modifier.ModifierLocal;
import androidx.compose.ui.modifier.ModifierLocalMap;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.f;
import d7.d;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B1\b\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0014\u0010\u0011\u001a\u00020\n*\u00020\u0010H¤@¢\u0006\u0004\b\u0011\u0010\u0012J*\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010#\u001a\u00020\n*\u00020\u001e2\u0006\u0010 \u001a\u00020\u001fH\u0084@ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u000f\u0010$\u001a\u00020\nH\u0004¢\u0006\u0004\b$\u0010\u001dR\"\u0010\u0006\u001a\u00020\u00058\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\b\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R(\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u0010\r\u001a\u00020\f8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\r\u00104\u001a\u0004\b5\u00106R\u001a\u00107\u001a\b\u0012\u0004\u0012\u00020\u00050\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u0010/R\u0014\u00109\u001a\u0002088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:\u0082\u0001\u0002;<\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006="}, d2 = {"Landroidx/compose/foundation/AbstractClickablePointerInputNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "", "enabled", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/foundation/AbstractClickableNode$InteractionData;", "interactionData", "<init>", "(ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/a;Landroidx/compose/foundation/AbstractClickableNode$InteractionData;)V", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "pointerInput", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "pass", "Landroidx/compose/ui/unit/IntSize;", "bounds", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onPointerEvent", "onCancelPointerInput", "()V", "Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/geometry/Offset;", "offset", "handlePressInteraction-d-4ec7I", "(Landroidx/compose/foundation/gestures/PressGestureScope;JLd7/d;)Ljava/lang/Object;", "handlePressInteraction", "resetPointerInputHandler", "Z", "getEnabled", "()Z", "setEnabled", "(Z)V", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getInteractionSource", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "setInteractionSource", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;)V", "Lr7/a;", "getOnClick", "()Lr7/a;", "setOnClick", "(Lr7/a;)V", "Landroidx/compose/foundation/AbstractClickableNode$InteractionData;", "getInteractionData", "()Landroidx/compose/foundation/AbstractClickableNode$InteractionData;", "delayPressInteraction", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "Landroidx/compose/foundation/ClickablePointerInputNode;", "Landroidx/compose/foundation/CombinedClickablePointerInputNode;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
abstract class AbstractClickablePointerInputNode extends DelegatingNode implements ModifierLocalModifierNode, CompositionLocalConsumerModifierNode, PointerInputModifierNode {
    private final r7.a<Boolean> delayPressInteraction;
    private boolean enabled;
    private final AbstractClickableNode.InteractionData interactionData;
    private MutableInteractionSource interactionSource;
    private r7.a<t0> onClick;
    private final SuspendingPointerInputModifierNode pointerInputNode;

    public /* synthetic */ AbstractClickablePointerInputNode(boolean z, MutableInteractionSource mutableInteractionSource, r7.a aVar, AbstractClickableNode.InteractionData interactionData, h hVar) {
        this(z, mutableInteractionSource, aVar, interactionData);
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode, androidx.compose.ui.modifier.ModifierLocalReadScope
    public final /* synthetic */ Object getCurrent(ModifierLocal modifierLocal) {
        return androidx.compose.ui.modifier.b.a(this, modifierLocal);
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final AbstractClickableNode.InteractionData getInteractionData() {
        return this.interactionData;
    }

    public final MutableInteractionSource getInteractionSource() {
        return this.interactionSource;
    }

    public final r7.a<t0> getOnClick() {
        return this.onClick;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public final /* synthetic */ ModifierLocalMap getProvidedValues() {
        return androidx.compose.ui.modifier.b.b(this);
    }

    /* JADX INFO: renamed from: handlePressInteraction-d-4ec7I, reason: not valid java name */
    public final Object m153handlePressInteractiond4ec7I(PressGestureScope pressGestureScope, long j10, d<? super t0> dVar) {
        Object objM221handlePressInteractionEPk0efs;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        return (mutableInteractionSource == null || (objM221handlePressInteractionEPk0efs = ClickableKt.m221handlePressInteractionEPk0efs(pressGestureScope, j10, mutableInteractionSource, this.interactionData, this.delayPressInteraction, dVar)) != e7.a.f15033i) ? t0.f22605a : objM221handlePressInteractionEPk0efs;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ boolean interceptOutOfBoundsChildEvents() {
        return f.a(this);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        this.pointerInputNode.onCancelPointerInput();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ void onDensityChange() {
        f.b(this);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo148onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        this.pointerInputNode.mo148onPointerEventH0pRuoY(pointerEvent, pass, bounds);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ void onViewConfigurationChange() {
        f.c(this);
    }

    public abstract Object pointerInput(PointerInputScope pointerInputScope, d<? super t0> dVar);

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public final /* synthetic */ void provide(ModifierLocal modifierLocal, Object obj) {
        androidx.compose.ui.modifier.b.c(this, modifierLocal, obj);
    }

    public final void resetPointerInputHandler() {
        this.pointerInputNode.resetPointerInputHandler();
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final void setInteractionSource(MutableInteractionSource mutableInteractionSource) {
        this.interactionSource = mutableInteractionSource;
    }

    public final void setOnClick(r7.a<t0> aVar) {
        this.onClick = aVar;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ boolean sharePointerInputWithSiblings() {
        return f.d(this);
    }

    private AbstractClickablePointerInputNode(boolean z, MutableInteractionSource mutableInteractionSource, r7.a<t0> aVar, AbstractClickableNode.InteractionData interactionData) {
        this.enabled = z;
        this.interactionSource = mutableInteractionSource;
        this.onClick = aVar;
        this.interactionData = interactionData;
        this.delayPressInteraction = new AbstractClickablePointerInputNode$delayPressInteraction$1(this);
        this.pointerInputNode = (SuspendingPointerInputModifierNode) delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new AbstractClickablePointerInputNode$pointerInputNode$1(this, null)));
    }
}
