package androidx.compose.material3;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.unit.IntOffset;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.ClockDialNode$pointerInputDragNode$1", f = "TimePicker.kt", l = {1292}, m = "invokeSuspend")
public final class ClockDialNode$pointerInputDragNode$1 extends j implements p<PointerInputScope, d7.d<? super t0>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ClockDialNode this$0;

    /* JADX INFO: renamed from: androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.a<t0> {
        final /* synthetic */ ClockDialNode this$0;

        /* JADX INFO: renamed from: androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$1$1", f = "TimePicker.kt", l = {1296, 1298}, m = "invokeSuspend")
        public static final class C00941 extends j implements p<k0, d7.d<? super t0>, Object> {
            int label;
            final /* synthetic */ ClockDialNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00941(ClockDialNode clockDialNode, d7.d<? super C00941> dVar) {
                super(2, dVar);
                this.this$0 = clockDialNode;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new C00941(this.this$0, dVar);
            }

            /* JADX WARN: Code restructure failed: missing block: B:15:0x0053, code lost:
            
                if (r5.animateToCurrent$material3_release(r4) == r3) goto L21;
             */
            /* JADX WARN: Code restructure failed: missing block: B:20:0x0076, code lost:
            
                if (r5.settle(r4) == r3) goto L21;
             */
            /* JADX WARN: Code restructure failed: missing block: B:21:0x0078, code lost:
            
                return r3;
             */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r5) {
                /*
                    r4 = this;
                    int r0 = r4.label
                    r1 = 2
                    r2 = 1
                    if (r0 == 0) goto L17
                    if (r0 == r2) goto L13
                    if (r0 != r1) goto Lb
                    goto L13
                Lb:
                    java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r5.<init>(r0)
                    throw r5
                L13:
                    k2.c.G(r5)
                    goto L79
                L17:
                    k2.c.G(r5)
                    androidx.compose.material3.ClockDialNode r5 = r4.this$0
                    androidx.compose.material3.TimePickerState r5 = androidx.compose.material3.ClockDialNode.access$getState$p(r5)
                    int r5 = r5.m2204getSelectionJiIwxys$material3_release()
                    androidx.compose.material3.Selection$Companion r0 = androidx.compose.material3.Selection.INSTANCE
                    int r3 = r0.m1869getHourJiIwxys()
                    boolean r5 = androidx.compose.material3.Selection.m1865equalsimpl0(r5, r3)
                    e7.a r3 = e7.a.f15033i
                    if (r5 == 0) goto L56
                    androidx.compose.material3.ClockDialNode r5 = r4.this$0
                    boolean r5 = androidx.compose.material3.ClockDialNode.access$getAutoSwitchToMinute$p(r5)
                    if (r5 == 0) goto L56
                    androidx.compose.material3.ClockDialNode r5 = r4.this$0
                    androidx.compose.material3.TimePickerState r5 = androidx.compose.material3.ClockDialNode.access$getState$p(r5)
                    int r0 = r0.m1870getMinuteJiIwxys()
                    r5.m2207setSelectioniHAOin8$material3_release(r0)
                    androidx.compose.material3.ClockDialNode r5 = r4.this$0
                    androidx.compose.material3.TimePickerState r5 = androidx.compose.material3.ClockDialNode.access$getState$p(r5)
                    r4.label = r2
                    java.lang.Object r5 = r5.animateToCurrent$material3_release(r4)
                    if (r5 != r3) goto L79
                    goto L78
                L56:
                    androidx.compose.material3.ClockDialNode r5 = r4.this$0
                    androidx.compose.material3.TimePickerState r5 = androidx.compose.material3.ClockDialNode.access$getState$p(r5)
                    int r5 = r5.m2204getSelectionJiIwxys$material3_release()
                    int r0 = r0.m1870getMinuteJiIwxys()
                    boolean r5 = androidx.compose.material3.Selection.m1865equalsimpl0(r5, r0)
                    if (r5 == 0) goto L79
                    androidx.compose.material3.ClockDialNode r5 = r4.this$0
                    androidx.compose.material3.TimePickerState r5 = androidx.compose.material3.ClockDialNode.access$getState$p(r5)
                    r4.label = r1
                    java.lang.Object r5 = r5.settle(r4)
                    if (r5 != r3) goto L79
                L78:
                    return r3
                L79:
                    x6.t0 r5 = x6.t0.f22605a
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ClockDialNode$pointerInputDragNode$1.AnonymousClass1.C00941.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((C00941) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ClockDialNode clockDialNode) {
            super(0);
            this.this$0 = clockDialNode;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1347invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1347invoke() {
            m0.p(this.this$0.getCoroutineScope(), null, 0, new C00941(this.this$0, null), 3);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "<anonymous parameter 0>", "Landroidx/compose/ui/geometry/Offset;", "dragAmount", "Lx6/t0;", "invoke-Uv8p0NA", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements p<PointerInputChange, Offset, t0> {
        final /* synthetic */ ClockDialNode this$0;

        /* JADX INFO: renamed from: androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$2$1", f = "TimePicker.kt", l = {1305}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ long $dragAmount;
            int label;
            final /* synthetic */ ClockDialNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(ClockDialNode clockDialNode, long j10, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = clockDialNode;
                this.$dragAmount = j10;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass1(this.this$0, this.$dragAmount, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    ClockDialNode clockDialNode = this.this$0;
                    clockDialNode.offsetX = Offset.m3236getXimpl(this.$dragAmount) + clockDialNode.offsetX;
                    ClockDialNode clockDialNode2 = this.this$0;
                    clockDialNode2.offsetY = Offset.m3237getYimpl(this.$dragAmount) + clockDialNode2.offsetY;
                    TimePickerState timePickerState = this.this$0.state;
                    float fAtan = TimePickerKt.atan(this.this$0.offsetY - IntOffset.m5803getYimpl(this.this$0.state.m2203getCenternOccac$material3_release()), this.this$0.offsetX - IntOffset.m5802getXimpl(this.this$0.state.m2203getCenternOccac$material3_release()));
                    this.label = 1;
                    Object objUpdate$material3_release$default = TimePickerState.update$material3_release$default(timePickerState, fAtan, false, this, 2, null);
                    e7.a aVar = e7.a.f15033i;
                    if (objUpdate$material3_release$default == aVar) {
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

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ClockDialNode clockDialNode) {
            super(2);
            this.this$0 = clockDialNode;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            m1348invokeUv8p0NA((PointerInputChange) obj, ((Offset) obj2).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
        public final void m1348invokeUv8p0NA(PointerInputChange pointerInputChange, long j10) {
            m0.p(this.this$0.getCoroutineScope(), null, 0, new AnonymousClass1(this.this$0, j10, null), 3);
            this.this$0.state.moveSelector$material3_release(this.this$0.offsetX, this.this$0.offsetY, this.this$0.getMaxDist());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClockDialNode$pointerInputDragNode$1(ClockDialNode clockDialNode, d7.d<? super ClockDialNode$pointerInputDragNode$1> dVar) {
        super(2, dVar);
        this.this$0 = clockDialNode;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        ClockDialNode$pointerInputDragNode$1 clockDialNode$pointerInputDragNode$1 = new ClockDialNode$pointerInputDragNode$1(this.this$0, dVar);
        clockDialNode$pointerInputDragNode$1.L$0 = obj;
        return clockDialNode$pointerInputDragNode$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super t0> dVar) {
        return ((ClockDialNode$pointerInputDragNode$1) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0);
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0);
            this.label = 1;
            Object objDetectDragGestures$default = DragGestureDetectorKt.detectDragGestures$default(pointerInputScope, null, anonymousClass1, null, anonymousClass2, this, 5, null);
            e7.a aVar = e7.a.f15033i;
            if (objDetectDragGestures$default == aVar) {
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
