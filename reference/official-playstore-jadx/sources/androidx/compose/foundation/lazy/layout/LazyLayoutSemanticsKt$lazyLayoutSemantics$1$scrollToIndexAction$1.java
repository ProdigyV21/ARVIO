package androidx.compose.foundation.lazy.layout;

import f7.e;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "index", "", "invoke", "(I)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutSemanticsKt$lazyLayoutSemantics$1$scrollToIndexAction$1 extends r implements l<Integer, Boolean> {
    final /* synthetic */ k0 $coroutineScope;
    final /* synthetic */ r7.a<LazyLayoutItemProvider> $itemProviderLambda;
    final /* synthetic */ LazyLayoutSemanticState $state;

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt$lazyLayoutSemantics$1$scrollToIndexAction$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt$lazyLayoutSemantics$1$scrollToIndexAction$1$2", f = "LazyLayoutSemantics.kt", l = {100}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ int $index;
        final /* synthetic */ LazyLayoutSemanticState $state;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(LazyLayoutSemanticState lazyLayoutSemanticState, int i10, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$state = lazyLayoutSemanticState;
            this.$index = i10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass2(this.$state, this.$index, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                LazyLayoutSemanticState lazyLayoutSemanticState = this.$state;
                int i11 = this.$index;
                this.label = 1;
                Object objScrollToItem = lazyLayoutSemanticState.scrollToItem(i11, this);
                e7.a aVar = e7.a.f15033i;
                if (objScrollToItem == aVar) {
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
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LazyLayoutSemanticsKt$lazyLayoutSemantics$1$scrollToIndexAction$1(r7.a<? extends LazyLayoutItemProvider> aVar, k0 k0Var, LazyLayoutSemanticState lazyLayoutSemanticState) {
        super(1);
        this.$itemProviderLambda = aVar;
        this.$coroutineScope = k0Var;
        this.$state = lazyLayoutSemanticState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final Boolean invoke(int i10) {
        LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) this.$itemProviderLambda.invoke();
        if (i10 >= 0 && i10 < lazyLayoutItemProvider.getItemCount()) {
            m0.p(this.$coroutineScope, null, 0, new AnonymousClass2(this.$state, i10, null), 3);
            return Boolean.TRUE;
        }
        StringBuilder sbS = a0.c.s(i10, "Can't scroll to index ", ", it is out of bounds [0, ");
        sbS.append(lazyLayoutItemProvider.getItemCount());
        sbS.append(')');
        throw new IllegalArgumentException(sbS.toString().toString());
    }
}
