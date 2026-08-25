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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_code", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Code", "Landroidx/compose/material/icons/Icons$Filled;", "getCode", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CodeKt {
    private static ImageVector _code;

    public static final ImageVector getCode(Icons.Filled filled) {
        ImageVector imageVector = _code;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Code", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(9.4f, 16.6f, 4.8f, 12.0f);
        pathBuilderJ.lineToRelative(4.6f, -4.6f);
        pathBuilderJ.lineTo(8.0f, 6.0f);
        pathBuilderJ.lineToRelative(-6.0f, 6.0f);
        b.D(pathBuilderJ, 6.0f, 6.0f, 1.4f, -1.4f);
        pathBuilderJ.moveTo(14.6f, 16.6f);
        pathBuilderJ.lineToRelative(4.6f, -4.6f);
        pathBuilderJ.lineToRelative(-4.6f, -4.6f);
        pathBuilderJ.lineTo(16.0f, 6.0f);
        pathBuilderJ.lineToRelative(6.0f, 6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderJ, -6.0f, 6.0f, -1.4f, -1.4f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _code = imageVectorBuild;
        return imageVectorBuild;
    }
}
