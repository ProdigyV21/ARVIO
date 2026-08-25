package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.draganddrop.DragAndDropModifierNode;
import androidx.compose.ui.draganddrop.DragAndDropNodeKt;
import androidx.compose.ui.draganddrop.DragAndDropTransferData;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import k2.c;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002BI\u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006\u0012'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\u0006¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000fH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012R3\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018RC\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0010\u0010\u001e\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/draganddrop/DragAndDropSourceNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/LayoutAwareModifierNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "Lx6/n;", "drawDragDecoration", "Lkotlin/Function2;", "Landroidx/compose/foundation/draganddrop/DragAndDropSourceScope;", "Ld7/d;", "", "dragAndDropSourceHandler", "<init>", "(Lr7/l;Lr7/p;)V", "Landroidx/compose/ui/unit/IntSize;", ContentDisposition.Parameters.Size, "onRemeasured-ozmzZPI", "(J)V", "onRemeasured", "Lr7/l;", "getDrawDragDecoration", "()Lr7/l;", "setDrawDragDecoration", "(Lr7/l;)V", "Lr7/p;", "getDragAndDropSourceHandler", "()Lr7/p;", "setDragAndDropSourceHandler", "(Lr7/p;)V", "J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DragAndDropSourceNode extends DelegatingNode implements LayoutAwareModifierNode {
    public static final int $stable = 8;
    private p<? super DragAndDropSourceScope, ? super d<? super t0>, ? extends Object> dragAndDropSourceHandler;
    private l<? super DrawScope, t0> drawDragDecoration;
    private long size = IntSize.INSTANCE.m5849getZeroYbymL2g();

    /* JADX INFO: renamed from: androidx.compose.foundation.draganddrop.DragAndDropSourceNode$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.draganddrop.DragAndDropSourceNode$1", f = "DragAndDropSource.kt", l = {113}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<PointerInputScope, d<? super t0>, Object> {
        final /* synthetic */ DragAndDropModifierNode $dragAndDropModifierNode;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.draganddrop.DragAndDropSourceNode$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0081\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J?\u0010\n\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00032'\u0010\t\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004¢\u0006\u0002\b\bH\u0096A¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u0010\u001a\u00020\r*\u00020\fH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\r*\u00020\u0011H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\f*\u00020\u0011H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0016\u001a\u00020\f*\u00020\u0017H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u0016\u001a\u00020\f*\u00020\rH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u001aJ\u0017\u0010\u001f\u001a\u00020\u001c*\u00020\u001bH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010!\u001a\u00020\u0017*\u00020\fH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\u0019J\u0017\u0010!\u001a\u00020\u0017*\u00020\u0011H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010\u0015J\u0014\u0010%\u001a\u00020$*\u00020#H\u0097\u0001¢\u0006\u0004\b%\u0010&J\u0017\u0010(\u001a\u00020\u001b*\u00020\u001cH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010\u001eJ\u0017\u0010+\u001a\u00020\u0011*\u00020\fH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\u001a\u0010+\u001a\u00020\u0011*\u00020\u0017H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010*J\u001a\u0010+\u001a\u00020\u0011*\u00020\rH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010-J\u0017\u00101\u001a\u0002002\u0006\u0010/\u001a\u00020.H\u0016¢\u0006\u0004\b1\u00102R\u0014\u00105\u001a\u00020\u00178\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b3\u00104R\u001a\u00108\u001a\u00020\u001b8VX\u0096\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b6\u00107R\u0014\u0010:\u001a\u00020\u00178\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b9\u00104R$\u0010A\u001a\u00020;2\u0006\u0010<\u001a\u00020;8V@VX\u0096\u000f¢\u0006\f\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001a\u0010D\u001a\u00020B8\u0016X\u0096\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bC\u00107R\u0014\u0010H\u001a\u00020E8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bF\u0010G\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006I"}, d2 = {"androidx/compose/foundation/draganddrop/DragAndDropSourceNode$1$1", "Landroidx/compose/foundation/draganddrop/DragAndDropSourceScope;", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "R", "Lkotlin/Function2;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Ld7/d;", "", "Lx6/n;", "block", "awaitPointerEventScope", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/unit/Dp;", "", "roundToPx-0680j_4", "(F)I", "roundToPx", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp-GaN1DYA", "(J)F", "toDp", "", "toDp-u2uoSUM", "(F)F", "(I)F", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/unit/DpSize;", "toDpSize-k-rfVVM", "(J)J", "toDpSize", "toPx-0680j_4", "toPx", "toPx--R2X_6o", "Landroidx/compose/ui/unit/DpRect;", "Landroidx/compose/ui/geometry/Rect;", "toRect", "(Landroidx/compose/ui/unit/DpRect;)Landroidx/compose/ui/geometry/Rect;", "toSize-XkaWNTQ", "toSize", "toSp-0xMU5do", "(F)J", "toSp", "toSp-kPz2Gy4", "(I)J", "Landroidx/compose/ui/draganddrop/DragAndDropTransferData;", "transferData", "Lx6/t0;", "startTransfer", "(Landroidx/compose/ui/draganddrop/DragAndDropTransferData;)V", "getDensity", "()F", "density", "getExtendedTouchPadding-NH-jbRc", "()J", "extendedTouchPadding", "getFontScale", "fontScale", "", "<anonymous parameter 0>", "getInterceptOutOfBoundsChildEvents", "()Z", "setInterceptOutOfBoundsChildEvents", "(Z)V", "interceptOutOfBoundsChildEvents", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "viewConfiguration", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class C00141 implements DragAndDropSourceScope, PointerInputScope {
            private final /* synthetic */ PointerInputScope $$delegate_0;
            final /* synthetic */ DragAndDropModifierNode $dragAndDropModifierNode;
            final /* synthetic */ DragAndDropSourceNode this$0;

            public C00141(PointerInputScope pointerInputScope, DragAndDropModifierNode dragAndDropModifierNode, DragAndDropSourceNode dragAndDropSourceNode) {
                this.$dragAndDropModifierNode = dragAndDropModifierNode;
                this.this$0 = dragAndDropSourceNode;
                this.$$delegate_0 = pointerInputScope;
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputScope
            public <R> Object awaitPointerEventScope(p<? super AwaitPointerEventScope, ? super d<? super R>, ? extends Object> pVar, d<? super R> dVar) {
                return this.$$delegate_0.awaitPointerEventScope(pVar, dVar);
            }

            @Override // androidx.compose.ui.unit.Density
            public float getDensity() {
                return this.$$delegate_0.getDensity();
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputScope
            /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
            public long mo276getExtendedTouchPaddingNHjbRc() {
                return this.$$delegate_0.mo276getExtendedTouchPaddingNHjbRc();
            }

            @Override // androidx.compose.ui.unit.FontScaling
            public float getFontScale() {
                return this.$$delegate_0.getFontScale();
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputScope
            public boolean getInterceptOutOfBoundsChildEvents() {
                return this.$$delegate_0.getInterceptOutOfBoundsChildEvents();
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputScope
            /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name */
            public long getBoundsSize() {
                return this.$$delegate_0.getBoundsSize();
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputScope
            public ViewConfiguration getViewConfiguration() {
                return this.$$delegate_0.getViewConfiguration();
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
            public int mo278roundToPxR2X_6o(long j10) {
                return this.$$delegate_0.mo278roundToPxR2X_6o(j10);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
            public int mo279roundToPx0680j_4(float f10) {
                return this.$$delegate_0.mo279roundToPx0680j_4(f10);
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputScope
            public void setInterceptOutOfBoundsChildEvents(boolean z) {
                this.$$delegate_0.setInterceptOutOfBoundsChildEvents(z);
            }

            @Override // androidx.compose.foundation.draganddrop.DragAndDropSourceScope
            public void startTransfer(DragAndDropTransferData transferData) {
                this.$dragAndDropModifierNode.mo3122drag12SF9DM(transferData, IntSizeKt.m5854toSizeozmzZPI(getBoundsSize()), this.this$0.getDrawDragDecoration());
            }

            @Override // androidx.compose.ui.unit.FontScaling
            /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
            public float mo280toDpGaN1DYA(long j10) {
                return this.$$delegate_0.mo280toDpGaN1DYA(j10);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
            public float mo281toDpu2uoSUM(float f10) {
                return this.$$delegate_0.mo281toDpu2uoSUM(f10);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
            public long mo283toDpSizekrfVVM(long j10) {
                return this.$$delegate_0.mo283toDpSizekrfVVM(j10);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
            public float mo284toPxR2X_6o(long j10) {
                return this.$$delegate_0.mo284toPxR2X_6o(j10);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
            public float mo285toPx0680j_4(float f10) {
                return this.$$delegate_0.mo285toPx0680j_4(f10);
            }

            @Override // androidx.compose.ui.unit.Density
            public Rect toRect(DpRect dpRect) {
                return this.$$delegate_0.toRect(dpRect);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
            public long mo286toSizeXkaWNTQ(long j10) {
                return this.$$delegate_0.mo286toSizeXkaWNTQ(j10);
            }

            @Override // androidx.compose.ui.unit.FontScaling
            /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
            public long mo287toSp0xMU5do(float f10) {
                return this.$$delegate_0.mo287toSp0xMU5do(f10);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
            public long mo288toSpkPz2Gy4(float f10) {
                return this.$$delegate_0.mo288toSpkPz2Gy4(f10);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
            public float mo282toDpu2uoSUM(int i10) {
                return this.$$delegate_0.mo282toDpu2uoSUM(i10);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
            public long mo289toSpkPz2Gy4(int i10) {
                return this.$$delegate_0.mo289toSpkPz2Gy4(i10);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DragAndDropModifierNode dragAndDropModifierNode, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$dragAndDropModifierNode = dragAndDropModifierNode;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = DragAndDropSourceNode.this.new AnonymousClass1(this.$dragAndDropModifierNode, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
            return ((AnonymousClass1) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                p<DragAndDropSourceScope, d<? super t0>, Object> dragAndDropSourceHandler = DragAndDropSourceNode.this.getDragAndDropSourceHandler();
                C00141 c00141 = new C00141(pointerInputScope, this.$dragAndDropModifierNode, DragAndDropSourceNode.this);
                this.label = 1;
                Object objInvoke = dragAndDropSourceHandler.invoke(c00141, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    public DragAndDropSourceNode(l<? super DrawScope, t0> lVar, p<? super DragAndDropSourceScope, ? super d<? super t0>, ? extends Object> pVar) {
        this.drawDragDecoration = lVar;
        this.dragAndDropSourceHandler = pVar;
        delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new AnonymousClass1((DragAndDropModifierNode) delegate(DragAndDropNodeKt.DragAndDropModifierNode()), null)));
    }

    public final p<DragAndDropSourceScope, d<? super t0>, Object> getDragAndDropSourceHandler() {
        return this.dragAndDropSourceHandler;
    }

    public final l<DrawScope, t0> getDrawDragDecoration() {
        return this.drawDragDecoration;
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    public final /* synthetic */ void onPlaced(LayoutCoordinates layoutCoordinates) {
        androidx.compose.ui.node.c.a(this, layoutCoordinates);
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    /* JADX INFO: renamed from: onRemeasured-ozmzZPI */
    public void mo236onRemeasuredozmzZPI(long size) {
        this.size = size;
    }

    public final void setDragAndDropSourceHandler(p<? super DragAndDropSourceScope, ? super d<? super t0>, ? extends Object> pVar) {
        this.dragAndDropSourceHandler = pVar;
    }

    public final void setDrawDragDecoration(l<? super DrawScope, t0> lVar) {
        this.drawDragDecoration = lVar;
    }
}
