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
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_grass", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Grass", "Landroidx/compose/material/icons/Icons$Rounded;", "getGrass", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GrassKt {
    private static ImageVector _grass;

    public static final ImageVector getGrass(Icons.Rounded rounded) {
        ImageVector imageVector = _grass;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Grass", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.64f, 11.02f);
        pathBuilderA.curveToRelative(0.55f, -1.47f, 1.43f, -2.78f, 2.56f, -3.83f);
        pathBuilderA.curveToRelative(0.38f, -0.36f, 0.04f, -1.0f, -0.46f, -0.85f);
        pathBuilderA.curveToRelative(-3.32f, 0.98f, -5.75f, 4.05f, -5.74f, 7.69f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilderA.curveTo(12.95f, 12.75f, 14.2f, 11.72f, 15.64f, 11.02f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.42f, 8.85f);
        pathBuilderA.curveToRelative(-0.6f, -1.56f, -1.63f, -2.91f, -2.96f, -3.87f);
        pathBuilderA.curveTo(8.04f, 4.68f, 7.5f, 5.17f, 7.74f, 5.63f);
        pathBuilderA.curveTo(8.54f, 7.15f, 9.0f, 8.88f, 9.0f, 10.71f);
        pathBuilderA.curveToRelative(0.0f, 0.21f, -0.03f, 0.41f, -0.04f, 0.61f);
        pathBuilderA.curveToRelative(0.43f, 0.24f, 0.83f, 0.52f, 1.22f, 0.82f);
        pathBuilderA.curveTo(10.39f, 10.96f, 10.83f, 9.85f, 11.42f, 8.85f);
        a.r(pathBuilderA, 12.0f, 20.0f, 3.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(4.75f);
        pathBuilderA.curveToRelative(-0.57f, -2.19f, -2.04f, -4.02f, -4.0f, -5.06f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.16f, -0.08f, -0.26f, -0.25f, -0.26f, -0.44f);
        pathBuilderA.curveToRelative(0.0f, -0.27f, 0.22f, -0.49f, 0.49f, -0.5f);
        pathBuilderA.curveToRelative(0.01f, 0.0f, 0.02f, 0.0f, 0.02f, 0.0f);
        pathBuilderA.curveTo(8.42f, 12.0f, 12.0f, 15.58f, 12.0f, 20.0f);
        v.a.p(pathBuilderA, 20.26f, 12.94f, 20.26f, 12.94f);
        pathBuilderA.curveToRelative(-1.96f, 1.04f, -3.44f, 2.87f, -4.0f, 5.06f);
        pathBuilderA.horizontalLineTo(21.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(-5.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.68f, -0.07f, -1.35f, -0.2f, -2.0f);
        pathBuilderA.curveToRelative(-0.15f, -0.72f, -0.38f, -1.42f, -0.67f, -2.07f);
        pathBuilderA.curveTo(14.52f, 13.58f, 17.07f, 12.0f, 20.0f, 12.0f);
        pathBuilderA.curveToRelative(0.01f, 0.0f, 0.02f, 0.0f, 0.02f, 0.0f);
        pathBuilderA.curveToRelative(0.27f, 0.0f, 0.49f, 0.23f, 0.49f, 0.5f);
        pathBuilderA.curveTo(20.52f, 12.69f, 20.41f, 12.85f, 20.26f, 12.94f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _grass = imageVectorBuild;
        return imageVectorBuild;
    }
}
