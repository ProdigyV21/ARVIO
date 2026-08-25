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
import v.b;
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fiberDvr", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FiberDvr", "Landroidx/compose/material/icons/Icons$Rounded;", "getFiberDvr", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FiberDvrKt {
    private static ImageVector _fiberDvr;

    public static final ImageVector getFiberDvr(Icons.Rounded rounded) {
        ImageVector imageVector = _fiberDvr;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FiberDvr", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = c.b(4.5f, 10.5f, 2.0f, 3.0f, -2.0f);
        a.C(pathBuilderB, 17.5f, 10.5f, 2.0f, 1.0f);
        f.x(pathBuilderB, -2.0f, 21.0f, 3.0f);
        pathBuilderB.lineTo(3.0f, 3.0f);
        pathBuilderB.curveToRelative(-1.11f, 0.0f, -2.0f, 0.89f, -2.0f, 2.0f);
        pathBuilderB.verticalLineToRelative(14.0f);
        pathBuilderB.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderB.horizontalLineToRelative(18.0f);
        pathBuilderB.curveToRelative(1.11f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderB.lineTo(23.0f, 5.0f);
        pathBuilderB.curveToRelative(0.0f, -1.11f, -0.89f, -2.0f, -2.0f, -2.0f);
        pathBuilderB.close();
        pathBuilderB.moveTo(8.0f, 13.5f);
        pathBuilderB.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderB.horizontalLineToRelative(-3.0f);
        pathBuilderB.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderB.verticalLineToRelative(-5.0f);
        pathBuilderB.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderB.horizontalLineToRelative(3.0f);
        pathBuilderB.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        b.f(pathBuilderB, 3.0f, 14.1f, 9.92f);
        pathBuilderB.lineToRelative(-1.27f, 4.36f);
        pathBuilderB.curveToRelative(-0.12f, 0.43f, -0.52f, 0.72f, -0.96f, 0.72f);
        pathBuilderB.reflectiveCurveToRelative(-0.84f, -0.29f, -0.96f, -0.72f);
        pathBuilderB.lineTo(9.64f, 9.92f);
        pathBuilderB.curveToRelative(-0.14f, -0.46f, 0.21f, -0.92f, 0.69f, -0.92f);
        pathBuilderB.curveToRelative(0.32f, 0.0f, 0.6f, 0.21f, 0.69f, 0.52f);
        pathBuilderB.lineToRelative(0.85f, 2.91f);
        pathBuilderB.lineToRelative(0.85f, -2.91f);
        pathBuilderB.curveToRelative(0.09f, -0.31f, 0.37f, -0.52f, 0.69f, -0.52f);
        pathBuilderB.curveToRelative(0.48f, 0.0f, 0.83f, 0.46f, 0.69f, 0.92f);
        pathBuilderB.close();
        pathBuilderB.moveTo(21.0f, 11.5f);
        pathBuilderB.curveToRelative(0.0f, 0.6f, -0.4f, 1.15f, -0.9f, 1.4f);
        pathBuilderB.lineToRelative(0.63f, 1.48f);
        pathBuilderB.curveToRelative(0.19f, 0.45f, -0.14f, 0.96f, -0.63f, 0.96f);
        pathBuilderB.curveToRelative(-0.28f, 0.0f, -0.53f, -0.16f, -0.63f, -0.42f);
        pathBuilderB.lineTo(18.65f, 13.0f);
        pathBuilderB.lineTo(17.5f, 13.0f);
        pathBuilderB.verticalLineToRelative(1.31f);
        pathBuilderB.curveToRelative(0.0f, 0.38f, -0.31f, 0.69f, -0.69f, 0.69f);
        pathBuilderB.horizontalLineToRelative(-0.12f);
        pathBuilderB.curveToRelative(-0.38f, 0.0f, -0.69f, -0.31f, -0.69f, -0.69f);
        pathBuilderB.lineTo(16.0f, 9.64f);
        pathBuilderB.curveToRelative(0.0f, -0.35f, 0.29f, -0.64f, 0.64f, -0.64f);
        pathBuilderB.horizontalLineToRelative(2.86f);
        pathBuilderB.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderB, 1.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fiberDvr = imageVectorBuild;
        return imageVectorBuild;
    }
}
