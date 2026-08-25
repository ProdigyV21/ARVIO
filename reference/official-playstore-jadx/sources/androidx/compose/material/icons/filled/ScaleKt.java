package androidx.compose.material.icons.filled;

import a0.a;
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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_scale", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Scale", "Landroidx/compose/material/icons/Icons$Filled;", "getScale", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScaleKt {
    private static ImageVector _scale;

    public static final ImageVector getScale(Icons.Filled filled) {
        ImageVector imageVector = _scale;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Scale", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(14.0f, 11.0f, 8.0f);
        pathBuilderA.curveToRelative(4.56f, -0.58f, 8.0f, -3.1f, 8.0f, -6.0f);
        pathBuilderA.horizontalLineTo(2.0f);
        pathBuilderA.curveToRelative(0.0f, 2.9f, 3.44f, 5.42f, 8.0f, 6.0f);
        pathBuilderA.lineToRelative(0.0f, 3.0f);
        pathBuilderA.curveToRelative(-3.68f, 0.73f, -8.0f, 3.61f, -8.0f, 11.0f);
        a.i(pathBuilderA, 6.0f, -2.0f, 4.13f);
        pathBuilderA.curveToRelative(0.93f, -6.83f, 6.65f, -7.2f, 7.87f, -7.2f);
        pathBuilderA.reflectiveCurveToRelative(6.94f, 0.37f, 7.87f, 7.2f);
        v.a.n(pathBuilderA, 16.0f, 2.0f, 6.0f);
        pathBuilderA.curveTo(22.0f, 14.61f, 17.68f, 11.73f, 14.0f, 11.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 22.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.22f, -1.05f, 0.59f, -1.41f);
        pathBuilderA.curveTo(11.39f, 17.79f, 16.0f, 16.0f, 16.0f, 16.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.79f, 4.61f, -2.59f, 5.41f);
        pathBuilderA.curveTo(13.05f, 21.78f, 12.55f, 22.0f, 12.0f, 22.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _scale = imageVectorBuild;
        return imageVectorBuild;
    }
}
