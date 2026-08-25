package androidx.compose.material.icons.twotone;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_checklist", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Checklist", "Landroidx/compose/material/icons/Icons$TwoTone;", "getChecklist", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChecklistKt {
    private static ImageVector _checklist;

    public static final ImageVector getChecklist(Icons.TwoTone twoTone) {
        ImageVector imageVector = _checklist;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Checklist", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(22.0f, 7.0f, -9.0f, 2.0f, 9.0f);
        a.D(pathBuilderK, 7.0f, 22.0f, 15.0f, -9.0f);
        a.t(pathBuilderK, 2.0f, 9.0f, 15.0f);
        pathBuilderK.moveTo(5.54f, 11.0f);
        pathBuilderK.lineTo(2.0f, 7.46f);
        pathBuilderK.lineToRelative(1.41f, -1.41f);
        pathBuilderK.lineToRelative(2.12f, 2.12f);
        pathBuilderK.lineToRelative(4.24f, -4.24f);
        d.C(pathBuilderK, 1.41f, 1.41f, 5.54f, 11.0f);
        pathBuilderK.moveTo(5.54f, 19.0f);
        pathBuilderK.lineTo(2.0f, 15.46f);
        pathBuilderK.lineToRelative(1.41f, -1.41f);
        pathBuilderK.lineToRelative(2.12f, 2.12f);
        pathBuilderK.lineToRelative(4.24f, -4.24f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderK, 1.41f, 1.41f, 5.54f, 19.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _checklist = imageVectorBuild;
        return imageVectorBuild;
    }
}
