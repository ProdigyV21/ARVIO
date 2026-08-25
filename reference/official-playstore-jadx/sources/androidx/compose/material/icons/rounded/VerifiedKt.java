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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_verified", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Verified", "Landroidx/compose/material/icons/Icons$Rounded;", "getVerified", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VerifiedKt {
    private static ImageVector _verified;

    public static final ImageVector getVerified(Icons.Rounded rounded) {
        ImageVector imageVector = _verified;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Verified", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(23.0f, 12.0f, -2.44f, -2.79f);
        pathBuilderR.lineToRelative(0.34f, -3.69f);
        pathBuilderR.lineToRelative(-3.61f, -0.82f);
        pathBuilderR.lineTo(15.4f, 1.5f);
        pathBuilderR.lineTo(12.0f, 2.96f);
        pathBuilderR.lineTo(8.6f, 1.5f);
        pathBuilderR.lineTo(6.71f, 4.69f);
        pathBuilderR.lineTo(3.1f, 5.5f);
        pathBuilderR.lineTo(3.44f, 9.2f);
        pathBuilderR.lineTo(1.0f, 12.0f);
        pathBuilderR.lineToRelative(2.44f, 2.79f);
        pathBuilderR.lineToRelative(-0.34f, 3.7f);
        pathBuilderR.lineToRelative(3.61f, 0.82f);
        pathBuilderR.lineTo(8.6f, 22.5f);
        pathBuilderR.lineToRelative(3.4f, -1.47f);
        pathBuilderR.lineToRelative(3.4f, 1.46f);
        pathBuilderR.lineToRelative(1.89f, -3.19f);
        pathBuilderR.lineToRelative(3.61f, -0.82f);
        d.C(pathBuilderR, -0.34f, -3.69f, 23.0f, 12.0f);
        pathBuilderR.moveTo(9.38f, 16.01f);
        pathBuilderR.lineTo(7.0f, 13.61f);
        pathBuilderR.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderR.lineToRelative(0.07f, -0.07f);
        pathBuilderR.curveToRelative(0.39f, -0.39f, 1.03f, -0.39f, 1.42f, 0.0f);
        pathBuilderR.lineToRelative(1.61f, 1.62f);
        pathBuilderR.lineToRelative(5.15f, -5.16f);
        pathBuilderR.curveToRelative(0.39f, -0.39f, 1.03f, -0.39f, 1.42f, 0.0f);
        pathBuilderR.lineToRelative(0.07f, 0.07f);
        pathBuilderR.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderR.lineToRelative(-5.92f, 5.94f);
        pathBuilderR.curveTo(10.41f, 16.4f, 9.78f, 16.4f, 9.38f, 16.01f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _verified = imageVectorBuild;
        return imageVectorBuild;
    }
}
