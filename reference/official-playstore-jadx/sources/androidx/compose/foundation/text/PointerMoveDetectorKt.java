package androidx.compose.foundation.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputScope;
import d7.d;
import e7.a;
import f7.e;
import f7.h;
import f7.j;
import k2.c;
import ka.k0;
import ka.l0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a2\u0010\u0007\u001a\u00020\u0005*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0080@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "pointerEventPass", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/t0;", "onMove", "detectMoves", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/ui/input/pointer/PointerEventPass;Lr7/l;Ld7/d;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PointerMoveDetectorKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text.PointerMoveDetectorKt$detectMoves$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text.PointerMoveDetectorKt$detectMoves$2", f = "PointerMoveDetector.kt", l = {41}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ l<Offset, t0> $onMove;
        final /* synthetic */ PointerEventPass $pointerEventPass;
        final /* synthetic */ PointerInputScope $this_detectMoves;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.PointerMoveDetectorKt$detectMoves$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text.PointerMoveDetectorKt$detectMoves$2$1", f = "PointerMoveDetector.kt", l = {44}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
            final /* synthetic */ d7.j $currentContext;
            final /* synthetic */ l<Offset, t0> $onMove;
            final /* synthetic */ PointerEventPass $pointerEventPass;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(d7.j jVar, PointerEventPass pointerEventPass, l<? super Offset, t0> lVar, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$currentContext = jVar;
                this.$pointerEventPass = pointerEventPass;
                this.$onMove = lVar;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$currentContext, this.$pointerEventPass, this.$onMove, dVar);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // r7.p
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
                return ((AnonymousClass1) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
            /* JADX WARN: Removed duplicated region for block: B:16:0x0053  */
            /* JADX WARN: Removed duplicated region for block: B:17:0x0055  */
            /* JADX WARN: Removed duplicated region for block: B:19:0x005f  */
            /* JADX WARN: Removed duplicated region for block: B:20:0x0061  */
            /* JADX WARN: Removed duplicated region for block: B:22:0x006b  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00a1  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x003e -> B:14:0x0041). Please report as a decompilation issue!!! */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r7) {
                /*
                    r6 = this;
                    int r0 = r6.label
                    r1 = 1
                    if (r0 == 0) goto L1b
                    if (r0 != r1) goto L13
                    java.lang.Object r0 = r6.L$1
                    kotlin.jvm.internal.k0 r0 = (kotlin.jvm.internal.k0) r0
                    java.lang.Object r2 = r6.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r2 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r2
                    k2.c.G(r7)
                    goto L41
                L13:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r0)
                    throw r7
                L1b:
                    k2.c.G(r7)
                    java.lang.Object r7 = r6.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r7 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r7
                    kotlin.jvm.internal.k0 r0 = new kotlin.jvm.internal.k0
                    r0.<init>()
                    r2 = r7
                L28:
                    d7.j r7 = r6.$currentContext
                    boolean r7 = ka.m0.m(r7)
                    if (r7 == 0) goto La1
                    androidx.compose.ui.input.pointer.PointerEventPass r7 = r6.$pointerEventPass
                    r6.L$0 = r2
                    r6.L$1 = r0
                    r6.label = r1
                    java.lang.Object r7 = r2.awaitPointerEvent(r7, r6)
                    e7.a r3 = e7.a.f15033i
                    if (r7 != r3) goto L41
                    return r3
                L41:
                    androidx.compose.ui.input.pointer.PointerEvent r7 = (androidx.compose.ui.input.pointer.PointerEvent) r7
                    int r3 = r7.getType()
                    androidx.compose.ui.input.pointer.PointerEventType$Companion r4 = androidx.compose.ui.input.pointer.PointerEventType.INSTANCE
                    int r5 = r4.m4473getMove7fucELk()
                    boolean r5 = androidx.compose.ui.input.pointer.PointerEventType.m4467equalsimpl0(r3, r5)
                    if (r5 == 0) goto L55
                    r5 = r1
                    goto L5d
                L55:
                    int r5 = r4.m4471getEnter7fucELk()
                    boolean r5 = androidx.compose.ui.input.pointer.PointerEventType.m4467equalsimpl0(r3, r5)
                L5d:
                    if (r5 == 0) goto L61
                    r3 = r1
                    goto L69
                L61:
                    int r4 = r4.m4472getExit7fucELk()
                    boolean r3 = androidx.compose.ui.input.pointer.PointerEventType.m4467equalsimpl0(r3, r4)
                L69:
                    if (r3 == 0) goto L28
                    java.util.List r7 = r7.getChanges()
                    java.lang.Object r7 = kotlin.collections.x.m0(r7)
                    androidx.compose.ui.input.pointer.PointerInputChange r7 = (androidx.compose.ui.input.pointer.PointerInputChange) r7
                    long r3 = r7.getPosition()
                    androidx.compose.ui.geometry.Offset r7 = androidx.compose.ui.geometry.Offset.m3225boximpl(r3)
                    long r3 = r7.getPackedValue()
                    java.lang.Object r5 = r0.f19746i
                    boolean r3 = androidx.compose.ui.geometry.Offset.m3232equalsimpl(r3, r5)
                    if (r3 != 0) goto L8a
                    goto L8b
                L8a:
                    r7 = 0
                L8b:
                    if (r7 == 0) goto L28
                    r7.l<androidx.compose.ui.geometry.Offset, x6.t0> r3 = r6.$onMove
                    long r4 = r7.getPackedValue()
                    androidx.compose.ui.geometry.Offset r7 = androidx.compose.ui.geometry.Offset.m3225boximpl(r4)
                    r0.f19746i = r7
                    androidx.compose.ui.geometry.Offset r7 = androidx.compose.ui.geometry.Offset.m3225boximpl(r4)
                    r3.invoke(r7)
                    goto L28
                La1:
                    x6.t0 r7 = x6.t0.f22605a
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.PointerMoveDetectorKt.AnonymousClass2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(PointerInputScope pointerInputScope, PointerEventPass pointerEventPass, l<? super Offset, t0> lVar, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$this_detectMoves = pointerInputScope;
            this.$pointerEventPass = pointerEventPass;
            this.$onMove = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$this_detectMoves, this.$pointerEventPass, this.$onMove, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                d7.j context = getContext();
                PointerInputScope pointerInputScope = this.$this_detectMoves;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(context, this.$pointerEventPass, this.$onMove, null);
                this.label = 1;
                Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(anonymousClass1, this);
                a aVar = a.f15033i;
                if (objAwaitPointerEventScope == aVar) {
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

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static final Object detectMoves(PointerInputScope pointerInputScope, PointerEventPass pointerEventPass, l<? super Offset, t0> lVar, d<? super t0> dVar) {
        Object objC = l0.c(new AnonymousClass2(pointerInputScope, pointerEventPass, lVar, null), dVar);
        return objC == a.f15033i ? objC : t0.f22605a;
    }

    public static /* synthetic */ Object detectMoves$default(PointerInputScope pointerInputScope, PointerEventPass pointerEventPass, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            pointerEventPass = PointerEventPass.Initial;
        }
        return detectMoves(pointerInputScope, pointerEventPass, lVar, dVar);
    }
}
