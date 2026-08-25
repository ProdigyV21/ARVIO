package androidx.compose.material.icons.rounded;

import a0.b;
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
import v.d;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tvOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TvOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getTvOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TvOffKt {
    private static ImageVector _tvOff;

    public static final ImageVector getTvOff(Icons.Rounded rounded) {
        ImageVector imageVector = _tvOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TvOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = a.g(21.0f, 8.0f, 9.88f, 1.85f, 1.85f);
        pathBuilderG.curveToRelative(0.1f, -0.22f, 0.15f, -0.47f, 0.15f, -0.73f);
        pathBuilderG.verticalLineTo(7.0f);
        pathBuilderG.curveToRelative(0.0f, -1.11f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderG.horizontalLineToRelative(-7.59f);
        pathBuilderG.lineToRelative(2.94f, -2.94f);
        pathBuilderG.curveToRelative(0.2f, -0.2f, 0.2f, -0.51f, 0.0f, -0.71f);
        pathBuilderG.reflectiveCurveToRelative(-0.51f, -0.2f, -0.71f, 0.0f);
        pathBuilderG.lineTo(12.0f, 4.99f);
        pathBuilderG.lineTo(8.36f, 1.35f);
        pathBuilderG.curveToRelative(-0.2f, -0.2f, -0.51f, -0.2f, -0.71f, 0.0f);
        pathBuilderG.reflectiveCurveToRelative(-0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilderG.lineTo(10.59f, 5.0f);
        b.j(pathBuilderG, 8.12f, 2.0f, 2.0f, 20.0f);
        pathBuilderG.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderG.close();
        pathBuilderG.moveTo(3.12f, 2.83f);
        pathBuilderG.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderG.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderG.lineToRelative(0.82f, 0.82f);
        pathBuilderG.curveTo(1.65f, 5.28f, 1.0f, 6.06f, 1.0f, 7.0f);
        pathBuilderG.verticalLineToRelative(12.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(15.46f);
        pathBuilderG.lineToRelative(1.29f, 1.29f);
        pathBuilderG.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderG.curveToRelative(0.36f, -0.36f, 0.37f, -0.92f, 0.07f, -1.31f);
        d.q(pathBuilderG, 0.03f, 3.12f, 2.83f);
        pathBuilderG.moveTo(3.0f, 18.0f);
        pathBuilderG.verticalLineTo(8.0f);
        pathBuilderG.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        a0.a.n(pathBuilderG, 0.46f, 12.0f, 12.0f, 4.0f);
        pathBuilderG.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderG.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tvOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
