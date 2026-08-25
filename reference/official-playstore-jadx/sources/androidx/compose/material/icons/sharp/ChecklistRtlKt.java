package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_checklistRtl", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ChecklistRtl", "Landroidx/compose/material/icons/Icons$Sharp;", "getChecklistRtl", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChecklistRtlKt {
    private static ImageVector _checklistRtl;

    public static final ImageVector getChecklistRtl(Icons.Sharp sharp) {
        ImageVector imageVector = _checklistRtl;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ChecklistRtl", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(11.0f, 7.0f, 2.0f, 2.0f, 9.0f);
        a.B(pathBuilderH, 7.0f, 11.0f, 15.0f, 2.0f);
        a.t(pathBuilderH, 2.0f, 9.0f, 15.0f);
        pathBuilderH.moveTo(16.34f, 11.0f);
        pathBuilderH.lineToRelative(-3.54f, -3.54f);
        pathBuilderH.lineToRelative(1.41f, -1.41f);
        pathBuilderH.lineToRelative(2.12f, 2.12f);
        pathBuilderH.lineToRelative(4.24f, -4.24f);
        b.y(pathBuilderH, 22.0f, 5.34f, 16.34f, 11.0f);
        pathBuilderH.moveTo(16.34f, 19.0f);
        pathBuilderH.lineToRelative(-3.54f, -3.54f);
        pathBuilderH.lineToRelative(1.41f, -1.41f);
        pathBuilderH.lineToRelative(2.12f, 2.12f);
        pathBuilderH.lineToRelative(4.24f, -4.24f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderH, 22.0f, 13.34f, 16.34f, 19.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _checklistRtl = imageVectorBuild;
        return imageVectorBuild;
    }
}
