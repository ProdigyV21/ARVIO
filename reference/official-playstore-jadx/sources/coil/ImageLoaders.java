package coil;

import android.content.Context;
import coil.ImageLoader;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import d7.d;
import d7.k;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0004\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"ImageLoader", "Lcoil/ImageLoader;", "context", "Landroid/content/Context;", "create", "executeBlocking", "Lcoil/request/ImageResult;", "request", "Lcoil/request/ImageRequest;", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class ImageLoaders {

    /* JADX INFO: renamed from: coil.ImageLoaders$executeBlocking$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcoil/request/ImageResult;", "<anonymous>", "(Lka/k0;)Lcoil/request/ImageResult;"}, k = 3, mv = {1, 9, 0})
    @e(c = "coil.ImageLoaders$executeBlocking$1", f = "ImageLoaders.kt", l = {26}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super ImageResult>, Object> {
        final /* synthetic */ ImageRequest $request;
        final /* synthetic */ ImageLoader $this_executeBlocking;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ImageLoader imageLoader, ImageRequest imageRequest, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$this_executeBlocking = imageLoader;
            this.$request = imageRequest;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass1(this.$this_executeBlocking, this.$request, dVar);
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
            ImageLoader imageLoader = this.$this_executeBlocking;
            ImageRequest imageRequest = this.$request;
            this.label = 1;
            Object objExecute = imageLoader.execute(imageRequest, this);
            e7.a aVar = e7.a.f15033i;
            return objExecute == aVar ? aVar : objExecute;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super ImageResult> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static final ImageLoader create(Context context) {
        return new ImageLoader.Builder(context).build();
    }

    public static final ImageResult executeBlocking(ImageLoader imageLoader, ImageRequest imageRequest) {
        return (ImageResult) m0.s(k.f14688i, new AnonymousClass1(imageLoader, imageRequest, null));
    }
}
