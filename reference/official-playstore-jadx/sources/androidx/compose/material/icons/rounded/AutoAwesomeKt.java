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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_autoAwesome", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AutoAwesome", "Landroidx/compose/material/icons/Icons$Rounded;", "getAutoAwesome", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AutoAwesomeKt {
    private static ImageVector _autoAwesome;

    public static final ImageVector getAutoAwesome(Icons.Rounded rounded) {
        ImageVector imageVector = _autoAwesome;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AutoAwesome", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.46f, 8.0f, 0.79f, -1.75f);
        pathBuilderR.lineTo(22.0f, 5.46f);
        pathBuilderR.curveToRelative(0.39f, -0.18f, 0.39f, -0.73f, 0.0f, -0.91f);
        pathBuilderR.lineToRelative(-1.75f, -0.79f);
        pathBuilderR.lineTo(19.46f, 2.0f);
        pathBuilderR.curveToRelative(-0.18f, -0.39f, -0.73f, -0.39f, -0.91f, 0.0f);
        pathBuilderR.lineToRelative(-0.79f, 1.75f);
        pathBuilderR.lineTo(16.0f, 4.54f);
        pathBuilderR.curveToRelative(-0.39f, 0.18f, -0.39f, 0.73f, 0.0f, 0.91f);
        pathBuilderR.lineToRelative(1.75f, 0.79f);
        pathBuilderR.lineTo(18.54f, 8.0f);
        pathBuilderR.curveTo(18.72f, 8.39f, 19.28f, 8.39f, 19.46f, 8.0f);
        a.p(pathBuilderR, 11.5f, 9.5f, 9.91f, 6.0f);
        pathBuilderR.curveTo(9.56f, 5.22f, 8.44f, 5.22f, 8.09f, 6.0f);
        pathBuilderR.lineTo(6.5f, 9.5f);
        pathBuilderR.lineTo(3.0f, 11.09f);
        pathBuilderR.curveToRelative(-0.78f, 0.36f, -0.78f, 1.47f, 0.0f, 1.82f);
        pathBuilderR.lineToRelative(3.5f, 1.59f);
        pathBuilderR.lineTo(8.09f, 18.0f);
        pathBuilderR.curveToRelative(0.36f, 0.78f, 1.47f, 0.78f, 1.82f, 0.0f);
        pathBuilderR.lineToRelative(1.59f, -3.5f);
        pathBuilderR.lineToRelative(3.5f, -1.59f);
        pathBuilderR.curveToRelative(0.78f, -0.36f, 0.78f, -1.47f, 0.0f, -1.82f);
        c.z(pathBuilderR, 11.5f, 9.5f, 18.54f, 16.0f);
        pathBuilderR.lineToRelative(-0.79f, 1.75f);
        pathBuilderR.lineTo(16.0f, 18.54f);
        pathBuilderR.curveToRelative(-0.39f, 0.18f, -0.39f, 0.73f, 0.0f, 0.91f);
        pathBuilderR.lineToRelative(1.75f, 0.79f);
        pathBuilderR.lineTo(18.54f, 22.0f);
        pathBuilderR.curveToRelative(0.18f, 0.39f, 0.73f, 0.39f, 0.91f, 0.0f);
        pathBuilderR.lineToRelative(0.79f, -1.75f);
        pathBuilderR.lineTo(22.0f, 19.46f);
        pathBuilderR.curveToRelative(0.39f, -0.18f, 0.39f, -0.73f, 0.0f, -0.91f);
        pathBuilderR.lineToRelative(-1.75f, -0.79f);
        pathBuilderR.lineTo(19.46f, 16.0f);
        pathBuilderR.curveTo(19.28f, 15.61f, 18.72f, 15.61f, 18.54f, 16.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _autoAwesome = imageVectorBuild;
        return imageVectorBuild;
    }
}
