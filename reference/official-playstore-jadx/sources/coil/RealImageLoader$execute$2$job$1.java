package coil;

import coil.request.ImageRequest;
import coil.request.ImageResult;
import d7.d;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcoil/request/ImageResult;", "<anonymous>", "(Lka/k0;)Lcoil/request/ImageResult;"}, k = 3, mv = {1, 9, 0})
@e(c = "coil.RealImageLoader$execute$2$job$1", f = "RealImageLoader.kt", l = {133}, m = "invokeSuspend")
public final class RealImageLoader$execute$2$job$1 extends j implements p<k0, d<? super ImageResult>, Object> {
    final /* synthetic */ ImageRequest $request;
    int label;
    final /* synthetic */ RealImageLoader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RealImageLoader$execute$2$job$1(RealImageLoader realImageLoader, ImageRequest imageRequest, d<? super RealImageLoader$execute$2$job$1> dVar) {
        super(2, dVar);
        this.this$0 = realImageLoader;
        this.$request = imageRequest;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new RealImageLoader$execute$2$job$1(this.this$0, this.$request, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            return obj;
        }
        c.G(obj);
        RealImageLoader realImageLoader = this.this$0;
        ImageRequest imageRequest = this.$request;
        this.label = 1;
        Object objExecuteMain = realImageLoader.executeMain(imageRequest, 1, this);
        e7.a aVar = e7.a.f15033i;
        return objExecuteMain == aVar ? aVar : objExecuteMain;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super ImageResult> dVar) {
        return ((RealImageLoader$execute$2$job$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
