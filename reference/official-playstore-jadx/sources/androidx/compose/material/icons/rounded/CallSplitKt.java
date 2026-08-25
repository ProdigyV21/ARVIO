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
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_callSplit", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CallSplit", "Landroidx/compose/material/icons/Icons$Rounded;", "getCallSplit$annotations", "(Landroidx/compose/material/icons/Icons$Rounded;)V", "getCallSplit", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CallSplitKt {
    private static ImageVector _callSplit;

    public static final ImageVector getCallSplit(Icons.Rounded rounded) {
        ImageVector imageVector = _callSplit;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.CallSplit", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(14.85f, 4.85f, 1.44f, 1.44f);
        pathBuilderR.lineToRelative(-2.88f, 2.88f);
        pathBuilderR.lineToRelative(1.42f, 1.42f);
        pathBuilderR.lineToRelative(2.88f, -2.88f);
        pathBuilderR.lineToRelative(1.44f, 1.44f);
        pathBuilderR.curveToRelative(0.31f, 0.31f, 0.85f, 0.09f, 0.85f, -0.36f);
        pathBuilderR.verticalLineTo(4.5f);
        pathBuilderR.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderR.horizontalLineToRelative(-4.29f);
        pathBuilderR.curveToRelative(-0.45f, 0.0f, -0.67f, 0.54f, -0.36f, 0.85f);
        w.a.r(pathBuilderR, 8.79f, 4.0f, 4.5f);
        pathBuilderR.curveToRelative(-0.28f, 0.0f, -0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderR.verticalLineToRelative(4.29f);
        pathBuilderR.curveToRelative(0.0f, 0.45f, 0.54f, 0.67f, 0.85f, 0.35f);
        pathBuilderR.lineTo(6.29f, 7.7f);
        pathBuilderR.lineTo(11.0f, 12.4f);
        pathBuilderR.verticalLineTo(19.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-7.0f);
        pathBuilderR.curveToRelative(0.0f, -0.26f, -0.11f, -0.52f, -0.29f, -0.71f);
        pathBuilderR.lineToRelative(-5.0f, -5.01f);
        pathBuilderR.lineToRelative(1.44f, -1.44f);
        pathBuilderR.curveToRelative(0.31f, -0.3f, 0.09f, -0.84f, -0.36f, -0.84f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _callSplit = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getCallSplit$annotations(Icons.Rounded rounded) {
    }
}
