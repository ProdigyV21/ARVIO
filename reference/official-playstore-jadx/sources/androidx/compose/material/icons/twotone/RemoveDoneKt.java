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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_removeDone", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RemoveDone", "Landroidx/compose/material/icons/Icons$TwoTone;", "getRemoveDone", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RemoveDoneKt {
    private static ImageVector _removeDone;

    public static final ImageVector getRemoveDone(Icons.TwoTone twoTone) {
        ImageVector imageVector = _removeDone;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.RemoveDone", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(4.84f, 1.98f, 3.43f, 3.39f);
        pathBuilderJ.lineToRelative(10.38f, 10.38f);
        pathBuilderJ.lineToRelative(-1.41f, 1.41f);
        pathBuilderJ.lineToRelative(-4.24f, -4.24f);
        pathBuilderJ.lineToRelative(-1.41f, 1.41f);
        pathBuilderJ.lineToRelative(5.66f, 5.66f);
        pathBuilderJ.lineToRelative(2.83f, -2.83f);
        pathBuilderJ.lineToRelative(6.6f, 6.6f);
        d.C(pathBuilderJ, 1.41f, -1.41f, 4.84f, 1.98f);
        pathBuilderJ.moveTo(18.05f, 12.36f);
        pathBuilderJ.lineTo(23.0f, 7.4f);
        pathBuilderJ.lineTo(21.57f, 6.0f);
        d.C(pathBuilderJ, -4.94f, 4.94f, 18.05f, 12.36f);
        pathBuilderJ.moveTo(17.34f, 7.4f);
        pathBuilderJ.lineToRelative(-1.41f, -1.41f);
        pathBuilderJ.lineToRelative(-2.12f, 2.12f);
        d.C(pathBuilderJ, 1.41f, 1.41f, 17.34f, 7.4f);
        pathBuilderJ.moveTo(1.08f, 12.35f);
        pathBuilderJ.lineToRelative(5.66f, 5.66f);
        pathBuilderJ.lineToRelative(1.41f, -1.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderJ, -5.66f, -5.66f, 1.08f, 12.35f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _removeDone = imageVectorBuild;
        return imageVectorBuild;
    }
}
