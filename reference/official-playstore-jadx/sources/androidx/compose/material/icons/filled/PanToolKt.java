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
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panTool", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanTool", "Landroidx/compose/material/icons/Icons$Filled;", "getPanTool", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanToolKt {
    private static ImageVector _panTool;

    public static final ImageVector getPanTool(Icons.Filled filled) {
        ImageVector imageVector = _panTool;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PanTool", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(23.0f, 5.5f, 20.0f);
        pathBuilderA.curveToRelative(0.0f, 2.2f, -1.8f, 4.0f, -4.0f, 4.0f);
        pathBuilderA.horizontalLineToRelative(-7.3f);
        pathBuilderA.curveToRelative(-1.08f, 0.0f, -2.1f, -0.43f, -2.85f, -1.19f);
        pathBuilderA.lineTo(1.0f, 14.83f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 1.26f, -1.23f, 1.3f, -1.25f);
        pathBuilderA.curveToRelative(0.22f, -0.19f, 0.49f, -0.29f, 0.79f, -0.29f);
        pathBuilderA.curveToRelative(0.22f, 0.0f, 0.42f, 0.06f, 0.6f, 0.16f);
        pathBuilderA.curveTo(3.73f, 13.46f, 8.0f, 15.91f, 8.0f, 15.91f);
        pathBuilderA.verticalLineTo(4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(11.0f, 3.17f, 11.0f, 4.0f);
        f.t(pathBuilderA, 7.0f, 1.0f, 1.5f);
        pathBuilderA.curveTo(12.0f, 0.67f, 12.67f, 0.0f, 13.5f, 0.0f);
        pathBuilderA.reflectiveCurveTo(15.0f, 0.67f, 15.0f, 1.5f);
        c.i(pathBuilderA, 11.0f, 1.0f, 2.5f);
        pathBuilderA.curveTo(16.0f, 1.67f, 16.67f, 1.0f, 17.5f, 1.0f);
        pathBuilderA.reflectiveCurveTo(19.0f, 1.67f, 19.0f, 2.5f);
        c.i(pathBuilderA, 11.0f, 1.0f, 5.5f);
        pathBuilderA.curveTo(20.0f, 4.67f, 20.67f, 4.0f, 21.5f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 23.0f, 4.67f, 23.0f, 5.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _panTool = imageVectorBuild;
        return imageVectorBuild;
    }
}
