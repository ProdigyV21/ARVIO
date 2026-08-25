package android.graphics;

import kotlin.Metadata;
import v1.i;
import xb.l;
import xb.m;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0019\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u0019\u0010\u0007\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\u0005\u001a\u0019\u0010\b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\b\u0010\u0005\u001a\u0019\u0010\t\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\t\u0010\u0005\"\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f\"\u0014\u0010\r\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\f\"\u0014\u0010\u000e\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\f\"\u0014\u0010\u000f\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\f\"\u0014\u0010\u0010\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\f\"\u0014\u0010\u0011\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\f\"\u0014\u0010\u0012\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\f\"\u0014\u0010\u0013\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\f\"\u0014\u0010\u0014\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\f¨\u0006\u0015"}, d2 = {"Lcoil/decode/DecodeUtils;", "Lxb/l;", "source", "", "isGif", "(Lcoil/decode/DecodeUtils;Lxb/l;)Z", "isWebP", "isAnimatedWebP", "isHeif", "isAnimatedHeif", "Lxb/m;", "GIF_HEADER_87A", "Lxb/m;", "GIF_HEADER_89A", "WEBP_HEADER_RIFF", "WEBP_HEADER_WEBP", "WEBP_HEADER_VPX8", "HEIF_HEADER_FTYP", "HEIF_HEADER_MSF1", "HEIF_HEADER_HEVC", "HEIF_HEADER_HEVX", "coil-gif_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class GifDecodeUtils {
    private static final m GIF_HEADER_87A = i.g("GIF87a");
    private static final m GIF_HEADER_89A = i.g("GIF89a");
    private static final m WEBP_HEADER_RIFF = i.g("RIFF");
    private static final m WEBP_HEADER_WEBP = i.g("WEBP");
    private static final m WEBP_HEADER_VPX8 = i.g("VP8X");
    private static final m HEIF_HEADER_FTYP = i.g("ftyp");
    private static final m HEIF_HEADER_MSF1 = i.g("msf1");
    private static final m HEIF_HEADER_HEVC = i.g("hevc");
    private static final m HEIF_HEADER_HEVX = i.g("hevx");

    public static final boolean isAnimatedHeif(DecodeUtils decodeUtils, l lVar) {
        if (isHeif(decodeUtils, lVar)) {
            return lVar.s0(8L, HEIF_HEADER_MSF1) || lVar.s0(8L, HEIF_HEADER_HEVC) || lVar.s0(8L, HEIF_HEADER_HEVX);
        }
        return false;
    }

    public static final boolean isAnimatedWebP(DecodeUtils decodeUtils, l lVar) {
        return isWebP(decodeUtils, lVar) && lVar.s0(12L, WEBP_HEADER_VPX8) && lVar.request(17L) && ((byte) (lVar.h().x(16L) & 2)) > 0;
    }

    public static final boolean isGif(DecodeUtils decodeUtils, l lVar) {
        return lVar.s0(0L, GIF_HEADER_89A) || lVar.s0(0L, GIF_HEADER_87A);
    }

    public static final boolean isHeif(DecodeUtils decodeUtils, l lVar) {
        return lVar.s0(4L, HEIF_HEADER_FTYP);
    }

    public static final boolean isWebP(DecodeUtils decodeUtils, l lVar) {
        return lVar.s0(0L, WEBP_HEADER_RIFF) && lVar.s0(8L, WEBP_HEADER_WEBP);
    }
}
