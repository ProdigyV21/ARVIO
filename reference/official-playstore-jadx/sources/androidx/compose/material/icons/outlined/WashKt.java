package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wash", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Wash", "Landroidx/compose/material/icons/Icons$Outlined;", "getWash", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WashKt {
    private static ImageVector _wash;

    public static final ImageVector getWash(Icons.Outlined outlined) {
        ImageVector imageVector = _wash;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Wash", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.75f, 16.0f);
        pathBuilderA.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderA.reflectiveCurveToRelative(-0.56f, -1.25f, -1.25f, -1.25f);
        a.n(pathBuilderA, 12.0f, -1.0f, 6.75f);
        pathBuilderA.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderA.curveToRelative(0.0f, -0.67f, -0.53f, -1.2f, -1.18f, -1.24f);
        pathBuilderA.lineTo(8.87f, 10.0f);
        pathBuilderA.lineToRelative(1.48f, -2.6f);
        pathBuilderA.curveToRelative(0.09f, -0.17f, 0.14f, -0.34f, 0.14f, -0.54f);
        pathBuilderA.curveToRelative(0.0f, -0.26f, -0.09f, -0.5f, -0.26f, -0.7f);
        pathBuilderA.lineTo(9.12f, 5.0f);
        pathBuilderA.lineToRelative(-7.18f, 6.8f);
        pathBuilderA.curveTo(1.34f, 12.36f, 1.0f, 13.15f, 1.0f, 13.97f);
        pathBuilderA.verticalLineTo(20.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.horizontalLineToRelative(13.75f);
        pathBuilderA.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderA.reflectiveCurveToRelative(-0.56f, -1.25f, -1.25f, -1.25f);
        a.n(pathBuilderA, 12.0f, -1.0f, 7.75f);
        pathBuilderA.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderA.reflectiveCurveTo(20.44f, 17.0f, 19.75f, 17.0f);
        b.e(pathBuilderA, 12.0f, -1.0f, 20.75f);
        pathBuilderA.moveTo(10.0f, 21.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-6.0f);
        pathBuilderA.curveToRelative(0.0f, -0.39f, 0.23f, -0.64f, 0.36f, -0.75f);
        pathBuilderA.lineTo(7.0f, 9.87f);
        pathBuilderA.verticalLineTo(12.0f);
        pathBuilderA.lineToRelative(3.0f, 0.0f);
        pathBuilderA.verticalLineTo(21.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.5f, 9.0f);
        pathBuilderA.curveTo(14.33f, 9.0f, 15.0f, 8.33f, 15.0f, 7.5f);
        pathBuilderA.curveTo(15.0f, 6.66f, 13.5f, 5.0f, 13.5f, 5.0f);
        pathBuilderA.reflectiveCurveTo(12.0f, 6.66f, 12.0f, 7.5f);
        pathBuilderA.curveTo(12.0f, 8.33f, 12.67f, 9.0f, 13.5f, 9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.5f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, -2.5f, 2.83f, -2.5f, 4.5f);
        pathBuilderA.curveTo(16.0f, 6.88f, 17.12f, 8.0f, 18.5f, 8.0f);
        pathBuilderA.reflectiveCurveTo(21.0f, 6.88f, 21.0f, 5.5f);
        pathBuilderA.curveTo(21.0f, 3.83f, 18.5f, 1.0f, 18.5f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.5f, 6.5f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.4f, 0.43f, -1.22f, 1.0f, -2.05f);
        pathBuilderA.curveToRelative(0.57f, 0.83f, 1.0f, 1.65f, 1.0f, 2.05f);
        pathBuilderA.curveTo(19.5f, 6.05f, 19.05f, 6.5f, 18.5f, 6.5f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wash = imageVectorBuild;
        return imageVectorBuild;
    }
}
