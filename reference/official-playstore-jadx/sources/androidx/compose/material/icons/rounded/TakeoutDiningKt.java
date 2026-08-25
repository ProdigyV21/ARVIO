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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_takeoutDining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TakeoutDining", "Landroidx/compose/material/icons/Icons$Rounded;", "getTakeoutDining", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TakeoutDiningKt {
    private static ImageVector _takeoutDining;

    public static final ImageVector getTakeoutDining(Icons.Rounded rounded) {
        ImageVector imageVector = _takeoutDining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TakeoutDining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.29f, 6.75f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.01f, -0.39f, -1.4f, 0.0f);
        pathBuilderA.lineTo(19.0f, 7.63f);
        pathBuilderA.lineToRelative(0.03f, -0.56f);
        pathBuilderA.lineToRelative(-3.46f, -3.48f);
        pathBuilderA.curveTo(15.19f, 3.21f, 14.68f, 3.0f, 14.15f, 3.0f);
        pathBuilderA.horizontalLineToRelative(-4.3f);
        pathBuilderA.curveTo(9.32f, 3.0f, 8.81f, 3.21f, 8.43f, 3.59f);
        pathBuilderA.lineTo(4.97f, 7.07f);
        pathBuilderA.lineTo(5.0f, 7.57f);
        pathBuilderA.lineTo(4.11f, 6.7f);
        pathBuilderA.curveTo(3.72f, 6.32f, 3.1f, 6.32f, 2.72f, 6.71f);
        pathBuilderA.lineTo(2.7f, 6.73f);
        pathBuilderA.curveTo(2.32f, 7.12f, 2.32f, 7.75f, 2.72f, 8.13f);
        pathBuilderA.lineTo(4.66f, 10.0f);
        pathBuilderA.horizontalLineToRelative(14.69f);
        pathBuilderA.lineToRelative(1.92f, -1.84f);
        pathBuilderA.curveTo(21.67f, 7.78f, 21.68f, 7.14f, 21.29f, 6.75f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(5.79f, 18.15f);
        pathBuilderA2.curveTo(5.87f, 19.19f, 6.74f, 20.0f, 7.79f, 20.0f);
        pathBuilderA2.horizontalLineToRelative(8.43f);
        pathBuilderA2.curveToRelative(1.05f, 0.0f, 1.92f, -0.81f, 1.99f, -1.85f);
        pathBuilderA2.lineToRelative(0.49f, -6.6f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.i(pathBuilderA2, 5.3f, 5.79f, 18.15f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _takeoutDining = imageVectorBuild;
        return imageVectorBuild;
    }
}
