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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_soap", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Soap", "Landroidx/compose/material/icons/Icons$Rounded;", "getSoap", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SoapKt {
    private static ImageVector _soap;

    public static final ImageVector getSoap(Icons.Rounded rounded) {
        ImageVector imageVector = _soap;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Soap", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.25f, 6.0f);
        pathBuilderA.curveTo(14.66f, 6.0f, 15.0f, 6.34f, 15.0f, 6.75f);
        pathBuilderA.reflectiveCurveTo(14.66f, 7.5f, 14.25f, 7.5f);
        pathBuilderA.reflectiveCurveTo(13.5f, 7.16f, 13.5f, 6.75f);
        pathBuilderA.reflectiveCurveTo(13.84f, 6.0f, 14.25f, 6.0f);
        pathBuilderA.moveTo(14.25f, 4.5f);
        pathBuilderA.curveTo(13.01f, 4.5f, 12.0f, 5.51f, 12.0f, 6.75f);
        pathBuilderA.reflectiveCurveTo(13.01f, 9.0f, 14.25f, 9.0f);
        pathBuilderA.reflectiveCurveToRelative(2.25f, -1.01f, 2.25f, -2.25f);
        pathBuilderA.reflectiveCurveTo(15.49f, 4.5f, 14.25f, 4.5f);
        c.z(pathBuilderA, 14.25f, 4.5f, 20.0f, 5.5f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderA.reflectiveCurveTo(20.28f, 6.5f, 20.0f, 6.5f);
        pathBuilderA.reflectiveCurveTo(19.5f, 6.28f, 19.5f, 6.0f);
        pathBuilderA.reflectiveCurveTo(19.72f, 5.5f, 20.0f, 5.5f);
        pathBuilderA.moveTo(20.0f, 4.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(21.1f, 4.0f, 20.0f, 4.0f);
        c.z(pathBuilderA, 20.0f, 4.0f, 18.0f, 2.5f);
        pathBuilderA.curveTo(18.0f, 3.33f, 17.33f, 4.0f, 16.5f, 4.0f);
        pathBuilderA.curveTo(15.67f, 4.0f, 15.0f, 3.33f, 15.0f, 2.5f);
        pathBuilderA.reflectiveCurveTo(15.67f, 1.0f, 16.5f, 1.0f);
        pathBuilderA.curveTo(17.33f, 1.0f, 18.0f, 1.67f, 18.0f, 2.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(1.94f, 11.79f);
        pathBuilderA.curveTo(1.34f, 12.35f, 1.0f, 13.14f, 1.0f, 13.97f);
        pathBuilderA.verticalLineTo(20.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.lineToRelative(13.68f, 0.0f);
        pathBuilderA.curveToRelative(0.65f, 0.0f, 1.25f, -0.47f, 1.32f, -1.12f);
        pathBuilderA.curveToRelative(0.08f, -0.75f, -0.51f, -1.38f, -1.24f, -1.38f);
        pathBuilderA.horizontalLineTo(12.5f);
        pathBuilderA.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderA.lineToRelative(7.18f, 0.0f);
        pathBuilderA.curveToRelative(0.65f, 0.0f, 1.25f, -0.47f, 1.32f, -1.12f);
        pathBuilderA.curveToRelative(0.08f, -0.75f, -0.51f, -1.38f, -1.24f, -1.38f);
        pathBuilderA.horizontalLineTo(12.5f);
        pathBuilderA.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderA.lineToRelative(8.18f, 0.0f);
        pathBuilderA.curveToRelative(0.65f, 0.0f, 1.25f, -0.47f, 1.32f, -1.12f);
        pathBuilderA.curveToRelative(0.08f, -0.75f, -0.51f, -1.38f, -1.24f, -1.38f);
        pathBuilderA.horizontalLineTo(12.5f);
        pathBuilderA.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderA.lineToRelative(6.18f, 0.0f);
        pathBuilderA.curveToRelative(0.65f, 0.0f, 1.25f, -0.47f, 1.32f, -1.12f);
        pathBuilderA.curveToRelative(0.08f, -0.75f, -0.51f, -1.38f, -1.24f, -1.38f);
        pathBuilderA.horizontalLineTo(8.86f);
        pathBuilderA.lineToRelative(1.49f, -2.61f);
        pathBuilderA.curveToRelative(0.09f, -0.16f, 0.14f, -0.33f, 0.14f, -0.53f);
        pathBuilderA.curveToRelative(0.0f, -0.26f, -0.09f, -0.5f, -0.26f, -0.7f);
        pathBuilderA.lineTo(9.81f, 5.71f);
        pathBuilderA.curveTo(9.43f, 5.32f, 8.8f, 5.3f, 8.4f, 5.68f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 1.94f, 11.79f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _soap = imageVectorBuild;
        return imageVectorBuild;
    }
}
