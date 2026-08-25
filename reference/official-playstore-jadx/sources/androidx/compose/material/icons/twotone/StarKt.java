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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_star", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Star", "Landroidx/compose/material/icons/Icons$TwoTone;", "getStar", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StarKt {
    private static ImageVector _star;

    public static final ImageVector getStar(Icons.TwoTone twoTone) {
        ImageVector imageVector = _star;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Star", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(12.0f, 15.4f, -3.76f, 2.27f);
        pathBuilderR.lineToRelative(1.0f, -4.28f);
        pathBuilderR.lineToRelative(-3.32f, -2.88f);
        pathBuilderR.lineToRelative(4.38f, -0.38f);
        pathBuilderR.lineToRelative(1.7f, -4.03f);
        pathBuilderR.lineToRelative(1.71f, 4.04f);
        pathBuilderR.lineToRelative(4.38f, 0.38f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -3.32f, 2.88f, 1.0f, 4.28f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(22.0f, 9.24f, -7.19f, -0.62f);
        pathBuilderR2.lineTo(12.0f, 2.0f);
        pathBuilderR2.lineTo(9.19f, 8.63f);
        pathBuilderR2.lineTo(2.0f, 9.24f);
        pathBuilderR2.lineToRelative(5.46f, 4.73f);
        pathBuilderR2.lineTo(5.82f, 21.0f);
        pathBuilderR2.lineTo(12.0f, 17.27f);
        pathBuilderR2.lineTo(18.18f, 21.0f);
        d.C(pathBuilderR2, -1.63f, -7.03f, 22.0f, 9.24f);
        pathBuilderR2.moveTo(12.0f, 15.4f);
        pathBuilderR2.lineToRelative(-3.76f, 2.27f);
        pathBuilderR2.lineToRelative(1.0f, -4.28f);
        pathBuilderR2.lineToRelative(-3.32f, -2.88f);
        pathBuilderR2.lineToRelative(4.38f, -0.38f);
        pathBuilderR2.lineTo(12.0f, 6.1f);
        pathBuilderR2.lineToRelative(1.71f, 4.04f);
        pathBuilderR2.lineToRelative(4.38f, 0.38f);
        pathBuilderR2.lineToRelative(-3.32f, 2.88f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR2, 1.0f, 4.28f, 12.0f, 15.4f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _star = imageVectorBuild;
        return imageVectorBuild;
    }
}
