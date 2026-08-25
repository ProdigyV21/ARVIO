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
import v.b;
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wbIridescent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WbIridescent", "Landroidx/compose/material/icons/Icons$Sharp;", "getWbIridescent", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WbIridescentKt {
    private static ImageVector _wbIridescent;

    public static final ImageVector getWbIridescent(Icons.Sharp sharp) {
        ImageVector imageVector = _wbIridescent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.WbIridescent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(5.0f, 15.0f, 14.0f, 19.0f, 9.05f);
        b.y(pathBuilderI, 5.0f, 9.05f, 5.0f, 15.0f);
        c.m(pathBuilderI, 11.0f, 1.0f, 3.0f, 2.0f);
        f.n(pathBuilderI, 13.0f, 1.0f, -2.0f);
        pathBuilderI.moveTo(19.04f, 3.6f);
        pathBuilderI.lineToRelative(-1.79f, 1.79f);
        pathBuilderI.lineToRelative(1.41f, 1.41f);
        b.D(pathBuilderI, 1.8f, -1.79f, -1.42f, -1.41f);
        c.m(pathBuilderI, 13.0f, 23.0f, -2.95f, -2.0f);
        f.n(pathBuilderI, 11.0f, 23.0f, 2.0f);
        pathBuilderI.moveTo(20.45f, 19.09f);
        pathBuilderI.lineToRelative(-1.8f, -1.79f);
        pathBuilderI.lineToRelative(-1.41f, 1.41f);
        b.D(pathBuilderI, 1.79f, 1.8f, 1.42f, -1.42f);
        pathBuilderI.moveTo(3.55f, 5.01f);
        pathBuilderI.lineTo(5.34f, 6.8f);
        pathBuilderI.lineToRelative(1.41f, -1.41f);
        b.y(pathBuilderI, 4.96f, 3.6f, 3.55f, 5.01f);
        pathBuilderI.moveTo(4.96f, 20.5f);
        pathBuilderI.lineToRelative(1.79f, -1.8f);
        pathBuilderI.lineToRelative(-1.41f, -1.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderI, -1.79f, 1.79f, 1.41f, 1.42f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wbIridescent = imageVectorBuild;
        return imageVectorBuild;
    }
}
