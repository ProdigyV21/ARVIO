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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_playlistAdd", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PlaylistAdd", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPlaylistAdd$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getPlaylistAdd", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PlaylistAddKt {
    private static ImageVector _playlistAdd;

    public static final ImageVector getPlaylistAdd(Icons.TwoTone twoTone) {
        ImageVector imageVector = _playlistAdd;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PlaylistAdd", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(14.0f, 10.0f, 3.0f, 2.0f, 11.0f);
        a.B(pathBuilderH, 10.0f, 14.0f, 6.0f, 3.0f);
        a.t(pathBuilderH, 2.0f, 11.0f, 6.0f);
        c.m(pathBuilderH, 18.0f, 14.0f, -4.0f, -2.0f);
        b.z(pathBuilderH, 4.0f, -4.0f, 2.0f, 4.0f);
        b.z(pathBuilderH, 4.0f, 2.0f, -4.0f, 4.0f);
        e.y(pathBuilderH, -2.0f, 18.0f, 3.0f, 16.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.k(pathBuilderH, 7.0f, -2.0f, 3.0f, 16.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _playlistAdd = imageVectorBuild;
        return imageVectorBuild;
    }

    @x6.e
    public static /* synthetic */ void getPlaylistAdd$annotations(Icons.TwoTone twoTone) {
    }
}
