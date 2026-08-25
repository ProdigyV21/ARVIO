package androidx.compose.material.icons.rounded;

import a0.a;
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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phonelinkOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhonelinkOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhonelinkOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhonelinkOffKt {
    private static ImageVector _phonelinkOff;

    public static final ImageVector getPhonelinkOff(Icons.Rounded rounded) {
        ImageVector imageVector = _phonelinkOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PhonelinkOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(24.0f, 19.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-6.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        a.l(pathBuilderA, 3.61f, 2.0f, 2.0f, 10.0f);
        e.g(pathBuilderA, 4.0f, 7.0f, -1.61f);
        pathBuilderA.lineToRelative(2.93f, 2.93f);
        pathBuilderA.curveToRelative(0.39f, -0.13f, 0.68f, -0.49f, 0.68f, -0.93f);
        pathBuilderA.close();
        pathBuilderA.moveTo(21.0f, 6.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(7.39f);
        pathBuilderA.lineToRelative(2.0f, 2.0f);
        pathBuilderA.horizontalLineTo(21.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(1.36f, 2.21f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(1.11f, 1.11f);
        pathBuilderA.curveTo(2.18f, 5.08f, 2.0f, 5.52f, 2.0f, 6.0f);
        pathBuilderA.verticalLineToRelative(11.0f);
        pathBuilderA.horizontalLineToRelative(-0.5f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(0.67f, 20.0f, 1.5f, 20.0f);
        pathBuilderA.horizontalLineToRelative(16.23f);
        pathBuilderA.lineToRelative(1.64f, 1.64f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineTo(2.77f, 2.21f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        w.a.u(pathBuilderA, 4.0f, 17.0f, 6.27f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.w(pathBuilderA, 14.73f, 17.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phonelinkOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
