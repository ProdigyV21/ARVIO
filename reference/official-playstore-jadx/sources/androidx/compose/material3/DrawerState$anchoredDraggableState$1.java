package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "distance", "invoke", "(F)Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class DrawerState$anchoredDraggableState$1 extends r implements l<Float, Float> {
    public static final DrawerState$anchoredDraggableState$1 INSTANCE = new DrawerState$anchoredDraggableState$1();

    public DrawerState$anchoredDraggableState$1() {
        super(1);
    }

    public final Float invoke(float f10) {
        return Float.valueOf(NavigationDrawerKt.DrawerPositionalThreshold * f10);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).floatValue());
    }
}
