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
import v.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noCrash", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoCrash", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNoCrash", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoCrashKt {
    private static ImageVector _noCrash;

    public static final ImageVector getNoCrash(Icons.TwoTone twoTone) {
        ImageVector imageVector = _noCrash;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.NoCrash", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(5.0f, 15.0f, 5.0f, 14.0f, -5.0f);
        v.a.x(pathBuilderU, 5.0f, 7.5f, 19.0f);
        pathBuilderU.curveTo(6.67f, 19.0f, 6.0f, 18.33f, 6.0f, 17.5f);
        pathBuilderU.reflectiveCurveTo(6.67f, 16.0f, 7.5f, 16.0f);
        pathBuilderU.reflectiveCurveTo(9.0f, 16.67f, 9.0f, 17.5f);
        pathBuilderU.reflectiveCurveTo(8.33f, 19.0f, 7.5f, 19.0f);
        pathBuilderU.close();
        pathBuilderU.moveTo(16.5f, 19.0f);
        pathBuilderU.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderU.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderU.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderU, 17.33f, 19.0f, 16.5f, 19.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.92f, 9.01f);
        pathBuilderA.curveTo(18.72f, 8.42f, 18.16f, 8.0f, 17.5f, 8.0f);
        pathBuilderA.horizontalLineToRelative(-11.0f);
        pathBuilderA.curveTo(5.84f, 8.0f, 5.29f, 8.42f, 5.08f, 9.01f);
        pathBuilderA.lineTo(3.0f, 15.0f);
        pathBuilderA.verticalLineToRelative(8.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        e.l(pathBuilderA, -1.0f, 12.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        a.n(pathBuilderA, -8.0f, 18.92f, 9.01f);
        pathBuilderA.moveTo(6.85f, 10.0f);
        a0.a.n(pathBuilderA, 10.29f, 1.04f, 3.0f, 5.81f);
        c.z(pathBuilderA, 6.85f, 10.0f, 19.0f, 20.0f);
        c.k(pathBuilderA, 5.0f, -5.0f, 14.0f, 20.0f);
        pathBuilderA.moveTo(6.0f, 17.5f);
        pathBuilderA.curveTo(6.0f, 16.67f, 6.67f, 16.0f, 7.5f, 16.0f);
        pathBuilderA.reflectiveCurveTo(9.0f, 16.67f, 9.0f, 17.5f);
        pathBuilderA.reflectiveCurveTo(8.33f, 19.0f, 7.5f, 19.0f);
        pathBuilderA.reflectiveCurveTo(6.0f, 18.33f, 6.0f, 17.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 17.5f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(17.33f, 19.0f, 16.5f, 19.0f);
        pathBuilderA.reflectiveCurveTo(15.0f, 18.33f, 15.0f, 17.5f);
        v.a.p(pathBuilderA, 12.0f, 6.36f, 9.17f, 3.54f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.lineTo(12.0f, 3.54f);
        pathBuilderA.lineTo(15.54f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 1.41f, 1.41f, 12.0f, 6.36f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noCrash = imageVectorBuild;
        return imageVectorBuild;
    }
}
