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
import v.d;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_backspace", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Backspace", "Landroidx/compose/material/icons/Icons$Outlined;", "getBackspace$annotations", "(Landroidx/compose/material/icons/Icons$Outlined;)V", "getBackspace", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BackspaceKt {
    private static ImageVector _backspace;

    public static final ImageVector getBackspace(Icons.Outlined outlined) {
        ImageVector imageVector = _backspace;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Backspace", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(22.0f, 3.0f, 7.0f, 3.0f);
        pathBuilderJ.curveToRelative(-0.69f, 0.0f, -1.23f, 0.35f, -1.59f, 0.88f);
        pathBuilderJ.lineTo(0.0f, 12.0f);
        pathBuilderJ.lineToRelative(5.41f, 8.11f);
        pathBuilderJ.curveToRelative(0.36f, 0.53f, 0.9f, 0.89f, 1.59f, 0.89f);
        pathBuilderJ.horizontalLineToRelative(15.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(24.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderJ, 22.0f, 19.0f, 7.07f, 19.0f);
        pathBuilderJ.lineTo(2.4f, 12.0f);
        pathBuilderJ.lineToRelative(4.66f, -7.0f);
        d.f(pathBuilderJ, 22.0f, 5.0f, 14.0f);
        pathBuilderJ.moveTo(10.41f, 17.0f);
        pathBuilderJ.lineTo(14.0f, 13.41f);
        pathBuilderJ.lineTo(17.59f, 17.0f);
        pathBuilderJ.lineTo(19.0f, 15.59f);
        pathBuilderJ.lineTo(15.41f, 12.0f);
        pathBuilderJ.lineTo(19.0f, 8.41f);
        pathBuilderJ.lineTo(17.59f, 7.0f);
        pathBuilderJ.lineTo(14.0f, 10.59f);
        pathBuilderJ.lineTo(10.41f, 7.0f);
        pathBuilderJ.lineTo(9.0f, 8.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderJ, 12.59f, 12.0f, 9.0f, 15.59f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _backspace = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getBackspace$annotations(Icons.Outlined outlined) {
    }
}
