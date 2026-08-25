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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_searchOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SearchOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getSearchOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SearchOffKt {
    private static ImageVector _searchOff;

    public static final ImageVector getSearchOff(Icons.Rounded rounded) {
        ImageVector imageVector = _searchOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SearchOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(15.5f, 14.0f, -0.79f, -0.28f, -0.27f);
        pathBuilderG.curveToRelative(1.2f, -1.4f, 1.82f, -3.31f, 1.48f, -5.34f);
        pathBuilderG.curveToRelative(-0.47f, -2.78f, -2.79f, -4.99f, -5.58f, -5.34f);
        pathBuilderG.curveTo(6.54f, 2.58f, 3.3f, 5.38f, 3.03f, 9.0f);
        pathBuilderG.horizontalLineToRelative(2.02f);
        pathBuilderG.curveToRelative(0.24f, -2.12f, 1.92f, -3.8f, 4.06f, -3.98f);
        pathBuilderG.curveTo(11.65f, 4.8f, 14.0f, 6.95f, 14.0f, 9.5f);
        pathBuilderG.curveToRelative(0.0f, 2.49f, -2.01f, 4.5f, -4.5f, 4.5f);
        pathBuilderG.curveToRelative(-0.17f, 0.0f, -0.33f, -0.03f, -0.5f, -0.05f);
        pathBuilderG.lineToRelative(0.0f, 2.02f);
        pathBuilderG.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.01f, 0.01f);
        pathBuilderG.curveToRelative(1.8f, 0.13f, 3.47f, -0.47f, 4.72f, -1.55f);
        pathBuilderG.lineTo(14.0f, 14.71f);
        pathBuilderG.verticalLineToRelative(0.79f);
        pathBuilderG.lineToRelative(4.25f, 4.25f);
        pathBuilderG.curveToRelative(0.41f, 0.41f, 1.08f, 0.41f, 1.49f, 0.0f);
        pathBuilderG.lineToRelative(0.0f, 0.0f);
        pathBuilderG.curveToRelative(0.41f, -0.41f, 0.41f, -1.08f, 0.0f, -1.49f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderG, 15.5f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(6.12f, 11.17f, 4.0f, 13.29f);
        pathBuilderJ.lineToRelative(-2.12f, -2.12f);
        pathBuilderJ.curveToRelative(-0.2f, -0.2f, -0.51f, -0.2f, -0.71f, 0.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(-0.2f, 0.2f, -0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilderJ.lineTo(3.29f, 14.0f);
        pathBuilderJ.lineToRelative(-2.12f, 2.12f);
        pathBuilderJ.curveToRelative(-0.2f, 0.2f, -0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.2f, 0.2f, 0.51f, 0.2f, 0.71f, 0.0f);
        pathBuilderJ.lineTo(4.0f, 14.71f);
        pathBuilderJ.lineToRelative(2.12f, 2.12f);
        pathBuilderJ.curveToRelative(0.2f, 0.2f, 0.51f, 0.2f, 0.71f, 0.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.2f, -0.2f, 0.2f, -0.51f, 0.0f, -0.71f);
        pathBuilderJ.lineTo(4.71f, 14.0f);
        pathBuilderJ.lineToRelative(2.12f, -2.12f);
        pathBuilderJ.curveToRelative(0.2f, -0.2f, 0.2f, -0.51f, 0.0f, -0.71f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveTo(6.63f, 10.98f, 6.32f, 10.98f, 6.12f, 11.17f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _searchOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
