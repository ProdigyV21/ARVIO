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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_integrationInstructions", "Landroidx/compose/ui/graphics/vector/ImageVector;", "IntegrationInstructions", "Landroidx/compose/material/icons/Icons$Sharp;", "getIntegrationInstructions", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IntegrationInstructionsKt {
    private static ImageVector _integrationInstructions;

    public static final ImageVector getIntegrationInstructions(Icons.Sharp sharp) {
        ImageVector imageVector = _integrationInstructions;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.IntegrationInstructions", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(21.0f, 3.0f, -6.18f);
        pathBuilderA.curveTo(14.4f, 1.84f, 13.3f, 1.0f, 12.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(9.6f, 1.84f, 9.18f, 3.0f);
        c.k(pathBuilderA, 3.0f, 18.0f, 18.0f, 3.0f);
        pathBuilderA.moveTo(11.0f, 14.17f);
        pathBuilderA.lineToRelative(-1.41f, 1.42f);
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
