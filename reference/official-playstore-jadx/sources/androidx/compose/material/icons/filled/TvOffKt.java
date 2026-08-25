package androidx.compose.material.icons.filled;

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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tvOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TvOff", "Landroidx/compose/material/icons/Icons$Filled;", "getTvOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TvOffKt {
    private static ImageVector _tvOff;

    public static final ImageVector getTvOff(Icons.Filled filled) {
        ImageVector imageVector = _tvOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.TvOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(1.0f, 3.54f, 1.53f, 1.53f);
        pathBuilderR.curveTo(1.65f, 5.28f, 1.0f, 6.06f, 1.0f, 7.0f);
        pathBuilderR.verticalLineToRelative(12.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(15.46f);
        pathBuilderR.lineToRelative(2.0f, 2.0f);
        pathBuilderR.lineToRelative(1.26f, -1.27f);
        b.y(pathBuilderR, 2.27f, 2.27f, 1.0f, 3.54f);
        e.o(pathBuilderR, 3.0f, 19.0f, 7.0f, 1.46f);
        f.v(pathBuilderR, 12.0f, 12.0f, 3.0f);
        pathBuilderR.moveTo(21.0f, 5.0f);
        pathBuilderR.horizontalLineToRelative(-7.58f);
        pathBuilderR.lineToRelative(3.29f, -3.3f);
        pathBuilderR.lineTo(16.0f, 1.0f);
        pathBuilderR.lineToRelative(-4.0f, 4.0f);
        pathBuilderR.lineToRelative(-4.0f, -4.0f);
        pathBuilderR.lineToRelative(-0.7f, 0.7f);
        pathBuilderR.lineTo(10.58f, 5.0f);
        a0.b.j(pathBuilderR, 7.52f, 2.0f, 2.0f, 21.0f);
        pathBuilderR.verticalLineToRelative(11.48f);
        pathBuilderR.lineToRelative(1.65f, 1.65f);
        pathBuilderR.curveToRelative(0.22f, -0.32f, 0.35f, -0.71f, 0.35f, -1.13f);
        pathBuilderR.verticalLineTo(7.0f);
        pathBuilderR.curveToRelative(0.0f, -1.11f, -0.89f, -2.0f, -2.0f, -2.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tvOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
