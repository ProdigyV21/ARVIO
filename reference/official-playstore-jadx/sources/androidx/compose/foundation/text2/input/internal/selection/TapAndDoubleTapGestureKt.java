package androidx.compose.foundation.text2.input.internal.selection;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import f7.h;
import f7.j;
import ka.k0;
import ka.l0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a,\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u0080@¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001e\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u00072\u0006\u0010\t\u001a\u00020\bH\u0082@¢\u0006\u0004\b\n\u0010\u000b\u001a\u0014\u0010\f\u001a\u00020\u0004*\u00020\u0007H\u0082@¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Landroidx/compose/foundation/text2/input/internal/selection/TapOnPosition;", "onTap", "onDoubleTap", "Lx6/t0;", "detectTapAndDoubleTap", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text2/input/internal/selection/TapOnPosition;Landroidx/compose/foundation/text2/input/internal/selection/TapOnPosition;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "firstUp", "awaitSecondDown", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/ui/input/pointer/PointerInputChange;Ld7/d;)Ljava/lang/Object;", "consumeUntilUp", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Ld7/d;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TapAndDoubleTapGestureKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$awaitSecondDown$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$awaitSecondDown$2", f = "TapAndDoubleTapGesture.kt", l = {97}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends h implements p<AwaitPointerEventScope, d<? super PointerInputChange>, Object> {
        final /* synthetic */ PointerInputChange $firstUp;
        long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(PointerInputChange pointerInputChange, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$firstUp = pointerInputChange;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$firstUp, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super PointerInputChange> dVar) {
            return ((AnonymousClass2) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
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
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$consumeUntilUp$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt", f = "TapAndDoubleTapGesture.kt", l = {108}, m = "consumeUntilUp")
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
            return TapAndDoubleTapGestureKt.consumeUntilUp(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$detectTapAndDoubleTap$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$detectTapAndDoubleTap$2", f = "TapAndDoubleTapGesture.kt", l = {43}, m = "invokeSuspend")
    public static final class C04202 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ TapOnPosition $onDoubleTap;
        final /* synthetic */ TapOnPosition $onTap;
        final /* synthetic */ PointerInputScope $this_detectTapAndDoubleTap;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$detectTapAndDoubleTap$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$detectTapAndDoubleTap$2$1", f = "TapAndDoubleTapGesture.kt", l = {44, 50, 54, 64, 69, 77}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
            final /* synthetic */ TapOnPosition $onDoubleTap;
            final /* synthetic */ TapOnPosition $onTap;
            long J$0;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$detectTapAndDoubleTap$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @e(c = "androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$detectTapAndDoubleTap$2$1$1", f = "TapAndDoubleTapGesture.kt", l = {51}, m = "invokeSuspend")
            public static final class C00701 extends h implements p<AwaitPointerEventScope, d<? super PointerInputChange>, Object> {
                private /* synthetic */ Object L$0;
                int label;

                public C00701(d<? super C00701> dVar) {
                    super(2, dVar);
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    C00701 c00701 = new C00701(dVar);
                    c00701.L$0 = obj;
                    return c00701;
                }

                @Override // r7.p
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super PointerInputChange> dVar) {
                    return ((C00701) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
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
                    a aVar = a.f15033i;
                    return objWaitForUpOrCancellation$default == aVar ? aVar : objWaitForUpOrCancellation$default;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$detectTapAndDoubleTap$2$1$2, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
            @e(c = "androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$detectTapAndDoubleTap$2$1$2", f = "TapAndDoubleTapGesture.kt", l = {70}, m = "invokeSuspend")
            public static final class C00712 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
                final /* synthetic */ TapOnPosition $onDoubleTap;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00712(TapOnPosition tapOnPosition, d<? super C00712> dVar) {
                    super(2, dVar);
                    this.$onDoubleTap = tapOnPosition;
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    C00712 c00712 = new C00712(this.$onDoubleTap, dVar);
                    c00712.L$0 = obj;
                    return c00712;
                }

                @Override // r7.p
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
                    return ((C00712) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        AwaitPointerEventScope awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                        this.label = 1;
                        obj = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope, null, this, 1, null);
                        a aVar = a.f15033i;
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
                    if (pointerInputChange != null) {
                        pointerInputChange.consume();
                        this.$onDoubleTap.mo1146onEventk4lQ0M(pointerInputChange.getPosition());
                    }
                    return t0.f22605a;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TapOnPosition tapOnPosition, TapOnPosition tapOnPosition2, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$onTap = tapOnPosition;
                this.$onDoubleTap = tapOnPosition2;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$onTap, this.$onDoubleTap, dVar);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // r7.p
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
                return ((AnonymousClass1) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Code restructure failed: missing block: B:29:0x0097, code lost:
            
                if (androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt.consumeUntilUp(r0, r9) == r2) goto L48;
             */
            /* JADX WARN: Code restructure failed: missing block: B:44:0x00d1, code lost:
            
                if (r0.withTimeout(r3, r10, r9) == r2) goto L48;
             */
            /* JADX WARN: Code restructure failed: missing block: B:47:0x00dd, code lost:
            
                if (androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt.consumeUntilUp(r0, r9) == r2) goto L48;
             */
            /* JADX WARN: Removed duplicated region for block: B:25:0x0088  */
            /* JADX WARN: Removed duplicated region for block: B:33:0x009d  */
            /* JADX WARN: Removed duplicated region for block: B:53:0x00c1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                /*
                    Method dump skipped, instruction units count: 246
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt.C04202.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04202(PointerInputScope pointerInputScope, TapOnPosition tapOnPosition, TapOnPosition tapOnPosition2, d<? super C04202> dVar) {
            super(2, dVar);
            this.$this_detectTapAndDoubleTap = pointerInputScope;
            this.$onTap = tapOnPosition;
            this.$onDoubleTap = tapOnPosition2;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C04202(this.$this_detectTapAndDoubleTap, this.$onTap, this.$onDoubleTap, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PointerInputScope pointerInputScope = this.$this_detectTapAndDoubleTap;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$onTap, this.$onDoubleTap, null);
                this.label = 1;
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, anonymousClass1, this);
                a aVar = a.f15033i;
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
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C04202) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object awaitSecondDown(AwaitPointerEventScope awaitPointerEventScope, PointerInputChange pointerInputChange, d<? super PointerInputChange> dVar) {
        return awaitPointerEventScope.withTimeoutOrNull(awaitPointerEventScope.getViewConfiguration().getDoubleTapTimeoutMillis(), new AnonymousClass2(pointerInputChange, null), dVar);
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
            boolean r0 = r8 instanceof androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$consumeUntilUp$1 r0 = (androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$consumeUntilUp$1 r0 = new androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt$consumeUntilUp$1
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TapAndDoubleTapGestureKt.consumeUntilUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope, d7.d):java.lang.Object");
    }

    public static final Object detectTapAndDoubleTap(PointerInputScope pointerInputScope, TapOnPosition tapOnPosition, TapOnPosition tapOnPosition2, d<? super t0> dVar) {
        Object objC = l0.c(new C04202(pointerInputScope, tapOnPosition, tapOnPosition2, null), dVar);
        return objC == a.f15033i ? objC : t0.f22605a;
    }

    public static /* synthetic */ Object detectTapAndDoubleTap$default(PointerInputScope pointerInputScope, TapOnPosition tapOnPosition, TapOnPosition tapOnPosition2, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            tapOnPosition = null;
        }
        if ((i10 & 2) != 0) {
            tapOnPosition2 = null;
        }
        return detectTapAndDoubleTap(pointerInputScope, tapOnPosition, tapOnPosition2, dVar);
    }
}
