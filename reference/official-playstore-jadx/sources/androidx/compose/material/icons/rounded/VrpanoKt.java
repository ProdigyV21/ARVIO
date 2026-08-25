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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_vrpano", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Vrpano", "Landroidx/compose/material/icons/Icons$Rounded;", "getVrpano", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VrpanoKt {
    private static ImageVector _vrpano;

    public static final ImageVector getVrpano(Icons.Rounded rounded) {
        ImageVector imageVector = _vrpano;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Vrpano", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.69f, 4.05f);
        pathBuilderA.curveTo(18.66f, 4.73f, 15.86f, 5.5f, 12.0f, 5.5f);
        pathBuilderA.curveToRelative(-3.89f, 0.0f, -6.95f, -0.84f, -8.69f, -1.43f);
        pathBuilderA.curveTo(2.67f, 3.85f, 2.0f, 4.33f, 2.0f, 5.02f);
        pathBuilderA.verticalLineTo(19.0f);
        pathBuilderA.curveToRelative(0.0f, 0.68f, 0.66f, 1.17f, 1.31f, 0.95f);
        pathBuilderA.curveTo(5.36f, 19.26f, 8.1f, 18.5f, 12.0f, 18.5f);
        pathBuilderA.curveToRelative(3.87f, 0.0f, 6.66f, 0.76f, 8.69f, 1.45f);
        pathBuilderA.curveTo(21.34f, 20.16f, 22.0f, 19.68f, 22.0f, 19.0f);
        pathBuilderA.verticalLineTo(5.0f);
        pathBuilderA.curveTo(22.0f, 4.32f, 21.34f, 3.84f, 20.69f, 4.05f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.28f, 15.26f);
        pathBuilderA.curveTo(15.62f, 15.1f, 13.84f, 15.0f, 12.0f, 15.0f);
        pathBuilderA.curveToRelative(-1.87f, 0.0f, -3.63f, 0.1f, -5.28f, 0.27f);
        pathBuilderA.curveTo(6.27f, 15.31f, 6.0f, 14.79f, 6.29f, 14.45f);
        pathBuilderA.lineToRelative(2.5f, -3.0f);
        pathBuilderA.curveToRelative(0.2f, -0.24f, 0.57f, -0.24f, 0.77f, 0.0f);
        pathBuilderA.lineToRelative(1.62f, 1.94f);
        pathBuilderA.lineToRelative(2.44f, -2.93f);
        pathBuilderA.curveToRelative(0.2f, -0.24f, 0.57f, -0.24f, 0.77f, 0.0f);
        pathBuilderA.lineToRelative(3.32f, 3.99f);
        pathBuilderA.curveTo(17.99f, 14.79f, 17.72f, 15.31f, 17.28f, 15.26f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _vrpano = imageVectorBuild;
        return imageVectorBuild;
    }
}
