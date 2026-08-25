package androidx.compose.material.icons.filled;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_mobiledataOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MobiledataOff", "Landroidx/compose/material/icons/Icons$Filled;", "getMobiledataOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MobiledataOffKt {
    private static ImageVector _mobiledataOff;

    public static final ImageVector getMobiledataOff(Icons.Filled filled) {
        ImageVector imageVector = _mobiledataOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.MobiledataOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(16.0f, 7.0f, 3.0f, 0.0f);
        pathBuilderR.lineToRelative(-4.0f, -4.0f);
        pathBuilderR.lineToRelative(-4.0f, 4.0f);
        pathBuilderR.lineToRelative(3.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 0.0f, 4.17f, 2.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(2.81f, 2.81f, -1.42f, 1.41f);
        pathBuilderR2.lineToRelative(6.61f, 6.61f);
        pathBuilderR2.lineToRelative(0.0f, 6.18f);
        pathBuilderR2.lineToRelative(-3.0f, 0.01f);
        pathBuilderR2.lineToRelative(4.0f, 3.98f);
        pathBuilderR2.lineToRelative(4.0f, -4.0f);
        pathBuilderR2.lineToRelative(-3.0f, 0.01f);
        pathBuilderR2.lineToRelative(0.0f, -4.18f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR2, 9.78f, 9.78f, 1.41f, -1.42f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _mobiledataOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
