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
import v.c;
import v.d;
import v.f;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_queueMusic", "Landroidx/compose/ui/graphics/vector/ImageVector;", "QueueMusic", "Landroidx/compose/material/icons/Icons$Outlined;", "getQueueMusic$annotations", "(Landroidx/compose/material/icons/Icons$Outlined;)V", "getQueueMusic", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class QueueMusicKt {
    private static ImageVector _queueMusic;

    public static final ImageVector getQueueMusic(Icons.Outlined outlined) {
        ImageVector imageVector = _queueMusic;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.QueueMusic", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(22.0f, 6.0f, -5.0f, 8.18f);
        pathBuilderA.curveTo(16.69f, 14.07f, 16.35f, 14.0f, 16.0f, 14.0f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        f.k(pathBuilderA, 8.0f, 3.0f, 6.0f);
        a.A(pathBuilderA, 15.0f, 6.0f, 3.0f, 2.0f);
        c.D(pathBuilderA, 12.0f, 6.0f, 15.0f, 10.0f);
        c.k(pathBuilderA, 3.0f, 2.0f, 12.0f, 10.0f);
        a.A(pathBuilderA, 11.0f, 14.0f, 3.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderA, 8.0f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _queueMusic = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getQueueMusic$annotations(Icons.Outlined outlined) {
    }
}
