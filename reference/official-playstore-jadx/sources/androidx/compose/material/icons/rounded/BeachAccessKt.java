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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_beachAccess", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BeachAccess", "Landroidx/compose/material/icons/Icons$Rounded;", "getBeachAccess", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BeachAccessKt {
    private static ImageVector _beachAccess;

    public static final ImageVector getBeachAccess(Icons.Rounded rounded) {
        ImageVector imageVector = _beachAccess;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.BeachAccess", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(13.13f, 14.56f, 1.43f, -1.43f);
        pathBuilderR.lineToRelative(5.73f, 5.73f);
        pathBuilderR.curveToRelative(0.39f, 0.39f, 0.39f, 1.03f, 0.0f, 1.43f);
        pathBuilderR.curveToRelative(-0.39f, 0.39f, -1.03f, 0.39f, -1.43f, 0.0f);
        b.C(pathBuilderR, -5.73f, -5.73f, 17.42f, 8.83f);
        pathBuilderR.lineToRelative(1.27f, -1.27f);
        pathBuilderR.curveToRelative(0.89f, -0.89f, 0.77f, -2.43f, -0.31f, -3.08f);
        pathBuilderR.curveToRelative(-3.89f, -2.38f, -9.03f, -1.89f, -12.4f, 1.47f);
        pathBuilderR.curveToRelative(3.93f, -1.3f, 8.31f, -0.25f, 11.44f, 2.88f);
        pathBuilderR.close();
        pathBuilderR.moveTo(5.95f, 5.98f);
        pathBuilderR.curveToRelative(-3.36f, 3.37f, -3.85f, 8.51f, -1.48f, 12.4f);
        pathBuilderR.curveToRelative(0.66f, 1.08f, 2.19f, 1.21f, 3.08f, 0.31f);
        pathBuilderR.lineToRelative(1.27f, -1.27f);
        pathBuilderR.curveTo(5.7f, 14.29f, 4.65f, 9.91f, 5.95f, 5.98f);
        a.z(pathBuilderR, 5.97f, 5.96f, -0.01f, 0.01f);
        pathBuilderR.curveToRelative(-0.38f, 3.01f, 1.17f, 6.88f, 4.3f, 10.02f);
        pathBuilderR.lineToRelative(5.73f, -5.73f);
        pathBuilderR.curveToRelative(-3.13f, -3.13f, -7.01f, -4.68f, -10.02f, -4.3f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _beachAccess = imageVectorBuild;
        return imageVectorBuild;
    }
}
