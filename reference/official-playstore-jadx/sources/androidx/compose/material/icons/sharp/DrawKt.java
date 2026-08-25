package androidx.compose.material.icons.sharp;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_draw", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Draw", "Landroidx/compose/material/icons/Icons$Sharp;", "getDraw", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DrawKt {
    private static ImageVector _draw;

    public static final ImageVector getDraw(Icons.Sharp sharp) {
        ImageVector imageVector = _draw;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Draw", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.85f, 10.39f, 2.48f, -2.48f);
        pathBuilderR.lineToRelative(-4.24f, -4.24f);
        d.C(pathBuilderR, -2.48f, 2.48f, 18.85f, 10.39f);
        pathBuilderR.moveTo(13.19f, 7.56f);
        b.n(pathBuilderR, 4.0f, 16.76f, 21.0f, 4.24f);
        d.C(pathBuilderR, 9.19f, -9.19f, 13.19f, 7.56f);
        pathBuilderR.moveTo(19.0f, 17.5f);
        pathBuilderR.curveToRelative(0.0f, 2.19f, -2.54f, 3.5f, -5.0f, 3.5f);
        pathBuilderR.curveToRelative(-0.48f, 0.0f, -1.0f, -0.07f, -1.0f, -0.07f);
        pathBuilderR.curveToRelative(0.0f, -0.34f, 0.0f, -2.03f, 0.0f, -2.03f);
        pathBuilderR.reflectiveCurveTo(13.46f, 19.0f, 14.0f, 19.0f);
        pathBuilderR.curveToRelative(1.54f, 0.0f, 3.0f, -0.73f, 3.0f, -1.5f);
        pathBuilderR.curveToRelative(0.0f, -0.47f, -0.48f, -0.87f, -1.23f, -1.2f);
        pathBuilderR.lineToRelative(1.48f, -1.48f);
        pathBuilderR.curveTo(18.32f, 15.45f, 19.0f, 16.29f, 19.0f, 17.5f);
        pathBuilderR.close();
        pathBuilderR.moveTo(4.58f, 13.35f);
        pathBuilderR.curveTo(3.61f, 12.79f, 3.0f, 12.06f, 3.0f, 11.0f);
        pathBuilderR.curveToRelative(0.0f, -1.8f, 1.89f, -2.63f, 3.56f, -3.36f);
        pathBuilderR.curveTo(7.59f, 7.18f, 9.0f, 6.56f, 9.0f, 6.0f);
        pathBuilderR.curveToRelative(0.0f, -0.41f, -0.78f, -1.0f, -2.0f, -1.0f);
        pathBuilderR.curveTo(5.76f, 5.0f, 5.0f, 5.61f, 5.0f, 6.0f);
        pathBuilderR.horizontalLineTo(3.0f);
        pathBuilderR.curveToRelative(0.0f, -1.65f, 1.7f, -3.0f, 4.0f, -3.0f);
        pathBuilderR.curveToRelative(2.24f, 0.0f, 4.0f, 1.32f, 4.0f, 3.0f);
        pathBuilderR.curveToRelative(0.0f, 1.87f, -1.93f, 2.72f, -3.64f, 3.47f);
        pathBuilderR.curveTo(6.42f, 9.88f, 5.0f, 10.5f, 5.0f, 11.0f);
        pathBuilderR.curveToRelative(0.0f, 0.31f, 0.43f, 0.6f, 1.07f, 0.86f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderR, 4.58f, 13.35f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _draw = imageVectorBuild;
        return imageVectorBuild;
    }
}
