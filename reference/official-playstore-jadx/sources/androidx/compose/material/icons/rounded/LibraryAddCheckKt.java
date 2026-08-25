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
import v.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_libraryAddCheck", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LibraryAddCheck", "Landroidx/compose/material/icons/Icons$Rounded;", "getLibraryAddCheck", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LibraryAddCheckKt {
    private static ImageVector _libraryAddCheck;

    public static final ImageVector getLibraryAddCheck(Icons.Rounded rounded) {
        ImageVector imageVector = _libraryAddCheck;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LibraryAddCheck", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 2.0f, 8.0f);
        pathBuilderQ.curveTo(6.9f, 2.0f, 6.0f, 2.9f, 6.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(4.0f);
        pathBuilderQ.curveTo(22.0f, 2.9f, 21.1f, 2.0f, 20.0f, 2.0f);
        a.p(pathBuilderQ, 11.76f, 13.28f, 9.69f, 11.2f);
        pathBuilderQ.curveToRelative(-0.38f, -0.39f, -0.38f, -1.01f, 0.0f, -1.4f);
        pathBuilderQ.lineToRelative(0.0f, 0.0f);
        pathBuilderQ.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderQ.lineToRelative(1.36f, 1.37f);
        pathBuilderQ.lineToRelative(4.42f, -4.46f);
        pathBuilderQ.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderQ.lineToRelative(0.0f, 0.0f);
        pathBuilderQ.curveToRelative(0.38f, 0.39f, 0.38f, 1.01f, 0.0f, 1.4f);
        pathBuilderQ.lineToRelative(-5.13f, 5.17f);
        pathBuilderQ.curveTo(12.79f, 13.68f, 12.15f, 13.68f, 11.76f, 13.28f);
        a.p(pathBuilderQ, 3.0f, 6.0f, 3.0f, 6.0f);
        pathBuilderQ.curveTo(2.45f, 6.0f, 2.0f, 6.45f, 2.0f, 7.0f);
        pathBuilderQ.verticalLineToRelative(13.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(13.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderQ.horizontalLineTo(5.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.verticalLineTo(7.0f);
        pathBuilderQ.curveTo(4.0f, 6.45f, 3.55f, 6.0f, 3.0f, 6.0f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _libraryAddCheck = imageVectorBuild;
        return imageVectorBuild;
    }
}
