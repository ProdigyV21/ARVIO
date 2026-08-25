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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tableBar", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TableBar", "Landroidx/compose/material/icons/Icons$TwoTone;", "getTableBar", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TableBarKt {
    private static ImageVector _tableBar;

    public static final ImageVector getTableBar(Icons.TwoTone twoTone) {
        ImageVector imageVector = _tableBar;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.TableBar", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(4.28f, 7.5f);
        pathBuilderA.arcToRelative(7.72f, 1.5f, 0.0f, true, false, 15.44f, 0.0f);
        pathBuilderA.arcToRelative(7.72f, 1.5f, 0.0f, true, false, -15.44f, 0.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(22.0f, 7.5f);
        pathBuilderA2.curveTo(22.0f, 5.57f, 17.52f, 4.0f, 12.0f, 4.0f);
        pathBuilderA2.reflectiveCurveTo(2.0f, 5.57f, 2.0f, 7.5f);
        pathBuilderA2.curveToRelative(0.0f, 1.81f, 3.95f, 3.31f, 9.0f, 3.48f);
        pathBuilderA2.verticalLineTo(15.0f);
        pathBuilderA2.horizontalLineTo(9.35f);
        pathBuilderA2.curveToRelative(-0.82f, 0.0f, -1.55f, 0.5f, -1.86f, 1.26f);
        pathBuilderA2.lineTo(6.0f, 20.0f);
        e.C(pathBuilderA2, 2.0f, 1.2f, -3.0f, 5.6f);
        pathBuilderA2.lineToRelative(1.2f, 3.0f);
        pathBuilderA2.horizontalLineToRelative(2.0f);
        pathBuilderA2.lineToRelative(-1.5f, -3.74f);
        pathBuilderA2.curveTo(16.2f, 15.5f, 15.46f, 15.0f, 14.65f, 15.0f);
        pathBuilderA2.horizontalLineTo(13.0f);
        pathBuilderA2.verticalLineToRelative(-4.02f);
        pathBuilderA2.curveTo(18.05f, 10.81f, 22.0f, 9.31f, 22.0f, 7.5f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 9.0f);
        pathBuilderA2.curveTo(7.95f, 9.0f, 5.26f, 8.14f, 4.28f, 7.5f);
        pathBuilderA2.curveTo(5.26f, 6.86f, 7.95f, 6.0f, 12.0f, 6.0f);
        pathBuilderA2.reflectiveCurveToRelative(6.74f, 0.86f, 7.72f, 1.5f);
        pathBuilderA2.curveTo(18.74f, 8.14f, 16.05f, 9.0f, 12.0f, 9.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tableBar = imageVectorBuild;
        return imageVectorBuild;
    }
}
