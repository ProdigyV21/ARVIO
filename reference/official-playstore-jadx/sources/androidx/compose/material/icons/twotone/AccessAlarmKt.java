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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_accessAlarm", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AccessAlarm", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAccessAlarm", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AccessAlarmKt {
    private static ImageVector _accessAlarm;

    public static final ImageVector getAccessAlarm(Icons.TwoTone twoTone) {
        ImageVector imageVector = _accessAlarm;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AccessAlarm", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.0f);
        pathBuilderA.curveToRelative(-3.87f, 0.0f, -7.0f, 3.13f, -7.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(3.13f, 7.0f, 7.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(7.0f, -3.13f, 7.0f, -7.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.13f, -7.0f, -7.0f, -7.0f);
        a.p(pathBuilderA, 15.75f, 16.85f, 11.0f, 14.0f);
        b.A(pathBuilderA, 11.0f, 8.0f, 1.5f, 5.25f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, 4.0f, 2.37f, -0.75f, 1.23f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 4.0f);
        pathBuilderA2.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderA2.reflectiveCurveToRelative(4.02f, 9.0f, 9.0f, 9.0f);
        pathBuilderA2.curveToRelative(4.97f, 0.0f, 9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderA2.reflectiveCurveToRelative(-4.03f, -9.0f, -9.0f, -9.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 20.0f);
        pathBuilderA2.curveToRelative(-3.87f, 0.0f, -7.0f, -3.13f, -7.0f, -7.0f);
        pathBuilderA2.reflectiveCurveToRelative(3.13f, -7.0f, 7.0f, -7.0f);
        pathBuilderA2.reflectiveCurveToRelative(7.0f, 3.13f, 7.0f, 7.0f);
        pathBuilderA2.reflectiveCurveToRelative(-3.13f, 7.0f, -7.0f, 7.0f);
        a.p(pathBuilderA2, 12.5f, 8.0f, 11.0f, 8.0f);
        pathBuilderA2.verticalLineToRelative(6.0f);
        pathBuilderA2.lineToRelative(4.75f, 2.85f);
        b.D(pathBuilderA2, 0.75f, -1.23f, -4.0f, -2.37f);
        pathBuilderA2.moveTo(22.0f, 5.72f);
        pathBuilderA2.lineToRelative(-4.6f, -3.86f);
        b.D(pathBuilderA2, -1.29f, 1.53f, 4.6f, 3.86f);
        pathBuilderA2.moveTo(7.88f, 3.39f);
        pathBuilderA2.lineTo(6.6f, 1.86f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderA2, 2.0f, 5.71f, 1.29f, 1.53f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _accessAlarm = imageVectorBuild;
        return imageVectorBuild;
    }
}
