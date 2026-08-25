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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pages", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Pages", "Landroidx/compose/material/icons/Icons$Sharp;", "getPages", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PagesKt {
    private static ImageVector _pages;

    public static final ImageVector getPages(Icons.Sharp sharp) {
        ImageVector imageVector = _pages;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Pages", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = b.a(3.0f, 3.0f, 8.0f, 5.0f);
        pathBuilderA.lineTo(7.0f, 7.0f);
        pathBuilderA.lineToRelative(4.0f, 1.0f);
        b.y(pathBuilderA, 11.0f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.moveTo(8.0f, 13.0f);
        a.h(pathBuilderA, 3.0f, 13.0f, 8.0f, 8.0f);
        pathBuilderA.verticalLineToRelative(-5.0f);
        b.D(pathBuilderA, -4.0f, 1.0f, 1.0f, -4.0f);
        pathBuilderA.moveTo(17.0f, 17.0f);
        b.u(pathBuilderA, -4.0f, -1.0f, 5.0f, 8.0f);
        pathBuilderA.verticalLineToRelative(-8.0f);
        pathBuilderA.horizontalLineToRelative(-5.0f);
        pathBuilderA.lineToRelative(1.0f, 4.0f);
        pathBuilderA.close();
        a.C(pathBuilderA, 21.0f, 3.0f, -8.0f, 5.0f);
        pathBuilderA.lineToRelative(4.0f, -1.0f);
        pathBuilderA.lineToRelative(-1.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.h(pathBuilderA, 5.0f, 21.0f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pages = imageVectorBuild;
        return imageVectorBuild;
    }
}
