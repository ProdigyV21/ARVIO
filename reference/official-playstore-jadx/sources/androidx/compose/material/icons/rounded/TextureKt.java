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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_texture", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Texture", "Landroidx/compose/material/icons/Icons$Rounded;", "getTexture", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextureKt {
    private static ImageVector _texture;

    public static final ImageVector getTexture(Icons.Rounded rounded) {
        ImageVector imageVector = _texture;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Texture", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.58f, 3.08f, 3.15f, 19.51f);
        pathBuilderJ.curveToRelative(0.09f, 0.34f, 0.27f, 0.65f, 0.51f, 0.9f);
        pathBuilderJ.curveToRelative(0.25f, 0.24f, 0.56f, 0.42f, 0.9f, 0.51f);
        pathBuilderJ.lineTo(21.0f, 4.49f);
        pathBuilderJ.curveToRelative(-0.19f, -0.69f, -0.73f, -1.23f, -1.42f, -1.41f);
        a.z(pathBuilderJ, 11.95f, 3.0f, -8.88f, 8.88f);
        pathBuilderJ.verticalLineToRelative(2.83f);
        pathBuilderJ.lineTo(14.78f, 3.0f);
        pathBuilderJ.horizontalLineToRelative(-2.83f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(5.07f, 3.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.lineToRelative(4.0f, -4.0f);
        pathBuilderJ.horizontalLineToRelative(-2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(19.07f, 21.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.05f, -0.22f, 1.41f, -0.59f);
        pathBuilderJ.curveToRelative(0.37f, -0.36f, 0.59f, -0.86f, 0.59f, -1.41f);
        pathBuilderJ.verticalLineToRelative(-2.0f);
        pathBuilderJ.lineToRelative(-4.0f, 4.0f);
        pathBuilderJ.horizontalLineToRelative(2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(9.36f, 21.0f);
        pathBuilderJ.horizontalLineToRelative(2.83f);
        pathBuilderJ.lineToRelative(8.88f, -8.88f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderJ, 21.07f, 9.29f, 9.36f, 21.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _texture = imageVectorBuild;
        return imageVectorBuild;
    }
}
