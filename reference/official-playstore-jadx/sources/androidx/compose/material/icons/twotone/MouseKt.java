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
import v.c;
import v.d;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_mouse", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Mouse", "Landroidx/compose/material/icons/Icons$TwoTone;", "getMouse", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MouseKt {
    private static ImageVector _mouse;

    public static final ImageVector getMouse(Icons.TwoTone twoTone) {
        ImageVector imageVector = _mouse;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Mouse", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(13.0f, 3.16f, 13.0f, 9.0f, 5.0f);
        pathBuilderT.curveToRelative(-0.04f, -2.94f, -2.19f, -5.37f, -5.0f, -5.84f);
        pathBuilderT.close();
        pathBuilderT.moveTo(11.0f, 3.16f);
        pathBuilderT.curveTo(8.19f, 3.63f, 6.04f, 6.06f, 6.0f, 9.0f);
        d.q(pathBuilderT, 5.0f, 11.0f, 3.16f);
        pathBuilderT.moveTo(11.0f, 11.0f);
        pathBuilderT.lineTo(6.0f, 11.0f);
        pathBuilderT.verticalLineToRelative(4.0f);
        pathBuilderT.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderT.reflectiveCurveToRelative(6.0f, -2.69f, 6.0f, -6.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.c(pathBuilderT, -4.0f, -7.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.0f, 9.0f);
        pathBuilderA.curveToRelative(-0.04f, -4.39f, -3.6f, -7.93f, -8.0f, -7.93f);
        pathBuilderA.reflectiveCurveTo(4.04f, 4.61f, 4.0f, 9.0f);
        pathBuilderA.verticalLineToRelative(6.0f);
        pathBuilderA.curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f);
        c.z(pathBuilderA, 20.0f, 9.0f, 13.0f, 3.16f);
        pathBuilderA.curveToRelative(2.81f, 0.47f, 4.96f, 2.9f, 5.0f, 5.84f);
        d.q(pathBuilderA, -5.0f, 13.0f, 3.16f);
        pathBuilderA.moveTo(11.0f, 3.16f);
        pathBuilderA.lineTo(11.0f, 9.0f);
        pathBuilderA.lineTo(6.0f, 9.0f);
        pathBuilderA.curveToRelative(0.04f, -2.94f, 2.19f, -5.37f, 5.0f, -5.84f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.0f, 15.0f);
        pathBuilderA.curveToRelative(0.0f, 3.31f, -2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(-6.0f, -2.69f, -6.0f, -6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.t(pathBuilderA, -4.0f, 12.0f, 4.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _mouse = imageVectorBuild;
        return imageVectorBuild;
    }
}
