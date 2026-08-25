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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sync", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sync", "Landroidx/compose/material/icons/Icons$Sharp;", "getSync", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SyncKt {
    private static ImageVector _sync;

    public static final ImageVector getSync(Icons.Sharp sharp) {
        ImageVector imageVector = _sync;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Sync", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(12.0f, 4.0f, 12.0f, 1.0f);
        pathBuilderJ.lineTo(8.0f, 5.0f);
        pathBuilderJ.lineToRelative(4.0f, 4.0f);
        pathBuilderJ.lineTo(12.0f, 6.0f);
        pathBuilderJ.curveToRelative(3.31f, 0.0f, 6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.01f, -0.25f, 1.97f, -0.7f, 2.8f);
        pathBuilderJ.lineToRelative(1.46f, 1.46f);
        pathBuilderJ.curveTo(19.54f, 15.03f, 20.0f, 13.57f, 20.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, -4.42f, -3.58f, -8.0f, -8.0f, -8.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 18.0f);
        pathBuilderJ.curveToRelative(-3.31f, 0.0f, -6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.01f, 0.25f, -1.97f, 0.7f, -2.8f);
        pathBuilderJ.lineTo(5.24f, 7.74f);
        pathBuilderJ.curveTo(4.46f, 8.97f, 4.0f, 10.43f, 4.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderJ.verticalLineToRelative(3.0f);
        pathBuilderJ.lineToRelative(4.0f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.j(pathBuilderJ, -4.0f, -4.0f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sync = imageVectorBuild;
        return imageVectorBuild;
    }
}
