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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_textRotationNone", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TextRotationNone", "Landroidx/compose/material/icons/Icons$Rounded;", "getTextRotationNone", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextRotationNoneKt {
    private static ImageVector _textRotationNone;

    public static final ImageVector getTextRotationNone(Icons.Rounded rounded) {
        ImageVector imageVector = _textRotationNone;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TextRotationNone", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.65f, 17.65f, -1.79f, -1.79f);
        pathBuilderR.curveToRelative(-0.32f, -0.32f, -0.86f, -0.1f, -0.86f, 0.35f);
        pathBuilderR.verticalLineTo(17.0f);
        pathBuilderR.horizontalLineTo(6.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderR.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(12.0f);
        pathBuilderR.verticalLineToRelative(0.79f);
        pathBuilderR.curveToRelative(0.0f, 0.45f, 0.54f, 0.67f, 0.85f, 0.35f);
        pathBuilderR.lineToRelative(1.79f, -1.79f);
        pathBuilderR.curveToRelative(0.2f, -0.19f, 0.2f, -0.51f, 0.01f, -0.7f);
        a.g(pathBuilderR, 9.5f, 11.8f, 5.0f);
        pathBuilderR.lineToRelative(0.66f, 1.6f);
        pathBuilderR.curveToRelative(0.15f, 0.36f, 0.5f, 0.6f, 0.89f, 0.6f);
        pathBuilderR.curveToRelative(0.69f, 0.0f, 1.15f, -0.71f, 0.88f, -1.34f);
        pathBuilderR.lineToRelative(-3.88f, -8.97f);
        pathBuilderR.curveTo(12.87f, 3.27f, 12.46f, 3.0f, 12.0f, 3.0f);
        pathBuilderR.curveToRelative(-0.46f, 0.0f, -0.87f, 0.27f, -1.05f, 0.69f);
        pathBuilderR.lineToRelative(-3.88f, 8.97f);
        pathBuilderR.curveToRelative(-0.27f, 0.63f, 0.2f, 1.34f, 0.89f, 1.34f);
        pathBuilderR.curveToRelative(0.39f, 0.0f, 0.74f, -0.24f, 0.89f, -0.6f);
        b.C(pathBuilderR, 0.65f, -1.6f, 12.0f, 4.98f);
        pathBuilderR.lineTo(13.87f, 10.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.h(pathBuilderR, -3.74f, 12.0f, 4.98f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _textRotationNone = imageVectorBuild;
        return imageVectorBuild;
    }
}
