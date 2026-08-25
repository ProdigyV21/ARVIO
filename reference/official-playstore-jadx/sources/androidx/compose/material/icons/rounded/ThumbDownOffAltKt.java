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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thumbDownOffAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ThumbDownOffAlt", "Landroidx/compose/material/icons/Icons$Rounded;", "getThumbDownOffAlt", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThumbDownOffAltKt {
    private static ImageVector _thumbDownOffAlt;

    public static final ImageVector getThumbDownOffAlt(Icons.Rounded rounded) {
        ImageVector imageVector = _thumbDownOffAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ThumbDownOffAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(14.99f, 3.0f, 6.0f);
        pathBuilderQ.curveTo(5.2f, 3.0f, 4.48f, 3.48f, 4.17f, 4.21f);
        pathBuilderQ.lineToRelative(-3.26f, 7.61f);
        pathBuilderQ.curveTo(0.06f, 13.8f, 1.51f, 16.0f, 3.66f, 16.0f);
        pathBuilderQ.horizontalLineToRelative(5.65f);
        pathBuilderQ.lineToRelative(-0.95f, 4.58f);
        pathBuilderQ.curveToRelative(-0.1f, 0.5f, 0.05f, 1.01f, 0.41f, 1.37f);
        pathBuilderQ.curveToRelative(0.29f, 0.29f, 0.67f, 0.43f, 1.05f, 0.43f);
        pathBuilderQ.curveToRelative(0.38f, 0.0f, 0.77f, -0.15f, 1.06f, -0.44f);
        pathBuilderQ.lineToRelative(5.53f, -5.54f);
        pathBuilderQ.curveToRelative(0.37f, -0.37f, 0.58f, -0.88f, 0.58f, -1.41f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveTo(16.99f, 3.9f, 16.09f, 3.0f, 14.99f, 3.0f);
        a.z(pathBuilderQ, 10.66f, 19.33f, 0.61f, -2.92f);
        pathBuilderQ.lineToRelative(0.5f, -2.41f);
        pathBuilderQ.horizontalLineTo(9.31f);
        pathBuilderQ.horizontalLineTo(3.66f);
        pathBuilderQ.curveToRelative(-0.47f, 0.0f, -0.72f, -0.28f, -0.83f, -0.45f);
        pathBuilderQ.curveToRelative(-0.11f, -0.17f, -0.27f, -0.51f, -0.08f, -0.95f);
        pathBuilderQ.lineTo(6.0f, 5.0f);
        pathBuilderQ.horizontalLineToRelative(8.99f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderQ, 0.0f, 9.99f, 10.66f, 19.33f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.0f, 3.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(8.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineTo(5.0f);
        pathBuilderA.curveTo(23.0f, 3.9f, 22.1f, 3.0f, 21.0f, 3.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _thumbDownOffAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
