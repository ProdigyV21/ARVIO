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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_mediation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Mediation", "Landroidx/compose/material/icons/Icons$Sharp;", "getMediation", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MediationKt {
    private static ImageVector _mediation;

    public static final ImageVector getMediation(Icons.Sharp sharp) {
        ImageVector imageVector = _mediation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Mediation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(18.0f, 13.0f, -5.06f);
        pathBuilderA.curveToRelative(-0.34f, 3.1f, -2.26f, 5.72f, -4.94f, 7.05f);
        pathBuilderA.curveTo(7.96f, 21.69f, 6.64f, 23.0f, 5.0f, 23.0f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.curveToRelative(0.95f, 0.0f, 1.78f, 0.45f, 2.33f, 1.14f);
        pathBuilderA.curveToRelative(1.9f, -1.03f, 3.26f, -2.91f, 3.58f, -5.14f);
        pathBuilderA.horizontalLineToRelative(-3.1f);
        pathBuilderA.curveTo(7.4f, 14.16f, 6.3f, 15.0f, 5.0f, 15.0f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.curveToRelative(1.3f, 0.0f, 2.4f, 0.84f, 2.82f, 2.0f);
        pathBuilderA.horizontalLineToRelative(3.1f);
        pathBuilderA.curveTo(10.6f, 8.77f, 9.23f, 6.9f, 7.33f, 5.86f);
        pathBuilderA.curveTo(6.78f, 6.55f, 5.95f, 7.0f, 5.0f, 7.0f);
        pathBuilderA.curveTo(3.34f, 7.0f, 2.0f, 5.66f, 2.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.curveToRelative(1.64f, 0.0f, 2.96f, 1.31f, 2.99f, 2.95f);
        pathBuilderA.curveToRelative(2.68f, 1.33f, 4.6f, 3.95f, 4.94f, 7.05f);
        pathBuilderA.horizontalLineTo(18.0f);
        pathBuilderA.verticalLineTo(8.0f);
        pathBuilderA.lineToRelative(4.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.d(pathBuilderA, -4.0f, 4.0f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _mediation = imageVectorBuild;
        return imageVectorBuild;
    }
}
