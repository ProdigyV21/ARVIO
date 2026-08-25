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
import v.a;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localFireDepartment", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalFireDepartment", "Landroidx/compose/material/icons/Icons$Sharp;", "getLocalFireDepartment", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalFireDepartmentKt {
    private static ImageVector _localFireDepartment;

    public static final ImageVector getLocalFireDepartment(Icons.Sharp sharp) {
        ImageVector imageVector = _localFireDepartment;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.LocalFireDepartment", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(12.0f, 12.9f, -2.13f, 2.09f);
        pathBuilderR.curveTo(9.31f, 15.55f, 9.0f, 16.28f, 9.0f, 17.06f);
        pathBuilderR.curveTo(9.0f, 18.68f, 10.35f, 20.0f, 12.0f, 20.0f);
        pathBuilderR.reflectiveCurveToRelative(3.0f, -1.32f, 3.0f, -2.94f);
        pathBuilderR.curveToRelative(0.0f, -0.78f, -0.31f, -1.52f, -0.87f, -2.07f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderR, 12.0f, 12.9f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(16.0f, 6.0f, -0.44f, 0.55f);
        pathBuilderR2.curveTo(14.38f, 8.02f, 12.0f, 7.19f, 12.0f, 5.3f);
        pathBuilderR2.verticalLineTo(2.0f);
        pathBuilderR2.curveToRelative(0.0f, 0.0f, -8.0f, 4.0f, -8.0f, 11.0f);
        pathBuilderR2.curveToRelative(0.0f, 2.92f, 1.56f, 5.47f, 3.89f, 6.86f);
        pathBuilderR2.curveTo(7.33f, 19.07f, 7.0f, 18.1f, 7.0f, 17.06f);
        pathBuilderR2.curveToRelative(0.0f, -1.32f, 0.52f, -2.56f, 1.47f, -3.5f);
        pathBuilderR2.lineTo(12.0f, 10.1f);
        pathBuilderR2.lineToRelative(3.53f, 3.47f);
        pathBuilderR2.curveToRelative(0.95f, 0.93f, 1.47f, 2.17f, 1.47f, 3.5f);
        pathBuilderR2.curveToRelative(0.0f, 1.02f, -0.31f, 1.96f, -0.85f, 2.75f);
        pathBuilderR2.curveToRelative(1.89f, -1.15f, 3.29f, -3.06f, 3.71f, -5.3f);
        pathBuilderR2.curveTo(20.52f, 10.97f, 18.79f, 7.62f, 16.0f, 6.0f);
        pathBuilderR2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localFireDepartment = imageVectorBuild;
        return imageVectorBuild;
    }
}
