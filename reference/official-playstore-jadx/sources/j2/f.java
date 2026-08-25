package j2;

import androidx.compose.material3.MenuKt;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.google.android.gms.cast.MediaError;

/* JADX INFO: loaded from: classes3.dex */
public abstract class f {
    public static final long A;
    public static final long B;
    public static final /* synthetic */ int C = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f19088a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f19089b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final long f19090c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final long f19091d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f19092e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long f19093f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final long f19094g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final long f19095h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final long f19096i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final long f19097j;
    public static final long k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final long f19098l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final long f19099m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final long f19100n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final long f19101o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final long f19102p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final long f19103q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final long f19104r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final long f19105s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final long f19106t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final long f19107u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final long f19108v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final long f19109w;
    public static final long x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final long f19110y;
    public static final long z;

    static {
        ColorKt.Color$default(0, 0, 0, 0, 8, null);
        ColorKt.Color$default(0, 0, 0, 0, 8, null);
        f19088a = ColorKt.Color$default(65, 14, 11, 0, 8, null);
        f19089b = ColorKt.Color$default(255, 255, 255, 0, 8, null);
        ColorKt.Color$default(96, 20, 16, 0, 8, null);
        f19090c = ColorKt.Color$default(140, 29, 24, 0, 8, null);
        f19091d = ColorKt.Color$default(179, 38, 30, 0, 8, null);
        ColorKt.Color$default(220, 54, 46, 0, 8, null);
        ColorKt.Color$default(228, 105, 98, 0, 8, null);
        ColorKt.Color$default(236, 146, 142, 0, 8, null);
        ColorKt.Color$default(242, 184, 181, 0, 8, null);
        f19092e = ColorKt.Color$default(249, 222, 220, 0, 8, null);
        ColorKt.Color$default(252, 238, 238, 0, 8, null);
        ColorKt.Color$default(255, 251, 249, 0, 8, null);
        f19093f = ColorKt.Color$default(0, 0, 0, 0, 8, null);
        f19094g = ColorKt.Color$default(28, 27, 31, 0, 8, null);
        ColorKt.Color$default(255, 255, 255, 0, 8, null);
        f19095h = ColorKt.Color$default(49, 48, 51, 0, 8, null);
        ColorKt.Color$default(72, 70, 73, 0, 8, null);
        ColorKt.Color$default(96, 93, 98, 0, 8, null);
        ColorKt.Color$default(MenuKt.InTransitionDuration, 117, 121, 0, 8, null);
        ColorKt.Color$default(147, 144, 148, 0, 8, null);
        ColorKt.Color$default(174, 170, 174, 0, 8, null);
        ColorKt.Color$default(201, 197, 202, 0, 8, null);
        f19096i = ColorKt.Color$default(230, 225, 229, 0, 8, null);
        f19097j = ColorKt.Color$default(244, 239, 244, 0, 8, null);
        k = ColorKt.Color$default(255, 251, 254, 0, 8, null);
        ColorKt.Color$default(0, 0, 0, 0, 8, null);
        ColorKt.Color$default(29, 26, 34, 0, 8, null);
        ColorKt.Color$default(255, 255, 255, 0, 8, null);
        ColorKt.Color$default(50, 47, 55, 0, 8, null);
        f19098l = ColorKt.Color$default(73, 69, 79, 0, 8, null);
        ColorKt.Color$default(96, 93, MediaError.DetailedErrorCode.MEDIA_DECODE, 0, 8, null);
        f19099m = ColorKt.Color$default(121, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, 126, 0, 8, null);
        ColorKt.Color$default(147, 143, 153, 0, 8, null);
        ColorKt.Color$default(174, 169, 180, 0, 8, null);
        f19100n = ColorKt.Color$default(202, 196, 208, 0, 8, null);
        f19101o = ColorKt.Color$default(231, 224, 236, 0, 8, null);
        ColorKt.Color$default(245, 238, AnimationConstants.DURATION_IMAGE_CROSSFADE, 0, 8, null);
        ColorKt.Color$default(255, 251, 254, 0, 8, null);
        ColorKt.Color$default(0, 0, 0, 0, 8, null);
        f19102p = ColorKt.Color$default(33, 0, 93, 0, 8, null);
        f19103q = ColorKt.Color$default(255, 255, 255, 0, 8, null);
        ColorKt.Color$default(56, 30, 114, 0, 8, null);
        ColorKt.Color$default(79, 55, TsExtractor.TS_STREAM_TYPE_DTS_UHD, 0, 8, null);
        f19104r = ColorKt.Color$default(MediaError.DetailedErrorCode.MEDIA_NETWORK, 80, 164, 0, 8, null);
        ColorKt.Color$default(127, MediaError.DetailedErrorCode.MEDIA_NETWORK, 190, 0, 8, null);
        ColorKt.Color$default(154, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 219, 0, 8, null);
        ColorKt.Color$default(182, 157, 248, 0, 8, null);
        f19105s = ColorKt.Color$default(208, TsExtractor.TS_PACKET_SIZE, 255, 0, 8, null);
        f19106t = ColorKt.Color$default(234, 221, 255, 0, 8, null);
        ColorKt.Color$default(246, 237, 255, 0, 8, null);
        ColorKt.Color$default(255, 251, 254, 0, 8, null);
        ColorKt.Color$default(0, 0, 0, 0, 8, null);
        f19107u = ColorKt.Color$default(29, 25, 43, 0, 8, null);
        f19108v = ColorKt.Color$default(255, 255, 255, 0, 8, null);
        ColorKt.Color$default(51, 45, 65, 0, 8, null);
        ColorKt.Color$default(74, 68, 88, 0, 8, null);
        f19109w = ColorKt.Color$default(98, 91, 113, 0, 8, null);
        ColorKt.Color$default(122, 114, 137, 0, 8, null);
        ColorKt.Color$default(149, 141, 165, 0, 8, null);
        ColorKt.Color$default(176, 167, PsExtractor.AUDIO_STREAM, 0, 8, null);
        ColorKt.Color$default(ComposerKt.providerMapsKey, 194, 220, 0, 8, null);
        x = ColorKt.Color$default(232, 222, 248, 0, 8, null);
        ColorKt.Color$default(246, 237, 255, 0, 8, null);
        ColorKt.Color$default(255, 251, 254, 0, 8, null);
        ColorKt.Color$default(0, 0, 0, 0, 8, null);
        f19110y = ColorKt.Color$default(49, 17, 29, 0, 8, null);
        z = ColorKt.Color$default(255, 255, 255, 0, 8, null);
        ColorKt.Color$default(73, 37, 50, 0, 8, null);
        ColorKt.Color$default(99, 59, 72, 0, 8, null);
        A = ColorKt.Color$default(125, 82, 96, 0, 8, null);
        ColorKt.Color$default(152, 105, 119, 0, 8, null);
        ColorKt.Color$default(181, 131, 146, 0, 8, null);
        ColorKt.Color$default(210, 157, TsExtractor.TS_STREAM_TYPE_AC4, 0, 8, null);
        ColorKt.Color$default(239, 184, 200, 0, 8, null);
        B = ColorKt.Color$default(255, 216, 228, 0, 8, null);
        ColorKt.Color$default(255, 236, 241, 0, 8, null);
        ColorKt.Color$default(255, 251, AnimationConstants.DURATION_IMAGE_CROSSFADE, 0, 8, null);
        ColorKt.Color$default(255, 255, 255, 0, 8, null);
    }
}
