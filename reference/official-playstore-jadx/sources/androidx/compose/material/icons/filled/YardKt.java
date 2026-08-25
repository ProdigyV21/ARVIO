package androidx.compose.material.icons.filled;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_yard", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Yard", "Landroidx/compose/material/icons/Icons$Filled;", "getYard", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class YardKt {
    private static ImageVector _yard;

    public static final ImageVector getYard(Icons.Filled filled) {
        ImageVector imageVector = _yard;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Yard", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 2.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 2.0f, 2.0f, 2.9f, 2.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(4.0f);
        pathBuilderQ.curveTo(22.0f, 2.9f, 21.1f, 2.0f, 20.0f, 2.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(8.0f, 8.22f);
        pathBuilderQ.curveToRelative(0.0f, -0.86f, 0.7f, -1.56f, 1.56f, -1.56f);
        pathBuilderQ.curveToRelative(0.33f, 0.0f, 0.64f, 0.1f, 0.89f, 0.28f);
        pathBuilderQ.lineToRelative(-0.01f, -0.12f);
        pathBuilderQ.curveToRelative(0.0f, -0.86f, 0.7f, -1.56f, 1.56f, -1.56f);
        pathBuilderQ.reflectiveCurveToRelative(1.56f, 0.7f, 1.56f, 1.56f);
        pathBuilderQ.lineToRelative(-0.01f, 0.12f);
        pathBuilderQ.curveToRelative(0.26f, -0.18f, 0.56f, -0.28f, 0.89f, -0.28f);
        pathBuilderQ.curveToRelative(0.86f, 0.0f, 1.56f, 0.7f, 1.56f, 1.56f);
        pathBuilderQ.curveToRelative(0.0f, 0.62f, -0.37f, 1.16f, -0.89f, 1.4f);
        pathBuilderQ.curveTo(15.63f, 9.87f, 16.0f, 10.41f, 16.0f, 11.03f);
        pathBuilderQ.curveToRelative(0.0f, 0.86f, -0.7f, 1.56f, -1.56f, 1.56f);
        pathBuilderQ.curveToRelative(-0.33f, 0.0f, -0.64f, -0.11f, -0.89f, -0.28f);
        pathBuilderQ.lineToRelative(0.01f, 0.12f);
        pathBuilderQ.curveToRelative(0.0f, 0.86f, -0.7f, 1.56f, -1.56f, 1.56f);
        pathBuilderQ.reflectiveCurveToRelative(-1.56f, -0.7f, -1.56f, -1.56f);
        pathBuilderQ.lineToRelative(0.01f, -0.12f);
        pathBuilderQ.curveToRelative(-0.26f, 0.18f, -0.56f, 0.28f, -0.89f, 0.28f);
        pathBuilderQ.curveTo(8.7f, 12.59f, 8.0f, 11.89f, 8.0f, 11.03f);
        pathBuilderQ.curveToRelative(0.0f, -0.62f, 0.37f, -1.16f, 0.89f, -1.4f);
        pathBuilderQ.curveTo(8.37f, 9.38f, 8.0f, 8.84f, 8.0f, 8.22f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(12.0f, 19.0f);
        pathBuilderQ.curveToRelative(-3.31f, 0.0f, -6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderQ.curveTo(9.31f, 13.0f, 12.0f, 15.69f, 12.0f, 19.0f);
        pathBuilderQ.curveToRelative(0.0f, -3.31f, 2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilderQ.curveTo(18.0f, 16.31f, 15.31f, 19.0f, 12.0f, 19.0f);
        pathBuilderQ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(12.0f, 9.62f, -1.56f, 0.0f);
        pathBuilderB.arcToRelative(1.56f, 1.56f, 0.0f, true, true, 3.12f, 0.0f);
        pathBuilderB.arcToRelative(1.56f, 1.56f, 0.0f, true, true, -3.12f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _yard = imageVectorBuild;
        return imageVectorBuild;
    }
}
