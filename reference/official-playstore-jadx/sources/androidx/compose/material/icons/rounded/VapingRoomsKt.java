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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_vapingRooms", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VapingRooms", "Landroidx/compose/material/icons/Icons$Rounded;", "getVapingRooms", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VapingRoomsKt {
    private static ImageVector _vapingRooms;

    public static final ImageVector getVapingRooms(Icons.Rounded rounded) {
        ImageVector imageVector = _vapingRooms;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.VapingRooms", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(22.0f, 17.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        a.n(pathBuilderA, 8.0f, -3.0f, 12.5f);
        pathBuilderA.curveTo(21.33f, 16.0f, 22.0f, 16.67f, 22.0f, 17.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(10.5f, 17.0f);
        pathBuilderA.curveToRelative(-0.28f, 0.0f, -0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.reflectiveCurveTo(10.78f, 17.0f, 10.5f, 17.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.85f, 7.73f);
        pathBuilderA.curveToRelative(0.62f, -0.61f, 1.0f, -1.45f, 1.0f, -2.38f);
        pathBuilderA.curveToRelative(0.0f, -1.51f, -1.0f, -2.79f, -2.38f, -3.21f);
        pathBuilderA.curveTo(16.99f, 2.0f, 16.5f, 2.36f, 16.5f, 2.86f);
        pathBuilderA.curveToRelative(0.0f, 0.33f, 0.21f, 0.62f, 0.52f, 0.71f);
        pathBuilderA.curveToRelative(0.77f, 0.23f, 1.33f, 0.94f, 1.33f, 1.78f);
        pathBuilderA.curveToRelative(0.0f, 0.82f, -0.53f, 1.51f, -1.27f, 1.76f);
        pathBuilderA.curveTo(16.75f, 7.22f, 16.5f, 7.5f, 16.5f, 7.85f);
        pathBuilderA.verticalLineTo(8.0f);
        pathBuilderA.curveToRelative(0.0f, 0.37f, 0.27f, 0.69f, 0.64f, 0.75f);
        pathBuilderA.curveToRelative(1.93f, 0.31f, 3.36f, 2.0f, 3.36f, 4.02f);
        pathBuilderA.verticalLineToRelative(1.48f);
        pathBuilderA.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderA.reflectiveCurveTo(22.0f, 14.66f, 22.0f, 14.25f);
        pathBuilderA.verticalLineToRelative(-1.49f);
        pathBuilderA.curveTo(22.0f, 10.54f, 20.72f, 8.62f, 18.85f, 7.73f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.76f, 15.0f);
        pathBuilderA.curveToRelative(0.41f, 0.0f, 0.75f, -0.33f, 0.75f, -0.75f);
        pathBuilderA.verticalLineToRelative(-0.89f);
        pathBuilderA.curveToRelative(-0.01f, -1.81f, -1.61f, -3.16f, -3.48f, -3.16f);
        pathBuilderA.horizontalLineToRelative(-1.3f);
        pathBuilderA.curveToRelative(-1.02f, 0.0f, -1.94f, -0.73f, -2.07f, -1.75f);
        pathBuilderA.curveToRelative(-0.12f, -0.95f, 0.46f, -1.7f, 1.3f, -1.93f);
        pathBuilderA.curveToRelative(0.32f, -0.09f, 0.54f, -0.38f, 0.54f, -0.72f);
        pathBuilderA.curveToRelative(0.0f, -0.49f, -0.46f, -0.86f, -0.93f, -0.72f);
        pathBuilderA.curveToRelative(-1.42f, 0.41f, -2.45f, 1.73f, -2.42f, 3.28f);
        pathBuilderA.curveToRelative(0.03f, 1.84f, 1.62f, 3.29f, 3.46f, 3.29f);
        pathBuilderA.horizontalLineToRelative(1.42f);
        pathBuilderA.curveToRelative(1.05f, 0.0f, 1.97f, 0.74f, 1.97f, 2.05f);
        pathBuilderA.verticalLineToRelative(0.55f);
        pathBuilderA.curveTo(18.0f, 14.66f, 18.34f, 15.0f, 18.76f, 15.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(3.0f, 18.5f);
        pathBuilderA.curveToRelative(1.33f, 0.0f, 2.71f, 0.18f, 4.0f, 0.5f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.curveToRelative(-1.29f, 0.32f, -2.67f, 0.5f, -4.0f, 0.5f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 2.45f, 18.5f, 3.0f, 18.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _vapingRooms = imageVectorBuild;
        return imageVectorBuild;
    }
}
