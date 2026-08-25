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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_doDisturbOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DoDisturbOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDoDisturbOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DoDisturbOffKt {
    private static ImageVector _doDisturbOff;

    public static final ImageVector getDoDisturbOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _doDisturbOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.DoDisturbOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 4.0f);
        pathBuilderA.curveToRelative(-1.41f, 0.0f, -2.73f, 0.37f, -3.88f, 1.01f);
        pathBuilderA.lineToRelative(6.0f, 5.99f);
        a.h(pathBuilderA, 17.0f, 11.0f, 2.0f, -0.88f);
        pathBuilderA.lineTo(19.0f, 15.88f);
        pathBuilderA.curveToRelative(0.63f, -1.15f, 1.0f, -2.47f, 1.0f, -3.88f);
        pathBuilderA.curveToRelative(0.0f, -4.41f, -3.59f, -8.0f, -8.0f, -8.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 20.0f);
        pathBuilderA.curveToRelative(1.56f, 0.0f, 3.0f, -0.45f, 4.23f, -1.23f);
        pathBuilderA.lineTo(10.46f, 13.0f);
        a.h(pathBuilderA, 7.0f, 13.0f, -2.0f, 1.46f);
        pathBuilderA.lineTo(5.23f, 7.77f);
        pathBuilderA.curveTo(4.45f, 9.0f, 4.0f, 10.44f, 4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 4.41f, 3.59f, 8.0f, 8.0f, 8.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 4.0f);
        pathBuilderA2.curveToRelative(4.41f, 0.0f, 8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.41f, -0.37f, 2.73f, -1.01f, 3.88f);
        pathBuilderA2.lineToRelative(1.46f, 1.46f);
        pathBuilderA2.curveTo(21.43f, 15.79f, 22.0f, 13.96f, 22.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, -5.52f, -4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderA2.curveToRelative(-1.96f, 0.0f, -3.79f, 0.57f, -5.33f, 1.55f);
        pathBuilderA2.lineToRelative(1.46f, 1.46f);
        pathBuilderA2.curveTo(9.27f, 4.37f, 10.59f, 4.0f, 12.0f, 4.0f);
        a.g(pathBuilderA2, 17.0f, 11.0f, -2.88f);
        d.C(pathBuilderA2, 2.0f, 2.0f, 17.0f, 13.0f);
        pathBuilderA2.moveTo(2.41f, 2.13f);
        pathBuilderA2.lineTo(1.0f, 3.54f);
        pathBuilderA2.lineToRelative(2.78f, 2.78f);
        pathBuilderA2.curveTo(2.66f, 7.93f, 2.0f, 9.89f, 2.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA2.curveToRelative(2.11f, 0.0f, 4.07f, -0.66f, 5.68f, -1.78f);
        pathBuilderA2.lineTo(20.46f, 23.0f);
        d.C(pathBuilderA2, 1.41f, -1.41f, 2.41f, 2.13f);
        pathBuilderA2.moveTo(12.0f, 20.0f);
        pathBuilderA2.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.56f, 0.45f, -3.0f, 1.23f, -4.23f);
        pathBuilderA2.lineTo(8.46f, 11.0f);
        a.h(pathBuilderA2, 7.0f, 11.0f, 2.0f, 3.46f);
        pathBuilderA2.lineToRelative(5.77f, 5.77f);
        pathBuilderA2.curveTo(15.0f, 19.55f, 13.56f, 20.0f, 12.0f, 20.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _doDisturbOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
