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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_layersClear", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LayersClear", "Landroidx/compose/material/icons/Icons$Outlined;", "getLayersClear", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LayersClearKt {
    private static ImageVector _layersClear;

    public static final ImageVector getLayersClear(Icons.Outlined outlined) {
        ImageVector imageVector = _layersClear;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.LayersClear", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(12.0f, 4.53f, 17.74f, 9.0f);
        pathBuilderJ.lineToRelative(-1.89f, 1.47f);
        pathBuilderJ.lineToRelative(1.43f, 1.42f);
        pathBuilderJ.lineTo(21.0f, 9.0f);
        pathBuilderJ.lineToRelative(-9.0f, -7.0f);
        b.D(pathBuilderJ, -2.59f, 2.02f, 1.42f, 1.42f);
        pathBuilderJ.moveTo(21.0f, 14.07f);
        pathBuilderJ.lineToRelative(-1.63f, -1.27f);
        b.D(pathBuilderJ, -0.67f, 0.52f, 1.43f, 1.43f);
        pathBuilderJ.moveTo(3.41f, 0.86f);
        pathBuilderJ.lineTo(2.0f, 2.27f);
        pathBuilderJ.lineToRelative(4.22f, 4.22f);
        pathBuilderJ.lineTo(3.0f, 9.0f);
        pathBuilderJ.lineToRelative(9.0f, 7.0f);
        pathBuilderJ.lineToRelative(2.1f, -1.63f);
        pathBuilderJ.lineToRelative(1.42f, 1.42f);
        pathBuilderJ.lineToRelative(-3.53f, 2.75f);
        pathBuilderJ.lineToRelative(-7.37f, -5.73f);
        pathBuilderJ.lineTo(3.0f, 14.07f);
        pathBuilderJ.lineToRelative(9.0f, 7.0f);
        pathBuilderJ.lineToRelative(4.95f, -3.85f);
        pathBuilderJ.lineTo(20.73f, 21.0f);
        d.C(pathBuilderJ, 1.41f, -1.41f, 3.41f, 0.86f);
        pathBuilderJ.moveTo(12.0f, 13.47f);
        pathBuilderJ.lineTo(6.26f, 9.0f);
        pathBuilderJ.lineToRelative(1.39f, -1.08f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderJ, 5.02f, 5.02f, -0.67f, 0.53f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _layersClear = imageVectorBuild;
        return imageVectorBuild;
    }
}
