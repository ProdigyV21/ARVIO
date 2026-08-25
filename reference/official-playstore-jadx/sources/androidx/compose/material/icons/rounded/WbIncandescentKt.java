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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wbIncandescent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WbIncandescent", "Landroidx/compose/material/icons/Icons$Rounded;", "getWbIncandescent", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WbIncandescentKt {
    private static ImageVector _wbIncandescent;

    public static final ImageVector getWbIncandescent(Icons.Rounded rounded) {
        ImageVector imageVector = _wbIncandescent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.WbIncandescent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(4.25f, 19.79f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(0.39f, -0.39f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.38f, -1.02f, 0.0f, -1.4f);
        pathBuilderA.lineToRelative(-0.01f, -0.01f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.lineToRelative(-0.39f, 0.39f);
        pathBuilderA.curveToRelative(-0.38f, 0.4f, -0.38f, 1.02f, 0.01f, 1.41f);
        a.p(pathBuilderA, 11.99f, 23.0f, 12.0f, 23.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 0.99f, -0.44f, 0.99f, -0.99f);
        pathBuilderA.verticalLineToRelative(-0.96f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.44f, -0.99f, -0.99f, -0.99f);
        pathBuilderA.horizontalLineToRelative(-0.01f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -0.99f, 0.44f, -0.99f, 0.99f);
        pathBuilderA.verticalLineToRelative(0.96f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.44f, 0.99f, 0.99f, 0.99f);
        a.p(pathBuilderA, 3.01f, 11.05f, 1.99f, 11.05f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -0.99f, 0.44f, -0.99f, 0.99f);
        pathBuilderA.verticalLineToRelative(0.01f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.44f, 0.99f, 0.99f, 0.99f);
        pathBuilderA.lineTo(3.0f, 13.04f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 0.99f, -0.44f, 0.99f, -0.99f);
        pathBuilderA.verticalLineToRelative(-0.01f);
        pathBuilderA.curveToRelative(0.01f, -0.55f, -0.43f, -0.99f, -0.98f, -0.99f);
        a.p(pathBuilderA, 15.0f, 6.86f, 15.0f, 3.05f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(3.81f);
        pathBuilderA.curveToRelative(-2.04f, 1.18f, -3.32f, 3.52f, -2.93f, 6.13f);
        pathBuilderA.curveToRelative(0.4f, 2.61f, 2.56f, 4.7f, 5.18f, 5.02f);
        pathBuilderA.curveToRelative(3.64f, 0.44f, 6.75f, -2.4f, 6.75f, -5.95f);
        pathBuilderA.curveToRelative(0.0f, -2.23f, -1.21f, -4.16f, -3.0f, -5.2f);
        d.k(pathBuilderA, 20.0f, 12.04f, 0.01f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.44f, 0.99f, 0.99f, 0.99f);
        pathBuilderA.lineTo(22.0f, 13.04f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 0.99f, -0.44f, 0.99f, -0.99f);
        pathBuilderA.verticalLineToRelative(-0.01f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.44f, -0.99f, -0.99f, -0.99f);
        pathBuilderA.horizontalLineToRelative(-1.01f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -0.99f, 0.44f, -0.99f, 0.99f);
        a.z(pathBuilderA, 17.94f, 19.41f, 0.39f, 0.39f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineToRelative(-0.39f, -0.39f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.38f, -1.4f, 0.0f);
        pathBuilderA.curveToRelative(-0.4f, 0.4f, -0.4f, 1.02f, -0.01f, 1.41f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wbIncandescent = imageVectorBuild;
        return imageVectorBuild;
    }
}
