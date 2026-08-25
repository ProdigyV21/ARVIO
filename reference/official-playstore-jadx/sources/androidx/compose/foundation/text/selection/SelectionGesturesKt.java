package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import d7.d;
import f7.c;
import f7.e;
import f7.h;
import f7.j;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\u001a'\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a#\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a$\u0010\u0011\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000fH\u0082@¢\u0006\u0004\b\u0011\u0010\u0012\u001a,\u0010\u0015\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u000fH\u0082@¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0014\u0010\u0017\u001a\u00020\u000f*\u00020\rH\u0082@¢\u0006\u0004\b\u0017\u0010\u0018\u001a&\u0010\u001e\u001a\u00020\u0002*\u00020\r2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\"\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b \u0010!\"\u0014\u0010#\u001a\u00020\"8\u0000X\u0080T¢\u0006\u0006\n\u0004\b#\u0010$\"\u0018\u0010%\u001a\u00020\u0002*\u00020\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function1;", "", "Lx6/t0;", "updateTouchMode", "updateSelectionTouchMode", "(Landroidx/compose/ui/Modifier;Lr7/l;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "mouseSelectionObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "textDragObserver", "selectionGestureInput", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/TextDragObserver;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "observer", "Landroidx/compose/ui/input/pointer/PointerEvent;", "down", "touchSelection", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/TextDragObserver;Landroidx/compose/ui/input/pointer/PointerEvent;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/foundation/text/selection/ClicksCounter;", "clicksCounter", "mouseSelection", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/selection/ClicksCounter;Landroidx/compose/ui/input/pointer/PointerEvent;Ld7/d;)Ljava/lang/Object;", "awaitDown", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/geometry/Offset;", "offset1", "offset2", "distanceIsTolerable-2x9bVx0", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JJ)Z", "distanceIsTolerable", "", "STATIC_KEY", "I", "", "ClicksSlop", "D", "isPrecisePointer", "(Landroidx/compose/ui/input/pointer/PointerEvent;)Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionGesturesKt {
    public static final double ClicksSlop = 100.0d;
    private static final int STATIC_KEY = 8675309;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", l = {225}, m = "awaitDown")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.awaitDown(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelection$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", l = {153, 176}, m = "mouseSelection")
    public static final class C03921 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C03921(d<? super C03921> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.mouseSelection(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1", f = "SelectionGestures.kt", l = {99}, m = "invokeSuspend")
    public static final class C03931 extends j implements p<PointerInputScope, d<? super t0>, Object> {
        final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
        final /* synthetic */ TextDragObserver $textDragObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1$1", f = "SelectionGestures.kt", l = {100, 106, 108}, m = "invokeSuspend")
        public static final class C00561 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
            final /* synthetic */ ClicksCounter $clicksCounter;
            final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
            final /* synthetic */ TextDragObserver $textDragObserver;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00561(MouseSelectionObserver mouseSelectionObserver, ClicksCounter clicksCounter, TextDragObserver textDragObserver, d<? super C00561> dVar) {
                super(2, dVar);
                this.$mouseSelectionObserver = mouseSelectionObserver;
                this.$clicksCounter = clicksCounter;
                this.$textDragObserver = textDragObserver;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                C00561 c00561 = new C00561(this.$mouseSelectionObserver, this.$clicksCounter, this.$textDragObserver, dVar);
                c00561.L$0 = obj;
                return c00561;
            }

            @Override // r7.p
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
                return ((C00561) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Code restructure failed: missing block: B:26:0x0072, code lost:
            
                if (androidx.compose.foundation.text.selection.SelectionGesturesKt.mouseSelection(r0, r1, r3, r10, r9) == r4) goto L32;
             */
            /* JADX WARN: Code restructure failed: missing block: B:31:0x0085, code lost:
            
                if (androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelection(r0, r2, r10, r9) == r4) goto L32;
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
                    r1 = 3
                    r2 = 2
                    r3 = 1
                    e7.a r4 = e7.a.f15033i
                    if (r0 == 0) goto L25
                    if (r0 == r3) goto L1d
                    if (r0 == r2) goto L18
                    if (r0 != r1) goto L10
                    goto L18
                L10:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r0)
                    throw r10
                L18:
                    k2.c.G(r10)
                    goto L88
                L1d:
                    java.lang.Object r0 = r9.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                    k2.c.G(r10)
                    goto L38
                L25:
                    k2.c.G(r10)
                    java.lang.Object r10 = r9.L$0
                    r0 = r10
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                    r9.L$0 = r0
                    r9.label = r3
                    java.lang.Object r10 = androidx.compose.foundation.text.selection.SelectionGesturesKt.access$awaitDown(r0, r9)
                    if (r10 != r4) goto L38
                    goto L87
                L38:
                    androidx.compose.ui.input.pointer.PointerEvent r10 = (androidx.compose.ui.input.pointer.PointerEvent) r10
                    boolean r3 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r10)
                    r5 = 0
                    if (r3 == 0) goto L75
                    int r3 = r10.getButtons()
                    boolean r3 = androidx.compose.ui.input.pointer.PointerEvent_androidKt.m4491isPrimaryPressedaHzCxE(r3)
                    if (r3 == 0) goto L75
                    java.util.List r3 = r10.getChanges()
                    int r6 = r3.size()
                    r7 = 0
                L54:
                    if (r7 >= r6) goto L66
                    java.lang.Object r8 = r3.get(r7)
                    androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
                    boolean r8 = r8.isConsumed()
                    if (r8 == 0) goto L63
                    goto L75
                L63:
                    int r7 = r7 + 1
                    goto L54
                L66:
                    androidx.compose.foundation.text.selection.MouseSelectionObserver r1 = r9.$mouseSelectionObserver
                    androidx.compose.foundation.text.selection.ClicksCounter r3 = r9.$clicksCounter
                    r9.L$0 = r5
                    r9.label = r2
                    java.lang.Object r10 = androidx.compose.foundation.text.selection.SelectionGesturesKt.access$mouseSelection(r0, r1, r3, r10, r9)
                    if (r10 != r4) goto L88
                    goto L87
                L75:
                    boolean r2 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r10)
                    if (r2 != 0) goto L88
                    androidx.compose.foundation.text.TextDragObserver r2 = r9.$textDragObserver
                    r9.L$0 = r5
                    r9.label = r1
                    java.lang.Object r10 = androidx.compose.foundation.text.selection.SelectionGesturesKt.access$touchSelection(r0, r2, r10, r9)
                    if (r10 != r4) goto L88
                L87:
                    return r4
                L88:
                    x6.t0 r10 = x6.t0.f22605a
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.C03931.C00561.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03931(MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, d<? super C03931> dVar) {
            super(2, dVar);
            this.$mouseSelectionObserver = mouseSelectionObserver;
            this.$textDragObserver = textDragObserver;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C03931 c03931 = new C03931(this.$mouseSelectionObserver, this.$textDragObserver, dVar);
            c03931.L$0 = obj;
            return c03931;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
            return ((C03931) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                C00561 c00561 = new C00561(this.$mouseSelectionObserver, new ClicksCounter(pointerInputScope.getViewConfiguration()), this.$textDragObserver, null);
                this.label = 1;
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, c00561, this);
                e7.a aVar = e7.a.f15033i;
                if (objAwaitEachGesture == aVar) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelection$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", l = {119, 123}, m = "touchSelection")
    public static final class C03941 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C03941(d<? super C03941> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelection(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelection$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<PointerInputChange, t0> {
        final /* synthetic */ TextDragObserver $observer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TextDragObserver textDragObserver) {
            super(1);
            this.$observer = textDragObserver;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((PointerInputChange) obj);
            return t0.f22605a;
        }

        public final void invoke(PointerInputChange pointerInputChange) {
            this.$observer.mo876onDragk4lQ0M(PointerEventKt.positionChange(pointerInputChange));
            pointerInputChange.consume();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1", f = "SelectionGestures.kt", l = {86}, m = "invokeSuspend")
    public static final class C03951 extends j implements p<PointerInputScope, d<? super t0>, Object> {
        final /* synthetic */ l<Boolean, t0> $updateTouchMode;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1", f = "SelectionGestures.kt", l = {88}, m = "invokeSuspend")
        public static final class C00571 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
            final /* synthetic */ l<Boolean, t0> $updateTouchMode;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C00571(l<? super Boolean, t0> lVar, d<? super C00571> dVar) {
                super(2, dVar);
                this.$updateTouchMode = lVar;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                C00571 c00571 = new C00571(this.$updateTouchMode, dVar);
                c00571.L$0 = obj;
                return c00571;
            }

            @Override // r7.p
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
                return ((C00571) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x002d A[RETURN] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x002b -> B:12:0x002e). Please report as a decompilation issue!!! */
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
            public final java.lang.Object invokeSuspend(java.lang.Object r4) {
                /*
                    r3 = this;
                    int r0 = r3.label
                    r1 = 1
                    if (r0 == 0) goto L17
                    if (r0 != r1) goto Lf
                    java.lang.Object r0 = r3.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                    k2.c.G(r4)
                    goto L2e
                Lf:
                    java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r4.<init>(r0)
                    throw r4
                L17:
                    k2.c.G(r4)
                    java.lang.Object r4 = r3.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                    r0 = r4
                L1f:
                    androidx.compose.ui.input.pointer.PointerEventPass r4 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                    r3.L$0 = r0
                    r3.label = r1
                    java.lang.Object r4 = r0.awaitPointerEvent(r4, r3)
                    e7.a r2 = e7.a.f15033i
                    if (r4 != r2) goto L2e
                    return r2
                L2e:
                    androidx.compose.ui.input.pointer.PointerEvent r4 = (androidx.compose.ui.input.pointer.PointerEvent) r4
                    r7.l<java.lang.Boolean, x6.t0> r2 = r3.$updateTouchMode
                    boolean r4 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r4)
                    r4 = r4 ^ r1
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
                    r2.invoke(r4)
                    goto L1f
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.C03951.C00571.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03951(l<? super Boolean, t0> lVar, d<? super C03951> dVar) {
            super(2, dVar);
            this.$updateTouchMode = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C03951 c03951 = new C03951(this.$updateTouchMode, dVar);
            c03951.L$0 = obj;
            return c03951;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
            return ((C03951) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                C00571 c00571 = new C00571(this.$updateTouchMode, null);
                this.label = 1;
                Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(c00571, this);
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0042 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0040 -> B:18:0x0043). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r6, d7.d<? super androidx.compose.ui.input.pointer.PointerEvent> r7) {
        /*
            boolean r0 = r7 instanceof androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r0 = (androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r0 = new androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r6 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r6 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r6
            k2.c.G(r7)
            goto L43
        L29:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L31:
            k2.c.G(r7)
        L34:
            androidx.compose.ui.input.pointer.PointerEventPass r7 = androidx.compose.ui.input.pointer.PointerEventPass.Main
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r7 = r6.awaitPointerEvent(r7, r0)
            e7.a r1 = e7.a.f15033i
            if (r7 != r1) goto L43
            return r1
        L43:
            androidx.compose.ui.input.pointer.PointerEvent r7 = (androidx.compose.ui.input.pointer.PointerEvent) r7
            java.util.List r1 = r7.getChanges()
            int r3 = r1.size()
            r4 = 0
        L4e:
            if (r4 >= r3) goto L60
            java.lang.Object r5 = r1.get(r4)
            androidx.compose.ui.input.pointer.PointerInputChange r5 = (androidx.compose.ui.input.pointer.PointerInputChange) r5
            boolean r5 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDownIgnoreConsumed(r5)
            if (r5 != 0) goto L5d
            goto L34
        L5d:
            int r4 = r4 + 1
            goto L4e
        L60:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, d7.d):java.lang.Object");
    }

    /* JADX INFO: renamed from: distanceIsTolerable-2x9bVx0, reason: not valid java name */
    private static final boolean m969distanceIsTolerable2x9bVx0(AwaitPointerEventScope awaitPointerEventScope, long j10, long j11) {
        return Offset.m3234getDistanceimpl(Offset.m3240minusMKHz9U(j10, j11)) < awaitPointerEventScope.getViewConfiguration().getTouchSlop();
    }

    public static final boolean isPrecisePointer(PointerEvent pointerEvent) {
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (!PointerType.m4577equalsimpl0(changes.get(i10).getType(), PointerType.INSTANCE.m4582getMouseT8wyACA())) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007d, code lost:
    
        if (r10 == r1) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00e5, code lost:
    
        if (r10 == r1) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00e7, code lost:
    
        return r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object mouseSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope r6, androidx.compose.foundation.text.selection.MouseSelectionObserver r7, androidx.compose.foundation.text.selection.ClicksCounter r8, androidx.compose.ui.input.pointer.PointerEvent r9, d7.d<? super x6.t0> r10) {
        /*
            Method dump skipped, instruction units count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.mouseSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.selection.MouseSelectionObserver, androidx.compose.foundation.text.selection.ClicksCounter, androidx.compose.ui.input.pointer.PointerEvent, d7.d):java.lang.Object");
    }

    public static final Modifier selectionGestureInput(Modifier modifier, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, mouseSelectionObserver, textDragObserver, new C03931(mouseSelectionObserver, textDragObserver, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a1, code lost:
    
        if (r13 == r4) goto L32;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object touchSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope r10, androidx.compose.foundation.text.TextDragObserver r11, androidx.compose.ui.input.pointer.PointerEvent r12, d7.d<? super x6.t0> r13) {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.TextDragObserver, androidx.compose.ui.input.pointer.PointerEvent, d7.d):java.lang.Object");
    }

    public static final Modifier updateSelectionTouchMode(Modifier modifier, l<? super Boolean, t0> lVar) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, Integer.valueOf(STATIC_KEY), new C03951(lVar, null));
    }
}
