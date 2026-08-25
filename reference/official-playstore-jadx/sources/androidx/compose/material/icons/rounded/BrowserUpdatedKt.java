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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_browserUpdated", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BrowserUpdated", "Landroidx/compose/material/icons/Icons$Rounded;", "getBrowserUpdated", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BrowserUpdatedKt {
    private static ImageVector _browserUpdated;

    public static final ImageVector getBrowserUpdated(Icons.Rounded rounded) {
        ImageVector imageVector = _browserUpdated;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.BrowserUpdated", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.0f, 3.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(6.0f);
        pathBuilderA.horizontalLineToRelative(1.59f);
        pathBuilderA.curveToRelative(0.89f, 0.0f, 1.34f, 1.08f, 0.71f, 1.71f);
        pathBuilderA.lineToRelative(-2.59f, 2.59f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderA.lineToRelative(-2.59f, -2.59f);
        pathBuilderA.curveTo(11.08f, 11.08f, 11.52f, 10.0f, 12.41f, 10.0f);
        pathBuilderA.horizontalLineTo(14.0f);
        pathBuilderA.verticalLineTo(4.0f);
        pathBuilderA.curveTo(14.0f, 3.45f, 14.45f, 3.0f, 15.0f, 3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(6.0f, 19.59f);
        pathBuilderA.curveTo(6.0f, 20.37f, 6.63f, 21.0f, 7.41f, 21.0f);
        pathBuilderA.horizontalLineToRelative(9.17f);
        pathBuilderA.curveToRelative(0.78f, 0.0f, 1.41f, -0.63f, 1.41f, -1.41f);
        pathBuilderA.curveToRelative(0.0f, -0.72f, -0.44f, -1.03f, -1.0f, -1.59f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        a.y(pathBuilderA, 2.0f, 4.0f, 5.0f);
        pathBuilderA.lineToRelative(7.0f, 0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.lineTo(4.0f, 3.0f);
        pathBuilderA.curveTo(2.9f, 3.0f, 2.0f, 3.9f, 2.0f, 5.0f);
        pathBuilderA.verticalLineToRelative(11.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.curveTo(6.45f, 18.55f, 6.0f, 18.87f, 6.0f, 19.59f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _browserUpdated = imageVectorBuild;
        return imageVectorBuild;
    }
}
