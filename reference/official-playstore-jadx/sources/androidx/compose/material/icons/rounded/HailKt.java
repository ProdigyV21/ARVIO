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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hail", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Hail", "Landroidx/compose/material/icons/Icons$Rounded;", "getHail", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HailKt {
    private static ImageVector _hail;

    public static final ImageVector getHail(Icons.Rounded rounded) {
        ImageVector imageVector = _hail;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Hail", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(13.1f, 6.0f, 12.0f, 6.0f);
        a.p(pathBuilderA, 17.95f, 2.0f, 17.95f, 2.0f);
        pathBuilderA.curveToRelative(0.59f, 0.0f, 1.06f, 0.51f, 1.0f, 1.09f);
        pathBuilderA.curveTo(18.93f, 3.24f, 18.74f, 7.15f, 15.0f, 8.4f);
        pathBuilderA.verticalLineTo(21.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        e.l(pathBuilderA, -5.0f, -2.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineTo(10.1f);
        pathBuilderA.curveToRelative(-0.3f, 0.1f, -0.5f, 0.2f, -0.6f, 0.3f);
        pathBuilderA.curveToRelative(-0.46f, 0.36f, -1.17f, 0.87f, -1.36f, 2.67f);
        pathBuilderA.curveTo(6.99f, 13.59f, 6.57f, 14.0f, 6.04f, 14.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.58f, 0.0f, -1.05f, -0.49f, -1.0f, -1.07f);
        pathBuilderA.curveToRelative(0.13f, -1.6f, 0.62f, -2.98f, 2.07f, -4.22f);
        pathBuilderA.curveTo(8.21f, 7.81f, 10.0f, 7.0f, 12.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(2.68f, -0.46f, 3.48f, -1.06f);
        pathBuilderA.curveToRelative(0.43f, -0.34f, 1.28f, -0.99f, 1.48f, -3.02f);
        pathBuilderA.curveTo(17.01f, 2.4f, 17.43f, 2.0f, 17.95f, 2.0f);
        a.g(pathBuilderA, 5.0f, 16.0f, 1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveTo(4.0f, 16.45f, 4.45f, 16.0f, 5.0f, 16.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hail = imageVectorBuild;
        return imageVectorBuild;
    }
}
