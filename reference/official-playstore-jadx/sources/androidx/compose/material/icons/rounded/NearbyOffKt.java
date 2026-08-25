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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nearbyOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NearbyOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getNearbyOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NearbyOffKt {
    private static ImageVector _nearbyOff;

    public static final ImageVector getNearbyOff(Icons.Rounded rounded) {
        ImageVector imageVector = _nearbyOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.NearbyOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.41f, 13.42f, 18.83f, 16.0f);
        pathBuilderJ.lineToRelative(-1.81f, -1.81f);
        pathBuilderJ.lineTo(19.2f, 12.0f);
        pathBuilderJ.lineTo(12.0f, 4.8f);
        pathBuilderJ.lineTo(9.81f, 6.99f);
        pathBuilderJ.lineTo(8.0f, 5.17f);
        pathBuilderJ.lineToRelative(2.58f, -2.58f);
        pathBuilderJ.curveToRelative(0.78f, -0.78f, 2.05f, -0.78f, 2.83f, 0.0f);
        pathBuilderJ.lineToRelative(8.0f, 8.0f);
        pathBuilderJ.curveTo(22.2f, 11.37f, 22.2f, 12.63f, 21.41f, 13.42f);
        a.p(pathBuilderJ, 20.48f, 21.9f, 20.48f, 21.9f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderJ.lineTo(16.0f, 18.83f);
        pathBuilderJ.lineToRelative(-2.58f, 2.58f);
        pathBuilderJ.curveToRelative(-0.78f, 0.78f, -2.05f, 0.78f, -2.83f, 0.0f);
        pathBuilderJ.lineToRelative(-8.0f, -8.0f);
        pathBuilderJ.curveToRelative(-0.78f, -0.78f, -0.78f, -2.05f, 0.0f, -2.83f);
        pathBuilderJ.lineTo(5.17f, 8.0f);
        pathBuilderJ.lineTo(2.1f, 4.93f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineToRelative(16.98f, 16.97f);
        pathBuilderJ.curveTo(20.87f, 20.87f, 20.87f, 21.51f, 20.48f, 21.9f);
        a.z(pathBuilderJ, 14.19f, 17.02f, -1.39f, -1.39f);
        pathBuilderJ.lineToRelative(-0.09f, 0.09f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.42f, 0.0f);
        pathBuilderJ.lineToRelative(-3.01f, -3.01f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderJ.lineToRelative(0.09f, -0.09f);
        pathBuilderJ.lineToRelative(-1.4f, -1.39f);
        pathBuilderJ.lineTo(4.8f, 12.0f);
        d.C(pathBuilderJ, 7.2f, 7.2f, 14.19f, 17.02f);
        pathBuilderJ.moveTo(15.71f, 11.29f);
        pathBuilderJ.lineToRelative(-3.01f, -3.01f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderJ.lineTo(11.2f, 8.38f);
        pathBuilderJ.lineToRelative(4.42f, 4.42f);
        pathBuilderJ.lineToRelative(0.09f, -0.09f);
        pathBuilderJ.curveTo(16.1f, 12.32f, 16.1f, 11.68f, 15.71f, 11.29f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nearbyOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
