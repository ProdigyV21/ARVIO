package android.view;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"SizeResolver", "Lcoil/size/SizeResolver;", ContentDisposition.Parameters.Size, "Lcoil/size/Size;", "create", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SizeResolvers {
    public static final SizeResolver create(Size size) {
        return new RealSizeResolver(size);
    }
}
