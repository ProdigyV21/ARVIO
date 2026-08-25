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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_playForWork", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PlayForWork", "Landroidx/compose/material/icons/Icons$Rounded;", "getPlayForWork", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PlayForWorkKt {
    private static ImageVector _playForWork;

    public static final ImageVector getPlayForWork(Icons.Rounded rounded) {
        ImageVector imageVector = _playForWork;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PlayForWork", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = d.s(11.0f, 6.0f, 4.59f, 8.71f, 10.59f);
        pathBuilderS.curveToRelative(-0.45f, 0.0f, -0.67f, 0.54f, -0.35f, 0.85f);
        pathBuilderS.lineToRelative(3.29f, 3.29f);
        pathBuilderS.curveToRelative(0.2f, 0.2f, 0.51f, 0.2f, 0.71f, 0.0f);
        pathBuilderS.lineToRelative(3.29f, -3.29f);
        pathBuilderS.curveToRelative(0.31f, -0.31f, 0.09f, -0.85f, -0.35f, -0.85f);
        pathBuilderS.lineTo(13.0f, 10.59f);
        pathBuilderS.lineTo(13.0f, 6.0f);
        pathBuilderS.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderS.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderS.close();
        pathBuilderS.moveTo(7.1f, 14.0f);
        pathBuilderS.curveToRelative(-0.61f, 0.0f, -1.11f, 0.55f, -0.99f, 1.15f);
        pathBuilderS.curveTo(6.65f, 17.91f, 9.08f, 20.0f, 12.0f, 20.0f);
        pathBuilderS.reflectiveCurveToRelative(5.35f, -2.09f, 5.89f, -4.85f);
        pathBuilderS.curveToRelative(0.12f, -0.6f, -0.38f, -1.15f, -0.99f, -1.15f);
        pathBuilderS.curveToRelative(-0.49f, 0.0f, -0.88f, 0.35f, -0.98f, 0.83f);
        pathBuilderS.curveTo(15.53f, 16.64f, 13.93f, 18.0f, 12.0f, 18.0f);
        pathBuilderS.reflectiveCurveToRelative(-3.53f, -1.36f, -3.91f, -3.17f);
        pathBuilderS.curveToRelative(-0.1f, -0.48f, -0.5f, -0.83f, -0.99f, -0.83f);
        pathBuilderS.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _playForWork = imageVectorBuild;
        return imageVectorBuild;
    }
}
