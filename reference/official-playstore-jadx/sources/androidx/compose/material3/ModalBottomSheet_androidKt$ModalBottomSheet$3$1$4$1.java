package androidx.compose.material3;

import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "", "it", "Lx6/t0;", "<anonymous>", "(Lka/k0;F)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$3$1$4$1", f = "ModalBottomSheet.android.kt", l = {}, m = "invokeSuspend")
public final class ModalBottomSheet_androidKt$ModalBottomSheet$3$1$4$1 extends j implements q<k0, Float, d7.d<? super t0>, Object> {
    final /* synthetic */ l<Float, t0> $settleToDismiss;
    /* synthetic */ float F$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ModalBottomSheet_androidKt$ModalBottomSheet$3$1$4$1(l<? super Float, t0> lVar, d7.d<? super ModalBottomSheet_androidKt$ModalBottomSheet$3$1$4$1> dVar) {
        super(3, dVar);
        this.$settleToDismiss = lVar;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((k0) obj, ((Number) obj2).floatValue(), (d7.d<? super t0>) obj3);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        this.$settleToDismiss.invoke(new Float(this.F$0));
        return t0.f22605a;
    }

    public final Object invoke(k0 k0Var, float f10, d7.d<? super t0> dVar) {
        ModalBottomSheet_androidKt$ModalBottomSheet$3$1$4$1 modalBottomSheet_androidKt$ModalBottomSheet$3$1$4$1 = new ModalBottomSheet_androidKt$ModalBottomSheet$3$1$4$1(this.$settleToDismiss, dVar);
        modalBottomSheet_androidKt$ModalBottomSheet$3$1$4$1.F$0 = f10;
        return modalBottomSheet_androidKt$ModalBottomSheet$3$1$4$1.invokeSuspend(t0.f22605a);
    }
}
