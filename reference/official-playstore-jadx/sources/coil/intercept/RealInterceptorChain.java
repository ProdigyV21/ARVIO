package coil.intercept;

import android.view.Size;
import coil.EventListener;
import coil.intercept.Interceptor;
import coil.request.ImageRequest;
import coil.request.NullRequestData;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0000\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0014\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J-\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\u0002H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010!R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010%\u001a\u0004\b&\u0010'R\u001a\u0010\t\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\u001f\u001a\u0004\b(\u0010!R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010)\u001a\u0004\b*\u0010+R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010,\u001a\u0004\b-\u0010.R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010/\u001a\u0004\b\u000f\u00100\u0082\u0002\u0004\n\u0002\b\u0019¨\u00061"}, d2 = {"Lcoil/intercept/RealInterceptorChain;", "Lcoil/intercept/Interceptor$Chain;", "Lcoil/request/ImageRequest;", "initialRequest", "", "Lcoil/intercept/Interceptor;", "interceptors", "", "index", "request", "Lcoil/size/Size;", ContentDisposition.Parameters.Size, "Lcoil/EventListener;", "eventListener", "", "isPlaceholderCached", "<init>", "(Lcoil/request/ImageRequest;Ljava/util/List;ILcoil/request/ImageRequest;Lcoil/size/Size;Lcoil/EventListener;Z)V", "interceptor", "Lx6/t0;", "checkRequest", "(Lcoil/request/ImageRequest;Lcoil/intercept/Interceptor;)V", "copy", "(ILcoil/request/ImageRequest;Lcoil/size/Size;)Lcoil/intercept/RealInterceptorChain;", "withRequest", "(Lcoil/request/ImageRequest;)Lcoil/intercept/Interceptor$Chain;", "withSize", "(Lcoil/size/Size;)Lcoil/intercept/Interceptor$Chain;", "Lcoil/request/ImageResult;", "proceed", "(Lcoil/request/ImageRequest;Ld7/d;)Ljava/lang/Object;", "Lcoil/request/ImageRequest;", "getInitialRequest", "()Lcoil/request/ImageRequest;", "Ljava/util/List;", "getInterceptors", "()Ljava/util/List;", "I", "getIndex", "()I", "getRequest", "Lcoil/size/Size;", "getSize", "()Lcoil/size/Size;", "Lcoil/EventListener;", "getEventListener", "()Lcoil/EventListener;", "Z", "()Z", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RealInterceptorChain implements Interceptor.Chain {
    private final EventListener eventListener;
    private final int index;
    private final ImageRequest initialRequest;
    private final List<Interceptor> interceptors;
    private final boolean isPlaceholderCached;
    private final ImageRequest request;
    private final Size size;

    /* JADX INFO: renamed from: coil.intercept.RealInterceptorChain$proceed$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "coil.intercept.RealInterceptorChain", f = "RealInterceptorChain.kt", l = {32}, m = "proceed")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RealInterceptorChain.this.proceed(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RealInterceptorChain(ImageRequest imageRequest, List<? extends Interceptor> list, int i10, ImageRequest imageRequest2, Size size, EventListener eventListener, boolean z) {
        this.initialRequest = imageRequest;
        this.interceptors = list;
        this.index = i10;
        this.request = imageRequest2;
        this.size = size;
        this.eventListener = eventListener;
        this.isPlaceholderCached = z;
    }

    private final void checkRequest(ImageRequest request, Interceptor interceptor) {
        if (request.getContext() != this.initialRequest.getContext()) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot modify the request's context.").toString());
        }
        if (request.getData() == NullRequestData.INSTANCE) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot set the request's data to null.").toString());
        }
        if (request.getTarget() != this.initialRequest.getTarget()) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot modify the request's target.").toString());
        }
        if (request.getLifecycle() != this.initialRequest.getLifecycle()) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot modify the request's lifecycle.").toString());
        }
        if (request.getSizeResolver() == this.initialRequest.getSizeResolver()) {
            return;
        }
        throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot modify the request's size resolver. Use `Interceptor.Chain.withSize` instead.").toString());
    }

    private final RealInterceptorChain copy(int index, ImageRequest request, Size size) {
        return new RealInterceptorChain(this.initialRequest, this.interceptors, index, request, size, this.eventListener, this.isPlaceholderCached);
    }

    public static /* synthetic */ RealInterceptorChain copy$default(RealInterceptorChain realInterceptorChain, int i10, ImageRequest imageRequest, Size size, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = realInterceptorChain.index;
        }
        if ((i11 & 2) != 0) {
            imageRequest = realInterceptorChain.getRequest();
        }
        if ((i11 & 4) != 0) {
            size = realInterceptorChain.getSize();
        }
        return realInterceptorChain.copy(i10, imageRequest, size);
    }

    public final EventListener getEventListener() {
        return this.eventListener;
    }

    public final int getIndex() {
        return this.index;
    }

    public final ImageRequest getInitialRequest() {
        return this.initialRequest;
    }

    public final List<Interceptor> getInterceptors() {
        return this.interceptors;
    }

    @Override // coil.intercept.Interceptor.Chain
    public ImageRequest getRequest() {
        return this.request;
    }

    @Override // coil.intercept.Interceptor.Chain
    public Size getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: isPlaceholderCached, reason: from getter */
    public final boolean getIsPlaceholderCached() {
        return this.isPlaceholderCached;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // coil.intercept.Interceptor.Chain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object proceed(coil.request.ImageRequest r10, d7.d<? super coil.request.ImageResult> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof coil.intercept.RealInterceptorChain.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            coil.intercept.RealInterceptorChain$proceed$1 r0 = (coil.intercept.RealInterceptorChain.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.intercept.RealInterceptorChain$proceed$1 r0 = new coil.intercept.RealInterceptorChain$proceed$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r10 = r0.L$1
            coil.intercept.Interceptor r10 = (coil.intercept.Interceptor) r10
            java.lang.Object r0 = r0.L$0
            coil.intercept.RealInterceptorChain r0 = (coil.intercept.RealInterceptorChain) r0
            k2.c.G(r11)
            r3 = r9
            goto L73
        L2e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L36:
            k2.c.G(r11)
            int r11 = r9.index
            if (r11 <= 0) goto L49
            java.util.List<coil.intercept.Interceptor> r1 = r9.interceptors
            int r11 = r11 - r2
            java.lang.Object r11 = r1.get(r11)
            coil.intercept.Interceptor r11 = (coil.intercept.Interceptor) r11
            r9.checkRequest(r10, r11)
        L49:
            java.util.List<coil.intercept.Interceptor> r11 = r9.interceptors
            int r1 = r9.index
            java.lang.Object r11 = r11.get(r1)
            coil.intercept.Interceptor r11 = (coil.intercept.Interceptor) r11
            int r1 = r9.index
            int r4 = r1 + 1
            r7 = 4
            r8 = 0
            r6 = 0
            r3 = r9
            r5 = r10
            coil.intercept.RealInterceptorChain r10 = copy$default(r3, r4, r5, r6, r7, r8)
            r0.L$0 = r3
            r0.L$1 = r11
            r0.label = r2
            java.lang.Object r10 = r11.intercept(r10, r0)
            e7.a r0 = e7.a.f15033i
            if (r10 != r0) goto L6f
            return r0
        L6f:
            r0 = r11
            r11 = r10
            r10 = r0
            r0 = r3
        L73:
            coil.request.ImageResult r11 = (coil.request.ImageResult) r11
            coil.request.ImageRequest r1 = r11.getRequest()
            r0.checkRequest(r1, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.RealInterceptorChain.proceed(coil.request.ImageRequest, d7.d):java.lang.Object");
    }

    @Override // coil.intercept.Interceptor.Chain
    public Interceptor.Chain withRequest(ImageRequest request) {
        int i10 = this.index;
        if (i10 > 0) {
            checkRequest(request, this.interceptors.get(i10 - 1));
        }
        return copy$default(this, 0, request, null, 5, null);
    }

    @Override // coil.intercept.Interceptor.Chain
    public Interceptor.Chain withSize(Size size) {
        return copy$default(this, 0, null, size, 3, null);
    }
}
