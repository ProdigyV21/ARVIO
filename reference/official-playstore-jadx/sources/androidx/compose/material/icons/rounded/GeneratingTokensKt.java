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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_generatingTokens", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GeneratingTokens", "Landroidx/compose/material/icons/Icons$Rounded;", "getGeneratingTokens", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GeneratingTokensKt {
    private static ImageVector _generatingTokens;

    public static final ImageVector getGeneratingTokens(Icons.Rounded rounded) {
        ImageVector imageVector = _generatingTokens;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.GeneratingTokens", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.0f, 4.0f);
        pathBuilderA.curveToRelative(-4.42f, 0.0f, -8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilderA.curveTo(17.0f, 7.58f, 13.42f, 4.0f, 9.0f, 4.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 15.5f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.horizontalLineTo(6.75f);
        pathBuilderA.curveTo(6.34f, 10.5f, 6.0f, 10.16f, 6.0f, 9.75f);
        pathBuilderA.reflectiveCurveTo(6.34f, 9.0f, 6.75f, 9.0f);
        pathBuilderA.horizontalLineToRelative(4.5f);
        pathBuilderA.curveTo(11.66f, 9.0f, 12.0f, 9.34f, 12.0f, 9.75f);
        pathBuilderA.reflectiveCurveToRelative(-0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderA.horizontalLineTo(10.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveTo(10.0f, 15.05f, 9.55f, 15.5f, 9.0f, 15.5f);
        a.p(pathBuilderA, 20.25f, 3.75f, 22.0f, 4.54f);
        pathBuilderA.curveToRelative(0.39f, 0.18f, 0.39f, 0.73f, 0.0f, 0.91f);
        pathBuilderA.lineToRelative(-1.75f, 0.79f);
        pathBuilderA.lineTo(19.46f, 8.0f);
        pathBuilderA.curveToRelative(-0.18f, 0.39f, -0.73f, 0.39f, -0.91f, 0.0f);
        pathBuilderA.lineToRelative(-0.79f, -1.75f);
        pathBuilderA.lineTo(16.0f, 5.46f);
        pathBuilderA.curveToRelative(-0.39f, -0.18f, -0.39f, -0.73f, 0.0f, -0.91f);
        pathBuilderA.lineToRelative(1.75f, -0.79f);
        pathBuilderA.lineTo(18.54f, 2.0f);
        pathBuilderA.curveToRelative(0.18f, -0.39f, 0.73f, -0.39f, 0.91f, 0.0f);
        c.z(pathBuilderA, 20.25f, 3.75f, 20.25f, 17.75f);
        pathBuilderA.lineTo(22.0f, 18.54f);
        pathBuilderA.curveToRelative(0.39f, 0.18f, 0.39f, 0.73f, 0.0f, 0.91f);
        pathBuilderA.lineToRelative(-1.75f, 0.79f);
        pathBuilderA.lineTo(19.46f, 22.0f);
        pathBuilderA.curveToRelative(-0.18f, 0.39f, -0.73f, 0.39f, -0.91f, 0.0f);
        pathBuilderA.lineToRelative(-0.79f, -1.75f);
        pathBuilderA.lineTo(16.0f, 19.46f);
        pathBuilderA.curveToRelative(-0.39f, -0.18f, -0.39f, -0.73f, 0.0f, -0.91f);
        pathBuilderA.lineToRelative(1.75f, -0.79f);
        pathBuilderA.lineTo(18.54f, 16.0f);
        pathBuilderA.curveToRelative(0.18f, -0.39f, 0.73f, -0.39f, 0.91f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 20.25f, 17.75f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _generatingTokens = imageVectorBuild;
        return imageVectorBuild;
    }
}
