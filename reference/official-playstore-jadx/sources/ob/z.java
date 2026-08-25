package ob;

import androidx.compose.material3.MenuKt;
import androidx.leanback.widget.r1;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.google.android.gms.cast.MediaError;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f20984a = {8184, 8388568, 268435426, 268435427, 268435428, 268435429, 268435430, 268435431, 268435432, 16777194, 1073741820, 268435433, 268435434, 1073741821, 268435435, 268435436, 268435437, 268435438, 268435439, 268435440, 268435441, 268435442, 1073741822, 268435443, 268435444, 268435445, 268435446, 268435447, 268435448, 268435449, 268435450, 268435451, 20, AnalyticsListener.EVENT_VIDEO_DECODER_INITIALIZED, AnalyticsListener.EVENT_VIDEO_INPUT_FORMAT_CHANGED, 4090, 8185, 21, 248, 2042, AnalyticsListener.EVENT_DROPPED_VIDEO_FRAMES, AnalyticsListener.EVENT_VIDEO_DECODER_RELEASED, 249, 2043, AnimationConstants.DURATION_IMAGE_CROSSFADE, 22, 23, 24, 0, 1, 2, 25, 26, 27, 28, 29, 30, 31, 92, 251, 32764, 32, 4091, AnalyticsListener.EVENT_VIDEO_DISABLED, 8186, 33, 93, 94, 95, 96, 97, 98, 99, 100, 101, MediaError.DetailedErrorCode.MEDIA_DECODE, MediaError.DetailedErrorCode.MEDIA_NETWORK, MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED, 105, 106, 107, 108, 109, MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE, 111, 112, 113, 114, 252, 115, 253, 8187, 524272, 8188, 16380, 34, 32765, 3, 35, 4, 36, 5, 37, 38, 39, 6, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, 117, 40, 41, 42, 7, 43, 118, 44, 8, 9, 45, 119, MenuKt.InTransitionDuration, 121, 122, 123, 32766, 2044, 16381, 8189, 268435452, 1048550, 4194258, 1048551, 1048552, 4194259, 4194260, 4194261, 8388569, 4194262, 8388570, 8388571, 8388572, 8388573, 8388574, 16777195, 8388575, 16777196, 16777197, 4194263, 8388576, 16777198, 8388577, 8388578, 8388579, 8388580, 2097116, 4194264, 8388581, 4194265, 8388582, 8388583, 16777199, 4194266, 2097117, 1048553, 4194267, 4194268, 8388584, 8388585, 2097118, 8388586, 4194269, 4194270, 16777200, 2097119, 4194271, 8388587, 8388588, 2097120, 2097121, 4194272, 2097122, 8388589, 4194273, 8388590, 8388591, 1048554, 4194274, 4194275, 4194276, 8388592, 4194277, 4194278, 8388593, 67108832, 67108833, 1048555, 524273, 4194279, 8388594, 4194280, 33554412, 67108834, 67108835, 67108836, 134217694, 134217695, 67108837, 16777201, 33554413, 524274, 2097123, 67108838, 134217696, 134217697, 67108839, 134217698, 16777202, 2097124, 2097125, 67108840, 67108841, 268435453, 134217699, 134217700, 134217701, 1048556, 16777203, 1048557, 2097126, 4194281, 2097127, 2097128, 8388595, 4194282, 4194283, 33554414, 33554415, 16777204, 16777205, 67108842, 8388596, 67108843, 134217702, 67108844, 67108845, 134217703, 134217704, 134217705, 134217706, 134217707, 268435454, 134217708, 134217709, 134217710, 134217711, 134217712, 67108846};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f20985b = {13, 23, 28, 28, 28, 28, 28, 28, 28, 24, 30, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 28, 6, 10, 10, 12, 13, 6, 8, 11, 10, 10, 8, 11, 8, 6, 6, 6, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 8, 15, 6, 12, 10, 13, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 13, 19, 13, 14, 6, 15, 5, 6, 5, 6, 5, 6, 6, 6, 5, 7, 7, 6, 6, 6, 5, 6, 7, 6, 5, 5, 6, 7, 7, 7, 7, 7, 15, 11, 14, 13, 28, 20, 22, 20, 20, 22, 22, 22, 23, 22, 23, 23, 23, 23, 23, 24, 23, 24, 24, 22, 23, 24, 23, 23, 23, 23, 21, 22, 23, 22, 23, 23, 24, 22, 21, 20, 22, 22, 23, 23, 21, 23, 22, 22, 24, 21, 22, 23, 23, 21, 21, 22, 21, 23, 22, 23, 23, 20, 22, 22, 22, 23, 22, 22, 23, 26, 26, 20, 19, 22, 23, 22, 25, 26, 26, 26, 27, 27, 26, 24, 25, 19, 21, 26, 27, 27, 26, 27, 24, 21, 21, 26, 26, 28, 27, 27, 27, 20, 24, 20, 21, 22, 21, 21, 23, 22, 22, 25, 25, 24, 24, 26, 23, 26, 27, 26, 26, 27, 27, 27, 27, 27, 28, 27, 27, 27, 27, 27, 26};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final r1 f20986c = new r1(3);

    static {
        r1[] r1VarArr;
        for (int i10 = 0; i10 < 256; i10++) {
            int i11 = f20984a[i10];
            int i12 = f20985b[i10];
            r1 r1Var = new r1(i10, i12, 3);
            r1 r1Var2 = f20986c;
            while (true) {
                r1VarArr = (r1[]) r1Var2.f3114d;
                if (i12 > 8) {
                    i12 -= 8;
                    int i13 = (i11 >>> i12) & 255;
                    r1 r1Var3 = r1VarArr[i13];
                    if (r1Var3 == null) {
                        r1Var3 = new r1(3);
                        r1VarArr[i13] = r1Var3;
                    }
                    r1Var2 = r1Var3;
                }
            }
            int i14 = 8 - i12;
            int i15 = (i11 << i14) & 255;
            Arrays.fill(r1VarArr, i15, (1 << i14) + i15, r1Var);
        }
    }
}
