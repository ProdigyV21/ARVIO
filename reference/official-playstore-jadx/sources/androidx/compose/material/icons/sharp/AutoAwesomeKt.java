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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_autoAwesome", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AutoAwesome", "Landroidx/compose/material/icons/Icons$Sharp;", "getAutoAwesome", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AutoAwesomeKt {
    private static ImageVector _autoAwesome;

    public static final ImageVector getAutoAwesome(Icons.Sharp sharp) {
        ImageVector imageVector = _autoAwesome;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.AutoAwesome", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.0f, 9.0f, 1.25f, -2.75f);
        pathBuilderR.lineTo(23.0f, 5.0f);
        pathBuilderR.lineToRelative(-2.75f, -1.25f);
        pathBuilderR.lineTo(19.0f, 1.0f);
        pathBuilderR.lineToRelative(-1.25f, 2.75f);
        pathBuilderR.lineTo(15.0f, 5.0f);
        d.C(pathBuilderR, 2.75f, 1.25f, 19.0f, 9.0f);
        pathBuilderR.moveTo(11.5f, 9.5f);
        pathBuilderR.lineTo(9.0f, 4.0f);
        pathBuilderR.lineTo(6.5f, 9.5f);
        pathBuilderR.lineTo(1.0f, 12.0f);
        pathBuilderR.lineToRelative(5.5f, 2.5f);
        pathBuilderR.lineTo(9.0f, 20.0f);
        pathBuilderR.lineToRelative(2.5f, -5.5f);
        b.y(pathBuilderR, 17.0f, 12.0f, 11.5f, 9.5f);
        pathBuilderR.moveTo(19.0f, 15.0f);
        pathBuilderR.lineToRelative(-1.25f, 2.75f);
        pathBuilderR.lineTo(15.0f, 19.0f);
        pathBuilderR.lineToRelative(2.75f, 1.25f);
        pathBuilderR.lineTo(19.0f, 23.0f);
        pathBuilderR.lineToRelative(1.25f, -2.75f);
        pathBuilderR.lineTo(23.0f, 19.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, -2.75f, -1.25f, 19.0f, 15.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _autoAwesome = imageVectorBuild;
        return imageVectorBuild;
    }
}
