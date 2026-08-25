package androidx.compose.material.icons.sharp;

import a0.a;
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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bookmarkRemove", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BookmarkRemove", "Landroidx/compose/material/icons/Icons$Sharp;", "getBookmarkRemove", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BookmarkRemoveKt {
    private static ImageVector _bookmarkRemove;

    public static final ImageVector getBookmarkRemove(Icons.Sharp sharp) {
        ImageVector imageVector = _bookmarkRemove;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.BookmarkRemove", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(21.0f, 7.0f, -6.0f, 5.0f, 6.0f);
        f.r(pathBuilderU, 7.0f, 19.0f, 10.9f);
        pathBuilderU.curveToRelative(-0.64f, 0.13f, -1.32f, 0.14f, -2.02f, 0.0f);
        pathBuilderU.curveToRelative(-1.91f, -0.38f, -3.47f, -1.92f, -3.87f, -3.83f);
        pathBuilderU.curveTo(12.79f, 5.54f, 13.18f, 4.1f, 14.0f, 3.0f);
        pathBuilderU.lineTo(5.0f, 3.0f);
        pathBuilderU.verticalLineToRelative(18.0f);
        pathBuilderU.lineToRelative(7.0f, -3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.d(pathBuilderU, 7.0f, 3.0f, 10.9f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bookmarkRemove = imageVectorBuild;
        return imageVectorBuild;
    }
}
