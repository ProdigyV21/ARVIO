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
import v.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_speed", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Speed", "Landroidx/compose/material/icons/Icons$Filled;", "getSpeed", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SpeedKt {
    private static ImageVector _speed;

    public static final ImageVector getSpeed(Icons.Filled filled) {
        ImageVector imageVector = _speed;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Speed", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.38f, 8.57f, -1.23f, 1.85f);
        pathBuilderR.arcToRelative(8.0f, 8.0f, 0.0f, false, true, -0.22f, 7.58f);
        pathBuilderR.lineTo(5.07f, 18.0f);
        pathBuilderR.arcTo(8.0f, 8.0f, 0.0f, false, true, 15.58f, 6.85f);
        pathBuilderR.lineToRelative(1.85f, -1.23f);
        pathBuilderR.arcTo(10.0f, 10.0f, 0.0f, false, false, 3.35f, 19.0f);
        pathBuilderR.arcToRelative(2.0f, 2.0f, 0.0f, false, false, 1.72f, 1.0f);
        pathBuilderR.horizontalLineToRelative(13.85f);
        pathBuilderR.arcToRelative(2.0f, 2.0f, 0.0f, false, false, 1.74f, -1.0f);
        pathBuilderR.arcToRelative(10.0f, 10.0f, 0.0f, false, false, -0.27f, -10.44f);
        pathBuilderR.close();
        pathBuilderR.moveTo(10.59f, 15.41f);
        pathBuilderR.arcToRelative(2.0f, 2.0f, 0.0f, false, false, 2.83f, 0.0f);
        pathBuilderR.lineToRelative(5.66f, -8.49f);
        pathBuilderR.lineToRelative(-8.49f, 5.66f);
        pathBuilderR.arcToRelative(2.0f, 2.0f, 0.0f, false, false, 0.0f, 2.83f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _speed = imageVectorBuild;
        return imageVectorBuild;
    }
}
