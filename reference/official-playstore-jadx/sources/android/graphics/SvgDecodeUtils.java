package android.graphics;

import android.content.SvgUtils;
import kotlin.Metadata;
import kotlin.text.a;
import xb.l;
import xb.m;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b\"\u0014\u0010\t\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lcoil/decode/DecodeUtils;", "Lxb/l;", "source", "", "isSvg", "(Lcoil/decode/DecodeUtils;Lxb/l;)Z", "Lxb/m;", "SVG_TAG", "Lxb/m;", "LEFT_ANGLE_BRACKET", "coil-svg_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SvgDecodeUtils {
    private static final m LEFT_ANGLE_BRACKET;
    private static final m SVG_TAG;

    static {
        m mVar = new m("<svg".getBytes(a.f19924a));
        mVar.f22775m = "<svg";
        SVG_TAG = mVar;
        m mVar2 = new m("<".getBytes(a.f19924a));
        mVar2.f22775m = "<";
        LEFT_ANGLE_BRACKET = mVar2;
    }

    public static final boolean isSvg(DecodeUtils decodeUtils, l lVar) {
        return lVar.s0(0L, LEFT_ANGLE_BRACKET) && SvgUtils.indexOf(lVar, SVG_TAG, 0L, 1024L) != -1;
    }
}
