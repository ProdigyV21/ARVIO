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
import v.b;
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_emojiObjects", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EmojiObjects", "Landroidx/compose/material/icons/Icons$Sharp;", "getEmojiObjects", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EmojiObjectsKt {
    private static ImageVector _emojiObjects;

    public static final ImageVector getEmojiObjects(Icons.Sharp sharp) {
        ImageVector imageVector = _emojiObjects;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.EmojiObjects", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 3.0f);
        pathBuilderA.curveToRelative(-0.42f, 0.0f, -0.85f, 0.04f, -1.28f, 0.11f);
        pathBuilderA.curveToRelative(-2.81f, 0.5f, -5.08f, 2.75f, -5.6f, 5.55f);
        pathBuilderA.curveToRelative(-0.48f, 2.61f, 0.48f, 5.01f, 2.22f, 6.56f);
        pathBuilderA.curveTo(7.77f, 15.6f, 8.0f, 16.13f, 8.0f, 16.69f);
        pathBuilderA.curveTo(8.0f, 18.21f, 8.0f, 21.0f, 8.0f, 21.0f);
        pathBuilderA.horizontalLineToRelative(2.28f);
        pathBuilderA.curveToRelative(0.35f, 0.6f, 0.98f, 1.0f, 1.72f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.38f, -0.4f, 1.72f, -1.0f);
        pathBuilderA.horizontalLineTo(16.0f);
        pathBuilderA.verticalLineToRelative(-4.31f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.22f, -1.09f, 0.64f, -1.46f);
        pathBuilderA.curveTo(18.09f, 13.95f, 19.0f, 12.08f, 19.0f, 10.0f);
        pathBuilderA.curveTo(19.0f, 6.13f, 15.87f, 3.0f, 12.0f, 3.0f);
        b.w(pathBuilderA, 14.0f, 19.0f, -4.0f, -1.0f);
        c.D(pathBuilderA, 4.0f, 19.0f, 14.0f, 17.0f);
        f.w(pathBuilderA, -4.0f, -1.0f, 4.0f, 17.0f);
        e.o(pathBuilderA, 12.5f, 11.41f, 14.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-2.59f);
        pathBuilderA.lineTo(9.67f, 9.59f);
        pathBuilderA.lineToRelative(0.71f, -0.71f);
        pathBuilderA.lineTo(12.0f, 10.5f);
        pathBuilderA.lineToRelative(1.62f, -1.62f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 0.71f, 0.71f, 12.5f, 11.41f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _emojiObjects = imageVectorBuild;
        return imageVectorBuild;
    }
}
