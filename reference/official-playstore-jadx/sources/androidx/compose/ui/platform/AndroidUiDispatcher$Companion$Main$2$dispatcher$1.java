package androidx.compose.ui.platform;

import android.view.Choreographer;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Landroid/view/Choreographer;", "kotlin.jvm.PlatformType", "<anonymous>", "(Lka/k0;)Landroid/view/Choreographer;"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.ui.platform.AndroidUiDispatcher$Companion$Main$2$dispatcher$1", f = "AndroidUiDispatcher.android.kt", l = {}, m = "invokeSuspend")
public final class AndroidUiDispatcher$Companion$Main$2$dispatcher$1 extends f7.j implements r7.p<k0, d7.d<? super Choreographer>, Object> {
    int label;

    public AndroidUiDispatcher$Companion$Main$2$dispatcher$1(d7.d<? super AndroidUiDispatcher$Companion$Main$2$dispatcher$1> dVar) {
        super(2, dVar);
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new AndroidUiDispatcher$Companion$Main$2$dispatcher$1(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return Choreographer.getInstance();
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super Choreographer> dVar) {
        return ((AndroidUiDispatcher$Companion$Main$2$dispatcher$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
