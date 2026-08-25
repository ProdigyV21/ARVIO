package androidx.compose.material.icons.twotone;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sensorsOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SensorsOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSensorsOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SensorsOffKt {
    private static ImageVector _sensorsOff;

    public static final ImageVector getSensorsOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _sensorsOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SensorsOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.14f, 10.96f);
        pathBuilderA.curveTo(8.05f, 11.29f, 8.0f, 11.64f, 8.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.45f, 2.1f, 1.17f, 2.83f);
        pathBuilderA.lineToRelative(-1.42f, 1.42f);
        pathBuilderA.curveTo(6.67f, 15.16f, 6.0f, 13.66f, 6.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -0.93f, 0.21f, -1.8f, 0.58f, -2.59f);
        pathBuilderA.lineTo(5.11f, 7.94f);
        pathBuilderA.curveTo(4.4f, 9.13f, 4.0f, 10.52f, 4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, 0.9f, 4.21f, 2.35f, 5.65f);
        pathBuilderA.lineToRelative(-1.42f, 1.42f);
        pathBuilderA.curveTo(3.12f, 17.26f, 2.0f, 14.76f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -2.04f, 0.61f, -3.93f, 1.66f, -5.51f);
        pathBuilderA.lineTo(1.39f, 4.22f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.lineToRelative(18.38f, 18.38f);
        d.C(pathBuilderA, -1.41f, 1.41f, 8.14f, 10.96f);
        pathBuilderA.moveTo(17.42f, 14.59f);
        pathBuilderA.curveTo(17.79f, 13.8f, 18.0f, 12.93f, 18.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.66f, -0.67f, -3.16f, -1.76f, -4.24f);
        pathBuilderA.lineToRelative(-1.42f, 1.42f);
        pathBuilderA.curveTo(15.55f, 9.9f, 16.0f, 10.9f, 16.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 0.36f, -0.05f, 0.71f, -0.14f, 1.04f);
        c.z(pathBuilderA, 17.42f, 14.59f, 20.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.48f, -0.4f, 2.87f, -1.11f, 4.06f);
        pathBuilderA.lineToRelative(1.45f, 1.45f);
        pathBuilderA.curveTo(21.39f, 15.93f, 22.0f, 14.04f, 22.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -2.76f, -1.12f, -5.26f, -2.93f, -7.07f);
        pathBuilderA.lineToRelative(-1.42f, 1.42f);
        pathBuilderA.curveTo(19.1f, 7.79f, 20.0f, 9.79f, 20.0f, 12.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sensorsOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
