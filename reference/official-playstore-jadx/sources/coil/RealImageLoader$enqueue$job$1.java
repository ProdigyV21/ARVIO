package coil;

import android.content.Logger;
import android.content.Logs;
import coil.request.ErrorResult;
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
@e(c = "coil.RealImageLoader$enqueue$job$1", f = "RealImageLoader.kt", l = {117}, m = "invokeSuspend")
public final class RealImageLoader$enqueue$job$1 extends j implements p<k0, d<? super ImageResult>, Object> {
    final /* synthetic */ ImageRequest $request;
    int label;
    final /* synthetic */ RealImageLoader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RealImageLoader$enqueue$job$1(RealImageLoader realImageLoader, ImageRequest imageRequest, d<? super RealImageLoader$enqueue$job$1> dVar) {
        super(2, dVar);
        this.this$0 = realImageLoader;
        this.$request = imageRequest;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new RealImageLoader$enqueue$job$1(this.this$0, this.$request, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Logger logger;
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            RealImageLoader realImageLoader = this.this$0;
            ImageRequest imageRequest = this.$request;
            this.label = 1;
            obj = realImageLoader.executeMain(imageRequest, 0, this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        RealImageLoader realImageLoader2 = this.this$0;
        ImageResult imageResult = (ImageResult) obj;
        if ((imageResult instanceof ErrorResult) && (logger = realImageLoader2.getLogger()) != null) {
            Logs.log(logger, "RealImageLoader", ((ErrorResult) imageResult).getThrowable());
        }
        return obj;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super ImageResult> dVar) {
        return ((RealImageLoader$enqueue$job$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
