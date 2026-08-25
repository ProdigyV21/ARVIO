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
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fastRewind", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FastRewind", "Landroidx/compose/material/icons/Icons$Outlined;", "getFastRewind", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FastRewindKt {
    private static ImageVector _fastRewind;

    public static final ImageVector getFastRewind(Icons.Outlined outlined) {
        ImageVector imageVector = _fastRewind;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.FastRewind", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = d.s(18.0f, 9.86f, 4.28f, 14.97f, 12.0f);
        pathBuilderS.lineTo(18.0f, 9.86f);
        pathBuilderS.moveToRelative(-9.0f, 0.0f);
        pathBuilderS.verticalLineToRelative(4.28f);
        pathBuilderS.lineTo(5.97f, 12.0f);
        pathBuilderS.lineTo(9.0f, 9.86f);
        pathBuilderS.moveTo(20.0f, 6.0f);
        pathBuilderS.lineToRelative(-8.5f, 6.0f);
        d.C(pathBuilderS, 8.5f, 6.0f, 20.0f, 6.0f);
        pathBuilderS.moveTo(11.0f, 6.0f);
        pathBuilderS.lineToRelative(-8.5f, 6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderS, 8.5f, 6.0f, 11.0f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fastRewind = imageVectorBuild;
        return imageVectorBuild;
    }
}
