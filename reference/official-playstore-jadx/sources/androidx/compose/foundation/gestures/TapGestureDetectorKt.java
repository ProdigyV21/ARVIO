package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.cast.MediaError;
import f7.j;
import ka.k0;
import ka.l0;
import ka.m0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u008d\u0001\u0010\r\u001a\u00020\u0003*\u00020\u00002\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012/\b\u0002\u0010\u000b\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\n2\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0001H\u0086@¢\u0006\u0004\b\r\u0010\u000e\u001a\u0014\u0010\u0010\u001a\u00020\u0003*\u00020\u000fH\u0082@¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0012*\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0012H\u0082@¢\u0006\u0004\b\u0014\u0010\u0015\u001a]\u0010\u0016\u001a\u00020\u0003*\u00020\u00002/\b\u0002\u0010\u000b\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\n2\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0001H\u0080@¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u001e\u0010\u001a\u001a\u00020\u0012*\u00020\u000f2\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0087@¢\u0006\u0004\b\u001a\u0010\u001b\u001a(\u0010\u001a\u001a\u00020\u0012*\u00020\u000f2\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0086@¢\u0006\u0004\b\u001a\u0010\u001e\u001a\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u0012*\u00020\u000fH\u0087@¢\u0006\u0004\b\u001f\u0010\u0011\u001a \u0010\u001f\u001a\u0004\u0018\u00010\u0012*\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0086@¢\u0006\u0004\b\u001f\u0010 \";\u0010!\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/t0;", "onDoubleTap", "onLongPress", "Lkotlin/Function3;", "Landroidx/compose/foundation/gestures/PressGestureScope;", "Ld7/d;", "", "Lx6/n;", "onPress", "onTap", "detectTapGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lr7/l;Lr7/l;Lr7/q;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "consumeUntilUp", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "firstUp", "awaitSecondDown", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/ui/input/pointer/PointerInputChange;Ld7/d;)Ljava/lang/Object;", "detectTapAndPress", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lr7/q;Lr7/l;Ld7/d;)Ljava/lang/Object;", "", "requireUnconsumed", "awaitFirstDown", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;ZLd7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "pass", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;ZLandroidx/compose/ui/input/pointer/PointerEventPass;Ld7/d;)Ljava/lang/Object;", "waitForUpOrCancellation", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/ui/input/pointer/PointerEventPass;Ld7/d;)Ljava/lang/Object;", "NoPressGesture", "Lr7/q;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TapGestureDetectorKt {
    private static final q<PressGestureScope, Offset, d7.d<? super t0>, Object> NoPressGesture = new TapGestureDetectorKt$NoPressGesture$1(null);

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", l = {279}, m = "awaitFirstDown")
    public static final class AnonymousClass2 extends f7.c {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.awaitFirstDown(null, false, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitSecondDown$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitSecondDown$2", f = "TapGestureDetector.kt", l = {212}, m = "invokeSuspend")
    public static final class C03232 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super PointerInputChange>, Object> {
        final /* synthetic */ PointerInputChange $firstUp;
        long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03232(PointerInputChange pointerInputChange, d7.d<? super C03232> dVar) {
            super(2, dVar);
            this.$firstUp = pointerInputChange;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C03232 c03232 = new C03232(this.$firstUp, dVar);
            c03232.L$0 = obj;
            return c03232;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super PointerInputChange> dVar) {
            return ((C03232) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0045 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0050 A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0043 -> B:12:0x0046). Please report as a decompilation issue!!! */
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
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                r12 = this;
                int r0 = r12.label
                r1 = 1
                if (r0 == 0) goto L1a
                if (r0 != r1) goto L12
                long r2 = r12.J$0
                java.lang.Object r0 = r12.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                k2.c.G(r13)
                r6 = r0
                goto L46
            L12:
                java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r13.<init>(r0)
                throw r13
            L1a:
                k2.c.G(r13)
                java.lang.Object r13 = r12.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r13 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r13
                androidx.compose.ui.input.pointer.PointerInputChange r0 = r12.$firstUp
                long r2 = r0.getUptimeMillis()
                androidx.compose.ui.platform.ViewConfiguration r0 = r13.getViewConfiguration()
                long r4 = r0.getDoubleTapMinTimeMillis()
                long r4 = r4 + r2
                r6 = r13
                r2 = r4
            L32:
                r12.L$0 = r6
                r12.J$0 = r2
                r12.label = r1
                r7 = 0
                r8 = 0
                r10 = 3
                r11 = 0
                r9 = r12
                java.lang.Object r13 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r6, r7, r8, r9, r10, r11)
                e7.a r0 = e7.a.f15033i
                if (r13 != r0) goto L46
                return r0
            L46:
                androidx.compose.ui.input.pointer.PointerInputChange r13 = (androidx.compose.ui.input.pointer.PointerInputChange) r13
                long r4 = r13.getUptimeMillis()
                int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
                if (r0 < 0) goto L32
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.C03232.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$consumeUntilUp$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", l = {195}, m = "consumeUntilUp")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.consumeUntilUp(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2", f = "TapGestureDetector.kt", l = {232}, m = "invokeSuspend")
    public static final class C03242 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ q<PressGestureScope, Offset, d7.d<? super t0>, Object> $onPress;
        final /* synthetic */ l<Offset, t0> $onTap;
        final /* synthetic */ PressGestureScopeImpl $pressScope;
        final /* synthetic */ PointerInputScope $this_detectTapAndPress;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1", f = "TapGestureDetector.kt", l = {237, 245}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
            final /* synthetic */ k0 $$this$coroutineScope;
            final /* synthetic */ q<PressGestureScope, Offset, d7.d<? super t0>, Object> $onPress;
            final /* synthetic */ l<Offset, t0> $onTap;
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$1", f = "TapGestureDetector.kt", l = {234}, m = "invokeSuspend")
            public static final class C00231 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00231(PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super C00231> dVar) {
                    super(2, dVar);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new C00231(this.$pressScope, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                        this.label = 1;
                        Object objReset = pressGestureScopeImpl.reset(this);
                        e7.a aVar = e7.a.f15033i;
                        if (objReset == aVar) {
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
                    return ((C00231) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$2, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$2", f = "TapGestureDetector.kt", l = {241}, m = "invokeSuspend")
            public static final class C00242 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ PointerInputChange $down;
                final /* synthetic */ q<PressGestureScope, Offset, d7.d<? super t0>, Object> $onPress;
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C00242(q<? super PressGestureScope, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, PressGestureScopeImpl pressGestureScopeImpl, PointerInputChange pointerInputChange, d7.d<? super C00242> dVar) {
                    super(2, dVar);
                    this.$onPress = qVar;
                    this.$pressScope = pressGestureScopeImpl;
                    this.$down = pointerInputChange;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new C00242(this.$onPress, this.$pressScope, this.$down, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        q<PressGestureScope, Offset, d7.d<? super t0>, Object> qVar = this.$onPress;
                        PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                        Offset offsetM3225boximpl = Offset.m3225boximpl(this.$down.getPosition());
                        this.label = 1;
                        Object objInvoke = qVar.invoke(pressGestureScopeImpl, offsetM3225boximpl, this);
                        e7.a aVar = e7.a.f15033i;
                        if (objInvoke == aVar) {
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
                    return ((C00242) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$3, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$3", f = "TapGestureDetector.kt", l = {}, m = "invokeSuspend")
            public static final class AnonymousClass3 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super AnonymousClass3> dVar) {
                    super(2, dVar);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new AnonymousClass3(this.$pressScope, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    this.$pressScope.cancel();
                    return t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                    return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$4, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$4", f = "TapGestureDetector.kt", l = {}, m = "invokeSuspend")
            public static final class AnonymousClass4 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super AnonymousClass4> dVar) {
                    super(2, dVar);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new AnonymousClass4(this.$pressScope, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    this.$pressScope.release();
                    return t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                    return ((AnonymousClass4) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(k0 k0Var, q<? super PressGestureScope, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, l<? super Offset, t0> lVar, PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$$this$coroutineScope = k0Var;
                this.$onPress = qVar;
                this.$onTap = lVar;
                this.$pressScope = pressGestureScopeImpl;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$coroutineScope, this.$onPress, this.$onTap, this.$pressScope, dVar);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // r7.p
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
                return ((AnonymousClass1) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Code restructure failed: missing block: B:18:0x006d, code lost:
            
                if (r14 == r6) goto L19;
             */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r14) {
                /*
                    r13 = this;
                    int r0 = r13.label
                    r1 = 0
                    r2 = 3
                    r3 = 2
                    r4 = 1
                    r5 = 0
                    e7.a r6 = e7.a.f15033i
                    if (r0 == 0) goto L25
                    if (r0 == r4) goto L1c
                    if (r0 != r3) goto L14
                    k2.c.G(r14)
                    r10 = r13
                    goto L70
                L14:
                    java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r14.<init>(r0)
                    throw r14
                L1c:
                    java.lang.Object r0 = r13.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                    k2.c.G(r14)
                    r10 = r13
                    goto L4a
                L25:
                    k2.c.G(r14)
                    java.lang.Object r14 = r13.L$0
                    r7 = r14
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r7 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r7
                    ka.k0 r14 = r13.$$this$coroutineScope
                    androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$1 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$1
                    androidx.compose.foundation.gestures.PressGestureScopeImpl r8 = r13.$pressScope
                    r0.<init>(r8, r5)
                    ka.m0.p(r14, r5, r1, r0, r2)
                    r13.L$0 = r7
                    r13.label = r4
                    r8 = 0
                    r9 = 0
                    r11 = 3
                    r12 = 0
                    r10 = r13
                    java.lang.Object r14 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r7, r8, r9, r10, r11, r12)
                    if (r14 != r6) goto L49
                    goto L6f
                L49:
                    r0 = r7
                L4a:
                    androidx.compose.ui.input.pointer.PointerInputChange r14 = (androidx.compose.ui.input.pointer.PointerInputChange) r14
                    r14.consume()
                    r7.q<androidx.compose.foundation.gestures.PressGestureScope, androidx.compose.ui.geometry.Offset, d7.d<? super x6.t0>, java.lang.Object> r7 = r10.$onPress
                    r7.q r8 = androidx.compose.foundation.gestures.TapGestureDetectorKt.access$getNoPressGesture$p()
                    if (r7 == r8) goto L65
                    ka.k0 r7 = r10.$$this$coroutineScope
                    androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$2 r8 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$2
                    r7.q<androidx.compose.foundation.gestures.PressGestureScope, androidx.compose.ui.geometry.Offset, d7.d<? super x6.t0>, java.lang.Object> r9 = r10.$onPress
                    androidx.compose.foundation.gestures.PressGestureScopeImpl r11 = r10.$pressScope
                    r8.<init>(r9, r11, r14, r5)
                    ka.m0.p(r7, r5, r1, r8, r2)
                L65:
                    r10.L$0 = r5
                    r10.label = r3
                    java.lang.Object r14 = androidx.compose.foundation.gestures.TapGestureDetectorKt.waitForUpOrCancellation$default(r0, r5, r13, r4, r5)
                    if (r14 != r6) goto L70
                L6f:
                    return r6
                L70:
                    androidx.compose.ui.input.pointer.PointerInputChange r14 = (androidx.compose.ui.input.pointer.PointerInputChange) r14
                    if (r14 != 0) goto L81
                    ka.k0 r14 = r10.$$this$coroutineScope
                    androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$3 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$3
                    androidx.compose.foundation.gestures.PressGestureScopeImpl r3 = r10.$pressScope
                    r0.<init>(r3, r5)
                    ka.m0.p(r14, r5, r1, r0, r2)
                    goto L9f
                L81:
                    r14.consume()
                    ka.k0 r0 = r10.$$this$coroutineScope
                    androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$4 r3 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$4
                    androidx.compose.foundation.gestures.PressGestureScopeImpl r4 = r10.$pressScope
                    r3.<init>(r4, r5)
                    ka.m0.p(r0, r5, r1, r3, r2)
                    r7.l<androidx.compose.ui.geometry.Offset, x6.t0> r0 = r10.$onTap
                    if (r0 == 0) goto L9f
                    long r1 = r14.getPosition()
                    androidx.compose.ui.geometry.Offset r14 = androidx.compose.ui.geometry.Offset.m3225boximpl(r1)
                    r0.invoke(r14)
                L9f:
                    x6.t0 r14 = x6.t0.f22605a
                    return r14
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.C03242.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03242(PointerInputScope pointerInputScope, q<? super PressGestureScope, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, l<? super Offset, t0> lVar, PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super C03242> dVar) {
            super(2, dVar);
            this.$this_detectTapAndPress = pointerInputScope;
            this.$onPress = qVar;
            this.$onTap = lVar;
            this.$pressScope = pressGestureScopeImpl;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C03242 c03242 = new C03242(this.$this_detectTapAndPress, this.$onPress, this.$onTap, this.$pressScope, dVar);
            c03242.L$0 = obj;
            return c03242;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                k0 k0Var = (k0) this.L$0;
                PointerInputScope pointerInputScope = this.$this_detectTapAndPress;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(k0Var, this.$onPress, this.$onTap, this.$pressScope, null);
                this.label = 1;
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, anonymousClass1, this);
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

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C03242) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2", f = "TapGestureDetector.kt", l = {99}, m = "invokeSuspend")
    public static final class C03252 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ l<Offset, t0> $onDoubleTap;
        final /* synthetic */ l<Offset, t0> $onLongPress;
        final /* synthetic */ q<PressGestureScope, Offset, d7.d<? super t0>, Object> $onPress;
        final /* synthetic */ l<Offset, t0> $onTap;
        final /* synthetic */ PointerInputScope $this_detectTapGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1", f = "TapGestureDetector.kt", l = {100, 114, TsExtractor.TS_STREAM_TYPE_AC3, 141, 156, 178}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
            final /* synthetic */ k0 $$this$coroutineScope;
            final /* synthetic */ l<Offset, t0> $onDoubleTap;
            final /* synthetic */ l<Offset, t0> $onLongPress;
            final /* synthetic */ q<PressGestureScope, Offset, d7.d<? super t0>, Object> $onPress;
            final /* synthetic */ l<Offset, t0> $onTap;
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            long J$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$1", f = "TapGestureDetector.kt", l = {MediaError.DetailedErrorCode.MEDIA_NETWORK}, m = "invokeSuspend")
            public static final class C00251 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00251(PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super C00251> dVar) {
                    super(2, dVar);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new C00251(this.$pressScope, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                        this.label = 1;
                        Object objReset = pressGestureScopeImpl.reset(this);
                        e7.a aVar = e7.a.f15033i;
                        if (objReset == aVar) {
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
                    return ((C00251) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$10, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$10", f = "TapGestureDetector.kt", l = {}, m = "invokeSuspend")
            public static final class AnonymousClass10 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass10(PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super AnonymousClass10> dVar) {
                    super(2, dVar);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new AnonymousClass10(this.$pressScope, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    this.$pressScope.release();
                    return t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                    return ((AnonymousClass10) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$2, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$2", f = "TapGestureDetector.kt", l = {106}, m = "invokeSuspend")
            public static final class C00262 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ PointerInputChange $down;
                final /* synthetic */ q<PressGestureScope, Offset, d7.d<? super t0>, Object> $onPress;
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C00262(q<? super PressGestureScope, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, PressGestureScopeImpl pressGestureScopeImpl, PointerInputChange pointerInputChange, d7.d<? super C00262> dVar) {
                    super(2, dVar);
                    this.$onPress = qVar;
                    this.$pressScope = pressGestureScopeImpl;
                    this.$down = pointerInputChange;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new C00262(this.$onPress, this.$pressScope, this.$down, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        q<PressGestureScope, Offset, d7.d<? super t0>, Object> qVar = this.$onPress;
                        PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                        Offset offsetM3225boximpl = Offset.m3225boximpl(this.$down.getPosition());
                        this.label = 1;
                        Object objInvoke = qVar.invoke(pressGestureScopeImpl, offsetM3225boximpl, this);
                        e7.a aVar = e7.a.f15033i;
                        if (objInvoke == aVar) {
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
                    return ((C00262) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$3, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$3", f = "TapGestureDetector.kt", l = {115}, m = "invokeSuspend")
            public static final class AnonymousClass3 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super PointerInputChange>, Object> {
                private /* synthetic */ Object L$0;
                int label;

                public AnonymousClass3(d7.d<? super AnonymousClass3> dVar) {
                    super(2, dVar);
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    AnonymousClass3 anonymousClass3 = new AnonymousClass3(dVar);
                    anonymousClass3.L$0 = obj;
                    return anonymousClass3;
                }

                @Override // r7.p
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super PointerInputChange> dVar) {
                    return ((AnonymousClass3) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 != 0) {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                        return obj;
                    }
                    k2.c.G(obj);
                    AwaitPointerEventScope awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                    this.label = 1;
                    Object objWaitForUpOrCancellation$default = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope, null, this, 1, null);
                    e7.a aVar = e7.a.f15033i;
                    return objWaitForUpOrCancellation$default == aVar ? aVar : objWaitForUpOrCancellation$default;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$4, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$4", f = "TapGestureDetector.kt", l = {}, m = "invokeSuspend")
            public static final class AnonymousClass4 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super AnonymousClass4> dVar) {
                    super(2, dVar);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new AnonymousClass4(this.$pressScope, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    this.$pressScope.cancel();
                    return t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                    return ((AnonymousClass4) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$5, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$5", f = "TapGestureDetector.kt", l = {}, m = "invokeSuspend")
            public static final class AnonymousClass5 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass5(PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super AnonymousClass5> dVar) {
                    super(2, dVar);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new AnonymousClass5(this.$pressScope, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    this.$pressScope.release();
                    return t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                    return ((AnonymousClass5) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$6, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$6", f = "TapGestureDetector.kt", l = {}, m = "invokeSuspend")
            public static final class AnonymousClass6 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass6(PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super AnonymousClass6> dVar) {
                    super(2, dVar);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new AnonymousClass6(this.$pressScope, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    this.$pressScope.release();
                    return t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                    return ((AnonymousClass6) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$7, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$7", f = "TapGestureDetector.kt", l = {148}, m = "invokeSuspend")
            public static final class AnonymousClass7 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass7(PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super AnonymousClass7> dVar) {
                    super(2, dVar);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new AnonymousClass7(this.$pressScope, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                        this.label = 1;
                        Object objReset = pressGestureScopeImpl.reset(this);
                        e7.a aVar = e7.a.f15033i;
                        if (objReset == aVar) {
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
                    return ((AnonymousClass7) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$8, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$8", f = "TapGestureDetector.kt", l = {151}, m = "invokeSuspend")
            public static final class AnonymousClass8 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ q<PressGestureScope, Offset, d7.d<? super t0>, Object> $onPress;
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                final /* synthetic */ PointerInputChange $secondDown;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public AnonymousClass8(q<? super PressGestureScope, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, PressGestureScopeImpl pressGestureScopeImpl, PointerInputChange pointerInputChange, d7.d<? super AnonymousClass8> dVar) {
                    super(2, dVar);
                    this.$onPress = qVar;
                    this.$pressScope = pressGestureScopeImpl;
                    this.$secondDown = pointerInputChange;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new AnonymousClass8(this.$onPress, this.$pressScope, this.$secondDown, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        q<PressGestureScope, Offset, d7.d<? super t0>, Object> qVar = this.$onPress;
                        PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                        Offset offsetM3225boximpl = Offset.m3225boximpl(this.$secondDown.getPosition());
                        this.label = 1;
                        Object objInvoke = qVar.invoke(pressGestureScopeImpl, offsetM3225boximpl, this);
                        e7.a aVar = e7.a.f15033i;
                        if (objInvoke == aVar) {
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
                    return ((AnonymousClass8) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$9, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$9", f = "TapGestureDetector.kt", l = {157}, m = "invokeSuspend")
            public static final class AnonymousClass9 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
                final /* synthetic */ k0 $$this$coroutineScope;
                final /* synthetic */ l<Offset, t0> $onDoubleTap;
                final /* synthetic */ l<Offset, t0> $onTap;
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                final /* synthetic */ kotlin.jvm.internal.k0 $upOrCancel;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$9$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
                @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$9$1", f = "TapGestureDetector.kt", l = {}, m = "invokeSuspend")
                public static final class C00271 extends j implements p<k0, d7.d<? super t0>, Object> {
                    final /* synthetic */ PressGestureScopeImpl $pressScope;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00271(PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super C00271> dVar) {
                        super(2, dVar);
                        this.$pressScope = pressGestureScopeImpl;
                    }

                    @Override // f7.a
                    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                        return new C00271(this.$pressScope, dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                        this.$pressScope.release();
                        return t0.f22605a;
                    }

                    @Override // r7.p
                    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                        return ((C00271) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$9$2, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
                @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$9$2", f = "TapGestureDetector.kt", l = {}, m = "invokeSuspend")
                public static final class C00282 extends j implements p<k0, d7.d<? super t0>, Object> {
                    final /* synthetic */ PressGestureScopeImpl $pressScope;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00282(PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super C00282> dVar) {
                        super(2, dVar);
                        this.$pressScope = pressGestureScopeImpl;
                    }

                    @Override // f7.a
                    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                        return new C00282(this.$pressScope, dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                        this.$pressScope.cancel();
                        return t0.f22605a;
                    }

                    @Override // r7.p
                    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                        return ((C00282) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public AnonymousClass9(k0 k0Var, l<? super Offset, t0> lVar, l<? super Offset, t0> lVar2, kotlin.jvm.internal.k0 k0Var2, PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super AnonymousClass9> dVar) {
                    super(2, dVar);
                    this.$$this$coroutineScope = k0Var;
                    this.$onDoubleTap = lVar;
                    this.$onTap = lVar2;
                    this.$upOrCancel = k0Var2;
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    AnonymousClass9 anonymousClass9 = new AnonymousClass9(this.$$this$coroutineScope, this.$onDoubleTap, this.$onTap, this.$upOrCancel, this.$pressScope, dVar);
                    anonymousClass9.L$0 = obj;
                    return anonymousClass9;
                }

                @Override // r7.p
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
                    return ((AnonymousClass9) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        AwaitPointerEventScope awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                        this.label = 1;
                        obj = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope, null, this, 1, null);
                        e7.a aVar = e7.a.f15033i;
                        if (obj == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                    }
                    PointerInputChange pointerInputChange = (PointerInputChange) obj;
                    t0 t0Var = t0.f22605a;
                    if (pointerInputChange != null) {
                        pointerInputChange.consume();
                        m0.p(this.$$this$coroutineScope, null, 0, new C00271(this.$pressScope, null), 3);
                        this.$onDoubleTap.invoke(Offset.m3225boximpl(pointerInputChange.getPosition()));
                        return t0Var;
                    }
                    m0.p(this.$$this$coroutineScope, null, 0, new C00282(this.$pressScope, null), 3);
                    l<Offset, t0> lVar = this.$onTap;
                    if (lVar == null) {
                        return null;
                    }
                    lVar.invoke(Offset.m3225boximpl(((PointerInputChange) this.$upOrCancel.f19746i).getPosition()));
                    return t0Var;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(k0 k0Var, q<? super PressGestureScope, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, l<? super Offset, t0> lVar, l<? super Offset, t0> lVar2, l<? super Offset, t0> lVar3, PressGestureScopeImpl pressGestureScopeImpl, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$$this$coroutineScope = k0Var;
                this.$onPress = qVar;
                this.$onLongPress = lVar;
                this.$onDoubleTap = lVar2;
                this.$onTap = lVar3;
                this.$pressScope = pressGestureScopeImpl;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$coroutineScope, this.$onPress, this.$onLongPress, this.$onDoubleTap, this.$onTap, this.$pressScope, dVar);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // r7.p
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
                return ((AnonymousClass1) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Code restructure failed: missing block: B:44:0x0134, code lost:
            
                if (androidx.compose.foundation.gestures.TapGestureDetectorKt.consumeUntilUp(r4, r17) != r9) goto L46;
             */
            /* JADX WARN: Code restructure failed: missing block: B:67:0x01d0, code lost:
            
                if (r2.withTimeout(r0, r10, r17) == r9) goto L78;
             */
            /* JADX WARN: Code restructure failed: missing block: B:77:0x0204, code lost:
            
                if (androidx.compose.foundation.gestures.TapGestureDetectorKt.consumeUntilUp(r2, r17) == r9) goto L78;
             */
            /* JADX WARN: Removed duplicated region for block: B:24:0x00a9  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00bb  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00c4  */
            /* JADX WARN: Removed duplicated region for block: B:33:0x00e8  */
            /* JADX WARN: Removed duplicated region for block: B:36:0x00f1 A[Catch: PointerEventTimeoutCancellationException -> 0x00fe, TryCatch #4 {PointerEventTimeoutCancellationException -> 0x00fe, blocks: (B:34:0x00eb, B:36:0x00f1, B:38:0x0102), top: B:92:0x00eb }] */
            /* JADX WARN: Removed duplicated region for block: B:38:0x0102 A[Catch: PointerEventTimeoutCancellationException -> 0x00fe, TRY_LEAVE, TryCatch #4 {PointerEventTimeoutCancellationException -> 0x00fe, blocks: (B:34:0x00eb, B:36:0x00f1, B:38:0x0102), top: B:92:0x00eb }] */
            /* JADX WARN: Removed duplicated region for block: B:42:0x0119  */
            /* JADX WARN: Removed duplicated region for block: B:49:0x0149  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x017d  */
            /* JADX WARN: Removed duplicated region for block: B:63:0x0192  */
            /* JADX WARN: Removed duplicated region for block: B:72:0x01d9  */
            /* JADX WARN: Removed duplicated region for block: B:75:0x01ec  */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r18) {
                /*
                    Method dump skipped, instruction units count: 552
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.C03252.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03252(PointerInputScope pointerInputScope, q<? super PressGestureScope, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, l<? super Offset, t0> lVar, l<? super Offset, t0> lVar2, l<? super Offset, t0> lVar3, d7.d<? super C03252> dVar) {
            super(2, dVar);
            this.$this_detectTapGestures = pointerInputScope;
            this.$onPress = qVar;
            this.$onLongPress = lVar;
            this.$onDoubleTap = lVar2;
            this.$onTap = lVar3;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C03252 c03252 = new C03252(this.$this_detectTapGestures, this.$onPress, this.$onLongPress, this.$onDoubleTap, this.$onTap, dVar);
            c03252.L$0 = obj;
            return c03252;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                k0 k0Var = (k0) this.L$0;
                PressGestureScopeImpl pressGestureScopeImpl = new PressGestureScopeImpl(this.$this_detectTapGestures);
                PointerInputScope pointerInputScope = this.$this_detectTapGestures;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(k0Var, this.$onPress, this.$onLongPress, this.$onDoubleTap, this.$onTap, pressGestureScopeImpl, null);
                this.label = 1;
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, anonymousClass1, this);
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

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C03252) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForUpOrCancellation$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", l = {305, 320}, m = "waitForUpOrCancellation")
    public static final class C03262 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C03262(d7.d<? super C03262> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.waitForUpOrCancellation(null, null, this);
        }
    }

    @x6.e
    public static final /* synthetic */ Object awaitFirstDown(AwaitPointerEventScope awaitPointerEventScope, boolean z, d7.d dVar) {
        return awaitFirstDown(awaitPointerEventScope, z, PointerEventPass.Main, dVar);
    }

    public static /* synthetic */ Object awaitFirstDown$default(AwaitPointerEventScope awaitPointerEventScope, boolean z, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        return awaitFirstDown(awaitPointerEventScope, z, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object awaitSecondDown(AwaitPointerEventScope awaitPointerEventScope, PointerInputChange pointerInputChange, d7.d<? super PointerInputChange> dVar) {
        return awaitPointerEventScope.withTimeoutOrNull(awaitPointerEventScope.getViewConfiguration().getDoubleTapTimeoutMillis(), new C03232(pointerInputChange, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0041 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0050 A[LOOP:0: B:19:0x004e->B:20:0x0050, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003f -> B:18:0x0042). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object consumeUntilUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, d7.d<? super x6.t0> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.gestures.TapGestureDetectorKt$consumeUntilUp$1 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$consumeUntilUp$1 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$consumeUntilUp$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r7 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r7 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r7
            k2.c.G(r8)
            goto L42
        L29:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L31:
            k2.c.G(r8)
        L34:
            r0.L$0 = r7
            r0.label = r2
            r8 = 0
            java.lang.Object r8 = androidx.compose.ui.input.pointer.a.t(r7, r8, r0, r2, r8)
            e7.a r1 = e7.a.f15033i
            if (r8 != r1) goto L42
            return r1
        L42:
            androidx.compose.ui.input.pointer.PointerEvent r8 = (androidx.compose.ui.input.pointer.PointerEvent) r8
            java.util.List r1 = r8.getChanges()
            int r3 = r1.size()
            r4 = 0
            r5 = r4
        L4e:
            if (r5 >= r3) goto L5c
            java.lang.Object r6 = r1.get(r5)
            androidx.compose.ui.input.pointer.PointerInputChange r6 = (androidx.compose.ui.input.pointer.PointerInputChange) r6
            r6.consume()
            int r5 = r5 + 1
            goto L4e
        L5c:
            java.util.List r8 = r8.getChanges()
            int r1 = r8.size()
        L64:
            if (r4 >= r1) goto L76
            java.lang.Object r3 = r8.get(r4)
            androidx.compose.ui.input.pointer.PointerInputChange r3 = (androidx.compose.ui.input.pointer.PointerInputChange) r3
            boolean r3 = r3.getPressed()
            if (r3 == 0) goto L73
            goto L34
        L73:
            int r4 = r4 + 1
            goto L64
        L76:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.consumeUntilUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope, d7.d):java.lang.Object");
    }

    public static final Object detectTapAndPress(PointerInputScope pointerInputScope, q<? super PressGestureScope, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, l<? super Offset, t0> lVar, d7.d<? super t0> dVar) {
        Object objC = l0.c(new C03242(pointerInputScope, qVar, lVar, new PressGestureScopeImpl(pointerInputScope), null), dVar);
        return objC == e7.a.f15033i ? objC : t0.f22605a;
    }

    public static /* synthetic */ Object detectTapAndPress$default(PointerInputScope pointerInputScope, q qVar, l lVar, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            qVar = NoPressGesture;
        }
        if ((i10 & 2) != 0) {
            lVar = null;
        }
        return detectTapAndPress(pointerInputScope, qVar, lVar, dVar);
    }

    public static final Object detectTapGestures(PointerInputScope pointerInputScope, l<? super Offset, t0> lVar, l<? super Offset, t0> lVar2, q<? super PressGestureScope, ? super Offset, ? super d7.d<? super t0>, ? extends Object> qVar, l<? super Offset, t0> lVar3, d7.d<? super t0> dVar) {
        Object objC = l0.c(new C03252(pointerInputScope, qVar, lVar2, lVar, lVar3, null), dVar);
        return objC == e7.a.f15033i ? objC : t0.f22605a;
    }

    public static /* synthetic */ Object detectTapGestures$default(PointerInputScope pointerInputScope, l lVar, l lVar2, q qVar, l lVar3, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = null;
        }
        if ((i10 & 2) != 0) {
            lVar2 = null;
        }
        if ((i10 & 4) != 0) {
            qVar = NoPressGesture;
        }
        if ((i10 & 8) != 0) {
            lVar3 = null;
        }
        return detectTapGestures(pointerInputScope, lVar, lVar2, qVar, lVar3, dVar);
    }

    public static /* synthetic */ Object waitForUpOrCancellation$default(AwaitPointerEventScope awaitPointerEventScope, PointerEventPass pointerEventPass, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return waitForUpOrCancellation(awaitPointerEventScope, pointerEventPass, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0075 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x004c -> B:18:0x004f). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitFirstDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r8, boolean r9, androidx.compose.ui.input.pointer.PointerEventPass r10, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r11) {
        /*
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt.AnonymousClass2
            if (r0 == 0) goto L13
            r0 = r11
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3b
            if (r1 != r2) goto L33
            boolean r8 = r0.Z$0
            java.lang.Object r9 = r0.L$1
            androidx.compose.ui.input.pointer.PointerEventPass r9 = (androidx.compose.ui.input.pointer.PointerEventPass) r9
            java.lang.Object r10 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r10 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r10
            k2.c.G(r11)
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r7
            goto L4f
        L33:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3b:
            k2.c.G(r11)
        L3e:
            r0.L$0 = r8
            r0.L$1 = r10
            r0.Z$0 = r9
            r0.label = r2
            java.lang.Object r11 = r8.awaitPointerEvent(r10, r0)
            e7.a r1 = e7.a.f15033i
            if (r11 != r1) goto L4f
            return r1
        L4f:
            androidx.compose.ui.input.pointer.PointerEvent r11 = (androidx.compose.ui.input.pointer.PointerEvent) r11
            java.util.List r1 = r11.getChanges()
            int r3 = r1.size()
            r4 = 0
            r5 = r4
        L5b:
            if (r5 >= r3) goto L75
            java.lang.Object r6 = r1.get(r5)
            androidx.compose.ui.input.pointer.PointerInputChange r6 = (androidx.compose.ui.input.pointer.PointerInputChange) r6
            if (r9 == 0) goto L6a
            boolean r6 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDown(r6)
            goto L6e
        L6a:
            boolean r6 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDownIgnoreConsumed(r6)
        L6e:
            if (r6 != 0) goto L72
            r1 = r4
            goto L76
        L72:
            int r5 = r5 + 1
            goto L5b
        L75:
            r1 = r2
        L76:
            if (r1 == 0) goto L3e
            java.util.List r8 = r11.getChanges()
            java.lang.Object r8 = r8.get(r4)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, boolean, androidx.compose.ui.input.pointer.PointerEventPass, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object awaitFirstDown$default(AwaitPointerEventScope awaitPointerEventScope, boolean z, PointerEventPass pointerEventPass, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        if ((i10 & 2) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return awaitFirstDown(awaitPointerEventScope, z, pointerEventPass, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b1, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00be, code lost:
    
        if (r0 == r7) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x00be -> B:13:0x0035). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object waitForUpOrCancellation(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, androidx.compose.ui.input.pointer.PointerEventPass r18, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r19) {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.waitForUpOrCancellation(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.ui.input.pointer.PointerEventPass, d7.d):java.lang.Object");
    }
}
