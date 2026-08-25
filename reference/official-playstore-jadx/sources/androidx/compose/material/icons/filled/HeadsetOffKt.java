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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_headsetOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HeadsetOff", "Landroidx/compose/material/icons/Icons$Filled;", "getHeadsetOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HeadsetOffKt {
    private static ImageVector _headsetOff;

    public static final ImageVector getHeadsetOff(Icons.Filled filled) {
        ImageVector imageVector = _headsetOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.HeadsetOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 4.0f);
        pathBuilderA.curveToRelative(3.87f, 0.0f, 7.0f, 3.13f, 7.0f, 7.0f);
        c.w(pathBuilderA, 2.0f, -2.92f, 21.0f, 17.92f);
        pathBuilderA.verticalLineTo(11.0f);
        pathBuilderA.curveToRelative(0.0f, -4.97f, -4.03f, -9.0f, -9.0f, -9.0f);
        pathBuilderA.curveToRelative(-1.95f, 0.0f, -3.76f, 0.62f, -5.23f, 1.68f);
        pathBuilderA.lineToRelative(1.44f, 1.44f);
        pathBuilderA.curveTo(9.3f, 4.41f, 10.6f, 4.0f, 12.0f, 4.0f);
        a.p(pathBuilderA, 2.27f, 1.72f, 1.0f, 3.0f);
        pathBuilderA.lineToRelative(3.33f, 3.32f);
        pathBuilderA.curveTo(3.49f, 7.68f, 3.0f, 9.29f, 3.0f, 11.0f);
        pathBuilderA.verticalLineToRelative(7.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        a0.a.y(pathBuilderA, 3.0f, -8.0f, 5.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -1.17f, 0.29f, -2.26f, 0.79f, -3.22f);
        a.h(pathBuilderA, 15.0f, 17.0f, 4.0f, 3.0f);
        pathBuilderA.curveToRelative(0.3f, 0.0f, 0.59f, -0.06f, 0.86f, -0.14f);
        pathBuilderA.lineTo(21.0f, 23.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, 1.27f, -1.27f, -20.0f, -20.01f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _headsetOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
