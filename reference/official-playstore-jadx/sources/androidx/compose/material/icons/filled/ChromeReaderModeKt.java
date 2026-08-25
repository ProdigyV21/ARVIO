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
import v.c;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_chromeReaderMode", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ChromeReaderMode", "Landroidx/compose/material/icons/Icons$Filled;", "getChromeReaderMode$annotations", "(Landroidx/compose/material/icons/Icons$Filled;)V", "getChromeReaderMode", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChromeReaderModeKt {
    private static ImageVector _chromeReaderMode;

    public static final ImageVector getChromeReaderMode(Icons.Filled filled) {
        ImageVector imageVector = _chromeReaderMode;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ChromeReaderMode", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = c.b(13.0f, 12.0f, 7.0f, 1.5f, -7.0f);
        pathBuilderB.moveTo(13.0f, 9.5f);
        c.j(pathBuilderB, 7.0f, 20.0f, 11.0f, -7.0f);
        pathBuilderB.moveTo(13.0f, 14.5f);
        c.j(pathBuilderB, 7.0f, 20.0f, 16.0f, -7.0f);
        pathBuilderB.moveTo(21.0f, 4.0f);
        pathBuilderB.lineTo(3.0f, 4.0f);
        pathBuilderB.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderB.verticalLineToRelative(13.0f);
        pathBuilderB.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderB.horizontalLineToRelative(18.0f);
        pathBuilderB.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderB.lineTo(23.0f, 6.0f);
        pathBuilderB.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.g(pathBuilderB, 21.0f, 19.0f, -9.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderB, 12.0f, 6.0f, 9.0f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _chromeReaderMode = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getChromeReaderMode$annotations(Icons.Filled filled) {
    }
}
