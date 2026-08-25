package android.content;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.Dimension;
import android.view.Scale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import xb.l;
import xb.m;

/* JADX INFO: renamed from: coil.util.-SvgUtils, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a+\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0015\u0010\t\u001a\u00020\b*\u0004\u0018\u00010\bH\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001b\u0010\u000f\u001a\u00020\u000e*\u00020\u000b2\u0006\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\"\u0018\u0010\u0012\u001a\u00020\u0011*\u00020\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lxb/l;", "Lxb/m;", "bytes", "", "fromIndex", "toIndex", "indexOf", "(Lxb/l;Lxb/m;JJ)J", "Landroid/graphics/Bitmap$Config;", "toSoftware", "(Landroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap$Config;", "Lcoil/size/Dimension;", "Lcoil/size/Scale;", "scale", "", "toPx", "(Lcoil/size/Dimension;Lcoil/size/Scale;)F", "", "isHardware", "(Landroid/graphics/Bitmap$Config;)Z", "coil-svg_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SvgUtils {

    /* JADX INFO: renamed from: coil.util.-SvgUtils$WhenMappings */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Scale.values().length];
            try {
                iArr[Scale.FILL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Scale.FIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final long indexOf(l lVar, m mVar, long j10, long j11) {
        if (mVar.f() <= 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        byte bM = mVar.m(0);
        long jF = j11 - ((long) mVar.f());
        long j12 = j10;
        while (j12 < jF) {
            l lVar2 = lVar;
            long jA = lVar2.A(bM, j12, jF);
            if (jA == -1 || lVar2.s0(jA, mVar)) {
                return jA;
            }
            j12 = jA + 1;
            lVar = lVar2;
        }
        return -1L;
    }

    public static final boolean isHardware(Bitmap.Config config) {
        return Build.VERSION.SDK_INT >= 26 && config == Bitmap.Config.HARDWARE;
    }

    public static final float toPx(Dimension dimension, Scale scale) {
        if (dimension instanceof Dimension.Pixels) {
            return ((Dimension.Pixels) dimension).px;
        }
        int i10 = WhenMappings.$EnumSwitchMapping$0[scale.ordinal()];
        if (i10 == 1) {
            return Float.MIN_VALUE;
        }
        if (i10 == 2) {
            return Float.MAX_VALUE;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final Bitmap.Config toSoftware(Bitmap.Config config) {
        return (config == null || isHardware(config)) ? Bitmap.Config.ARGB_8888 : config;
    }
}
