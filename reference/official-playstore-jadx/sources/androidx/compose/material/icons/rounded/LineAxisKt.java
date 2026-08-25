package androidx.compose.material.icons.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_lineAxis", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LineAxis", "Landroidx/compose/material/icons/Icons$Rounded;", "getLineAxis", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LineAxisKt {
    private static ImageVector _lineAxis;

    public static final ImageVector getLineAxis(Icons.Rounded rounded) {
        ImageVector imageVector = _lineAxis;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LineAxis", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.34f, 6.77f, 21.34f, 6.77f);
        pathBuilderJ.curveToRelative(-0.4f, -0.4f, -1.07f, -0.39f, -1.45f, 0.04f);
        pathBuilderJ.lineToRelative(-3.33f, 3.74f);
        pathBuilderJ.lineToRelative(-5.65f, -5.24f);
        pathBuilderJ.curveTo(10.12f, 4.58f, 8.9f, 4.6f, 8.14f, 5.36f);
        pathBuilderJ.lineTo(2.7f, 10.81f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.lineToRelative(0.09f, 0.09f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineToRelative(5.44f, -5.45f);
        pathBuilderJ.lineToRelative(5.59f, 5.19f);
        pathBuilderJ.lineToRelative(-1.73f, 1.95f);
        pathBuilderJ.lineToRelative(-2.58f, -2.58f);
        pathBuilderJ.curveToRelative(-0.78f, -0.78f, -2.05f, -0.78f, -2.83f, 0.0f);
        pathBuilderJ.lineTo(2.7f, 16.8f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.lineTo(2.8f, 18.3f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineToRelative(5.3f, -5.3f);
        pathBuilderJ.lineToRelative(2.5f, 2.5f);
        pathBuilderJ.curveToRelative(0.81f, 0.81f, 2.14f, 0.77f, 2.91f, -0.09f);
        pathBuilderJ.lineToRelative(1.78f, -2.01f);
        pathBuilderJ.lineToRelative(3.19f, 2.96f);
        pathBuilderJ.curveToRelative(0.39f, 0.36f, 1.0f, 0.35f, 1.38f, -0.03f);
        pathBuilderJ.lineToRelative(0.01f, -0.01f);
        pathBuilderJ.curveToRelative(0.4f, -0.4f, 0.39f, -1.05f, -0.03f, -1.43f);
        pathBuilderJ.lineToRelative(-3.22f, -2.99f);
        pathBuilderJ.lineToRelative(3.35f, -3.77f);
        pathBuilderJ.curveTo(21.73f, 7.74f, 21.71f, 7.14f, 21.34f, 6.77f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _lineAxis = imageVectorBuild;
        return imageVectorBuild;
    }
}
