package androidx.compose.material.icons.twotone;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_assistant", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Assistant", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAssistant", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AssistantKt {
    private static ImageVector _assistant;

    public static final ImageVector getAssistant(Icons.TwoTone twoTone) {
        ImageVector imageVector = _assistant;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Assistant", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(9.83f, 18.0f, 0.59f, 0.59f);
        pathBuilderR.lineTo(12.0f, 20.17f);
        pathBuilderR.lineToRelative(1.59f, -1.59f);
        pathBuilderR.lineToRelative(0.58f, -0.58f);
        pathBuilderR.lineTo(19.0f, 18.0f);
        pathBuilderR.lineTo(19.0f, 4.0f);
        e.p(pathBuilderR, 5.0f, 4.0f, 14.0f, 4.83f);
        pathBuilderR.moveTo(10.12f, 9.12f);
        pathBuilderR.lineTo(12.0f, 5.0f);
        pathBuilderR.lineToRelative(1.88f, 4.12f);
        pathBuilderR.lineTo(18.0f, 11.0f);
        pathBuilderR.lineToRelative(-4.12f, 1.88f);
        pathBuilderR.lineTo(12.0f, 17.0f);
        pathBuilderR.lineToRelative(-1.88f, -4.12f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderR, 6.0f, 11.0f, 4.12f, -1.88f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(5.0f, 20.0f, 4.0f, 3.0f, 3.0f);
        pathBuilderG.lineToRelative(3.0f, -3.0f);
        pathBuilderG.horizontalLineToRelative(4.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.lineTo(21.0f, 4.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderG.lineTo(5.0f, 2.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderG.verticalLineToRelative(14.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        b.w(pathBuilderG, 5.0f, 4.0f, 14.0f, 14.0f);
        pathBuilderG.horizontalLineToRelative(-4.83f);
        pathBuilderG.lineToRelative(-0.59f, 0.59f);
        pathBuilderG.lineTo(12.0f, 20.17f);
        pathBuilderG.lineToRelative(-1.59f, -1.59f);
        pathBuilderG.lineToRelative(-0.58f, -0.58f);
        b.y(pathBuilderG, 5.0f, 18.0f, 5.0f, 4.0f);
        pathBuilderG.moveTo(12.0f, 17.0f);
        pathBuilderG.lineToRelative(1.88f, -4.12f);
        pathBuilderG.lineTo(18.0f, 11.0f);
        pathBuilderG.lineToRelative(-4.12f, -1.88f);
        pathBuilderG.lineTo(12.0f, 5.0f);
        pathBuilderG.lineToRelative(-1.88f, 4.12f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderG, 6.0f, 11.0f, 4.12f, 1.88f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _assistant = imageVectorBuild;
        return imageVectorBuild;
    }
}
