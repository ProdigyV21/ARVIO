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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bookmarks", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Bookmarks", "Landroidx/compose/material/icons/Icons$Outlined;", "getBookmarks", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BookmarksKt {
    private static ImageVector _bookmarks;

    public static final ImageVector getBookmarks(Icons.Outlined outlined) {
        ImageVector imageVector = _bookmarks;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Bookmarks", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = a.g(15.0f, 7.0f, 12.97f, -4.21f, -1.81f);
        pathBuilderG.lineToRelative(-0.79f, -0.34f);
        pathBuilderG.lineToRelative(-0.79f, 0.34f);
        pathBuilderG.lineTo(5.0f, 19.97f);
        pathBuilderG.lineTo(5.0f, 7.0f);
        pathBuilderG.horizontalLineToRelative(10.0f);
        pathBuilderG.moveToRelative(4.0f, -6.0f);
        pathBuilderG.lineTo(8.99f, 1.0f);
        pathBuilderG.curveTo(7.89f, 1.0f, 7.0f, 1.9f, 7.0f, 3.0f);
        pathBuilderG.horizontalLineToRelative(10.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderG.verticalLineToRelative(13.0f);
        pathBuilderG.lineToRelative(2.0f, 1.0f);
        pathBuilderG.lineTo(21.0f, 3.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        v.a.p(pathBuilderG, 15.0f, 5.0f, 5.0f, 5.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderG.verticalLineToRelative(16.0f);
        pathBuilderG.lineToRelative(7.0f, -3.0f);
        pathBuilderG.lineToRelative(7.0f, 3.0f);
        pathBuilderG.lineTo(17.0f, 7.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderG.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bookmarks = imageVectorBuild;
        return imageVectorBuild;
    }
}
