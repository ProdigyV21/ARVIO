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
import v.b;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hourglassDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HourglassDisabled", "Landroidx/compose/material/icons/Icons$Filled;", "getHourglassDisabled", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HourglassDisabledKt {
    private static ImageVector _hourglassDisabled;

    public static final ImageVector getHourglassDisabled(Icons.Filled filled) {
        ImageVector imageVector = _hourglassDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.HourglassDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(8.0f, 4.0f, 8.0f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, 3.5f);
        pathBuilderR.lineToRelative(-2.84f, 2.84f);
        pathBuilderR.lineToRelative(1.25f, 1.25f);
        pathBuilderR.lineToRelative(3.59f, -3.58f);
        pathBuilderR.lineToRelative(-0.01f, -0.01f);
        pathBuilderR.lineToRelative(0.01f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, -6.0f);
        pathBuilderR.lineToRelative(-12.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 0.0f, 1.17f, 2.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.1f, 2.1f, 0.69f, 3.51f);
        pathBuilderJ.lineToRelative(8.9f, 8.9f);
        pathBuilderJ.lineTo(6.0f, 16.0f);
        w.a.p(pathBuilderJ, 0.01f, 0.01f, 6.0f, 22.0f);
        f.y(pathBuilderJ, 12.0f, -1.17f, 2.49f, 2.49f);
        d.C(pathBuilderJ, 1.41f, -1.41f, 2.1f, 2.1f);
        a.A(pathBuilderJ, 16.0f, 20.0f, 8.0f, -3.5f);
        pathBuilderJ.lineToRelative(2.84f, -2.84f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.e(pathBuilderJ, 16.0f, 18.83f, 20.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hourglassDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
