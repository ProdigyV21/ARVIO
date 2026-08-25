package androidx.compose.foundation.text2.input.internal.selection;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.media3.extractor.flac.FlacConstants;
import d7.d;
import f7.e;
import f7.h;
import kotlin.Metadata;
import r7.a;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a.\u0010\u0006\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0080@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Landroidx/compose/foundation/text2/input/internal/selection/TapOnPosition;", "onDown", "Lkotlin/Function0;", "Lx6/t0;", "onUp", "detectPressDownGesture", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text2/input/internal/selection/TapOnPosition;Lr7/a;Ld7/d;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PressDownGestureKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.PressDownGestureKt$detectPressDownGesture$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.PressDownGestureKt$detectPressDownGesture$2", f = "PressDownGesture.kt", l = {32, FlacConstants.STREAM_INFO_BLOCK_SIZE}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
        final /* synthetic */ TapOnPosition $onDown;
        final /* synthetic */ a<t0> $onUp;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TapOnPosition tapOnPosition, a<t0> aVar, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$onDown = tapOnPosition;
            this.$onUp = aVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$onDown, this.$onUp, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
            return ((AnonymousClass2) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x005f, code lost:
        
            if (r14 != r3) goto L20;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x005f -> B:20:0x0062). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                r13 = this;
                int r0 = r13.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L2a
                if (r0 == r2) goto L21
                if (r0 != r1) goto L19
                java.lang.Object r0 = r13.L$1
                androidx.compose.ui.input.pointer.PointerInputChange r0 = (androidx.compose.ui.input.pointer.PointerInputChange) r0
                java.lang.Object r4 = r13.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                k2.c.G(r14)
                r7 = r13
                goto L62
            L19:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r0)
                throw r14
            L21:
                java.lang.Object r0 = r13.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                k2.c.G(r14)
                r7 = r13
                goto L43
            L2a:
                k2.c.G(r14)
                java.lang.Object r14 = r13.L$0
                r4 = r14
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                r13.L$0 = r4
                r13.label = r2
                r5 = 0
                r6 = 0
                r8 = 2
                r9 = 0
                r7 = r13
                java.lang.Object r14 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r4, r5, r6, r7, r8, r9)
                if (r14 != r3) goto L42
                goto L61
            L42:
                r0 = r4
            L43:
                androidx.compose.ui.input.pointer.PointerInputChange r14 = (androidx.compose.ui.input.pointer.PointerInputChange) r14
                androidx.compose.foundation.text2.input.internal.selection.TapOnPosition r4 = r7.$onDown
                long r5 = r14.getPosition()
                r4.mo1146onEventk4lQ0M(r5)
                r7.a<x6.t0> r4 = r7.$onUp
                if (r4 == 0) goto L92
                r4 = r0
                r0 = r14
            L54:
                r7.L$0 = r4
                r7.L$1 = r0
                r7.label = r1
                r14 = 0
                java.lang.Object r14 = androidx.compose.ui.input.pointer.a.t(r4, r14, r13, r2, r14)
                if (r14 != r3) goto L62
            L61:
                return r3
            L62:
                androidx.compose.ui.input.pointer.PointerEvent r14 = (androidx.compose.ui.input.pointer.PointerEvent) r14
                java.util.List r14 = r14.getChanges()
                int r5 = r14.size()
                r6 = 0
            L6d:
                if (r6 >= r5) goto L8d
                java.lang.Object r8 = r14.get(r6)
                androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
                long r9 = r8.getId()
                long r11 = r0.getId()
                boolean r9 = androidx.compose.ui.input.pointer.PointerId.m4500equalsimpl0(r9, r11)
                if (r9 == 0) goto L8a
                boolean r8 = r8.getPressed()
                if (r8 == 0) goto L8a
                goto L54
            L8a:
                int r6 = r6 + 1
                goto L6d
            L8d:
                r7.a<x6.t0> r14 = r7.$onUp
                r14.invoke()
            L92:
                x6.t0 r14 = x6.t0.f22605a
                return r14
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.PressDownGestureKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final Object detectPressDownGesture(PointerInputScope pointerInputScope, TapOnPosition tapOnPosition, a<t0> aVar, d<? super t0> dVar) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new AnonymousClass2(tapOnPosition, aVar, null), dVar);
        return objAwaitEachGesture == e7.a.f15033i ? objAwaitEachGesture : t0.f22605a;
    }

    public static /* synthetic */ Object detectPressDownGesture$default(PointerInputScope pointerInputScope, TapOnPosition tapOnPosition, a aVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            aVar = null;
        }
        return detectPressDownGesture(pointerInputScope, tapOnPosition, aVar, dVar);
    }
}
