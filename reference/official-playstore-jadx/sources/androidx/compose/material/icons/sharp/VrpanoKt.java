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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_vrpano", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Vrpano", "Landroidx/compose/material/icons/Icons$Sharp;", "getVrpano", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VrpanoKt {
    private static ImageVector _vrpano;

    public static final ImageVector getVrpano(Icons.Sharp sharp) {
        ImageVector imageVector = _vrpano;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Vrpano", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 5.5f);
        pathBuilderA.curveToRelative(-5.25f, 0.0f, -9.01f, -1.54f, -10.0f, -1.92f);
        pathBuilderA.verticalLineTo(20.4f);
        pathBuilderA.curveToRelative(2.16f, -0.76f, 5.21f, -1.9f, 10.0f, -1.9f);
        pathBuilderA.curveToRelative(4.78f, 0.0f, 7.91f, 1.17f, 10.0f, 1.9f);
        pathBuilderA.verticalLineTo(3.6f);
        pathBuilderA.curveTo(19.91f, 4.33f, 16.77f, 5.5f, 12.0f, 5.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 15.0f);
        pathBuilderA.curveToRelative(-2.34f, 0.0f, -4.52f, 0.15f, -6.52f, 0.41f);
        pathBuilderA.lineToRelative(3.69f, -4.42f);
        pathBuilderA.lineToRelative(2.0f, 2.4f);
        pathBuilderA.lineTo(14.0f, 10.0f);
        pathBuilderA.lineToRelative(4.51f, 5.4f);
        pathBuilderA.curveTo(16.52f, 15.15f, 14.3f, 15.0f, 12.0f, 15.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _vrpano = imageVectorBuild;
        return imageVectorBuild;
    }
}
