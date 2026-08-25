package androidx.compose.material.icons.automirrored.sharp;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_assistantDirection", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AssistantDirection", "Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;", "getAssistantDirection", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AssistantDirectionKt {
    private static ImageVector _assistantDirection;

    public static final ImageVector getAssistantDirection(Icons.AutoMirrored.Sharp sharp) {
        ImageVector imageVector = _assistantDirection;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Sharp.AssistantDirection", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(13.5f, 10.0f, 8.0f, 5.0f, 2.0f);
        e.l(pathBuilderH, -3.0f, 3.5f, 2.5f);
        pathBuilderH.lineTo(17.0f, 11.0f);
        c.e(pathBuilderH, -3.5f, -3.5f, 10.0f);
        pathBuilderH.moveTo(12.0f, 1.0f);
        pathBuilderH.curveTo(5.9f, 1.0f, 1.0f, 5.9f, 1.0f, 12.0f);
        pathBuilderH.reflectiveCurveToRelative(4.9f, 11.0f, 11.0f, 11.0f);
        pathBuilderH.reflectiveCurveToRelative(11.0f, -4.9f, 11.0f, -11.0f);
        pathBuilderH.reflectiveCurveTo(18.1f, 1.0f, 12.0f, 1.0f);
        a.z(pathBuilderH, 20.31f, 12.0f, -8.34f, 8.37f);
        pathBuilderH.lineTo(3.62f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderH, 8.34f, -8.37f, 20.31f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _assistantDirection = imageVectorBuild;
        return imageVectorBuild;
    }
}
