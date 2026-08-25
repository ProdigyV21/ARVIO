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
import v.a;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_extension", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Extension", "Landroidx/compose/material/icons/Icons$Sharp;", "getExtension", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExtensionKt {
    private static ImageVector _extension;

    public static final ImageVector getExtension(Icons.Sharp sharp) {
        ImageVector imageVector = _extension;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Extension", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = a.c(20.36f, 11.0f, 19.0f, 5.0f, -6.0f);
        pathBuilderC.verticalLineTo(3.64f);
        pathBuilderC.curveToRelative(0.0f, -1.31f, -0.94f, -2.5f, -2.24f, -2.63f);
        pathBuilderC.curveTo(9.26f, 0.86f, 8.0f, 2.03f, 8.0f, 3.5f);
        pathBuilderC.verticalLineTo(5.0f);
        pathBuilderC.horizontalLineTo(2.01f);
        pathBuilderC.verticalLineToRelative(5.8f);
        pathBuilderC.horizontalLineTo(3.4f);
        pathBuilderC.curveToRelative(1.31f, 0.0f, 2.5f, 0.88f, 2.75f, 2.16f);
        pathBuilderC.curveToRelative(0.33f, 1.72f, -0.98f, 3.24f, -2.65f, 3.24f);
        y.a.t(pathBuilderC, 2.0f, 22.0f, 5.8f, -1.4f);
        pathBuilderC.curveToRelative(0.0f, -1.31f, 0.88f, -2.5f, 2.16f, -2.75f);
        pathBuilderC.curveToRelative(1.72f, -0.33f, 3.24f, 0.98f, 3.24f, 2.65f);
        c.o(pathBuilderC, 22.0f, 19.0f, -6.0f, 1.5f);
        pathBuilderC.curveToRelative(1.47f, 0.0f, 2.64f, -1.26f, 2.49f, -2.76f);
        pathBuilderC.curveToRelative(-0.13f, -1.3f, -1.33f, -2.24f, -2.63f, -2.24f);
        pathBuilderC.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderC.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _extension = imageVectorBuild;
        return imageVectorBuild;
    }
}
