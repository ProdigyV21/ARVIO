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
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_redeem", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Redeem", "Landroidx/compose/material/icons/Icons$Outlined;", "getRedeem", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RedeemKt {
    private static ImageVector _redeem;

    public static final ImageVector getRedeem(Icons.Outlined outlined) {
        ImageVector imageVector = _redeem;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Redeem", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(20.0f, 6.0f, -2.18f);
        pathBuilderA.curveToRelative(0.11f, -0.31f, 0.18f, -0.65f, 0.18f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.curveToRelative(-1.05f, 0.0f, -1.96f, 0.54f, -2.5f, 1.35f);
        pathBuilderA.lineToRelative(-0.5f, 0.67f);
        pathBuilderA.lineToRelative(-0.5f, -0.68f);
        pathBuilderA.curveTo(10.96f, 2.54f, 10.05f, 2.0f, 9.0f, 2.0f);
        pathBuilderA.curveTo(7.34f, 2.0f, 6.0f, 3.34f, 6.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, 0.35f, 0.07f, 0.69f, 0.18f, 1.0f);
        pathBuilderA.lineTo(4.0f, 6.0f);
        pathBuilderA.curveToRelative(-1.11f, 0.0f, -1.99f, 0.89f, -1.99f, 2.0f);
        pathBuilderA.lineTo(2.0f, 19.0f);
        pathBuilderA.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(16.0f);
        pathBuilderA.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderA.lineTo(22.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, -1.11f, -0.89f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 4.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 4.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        a.p(pathBuilderA, 20.0f, 19.0f, 4.0f, 19.0f);
        b.l(pathBuilderA, -2.0f, 16.0f, 2.0f);
        pathBuilderA.moveTo(20.0f, 14.0f);
        pathBuilderA.lineTo(4.0f, 14.0f);
        pathBuilderA.lineTo(4.0f, 8.0f);
        pathBuilderA.horizontalLineToRelative(5.08f);
        pathBuilderA.lineTo(7.0f, 10.83f);
        pathBuilderA.lineTo(8.62f, 12.0f);
        pathBuilderA.lineTo(11.0f, 8.76f);
        pathBuilderA.lineToRelative(1.0f, -1.36f);
        pathBuilderA.lineToRelative(1.0f, 1.36f);
        pathBuilderA.lineTo(15.38f, 12.0f);
        pathBuilderA.lineTo(17.0f, 10.83f);
        pathBuilderA.lineTo(14.92f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.d(pathBuilderA, 20.0f, 8.0f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _redeem = imageVectorBuild;
        return imageVectorBuild;
    }
}
