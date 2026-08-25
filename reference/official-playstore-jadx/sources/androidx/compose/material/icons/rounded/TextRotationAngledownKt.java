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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_textRotationAngledown", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TextRotationAngledown", "Landroidx/compose/material/icons/Icons$Rounded;", "getTextRotationAngledown", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextRotationAngledownKt {
    private static ImageVector _textRotationAngledown;

    public static final ImageVector getTextRotationAngledown(Icons.Rounded rounded) {
        ImageVector imageVector = _textRotationAngledown;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TextRotationAngledown", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(15.0f, 20.5f, -2.54f);
        pathBuilderI.curveToRelative(0.0f, -0.45f, -0.54f, -0.67f, -0.85f, -0.35f);
        pathBuilderI.lineToRelative(-0.56f, 0.56f);
        pathBuilderI.lineTo(5.1f, 9.68f);
        pathBuilderI.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderI.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderI.lineToRelative(8.49f, 8.49f);
        pathBuilderI.lineToRelative(-0.56f, 0.56f);
        pathBuilderI.curveToRelative(-0.32f, 0.32f, -0.1f, 0.86f, 0.34f, 0.86f);
        pathBuilderI.horizontalLineToRelative(2.54f);
        pathBuilderI.curveToRelative(0.28f, 0.0f, 0.5f, -0.23f, 0.5f, -0.5f);
        a.z(pathBuilderI, 11.25f, 8.48f, 3.54f, 3.54f);
        pathBuilderI.lineToRelative(-0.67f, 1.6f);
        pathBuilderI.curveToRelative(-0.15f, 0.36f, -0.07f, 0.77f, 0.21f, 1.05f);
        pathBuilderI.curveToRelative(0.49f, 0.49f, 1.31f, 0.32f, 1.57f, -0.32f);
        pathBuilderI.lineToRelative(3.61f, -9.09f);
        pathBuilderI.curveToRelative(0.17f, -0.42f, 0.07f, -0.91f, -0.25f, -1.23f);
        pathBuilderI.curveToRelative(-0.32f, -0.32f, -0.8f, -0.42f, -1.23f, -0.25f);
        pathBuilderI.lineToRelative(-9.1f, 3.6f);
        pathBuilderI.curveToRelative(-0.64f, 0.25f, -0.81f, 1.08f, -0.32f, 1.57f);
        pathBuilderI.curveToRelative(0.27f, 0.27f, 0.68f, 0.35f, 1.04f, 0.2f);
        b.C(pathBuilderI, 1.6f, -0.67f, 17.84f, 5.43f);
        pathBuilderI.lineToRelative(-2.23f, 4.87f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderI, -2.64f, -2.64f, 4.87f, -2.23f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _textRotationAngledown = imageVectorBuild;
        return imageVectorBuild;
    }
}
