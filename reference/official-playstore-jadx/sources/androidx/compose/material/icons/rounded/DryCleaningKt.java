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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dryCleaning", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DryCleaning", "Landroidx/compose/material/icons/Icons$Rounded;", "getDryCleaning", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DryCleaningKt {
    private static ImageVector _dryCleaning;

    public static final ImageVector getDryCleaning(Icons.Rounded rounded) {
        ImageVector imageVector = _dryCleaning;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DryCleaning", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = a.n(19.56f, 11.36f, 13.0f, 8.44f, 7.0f);
        pathBuilderN.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderN.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderN.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderN.curveToRelative(0.38f, 0.0f, 0.72f, 0.22f, 0.88f, 0.53f);
        pathBuilderN.curveTo(13.04f, 4.84f, 13.39f, 5.0f, 13.73f, 5.0f);
        pathBuilderN.curveToRelative(0.74f, 0.0f, 1.26f, -0.79f, 0.91f, -1.44f);
        pathBuilderN.curveToRelative(-0.6f, -1.1f, -1.86f, -1.78f, -3.24f, -1.51f);
        pathBuilderN.curveToRelative(-1.17f, 0.23f, -2.12f, 1.2f, -2.34f, 2.37f);
        pathBuilderN.curveTo(8.77f, 5.98f, 9.67f, 7.35f, 11.0f, 7.82f);
        pathBuilderN.verticalLineToRelative(0.63f);
        pathBuilderN.lineToRelative(-6.56f, 2.92f);
        pathBuilderN.curveTo(3.56f, 11.75f, 3.0f, 12.62f, 3.0f, 13.57f);
        pathBuilderN.verticalLineToRelative(0.01f);
        pathBuilderN.curveTo(3.0f, 14.92f, 4.08f, 16.0f, 5.42f, 16.0f);
        pathBuilderN.horizontalLineTo(7.0f);
        pathBuilderN.verticalLineToRelative(4.0f);
        pathBuilderN.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderN.horizontalLineToRelative(6.0f);
        pathBuilderN.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderN.verticalLineToRelative(-4.0f);
        pathBuilderN.horizontalLineToRelative(1.58f);
        pathBuilderN.curveToRelative(1.34f, 0.0f, 2.42f, -1.08f, 2.42f, -2.42f);
        pathBuilderN.verticalLineToRelative(-0.01f);
        pathBuilderN.curveTo(21.0f, 12.62f, 20.44f, 11.75f, 19.56f, 11.36f);
        v.a.g(pathBuilderN, 18.58f, 14.0f, -1.86f);
        pathBuilderN.curveToRelative(-0.35f, -0.6f, -0.98f, -1.0f, -1.72f, -1.0f);
        pathBuilderN.horizontalLineTo(9.0f);
        pathBuilderN.curveToRelative(-0.74f, 0.0f, -1.38f, 0.4f, -1.72f, 1.0f);
        pathBuilderN.horizontalLineTo(5.42f);
        pathBuilderN.curveToRelative(-0.46f, 0.0f, -0.58f, -0.65f, -0.17f, -0.81f);
        pathBuilderN.lineToRelative(6.75f, -3.0f);
        pathBuilderN.lineToRelative(6.75f, 3.0f);
        pathBuilderN.curveTo(19.17f, 13.38f, 19.03f, 14.0f, 18.58f, 14.0f);
        pathBuilderN.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderN.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dryCleaning = imageVectorBuild;
        return imageVectorBuild;
    }
}
