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
import v.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_doNotStep", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DoNotStep", "Landroidx/compose/material/icons/Icons$Filled;", "getDoNotStep", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DoNotStepKt {
    private static ImageVector _doNotStep;

    public static final ImageVector getDoNotStep(Icons.Filled filled) {
        ImageVector imageVector = _doNotStep;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.DoNotStep", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(1.39f, 4.22f, 7.9f, 7.9f);
        pathBuilderR.curveToRelative(0.18f, 0.2f, 0.18f, 0.5f, -0.01f, 0.7f);
        pathBuilderR.curveToRelative(-0.1f, 0.1f, -0.23f, 0.15f, -0.35f, 0.15f);
        pathBuilderR.reflectiveCurveToRelative(-0.26f, -0.05f, -0.35f, -0.15f);
        pathBuilderR.lineTo(6.87f, 11.1f);
        pathBuilderR.curveToRelative(-0.11f, 0.4f, -0.26f, 0.78f, -0.45f, 1.12f);
        pathBuilderR.lineToRelative(1.4f, 1.4f);
        pathBuilderR.curveToRelative(0.2f, 0.2f, 0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilderR.curveToRelative(-0.1f, 0.1f, -0.23f, 0.15f, -0.35f, 0.15f);
        pathBuilderR.reflectiveCurveToRelative(-0.26f, -0.05f, -0.35f, -0.15f);
        pathBuilderR.lineToRelative(-1.27f, -1.27f);
        pathBuilderR.curveToRelative(-0.24f, 0.29f, -0.5f, 0.56f, -0.77f, 0.8f);
        pathBuilderR.lineToRelative(1.28f, 1.28f);
        pathBuilderR.curveToRelative(0.2f, 0.2f, 0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilderR.curveTo(6.26f, 15.95f, 6.13f, 16.0f, 6.0f, 16.0f);
        pathBuilderR.reflectiveCurveToRelative(-0.26f, -0.05f, -0.35f, -0.15f);
        pathBuilderR.lineToRelative(-1.38f, -1.38f);
        pathBuilderR.curveToRelative(-0.69f, 0.46f, -1.39f, 0.79f, -1.97f, 1.02f);
        pathBuilderR.curveTo(1.52f, 15.8f, 1.0f, 16.53f, 1.0f, 17.37f);
        b.l(pathBuilderR, 20.0f, 9.5f, 3.33f, -3.33f);
        pathBuilderR.lineToRelative(5.94f, 5.94f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        v.b.y(pathBuilderR, 2.81f, 2.81f, 1.39f, 4.22f);
        pathBuilderR.moveTo(18.51f, 15.68f);
        pathBuilderR.lineToRelative(-1.41f, -1.41f);
        pathBuilderR.lineToRelative(4.48f, -4.48f);
        v.b.y(pathBuilderR, 23.0f, 11.2f, 18.51f, 15.68f);
        pathBuilderR.moveTo(20.88f, 9.08f);
        pathBuilderR.lineToRelative(-4.48f, 4.48f);
        pathBuilderR.lineTo(9.3f, 6.47f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderR, 13.8f, 2.0f, 20.88f, 9.08f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _doNotStep = imageVectorBuild;
        return imageVectorBuild;
    }
}
