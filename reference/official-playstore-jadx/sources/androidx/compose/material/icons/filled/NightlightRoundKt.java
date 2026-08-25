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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nightlightRound", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NightlightRound", "Landroidx/compose/material/icons/Icons$Filled;", "getNightlightRound", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NightlightRoundKt {
    private static ImageVector _nightlightRound;

    public static final ImageVector getNightlightRound(Icons.Filled filled) {
        ImageVector imageVector = _nightlightRound;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NightlightRound", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.01f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -3.57f, 2.2f, -6.62f, 5.31f, -7.87f);
        pathBuilderA.curveToRelative(0.89f, -0.36f, 0.75f, -1.69f, -0.19f, -1.9f);
        pathBuilderA.curveToRelative(-1.1f, -0.24f, -2.27f, -0.3f, -3.48f, -0.14f);
        pathBuilderA.curveToRelative(-4.51f, 0.6f, -8.12f, 4.31f, -8.59f, 8.83f);
        pathBuilderA.curveTo(4.44f, 16.93f, 9.13f, 22.0f, 15.01f, 22.0f);
        pathBuilderA.curveToRelative(0.73f, 0.0f, 1.43f, -0.08f, 2.12f, -0.23f);
        pathBuilderA.curveToRelative(0.95f, -0.21f, 1.1f, -1.53f, 0.2f, -1.9f);
        pathBuilderA.curveToRelative(-3.22f, -1.29f, -5.33f, -4.41f, -5.32f, -7.87f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nightlightRound = imageVectorBuild;
        return imageVectorBuild;
    }
}
