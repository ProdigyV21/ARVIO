package androidx.compose.material.icons.sharp;

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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_textRotationAngleup", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TextRotationAngleup", "Landroidx/compose/material/icons/Icons$Sharp;", "getTextRotationAngleup", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextRotationAngleupKt {
    private static ImageVector _textRotationAngleup;

    public static final ImageVector getTextRotationAngleup(Icons.Sharp sharp) {
        ImageVector imageVector = _textRotationAngleup;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.TextRotationAngleup", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(16.76f, 9.0f, 1.41f, 1.41f);
        pathBuilderR.lineToRelative(-9.19f, 9.19f);
        pathBuilderR.lineToRelative(1.41f, 1.41f);
        pathBuilderR.lineToRelative(9.19f, -9.19f);
        pathBuilderR.lineTo(21.0f, 13.24f);
        f.n(pathBuilderR, 21.0f, 9.0f, -4.24f);
        pathBuilderR.moveTo(8.48f, 12.75f);
        pathBuilderR.lineToRelative(3.54f, -3.54f);
        pathBuilderR.lineToRelative(2.19f, 0.92f);
        pathBuilderR.lineToRelative(1.48f, -1.48f);
        pathBuilderR.lineTo(4.56f, 4.23f);
        pathBuilderR.lineTo(3.5f, 5.29f);
        pathBuilderR.lineToRelative(4.42f, 11.14f);
        b.D(pathBuilderR, 1.48f, -1.48f, -0.92f, -2.2f);
        pathBuilderR.moveTo(7.66f, 11.03f);
        pathBuilderR.lineTo(5.43f, 6.16f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 4.87f, 2.23f, -2.64f, 2.64f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _textRotationAngleup = imageVectorBuild;
        return imageVectorBuild;
    }
}
