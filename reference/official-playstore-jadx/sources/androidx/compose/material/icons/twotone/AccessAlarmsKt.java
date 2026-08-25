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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_accessAlarms", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AccessAlarms", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAccessAlarms", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AccessAlarmsKt {
    private static ImageVector _accessAlarms;

    public static final ImageVector getAccessAlarms(Icons.TwoTone twoTone) {
        ImageVector imageVector = _accessAlarms;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AccessAlarms", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.0f);
        pathBuilderA.curveToRelative(-3.9f, 0.0f, -7.0f, 3.1f, -7.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(3.1f, 7.0f, 7.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(7.0f, -3.1f, 7.0f, -7.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.1f, -7.0f, -7.0f, -7.0f);
        a.p(pathBuilderA, 15.7f, 16.9f, 11.0f, 14.0f);
        b.A(pathBuilderA, 11.0f, 8.0f, 1.5f, 5.3f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, 4.0f, 2.4f, -0.8f, 1.2f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(22.0f, 5.7f, -4.6f, -3.9f);
        b.D(pathBuilderR, -1.3f, 1.5f, 4.6f, 3.9f);
        pathBuilderR.moveTo(12.5f, 8.0f);
        pathBuilderR.lineTo(11.0f, 8.0f);
        pathBuilderR.verticalLineToRelative(6.0f);
        pathBuilderR.lineToRelative(4.7f, 2.9f);
        b.D(pathBuilderR, 0.8f, -1.2f, -4.0f, -2.4f);
        pathBuilderR.moveTo(12.0f, 4.0f);
        pathBuilderR.curveToRelative(-5.0f, 0.0f, -9.0f, 4.0f, -9.0f, 9.0f);
        pathBuilderR.reflectiveCurveToRelative(4.0f, 9.0f, 9.0f, 9.0f);
        pathBuilderR.reflectiveCurveToRelative(9.0f, -4.0f, 9.0f, -9.0f);
        pathBuilderR.reflectiveCurveToRelative(-4.0f, -9.0f, -9.0f, -9.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 20.0f);
        pathBuilderR.curveToRelative(-3.9f, 0.0f, -7.0f, -3.1f, -7.0f, -7.0f);
        pathBuilderR.reflectiveCurveToRelative(3.1f, -7.0f, 7.0f, -7.0f);
        pathBuilderR.reflectiveCurveToRelative(7.0f, 3.1f, 7.0f, 7.0f);
        pathBuilderR.reflectiveCurveToRelative(-3.1f, 7.0f, -7.0f, 7.0f);
        a.p(pathBuilderR, 7.9f, 3.4f, 6.6f, 1.9f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderR, 2.0f, 5.7f, 1.3f, 1.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _accessAlarms = imageVectorBuild;
        return imageVectorBuild;
    }
}
