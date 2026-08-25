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
import v.a;
import v.b;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wbIridescent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WbIridescent", "Landroidx/compose/material/icons/Icons$Outlined;", "getWbIridescent", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WbIridescentKt {
    private static ImageVector _wbIridescent;

    public static final ImageVector getWbIridescent(Icons.Outlined outlined) {
        ImageVector imageVector = _wbIridescent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.WbIridescent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(5.0f, 15.0f, 14.0f, 19.0f, 9.0f);
        d.f(pathBuilderI, 5.0f, 9.0f, 6.0f);
        a.C(pathBuilderI, 7.0f, 11.0f, 10.0f, 2.0f);
        d.f(pathBuilderI, 7.0f, 13.0f, -2.0f);
        a.C(pathBuilderI, 11.0f, 1.0f, 2.0f, 3.0f);
        f.x(pathBuilderI, -2.0f, 20.46f, 5.01f);
        pathBuilderI.lineTo(19.04f, 3.6f);
        b.D(pathBuilderI, -1.79f, 1.79f, 1.41f, 1.41f);
        a.C(pathBuilderI, 11.0f, 20.0f, 2.0f, 3.0f);
        f.x(pathBuilderI, -2.0f, 17.24f, 18.71f);
        pathBuilderI.lineToRelative(1.79f, 1.8f);
        b.D(pathBuilderI, 1.42f, -1.42f, -1.8f, -1.79f);
        pathBuilderI.moveTo(4.96f, 3.595f);
        pathBuilderI.lineToRelative(1.788f, 1.79f);
        b.y(pathBuilderI, 5.34f, 6.79f, 3.553f, 5.003f);
        pathBuilderI.moveTo(3.55f, 19.08f);
        pathBuilderI.lineToRelative(1.41f, 1.42f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderI, 1.79f, -1.8f, -1.41f, -1.41f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wbIridescent = imageVectorBuild;
        return imageVectorBuild;
    }
}
