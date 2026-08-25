package androidx.compose.material.icons.automirrored.filled;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_input", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Input", "Landroidx/compose/material/icons/Icons$AutoMirrored$Filled;", "getInput", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InputKt {
    private static ImageVector _input;

    public static final ImageVector getInput(Icons.AutoMirrored.Filled filled) {
        ImageVector imageVector = _input;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Filled.Input", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(21.0f, 3.01f, 3.0f);
        pathBuilderQ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        d.A(pathBuilderQ, 9.0f, 2.0f, 4.99f, 18.0f);
        d.B(pathBuilderQ, 14.03f, 3.0f, 15.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(4.01f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 1.98f, 2.0f, 1.98f);
        pathBuilderQ.horizontalLineToRelative(18.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.88f, 2.0f, -1.98f);
        pathBuilderQ.verticalLineToRelative(-14.0f);
        pathBuilderQ.curveToRelative(0.0f, -1.11f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.z(pathBuilderQ, 11.0f, 16.0f, 4.0f, -4.0f);
        d.v(pathBuilderQ, -4.0f, -4.0f, 3.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.t(pathBuilderQ, 2.0f, 10.0f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _input = imageVectorBuild;
        return imageVectorBuild;
    }
}
