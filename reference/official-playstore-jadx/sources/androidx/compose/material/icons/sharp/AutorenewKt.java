package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_autorenew", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Autorenew", "Landroidx/compose/material/icons/Icons$Sharp;", "getAutorenew", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AutorenewKt {
    private static ImageVector _autorenew;

    public static final ImageVector getAutorenew(Icons.Sharp sharp) {
        ImageVector imageVector = _autorenew;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Autorenew", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = a.g(12.0f, 6.0f, 3.0f, 4.0f, -4.0f);
        pathBuilderG.lineToRelative(-4.0f, -4.0f);
        pathBuilderG.verticalLineToRelative(3.0f);
        pathBuilderG.curveToRelative(-4.42f, 0.0f, -8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilderG.curveToRelative(0.0f, 1.57f, 0.46f, 3.03f, 1.24f, 4.26f);
        pathBuilderG.lineTo(6.7f, 14.8f);
        pathBuilderG.curveToRelative(-0.45f, -0.83f, -0.7f, -1.79f, -0.7f, -2.8f);
        pathBuilderG.curveToRelative(0.0f, -3.31f, 2.69f, -6.0f, 6.0f, -6.0f);
        v.a.p(pathBuilderG, 18.76f, 7.74f, 17.3f, 9.2f);
        pathBuilderG.curveToRelative(0.44f, 0.84f, 0.7f, 1.79f, 0.7f, 2.8f);
        pathBuilderG.curveToRelative(0.0f, 3.31f, -2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilderG.verticalLineToRelative(-3.0f);
        pathBuilderG.lineToRelative(-4.0f, 4.0f);
        pathBuilderG.lineToRelative(4.0f, 4.0f);
        pathBuilderG.verticalLineToRelative(-3.0f);
        pathBuilderG.curveToRelative(4.42f, 0.0f, 8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilderG.curveToRelative(0.0f, -1.57f, -0.46f, -3.03f, -1.24f, -4.26f);
        pathBuilderG.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _autorenew = imageVectorBuild;
        return imageVectorBuild;
    }
}
