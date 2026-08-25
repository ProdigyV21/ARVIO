package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.text.TextLayoutResult;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.foundation.text.ClickableTextKt$ClickableText$pointerInputModifier$1", f = "ClickableText.kt", l = {177}, m = "invokeSuspend")
public final class ClickableTextKt$ClickableText$pointerInputModifier$1 extends j implements p<PointerInputScope, d<? super t0>, Object> {
    final /* synthetic */ k0 $coroutineScope;
    final /* synthetic */ MutableState<TextLayoutResult> $layoutResult;
    final /* synthetic */ l<Integer, t0> $onClick;
    final /* synthetic */ l<Integer, t0> $onHover;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.ClickableTextKt$ClickableText$pointerInputModifier$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text.ClickableTextKt$ClickableText$pointerInputModifier$1$1", f = "ClickableText.kt", l = {168}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ PointerInputScope $$this$pointerInput;
        final /* synthetic */ MutableState<TextLayoutResult> $layoutResult;
        final /* synthetic */ l<Integer, t0> $onHover;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.ClickableTextKt$ClickableText$pointerInputModifier$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "pos", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C00391 extends r implements l<Offset, t0> {
            final /* synthetic */ MutableState<TextLayoutResult> $layoutResult;
            final /* synthetic */ l<Integer, t0> $onHover;
            final /* synthetic */ kotlin.jvm.internal.k0 $previousIndex;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C00391(kotlin.jvm.internal.k0 k0Var, l<? super Integer, t0> lVar, MutableState<TextLayoutResult> mutableState) {
                super(1);
                this.$previousIndex = k0Var;
                this.$onHover = lVar;
                this.$layoutResult = mutableState;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m814invokek4lQ0M(((Offset) obj).getPackedValue());
                return t0.f22605a;
            }

            /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
            public final void m814invokek4lQ0M(long j10) {
                Integer numClickableText_03UYbkw$getOffset = ClickableTextKt.ClickableText_03UYbkw$getOffset(this.$layoutResult, j10);
                if (kotlin.jvm.internal.p.a(this.$previousIndex.f19746i, numClickableText_03UYbkw$getOffset)) {
                    return;
                }
                this.$previousIndex.f19746i = numClickableText_03UYbkw$getOffset;
                this.$onHover.invoke(numClickableText_03UYbkw$getOffset);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(PointerInputScope pointerInputScope, l<? super Integer, t0> lVar, MutableState<TextLayoutResult> mutableState, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$$this$pointerInput = pointerInputScope;
            this.$onHover = lVar;
            this.$layoutResult = mutableState;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass1(this.$$this$pointerInput, this.$onHover, this.$layoutResult, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                kotlin.jvm.internal.k0 k0Var = new kotlin.jvm.internal.k0();
                PointerInputScope pointerInputScope = this.$$this$pointerInput;
                PointerEventPass pointerEventPass = PointerEventPass.Main;
                C00391 c00391 = new C00391(k0Var, this.$onHover, this.$layoutResult);
                this.label = 1;
                Object objDetectMoves = PointerMoveDetectorKt.detectMoves(pointerInputScope, pointerEventPass, c00391, this);
                a aVar = a.f15033i;
                if (objDetectMoves == aVar) {
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
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.ClickableTextKt$ClickableText$pointerInputModifier$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "pos", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Offset, t0> {
        final /* synthetic */ MutableState<TextLayoutResult> $layoutResult;
        final /* synthetic */ l<Integer, t0> $onClick;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super Integer, t0> lVar, MutableState<TextLayoutResult> mutableState) {
            super(1);
            this.$onClick = lVar;
            this.$layoutResult = mutableState;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m815invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m815invokek4lQ0M(long j10) {
            Integer numClickableText_03UYbkw$getOffset = ClickableTextKt.ClickableText_03UYbkw$getOffset(this.$layoutResult, j10);
            if (numClickableText_03UYbkw$getOffset != null) {
                this.$onClick.invoke(numClickableText_03UYbkw$getOffset);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ClickableTextKt$ClickableText$pointerInputModifier$1(k0 k0Var, l<? super Integer, t0> lVar, MutableState<TextLayoutResult> mutableState, l<? super Integer, t0> lVar2, d<? super ClickableTextKt$ClickableText$pointerInputModifier$1> dVar) {
        super(2, dVar);
        this.$coroutineScope = k0Var;
        this.$onHover = lVar;
        this.$layoutResult = mutableState;
        this.$onClick = lVar2;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        ClickableTextKt$ClickableText$pointerInputModifier$1 clickableTextKt$ClickableText$pointerInputModifier$1 = new ClickableTextKt$ClickableText$pointerInputModifier$1(this.$coroutineScope, this.$onHover, this.$layoutResult, this.$onClick, dVar);
        clickableTextKt$ClickableText$pointerInputModifier$1.L$0 = obj;
        return clickableTextKt$ClickableText$pointerInputModifier$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
        return ((ClickableTextKt$ClickableText$pointerInputModifier$1) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            m0.p(this.$coroutineScope, null, 0, new AnonymousClass1(pointerInputScope, this.$onHover, this.$layoutResult, null), 3);
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$onClick, this.$layoutResult);
            this.label = 1;
            Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, anonymousClass2, this, 7, null);
            a aVar = a.f15033i;
            if (objDetectTapGestures$default == aVar) {
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
