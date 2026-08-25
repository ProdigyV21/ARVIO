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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_integrationInstructions", "Landroidx/compose/ui/graphics/vector/ImageVector;", "IntegrationInstructions", "Landroidx/compose/material/icons/Icons$Filled;", "getIntegrationInstructions", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IntegrationInstructionsKt {
    private static ImageVector _integrationInstructions;

    public static final ImageVector getIntegrationInstructions(Icons.Filled filled) {
        ImageVector imageVector = _integrationInstructions;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.IntegrationInstructions", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(19.0f, 3.0f, -4.18f);
        pathBuilderA.curveTo(14.4f, 1.84f, 13.3f, 1.0f, 12.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(9.6f, 1.84f, 9.18f, 3.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveTo(4.86f, 3.0f, 4.73f, 3.01f, 4.6f, 3.04f);
        pathBuilderA.curveTo(4.21f, 3.12f, 3.86f, 3.32f, 3.59f, 3.59f);
        pathBuilderA.curveToRelative(-0.18f, 0.18f, -0.33f, 0.4f, -0.43f, 0.64f);
        pathBuilderA.curveTo(3.06f, 4.46f, 3.0f, 4.72f, 3.0f, 5.0f);
        pathBuilderA.verticalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(0.0f, 0.27f, 0.06f, 0.54f, 0.16f, 0.78f);
        pathBuilderA.curveToRelative(0.1f, 0.24f, 0.25f, 0.45f, 0.43f, 0.64f);
        pathBuilderA.curveToRelative(0.27f, 0.27f, 0.62f, 0.47f, 1.01f, 0.55f);
        pathBuilderA.curveTo(4.73f, 20.99f, 4.86f, 21.0f, 5.0f, 21.0f);
        pathBuilderA.horizontalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineTo(5.0f);
        pathBuilderA.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        a.z(pathBuilderA, 11.0f, 14.17f, -1.41f, 1.42f);
        pathBuilderA.lineTo(6.0f, 12.0f);
        pathBuilderA.lineToRelative(3.59f, -3.59f);
        pathBuilderA.lineTo(11.0f, 9.83f);
        b.y(pathBuilderA, 8.83f, 12.0f, 11.0f, 14.17f);
        pathBuilderA.moveTo(12.0f, 4.25f);
        pathBuilderA.curveToRelative(-0.41f, 0.0f, -0.75f, -0.34f, -0.75f, -0.75f);
        pathBuilderA.reflectiveCurveTo(11.59f, 2.75f, 12.0f, 2.75f);
        pathBuilderA.reflectiveCurveToRelative(0.75f, 0.34f, 0.75f, 0.75f);
        pathBuilderA.reflectiveCurveTo(12.41f, 4.25f, 12.0f, 4.25f);
        a.p(pathBuilderA, 14.41f, 15.59f, 13.0f, 14.17f);
        pathBuilderA.lineTo(15.17f, 12.0f);
        pathBuilderA.lineTo(13.0f, 9.83f);
        pathBuilderA.lineToRelative(1.41f, -1.42f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA, 18.0f, 12.0f, 14.41f, 15.59f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _integrationInstructions = imageVectorBuild;
        return imageVectorBuild;
    }
}
