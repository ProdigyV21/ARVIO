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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_micOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MicOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getMicOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MicOffKt {
    private static ImageVector _micOff;

    public static final ImageVector getMicOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _micOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.MicOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 3.7f);
        pathBuilderA.curveToRelative(-0.66f, 0.0f, -1.2f, 0.54f, -1.2f, 1.2f);
        pathBuilderA.verticalLineToRelative(1.51f);
        pathBuilderA.lineToRelative(2.39f, 2.39f);
        pathBuilderA.lineToRelative(0.01f, -3.9f);
        pathBuilderA.curveToRelative(0.0f, -0.66f, -0.54f, -1.2f, -1.2f, -1.2f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = a.a(19.0f, 11.0f, -1.7f);
        pathBuilderA2.curveToRelative(0.0f, 0.58f, -0.1f, 1.13f, -0.27f, 1.64f);
        pathBuilderA2.lineToRelative(1.27f, 1.27f);
        pathBuilderA2.curveToRelative(0.44f, -0.88f, 0.7f, -1.87f, 0.7f, -2.91f);
        a.p(pathBuilderA2, 4.41f, 2.86f, 3.0f, 4.27f);
        pathBuilderA2.lineToRelative(6.0f, 6.0f);
        pathBuilderA2.verticalLineTo(11.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA2.curveToRelative(0.23f, 0.0f, 0.44f, -0.03f, 0.65f, -0.08f);
        pathBuilderA2.lineToRelative(1.66f, 1.66f);
        pathBuilderA2.curveToRelative(-0.71f, 0.33f, -1.5f, 0.52f, -2.31f, 0.52f);
        pathBuilderA2.curveToRelative(-2.76f, 0.0f, -5.3f, -2.1f, -5.3f, -5.1f);
        pathBuilderA2.horizontalLineTo(5.0f);
        pathBuilderA2.curveToRelative(0.0f, 3.41f, 2.72f, 6.23f, 6.0f, 6.72f);
        w.a.j(pathBuilderA2, 21.0f, 2.0f, -3.28f);
        pathBuilderA2.curveToRelative(0.91f, -0.13f, 1.77f, -0.45f, 2.55f, -0.9f);
        pathBuilderA2.lineToRelative(4.2f, 4.2f);
        d.C(pathBuilderA2, 1.41f, -1.41f, 4.41f, 2.86f);
        pathBuilderA2.moveTo(10.8f, 4.9f);
        pathBuilderA2.curveToRelative(0.0f, -0.66f, 0.54f, -1.2f, 1.2f, -1.2f);
        pathBuilderA2.reflectiveCurveToRelative(1.2f, 0.54f, 1.2f, 1.2f);
        pathBuilderA2.lineToRelative(-0.01f, 3.91f);
        pathBuilderA2.lineTo(15.0f, 10.6f);
        pathBuilderA2.verticalLineTo(5.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderA2.curveToRelative(-1.54f, 0.0f, -2.79f, 1.16f, -2.96f, 2.65f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.d(pathBuilderA2, 1.76f, 1.76f, 4.9f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _micOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
