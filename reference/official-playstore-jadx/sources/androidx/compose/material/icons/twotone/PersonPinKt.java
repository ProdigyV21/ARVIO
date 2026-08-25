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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_personPin", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PersonPin", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPersonPin", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PersonPinKt {
    private static ImageVector _personPin;

    public static final ImageVector getPersonPin(Icons.TwoTone twoTone) {
        ImageVector imageVector = _personPin;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PersonPin", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(9.83f, 18.0f, 0.59f, 0.59f);
        pathBuilderR.lineTo(12.0f, 20.17f);
        pathBuilderR.lineToRelative(1.59f, -1.59f);
        w.a.p(pathBuilderR, 0.58f, -0.58f, 19.0f, 4.0f);
        pathBuilderR.horizontalLineTo(5.0f);
        pathBuilderR.verticalLineToRelative(14.0f);
        pathBuilderR.horizontalLineToRelative(4.83f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 5.0f);
        pathBuilderR.curveToRelative(1.65f, 0.0f, 3.0f, 1.35f, 3.0f, 3.0f);
        pathBuilderR.reflectiveCurveToRelative(-1.35f, 3.0f, -3.0f, 3.0f);
        pathBuilderR.reflectiveCurveToRelative(-3.0f, -1.35f, -3.0f, -3.0f);
        pathBuilderR.reflectiveCurveToRelative(1.35f, -3.0f, 3.0f, -3.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(6.0f, 15.58f);
        pathBuilderR.curveTo(6.0f, 13.08f, 9.97f, 12.0f, 12.0f, 12.0f);
        pathBuilderR.reflectiveCurveToRelative(6.0f, 1.08f, 6.0f, 3.58f);
        pathBuilderR.verticalLineTo(17.0f);
        pathBuilderR.horizontalLineTo(6.0f);
        pathBuilderR.verticalLineToRelative(-1.42f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(9.0f, 20.0f, 3.0f, 3.0f);
        pathBuilderR2.lineToRelative(3.0f, -3.0f);
        pathBuilderR2.horizontalLineToRelative(4.0f);
        pathBuilderR2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR2.lineTo(21.0f, 4.0f);
        pathBuilderR2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderR2.lineTo(5.0f, 2.0f);
        pathBuilderR2.curveToRelative(-1.11f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderR2.verticalLineToRelative(14.0f);
        pathBuilderR2.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        e.r(pathBuilderR2, 4.0f, 5.0f, 4.0f, 14.0f);
        d.D(pathBuilderR2, 14.0f, -4.83f, -0.59f, 0.59f);
        pathBuilderR2.lineTo(12.0f, 20.17f);
        pathBuilderR2.lineToRelative(-1.59f, -1.59f);
        pathBuilderR2.lineToRelative(-0.58f, -0.58f);
        b.y(pathBuilderR2, 5.0f, 18.0f, 5.0f, 4.0f);
        pathBuilderR2.moveTo(12.0f, 11.0f);
        pathBuilderR2.curveToRelative(1.65f, 0.0f, 3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilderR2.reflectiveCurveToRelative(-1.35f, -3.0f, -3.0f, -3.0f);
        pathBuilderR2.reflectiveCurveToRelative(-3.0f, 1.35f, -3.0f, 3.0f);
        pathBuilderR2.reflectiveCurveToRelative(1.35f, 3.0f, 3.0f, 3.0f);
        pathBuilderR2.close();
        pathBuilderR2.moveTo(12.0f, 7.0f);
        pathBuilderR2.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR2.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderR2.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR2.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR2.close();
        pathBuilderR2.moveTo(18.0f, 15.58f);
        pathBuilderR2.curveToRelative(0.0f, -2.5f, -3.97f, -3.58f, -6.0f, -3.58f);
        pathBuilderR2.reflectiveCurveToRelative(-6.0f, 1.08f, -6.0f, 3.58f);
        b.g(pathBuilderR2, 6.0f, 17.0f, 12.0f, -1.42f);
        pathBuilderR2.moveTo(8.48f, 15.0f);
        pathBuilderR2.curveToRelative(0.74f, -0.51f, 2.23f, -1.0f, 3.52f, -1.0f);
        pathBuilderR2.reflectiveCurveToRelative(2.78f, 0.49f, 3.52f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderR2, 8.48f, 15.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _personPin = imageVectorBuild;
        return imageVectorBuild;
    }
}
