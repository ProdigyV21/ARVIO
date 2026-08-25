package androidx.compose.material.icons.automirrored.rounded;

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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_directionsBike", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DirectionsBike", "Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;", "getDirectionsBike", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsBikeKt {
    private static ImageVector _directionsBike;

    public static final ImageVector getDirectionsBike(Icons.AutoMirrored.Rounded rounded) {
        ImageVector imageVector = _directionsBike;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Rounded.DirectionsBike", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.5f, 5.5f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.0f, 12.0f);
        pathBuilderA.curveToRelative(-2.8f, 0.0f, -5.0f, 2.2f, -5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(2.2f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, -2.2f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.2f, -5.0f, -5.0f, -5.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.0f, 20.5f);
        pathBuilderA.curveToRelative(-1.9f, 0.0f, -3.5f, -1.6f, -3.5f, -3.5f);
        pathBuilderA.reflectiveCurveToRelative(1.6f, -3.5f, 3.5f, -3.5f);
        pathBuilderA.reflectiveCurveToRelative(3.5f, 1.6f, 3.5f, 3.5f);
        pathBuilderA.reflectiveCurveToRelative(-1.6f, 3.5f, -3.5f, 3.5f);
        a.z(pathBuilderA, 10.8f, 10.5f, 2.4f, -2.4f);
        pathBuilderA.lineToRelative(0.8f, 0.8f);
        pathBuilderA.curveToRelative(1.06f, 1.06f, 2.38f, 1.78f, 3.96f, 2.02f);
        pathBuilderA.curveToRelative(0.6f, 0.09f, 1.14f, -0.39f, 1.14f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.49f, -0.37f, -0.91f, -0.85f, -0.99f);
        pathBuilderA.curveToRelative(-1.11f, -0.18f, -2.02f, -0.71f, -2.75f, -1.43f);
        pathBuilderA.lineToRelative(-1.9f, -1.9f);
        pathBuilderA.curveToRelative(-0.5f, -0.4f, -1.0f, -0.6f, -1.6f, -0.6f);
        pathBuilderA.reflectiveCurveToRelative(-1.1f, 0.2f, -1.4f, 0.6f);
        pathBuilderA.lineTo(7.8f, 8.4f);
        pathBuilderA.curveToRelative(-0.4f, 0.4f, -0.6f, 0.9f, -0.6f, 1.4f);
        pathBuilderA.curveToRelative(0.0f, 0.6f, 0.2f, 1.1f, 0.6f, 1.4f);
        pathBuilderA.lineTo(11.0f, 14.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-4.4f);
        pathBuilderA.curveToRelative(0.0f, -0.52f, -0.2f, -1.01f, -0.55f, -1.38f);
        c.z(pathBuilderA, 10.8f, 10.5f, 19.0f, 12.0f);
        pathBuilderA.curveToRelative(-2.8f, 0.0f, -5.0f, 2.2f, -5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(2.2f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, -2.2f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.2f, -5.0f, -5.0f, -5.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(19.0f, 20.5f);
        pathBuilderA.curveToRelative(-1.9f, 0.0f, -3.5f, -1.6f, -3.5f, -3.5f);
        pathBuilderA.reflectiveCurveToRelative(1.6f, -3.5f, 3.5f, -3.5f);
        pathBuilderA.reflectiveCurveToRelative(3.5f, 1.6f, 3.5f, 3.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -1.6f, 3.5f, -3.5f, 3.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _directionsBike = imageVectorBuild;
        return imageVectorBuild;
    }
}
