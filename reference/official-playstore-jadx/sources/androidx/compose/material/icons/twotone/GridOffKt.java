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
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_gridOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GridOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getGridOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GridOffKt {
    private static ImageVector _gridOff;

    public static final ImageVector getGridOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _gridOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.GridOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(20.0f, 14.0f, -4.0f, -4.0f, 2.89f);
        c.z(pathBuilderU, 17.11f, 14.0f, 10.0f, 12.55f);
        f.n(pathBuilderU, 10.0f, 14.0f, 1.45f);
        pathBuilderU.moveTo(14.0f, 10.0f);
        c.v(pathBuilderU, -0.89f, 0.89f, 0.89f);
        pathBuilderU.moveTo(19.11f, 16.0f);
        d.C(pathBuilderU, 0.89f, 0.89f, 20.0f, 16.0f);
        pathBuilderU.moveTo(8.0f, 4.0f);
        c.v(pathBuilderU, -0.89f, 0.89f, 0.89f);
        pathBuilderU.moveTo(14.0f, 8.0f);
        b.A(pathBuilderU, 14.0f, 4.0f, -4.0f, 2.89f);
        c.z(pathBuilderU, 11.11f, 8.0f, 16.0f, 4.0f);
        d.x(pathBuilderU, 4.0f, 4.0f, -4.0f);
        c.m(pathBuilderU, 10.0f, 16.0f, 4.0f, 4.0f);
        c.t(pathBuilderU, -3.45f, -0.55f, -0.55f);
        c.m(pathBuilderU, 4.0f, 10.0f, 4.0f, 4.0f);
        a.n(pathBuilderU, -3.45f, 7.45f, 10.0f);
        pathBuilderU.moveTo(16.0f, 20.0f);
        d.q(pathBuilderU, 1.45f, 16.0f, 18.55f);
        v.a.C(pathBuilderU, 4.0f, 16.0f, 4.0f, 4.0f);
        c.z(pathBuilderU, 4.0f, 20.0f, 4.0f, 6.55f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.d(pathBuilderU, 4.0f, 8.0f, 1.45f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = w.a.g(8.0f, 4.0f, 0.89f, 2.0f, 2.0f);
        b.A(pathBuilderG, 10.0f, 4.0f, 4.0f, 4.0f);
        pathBuilderG.horizontalLineToRelative(-2.89f);
        pathBuilderG.lineToRelative(2.0f, 2.0f);
        pathBuilderG.lineTo(14.0f, 10.0f);
        pathBuilderG.verticalLineToRelative(0.89f);
        pathBuilderG.lineToRelative(2.0f, 2.0f);
        b.A(pathBuilderG, 16.0f, 10.0f, 4.0f, 4.0f);
        pathBuilderG.horizontalLineToRelative(-2.89f);
        pathBuilderG.lineToRelative(2.0f, 2.0f);
        pathBuilderG.lineTo(20.0f, 16.0f);
        pathBuilderG.verticalLineToRelative(0.89f);
        pathBuilderG.lineToRelative(2.0f, 2.0f);
        pathBuilderG.lineTo(22.0f, 4.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderG.lineTo(5.11f, 2.0f);
        d.C(pathBuilderG, 2.0f, 2.0f, 8.0f, 4.0f);
        v.a.C(pathBuilderG, 16.0f, 4.0f, 4.0f, 4.0f);
        d.q(pathBuilderG, -4.0f, 16.0f, 4.0f);
        pathBuilderG.moveTo(1.41f, 1.14f);
        pathBuilderG.lineTo(0.0f, 2.55f);
        pathBuilderG.lineToRelative(2.0f, 2.0f);
        pathBuilderG.lineTo(2.0f, 20.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(15.45f);
        pathBuilderG.lineToRelative(2.01f, 2.01f);
        d.C(pathBuilderG, 1.41f, -1.41f, 1.41f, 1.14f);
        pathBuilderG.moveTo(10.0f, 12.55f);
        pathBuilderG.lineTo(11.45f, 14.0f);
        d.f(pathBuilderG, 10.0f, 14.0f, -1.45f);
        pathBuilderG.moveTo(4.0f, 6.55f);
        pathBuilderG.lineTo(5.45f, 8.0f);
        b.y(pathBuilderG, 4.0f, 8.0f, 4.0f, 6.55f);
        pathBuilderG.moveTo(8.0f, 20.0f);
        v.a.h(pathBuilderG, 4.0f, 20.0f, -4.0f, 4.0f);
        b.f(pathBuilderG, 4.0f, 8.0f, 14.0f);
        v.a.h(pathBuilderG, 4.0f, 14.0f, -4.0f, 3.45f);
        d.C(pathBuilderG, 0.55f, 0.55f, 8.0f, 14.0f);
        v.a.C(pathBuilderG, 14.0f, 20.0f, -4.0f, -4.0f);
        pathBuilderG.horizontalLineToRelative(3.45f);
        d.C(pathBuilderG, 0.55f, 0.55f, 14.0f, 20.0f);
        pathBuilderG.moveTo(16.0f, 20.0f);
        pathBuilderG.verticalLineToRelative(-1.45f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderG, 17.45f, 20.0f, 16.0f, 20.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _gridOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
