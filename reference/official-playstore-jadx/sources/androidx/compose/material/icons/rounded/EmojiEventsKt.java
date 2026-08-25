package androidx.compose.material.icons.rounded;

import a0.b;
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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_emojiEvents", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EmojiEvents", "Landroidx/compose/material/icons/Icons$Rounded;", "getEmojiEvents", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EmojiEventsKt {
    private static ImageVector _emojiEvents;

    public static final ImageVector getEmojiEvents(Icons.Rounded rounded) {
        ImageVector imageVector = _emojiEvents;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.EmojiEvents", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = e.b(19.0f, 5.0f, -2.0f, 4.0f);
        pathBuilderB.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderB.horizontalLineTo(8.0f);
        pathBuilderB.curveTo(7.45f, 3.0f, 7.0f, 3.45f, 7.0f, 4.0f);
        pathBuilderB.verticalLineToRelative(1.0f);
        pathBuilderB.horizontalLineTo(5.0f);
        pathBuilderB.curveTo(3.9f, 5.0f, 3.0f, 5.9f, 3.0f, 7.0f);
        pathBuilderB.verticalLineToRelative(1.0f);
        pathBuilderB.curveToRelative(0.0f, 2.55f, 1.92f, 4.63f, 4.39f, 4.94f);
        pathBuilderB.curveToRelative(0.63f, 1.5f, 1.98f, 2.63f, 3.61f, 2.96f);
        pathBuilderB.verticalLineTo(19.0f);
        pathBuilderB.horizontalLineTo(8.0f);
        pathBuilderB.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderB.verticalLineToRelative(0.0f);
        pathBuilderB.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderB.horizontalLineToRelative(8.0f);
        pathBuilderB.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderB.verticalLineToRelative(0.0f);
        pathBuilderB.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderB.horizontalLineToRelative(-3.0f);
        pathBuilderB.verticalLineToRelative(-3.1f);
        pathBuilderB.curveToRelative(1.63f, -0.33f, 2.98f, -1.46f, 3.61f, -2.96f);
        pathBuilderB.curveTo(19.08f, 12.63f, 21.0f, 10.55f, 21.0f, 8.0f);
        pathBuilderB.verticalLineTo(7.0f);
        pathBuilderB.curveTo(21.0f, 5.9f, 20.1f, 5.0f, 19.0f, 5.0f);
        b.t(pathBuilderB, 5.0f, 8.0f, 7.0f, 2.0f);
        pathBuilderB.verticalLineToRelative(3.82f);
        pathBuilderB.curveTo(5.84f, 10.4f, 5.0f, 9.3f, 5.0f, 8.0f);
        pathBuilderB.close();
        pathBuilderB.moveTo(19.0f, 8.0f);
        pathBuilderB.curveToRelative(0.0f, 1.3f, -0.84f, 2.4f, -2.0f, 2.82f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.m(pathBuilderB, 7.0f, 2.0f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _emojiEvents = imageVectorBuild;
        return imageVectorBuild;
    }
}
