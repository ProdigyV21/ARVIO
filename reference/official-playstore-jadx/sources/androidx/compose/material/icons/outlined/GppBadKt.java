package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_gppBad", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GppBad", "Landroidx/compose/material/icons/Icons$Outlined;", "getGppBad", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GppBadKt {
    private static ImageVector _gppBad;

    public static final ImageVector getGppBad(Icons.Outlined outlined) {
        ImageVector imageVector = _gppBad;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.GppBad", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(12.0f, 2.0f, 4.0f, 5.0f, 6.09f);
        pathBuilderS.curveToRelative(0.0f, 5.05f, 3.41f, 9.76f, 8.0f, 10.91f);
        pathBuilderS.curveToRelative(4.59f, -1.15f, 8.0f, -5.86f, 8.0f, -10.91f);
        c.x(pathBuilderS, 5.0f, 12.0f, 2.0f);
        pathBuilderS.moveTo(18.0f, 11.09f);
        pathBuilderS.curveToRelative(0.0f, 4.0f, -2.55f, 7.7f, -6.0f, 8.83f);
        pathBuilderS.curveToRelative(-3.45f, -1.13f, -6.0f, -4.82f, -6.0f, -8.83f);
        pathBuilderS.verticalLineToRelative(-4.7f);
        pathBuilderS.lineToRelative(6.0f, -2.25f);
        c.e(pathBuilderS, 6.0f, 2.25f, 11.09f);
        pathBuilderS.moveTo(9.91f, 8.5f);
        pathBuilderS.lineTo(8.5f, 9.91f);
        pathBuilderS.lineTo(10.59f, 12.0f);
        pathBuilderS.lineTo(8.5f, 14.09f);
        pathBuilderS.lineToRelative(1.41f, 1.41f);
        pathBuilderS.lineTo(12.0f, 13.42f);
        pathBuilderS.lineToRelative(2.09f, 2.08f);
        pathBuilderS.lineToRelative(1.41f, -1.41f);
        pathBuilderS.lineTo(13.42f, 12.0f);
        pathBuilderS.lineToRelative(2.08f, -2.09f);
        pathBuilderS.lineTo(14.09f, 8.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderS, 12.0f, 10.59f, 9.91f, 8.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _gppBad = imageVectorBuild;
        return imageVectorBuild;
    }
}
