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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_speed", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Speed", "Landroidx/compose/material/icons/Icons$Rounded;", "getSpeed", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SpeedKt {
    private static ImageVector _speed;

    public static final ImageVector getSpeed(Icons.Rounded rounded) {
        ImageVector imageVector = _speed;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Speed", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.46f, 10.0f);
        pathBuilderA.arcToRelative(1.0f, 1.0f, 0.0f, false, false, -0.07f, 1.0f);
        pathBuilderA.arcToRelative(7.55f, 7.55f, 0.0f, false, true, 0.52f, 1.81f);
        pathBuilderA.arcToRelative(8.0f, 8.0f, 0.0f, false, true, -0.69f, 4.73f);
        pathBuilderA.arcToRelative(1.0f, 1.0f, 0.0f, false, true, -0.89f, 0.53f);
        pathBuilderA.horizontalLineTo(5.68f);
        pathBuilderA.arcToRelative(1.0f, 1.0f, 0.0f, false, true, -0.89f, -0.54f);
        pathBuilderA.arcTo(8.0f, 8.0f, 0.0f, false, true, 13.0f, 6.06f);
        pathBuilderA.arcToRelative(7.69f, 7.69f, 0.0f, false, true, 2.11f, 0.56f);
        pathBuilderA.arcToRelative(1.0f, 1.0f, 0.0f, false, false, 1.0f, -0.07f);
        pathBuilderA.arcToRelative(1.0f, 1.0f, 0.0f, false, false, -0.17f, -1.76f);
        pathBuilderA.arcTo(10.0f, 10.0f, 0.0f, false, false, 3.35f, 19.0f);
        pathBuilderA.arcToRelative(2.0f, 2.0f, 0.0f, false, false, 1.72f, 1.0f);
        pathBuilderA.horizontalLineToRelative(13.85f);
        pathBuilderA.arcToRelative(2.0f, 2.0f, 0.0f, false, false, 1.74f, -1.0f);
        pathBuilderA.arcToRelative(10.0f, 10.0f, 0.0f, false, false, 0.55f, -8.89f);
        pathBuilderA.arcToRelative(1.0f, 1.0f, 0.0f, false, false, -1.75f, -0.11f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(10.59f, 12.59f);
        pathBuilderA2.arcToRelative(2.0f, 2.0f, 0.0f, false, false, 2.83f, 2.83f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderA2, 5.66f, -8.49f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _speed = imageVectorBuild;
        return imageVectorBuild;
    }
}
