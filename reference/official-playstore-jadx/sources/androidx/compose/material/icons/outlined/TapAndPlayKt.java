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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tapAndPlay", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TapAndPlay", "Landroidx/compose/material/icons/Icons$Outlined;", "getTapAndPlay", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TapAndPlayKt {
    private static ImageVector _tapAndPlay;

    public static final ImageVector getTapAndPlay(Icons.Outlined outlined) {
        ImageVector imageVector = _tapAndPlay;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.TapAndPlay", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(2.0f, 16.0f, 2.0f);
        pathBuilderI.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.curveToRelative(0.0f, -3.87f, -3.13f, -7.0f, -7.0f, -7.0f);
        c.y(pathBuilderI, 2.0f, 20.0f, 3.0f, 3.0f);
        pathBuilderI.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        d.k(pathBuilderI, 2.0f, 12.0f, 2.0f);
        pathBuilderI.curveToRelative(4.97f, 0.0f, 9.0f, 4.03f, 9.0f, 9.0f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.curveToRelative(0.0f, -6.08f, -4.92f, -11.0f, -11.0f, -11.0f);
        a.p(pathBuilderI, 17.0f, 1.01f, 7.0f, 1.0f);
        pathBuilderI.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderI.verticalLineToRelative(7.37f);
        pathBuilderI.curveToRelative(0.69f, 0.16f, 1.36f, 0.37f, 2.0f, 0.64f);
        b.A(pathBuilderI, 7.0f, 5.0f, 10.0f, 13.0f);
        pathBuilderI.horizontalLineToRelative(-3.03f);
        pathBuilderI.curveToRelative(0.52f, 1.25f, 0.84f, 2.59f, 0.95f, 4.0f);
        pathBuilderI.lineTo(17.0f, 22.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.lineTo(19.0f, 3.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, -0.9f, -1.99f, -2.0f, -1.99f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tapAndPlay = imageVectorBuild;
        return imageVectorBuild;
    }
}
