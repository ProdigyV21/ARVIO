package androidx.compose.material.icons.outlined;

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
import v.b;
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airlineSeatFlat", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AirlineSeatFlat", "Landroidx/compose/material/icons/Icons$Outlined;", "getAirlineSeatFlat", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirlineSeatFlatKt {
    private static ImageVector _airlineSeatFlat;

    public static final ImageVector getAirlineSeatFlat(Icons.Outlined outlined) {
        ImageVector imageVector = _airlineSeatFlat;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AirlineSeatFlat", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(5.0f, 13.0f);
        pathBuilderA.curveToRelative(0.78f, 0.0f, 1.55f, -0.3f, 2.14f, -0.9f);
        pathBuilderA.curveToRelative(1.16f, -1.19f, 1.14f, -3.08f, -0.04f, -4.24f);
        pathBuilderA.curveTo(6.51f, 7.29f, 5.75f, 7.0f, 5.0f, 7.0f);
        pathBuilderA.curveToRelative(-0.78f, 0.0f, -1.55f, 0.3f, -2.14f, 0.9f);
        pathBuilderA.curveToRelative(-1.16f, 1.19f, -1.14f, 3.08f, 0.04f, 4.24f);
        pathBuilderA.curveToRelative(0.59f, 0.57f, 1.35f, 0.86f, 2.1f, 0.86f);
        pathBuilderA.close();
        pathBuilderA.moveTo(4.29f, 9.3f);
        pathBuilderA.curveToRelative(0.19f, -0.19f, 0.44f, -0.3f, 0.71f, -0.3f);
        pathBuilderA.curveToRelative(0.26f, 0.0f, 0.51f, 0.1f, 0.7f, 0.28f);
        pathBuilderA.curveToRelative(0.4f, 0.39f, 0.4f, 1.01f, 0.02f, 1.41f);
        pathBuilderA.curveToRelative(-0.2f, 0.2f, -0.45f, 0.31f, -0.72f, 0.31f);
        pathBuilderA.curveToRelative(-0.26f, 0.0f, -0.51f, -0.1f, -0.7f, -0.28f);
        pathBuilderA.curveToRelative(-0.4f, -0.4f, -0.4f, -1.02f, -0.01f, -1.42f);
        a.p(pathBuilderA, 18.0f, 7.0f, 9.0f, 7.0f);
        e.l(pathBuilderA, 6.0f, 13.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        a.p(pathBuilderA, 11.0f, 11.0f, 11.0f, 9.0f);
        pathBuilderA.horizontalLineToRelative(7.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        e.r(pathBuilderA, -9.0f, 2.0f, 16.0f, 6.0f);
        b.z(pathBuilderA, 2.0f, 8.0f, -2.0f, 6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.j(pathBuilderA, -2.0f, 2.0f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _airlineSeatFlat = imageVectorBuild;
        return imageVectorBuild;
    }
}
