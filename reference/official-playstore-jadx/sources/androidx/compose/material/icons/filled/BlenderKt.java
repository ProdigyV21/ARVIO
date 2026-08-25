package androidx.compose.material.icons.filled;

import a0.b;
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
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_blender", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Blender", "Landroidx/compose/material/icons/Icons$Filled;", "getBlender", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BlenderKt {
    private static ImageVector _blender;

    public static final ImageVector getBlender(Icons.Filled filled) {
        ImageVector imageVector = _blender;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Blender", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(16.13f, 15.13f, 18.0f, 3.0f, -4.0f);
        e.x(pathBuilderT, 2.0f, -4.0f, 1.0f, 5.0f);
        pathBuilderT.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderT.verticalLineToRelative(4.0f);
        pathBuilderT.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderT.horizontalLineToRelative(2.23f);
        pathBuilderT.lineToRelative(0.64f, 4.13f);
        pathBuilderT.curveTo(6.74f, 16.05f, 6.0f, 17.43f, 6.0f, 19.0f);
        pathBuilderT.verticalLineToRelative(1.0f);
        pathBuilderT.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderT.horizontalLineToRelative(8.0f);
        pathBuilderT.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderT.verticalLineToRelative(-1.0f);
        pathBuilderT.curveTo(18.0f, 17.43f, 17.26f, 16.05f, 16.13f, 15.13f);
        b.t(pathBuilderT, 5.0f, 9.0f, 5.0f, 1.31f);
        f.v(pathBuilderT, 0.62f, 4.0f, 5.0f);
        pathBuilderT.moveTo(12.0f, 19.0f);
        pathBuilderT.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderT.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderT.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderT.reflectiveCurveTo(12.55f, 19.0f, 12.0f, 19.0f);
        a.r(pathBuilderT, 14.29f, 14.0f, 9.72f);
        pathBuilderT.lineTo(8.33f, 5.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.h(pathBuilderT, 7.34f, 14.29f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _blender = imageVectorBuild;
        return imageVectorBuild;
    }
}
