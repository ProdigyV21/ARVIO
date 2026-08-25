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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nordicWalking", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NordicWalking", "Landroidx/compose/material/icons/Icons$Outlined;", "getNordicWalking", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NordicWalkingKt {
    private static ImageVector _nordicWalking;

    public static final ImageVector getNordicWalking(Icons.Outlined outlined) {
        ImageVector imageVector = _nordicWalking;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.NordicWalking", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = d.h(19.0f, 23.0f, -1.5f, -9.0f, 19.0f);
        a.B(pathBuilderH, 23.0f, 7.53f, 14.0f, 6.0f);
        pathBuilderH.lineToRelative(-2.0f, 9.0f);
        d.q(pathBuilderH, 1.53f, 7.53f, 14.0f);
        pathBuilderH.moveTo(13.5f, 5.5f);
        pathBuilderH.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderH.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderH.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderH.reflectiveCurveTo(12.4f, 5.5f, 13.5f, 5.5f);
        a.p(pathBuilderH, 9.8f, 8.9f, 7.0f, 23.0f);
        pathBuilderH.horizontalLineToRelative(2.1f);
        pathBuilderH.lineToRelative(1.8f, -8.0f);
        b.u(pathBuilderH, 2.1f, 2.0f, 6.0f, 2.0f);
        pathBuilderH.verticalLineToRelative(-7.5f);
        pathBuilderH.lineToRelative(-2.1f, -2.0f);
        pathBuilderH.lineToRelative(0.6f, -3.0f);
        pathBuilderH.curveTo(14.8f, 12.0f, 16.8f, 13.0f, 19.0f, 13.0f);
        pathBuilderH.verticalLineToRelative(-2.0f);
        pathBuilderH.curveToRelative(-1.9f, 0.0f, -3.5f, -1.0f, -4.3f, -2.4f);
        pathBuilderH.lineToRelative(-1.0f, -1.6f);
        pathBuilderH.curveToRelative(-0.56f, -0.89f, -1.68f, -1.25f, -2.65f, -0.84f);
        b.n(pathBuilderH, 6.0f, 8.3f, 13.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.c(pathBuilderH, 9.6f, 9.8f, 8.9f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nordicWalking = imageVectorBuild;
        return imageVectorBuild;
    }
}
