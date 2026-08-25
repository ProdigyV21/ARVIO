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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rocket", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rocket", "Landroidx/compose/material/icons/Icons$Filled;", "getRocket", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RocketKt {
    private static ImageVector _rocket;

    public static final ImageVector getRocket(Icons.Filled filled) {
        ImageVector imageVector = _rocket;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Rocket", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.5f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 4.5f, 2.04f, 4.5f, 10.5f);
        pathBuilderA.curveToRelative(0.0f, 2.49f, -1.04f, 5.57f, -1.6f, 7.0f);
        pathBuilderA.horizontalLineTo(9.1f);
        pathBuilderA.curveToRelative(-0.56f, -1.43f, -1.6f, -4.51f, -1.6f, -7.0f);
        pathBuilderA.curveTo(7.5f, 4.54f, 12.0f, 2.5f, 12.0f, 2.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.0f, 11.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(14.0f, 12.1f, 14.0f, 11.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.69f, 20.52f);
        pathBuilderA.curveToRelative(-0.48f, -1.23f, -1.52f, -4.17f, -1.67f, -6.87f);
        pathBuilderA.lineToRelative(-1.13f, 0.75f);
        pathBuilderA.curveTo(4.33f, 14.78f, 4.0f, 15.4f, 4.0f, 16.07f);
        c.x(pathBuilderA, 22.0f, 7.69f, 20.52f);
        pathBuilderA.moveTo(20.0f, 22.0f);
        pathBuilderA.verticalLineToRelative(-5.93f);
        pathBuilderA.curveToRelative(0.0f, -0.67f, -0.33f, -1.29f, -0.89f, -1.66f);
        pathBuilderA.lineToRelative(-1.13f, -0.75f);
        pathBuilderA.curveToRelative(-0.15f, 2.69f, -1.2f, 5.64f, -1.67f, 6.87f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 20.0f, 22.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rocket = imageVectorBuild;
        return imageVectorBuild;
    }
}
