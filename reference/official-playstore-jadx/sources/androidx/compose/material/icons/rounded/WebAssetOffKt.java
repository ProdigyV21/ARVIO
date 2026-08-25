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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_webAssetOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WebAssetOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getWebAssetOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WebAssetOffKt {
    private static ImageVector _webAssetOff;

    public static final ImageVector getWebAssetOff(Icons.Rounded rounded) {
        ImageVector imageVector = _webAssetOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.WebAssetOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(6.83f, 4.0f, 20.0f);
        pathBuilderQ.curveToRelative(1.11f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.34f, -0.09f, 0.66f, -0.23f, 0.94f);
        b.n(pathBuilderQ, 20.0f, 17.17f, 8.0f, -9.17f);
        c.z(pathBuilderQ, 6.83f, 4.0f, 19.78f, 22.61f);
        pathBuilderQ.lineTo(17.17f, 20.0f);
        pathBuilderQ.horizontalLineTo(4.0f);
        pathBuilderQ.curveToRelative(-1.11f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.34f, 0.08f, -0.66f, 0.23f, -0.94f);
        pathBuilderQ.lineTo(1.39f, 4.22f);
        pathBuilderQ.curveTo(1.0f, 3.83f, 1.0f, 3.2f, 1.39f, 2.81f);
        pathBuilderQ.lineToRelative(0.0f, 0.0f);
        pathBuilderQ.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderQ.lineToRelative(18.38f, 18.38f);
        pathBuilderQ.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderQ.lineToRelative(0.0f, 0.0f);
        pathBuilderQ.curveTo(20.8f, 23.0f, 20.17f, 23.0f, 19.78f, 22.61f);
        a.z(pathBuilderQ, 15.17f, 18.0f, -10.0f, -10.0f);
        pathBuilderQ.horizontalLineTo(4.0f);
        pathBuilderQ.verticalLineToRelative(10.0f);
        pathBuilderQ.horizontalLineTo(15.17f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _webAssetOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
