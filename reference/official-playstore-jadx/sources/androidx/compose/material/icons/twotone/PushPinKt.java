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
import v.b;
import v.c;
import v.d;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pushPin", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PushPin", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPushPin", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PushPinKt {
    private static ImageVector _pushPin;

    public static final ImageVector getPushPin(Icons.TwoTone twoTone) {
        ImageVector imageVector = _pushPin;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PushPin", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(14.0f, 4.0f, -4.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.35f, 2.14f, -1.0f, 3.0f);
        pathBuilderA.horizontalLineToRelative(6.0f);
        pathBuilderA.curveToRelative(-0.63f, -0.84f, -1.0f, -1.88f, -1.0f, -3.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 4.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(19.0f, 12.0f);
        pathBuilderA2.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderA2.verticalLineTo(4.0f);
        pathBuilderA2.lineToRelative(1.0f, 0.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilderA2.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA2.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA2.horizontalLineTo(7.0f);
        pathBuilderA2.curveTo(6.45f, 2.0f, 6.0f, 2.45f, 6.0f, 3.0f);
        pathBuilderA2.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilderA2.lineToRelative(1.0f, 0.0f);
        pathBuilderA2.verticalLineToRelative(5.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.66f, -1.34f, 3.0f, -3.0f, 3.0f);
        e.l(pathBuilderA2, 2.0f, 5.97f, 7.0f);
        pathBuilderA2.lineToRelative(1.0f, 1.0f);
        d.v(pathBuilderA2, 1.0f, -1.0f, -7.0f, 19.0f);
        pathBuilderA2.lineTo(19.0f, 12.0f);
        pathBuilderA2.curveTo(19.0f, 12.0f, 19.0f, 12.0f, 19.0f, 12.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(9.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.65f, -0.86f, 1.0f, -1.9f, 1.0f, -3.0f);
        a.j(pathBuilderA2, 4.0f, 4.0f, 5.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.12f, 0.37f, 2.16f, 1.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.l(pathBuilderA2, 9.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pushPin = imageVectorBuild;
        return imageVectorBuild;
    }
}
