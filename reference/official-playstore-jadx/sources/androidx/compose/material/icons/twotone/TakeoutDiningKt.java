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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_takeoutDining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TakeoutDining", "Landroidx/compose/material/icons/Icons$TwoTone;", "getTakeoutDining", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TakeoutDiningKt {
    private static ImageVector _takeoutDining;

    public static final ImageVector getTakeoutDining(Icons.TwoTone twoTone) {
        ImageVector imageVector = _takeoutDining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.TakeoutDining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(9.83f, 5.0f, -2.8f, 2.73f);
        pathBuilderR.lineToRelative(0.09f, 1.27f);
        pathBuilderR.lineToRelative(9.75f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 0.09f, -1.27f, -2.8f, -2.73f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(7.79f, 18.0f, 8.44f, 0.0f), 0.51f, -7.0f, -9.46f, 0.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 0.3f, null, 0.3f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.59f, 6.05f, 19.0f, 7.63f);
        pathBuilderJ.lineToRelative(0.03f, -0.56f);
        pathBuilderJ.lineTo(14.98f, 3.0f);
        pathBuilderJ.horizontalLineTo(9.02f);
        pathBuilderJ.lineTo(4.97f, 7.07f);
        pathBuilderJ.lineTo(5.0f, 7.57f);
        pathBuilderJ.lineTo(3.41f, 6.01f);
        pathBuilderJ.lineTo(2.0f, 7.44f);
        pathBuilderJ.lineToRelative(3.23f, 3.11f);
        pathBuilderJ.lineTo(5.93f, 20.0f);
        pathBuilderJ.horizontalLineToRelative(12.14f);
        pathBuilderJ.lineToRelative(0.7f, -9.44f);
        b.y(pathBuilderJ, 22.0f, 7.46f, 20.59f, 6.05f);
        pathBuilderJ.moveTo(16.23f, 18.0f);
        a0.b.D(pathBuilderJ, 7.79f, -0.51f, -7.0f, 9.46f);
        c.z(pathBuilderJ, 16.23f, 18.0f, 16.87f, 9.0f);
        pathBuilderJ.horizontalLineTo(7.12f);
        pathBuilderJ.lineTo(7.03f, 7.73f);
        pathBuilderJ.lineTo(9.83f, 5.0f);
        pathBuilderJ.horizontalLineToRelative(4.33f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderJ, 2.8f, 2.73f, 16.87f, 9.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _takeoutDining = imageVectorBuild;
        return imageVectorBuild;
    }
}
