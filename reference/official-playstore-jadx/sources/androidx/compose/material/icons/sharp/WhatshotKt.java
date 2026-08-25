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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_whatshot", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Whatshot", "Landroidx/compose/material/icons/Icons$Sharp;", "getWhatshot", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WhatshotKt {
    private static ImageVector _whatshot;

    public static final ImageVector getWhatshot(Icons.Sharp sharp) {
        ImageVector imageVector = _whatshot;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Whatshot", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(13.5f, 0.67f);
        pathBuilderA.reflectiveCurveToRelative(0.74f, 2.65f, 0.74f, 4.8f);
        pathBuilderA.curveToRelative(0.0f, 2.06f, -1.35f, 3.73f, -3.41f, 3.73f);
        pathBuilderA.curveToRelative(-2.07f, 0.0f, -3.63f, -1.67f, -3.63f, -3.73f);
        pathBuilderA.lineToRelative(0.03f, -0.36f);
        pathBuilderA.curveTo(5.21f, 7.51f, 4.0f, 10.62f, 4.0f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilderA.curveTo(20.0f, 8.61f, 17.41f, 3.8f, 13.5f, 0.67f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.71f, 19.0f);
        pathBuilderA.curveToRelative(-1.78f, 0.0f, -3.22f, -1.4f, -3.22f, -3.14f);
        pathBuilderA.curveToRelative(0.0f, -1.62f, 1.05f, -2.76f, 2.81f, -3.12f);
        pathBuilderA.curveToRelative(1.77f, -0.36f, 3.6f, -1.21f, 4.62f, -2.58f);
        pathBuilderA.curveToRelative(0.39f, 1.29f, 0.59f, 2.65f, 0.59f, 4.04f);
        pathBuilderA.curveToRelative(0.0f, 2.65f, -2.15f, 4.8f, -4.8f, 4.8f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _whatshot = imageVectorBuild;
        return imageVectorBuild;
    }
}
