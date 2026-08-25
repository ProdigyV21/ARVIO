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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_folderSpecial", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FolderSpecial", "Landroidx/compose/material/icons/Icons$Rounded;", "getFolderSpecial", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FolderSpecialKt {
    private static ImageVector _folderSpecial;

    public static final ImageVector getFolderSpecial(Icons.Rounded rounded) {
        ImageVector imageVector = _folderSpecial;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FolderSpecial", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(20.0f, 6.0f, -8.0f, -1.41f, -1.41f);
        pathBuilderG.curveTo(10.21f, 4.21f, 9.7f, 4.0f, 9.17f, 4.0f);
        pathBuilderG.lineTo(4.0f, 4.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderG.verticalLineToRelative(12.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(16.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.lineTo(22.0f, 8.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderG, 16.94f, 16.41f, 15.0f, 15.28f);
        pathBuilderG.lineToRelative(-1.94f, 1.13f);
        pathBuilderG.curveToRelative(-0.38f, 0.22f, -0.84f, -0.12f, -0.74f, -0.55f);
        pathBuilderG.lineToRelative(0.51f, -2.2f);
        pathBuilderG.lineToRelative(-1.69f, -1.46f);
        pathBuilderG.curveToRelative(-0.33f, -0.29f, -0.16f, -0.84f, 0.28f, -0.88f);
        pathBuilderG.lineToRelative(2.23f, -0.19f);
        pathBuilderG.lineToRelative(0.88f, -2.06f);
        pathBuilderG.curveToRelative(0.17f, -0.4f, 0.75f, -0.4f, 0.92f, 0.0f);
        pathBuilderG.lineToRelative(0.88f, 2.06f);
        pathBuilderG.lineToRelative(2.23f, 0.19f);
        pathBuilderG.curveToRelative(0.44f, 0.04f, 0.62f, 0.59f, 0.28f, 0.88f);
        pathBuilderG.lineToRelative(-1.69f, 1.46f);
        pathBuilderG.lineToRelative(0.51f, 2.2f);
        pathBuilderG.curveToRelative(0.11f, 0.43f, -0.35f, 0.77f, -0.72f, 0.55f);
        pathBuilderG.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _folderSpecial = imageVectorBuild;
        return imageVectorBuild;
    }
}
