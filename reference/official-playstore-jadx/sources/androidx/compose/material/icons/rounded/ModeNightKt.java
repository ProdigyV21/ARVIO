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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_modeNight", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ModeNight", "Landroidx/compose/material/icons/Icons$Rounded;", "getModeNight", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModeNightKt {
    private static ImageVector _modeNight;

    public static final ImageVector getModeNight(Icons.Rounded rounded) {
        ImageVector imageVector = _modeNight;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ModeNight", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.93f, 2.3f);
        pathBuilderA.curveTo(9.89f, 1.8f, 7.91f, 1.95f, 6.16f, 2.58f);
        pathBuilderA.curveTo(5.44f, 2.84f, 5.25f, 3.8f, 5.85f, 4.29f);
        pathBuilderA.curveTo(8.08f, 6.12f, 9.5f, 8.89f, 9.5f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 3.11f, -1.42f, 5.88f, -3.65f, 7.71f);
        pathBuilderA.curveToRelative(-0.59f, 0.49f, -0.42f, 1.45f, 0.31f, 1.7f);
        pathBuilderA.curveTo(7.2f, 21.79f, 8.33f, 22.0f, 9.5f, 22.0f);
        pathBuilderA.curveToRelative(6.05f, 0.0f, 10.85f, -5.38f, 9.87f, -11.6f);
        pathBuilderA.curveTo(18.76f, 6.48f, 15.78f, 3.24f, 11.93f, 2.3f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _modeNight = imageVectorBuild;
        return imageVectorBuild;
    }
}
