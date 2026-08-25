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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localFlorist", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalFlorist", "Landroidx/compose/material/icons/Icons$Filled;", "getLocalFlorist", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalFloristKt {
    private static ImageVector _localFlorist;

    public static final ImageVector getLocalFlorist(Icons.Filled filled) {
        ImageVector imageVector = _localFlorist;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.LocalFlorist", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 22.0f);
        pathBuilderA.curveToRelative(4.97f, 0.0f, 9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderA.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.6f, 10.25f);
        pathBuilderA.curveToRelative(0.0f, 1.38f, 1.12f, 2.5f, 2.5f, 2.5f);
        pathBuilderA.curveToRelative(0.53f, 0.0f, 1.01f, -0.16f, 1.42f, -0.44f);
        pathBuilderA.lineToRelative(-0.02f, 0.19f);
        pathBuilderA.curveToRelative(0.0f, 1.38f, 1.12f, 2.5f, 2.5f, 2.5f);
        pathBuilderA.reflectiveCurveToRelative(2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilderA.lineToRelative(-0.02f, -0.19f);
        pathBuilderA.curveToRelative(0.4f, 0.28f, 0.89f, 0.44f, 1.42f, 0.44f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilderA.curveToRelative(0.0f, -1.0f, -0.59f, -1.85f, -1.43f, -2.25f);
        pathBuilderA.curveToRelative(0.84f, -0.4f, 1.43f, -1.25f, 1.43f, -2.25f);
        pathBuilderA.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilderA.curveToRelative(-0.53f, 0.0f, -1.01f, 0.16f, -1.42f, 0.44f);
        pathBuilderA.lineToRelative(0.02f, -0.19f);
        pathBuilderA.curveTo(14.5f, 2.12f, 13.38f, 1.0f, 12.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(9.5f, 2.12f, 9.5f, 3.5f);
        pathBuilderA.lineToRelative(0.02f, 0.19f);
        pathBuilderA.curveToRelative(-0.4f, -0.28f, -0.89f, -0.44f, -1.42f, -0.44f);
        pathBuilderA.curveToRelative(-1.38f, 0.0f, -2.5f, 1.12f, -2.5f, 2.5f);
        pathBuilderA.curveToRelative(0.0f, 1.0f, 0.59f, 1.85f, 1.43f, 2.25f);
        pathBuilderA.curveToRelative(-0.84f, 0.4f, -1.43f, 1.25f, -1.43f, 2.25f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 5.5f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderA.reflectiveCurveToRelative(-1.12f, 2.5f, -2.5f, 2.5f);
        pathBuilderA.reflectiveCurveTo(9.5f, 9.38f, 9.5f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(3.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 4.97f, 4.03f, 9.0f, 9.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -4.97f, -4.03f, -9.0f, -9.0f, -9.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localFlorist = imageVectorBuild;
        return imageVectorBuild;
    }
}
