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
import v.b;
import v.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_superscript", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Superscript", "Landroidx/compose/material/icons/Icons$Sharp;", "getSuperscript", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SuperscriptKt {
    private static ImageVector _superscript;

    public static final ImageVector getSuperscript(Icons.Sharp sharp) {
        ImageVector imageVector = _superscript;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Superscript", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(20.0f, 7.0f, 1.0f, 3.0f, 1.0f);
        a0.a.C(pathBuilderU, -4.0f, 6.0f, 3.0f, 5.0f);
        w.a.C(pathBuilderU, -3.0f, 4.0f, 4.0f, 3.0f);
        w.a.v(pathBuilderU, 20.0f, 5.88f, 20.0f, 2.66f);
        pathBuilderU.lineToRelative(3.4f, -5.42f);
        e.C(pathBuilderU, 0.12f, 3.4f, 5.42f, 2.66f);
        pathBuilderU.lineToRelative(-4.65f, -7.27f);
        pathBuilderU.lineTo(17.81f, 6.0f);
        e.C(pathBuilderU, -2.68f, -3.07f, 4.99f, -0.12f);
        pathBuilderU.lineTo(8.85f, 6.0f);
        pathBuilderU.horizontalLineTo(6.19f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderU, 4.32f, 6.73f, 5.88f, 20.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _superscript = imageVectorBuild;
        return imageVectorBuild;
    }
}
