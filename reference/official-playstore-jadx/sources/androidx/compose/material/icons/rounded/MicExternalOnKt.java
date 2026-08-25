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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_micExternalOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MicExternalOn", "Landroidx/compose/material/icons/Icons$Rounded;", "getMicExternalOn", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MicExternalOnKt {
    private static ImageVector _micExternalOn;

    public static final ImageVector getMicExternalOn(Icons.Rounded rounded) {
        ImageVector imageVector = _micExternalOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.MicExternalOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(9.22f, 7.0f, 4.78f);
        pathBuilderQ.curveTo(4.3f, 6.47f, 4.0f, 5.77f, 4.0f, 5.0f);
        pathBuilderQ.curveToRelative(0.0f, -1.66f, 1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderQ.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderQ.curveTo(10.0f, 5.77f, 9.7f, 6.47f, 9.22f, 7.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(16.24f, 2.01f);
        pathBuilderQ.curveTo(18.32f, 2.13f, 20.0f, 4.08f, 20.0f, 6.16f);
        pathBuilderQ.lineTo(20.0f, 21.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.lineToRelative(0.0f, -14.91f);
        pathBuilderQ.curveToRelative(0.0f, -0.96f, -0.64f, -1.86f, -1.58f, -2.05f);
        pathBuilderQ.curveTo(15.14f, 3.78f, 14.0f, 4.76f, 14.0f, 6.0f);
        pathBuilderQ.lineToRelative(0.0f, 11.84f);
        pathBuilderQ.curveToRelative(0.0f, 2.08f, -1.68f, 4.03f, -3.76f, 4.15f);
        pathBuilderQ.curveTo(7.92f, 22.13f, 6.0f, 20.29f, 6.0f, 18.0f);
        pathBuilderQ.horizontalLineTo(5.45f);
        pathBuilderQ.curveToRelative(-0.26f, 0.0f, -0.47f, -0.19f, -0.5f, -0.45f);
        pathBuilderQ.lineTo(4.11f, 9.1f);
        pathBuilderQ.curveTo(4.05f, 8.51f, 4.51f, 8.0f, 5.1f, 8.0f);
        pathBuilderQ.horizontalLineTo(8.9f);
        pathBuilderQ.curveToRelative(0.59f, 0.0f, 1.05f, 0.51f, 1.0f, 1.1f);
        pathBuilderQ.lineToRelative(-0.85f, 8.45f);
        pathBuilderQ.curveTo(9.02f, 17.81f, 8.8f, 18.0f, 8.55f, 18.0f);
        pathBuilderQ.horizontalLineTo(8.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.24f, 1.14f, 2.22f, 2.42f, 1.96f);
        pathBuilderQ.curveToRelative(0.94f, -0.19f, 1.58f, -1.09f, 1.58f, -2.05f);
        pathBuilderQ.lineTo(12.0f, 6.0f);
        pathBuilderQ.curveTo(12.0f, 3.71f, 13.92f, 1.87f, 16.24f, 2.01f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _micExternalOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
