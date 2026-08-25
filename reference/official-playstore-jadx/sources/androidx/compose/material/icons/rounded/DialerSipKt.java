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
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dialerSip", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DialerSip", "Landroidx/compose/material/icons/Icons$Rounded;", "getDialerSip", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DialerSipKt {
    private static ImageVector _dialerSip;

    public static final ImageVector getDialerSip(Icons.Rounded rounded) {
        ImageVector imageVector = _dialerSip;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DialerSip", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.5f, 8.0f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.5f, 7.0f);
        pathBuilderA.curveToRelative(-0.28f, 0.0f, -0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.horizontalLineToRelative(1.95f);
        pathBuilderA.curveToRelative(0.3f, 0.0f, 0.55f, -0.25f, 0.55f, -0.55f);
        pathBuilderA.verticalLineToRelative(-1.9f);
        pathBuilderA.curveToRelative(0.0f, -0.3f, -0.25f, -0.55f, -0.55f, -0.55f);
        pathBuilderA.lineTo(13.0f, 5.0f);
        pathBuilderA.lineTo(13.0f, 4.0f);
        pathBuilderA.horizontalLineToRelative(1.5f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.horizontalLineToRelative(-1.95f);
        pathBuilderA.curveToRelative(-0.3f, 0.0f, -0.55f, 0.25f, -0.55f, 0.55f);
        pathBuilderA.verticalLineToRelative(1.89f);
        pathBuilderA.curveToRelative(0.0f, 0.31f, 0.25f, 0.56f, 0.55f, 0.56f);
        e.p(pathBuilderA, 14.0f, 6.0f, 1.0f, -1.5f);
        pathBuilderA.moveTo(20.45f, 3.0f);
        pathBuilderA.horizontalLineToRelative(-1.89f);
        pathBuilderA.curveToRelative(-0.31f, 0.0f, -0.56f, 0.25f, -0.56f, 0.55f);
        pathBuilderA.lineTo(18.0f, 7.5f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.lineTo(19.0f, 6.0f);
        pathBuilderA.horizontalLineToRelative(1.45f);
        pathBuilderA.curveToRelative(0.3f, 0.0f, 0.55f, -0.25f, 0.55f, -0.55f);
        pathBuilderA.verticalLineToRelative(-1.9f);
        pathBuilderA.curveToRelative(0.0f, -0.3f, -0.25f, -0.55f, -0.55f, -0.55f);
        a.g(pathBuilderA, 20.0f, 5.0f, -1.0f);
        b.g(pathBuilderA, 19.0f, 4.0f, 1.0f, 1.0f);
        pathBuilderA.moveTo(19.21f, 15.27f);
        pathBuilderA.lineToRelative(-2.54f, -0.29f);
        pathBuilderA.curveToRelative(-0.61f, -0.07f, -1.21f, 0.14f, -1.64f, 0.57f);
        pathBuilderA.lineToRelative(-1.84f, 1.84f);
        pathBuilderA.curveToRelative(-2.83f, -1.44f, -5.15f, -3.75f, -6.59f, -6.59f);
        pathBuilderA.lineToRelative(1.85f, -1.85f);
        pathBuilderA.curveToRelative(0.43f, -0.43f, 0.64f, -1.04f, 0.57f, -1.64f);
        pathBuilderA.lineToRelative(-0.29f, -2.52f);
        pathBuilderA.curveToRelative(-0.11f, -1.01f, -0.97f, -1.78f, -1.98f, -1.78f);
        pathBuilderA.lineTo(5.02f, 3.01f);
        pathBuilderA.curveToRelative(-1.13f, 0.0f, -2.07f, 0.94f, -2.0f, 2.07f);
        pathBuilderA.curveToRelative(0.53f, 8.54f, 7.36f, 15.36f, 15.89f, 15.89f);
        pathBuilderA.curveToRelative(1.13f, 0.07f, 2.07f, -0.87f, 2.07f, -2.0f);
        pathBuilderA.verticalLineToRelative(-1.73f);
        pathBuilderA.curveToRelative(0.01f, -1.0f, -0.76f, -1.86f, -1.77f, -1.97f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dialerSip = imageVectorBuild;
        return imageVectorBuild;
    }
}
