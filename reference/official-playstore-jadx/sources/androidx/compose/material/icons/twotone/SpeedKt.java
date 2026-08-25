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
import v.a;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_speed", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Speed", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSpeed", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SpeedKt {
    private static ImageVector _speed;

    public static final ImageVector getSpeed(Icons.TwoTone twoTone) {
        ImageVector imageVector = _speed;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Speed", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.38f, 8.57f, -1.23f, 1.85f);
        pathBuilderR.arcToRelative(8.0f, 8.0f, 0.0f, false, true, -0.22f, 7.58f);
        pathBuilderR.horizontalLineTo(5.07f);
        pathBuilderR.arcTo(8.0f, 8.0f, 0.0f, false, true, 15.58f, 6.85f);
        pathBuilderR.lineToRelative(1.85f, -1.23f);
        pathBuilderR.arcTo(10.0f, 10.0f, 0.0f, false, false, 3.35f, 19.0f);
        pathBuilderR.arcToRelative(2.0f, 2.0f, 0.0f, false, false, 1.72f, 1.0f);
        pathBuilderR.horizontalLineToRelative(13.85f);
        pathBuilderR.arcToRelative(2.0f, 2.0f, 0.0f, false, false, 1.74f, -1.0f);
        pathBuilderR.arcToRelative(10.0f, 10.0f, 0.0f, false, false, -0.27f, -10.44f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.59f, 15.41f);
        pathBuilderA.arcToRelative(2.0f, 2.0f, 0.0f, false, false, 2.83f, 0.0f);
        pathBuilderA.lineToRelative(5.66f, -8.49f);
        pathBuilderA.lineToRelative(-8.49f, 5.66f);
        pathBuilderA.arcToRelative(2.0f, 2.0f, 0.0f, false, false, 0.0f, 2.83f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _speed = imageVectorBuild;
        return imageVectorBuild;
    }
}
