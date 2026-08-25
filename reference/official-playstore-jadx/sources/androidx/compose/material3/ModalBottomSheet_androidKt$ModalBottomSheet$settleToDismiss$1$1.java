package androidx.compose.material3;

import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(F)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ModalBottomSheet_androidKt$ModalBottomSheet$settleToDismiss$1$1 extends r implements l<Float, t0> {
    final /* synthetic */ r7.a<t0> $onDismissRequest;
    final /* synthetic */ k0 $scope;
    final /* synthetic */ SheetState $sheetState;

    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$settleToDismiss$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$settleToDismiss$1$1$1", f = "ModalBottomSheet.android.kt", l = {168}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ float $it;
        final /* synthetic */ SheetState $sheetState;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SheetState sheetState, float f10, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$sheetState = sheetState;
            this.$it = f10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$sheetState, this.$it, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SheetState sheetState = this.$sheetState;
                float f10 = this.$it;
                this.label = 1;
                Object obj2 = sheetState.settle$material3_release(f10, this);
                e7.a aVar = e7.a.f15033i;
                if (obj2 == aVar) {
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

    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$settleToDismiss$1$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Throwable, t0> {
        final /* synthetic */ r7.a<t0> $onDismissRequest;
        final /* synthetic */ SheetState $sheetState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(SheetState sheetState, r7.a<t0> aVar) {
            super(1);
            this.$sheetState = sheetState;
            this.$onDismissRequest = aVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            if (this.$sheetState.isVisible()) {
                return;
            }
            this.$onDismissRequest.invoke();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModalBottomSheet_androidKt$ModalBottomSheet$settleToDismiss$1$1(k0 k0Var, SheetState sheetState, r7.a<t0> aVar) {
        super(1);
        this.$scope = k0Var;
        this.$sheetState = sheetState;
        this.$onDismissRequest = aVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).floatValue());
        return t0.f22605a;
    }

    public final void invoke(float f10) {
        m0.p(this.$scope, null, 0, new AnonymousClass1(this.$sheetState, f10, null), 3).invokeOnCompletion(new AnonymousClass2(this.$sheetState, this.$onDismissRequest));
    }
}
