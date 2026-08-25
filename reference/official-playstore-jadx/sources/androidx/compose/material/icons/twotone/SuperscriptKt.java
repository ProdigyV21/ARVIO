package androidx.compose.material.icons.twotone;

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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_superscript", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Superscript", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSuperscript", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SuperscriptKt {
    private static ImageVector _superscript;

    public static final ImageVector getSuperscript(Icons.TwoTone twoTone) {
        ImageVector imageVector = _superscript;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Superscript", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(22.0f, 7.0f, -2.0f, 1.0f, 3.0f);
        f.t(pathBuilderK, 1.0f, -4.0f, 7.0f);
        pathBuilderK.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        a0.a.C(pathBuilderK, 2.0f, 5.0f, -3.0f, 4.0f);
        pathBuilderK.horizontalLineToRelative(3.0f);
        pathBuilderK.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderK.verticalLineToRelative(1.0f);
        pathBuilderK.curveTo(23.0f, 6.55f, 22.55f, 7.0f, 22.0f, 7.0f);
        a.g(pathBuilderK, 5.88f, 20.0f, 2.66f);
        pathBuilderK.lineToRelative(3.4f, -5.42f);
        e.C(pathBuilderK, 0.12f, 3.4f, 5.42f, 2.66f);
        pathBuilderK.lineToRelative(-4.65f, -7.27f);
        pathBuilderK.lineTo(17.81f, 6.0f);
        e.C(pathBuilderK, -2.68f, -3.07f, 4.99f, -0.12f);
        pathBuilderK.lineTo(8.85f, 6.0f);
        pathBuilderK.horizontalLineTo(6.19f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderK, 4.32f, 6.73f, 5.88f, 20.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _superscript = imageVectorBuild;
        return imageVectorBuild;
    }
}
