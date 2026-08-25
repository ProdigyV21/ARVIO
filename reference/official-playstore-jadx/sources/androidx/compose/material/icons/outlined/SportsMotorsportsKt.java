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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsMotorsports", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsMotorsports", "Landroidx/compose/material/icons/Icons$Outlined;", "getSportsMotorsports", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsMotorsportsKt {
    private static ImageVector _sportsMotorsports;

    public static final ImageVector getSportsMotorsports(Icons.Outlined outlined) {
        ImageVector imageVector = _sportsMotorsports;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SportsMotorsports", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.96f, 11.22f);
        pathBuilderA.curveTo(21.57f, 7.01f, 17.76f, 4.0f, 13.56f, 4.0f);
        pathBuilderA.curveToRelative(-0.19f, 0.0f, -0.38f, 0.01f, -0.57f, 0.02f);
        pathBuilderA.curveTo(2.0f, 4.74f, 2.0f, 17.2f, 2.0f, 17.2f);
        pathBuilderA.verticalLineTo(18.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(10.0f);
        pathBuilderA.curveTo(18.67f, 20.0f, 22.41f, 15.99f, 21.96f, 11.22f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.26f, 11.56f);
        pathBuilderA.curveToRelative(0.57f, -1.29f, 1.28f, -2.35f, 2.14f, -3.19f);
        pathBuilderA.lineToRelative(3.62f, 1.53f);
        pathBuilderA.curveToRelative(0.6f, 0.25f, 0.98f, 0.83f, 0.98f, 1.48f);
        pathBuilderA.curveToRelative(0.0f, 0.89f, -0.72f, 1.61f, -1.61f, 1.61f);
        pathBuilderA.horizontalLineTo(4.72f);
        pathBuilderA.curveTo(4.87f, 12.53f, 5.04f, 12.05f, 5.26f, 11.56f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.44f, 16.04f);
        pathBuilderA.curveTo(17.3f, 17.29f, 15.68f, 18.0f, 14.0f, 18.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.verticalLineToRelative(-0.8f);
        pathBuilderA.curveToRelative(0.0f, -0.02f, 0.01f, -0.92f, 0.24f, -2.2f);
        pathBuilderA.horizontalLineToRelative(6.15f);
        pathBuilderA.curveToRelative(1.99f, 0.0f, 3.61f, -1.62f, 3.61f, -3.61f);
        pathBuilderA.curveToRelative(0.0f, -1.45f, -0.87f, -2.76f, -2.2f, -3.32f);
        pathBuilderA.lineTo(9.3f, 7.01f);
        pathBuilderA.curveToRelative(1.1f, -0.57f, 2.37f, -0.9f, 3.82f, -0.99f);
        pathBuilderA.curveTo(13.27f, 6.0f, 13.42f, 6.0f, 13.56f, 6.0f);
        pathBuilderA.curveToRelative(3.31f, 0.0f, 6.13f, 2.37f, 6.41f, 5.41f);
        pathBuilderA.curveTo(20.13f, 13.13f, 19.59f, 14.77f, 18.44f, 16.04f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sportsMotorsports = imageVectorBuild;
        return imageVectorBuild;
    }
}
