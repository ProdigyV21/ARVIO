package android.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Dimension;
import d7.d;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0007\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lcoil/size/DisplaySizeResolver;", "Lcoil/size/SizeResolver;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcoil/size/Size;", ContentDisposition.Parameters.Size, "(Ld7/d;)Ljava/lang/Object;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroid/content/Context;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DisplaySizeResolver implements SizeResolver {
    private final Context context;

    public DisplaySizeResolver(Context context) {
        this.context = context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DisplaySizeResolver) && p.a(this.context, ((DisplaySizeResolver) other).context);
    }

    public int hashCode() {
        return this.context.hashCode();
    }

    @Override // android.view.SizeResolver
    public Object size(d<? super Size> dVar) {
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        Dimension.Pixels pixelsDimension = Dimensions.Dimension(Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels));
        return new Size(pixelsDimension, pixelsDimension);
    }
}
