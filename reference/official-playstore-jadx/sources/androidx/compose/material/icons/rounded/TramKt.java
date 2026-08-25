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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tram", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Tram", "Landroidx/compose/material/icons/Icons$Rounded;", "getTram", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TramKt {
    private static ImageVector _tram;

    public static final ImageVector getTram(Icons.Rounded rounded) {
        ImageVector imageVector = _tram;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Tram", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(13.0f, 5.0f, 0.75f, -1.5f);
        pathBuilderR.lineTo(17.0f, 3.5f);
        pathBuilderR.lineTo(17.0f, 2.0f);
        a.h(pathBuilderR, 7.0f, 2.0f, 1.5f, 4.75f);
        pathBuilderR.lineTo(11.0f, 5.0f);
        pathBuilderR.curveToRelative(-3.13f, 0.09f, -6.0f, 0.73f, -6.0f, 3.5f);
        pathBuilderR.lineTo(5.0f, 17.0f);
        pathBuilderR.curveToRelative(0.0f, 1.5f, 1.11f, 2.73f, 2.55f, 2.95f);
        pathBuilderR.lineToRelative(-1.19f, 1.19f);
        pathBuilderR.curveToRelative(-0.32f, 0.32f, -0.1f, 0.86f, 0.35f, 0.86f);
        pathBuilderR.lineTo(7.8f, 22.0f);
        pathBuilderR.curveToRelative(0.13f, 0.0f, 0.26f, -0.05f, 0.35f, -0.15f);
        pathBuilderR.lineTo(10.0f, 20.0f);
        pathBuilderR.horizontalLineToRelative(4.0f);
        pathBuilderR.lineToRelative(1.85f, 1.85f);
        pathBuilderR.curveToRelative(0.09f, 0.09f, 0.22f, 0.15f, 0.35f, 0.15f);
        pathBuilderR.horizontalLineToRelative(1.09f);
        pathBuilderR.curveToRelative(0.45f, 0.0f, 0.67f, -0.54f, 0.35f, -0.85f);
        pathBuilderR.lineToRelative(-1.19f, -1.19f);
        pathBuilderR.curveTo(17.89f, 19.73f, 19.0f, 18.5f, 19.0f, 17.0f);
        pathBuilderR.lineTo(19.0f, 8.5f);
        pathBuilderR.curveToRelative(0.0f, -2.77f, -2.87f, -3.41f, -6.0f, -3.5f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 18.5f);
        pathBuilderR.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderR.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderR.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderR.reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f);
        a.p(pathBuilderR, 17.0f, 14.0f, 7.0f, 14.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderR, 7.0f, 9.0f, 10.0f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tram = imageVectorBuild;
        return imageVectorBuild;
    }
}
