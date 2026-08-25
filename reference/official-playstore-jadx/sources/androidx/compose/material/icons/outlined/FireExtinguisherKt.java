package androidx.compose.material.icons.outlined;

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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fireExtinguisher", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FireExtinguisher", "Landroidx/compose/material/icons/Icons$Outlined;", "getFireExtinguisher", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FireExtinguisherKt {
    private static ImageVector _fireExtinguisher;

    public static final ImageVector getFireExtinguisher(Icons.Outlined outlined) {
        ImageVector imageVector = _fireExtinguisher;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.FireExtinguisher", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(7.0f, 19.0f, 10.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.horizontalLineTo(9.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        a.D(pathBuilderA, 19.0f, 7.0f, 18.0f, 10.0f);
        d.u(pathBuilderA, -5.0f, 7.0f, 18.0f);
        pathBuilderA.moveTo(17.0f, 3.0f);
        pathBuilderA.verticalLineToRelative(6.0f);
        pathBuilderA.lineToRelative(-3.15f, -0.66f);
        pathBuilderA.curveToRelative(-0.01f, 0.0f, -0.01f, 0.01f, -0.02f, 0.02f);
        pathBuilderA.curveToRelative(1.55f, 0.62f, 2.72f, 1.98f, 3.07f, 3.64f);
        pathBuilderA.horizontalLineTo(7.1f);
        pathBuilderA.curveToRelative(0.34f, -1.66f, 1.52f, -3.02f, 3.07f, -3.64f);
        pathBuilderA.curveToRelative(-0.33f, -0.26f, -0.6f, -0.58f, -0.8f, -0.95f);
        pathBuilderA.lineTo(5.0f, 6.5f);
        pathBuilderA.verticalLineToRelative(-1.0f);
        pathBuilderA.lineToRelative(4.37f, -0.91f);
        pathBuilderA.curveTo(9.87f, 3.65f, 10.86f, 3.0f, 12.0f, 3.0f);
        pathBuilderA.curveToRelative(0.7f, 0.0f, 1.34f, 0.25f, 1.85f, 0.66f);
        c.z(pathBuilderA, 17.0f, 3.0f, 13.0f, 6.0f);
        pathBuilderA.curveToRelative(-0.03f, -0.59f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 13.0f, 6.55f, 13.0f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fireExtinguisher = imageVectorBuild;
        return imageVectorBuild;
    }
}
