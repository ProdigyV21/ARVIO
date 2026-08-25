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
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import d7.d;
import f7.e;
import f7.j;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0004\b\r\u0010\u000eJ\u0014\u0010\u0010\u001a\u00020\u0007*\u00020\u000fH\u0094@¢\u0006\u0004\b\u0010\u0010\u0011JK\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0004\b\u0012\u0010\u0013R\u001e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\u0014R\u001e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/CombinedClickablePointerInputNode;", "Landroidx/compose/foundation/AbstractClickablePointerInputNode;", "", "enabled", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/foundation/AbstractClickableNode$InteractionData;", "interactionData", "onLongClick", "onDoubleClick", "<init>", "(ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/a;Landroidx/compose/foundation/AbstractClickableNode$InteractionData;Lr7/a;Lr7/a;)V", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "pointerInput", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Ld7/d;)Ljava/lang/Object;", "update", "(ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/a;Lr7/a;Lr7/a;)V", "Lr7/a;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class CombinedClickablePointerInputNode extends AbstractClickablePointerInputNode {
    private r7.a<t0> onDoubleClick;
    private r7.a<t0> onLongClick;

    /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickablePointerInputNode$pointerInput$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Offset, t0> {
        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m232invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m232invokek4lQ0M(long j10) {
            r7.a aVar = CombinedClickablePointerInputNode.this.onDoubleClick;
            if (aVar != null) {
                aVar.invoke();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickablePointerInputNode$pointerInput$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements l<Offset, t0> {
        public AnonymousClass3() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m233invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m233invokek4lQ0M(long j10) {
            r7.a aVar = CombinedClickablePointerInputNode.this.onLongClick;
            if (aVar != null) {
                aVar.invoke();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickablePointerInputNode$pointerInput$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/geometry/Offset;", "offset", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/PressGestureScope;Landroidx/compose/ui/geometry/Offset;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.CombinedClickablePointerInputNode$pointerInput$4", f = "Clickable.kt", l = {AnalyticsListener.EVENT_PLAYER_RELEASED}, m = "invokeSuspend")
    public static final class AnonymousClass4 extends j implements q<PressGestureScope, Offset, d<? super t0>, Object> {
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        int label;

        public AnonymousClass4(d<? super AnonymousClass4> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return m234invoked4ec7I((PressGestureScope) obj, ((Offset) obj2).getPackedValue(), (d) obj3);
        }

        /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m234invoked4ec7I(PressGestureScope pressGestureScope, long j10, d<? super t0> dVar) {
            AnonymousClass4 anonymousClass4 = CombinedClickablePointerInputNode.this.new AnonymousClass4(dVar);
            anonymousClass4.L$0 = pressGestureScope;
            anonymousClass4.J$0 = j10;
            return anonymousClass4.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PressGestureScope pressGestureScope = (PressGestureScope) this.L$0;
                long j10 = this.J$0;
                if (CombinedClickablePointerInputNode.this.getEnabled()) {
                    CombinedClickablePointerInputNode combinedClickablePointerInputNode = CombinedClickablePointerInputNode.this;
                    this.label = 1;
                    Object objM153handlePressInteractiond4ec7I = combinedClickablePointerInputNode.m153handlePressInteractiond4ec7I(pressGestureScope, j10, this);
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

    /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickablePointerInputNode$pointerInput$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements l<Offset, t0> {
        public AnonymousClass5() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m235invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m235invokek4lQ0M(long j10) {
            if (CombinedClickablePointerInputNode.this.getEnabled()) {
                CombinedClickablePointerInputNode.this.getOnClick().invoke();
            }
        }
    }

    public CombinedClickablePointerInputNode(boolean z, MutableInteractionSource mutableInteractionSource, r7.a<t0> aVar, AbstractClickableNode.InteractionData interactionData, r7.a<t0> aVar2, r7.a<t0> aVar3) {
        super(z, mutableInteractionSource, aVar, interactionData, null);
        this.onLongClick = aVar2;
        this.onDoubleClick = aVar3;
    }

    @Override // androidx.compose.foundation.AbstractClickablePointerInputNode
    public Object pointerInput(PointerInputScope pointerInputScope, d<? super t0> dVar) {
        AbstractClickableNode.InteractionData interactionData = getInteractionData();
        long jM5850getCenterozmzZPI = IntSizeKt.m5850getCenterozmzZPI(pointerInputScope.getBoundsSize());
        interactionData.m152setCentreOffsetk4lQ0M(OffsetKt.Offset(IntOffset.m5802getXimpl(jM5850getCenterozmzZPI), IntOffset.m5803getYimpl(jM5850getCenterozmzZPI)));
        Object objDetectTapGestures = TapGestureDetectorKt.detectTapGestures(pointerInputScope, (!getEnabled() || this.onDoubleClick == null) ? null : new AnonymousClass2(), (!getEnabled() || this.onLongClick == null) ? null : new AnonymousClass3(), new AnonymousClass4(null), new AnonymousClass5(), dVar);
        return objDetectTapGestures == e7.a.f15033i ? objDetectTapGestures : t0.f22605a;
    }

    public final void update(boolean enabled, MutableInteractionSource interactionSource, r7.a<t0> onClick, r7.a<t0> onLongClick, r7.a<t0> onDoubleClick) {
        boolean z;
        setOnClick(onClick);
        setInteractionSource(interactionSource);
        if (getEnabled() != enabled) {
            setEnabled(enabled);
            z = true;
        } else {
            z = false;
        }
        if ((this.onLongClick == null) != (onLongClick == null)) {
            z = true;
        }
        this.onLongClick = onLongClick;
        boolean z5 = (this.onDoubleClick == null) == (onDoubleClick == null) ? z : true;
        this.onDoubleClick = onDoubleClick;
        if (z5) {
            resetPointerInputHandler();
        }
    }
}
