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
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_iceSkating", "Landroidx/compose/ui/graphics/vector/ImageVector;", "IceSkating", "Landroidx/compose/material/icons/Icons$Rounded;", "getIceSkating", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IceSkatingKt {
    private static ImageVector _iceSkating;

    public static final ImageVector getIceSkating(Icons.Rounded rounded) {
        ImageVector imageVector = _iceSkating;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.IceSkating", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.87f, 17.0f);
        pathBuilderA.curveToRelative(-0.47f, 0.0f, -0.85f, 0.34f, -0.98f, 0.8f);
        pathBuilderA.curveTo(20.54f, 19.07f, 19.38f, 20.0f, 18.0f, 20.0f);
        e.g(pathBuilderA, -2.0f, -2.0f, 1.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.lineToRelative(0.0f, -0.88f);
        pathBuilderA.curveToRelative(0.0f, -2.1f, -1.55f, -3.53f, -3.03f, -3.88f);
        pathBuilderA.lineToRelative(-2.7f, -0.67f);
        pathBuilderA.curveTo(12.4f, 10.35f, 11.7f, 9.76f, 11.32f, 9.0f);
        pathBuilderA.horizontalLineTo(8.5f);
        pathBuilderA.curveTo(8.22f, 9.0f, 8.0f, 8.78f, 8.0f, 8.5f);
        pathBuilderA.reflectiveCurveTo(8.22f, 8.0f, 8.5f, 8.0f);
        a.f(pathBuilderA, 2.52f, 11.0f, 7.0f, 8.5f);
        pathBuilderA.curveTo(8.22f, 7.0f, 8.0f, 6.78f, 8.0f, 6.5f);
        pathBuilderA.curveTo(8.0f, 6.22f, 8.22f, 6.0f, 8.5f, 6.0f);
        pathBuilderA.horizontalLineTo(11.0f);
        pathBuilderA.verticalLineTo(5.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderA.verticalLineToRelative(11.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        a0.a.i(pathBuilderA, 1.0f, 2.0f, 3.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(15.0f);
        pathBuilderA.curveToRelative(2.33f, 0.0f, 4.29f, -1.6f, 4.84f, -3.75f);
        pathBuilderA.curveTo(23.01f, 17.62f, 22.52f, 17.0f, 21.87f, 17.0f);
        c.C(pathBuilderA, 14.0f, 20.0f, 8.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderA, 6.0f, 20.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _iceSkating = imageVectorBuild;
        return imageVectorBuild;
    }
}
