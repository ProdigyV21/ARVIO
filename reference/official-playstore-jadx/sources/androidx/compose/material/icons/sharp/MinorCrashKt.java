package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_minorCrash", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MinorCrash", "Landroidx/compose/material/icons/Icons$Sharp;", "getMinorCrash", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MinorCrashKt {
    private static ImageVector _minorCrash;

    public static final ImageVector getMinorCrash(Icons.Sharp sharp) {
        ImageVector imageVector = _minorCrash;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.MinorCrash", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderP = a.p(18.57f, 8.0f, 5.43f, 3.0f, 15.0f);
        b.z(pathBuilderP, 9.0f, 3.0f, -2.0f, 12.0f);
        e.l(pathBuilderP, 2.0f, 3.0f, -9.0f);
        c.z(pathBuilderP, 18.57f, 8.0f, 6.85f, 10.0f);
        a0.a.n(pathBuilderP, 10.29f, 1.04f, 3.0f, 5.81f);
        c.z(pathBuilderP, 6.85f, 10.0f, 6.0f, 17.5f);
        pathBuilderP.curveTo(6.0f, 16.67f, 6.67f, 16.0f, 7.5f, 16.0f);
        pathBuilderP.reflectiveCurveTo(9.0f, 16.67f, 9.0f, 17.5f);
        pathBuilderP.reflectiveCurveTo(8.33f, 19.0f, 7.5f, 19.0f);
        pathBuilderP.reflectiveCurveTo(6.0f, 18.33f, 6.0f, 17.5f);
        pathBuilderP.close();
        pathBuilderP.moveTo(15.0f, 17.5f);
        pathBuilderP.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderP.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderP.reflectiveCurveTo(17.33f, 19.0f, 16.5f, 19.0f);
        pathBuilderP.reflectiveCurveTo(15.0f, 18.33f, 15.0f, 17.5f);
        v.a.p(pathBuilderP, 9.41f, 5.0f, 8.0f, 6.41f);
        pathBuilderP.lineToRelative(-3.0f, -3.0f);
        b.y(pathBuilderP, 6.41f, 2.0f, 9.41f, 5.0f);
        pathBuilderP.moveTo(16.0f, 6.41f);
        pathBuilderP.lineTo(14.59f, 5.0f);
        pathBuilderP.lineToRelative(3.0f, -3.0f);
        b.y(pathBuilderP, 19.0f, 3.41f, 16.0f, 6.41f);
        e.m(pathBuilderP, 13.0f, 5.0f, -2.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderP, 2.0f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _minorCrash = imageVectorBuild;
        return imageVectorBuild;
    }
}
