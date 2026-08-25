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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hiking", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Hiking", "Landroidx/compose/material/icons/Icons$Filled;", "getHiking", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HikingKt {
    private static ImageVector _hiking;

    public static final ImageVector getHiking(Icons.Filled filled) {
        ImageVector imageVector = _hiking;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Hiking", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(13.5f, 5.5f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(12.4f, 5.5f, 13.5f, 5.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.5f, 10.78f);
        pathBuilderA.curveToRelative(-1.23f, -0.37f, -2.22f, -1.17f, -2.8f, -2.18f);
        pathBuilderA.lineToRelative(-1.0f, -1.6f);
        pathBuilderA.curveToRelative(-0.41f, -0.65f, -1.11f, -1.0f, -1.84f, -1.0f);
        pathBuilderA.curveToRelative(-0.78f, 0.0f, -1.59f, 0.5f, -1.78f, 1.44f);
        pathBuilderA.reflectiveCurveTo(7.0f, 23.0f, 7.0f, 23.0f);
        pathBuilderA.horizontalLineToRelative(2.1f);
        pathBuilderA.lineToRelative(1.8f, -8.0f);
        b.u(pathBuilderA, 2.1f, 2.0f, 6.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(-7.5f);
        pathBuilderA.lineToRelative(-2.1f, -2.0f);
        pathBuilderA.lineToRelative(0.6f, -3.0f);
        pathBuilderA.curveToRelative(1.0f, 1.15f, 2.41f, 2.01f, 4.0f, 2.34f);
        a0.b.p(pathBuilderA, 23.0f, 19.0f, 9.0f, -1.5f);
        c.z(pathBuilderA, 17.5f, 10.78f, 7.43f, 13.13f);
        pathBuilderA.lineToRelative(-2.12f, -0.41f);
        pathBuilderA.curveToRelative(-0.54f, -0.11f, -0.9f, -0.63f, -0.79f, -1.17f);
        pathBuilderA.lineToRelative(0.76f, -3.93f);
        pathBuilderA.curveToRelative(0.21f, -1.08f, 1.26f, -1.79f, 2.34f, -1.58f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 1.16f, 0.23f, 7.43f, 13.13f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hiking = imageVectorBuild;
        return imageVectorBuild;
    }
}
