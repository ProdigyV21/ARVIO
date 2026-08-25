package android.view;

import android.view.Dimension;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: renamed from: coil.size.-Dimensions, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\"\u0010\b\u001a\u00020\u0000*\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006H\u0086\b¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"", "px", "Lcoil/size/Dimension$Pixels;", "Dimension", "(I)Lcoil/size/Dimension$Pixels;", "Lcoil/size/Dimension;", "Lkotlin/Function0;", "block", "pxOrElse", "(Lcoil/size/Dimension;Lr7/a;)I", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class Dimensions {
    public static final Dimension.Pixels Dimension(int i10) {
        return new Dimension.Pixels(i10);
    }

    public static final int pxOrElse(Dimension dimension, a<Integer> aVar) {
        return dimension instanceof Dimension.Pixels ? ((Dimension.Pixels) dimension).px : ((Number) aVar.invoke()).intValue();
    }
}
