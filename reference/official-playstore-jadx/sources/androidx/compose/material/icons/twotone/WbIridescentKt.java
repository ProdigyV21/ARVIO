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
import v.d;
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wbIridescent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WbIridescent", "Landroidx/compose/material/icons/Icons$TwoTone;", "getWbIridescent", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WbIridescentKt {
    private static ImageVector _wbIridescent;

    public static final ImageVector getWbIridescent(Icons.TwoTone twoTone) {
        ImageVector imageVector = _wbIridescent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.WbIridescent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.c(7.0f, 11.0f, 10.0f, 2.0f, 7.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(5.0f, 15.0f, 14.0f, 19.0f, 9.0f);
        d.f(pathBuilderI, 5.0f, 9.0f, 6.0f);
        v.a.C(pathBuilderI, 7.0f, 11.0f, 10.0f, 2.0f);
        d.f(pathBuilderI, 7.0f, 13.0f, -2.0f);
        v.a.C(pathBuilderI, 11.0f, 1.0f, 2.0f, 3.0f);
        f.x(pathBuilderI, -2.0f, 17.25f, 5.39f);
        pathBuilderI.lineToRelative(1.41f, 1.41f);
        b.D(pathBuilderI, 1.8f, -1.79f, -1.42f, -1.41f);
        v.a.C(pathBuilderI, 11.0f, 20.0f, 2.0f, 3.0f);
        f.x(pathBuilderI, -2.0f, 17.24f, 18.71f);
        pathBuilderI.lineToRelative(1.79f, 1.8f);
        b.D(pathBuilderI, 1.42f, -1.42f, -1.8f, -1.79f);
        pathBuilderI.moveTo(5.34f, 6.805f);
        pathBuilderI.lineToRelative(-1.788f, -1.79f);
        d.l(pathBuilderI, 4.96f, 3.61f, 1.788f, 1.788f);
        pathBuilderI.moveTo(3.55f, 19.08f);
        pathBuilderI.lineToRelative(1.41f, 1.42f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderI, 1.79f, -1.8f, -1.41f, -1.41f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wbIridescent = imageVectorBuild;
        return imageVectorBuild;
    }
}
