package android.view;

import kotlin.Metadata;
import kotlin.jvm.internal.p;
import x6.e;

/* JADX INFO: renamed from: coil.size.-Sizes, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0018\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\r\u001a\u0018\u0010\t\u001a\u00020\u00012\b\b\u0001\u0010\n\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b\u001a\u001a\u0010\t\u001a\u00020\u00012\b\b\u0001\u0010\n\u001a\u00020\r2\b\b\u0001\u0010\f\u001a\u00020\r\"\u001a\u0010\u0000\u001a\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\b¨\u0006\u000e"}, d2 = {"OriginalSize", "Lcoil/size/Size;", "getOriginalSize$annotations", "()V", "getOriginalSize", "()Lcoil/size/Size;", "isOriginal", "", "(Lcoil/size/Size;)Z", "Size", "width", "Lcoil/size/Dimension;", "height", "", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class Sizes {
    public static final Size Size(int i10, Dimension dimension) {
        return new Size(Dimensions.Dimension(i10), dimension);
    }

    public static final Size getOriginalSize() {
        return Size.ORIGINAL;
    }

    @e
    public static /* synthetic */ void getOriginalSize$annotations() {
    }

    public static final boolean isOriginal(Size size) {
        return p.a(size, Size.ORIGINAL);
    }

    public static final Size Size(Dimension dimension, int i10) {
        return new Size(dimension, Dimensions.Dimension(i10));
    }

    public static final Size Size(int i10, int i11) {
        return new Size(Dimensions.Dimension(i10), Dimensions.Dimension(i11));
    }
}
