package coil;

import android.graphics.Bitmap;
import android.view.Size;
import coil.intercept.RealInterceptorChain;
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
@e(c = "coil.RealImageLoader$executeMain$result$1", f = "RealImageLoader.kt", l = {191}, m = "invokeSuspend")
public final class RealImageLoader$executeMain$result$1 extends j implements p<k0, d<? super ImageResult>, Object> {
    final /* synthetic */ EventListener $eventListener;
    final /* synthetic */ Bitmap $placeholderBitmap;
    final /* synthetic */ ImageRequest $request;
    final /* synthetic */ Size $size;
    int label;
    final /* synthetic */ RealImageLoader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RealImageLoader$executeMain$result$1(ImageRequest imageRequest, RealImageLoader realImageLoader, Size size, EventListener eventListener, Bitmap bitmap, d<? super RealImageLoader$executeMain$result$1> dVar) {
        super(2, dVar);
        this.$request = imageRequest;
        this.this$0 = realImageLoader;
        this.$size = size;
        this.$eventListener = eventListener;
        this.$placeholderBitmap = bitmap;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new RealImageLoader$executeMain$result$1(this.$request, this.this$0, this.$size, this.$eventListener, this.$placeholderBitmap, dVar);
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
        RealInterceptorChain realInterceptorChain = new RealInterceptorChain(this.$request, this.this$0.interceptors, 0, this.$request, this.$size, this.$eventListener, this.$placeholderBitmap != null);
        ImageRequest imageRequest = this.$request;
        this.label = 1;
        Object objProceed = realInterceptorChain.proceed(imageRequest, this);
        e7.a aVar = e7.a.f15033i;
        return objProceed == aVar ? aVar : objProceed;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super ImageResult> dVar) {
        return ((RealImageLoader$executeMain$result$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
