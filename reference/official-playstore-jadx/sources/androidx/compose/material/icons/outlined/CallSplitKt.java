package androidx.compose.material.icons.outlined;

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
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_callSplit", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CallSplit", "Landroidx/compose/material/icons/Icons$Outlined;", "getCallSplit$annotations", "(Landroidx/compose/material/icons/Icons$Outlined;)V", "getCallSplit", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CallSplitKt {
    private static ImageVector _callSplit;

    public static final ImageVector getCallSplit(Icons.Outlined outlined) {
        ImageVector imageVector = _callSplit;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.CallSplit", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(14.0f, 4.0f, 2.29f, 2.29f);
        pathBuilderR.lineToRelative(-2.88f, 2.88f);
        pathBuilderR.lineToRelative(1.42f, 1.42f);
        pathBuilderR.lineToRelative(2.88f, -2.88f);
        pathBuilderR.lineTo(20.0f, 10.0f);
        f.n(pathBuilderR, 20.0f, 4.0f, -6.0f);
        pathBuilderR.moveTo(10.0f, 4.0f);
        pathBuilderR.lineTo(4.0f, 4.0f);
        pathBuilderR.verticalLineToRelative(6.0f);
        pathBuilderR.lineToRelative(2.29f, -2.29f);
        pathBuilderR.lineToRelative(4.71f, 4.7f);
        b.A(pathBuilderR, 11.0f, 20.0f, 2.0f, -8.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, -5.29f, -5.3f, 10.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _callSplit = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getCallSplit$annotations(Icons.Outlined outlined) {
    }
}
