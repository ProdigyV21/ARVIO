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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localActivity", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalActivity", "Landroidx/compose/material/icons/Icons$Rounded;", "getLocalActivity", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalActivityKt {
    private static ImageVector _localActivity;

    public static final ImageVector getLocalActivity(Icons.Rounded rounded) {
        ImageVector imageVector = _localActivity;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LocalActivity", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -0.76f, 0.43f, -1.42f, 1.06f, -1.76f);
        pathBuilderA.curveToRelative(0.6f, -0.33f, 0.94f, -1.01f, 0.94f, -1.7f);
        pathBuilderA.lineTo(22.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.lineTo(4.0f, 4.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -1.99f, 0.89f, -1.99f, 1.99f);
        pathBuilderA.verticalLineToRelative(2.55f);
        pathBuilderA.curveToRelative(0.0f, 0.69f, 0.33f, 1.37f, 0.94f, 1.69f);
        pathBuilderA.curveTo(3.58f, 10.58f, 4.0f, 11.24f, 4.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.43f, 1.43f, -1.06f, 1.76f);
        pathBuilderA.curveToRelative(-0.6f, 0.33f, -0.94f, 1.01f, -0.94f, 1.7f);
        pathBuilderA.lineTo(2.0f, 18.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(16.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-2.54f);
        pathBuilderA.curveToRelative(0.0f, -0.69f, -0.34f, -1.37f, -0.94f, -1.7f);
        pathBuilderA.curveToRelative(-0.63f, -0.34f, -1.06f, -1.0f, -1.06f, -1.76f);
        a.p(pathBuilderA, 14.5f, 16.1f, 12.0f, 14.5f);
        pathBuilderA.lineToRelative(-2.5f, 1.61f);
        pathBuilderA.curveToRelative(-0.38f, 0.24f, -0.87f, -0.11f, -0.75f, -0.55f);
        pathBuilderA.lineToRelative(0.75f, -2.88f);
        pathBuilderA.lineToRelative(-2.3f, -1.88f);
        pathBuilderA.curveToRelative(-0.35f, -0.29f, -0.17f, -0.86f, 0.29f, -0.89f);
        pathBuilderA.lineToRelative(2.96f, -0.17f);
        pathBuilderA.lineToRelative(1.08f, -2.75f);
        pathBuilderA.curveToRelative(0.17f, -0.42f, 0.77f, -0.42f, 0.93f, 0.0f);
        pathBuilderA.lineToRelative(1.08f, 2.76f);
        pathBuilderA.lineToRelative(2.96f, 0.17f);
        pathBuilderA.curveToRelative(0.45f, 0.03f, 0.64f, 0.6f, 0.29f, 0.89f);
        pathBuilderA.lineToRelative(-2.3f, 1.88f);
        pathBuilderA.lineToRelative(0.76f, 2.86f);
        pathBuilderA.curveToRelative(0.12f, 0.45f, -0.37f, 0.8f, -0.75f, 0.55f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localActivity = imageVectorBuild;
        return imageVectorBuild;
    }
}
