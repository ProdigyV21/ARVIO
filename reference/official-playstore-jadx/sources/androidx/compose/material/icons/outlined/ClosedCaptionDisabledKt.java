package androidx.compose.material.icons.outlined;

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
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_closedCaptionDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ClosedCaptionDisabled", "Landroidx/compose/material/icons/Icons$Outlined;", "getClosedCaptionDisabled", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ClosedCaptionDisabledKt {
    private static ImageVector _closedCaptionDisabled;

    public static final ImageVector getClosedCaptionDisabled(Icons.Outlined outlined) {
        ImageVector imageVector = _closedCaptionDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ClosedCaptionDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(13.0f, 10.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        b.z(pathBuilderA, 1.0f, -1.5f, -0.5f, -2.0f);
        a.n(pathBuilderA, 1.0f, 13.0f, 10.0f);
        pathBuilderA.moveTo(16.5f, 13.5f);
        pathBuilderA.lineToRelative(1.21f, 1.21f);
        pathBuilderA.curveTo(17.89f, 14.52f, 18.0f, 14.27f, 18.0f, 14.0f);
        v.a.t(pathBuilderA, -1.0f, -1.5f, 13.5f);
        v.a.A(pathBuilderA, 8.83f, 6.0f, 19.0f, 10.17f);
        pathBuilderA.lineToRelative(1.98f, 1.98f);
        pathBuilderA.curveToRelative(0.0f, -0.05f, 0.02f, -0.1f, 0.02f, -0.16f);
        pathBuilderA.verticalLineTo(6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        w.a.w(pathBuilderA, 6.83f, 8.83f, 6.0f);
        pathBuilderA.moveTo(19.78f, 22.61f);
        pathBuilderA.lineTo(17.17f, 20.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveToRelative(-1.11f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.verticalLineTo(6.0f);
        pathBuilderA.curveToRelative(0.0f, -0.05f, 0.02f, -0.1f, 0.02f, -0.15f);
        pathBuilderA.lineTo(1.39f, 4.22f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        d.C(pathBuilderA, 18.38f, 18.38f, 19.78f, 22.61f);
        e.m(pathBuilderA, 7.5f, 13.5f, 2.0f, 13.0f);
        a0.a.n(pathBuilderA, 0.67f, -2.5f, -2.5f, 7.5f);
        f.r(pathBuilderA, 13.5f, 15.17f, 18.0f);
        pathBuilderA.lineTo(11.0f, 13.83f);
        pathBuilderA.verticalLineTo(14.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.horizontalLineTo(7.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.32f, 0.16f, -0.59f, 0.4f, -0.78f);
        pathBuilderA.lineTo(5.0f, 7.83f);
        pathBuilderA.verticalLineTo(18.0f);
        pathBuilderA.horizontalLineTo(15.17f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _closedCaptionDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
