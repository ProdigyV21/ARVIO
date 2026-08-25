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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_textRotateVertical", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TextRotateVertical", "Landroidx/compose/material/icons/Icons$Rounded;", "getTextRotateVertical", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextRotateVerticalKt {
    private static ImageVector _textRotateVertical;

    public static final ImageVector getTextRotateVertical(Icons.Rounded rounded) {
        ImageVector imageVector = _textRotateVertical;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TextRotateVertical", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.0f, 5.0f);
        pathBuilderA.curveToRelative(-0.46f, 0.0f, -0.87f, 0.27f, -1.05f, 0.69f);
        pathBuilderA.lineToRelative(-3.88f, 8.97f);
        pathBuilderA.curveToRelative(-0.27f, 0.63f, 0.2f, 1.34f, 0.89f, 1.34f);
        pathBuilderA.curveToRelative(0.39f, 0.0f, 0.74f, -0.24f, 0.89f, -0.6f);
        pathBuilderA.lineToRelative(0.66f, -1.6f);
        pathBuilderA.horizontalLineToRelative(5.0f);
        pathBuilderA.lineToRelative(0.66f, 1.6f);
        pathBuilderA.curveToRelative(0.15f, 0.36f, 0.5f, 0.6f, 0.89f, 0.6f);
        pathBuilderA.curveToRelative(0.69f, 0.0f, 1.15f, -0.71f, 0.88f, -1.34f);
        pathBuilderA.lineToRelative(-3.88f, -8.97f);
        pathBuilderA.curveTo(15.87f, 5.27f, 15.46f, 5.0f, 15.0f, 5.0f);
        a.p(pathBuilderA, 13.13f, 12.0f, 15.0f, 6.98f);
        f.n(pathBuilderA, 16.87f, 12.0f, -3.74f);
        pathBuilderA.moveTo(6.35f, 19.64f);
        pathBuilderA.lineToRelative(1.79f, -1.79f);
        pathBuilderA.curveToRelative(0.32f, -0.31f, 0.1f, -0.85f, -0.35f, -0.85f);
        pathBuilderA.lineTo(7.0f, 17.0f);
        pathBuilderA.lineTo(7.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.44f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(12.0f);
        pathBuilderA.horizontalLineToRelative(-0.79f);
        pathBuilderA.curveToRelative(-0.45f, 0.0f, -0.67f, 0.54f, -0.35f, 0.85f);
        pathBuilderA.lineToRelative(1.79f, 1.79f);
        pathBuilderA.curveToRelative(0.19f, 0.2f, 0.51f, 0.2f, 0.7f, 0.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _textRotateVertical = imageVectorBuild;
        return imageVectorBuild;
    }
}
