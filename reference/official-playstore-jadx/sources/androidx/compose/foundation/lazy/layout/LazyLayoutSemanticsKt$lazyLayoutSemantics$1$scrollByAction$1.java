package androidx.compose.foundation.lazy.layout;

import f7.e;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "x", "", "y", "invoke", "(FF)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutSemanticsKt$lazyLayoutSemantics$1$scrollByAction$1 extends r implements p<Float, Float, Boolean> {
    final /* synthetic */ k0 $coroutineScope;
    final /* synthetic */ boolean $isVertical;
    final /* synthetic */ LazyLayoutSemanticState $state;

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt$lazyLayoutSemantics$1$scrollByAction$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt$lazyLayoutSemantics$1$scrollByAction$1$1", f = "LazyLayoutSemantics.kt", l = {83}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ float $delta;
        final /* synthetic */ LazyLayoutSemanticState $state;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LazyLayoutSemanticState lazyLayoutSemanticState, float f10, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$state = lazyLayoutSemanticState;
            this.$delta = f10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$state, this.$delta, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                LazyLayoutSemanticState lazyLayoutSemanticState = this.$state;
                float f10 = this.$delta;
                this.label = 1;
                Object objAnimateScrollBy = lazyLayoutSemanticState.animateScrollBy(f10, this);
                e7.a aVar = e7.a.f15033i;
                if (objAnimateScrollBy == aVar) {
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
    public LazyLayoutSemanticsKt$lazyLayoutSemantics$1$scrollByAction$1(boolean z, k0 k0Var, LazyLayoutSemanticState lazyLayoutSemanticState) {
        super(2);
        this.$isVertical = z;
        this.$coroutineScope = k0Var;
        this.$state = lazyLayoutSemanticState;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Number) obj).floatValue(), ((Number) obj2).floatValue());
    }

    public final Boolean invoke(float f10, float f11) {
        if (this.$isVertical) {
            f10 = f11;
        }
        m0.p(this.$coroutineScope, null, 0, new AnonymousClass1(this.$state, f10, null), 3);
        return Boolean.TRUE;
    }
}
