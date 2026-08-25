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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_highlight", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Highlight", "Landroidx/compose/material/icons/Icons$Outlined;", "getHighlight", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HighlightKt {
    private static ImageVector _highlight;

    public static final ImageVector getHighlight(Icons.Outlined outlined) {
        ImageVector imageVector = _highlight;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Highlight", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = e.s(6.0f, 14.0f, 3.0f, 3.0f, 5.0f);
        f.y(pathBuilderS, 6.0f, -5.0f, 3.0f, -3.0f);
        pathBuilderS.lineTo(18.0f, 9.0f);
        d.f(pathBuilderS, 6.0f, 9.0f, 5.0f);
        a.C(pathBuilderS, 8.0f, 11.0f, 8.0f, 2.17f);
        pathBuilderS.lineToRelative(-3.0f, 3.0f);
        b.A(pathBuilderS, 13.0f, 20.0f, -2.0f, -3.83f);
        d.C(pathBuilderS, -3.0f, -3.0f, 8.0f, 11.0f);
        a.C(pathBuilderS, 11.0f, 2.0f, 2.0f, 3.0f);
        f.x(pathBuilderS, -2.0f, 3.502f, 5.874f);
        pathBuilderS.lineTo(4.916f, 4.46f);
        b.D(pathBuilderS, 2.122f, 2.12f, -1.414f, 1.415f);
        pathBuilderS.moveTo(16.96f, 6.582f);
        pathBuilderS.lineToRelative(2.123f, -2.12f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderS, 1.413f, 1.416f, -2.123f, 2.12f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _highlight = imageVectorBuild;
        return imageVectorBuild;
    }
}
