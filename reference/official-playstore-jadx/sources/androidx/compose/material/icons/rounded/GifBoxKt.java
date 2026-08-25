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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_gifBox", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GifBox", "Landroidx/compose/material/icons/Icons$Rounded;", "getGifBox", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GifBoxKt {
    private static ImageVector _gifBox;

    public static final ImageVector getGifBox(Icons.Rounded rounded) {
        ImageVector imageVector = _gifBox;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.GifBox", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(19.0f, 3.0f, 5.0f);
        pathBuilderQ.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderQ.verticalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(10.5f, 10.5f);
        pathBuilderQ.curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f);
        b.q(pathBuilderQ, 8.5f, 2.0f, 1.0f, -0.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.29f, 0.25f, -0.53f, 0.55f, -0.5f);
        pathBuilderQ.curveToRelative(0.26f, 0.02f, 0.45f, 0.26f, 0.45f, 0.52f);
        pathBuilderQ.verticalLineTo(13.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(-1.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(-2.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineTo(10.0f);
        pathBuilderQ.curveTo(10.28f, 10.0f, 10.5f, 10.22f, 10.5f, 10.5f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(12.0f, 10.0f);
        pathBuilderQ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderQ.verticalLineToRelative(3.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f);
        pathBuilderQ.reflectiveCurveToRelative(-0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderQ.verticalLineToRelative(-3.0f);
        pathBuilderQ.curveTo(11.5f, 10.22f, 11.72f, 10.0f, 12.0f, 10.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(14.0f, 14.0f);
        pathBuilderQ.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderQ.verticalLineToRelative(-3.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderQ.horizontalLineToRelative(2.0f);
        pathBuilderQ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderQ.reflectiveCurveTo(16.28f, 11.0f, 16.0f, 11.0f);
        e.g(pathBuilderQ, -1.5f, 0.5f, 1.0f);
        pathBuilderQ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderQ.reflectiveCurveToRelative(-0.22f, 0.5f, -0.5f, 0.5f);
        pathBuilderQ.horizontalLineToRelative(-1.0f);
        pathBuilderQ.verticalLineToRelative(1.0f);
        pathBuilderQ.curveTo(14.5f, 13.78f, 14.28f, 14.0f, 14.0f, 14.0f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _gifBox = imageVectorBuild;
        return imageVectorBuild;
    }
}
