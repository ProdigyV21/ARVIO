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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tipsAndUpdates", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TipsAndUpdates", "Landroidx/compose/material/icons/Icons$Outlined;", "getTipsAndUpdates", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TipsAndUpdatesKt {
    private static ImageVector _tipsAndUpdates;

    public static final ImageVector getTipsAndUpdates(Icons.Outlined outlined) {
        ImageVector imageVector = _tipsAndUpdates;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.TipsAndUpdates", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(7.0f, 20.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(7.0f, 21.1f, 7.0f, 20.0f);
        b.w(pathBuilderA, 5.0f, 19.0f, 8.0f, -2.0f);
        c.l(pathBuilderA, 5.0f, 19.0f, 16.5f, 9.5f);
        pathBuilderA.curveToRelative(0.0f, 3.82f, -2.66f, 5.86f, -3.77f, 6.5f);
        pathBuilderA.horizontalLineTo(5.27f);
        pathBuilderA.curveTo(4.16f, 15.36f, 1.5f, 13.32f, 1.5f, 9.5f);
        pathBuilderA.curveTo(1.5f, 5.36f, 4.86f, 2.0f, 9.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(16.5f, 5.36f, 16.5f, 9.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.5f, 9.5f);
        pathBuilderA.curveTo(14.5f, 6.47f, 12.03f, 4.0f, 9.0f, 4.0f);
        pathBuilderA.reflectiveCurveTo(3.5f, 6.47f, 3.5f, 9.5f);
        pathBuilderA.curveToRelative(0.0f, 2.47f, 1.49f, 3.89f, 2.35f, 4.5f);
        pathBuilderA.horizontalLineToRelative(6.3f);
        pathBuilderA.curveTo(13.01f, 13.39f, 14.5f, 11.97f, 14.5f, 9.5f);
        a.p(pathBuilderA, 21.37f, 7.37f, 20.0f, 8.0f);
        pathBuilderA.lineToRelative(1.37f, 0.63f);
        pathBuilderA.lineTo(22.0f, 10.0f);
        pathBuilderA.lineToRelative(0.63f, -1.37f);
        pathBuilderA.lineTo(24.0f, 8.0f);
        pathBuilderA.lineToRelative(-1.37f, -0.63f);
        b.y(pathBuilderA, 22.0f, 6.0f, 21.37f, 7.37f);
        pathBuilderA.moveTo(19.0f, 6.0f);
        pathBuilderA.lineToRelative(0.94f, -2.06f);
        pathBuilderA.lineTo(22.0f, 3.0f);
        pathBuilderA.lineToRelative(-2.06f, -0.94f);
        pathBuilderA.lineTo(19.0f, 0.0f);
        pathBuilderA.lineToRelative(-0.94f, 2.06f);
        pathBuilderA.lineTo(16.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 2.06f, 0.94f, 19.0f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tipsAndUpdates = imageVectorBuild;
        return imageVectorBuild;
    }
}
