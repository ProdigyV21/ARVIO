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
import v.a;
import v.b;
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_microwave", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Microwave", "Landroidx/compose/material/icons/Icons$Outlined;", "getMicrowave", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MicrowaveKt {
    private static ImageVector _microwave;

    public static final ImageVector getMicrowave(Icons.Outlined outlined) {
        ImageVector imageVector = _microwave;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Microwave", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 4.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 4.0f, 2.0f, 4.9f, 2.0f, 6.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveTo(22.0f, 4.9f, 21.1f, 4.0f, 20.0f, 4.0f);
        b.w(pathBuilderQ, 4.0f, 6.0f, 10.0f, 12.0f);
        c.l(pathBuilderQ, 4.0f, 6.0f, 20.0f, 18.0f);
        f.A(pathBuilderQ, -4.0f, 6.0f, 4.0f, 18.0f);
        e.m(pathBuilderQ, 19.0f, 9.0f, -2.0f, 7.0f);
        c.D(pathBuilderQ, 2.0f, 9.0f, 18.0f, 13.0f);
        pathBuilderQ.lineTo(18.0f, 13.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveTo(19.0f, 12.55f, 18.55f, 13.0f, 18.0f, 13.0f);
        a.p(pathBuilderQ, 18.0f, 17.0f, 18.0f, 17.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveTo(19.0f, 16.55f, 18.55f, 17.0f, 18.0f, 17.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(10.25f, 16.0f);
        pathBuilderQ.curveToRelative(-0.79f, 0.0f, -1.37f, -0.38f, -1.79f, -0.66f);
        pathBuilderQ.curveTo(8.13f, 15.12f, 7.94f, 15.0f, 7.75f, 15.0f);
        pathBuilderQ.curveToRelative(-0.37f, 0.0f, -0.8f, 0.41f, -0.95f, 0.61f);
        pathBuilderQ.lineToRelative(-1.42f, -1.42f);
        pathBuilderQ.curveTo(5.73f, 13.79f, 6.59f, 13.0f, 7.75f, 13.0f);
        pathBuilderQ.curveToRelative(0.8f, 0.0f, 1.39f, 0.39f, 1.81f, 0.67f);
        pathBuilderQ.curveTo(9.87f, 13.88f, 10.07f, 14.0f, 10.25f, 14.0f);
        pathBuilderQ.curveToRelative(0.37f, 0.0f, 0.8f, -0.41f, 0.95f, -0.61f);
        pathBuilderQ.lineToRelative(1.42f, 1.42f);
        pathBuilderQ.curveTo(12.26f, 15.21f, 11.41f, 16.0f, 10.25f, 16.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(10.25f, 11.0f);
        pathBuilderQ.curveToRelative(-0.79f, 0.0f, -1.37f, -0.38f, -1.79f, -0.66f);
        pathBuilderQ.curveTo(8.13f, 10.12f, 7.94f, 10.0f, 7.75f, 10.0f);
        pathBuilderQ.curveToRelative(-0.37f, 0.0f, -0.8f, 0.41f, -0.95f, 0.61f);
        pathBuilderQ.lineTo(5.37f, 9.19f);
        pathBuilderQ.curveTo(5.73f, 8.79f, 6.59f, 8.0f, 7.75f, 8.0f);
        pathBuilderQ.curveToRelative(0.8f, 0.0f, 1.39f, 0.39f, 1.81f, 0.67f);
        pathBuilderQ.curveTo(9.87f, 8.88f, 10.07f, 9.0f, 10.25f, 9.0f);
        pathBuilderQ.curveToRelative(0.37f, 0.0f, 0.8f, -0.41f, 0.95f, -0.61f);
        pathBuilderQ.lineToRelative(1.42f, 1.42f);
        pathBuilderQ.curveTo(12.26f, 10.21f, 11.41f, 11.0f, 10.25f, 11.0f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _microwave = imageVectorBuild;
        return imageVectorBuild;
    }
}
