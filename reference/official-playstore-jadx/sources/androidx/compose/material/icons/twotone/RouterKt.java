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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_router", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Router", "Landroidx/compose/material/icons/Icons$TwoTone;", "getRouter", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RouterKt {
    private static ImageVector _router;

    public static final ImageVector getRouter(Icons.TwoTone twoTone) {
        ImageVector imageVector = _router;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Router", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(15.0f, 15.0f, 5.0f, 15.0f, 4.0f);
        d.x(pathBuilderS, 14.0f, -4.0f, -4.0f);
        pathBuilderS.moveTo(8.0f, 18.0f);
        a.h(pathBuilderS, 6.0f, 18.0f, -2.0f, 2.0f);
        c.r(pathBuilderS, 2.0f, 11.5f, 18.0f, -2.0f);
        b.l(pathBuilderS, -2.0f, 2.0f, 2.0f);
        a.C(pathBuilderS, 15.0f, 18.0f, -2.0f, -2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.s(pathBuilderS, 2.0f, 2.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.0f, 4.2f);
        pathBuilderA.curveToRelative(1.5f, 0.0f, 3.0f, 0.6f, 4.2f, 1.7f);
        pathBuilderA.lineToRelative(0.8f, -0.8f);
        pathBuilderA.curveTo(19.6f, 3.7f, 17.8f, 3.0f, 16.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.6f, 0.7f, -5.0f, 2.1f);
        pathBuilderA.lineToRelative(0.8f, 0.8f);
        pathBuilderA.curveTo(13.0f, 4.8f, 14.5f, 4.2f, 16.0f, 4.2f);
        a.z(pathBuilderA, 12.7f, 6.7f, 0.8f, 0.8f);
        pathBuilderA.curveToRelative(0.7f, -0.7f, 1.6f, -1.0f, 2.5f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.8f, 0.3f, 2.5f, 1.0f);
        pathBuilderA.lineToRelative(0.8f, -0.8f);
        pathBuilderA.curveToRelative(-0.9f, -0.9f, -2.1f, -1.4f, -3.3f, -1.4f);
        pathBuilderA.reflectiveCurveToRelative(-2.4f, 0.5f, -3.3f, 1.4f);
        a.g(pathBuilderA, 19.0f, 13.0f, -2.0f);
        b.A(pathBuilderA, 17.0f, 9.0f, -2.0f, 4.0f);
        pathBuilderA.lineTo(5.0f, 13.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderA, 19.0f, 19.0f, 5.0f, 19.0f);
        b.l(pathBuilderA, -4.0f, 14.0f, 4.0f);
        a.C(pathBuilderA, 6.0f, 16.0f, 2.0f, 2.0f);
        c.z(pathBuilderA, 6.0f, 18.0f, 9.5f, 16.0f);
        d.x(pathBuilderA, 2.0f, 2.0f, -2.0f);
        a.C(pathBuilderA, 13.0f, 16.0f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderA, -2.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _router = imageVectorBuild;
        return imageVectorBuild;
    }
}
