package androidx.compose.material.icons.rounded;

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
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bookmarkAdded", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BookmarkAdded", "Landroidx/compose/material/icons/Icons$Rounded;", "getBookmarkAdded", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BookmarkAddedKt {
    private static ImageVector _bookmarkAdded;

    public static final ImageVector getBookmarkAdded(Icons.Rounded rounded) {
        ImageVector imageVector = _bookmarkAdded;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.BookmarkAdded", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(5.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.lineToRelative(7.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.63f, 0.84f, -1.0f, 1.87f, -1.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, 2.76f, 2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.curveToRelative(0.34f, 0.0f, 0.68f, -0.03f, 1.0f, -0.1f);
        pathBuilderA.verticalLineToRelative(8.58f);
        pathBuilderA.curveToRelative(0.0f, 0.72f, -0.73f, 1.2f, -1.39f, 0.92f);
        pathBuilderA.lineTo(12.0f, 18.0f);
        pathBuilderA.lineToRelative(-5.61f, 2.4f);
        pathBuilderA.curveTo(5.73f, 20.69f, 5.0f, 20.2f, 5.0f, 19.48f);
        f.r(pathBuilderA, 5.0f, 22.07f, 3.34f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(-3.54f, 3.54f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderA.lineToRelative(-1.41f, -1.41f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(0.71f, 0.71f);
        pathBuilderA.lineToRelative(2.83f, -2.83f);
        pathBuilderA.curveTo(21.05f, 2.95f, 21.68f, 2.95f, 22.07f, 3.34f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bookmarkAdded = imageVectorBuild;
        return imageVectorBuild;
    }
}
