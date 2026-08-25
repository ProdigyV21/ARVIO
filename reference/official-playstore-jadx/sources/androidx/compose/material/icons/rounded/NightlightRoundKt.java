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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nightlightRound", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NightlightRound", "Landroidx/compose/material/icons/Icons$Rounded;", "getNightlightRound", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NightlightRoundKt {
    private static ImageVector _nightlightRound;

    public static final ImageVector getNightlightRound(Icons.Rounded rounded) {
        ImageVector imageVector = _nightlightRound;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.NightlightRound", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.5f, 22.0f);
        pathBuilderA.curveToRelative(0.07f, 0.0f, 0.14f, 0.0f, 0.21f, 0.0f);
        pathBuilderA.curveToRelative(0.84f, -0.02f, 1.12f, -1.11f, 0.41f, -1.56f);
        pathBuilderA.curveToRelative(-2.78f, -1.77f, -4.63f, -4.89f, -4.63f, -8.43f);
        pathBuilderA.curveToRelative(0.0f, -3.55f, 1.85f, -6.66f, 4.63f, -8.44f);
        pathBuilderA.curveToRelative(0.7f, -0.45f, 0.44f, -1.54f, -0.39f, -1.56f);
        pathBuilderA.curveToRelative(-0.04f, 0.0f, -0.09f, 0.0f, -0.13f, 0.0f);
        pathBuilderA.curveToRelative(-4.9f, -0.05f, -9.21f, 3.53f, -9.98f, 8.37f);
        pathBuilderA.curveTo(4.64f, 16.61f, 9.45f, 22.0f, 15.5f, 22.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 15.5f, 22.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nightlightRound = imageVectorBuild;
        return imageVectorBuild;
    }
}
