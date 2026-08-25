package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.TransformEvent;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import f7.j;
import java.util.concurrent.CancellationException;
import ka.k0;
import ka.l0;
import ka.m0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1", f = "Transformable.kt", l = {155}, m = "invokeSuspend")
public final class TransformableNode$pointerInputNode$1 extends j implements p<PointerInputScope, d7.d<? super t0>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TransformableNode this$0;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1$1", f = "Transformable.kt", l = {174}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ PointerInputScope $$this$SuspendingPointerInputModifierNode;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ TransformableNode this$0;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1$1$1", f = "Transformable.kt", l = {158, 161}, m = "invokeSuspend")
        public static final class C00291 extends j implements p<k0, d7.d<? super t0>, Object> {
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ TransformableNode this$0;

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/TransformScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/TransformScope;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1$1$1$1", f = "Transformable.kt", l = {166}, m = "invokeSuspend")
            public static final class C00301 extends j implements p<TransformScope, d7.d<? super t0>, Object> {
                final /* synthetic */ kotlin.jvm.internal.k0 $event;
                private /* synthetic */ Object L$0;
                Object L$1;
                int label;
                final /* synthetic */ TransformableNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00301(kotlin.jvm.internal.k0 k0Var, TransformableNode transformableNode, d7.d<? super C00301> dVar) {
                    super(2, dVar);
                    this.$event = k0Var;
                    this.this$0 = transformableNode;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    C00301 c00301 = new C00301(this.$event, this.this$0, dVar);
                    c00301.L$0 = obj;
                    return c00301;
                }

                @Override // r7.p
                public final Object invoke(TransformScope transformScope, d7.d<? super t0> dVar) {
                    return ((C00301) create(transformScope, dVar)).invokeSuspend(t0.f22605a);
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x002b  */
                /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0058 -> B:20:0x005b). Please report as a decompilation issue!!! */
                @Override // f7.a
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r6) {
                    /*
                        r5 = this;
                        int r0 = r5.label
                        r1 = 1
                        if (r0 == 0) goto L1b
                        if (r0 != r1) goto L13
                        java.lang.Object r0 = r5.L$1
                        kotlin.jvm.internal.k0 r0 = (kotlin.jvm.internal.k0) r0
                        java.lang.Object r2 = r5.L$0
                        androidx.compose.foundation.gestures.TransformScope r2 = (androidx.compose.foundation.gestures.TransformScope) r2
                        k2.c.G(r6)
                        goto L5b
                    L13:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r0)
                        throw r6
                    L1b:
                        k2.c.G(r6)
                        java.lang.Object r6 = r5.L$0
                        androidx.compose.foundation.gestures.TransformScope r6 = (androidx.compose.foundation.gestures.TransformScope) r6
                        r2 = r6
                    L23:
                        kotlin.jvm.internal.k0 r6 = r5.$event
                        java.lang.Object r6 = r6.f19746i
                        boolean r0 = r6 instanceof androidx.compose.foundation.gestures.TransformEvent.TransformStopped
                        if (r0 != 0) goto L5e
                        boolean r0 = r6 instanceof androidx.compose.foundation.gestures.TransformEvent.TransformDelta
                        if (r0 == 0) goto L32
                        androidx.compose.foundation.gestures.TransformEvent$TransformDelta r6 = (androidx.compose.foundation.gestures.TransformEvent.TransformDelta) r6
                        goto L33
                    L32:
                        r6 = 0
                    L33:
                        if (r6 == 0) goto L44
                        float r0 = r6.getZoomChange()
                        long r3 = r6.getPanChange()
                        float r6 = r6.getRotationChange()
                        r2.mo302transformByd4ec7I(r0, r3, r6)
                    L44:
                        kotlin.jvm.internal.k0 r0 = r5.$event
                        androidx.compose.foundation.gestures.TransformableNode r6 = r5.this$0
                        ma.j r6 = androidx.compose.foundation.gestures.TransformableNode.access$getChannel$p(r6)
                        r5.L$0 = r2
                        r5.L$1 = r0
                        r5.label = r1
                        java.lang.Object r6 = r6.receive(r5)
                        e7.a r3 = e7.a.f15033i
                        if (r6 != r3) goto L5b
                        return r3
                    L5b:
                        r0.f19746i = r6
                        goto L23
                    L5e:
                        x6.t0 r6 = x6.t0.f22605a
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1.AnonymousClass1.C00291.C00301.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00291(TransformableNode transformableNode, d7.d<? super C00291> dVar) {
                super(2, dVar);
                this.this$0 = transformableNode;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                C00291 c00291 = new C00291(this.this$0, dVar);
                c00291.L$0 = obj;
                return c00291;
            }

            /* JADX WARN: Code restructure failed: missing block: B:22:0x007b, code lost:
            
                if (r10.transform(r0, r6, r9) == r3) goto L23;
             */
            /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
            /* JADX WARN: Removed duplicated region for block: B:25:0x0080  */
            /* JADX WARN: Removed duplicated region for block: B:29:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x007e -> B:13:0x0034). Please report as a decompilation issue!!! */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                /*
                    r9 = this;
                    int r0 = r9.label
                    r1 = 2
                    r2 = 1
                    e7.a r3 = e7.a.f15033i
                    if (r0 == 0) goto L2d
                    if (r0 == r2) goto L1d
                    if (r0 != r1) goto L15
                    java.lang.Object r0 = r9.L$0
                    ka.k0 r0 = (ka.k0) r0
                    k2.c.G(r10)     // Catch: java.util.concurrent.CancellationException -> L13
                L13:
                    r10 = r0
                    goto L34
                L15:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r0)
                    throw r10
                L1d:
                    java.lang.Object r0 = r9.L$2
                    kotlin.jvm.internal.k0 r0 = (kotlin.jvm.internal.k0) r0
                    java.lang.Object r4 = r9.L$1
                    kotlin.jvm.internal.k0 r4 = (kotlin.jvm.internal.k0) r4
                    java.lang.Object r5 = r9.L$0
                    ka.k0 r5 = (ka.k0) r5
                    k2.c.G(r10)
                    goto L57
                L2d:
                    k2.c.G(r10)
                    java.lang.Object r10 = r9.L$0
                    ka.k0 r10 = (ka.k0) r10
                L34:
                    boolean r0 = ka.l0.d(r10)
                    if (r0 == 0) goto L80
                    kotlin.jvm.internal.k0 r0 = new kotlin.jvm.internal.k0
                    r0.<init>()
                    androidx.compose.foundation.gestures.TransformableNode r4 = r9.this$0
                    ma.j r4 = androidx.compose.foundation.gestures.TransformableNode.access$getChannel$p(r4)
                    r9.L$0 = r10
                    r9.L$1 = r0
                    r9.L$2 = r0
                    r9.label = r2
                    java.lang.Object r4 = r4.receive(r9)
                    if (r4 != r3) goto L54
                    goto L7d
                L54:
                    r5 = r10
                    r10 = r4
                    r4 = r0
                L57:
                    r0.f19746i = r10
                    java.lang.Object r10 = r4.f19746i
                    boolean r10 = r10 instanceof androidx.compose.foundation.gestures.TransformEvent.TransformStarted
                    if (r10 == 0) goto L7e
                    androidx.compose.foundation.gestures.TransformableNode r10 = r9.this$0     // Catch: java.util.concurrent.CancellationException -> L7e
                    androidx.compose.foundation.gestures.TransformableState r10 = androidx.compose.foundation.gestures.TransformableNode.access$getState$p(r10)     // Catch: java.util.concurrent.CancellationException -> L7e
                    androidx.compose.foundation.MutatePriority r0 = androidx.compose.foundation.MutatePriority.UserInput     // Catch: java.util.concurrent.CancellationException -> L7e
                    androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1$1$1$1 r6 = new androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1$1$1$1     // Catch: java.util.concurrent.CancellationException -> L7e
                    androidx.compose.foundation.gestures.TransformableNode r7 = r9.this$0     // Catch: java.util.concurrent.CancellationException -> L7e
                    r8 = 0
                    r6.<init>(r4, r7, r8)     // Catch: java.util.concurrent.CancellationException -> L7e
                    r9.L$0 = r5     // Catch: java.util.concurrent.CancellationException -> L7e
                    r9.L$1 = r8     // Catch: java.util.concurrent.CancellationException -> L7e
                    r9.L$2 = r8     // Catch: java.util.concurrent.CancellationException -> L7e
                    r9.label = r1     // Catch: java.util.concurrent.CancellationException -> L7e
                    java.lang.Object r10 = r10.transform(r0, r6, r9)     // Catch: java.util.concurrent.CancellationException -> L7e
                    if (r10 != r3) goto L7e
                L7d:
                    return r3
                L7e:
                    r10 = r5
                    goto L34
                L80:
                    x6.t0 r10 = x6.t0.f22605a
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1.AnonymousClass1.C00291.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((C00291) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.foundation.gestures.TransformableNode$pointerInputNode$1$1$2", f = "Transformable.kt", l = {176}, m = "invokeSuspend")
        public static final class AnonymousClass2 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
            final /* synthetic */ k0 $$this$coroutineScope;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ TransformableNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(TransformableNode transformableNode, k0 k0Var, d7.d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.this$0 = transformableNode;
                this.$$this$coroutineScope = k0Var;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, this.$$this$coroutineScope, dVar);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // r7.p
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
                return ((AnonymousClass2) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                try {
                    try {
                        if (i10 == 0) {
                            k2.c.G(obj);
                            AwaitPointerEventScope awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                            boolean z = this.this$0.lockRotationOnZoomPan;
                            ma.j jVar = this.this$0.channel;
                            l lVar = this.this$0.updatedCanPan;
                            this.label = 1;
                            Object objDetectZoom = TransformableKt.detectZoom(awaitPointerEventScope, z, jVar, lVar, this);
                            e7.a aVar = e7.a.f15033i;
                            if (objDetectZoom == aVar) {
                                return aVar;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            k2.c.G(obj);
                        }
                    } catch (CancellationException e5) {
                        if (!l0.d(this.$$this$coroutineScope)) {
                            throw e5;
                        }
                    }
                    return t0.f22605a;
                } finally {
                    this.this$0.channel.mo6685trySendJP2dKIU(TransformEvent.TransformStopped.INSTANCE);
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(PointerInputScope pointerInputScope, TransformableNode transformableNode, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$$this$SuspendingPointerInputModifierNode = pointerInputScope;
            this.this$0 = transformableNode;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$SuspendingPointerInputModifierNode, this.this$0, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                k0 k0Var = (k0) this.L$0;
                m0.p(k0Var, null, 4, new C00291(this.this$0, null), 1);
                PointerInputScope pointerInputScope = this.$$this$SuspendingPointerInputModifierNode;
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, k0Var, null);
                this.label = 1;
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, anonymousClass2, this);
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
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransformableNode$pointerInputNode$1(TransformableNode transformableNode, d7.d<? super TransformableNode$pointerInputNode$1> dVar) {
        super(2, dVar);
        this.this$0 = transformableNode;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TransformableNode$pointerInputNode$1 transformableNode$pointerInputNode$1 = new TransformableNode$pointerInputNode$1(this.this$0, dVar);
        transformableNode$pointerInputNode$1.L$0 = obj;
        return transformableNode$pointerInputNode$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super t0> dVar) {
        return ((TransformableNode$pointerInputNode$1) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            if (!this.this$0.enabled) {
                return t0Var;
            }
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(pointerInputScope, this.this$0, null);
            this.label = 1;
            Object objC = l0.c(anonymousClass1, this);
            e7.a aVar = e7.a.f15033i;
            if (objC == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0Var;
    }
}
