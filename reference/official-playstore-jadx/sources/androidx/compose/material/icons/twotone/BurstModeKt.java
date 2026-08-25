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
import v.b;
import v.c;
import v.d;
import v.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_burstMode", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BurstMode", "Landroidx/compose/material/icons/Icons$TwoTone;", "getBurstMode", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BurstModeKt {
    private static ImageVector _burstMode;

    public static final ImageVector getBurstMode(Icons.TwoTone twoTone) {
        ImageVector imageVector = _burstMode;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.BurstMode", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(11.0f, 17.0f, 10.0f, 21.0f, 7.0f);
        d.f(pathBuilderI, 11.0f, 7.0f, 10.0f);
        pathBuilderI.moveTo(14.0f, 13.47f);
        pathBuilderI.lineToRelative(1.43f, 1.72f);
        pathBuilderI.lineToRelative(2.0f, -2.58f);
        pathBuilderI.lineTo(20.0f, 15.99f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderI, -8.0f, 2.0f, -2.52f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(1.0f, 5.0f, 2.0f, 14.0f);
        c.z(pathBuilderA, 1.0f, 19.0f, 5.0f, 5.0f);
        d.m(pathBuilderA, 2.0f, 14.0f, 5.0f, 19.0f);
        pathBuilderA.moveTo(22.0f, 5.0f);
        pathBuilderA.lineTo(10.0f, 5.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.lineTo(23.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        v.a.p(pathBuilderA, 21.0f, 17.0f, 11.0f, 17.0f);
        b.g(pathBuilderA, 11.0f, 7.0f, 10.0f, 10.0f);
        pathBuilderA.moveTo(17.43f, 12.62f);
        pathBuilderA.lineToRelative(-2.0f, 2.57f);
        pathBuilderA.lineTo(14.0f, 13.47f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.g(pathBuilderA, -2.0f, 2.52f, 8.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _burstMode = imageVectorBuild;
        return imageVectorBuild;
    }
}
