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
import v.b;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_snippetFolder", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SnippetFolder", "Landroidx/compose/material/icons/Icons$Rounded;", "getSnippetFolder", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SnippetFolderKt {
    private static ImageVector _snippetFolder;

    public static final ImageVector getSnippetFolder(Icons.Rounded rounded) {
        ImageVector imageVector = _snippetFolder;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SnippetFolder", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = e.s(15.88f, 10.5f, 1.62f, 1.62f, 3.38f);
        pathBuilderS.lineToRelative(-3.0f, 0.0f);
        pathBuilderS.verticalLineToRelative(-5.0f);
        pathBuilderS.horizontalLineTo(15.88f);
        pathBuilderS.close();
        pathBuilderS.moveTo(22.0f, 8.0f);
        pathBuilderS.verticalLineToRelative(10.0f);
        pathBuilderS.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderS.horizontalLineTo(4.0f);
        pathBuilderS.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderS.lineTo(2.01f, 6.0f);
        pathBuilderS.curveTo(2.01f, 4.9f, 2.9f, 4.0f, 4.0f, 4.0f);
        pathBuilderS.horizontalLineToRelative(5.17f);
        pathBuilderS.curveToRelative(0.53f, 0.0f, 1.04f, 0.21f, 1.41f, 0.59f);
        pathBuilderS.lineTo(12.0f, 6.0f);
        pathBuilderS.horizontalLineToRelative(8.0f);
        pathBuilderS.curveTo(21.1f, 6.0f, 22.0f, 6.9f, 22.0f, 8.0f);
        pathBuilderS.close();
        pathBuilderS.moveTo(19.0f, 11.91f);
        pathBuilderS.curveToRelative(0.0f, -0.27f, -0.11f, -0.52f, -0.29f, -0.71f);
        pathBuilderS.lineToRelative(-1.91f, -1.91f);
        pathBuilderS.curveTo(16.61f, 9.11f, 16.35f, 9.0f, 16.09f, 9.0f);
        pathBuilderS.horizontalLineTo(14.0f);
        pathBuilderS.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderS.verticalLineToRelative(6.0f);
        pathBuilderS.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderS.lineToRelative(4.0f, 0.0f);
        pathBuilderS.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderS, 11.91f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _snippetFolder = imageVectorBuild;
        return imageVectorBuild;
    }
}
