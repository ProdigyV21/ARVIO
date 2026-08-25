package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatingNode;
import f7.j;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\nR$\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/gestures/MouseWheelScrollNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "scrollingLogic", "<init>", "(Landroidx/compose/foundation/gestures/ScrollingLogic;)V", "Lx6/t0;", "onAttach", "()V", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "Landroidx/compose/foundation/gestures/ScrollConfig;", "scrollConfig", "Landroidx/compose/foundation/gestures/ScrollConfig;", "getScrollConfig", "()Landroidx/compose/foundation/gestures/ScrollConfig;", "setScrollConfig", "(Landroidx/compose/foundation/gestures/ScrollConfig;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class MouseWheelScrollNode extends DelegatingNode implements CompositionLocalConsumerModifierNode {
    private ScrollConfig scrollConfig;
    private final ScrollingLogic scrollingLogic;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.MouseWheelScrollNode$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.MouseWheelScrollNode$1", f = "Scrollable.kt", l = {669}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<PointerInputScope, d7.d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.MouseWheelScrollNode$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.foundation.gestures.MouseWheelScrollNode$1$1", f = "Scrollable.kt", l = {671}, m = "invokeSuspend")
        public static final class C00221 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ MouseWheelScrollNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00221(MouseWheelScrollNode mouseWheelScrollNode, d7.d<? super C00221> dVar) {
                super(2, dVar);
                this.this$0 = mouseWheelScrollNode;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                C00221 c00221 = new C00221(this.this$0, dVar);
                c00221.L$0 = obj;
                return c00221;
            }

            @Override // r7.p
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
                return ((C00221) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x002b A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
            /* JADX WARN: Removed duplicated region for block: B:21:0x004a A[EDGE_INSN: B:21:0x004a->B:18:0x004a BREAK  A[LOOP:0: B:13:0x0038->B:17:0x0047], SYNTHETIC] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0029 -> B:12:0x002c). Please report as a decompilation issue!!! */
            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                /*
                    r9 = this;
                    int r0 = r9.label
                    r1 = 1
                    if (r0 == 0) goto L17
                    if (r0 != r1) goto Lf
                    java.lang.Object r0 = r9.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                    k2.c.G(r10)
                    goto L2c
                Lf:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r0)
                    throw r10
                L17:
                    k2.c.G(r10)
                    java.lang.Object r10 = r9.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r10 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r10
                    r0 = r10
                L1f:
                    r9.L$0 = r0
                    r9.label = r1
                    java.lang.Object r10 = androidx.compose.foundation.gestures.ScrollableKt.access$awaitScrollEvent(r0, r9)
                    e7.a r2 = e7.a.f15033i
                    if (r10 != r2) goto L2c
                    return r2
                L2c:
                    androidx.compose.ui.input.pointer.PointerEvent r10 = (androidx.compose.ui.input.pointer.PointerEvent) r10
                    java.util.List r2 = r10.getChanges()
                    int r3 = r2.size()
                    r4 = 0
                    r5 = r4
                L38:
                    if (r5 >= r3) goto L4a
                    java.lang.Object r6 = r2.get(r5)
                    androidx.compose.ui.input.pointer.PointerInputChange r6 = (androidx.compose.ui.input.pointer.PointerInputChange) r6
                    boolean r6 = r6.isConsumed()
                    if (r6 == 0) goto L47
                    goto L1f
                L47:
                    int r5 = r5 + 1
                    goto L38
                L4a:
                    androidx.compose.foundation.gestures.MouseWheelScrollNode r2 = r9.this$0
                    androidx.compose.foundation.gestures.ScrollConfig r2 = r2.getScrollConfig()
                    androidx.compose.foundation.gestures.MouseWheelScrollNode r3 = r9.this$0
                    long r5 = r0.mo4430getSizeYbymL2g()
                    long r5 = r2.mo291calculateMouseWheelScroll8xgXZGE(r0, r10, r5)
                    androidx.compose.foundation.gestures.ScrollingLogic r2 = androidx.compose.foundation.gestures.MouseWheelScrollNode.access$getScrollingLogic$p(r3)
                    ka.k0 r3 = r3.getCoroutineScope()
                    androidx.compose.foundation.gestures.MouseWheelScrollNode$1$1$2$1$1 r7 = new androidx.compose.foundation.gestures.MouseWheelScrollNode$1$1$2$1$1
                    r8 = 0
                    r7.<init>(r2, r5, r8)
                    r2 = 3
                    ka.m0.p(r3, r8, r4, r7, r2)
                    java.util.List r10 = r10.getChanges()
                    int r2 = r10.size()
                L74:
                    if (r4 >= r2) goto L1f
                    java.lang.Object r3 = r10.get(r4)
                    androidx.compose.ui.input.pointer.PointerInputChange r3 = (androidx.compose.ui.input.pointer.PointerInputChange) r3
                    r3.consume()
                    int r4 = r4 + 1
                    goto L74
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.MouseWheelScrollNode.AnonymousClass1.C00221.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = MouseWheelScrollNode.this.new AnonymousClass1(dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                C00221 c00221 = new C00221(MouseWheelScrollNode.this, null);
                this.label = 1;
                Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(c00221, this);
                e7.a aVar = e7.a.f15033i;
                if (objAwaitPointerEventScope == aVar) {
                    return aVar;
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

    public MouseWheelScrollNode(ScrollingLogic scrollingLogic) {
        this.scrollingLogic = scrollingLogic;
        delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new AnonymousClass1(null)));
    }

    public final ScrollConfig getScrollConfig() {
        return this.scrollConfig;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        this.scrollConfig = AndroidScrollable_androidKt.platformScrollConfig(this);
    }

    public final void setScrollConfig(ScrollConfig scrollConfig) {
        this.scrollConfig = scrollConfig;
    }
}
