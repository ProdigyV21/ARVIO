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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hlsOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HlsOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getHlsOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HlsOffKt {
    private static ImageVector _hlsOff;

    public static final ImageVector getHlsOff(Icons.Rounded rounded) {
        ImageVector imageVector = _hlsOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.HlsOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(17.83f, 15.0f, 1.67f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-1.5f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        a0.a.D(pathBuilderA, 17.0f, -1.0f, 2.04f, 0.0f);
        pathBuilderA.curveToRelative(0.1f, 0.29f, 0.38f, 0.5f, 0.71f, 0.5f);
        pathBuilderA.curveToRelative(0.41f, 0.0f, 0.75f, -0.34f, 0.75f, -0.75f);
        pathBuilderA.verticalLineTo(10.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-3.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(1.5f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        b.q(pathBuilderA, 19.0f, 1.0f, -2.04f, 0.0f);
        pathBuilderA.curveToRelative(-0.1f, -0.29f, -0.38f, -0.5f, -0.71f, -0.5f);
        pathBuilderA.curveToRelative(-0.12f, 0.0f, -0.24f, 0.03f, -0.34f, 0.08f);
        c.z(pathBuilderA, 17.83f, 15.0f, 19.07f, 21.9f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.reflectiveCurveToRelative(0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineTo(3.51f, 3.51f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(4.48f, 4.48f);
        pathBuilderA.curveTo(6.53f, 9.51f, 6.5f, 9.63f, 6.5f, 9.75f);
        c.i(pathBuilderA, 11.0f, -2.0f, 9.75f);
        pathBuilderA.curveTo(4.5f, 9.34f, 4.16f, 9.0f, 3.75f, 9.0f);
        pathBuilderA.reflectiveCurveTo(3.0f, 9.34f, 3.0f, 9.75f);
        pathBuilderA.verticalLineToRelative(4.5f);
        pathBuilderA.curveTo(3.0f, 14.66f, 3.34f, 15.0f, 3.75f, 15.0f);
        pathBuilderA.reflectiveCurveToRelative(0.75f, -0.34f, 0.75f, -0.75f);
        w.a.j(pathBuilderA, 12.5f, 2.0f, 1.75f);
        pathBuilderA.curveTo(6.5f, 14.66f, 6.84f, 15.0f, 7.25f, 15.0f);
        pathBuilderA.reflectiveCurveTo(8.0f, 14.66f, 8.0f, 14.25f);
        a0.a.l(pathBuilderA, -3.42f, 2.0f, 2.0f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.h(pathBuilderA, 1.17f, 19.07f, 21.9f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hlsOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
