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
import v.d;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_nextWeek", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NextWeek", "Landroidx/compose/material/icons/Icons$Outlined;", "getNextWeek$annotations", "(Landroidx/compose/material/icons/Icons$Outlined;)V", "getNextWeek", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NextWeekKt {
    private static ImageVector _nextWeek;

    public static final ImageVector getNextWeek(Icons.Outlined outlined) {
        ImageVector imageVector = _nextWeek;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.NextWeek", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(11.0f, 18.5f, 4.0f, -4.0f);
        pathBuilderR.lineToRelative(-4.0f, -4.0f);
        pathBuilderR.lineToRelative(-1.0f, 1.0f);
        b.D(pathBuilderR, 3.0f, 3.0f, -3.0f, 3.0f);
        pathBuilderR.moveTo(20.0f, 7.0f);
        pathBuilderR.horizontalLineToRelative(-4.0f);
        pathBuilderR.lineTo(16.0f, 5.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.22f, -1.05f, -0.59f, -1.41f);
        pathBuilderR.curveTo(15.05f, 3.22f, 14.55f, 3.0f, 14.0f, 3.0f);
        pathBuilderR.horizontalLineToRelative(-4.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(2.0f);
        pathBuilderR.lineTo(4.0f, 7.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(11.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(16.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.lineTo(22.0f, 9.0f);
        pathBuilderR.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        b.w(pathBuilderR, 10.0f, 5.0f, 4.0f, 2.0f);
        d.q(pathBuilderR, -4.0f, 10.0f, 5.0f);
        pathBuilderR.moveTo(20.0f, 20.0f);
        pathBuilderR.lineTo(4.0f, 20.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderR, 4.0f, 9.0f, 16.0f, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nextWeek = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getNextWeek$annotations(Icons.Outlined outlined) {
    }
}
