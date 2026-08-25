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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_driveEta", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DriveEta", "Landroidx/compose/material/icons/Icons$Rounded;", "getDriveEta", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DriveEtaKt {
    private static ImageVector _driveEta;

    public static final ImageVector getDriveEta(Icons.Rounded rounded) {
        ImageVector imageVector = _driveEta;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DriveEta", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.92f, 5.01f);
        pathBuilderA.curveTo(18.72f, 4.42f, 18.16f, 4.0f, 17.5f, 4.0f);
        pathBuilderA.horizontalLineToRelative(-11.0f);
        pathBuilderA.curveToRelative(-0.66f, 0.0f, -1.21f, 0.42f, -1.42f, 1.01f);
        pathBuilderA.lineToRelative(-1.97f, 5.67f);
        pathBuilderA.curveToRelative(-0.07f, 0.21f, -0.11f, 0.43f, -0.11f, 0.66f);
        pathBuilderA.verticalLineToRelative(7.16f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(6.0f, 19.33f, 6.0f, 18.5f);
        b.A(pathBuilderA, 6.0f, 18.0f, 12.0f, 0.5f);
        pathBuilderA.curveToRelative(0.0f, 0.82f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.curveToRelative(0.82f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.verticalLineToRelative(-7.16f);
        pathBuilderA.curveToRelative(0.0f, -0.22f, -0.04f, -0.45f, -0.11f, -0.66f);
        b.C(pathBuilderA, -1.97f, -5.67f, 6.5f, 15.0f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(5.67f, 12.0f, 6.5f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(7.33f, 15.0f, 6.5f, 15.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.5f, 15.0f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f);
        a.z(pathBuilderA, 5.0f, 10.0f, 1.27f, -3.82f);
        pathBuilderA.curveToRelative(0.14f, -0.4f, 0.52f, -0.68f, 0.95f, -0.68f);
        pathBuilderA.horizontalLineToRelative(9.56f);
        pathBuilderA.curveToRelative(0.43f, 0.0f, 0.81f, 0.28f, 0.95f, 0.68f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA, 19.0f, 10.0f, 5.0f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _driveEta = imageVectorBuild;
        return imageVectorBuild;
    }
}
