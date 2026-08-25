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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_textRotateUp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TextRotateUp", "Landroidx/compose/material/icons/Icons$Rounded;", "getTextRotateUp", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextRotateUpKt {
    private static ImageVector _textRotateUp;

    public static final ImageVector getTextRotateUp(Icons.Rounded rounded) {
        ImageVector imageVector = _textRotateUp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TextRotateUp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.35f, 4.35f);
        pathBuilderA.curveToRelative(-0.2f, -0.2f, -0.51f, -0.2f, -0.71f, 0.0f);
        pathBuilderA.lineToRelative(-1.79f, 1.79f);
        pathBuilderA.curveToRelative(-0.31f, 0.32f, -0.09f, 0.86f, 0.36f, 0.86f);
        pathBuilderA.horizontalLineTo(17.0f);
        pathBuilderA.verticalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineTo(7.0f);
        pathBuilderA.horizontalLineToRelative(0.79f);
        pathBuilderA.curveToRelative(0.45f, 0.0f, 0.67f, -0.54f, 0.35f, -0.85f);
        b.C(pathBuilderA, -1.79f, -1.8f, 11.8f, 15.5f);
        pathBuilderA.verticalLineToRelative(-5.0f);
        pathBuilderA.lineToRelative(1.6f, -0.66f);
        pathBuilderA.curveToRelative(0.36f, -0.14f, 0.6f, -0.49f, 0.6f, -0.88f);
        pathBuilderA.curveToRelative(0.0f, -0.69f, -0.71f, -1.15f, -1.34f, -0.88f);
        pathBuilderA.lineToRelative(-8.97f, 3.88f);
        pathBuilderA.curveToRelative(-0.42f, 0.17f, -0.69f, 0.58f, -0.69f, 1.04f);
        pathBuilderA.curveToRelative(0.0f, 0.46f, 0.27f, 0.87f, 0.69f, 1.05f);
        pathBuilderA.lineToRelative(8.97f, 3.88f);
        pathBuilderA.curveToRelative(0.63f, 0.27f, 1.34f, -0.2f, 1.34f, -0.89f);
        pathBuilderA.curveToRelative(0.0f, -0.39f, -0.24f, -0.74f, -0.6f, -0.89f);
        b.C(pathBuilderA, -1.6f, -0.65f, 4.98f, 13.0f);
        pathBuilderA.lineTo(10.0f, 11.13f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.j(pathBuilderA, 3.74f, 4.98f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _textRotateUp = imageVectorBuild;
        return imageVectorBuild;
    }
}
