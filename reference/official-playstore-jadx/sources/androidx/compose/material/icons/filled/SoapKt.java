package androidx.compose.material.icons.filled;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_soap", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Soap", "Landroidx/compose/material/icons/Icons$Filled;", "getSoap", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SoapKt {
    private static ImageVector _soap;

    public static final ImageVector getSoap(Icons.Filled filled) {
        ImageVector imageVector = _soap;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Soap", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(9.12f, 5.0f, -7.18f, 6.79f);
        pathBuilderR.curveTo(1.34f, 12.35f, 1.0f, 13.14f, 1.0f, 13.97f);
        pathBuilderR.verticalLineTo(20.0f);
        pathBuilderR.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderR.horizontalLineToRelative(6.25f);
        pathBuilderR.horizontalLineTo(12.0f);
        pathBuilderR.horizontalLineToRelative(5.75f);
        pathBuilderR.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderR.reflectiveCurveToRelative(-0.56f, -1.25f, -1.25f, -1.25f);
        a.n(pathBuilderR, 12.0f, -1.0f, 7.75f);
        pathBuilderR.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderR.reflectiveCurveTo(20.44f, 17.0f, 19.75f, 17.0f);
        a.n(pathBuilderR, 12.0f, -1.0f, 8.75f);
        pathBuilderR.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderR.reflectiveCurveToRelative(-0.56f, -1.25f, -1.25f, -1.25f);
        a.n(pathBuilderR, 12.0f, -1.0f, 6.75f);
        pathBuilderR.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderR.reflectiveCurveTo(19.44f, 10.0f, 18.75f, 10.0f);
        pathBuilderR.horizontalLineTo(8.86f);
        pathBuilderR.curveToRelative(0.64f, -1.11f, 1.48f, -2.58f, 1.49f, -2.61f);
        pathBuilderR.curveToRelative(0.09f, -0.16f, 0.14f, -0.33f, 0.14f, -0.53f);
        pathBuilderR.curveToRelative(0.0f, -0.26f, -0.09f, -0.5f, -0.26f, -0.7f);
        pathBuilderR.curveTo(10.22f, 6.12f, 9.12f, 5.0f, 9.12f, 5.0f);
        c.z(pathBuilderR, 9.12f, 5.0f, 14.0f, 6.25f);
        pathBuilderR.curveToRelative(0.41f, 0.0f, 0.75f, 0.34f, 0.75f, 0.75f);
        pathBuilderR.reflectiveCurveTo(14.41f, 7.75f, 14.0f, 7.75f);
        pathBuilderR.reflectiveCurveTo(13.25f, 7.41f, 13.25f, 7.0f);
        pathBuilderR.reflectiveCurveTo(13.59f, 6.25f, 14.0f, 6.25f);
        pathBuilderR.moveTo(14.0f, 4.75f);
        pathBuilderR.curveToRelative(-1.24f, 0.0f, -2.25f, 1.01f, -2.25f, 2.25f);
        pathBuilderR.reflectiveCurveTo(12.76f, 9.25f, 14.0f, 9.25f);
        pathBuilderR.reflectiveCurveTo(16.25f, 8.24f, 16.25f, 7.0f);
        pathBuilderR.reflectiveCurveTo(15.24f, 4.75f, 14.0f, 4.75f);
        c.z(pathBuilderR, 14.0f, 4.75f, 19.75f, 5.5f);
        pathBuilderR.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderR.reflectiveCurveToRelative(-0.22f, 0.5f, -0.5f, 0.5f);
        pathBuilderR.reflectiveCurveToRelative(-0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderR.reflectiveCurveTo(19.47f, 5.5f, 19.75f, 5.5f);
        pathBuilderR.moveTo(19.75f, 4.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderR.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.reflectiveCurveTo(20.85f, 4.0f, 19.75f, 4.0f);
        c.z(pathBuilderR, 19.75f, 4.0f, 16.5f, 1.0f);
        pathBuilderR.curveTo(15.67f, 1.0f, 15.0f, 1.67f, 15.0f, 2.5f);
        pathBuilderR.reflectiveCurveTo(15.67f, 4.0f, 16.5f, 4.0f);
        pathBuilderR.curveTo(17.33f, 4.0f, 18.0f, 3.33f, 18.0f, 2.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderR, 17.33f, 1.0f, 16.5f, 1.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _soap = imageVectorBuild;
        return imageVectorBuild;
    }
}
