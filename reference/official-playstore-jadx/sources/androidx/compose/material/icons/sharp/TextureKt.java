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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_texture", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Texture", "Landroidx/compose/material/icons/Icons$Sharp;", "getTexture", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextureKt {
    private static ImageVector _texture;

    public static final ImageVector getTexture(Icons.Sharp sharp) {
        ImageVector imageVector = _texture;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Texture", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.66f, 3.0f, 3.07f, 19.59f);
        pathBuilderJ.lineTo(3.07f, 21.0f);
        pathBuilderJ.horizontalLineToRelative(1.41f);
        b.y(pathBuilderJ, 21.07f, 4.42f, 21.07f, 3.0f);
        pathBuilderJ.moveTo(11.95f, 3.0f);
        pathBuilderJ.lineToRelative(-8.88f, 8.88f);
        y.a.n(pathBuilderJ, 2.83f, 14.78f, 3.0f);
        pathBuilderJ.moveTo(3.07f, 3.0f);
        c.t(pathBuilderJ, 4.0f, 4.0f, -4.0f);
        pathBuilderJ.moveTo(21.07f, 21.0f);
        c.t(pathBuilderJ, -4.0f, -4.0f, 4.0f);
        pathBuilderJ.moveTo(12.19f, 21.0f);
        pathBuilderJ.lineToRelative(8.88f, -8.88f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderJ, 21.07f, 9.29f, 9.36f, 21.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _texture = imageVectorBuild;
        return imageVectorBuild;
    }
}
