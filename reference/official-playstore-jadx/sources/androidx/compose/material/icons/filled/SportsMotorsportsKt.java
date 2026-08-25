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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsMotorsports", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsMotorsports", "Landroidx/compose/material/icons/Icons$Filled;", "getSportsMotorsports", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsMotorsportsKt {
    private static ImageVector _sportsMotorsports;

    public static final ImageVector getSportsMotorsports(Icons.Filled filled) {
        ImageVector imageVector = _sportsMotorsports;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SportsMotorsports", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 11.39f);
        pathBuilderA.curveToRelative(0.0f, -0.65f, -0.39f, -1.23f, -0.98f, -1.48f);
        pathBuilderA.lineTo(5.44f, 7.55f);
        pathBuilderA.curveToRelative(-1.48f, 1.68f, -2.32f, 3.7f, -2.8f, 5.45f);
        pathBuilderA.horizontalLineToRelative(7.75f);
        pathBuilderA.curveTo(11.28f, 13.0f, 12.0f, 12.28f, 12.0f, 11.39f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(21.96f, 11.22f);
        pathBuilderA2.curveToRelative(-0.41f, -4.41f, -4.56f, -7.49f, -8.98f, -7.2f);
        pathBuilderA2.curveToRelative(-2.51f, 0.16f, -4.44f, 0.94f, -5.93f, 2.04f);
        pathBuilderA2.lineToRelative(4.74f, 2.01f);
        pathBuilderA2.curveToRelative(1.33f, 0.57f, 2.2f, 1.87f, 2.2f, 3.32f);
        pathBuilderA2.curveToRelative(0.0f, 1.99f, -1.62f, 3.61f, -3.61f, 3.61f);
        pathBuilderA2.horizontalLineTo(2.21f);
        pathBuilderA2.curveTo(2.0f, 16.31f, 2.0f, 17.2f, 2.0f, 17.2f);
        pathBuilderA2.verticalLineTo(18.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA2.horizontalLineToRelative(10.0f);
        pathBuilderA2.curveTo(18.67f, 20.0f, 22.41f, 15.99f, 21.96f, 11.22f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sportsMotorsports = imageVectorBuild;
        return imageVectorBuild;
    }
}
