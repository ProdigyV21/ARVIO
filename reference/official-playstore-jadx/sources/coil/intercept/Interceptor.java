package coil.intercept;

import android.view.Size;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import d7.d;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bæ\u0080\u0001\u0018\u00002\u00020\u0001:\u0001\u0007J\u001b\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006ø\u0001\u0001\u0082\u0002\n\n\u0002\b\u0019\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lcoil/intercept/Interceptor;", "", "Lcoil/intercept/Interceptor$Chain;", "chain", "Lcoil/request/ImageResult;", "intercept", "(Lcoil/intercept/Interceptor$Chain;Ld7/d;)Ljava/lang/Object;", "Chain", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface Interceptor {

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010ø\u0001\u0001\u0082\u0002\n\n\u0002\b\u0019\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Lcoil/intercept/Interceptor$Chain;", "", "Lcoil/request/ImageRequest;", "request", "withRequest", "(Lcoil/request/ImageRequest;)Lcoil/intercept/Interceptor$Chain;", "Lcoil/size/Size;", ContentDisposition.Parameters.Size, "withSize", "(Lcoil/size/Size;)Lcoil/intercept/Interceptor$Chain;", "Lcoil/request/ImageResult;", "proceed", "(Lcoil/request/ImageRequest;Ld7/d;)Ljava/lang/Object;", "getRequest", "()Lcoil/request/ImageRequest;", "getSize", "()Lcoil/size/Size;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Chain {
        ImageRequest getRequest();

        Size getSize();

        Object proceed(ImageRequest imageRequest, d<? super ImageResult> dVar);

        Chain withRequest(ImageRequest request);

        Chain withSize(Size size);
    }

    Object intercept(Chain chain, d<? super ImageResult> dVar);
}
