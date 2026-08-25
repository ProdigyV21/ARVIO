package androidx.compose.material3;

import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ModalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1$1$3 extends r implements r7.a<Boolean> {
    final /* synthetic */ k0 $scope;
    final /* synthetic */ SheetState $this_with;

    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1$1$3$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1$1$3$1", f = "ModalBottomSheet.android.kt", l = {264}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ SheetState $this_with;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SheetState sheetState, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$this_with = sheetState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$this_with, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SheetState sheetState = this.$this_with;
                this.label = 1;
                Object objPartialExpand = sheetState.partialExpand(this);
                e7.a aVar = e7.a.f15033i;
                if (objPartialExpand == aVar) {
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
    public ModalBottomSheet_androidKt$ModalBottomSheet$3$1$5$1$1$1$1$3(SheetState sheetState, k0 k0Var) {
        super(0);
        this.$this_with = sheetState;
        this.$scope = k0Var;
    }

    @Override // r7.a
    public final Boolean invoke() {
        if (((Boolean) this.$this_with.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded)).booleanValue()) {
            m0.p(this.$scope, null, 0, new AnonymousClass1(this.$this_with, null), 3);
        }
        return Boolean.TRUE;
    }
}
