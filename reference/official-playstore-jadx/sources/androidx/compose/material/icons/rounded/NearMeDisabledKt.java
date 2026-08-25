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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nearMeDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NearMeDisabled", "Landroidx/compose/material/icons/Icons$Rounded;", "getNearMeDisabled", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NearMeDisabledKt {
    private static ImageVector _nearMeDisabled;

    public static final ImageVector getNearMeDisabled(Icons.Rounded rounded) {
        ImageVector imageVector = _nearMeDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.NearMeDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(12.0f, 6.34f, 6.95f, -2.58f);
        pathBuilderR.curveToRelative(0.8f, -0.3f, 1.58f, 0.48f, 1.29f, 1.29f);
        b.y(pathBuilderR, 17.66f, 12.0f, 12.0f, 6.34f);
        pathBuilderR.moveTo(21.9f, 19.07f);
        pathBuilderR.lineTo(4.93f, 2.1f);
        pathBuilderR.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderR.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderR.lineToRelative(4.36f, 4.36f);
        pathBuilderR.lineToRelative(-4.2f, 1.56f);
        pathBuilderR.curveTo(3.27f, 9.59f, 3.0f, 9.97f, 3.0f, 10.4f);
        pathBuilderR.curveToRelative(0.0f, 0.42f, 0.26f, 0.8f, 0.65f, 0.96f);
        pathBuilderR.lineToRelative(6.42f, 2.57f);
        pathBuilderR.lineToRelative(2.57f, 6.42f);
        pathBuilderR.curveTo(12.8f, 20.74f, 13.18f, 21.0f, 13.6f, 21.0f);
        pathBuilderR.curveToRelative(0.43f, 0.0f, 0.82f, -0.27f, 0.97f, -0.67f);
        pathBuilderR.lineToRelative(1.56f, -4.2f);
        pathBuilderR.lineToRelative(4.36f, 4.36f);
        pathBuilderR.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderR.curveTo(22.29f, 20.09f, 22.29f, 19.46f, 21.9f, 19.07f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nearMeDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
