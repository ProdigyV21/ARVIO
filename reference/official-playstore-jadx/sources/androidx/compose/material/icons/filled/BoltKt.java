package androidx.compose.material.icons.filled;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bolt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Bolt", "Landroidx/compose/material/icons/Icons$Filled;", "getBolt", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BoltKt {
    private static ImageVector _bolt;

    public static final ImageVector getBolt(Icons.Filled filled) {
        ImageVector imageVector = _bolt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Bolt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(11.0f, 21.0f, -1.0f, 1.0f, -7.0f);
        pathBuilderG.horizontalLineTo(7.5f);
        pathBuilderG.curveToRelative(-0.58f, 0.0f, -0.57f, -0.32f, -0.38f, -0.66f);
        pathBuilderG.curveToRelative(0.19f, -0.34f, 0.05f, -0.08f, 0.07f, -0.12f);
        pathBuilderG.curveTo(8.48f, 10.94f, 10.42f, 7.54f, 13.0f, 3.0f);
        e.C(pathBuilderG, 1.0f, -1.0f, 7.0f, 3.5f);
        pathBuilderG.curveToRelative(0.49f, 0.0f, 0.56f, 0.33f, 0.47f, 0.51f);
        pathBuilderG.lineToRelative(-0.07f, 0.15f);
        pathBuilderG.curveTo(12.96f, 17.55f, 11.0f, 21.0f, 11.0f, 21.0f);
        pathBuilderG.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bolt = imageVectorBuild;
        return imageVectorBuild;
    }
}
