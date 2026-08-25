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
import v.a;
import v.b;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_handyman", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Handyman", "Landroidx/compose/material/icons/Icons$Filled;", "getHandyman", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HandymanKt {
    private static ImageVector _handyman;

    public static final ImageVector getHandyman(Icons.Filled filled) {
        ImageVector imageVector = _handyman;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Handyman", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = e.c(21.67f, 18.17f, -5.3f, -5.3f, -0.99f);
        pathBuilderC.lineToRelative(-2.54f, 2.54f);
        pathBuilderC.verticalLineToRelative(0.99f);
        pathBuilderC.lineToRelative(5.3f, 5.3f);
        pathBuilderC.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderC.lineToRelative(2.12f, -2.12f);
        pathBuilderC.curveTo(22.06f, 19.2f, 22.06f, 18.56f, 21.67f, 18.17f);
        pathBuilderC.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderC.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(17.34f, 10.19f, 1.41f, -1.41f);
        pathBuilderR.lineToRelative(2.12f, 2.12f);
        pathBuilderR.curveToRelative(1.17f, -1.17f, 1.17f, -3.07f, 0.0f, -4.24f);
        pathBuilderR.lineToRelative(-3.54f, -3.54f);
        pathBuilderR.lineToRelative(-1.41f, 1.41f);
        pathBuilderR.verticalLineTo(1.71f);
        pathBuilderR.lineTo(15.22f, 1.0f);
        pathBuilderR.lineToRelative(-3.54f, 3.54f);
        pathBuilderR.lineToRelative(0.71f, 0.71f);
        pathBuilderR.horizontalLineToRelative(2.83f);
        pathBuilderR.lineToRelative(-1.41f, 1.41f);
        pathBuilderR.lineToRelative(1.06f, 1.06f);
        pathBuilderR.lineToRelative(-2.89f, 2.89f);
        pathBuilderR.lineTo(7.85f, 6.48f);
        pathBuilderR.verticalLineTo(5.06f);
        pathBuilderR.lineTo(4.83f, 2.04f);
        pathBuilderR.lineTo(2.0f, 4.87f);
        pathBuilderR.lineToRelative(3.03f, 3.03f);
        pathBuilderR.horizontalLineToRelative(1.41f);
        pathBuilderR.lineToRelative(4.13f, 4.13f);
        pathBuilderR.lineToRelative(-0.85f, 0.85f);
        pathBuilderR.horizontalLineTo(7.6f);
        pathBuilderR.lineToRelative(-5.3f, 5.3f);
        pathBuilderR.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderR.lineToRelative(2.12f, 2.12f);
        pathBuilderR.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderR.lineToRelative(5.3f, -5.3f);
        pathBuilderR.verticalLineToRelative(-2.12f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 5.15f, -5.15f, 17.34f, 10.19f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _handyman = imageVectorBuild;
        return imageVectorBuild;
    }
}
