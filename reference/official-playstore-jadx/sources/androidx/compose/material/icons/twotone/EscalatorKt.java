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
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_escalator", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Escalator", "Landroidx/compose/material/icons/Icons$TwoTone;", "getEscalator", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EscalatorKt {
    private static ImageVector _escalator;

    public static final ImageVector getEscalator(Icons.TwoTone twoTone) {
        ImageVector imageVector = _escalator;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Escalator", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.0f, 5.0f, 19.0f, 5.0f);
        w.a.p(pathBuilderJ, 0.0f, 14.0f, 5.0f, 5.0f);
        pathBuilderJ.horizontalLineTo(19.0f);
        pathBuilderJ.moveTo(17.0f, 6.0f);
        pathBuilderJ.horizontalLineToRelative(-3.3f);
        pathBuilderJ.lineToRelative(-5.0f, 9.0f);
        pathBuilderJ.horizontalLineTo(7.0f);
        pathBuilderJ.curveToRelative(-0.83f, 0.0f, -1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderJ.reflectiveCurveTo(6.17f, 18.0f, 7.0f, 18.0f);
        a0.a.n(pathBuilderJ, 3.3f, 5.0f, -9.0f, 17.0f);
        pathBuilderJ.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderJ, 17.83f, 6.0f, 17.0f, 6.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ2 = a.j(19.0f, 5.0f, 19.0f, 5.0f);
        w.a.p(pathBuilderJ2, 0.0f, 14.0f, 5.0f, 5.0f);
        f.s(pathBuilderJ2, 19.0f, 19.0f, 3.0f, 5.0f);
        pathBuilderJ2.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderJ2.verticalLineToRelative(14.0f);
        pathBuilderJ2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ2.horizontalLineToRelative(14.0f);
        pathBuilderJ2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ2.lineToRelative(0.0f, -14.0f);
        pathBuilderJ2.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        c.z(pathBuilderJ2, 19.0f, 3.0f, 17.0f, 6.0f);
        a0.a.n(pathBuilderJ2, -3.3f, -5.0f, 9.0f, 7.0f);
        pathBuilderJ2.curveToRelative(-0.83f, 0.0f, -1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderJ2.reflectiveCurveTo(6.17f, 18.0f, 7.0f, 18.0f);
        a0.a.n(pathBuilderJ2, 3.3f, 5.0f, -9.0f, 17.0f);
        pathBuilderJ2.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderJ2, 17.83f, 6.0f, 17.0f, 6.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _escalator = imageVectorBuild;
        return imageVectorBuild;
    }
}
