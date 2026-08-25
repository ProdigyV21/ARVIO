package androidx.compose.material.icons.twotone;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_starBorder", "Landroidx/compose/ui/graphics/vector/ImageVector;", "StarBorder", "Landroidx/compose/material/icons/Icons$TwoTone;", "getStarBorder", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StarBorderKt {
    private static ImageVector _starBorder;

    public static final ImageVector getStarBorder(Icons.TwoTone twoTone) {
        ImageVector imageVector = _starBorder;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.StarBorder", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(22.0f, 9.24f, -7.19f, -0.62f);
        pathBuilderR.lineTo(12.0f, 2.0f);
        pathBuilderR.lineTo(9.19f, 8.63f);
        pathBuilderR.lineTo(2.0f, 9.24f);
        pathBuilderR.lineToRelative(5.46f, 4.73f);
        pathBuilderR.lineTo(5.82f, 21.0f);
        pathBuilderR.lineTo(12.0f, 17.27f);
        pathBuilderR.lineTo(18.18f, 21.0f);
        d.C(pathBuilderR, -1.63f, -7.03f, 22.0f, 9.24f);
        pathBuilderR.moveTo(12.0f, 15.4f);
        pathBuilderR.lineToRelative(-3.76f, 2.27f);
        pathBuilderR.lineToRelative(1.0f, -4.28f);
        pathBuilderR.lineToRelative(-3.32f, -2.88f);
        pathBuilderR.lineToRelative(4.38f, -0.38f);
        pathBuilderR.lineTo(12.0f, 6.1f);
        pathBuilderR.lineToRelative(1.71f, 4.04f);
        pathBuilderR.lineToRelative(4.38f, 0.38f);
        pathBuilderR.lineToRelative(-3.32f, 2.88f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 1.0f, 4.28f, 12.0f, 15.4f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _starBorder = imageVectorBuild;
        return imageVectorBuild;
    }
}
