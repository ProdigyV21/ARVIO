package androidx.compose.material.icons.rounded;

import a0.b;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_kitesurfing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Kitesurfing", "Landroidx/compose/material/icons/Icons$Rounded;", "getKitesurfing", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KitesurfingKt {
    private static ImageVector _kitesurfing;

    public static final ImageVector getKitesurfing(Icons.Rounded rounded) {
        ImageVector imageVector = _kitesurfing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Kitesurfing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(6.0f, 4.1f, 6.0f, 3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.03f, 3.97f);
        pathBuilderA.curveToRelative(0.29f, 0.29f, 0.77f, 0.29f, 1.06f, 0.0f);
        pathBuilderA.lineTo(20.06f, 1.0f);
        pathBuilderA.horizontalLineToRelative(-2.12f);
        pathBuilderA.lineToRelative(-1.91f, 1.91f);
        pathBuilderA.curveTo(15.74f, 3.2f, 15.74f, 3.68f, 16.03f, 3.97f);
        pathBuilderA.close();
        pathBuilderA.moveTo(19.15f, 12.0f);
        pathBuilderA.curveToRelative(-1.29f, 0.0f, -3.11f, 0.53f, -5.06f, 1.38f);
        pathBuilderA.lineTo(13.0f, 12.16f);
        pathBuilderA.curveToRelative(-0.38f, -0.42f, -0.92f, -0.66f, -1.49f, -0.66f);
        b.j(pathBuilderA, 9.6f, 0.0f, -3.5f, 11.0f);
        pathBuilderA.curveToRelative(1.52f, 0.0f, 2.94f, -0.49f, 4.09f, -1.32f);
        pathBuilderA.curveToRelative(0.49f, -0.35f, 0.52f, -1.07f, 0.09f, -1.5f);
        pathBuilderA.curveToRelative(-0.35f, -0.35f, -0.9f, -0.38f, -1.3f, -0.09f);
        pathBuilderA.curveTo(13.06f, 5.66f, 12.07f, 6.0f, 11.0f, 6.0f);
        pathBuilderA.horizontalLineTo(8.0f);
        pathBuilderA.curveTo(6.9f, 6.0f, 6.0f, 6.9f, 6.0f, 8.0f);
        pathBuilderA.verticalLineToRelative(4.04f);
        pathBuilderA.curveToRelative(0.0f, 0.64f, 0.15f, 1.27f, 0.45f, 1.83f);
        pathBuilderA.lineTo(8.0f, 16.84f);
        pathBuilderA.curveToRelative(-0.53f, 0.38f, -1.03f, 0.78f, -1.49f, 1.17f);
        pathBuilderA.curveTo(7.19f, 18.59f, 8.06f, 19.0f, 9.0f, 19.0f);
        pathBuilderA.curveToRelative(1.2f, 0.0f, 2.27f, -0.66f, 3.0f, -1.5f);
        pathBuilderA.curveToRelative(0.73f, 0.84f, 1.8f, 1.5f, 3.0f, 1.5f);
        pathBuilderA.curveToRelative(0.33f, 0.0f, 0.65f, -0.05f, 0.96f, -0.14f);
        pathBuilderA.curveTo(18.81f, 16.9f, 21.0f, 14.72f, 21.0f, 13.28f);
        pathBuilderA.curveTo(21.0f, 12.25f, 19.99f, 12.0f, 19.15f, 12.0f);
        a.p(pathBuilderA, 9.83f, 15.61f, 9.0f, 13.6f);
        pathBuilderA.lineToRelative(2.5f, -0.1f);
        pathBuilderA.lineToRelative(0.7f, 0.77f);
        pathBuilderA.curveTo(11.64f, 14.55f, 10.42f, 15.23f, 9.83f, 15.61f);
        pathBuilderA.close();
        pathBuilderA.moveTo(22.0f, 22.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.87f, 0.0f, -1.73f, -0.24f, -2.53f, -0.7f);
        pathBuilderA.curveToRelative(-0.29f, -0.16f, -0.65f, -0.17f, -0.94f, 0.0f);
        pathBuilderA.curveToRelative(-1.59f, 0.9f, -3.47f, 0.9f, -5.06f, 0.0f);
        pathBuilderA.curveToRelative(-0.29f, -0.16f, -0.65f, -0.16f, -0.94f, 0.0f);
        pathBuilderA.curveToRelative(-1.59f, 0.9f, -3.47f, 0.9f, -5.06f, 0.0f);
        pathBuilderA.curveToRelative(-0.29f, -0.16f, -0.65f, -0.16f, -0.94f, 0.0f);
        pathBuilderA.curveTo(4.73f, 20.76f, 3.87f, 21.0f, 3.0f, 21.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(1.15f, 0.0f, 2.3f, -0.31f, 3.33f, -0.94f);
        pathBuilderA.curveToRelative(1.66f, 1.11f, 3.78f, 1.01f, 5.58f, 0.14f);
        pathBuilderA.curveToRelative(1.91f, 1.05f, 4.17f, 1.07f, 6.09f, 0.05f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.95f, 0.5f, 1.97f, 0.75f, 3.0f, 0.75f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveTo(21.55f, 23.0f, 22.0f, 22.55f, 22.0f, 22.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _kitesurfing = imageVectorBuild;
        return imageVectorBuild;
    }
}
