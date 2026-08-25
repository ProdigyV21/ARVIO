package androidx.compose.material.icons.rounded;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.a;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hotelClass", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HotelClass", "Landroidx/compose/material/icons/Icons$Rounded;", "getHotelClass", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HotelClassKt {
    private static ImageVector _hotelClass;

    public static final ImageVector getHotelClass(Icons.Rounded rounded) {
        ImageVector imageVector = _hotelClass;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.HotelClass", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(8.58f, 10.0f, 2.56f);
        pathBuilderQ.curveToRelative(-0.49f, 0.0f, -0.69f, 0.62f, -0.29f, 0.91f);
        pathBuilderQ.lineToRelative(4.91f, 3.51f);
        pathBuilderQ.lineToRelative(-1.89f, 6.1f);
        pathBuilderQ.curveToRelative(-0.14f, 0.46f, 0.39f, 0.84f, 0.78f, 0.55f);
        pathBuilderQ.lineTo(11.0f, 17.31f);
        pathBuilderQ.lineToRelative(4.93f, 3.75f);
        pathBuilderQ.curveToRelative(0.39f, 0.29f, 0.92f, -0.08f, 0.78f, -0.55f);
        pathBuilderQ.lineToRelative(-1.89f, -6.1f);
        pathBuilderQ.lineToRelative(4.91f, -3.51f);
        pathBuilderQ.curveToRelative(0.4f, -0.28f, 0.2f, -0.91f, -0.29f, -0.91f);
        pathBuilderQ.horizontalLineToRelative(-6.02f);
        pathBuilderQ.lineToRelative(-1.95f, -6.42f);
        pathBuilderQ.curveToRelative(-0.14f, -0.47f, -0.81f, -0.47f, -0.96f, 0.0f);
        c.z(pathBuilderQ, 8.58f, 10.0f, 20.9f, 20.51f);
        pathBuilderQ.lineToRelative(-1.4f, -4.52f);
        pathBuilderQ.lineToRelative(2.91f, -2.08f);
        pathBuilderQ.curveToRelative(0.4f, -0.28f, 0.2f, -0.91f, -0.29f, -0.91f);
        pathBuilderQ.horizontalLineToRelative(-1.88f);
        pathBuilderQ.lineToRelative(-3.08f, 2.2f);
        pathBuilderQ.lineToRelative(1.46f, 4.72f);
        pathBuilderQ.lineToRelative(1.5f, 1.14f);
        pathBuilderQ.curveTo(20.51f, 21.35f, 21.04f, 20.97f, 20.9f, 20.51f);
        a.z(pathBuilderQ, 17.0f, 8.0f, -1.34f, -4.42f);
        pathBuilderQ.curveToRelative(-0.14f, -0.47f, -0.81f, -0.47f, -0.96f, 0.0f);
        pathBuilderQ.lineToRelative(-0.57f, 1.87f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.w(pathBuilderQ, 14.91f, 8.0f, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hotelClass = imageVectorBuild;
        return imageVectorBuild;
    }
}
