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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_plagiarism", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Plagiarism", "Landroidx/compose/material/icons/Icons$Rounded;", "getPlagiarism", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PlagiarismKt {
    private static ImageVector _plagiarism;

    public static final ImageVector getPlagiarism(Icons.Rounded rounded) {
        ImageVector imageVector = _plagiarism;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Plagiarism", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.41f, 7.41f, -4.83f, -4.83f);
        pathBuilderR.curveTo(14.21f, 2.21f, 13.7f, 2.0f, 13.17f, 2.0f);
        pathBuilderR.horizontalLineTo(6.0f);
        pathBuilderR.curveTo(4.9f, 2.0f, 4.0f, 2.9f, 4.0f, 4.0f);
        pathBuilderR.verticalLineToRelative(16.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(12.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.verticalLineTo(8.83f);
        pathBuilderR.curveTo(20.0f, 8.3f, 19.79f, 7.79f, 19.41f, 7.41f);
        a.p(pathBuilderR, 15.74f, 18.74f, 15.74f, 18.74f);
        pathBuilderR.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderR.lineToRelative(-1.18f, -1.18f);
        pathBuilderR.curveToRelative(-1.33f, 0.71f, -3.01f, 0.53f, -4.13f, -0.59f);
        pathBuilderR.curveToRelative(-1.52f, -1.52f, -1.35f, -4.08f, 0.5f, -5.37f);
        pathBuilderR.curveToRelative(1.16f, -0.81f, 2.78f, -0.81f, 3.95f, 0.0f);
        pathBuilderR.curveToRelative(1.55f, 1.08f, 1.9f, 3.04f, 1.09f, 4.55f);
        pathBuilderR.lineToRelative(1.18f, 1.18f);
        pathBuilderR.curveTo(16.13f, 17.72f, 16.13f, 18.35f, 15.74f, 18.74f);
        pathBuilderR.close();
        pathBuilderR.moveTo(14.0f, 9.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.e(pathBuilderR, 3.5f, 18.5f, 9.0f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(11.5f, 14.5f, -1.5f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _plagiarism = imageVectorBuild;
        return imageVectorBuild;
    }
}
