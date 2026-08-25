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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_escalatorWarning", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EscalatorWarning", "Landroidx/compose/material/icons/Icons$Rounded;", "getEscalatorWarning", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EscalatorWarningKt {
    private static ImageVector _escalatorWarning;

    public static final ImageVector getEscalatorWarning(Icons.Rounded rounded) {
        ImageVector imageVector = _escalatorWarning;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.EscalatorWarning", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.5f, 2.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(5.4f, 2.0f, 6.5f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.5f, 9.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(17.83f, 8.0f, 17.0f, 8.0f);
        pathBuilderA.reflectiveCurveTo(15.5f, 8.67f, 15.5f, 9.5f);
        a.g(pathBuilderA, 18.5f, 12.0f, -2.84f);
        pathBuilderA.curveToRelative(-0.58f, 0.01f, -1.14f, 0.32f, -1.45f, 0.86f);
        pathBuilderA.lineToRelative(-0.92f, 1.32f);
        pathBuilderA.lineTo(9.72f, 8.0f);
        pathBuilderA.curveTo(9.35f, 7.37f, 8.69f, 7.01f, 8.01f, 7.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveTo(3.9f, 7.0f, 3.0f, 7.9f, 3.0f, 9.0f);
        pathBuilderA.verticalLineToRelative(5.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.5f);
        pathBuilderA.verticalLineToRelative(6.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-9.39f);
        pathBuilderA.lineToRelative(2.24f, 3.89f);
        pathBuilderA.curveToRelative(0.18f, 0.31f, 0.51f, 0.5f, 0.87f, 0.5f);
        pathBuilderA.horizontalLineToRelative(1.1f);
        pathBuilderA.curveToRelative(0.33f, 0.0f, 0.63f, -0.16f, 0.82f, -0.43f);
        pathBuilderA.lineTo(15.0f, 14.9f);
        pathBuilderA.verticalLineTo(21.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-2.5f);
        pathBuilderA.curveTo(20.0f, 12.68f, 19.33f, 12.0f, 18.5f, 12.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _escalatorWarning = imageVectorBuild;
        return imageVectorBuild;
    }
}
