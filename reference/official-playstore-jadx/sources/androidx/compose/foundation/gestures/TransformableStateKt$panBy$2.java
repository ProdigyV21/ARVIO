package androidx.compose.foundation.gestures;

import f7.j;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/TransformScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/TransformScope;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.foundation.gestures.TransformableStateKt$panBy$2", f = "TransformableState.kt", l = {}, m = "invokeSuspend")
public final class TransformableStateKt$panBy$2 extends j implements p<TransformScope, d7.d<? super t0>, Object> {
    final /* synthetic */ long $offset;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransformableStateKt$panBy$2(long j10, d7.d<? super TransformableStateKt$panBy$2> dVar) {
        super(2, dVar);
        this.$offset = j10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TransformableStateKt$panBy$2 transformableStateKt$panBy$2 = new TransformableStateKt$panBy$2(this.$offset, dVar);
        transformableStateKt$panBy$2.L$0 = obj;
        return transformableStateKt$panBy$2;
    }

    @Override // r7.p
    public final Object invoke(TransformScope transformScope, d7.d<? super t0> dVar) {
        return ((TransformableStateKt$panBy$2) create(transformScope, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        ((TransformScope) this.L$0).mo302transformByd4ec7I(1.0f, this.$offset, 0.0f);
        return t0.f22605a;
    }
}
