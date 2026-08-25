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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localAirport", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalAirport", "Landroidx/compose/material/icons/Icons$Rounded;", "getLocalAirport", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalAirportKt {
    private static ImageVector _localAirport;

    public static final ImageVector getLocalAirport(Icons.Rounded rounded) {
        ImageVector imageVector = _localAirport;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LocalAirport", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = a.n(21.48f, 13.7f, 13.5f, 9.0f, 3.5f);
        pathBuilderN.curveTo(13.5f, 2.67f, 12.83f, 2.0f, 12.0f, 2.0f);
        pathBuilderN.curveToRelative(-0.83f, 0.0f, -1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderN.verticalLineTo(9.0f);
        pathBuilderN.lineToRelative(-7.98f, 4.7f);
        pathBuilderN.curveTo(2.2f, 13.88f, 2.0f, 14.23f, 2.0f, 14.6f);
        pathBuilderN.curveToRelative(0.0f, 0.7f, 0.67f, 1.2f, 1.34f, 1.01f);
        pathBuilderN.lineToRelative(7.16f, -2.1f);
        pathBuilderN.verticalLineTo(19.0f);
        pathBuilderN.lineToRelative(-2.26f, 1.35f);
        pathBuilderN.curveTo(8.09f, 20.44f, 8.0f, 20.61f, 8.0f, 20.78f);
        a0.a.m(pathBuilderN, 0.0f, 0.5f, 0.0f, 0.08f);
        pathBuilderN.curveToRelative(0.0f, 0.33f, 0.31f, 0.57f, 0.62f, 0.49f);
        pathBuilderN.lineToRelative(2.92f, -0.73f);
        pathBuilderN.lineTo(12.0f, 21.0f);
        pathBuilderN.lineToRelative(0.38f, 0.09f);
        pathBuilderN.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilderN.lineToRelative(0.42f, 0.11f);
        pathBuilderN.lineToRelative(1.9f, 0.48f);
        pathBuilderN.lineToRelative(0.0f, 0.0f);
        pathBuilderN.lineToRelative(0.67f, 0.17f);
        pathBuilderN.curveToRelative(0.32f, 0.08f, 0.62f, -0.16f, 0.62f, -0.49f);
        pathBuilderN.verticalLineToRelative(-0.37f);
        pathBuilderN.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilderN.verticalLineToRelative(-0.21f);
        pathBuilderN.curveToRelative(0.0f, -0.18f, -0.09f, -0.34f, -0.24f, -0.43f);
        pathBuilderN.lineTo(13.5f, 19.0f);
        pathBuilderN.verticalLineToRelative(-5.5f);
        pathBuilderN.lineToRelative(7.16f, 2.1f);
        pathBuilderN.curveTo(21.33f, 15.8f, 22.0f, 15.3f, 22.0f, 14.6f);
        pathBuilderN.curveTo(22.0f, 14.23f, 21.8f, 13.88f, 21.48f, 13.7f);
        pathBuilderN.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderN.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localAirport = imageVectorBuild;
        return imageVectorBuild;
    }
}
