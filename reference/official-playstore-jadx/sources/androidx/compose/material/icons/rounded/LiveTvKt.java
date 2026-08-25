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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_liveTv", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LiveTv", "Landroidx/compose/material/icons/Icons$Rounded;", "getLiveTv", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LiveTvKt {
    private static ImageVector _liveTv;

    public static final ImageVector getLiveTv(Icons.Rounded rounded) {
        ImageVector imageVector = _liveTv;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LiveTv", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(10.5f, 17.15f, 3.98f, -2.28f);
        pathBuilderR.curveToRelative(0.67f, -0.38f, 0.67f, -1.35f, 0.0f, -1.74f);
        pathBuilderR.lineToRelative(-3.98f, -2.28f);
        pathBuilderR.curveToRelative(-0.67f, -0.38f, -1.5f, 0.11f, -1.5f, 0.87f);
        pathBuilderR.verticalLineToRelative(4.55f);
        pathBuilderR.curveToRelative(0.0f, 0.77f, 0.83f, 1.26f, 1.5f, 0.88f);
        a.g(pathBuilderR, 21.0f, 6.0f, -7.59f);
        pathBuilderR.lineToRelative(2.94f, -2.94f);
        pathBuilderR.curveToRelative(0.2f, -0.2f, 0.2f, -0.51f, 0.0f, -0.71f);
        pathBuilderR.reflectiveCurveToRelative(-0.51f, -0.2f, -0.71f, 0.0f);
        pathBuilderR.lineTo(12.0f, 5.99f);
        pathBuilderR.lineTo(8.36f, 2.35f);
        pathBuilderR.curveToRelative(-0.2f, -0.2f, -0.51f, -0.2f, -0.71f, 0.0f);
        pathBuilderR.reflectiveCurveToRelative(-0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilderR.lineTo(10.59f, 6.0f);
        pathBuilderR.lineTo(3.0f, 6.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, 0.89f, -2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(12.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(18.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.lineTo(23.0f, 8.0f);
        pathBuilderR.curveToRelative(0.0f, -1.11f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderR, 20.0f, 20.0f, 4.0f, 20.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.lineTo(3.0f, 9.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.horizontalLineToRelative(16.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(10.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _liveTv = imageVectorBuild;
        return imageVectorBuild;
    }
}
