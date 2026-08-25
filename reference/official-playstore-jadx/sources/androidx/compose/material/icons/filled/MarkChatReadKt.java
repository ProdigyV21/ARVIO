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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_markChatRead", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MarkChatRead", "Landroidx/compose/material/icons/Icons$Filled;", "getMarkChatRead", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MarkChatReadKt {
    private static ImageVector _markChatRead;

    public static final ImageVector getMarkChatRead(Icons.Filled filled) {
        ImageVector imageVector = _markChatRead;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.MarkChatRead", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(17.34f, 20.0f, -3.54f, -3.54f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        pathBuilderR.lineToRelative(2.12f, 2.12f);
        pathBuilderR.lineToRelative(4.24f, -4.24f);
        b.y(pathBuilderR, 23.0f, 14.34f, 17.34f, 20.0f);
        pathBuilderR.moveTo(12.0f, 17.0f);
        pathBuilderR.curveToRelative(0.0f, -3.87f, 3.13f, -7.0f, 7.0f, -7.0f);
        pathBuilderR.curveToRelative(1.08f, 0.0f, 2.09f, 0.25f, 3.0f, 0.68f);
        pathBuilderR.verticalLineTo(4.0f);
        pathBuilderR.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderR.horizontalLineTo(4.0f);
        pathBuilderR.curveTo(2.9f, 2.0f, 2.0f, 2.9f, 2.0f, 4.0f);
        f.q(pathBuilderR, 18.0f, 4.0f, -4.0f, 6.0f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.0f, -0.17f, 0.01f, -0.33f, 0.03f, -0.5f);
        pathBuilderR.curveTo(12.01f, 17.34f, 12.0f, 17.17f, 12.0f, 17.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _markChatRead = imageVectorBuild;
        return imageVectorBuild;
    }
}
