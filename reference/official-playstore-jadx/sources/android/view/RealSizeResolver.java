package android.view;

import d7.d;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0003\u001a\u00020\u0002H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0006J\u001a\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0096\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lcoil/size/RealSizeResolver;", "Lcoil/size/SizeResolver;", "Lcoil/size/Size;", ContentDisposition.Parameters.Size, "<init>", "(Lcoil/size/Size;)V", "(Ld7/d;)Ljava/lang/Object;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Lcoil/size/Size;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RealSizeResolver implements SizeResolver {
    private final Size size;

    public RealSizeResolver(Size size) {
        this.size = size;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof RealSizeResolver) && p.a(this.size, ((RealSizeResolver) other).size);
    }

    public int hashCode() {
        return this.size.hashCode();
    }

    @Override // android.view.SizeResolver
    public Object size(d<? super Size> dVar) {
        return this.size;
    }
}
