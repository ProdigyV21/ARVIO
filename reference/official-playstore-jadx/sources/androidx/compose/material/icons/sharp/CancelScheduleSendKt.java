package androidx.compose.material.icons.sharp;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cancelScheduleSend", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CancelScheduleSend", "Landroidx/compose/material/icons/Icons$Sharp;", "getCancelScheduleSend", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CancelScheduleSendKt {
    private static ImageVector _cancelScheduleSend;

    public static final ImageVector getCancelScheduleSend(Icons.Sharp sharp) {
        ImageVector imageVector = _cancelScheduleSend;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.CancelScheduleSend", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.5f, 9.0f);
        pathBuilderA.curveToRelative(-0.42f, 0.0f, -0.83f, 0.04f, -1.24f, 0.11f);
        pathBuilderA.lineTo(1.01f, 3.0f);
        pathBuilderA.lineTo(1.0f, 10.0f);
        pathBuilderA.lineToRelative(9.0f, 2.0f);
        pathBuilderA.lineToRelative(-9.0f, 2.0f);
        pathBuilderA.lineToRelative(0.01f, 7.0f);
        pathBuilderA.lineToRelative(8.07f, -3.46f);
        pathBuilderA.curveTo(9.59f, 21.19f, 12.71f, 24.0f, 16.5f, 24.0f);
        pathBuilderA.curveToRelative(4.14f, 0.0f, 7.5f, -3.36f, 7.5f, -7.5f);
        pathBuilderA.reflectiveCurveTo(20.64f, 9.0f, 16.5f, 9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.5f, 22.0f);
        pathBuilderA.curveToRelative(-3.03f, 0.0f, -5.5f, -2.47f, -5.5f, -5.5f);
        pathBuilderA.reflectiveCurveToRelative(2.47f, -5.5f, 5.5f, -5.5f);
        pathBuilderA.reflectiveCurveToRelative(5.5f, 2.47f, 5.5f, 5.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 19.53f, 22.0f, 16.5f, 22.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.27f, 14.03f, -1.77f, 1.76f);
        pathBuilderR.lineToRelative(-1.77f, -1.76f);
        pathBuilderR.lineToRelative(-0.7f, 0.7f);
        pathBuilderR.lineToRelative(1.76f, 1.77f);
        pathBuilderR.lineToRelative(-1.76f, 1.77f);
        pathBuilderR.lineToRelative(0.7f, 0.7f);
        pathBuilderR.lineToRelative(1.77f, -1.76f);
        pathBuilderR.lineToRelative(1.77f, 1.76f);
        pathBuilderR.lineToRelative(0.7f, -0.7f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -1.76f, -1.77f, 1.76f, -1.77f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cancelScheduleSend = imageVectorBuild;
        return imageVectorBuild;
    }
}
