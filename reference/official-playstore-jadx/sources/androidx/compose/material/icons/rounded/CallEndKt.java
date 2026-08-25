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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_callEnd", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CallEnd", "Landroidx/compose/material/icons/Icons$Rounded;", "getCallEnd", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CallEndKt {
    private static ImageVector _callEnd;

    public static final ImageVector getCallEnd(Icons.Rounded rounded) {
        ImageVector imageVector = _callEnd;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.CallEnd", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(4.51f, 15.48f, 2.0f, -1.59f);
        pathBuilderR.curveToRelative(0.48f, -0.38f, 0.76f, -0.96f, 0.76f, -1.57f);
        pathBuilderR.verticalLineToRelative(-2.6f);
        pathBuilderR.curveToRelative(3.02f, -0.98f, 6.29f, -0.99f, 9.32f, 0.0f);
        pathBuilderR.verticalLineToRelative(2.61f);
        pathBuilderR.curveToRelative(0.0f, 0.61f, 0.28f, 1.19f, 0.76f, 1.57f);
        pathBuilderR.lineToRelative(1.99f, 1.58f);
        pathBuilderR.curveToRelative(0.8f, 0.63f, 1.94f, 0.57f, 2.66f, -0.15f);
        pathBuilderR.lineToRelative(1.22f, -1.22f);
        pathBuilderR.curveToRelative(0.8f, -0.8f, 0.8f, -2.13f, -0.05f, -2.88f);
        pathBuilderR.curveToRelative(-6.41f, -5.66f, -16.07f, -5.66f, -22.48f, 0.0f);
        pathBuilderR.curveToRelative(-0.85f, 0.75f, -0.85f, 2.08f, -0.05f, 2.88f);
        pathBuilderR.lineToRelative(1.22f, 1.22f);
        pathBuilderR.curveToRelative(0.71f, 0.72f, 1.85f, 0.78f, 2.65f, 0.15f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _callEnd = imageVectorBuild;
        return imageVectorBuild;
    }
}
