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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_doneAll", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DoneAll", "Landroidx/compose/material/icons/Icons$Sharp;", "getDoneAll", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DoneAllKt {
    private static ImageVector _doneAll;

    public static final ImageVector getDoneAll(Icons.Sharp sharp) {
        ImageVector imageVector = _doneAll;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.DoneAll", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.0f, 7.0f, -1.41f, -1.41f);
        pathBuilderR.lineToRelative(-6.34f, 6.34f);
        d.C(pathBuilderR, 1.41f, 1.41f, 18.0f, 7.0f);
        pathBuilderR.moveTo(22.24f, 5.59f);
        pathBuilderR.lineTo(11.66f, 16.17f);
        pathBuilderR.lineTo(7.48f, 12.0f);
        pathBuilderR.lineToRelative(-1.41f, 1.41f);
        pathBuilderR.lineTo(11.66f, 19.0f);
        b.D(pathBuilderR, 12.0f, -12.0f, -1.42f, -1.41f);
        pathBuilderR.moveTo(0.41f, 13.41f);
        pathBuilderR.lineTo(6.0f, 19.0f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderR, 1.83f, 12.0f, 0.41f, 13.41f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _doneAll = imageVectorBuild;
        return imageVectorBuild;
    }
}
