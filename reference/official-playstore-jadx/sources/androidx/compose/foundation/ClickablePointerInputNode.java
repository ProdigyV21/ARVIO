package androidx.compose.foundation;

import androidx.compose.foundation.AbstractClickableNode;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import d7.d;
import f7.e;
import f7.j;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0014\u0010\u000e\u001a\u00020\u0007*\u00020\rH\u0094@¢\u0006\u0004\b\u000e\u0010\u000fJ+\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/ClickablePointerInputNode;", "Landroidx/compose/foundation/AbstractClickablePointerInputNode;", "", "enabled", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/foundation/AbstractClickableNode$InteractionData;", "interactionData", "<init>", "(ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/a;Landroidx/compose/foundation/AbstractClickableNode$InteractionData;)V", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "pointerInput", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Ld7/d;)Ljava/lang/Object;", "update", "(ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/a;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ClickablePointerInputNode extends AbstractClickablePointerInputNode {

    /* JADX INFO: renamed from: androidx.compose.foundation.ClickablePointerInputNode$pointerInput$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/geometry/Offset;", "offset", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/PressGestureScope;Landroidx/compose/ui/geometry/Offset;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.ClickablePointerInputNode$pointerInput$2", f = "Clickable.kt", l = {984}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements q<PressGestureScope, Offset, d<? super t0>, Object> {
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        int label;

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return m224invoked4ec7I((PressGestureScope) obj, ((Offset) obj2).getPackedValue(), (d) obj3);
        }

        /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m224invoked4ec7I(PressGestureScope pressGestureScope, long j10, d<? super t0> dVar) {
            AnonymousClass2 anonymousClass2 = ClickablePointerInputNode.this.new AnonymousClass2(dVar);
            anonymousClass2.L$0 = pressGestureScope;
            anonymousClass2.J$0 = j10;
            return anonymousClass2.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PressGestureScope pressGestureScope = (PressGestureScope) this.L$0;
                long j10 = this.J$0;
                if (ClickablePointerInputNode.this.getEnabled()) {
                    ClickablePointerInputNode clickablePointerInputNode = ClickablePointerInputNode.this;
                    this.label = 1;
                    Object objM153handlePressInteractiond4ec7I = clickablePointerInputNode.m153handlePressInteractiond4ec7I(pressGestureScope, j10, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objM153handlePressInteractiond4ec7I == aVar) {
                        return aVar;
                    }
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.ClickablePointerInputNode$pointerInput$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements l<Offset, t0> {
        public AnonymousClass3() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m225invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m225invokek4lQ0M(long j10) {
            if (ClickablePointerInputNode.this.getEnabled()) {
                ClickablePointerInputNode.this.getOnClick().invoke();
            }
        }
    }

    public ClickablePointerInputNode(boolean z, MutableInteractionSource mutableInteractionSource, r7.a<t0> aVar, AbstractClickableNode.InteractionData interactionData) {
        super(z, mutableInteractionSource, aVar, interactionData, null);
    }

    @Override // androidx.compose.foundation.AbstractClickablePointerInputNode
    public Object pointerInput(PointerInputScope pointerInputScope, d<? super t0> dVar) {
        AbstractClickableNode.InteractionData interactionData = getInteractionData();
        long jM5850getCenterozmzZPI = IntSizeKt.m5850getCenterozmzZPI(pointerInputScope.getBoundsSize());
        interactionData.m152setCentreOffsetk4lQ0M(OffsetKt.Offset(IntOffset.m5802getXimpl(jM5850getCenterozmzZPI), IntOffset.m5803getYimpl(jM5850getCenterozmzZPI)));
        Object objDetectTapAndPress = TapGestureDetectorKt.detectTapAndPress(pointerInputScope, new AnonymousClass2(null), new AnonymousClass3(), dVar);
        return objDetectTapAndPress == e7.a.f15033i ? objDetectTapAndPress : t0.f22605a;
    }

    public final void update(boolean enabled, MutableInteractionSource interactionSource, r7.a<t0> onClick) {
        setEnabled(enabled);
        setOnClick(onClick);
        setInteractionSource(interactionSource);
    }
}
