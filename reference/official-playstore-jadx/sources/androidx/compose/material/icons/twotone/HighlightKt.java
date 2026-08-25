package androidx.compose.material.icons.twotone;

import a0.b;
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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_highlight", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Highlight", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHighlight", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HighlightKt {
    private static ImageVector _highlight;

    public static final ImageVector getHighlight(Icons.TwoTone twoTone) {
        ImageVector imageVector = _highlight;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Highlight", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(11.0f, 20.0f, 2.0f, -3.83f);
        b.m(pathBuilderA, 3.0f, -3.0f, 11.0f, 8.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.s(pathBuilderA, 2.17f, 3.0f, 3.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = e.s(6.0f, 14.0f, 3.0f, 3.0f, 5.0f);
        f.y(pathBuilderS, 6.0f, -5.0f, 3.0f, -3.0f);
        pathBuilderS.lineTo(18.0f, 9.0f);
        d.f(pathBuilderS, 6.0f, 9.0f, 5.0f);
        a.C(pathBuilderS, 8.0f, 11.0f, 8.0f, 2.17f);
        pathBuilderS.lineToRelative(-3.0f, 3.0f);
        v.b.A(pathBuilderS, 13.0f, 20.0f, -2.0f, -3.83f);
        d.C(pathBuilderS, -3.0f, -3.0f, 8.0f, 11.0f);
        a.C(pathBuilderS, 11.0f, 2.0f, 2.0f, 3.0f);
        f.x(pathBuilderS, -2.0f, 4.916f, 4.464f);
        pathBuilderS.lineToRelative(2.12f, 2.122f);
        v.b.y(pathBuilderS, 5.62f, 8.0f, 3.5f, 5.877f);
        pathBuilderS.moveTo(18.372f, 8.0f);
        pathBuilderS.lineToRelative(-1.414f, -1.414f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.t(pathBuilderS, 2.12f, -2.12f, 1.415f, 1.413f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _highlight = imageVectorBuild;
        return imageVectorBuild;
    }
}
