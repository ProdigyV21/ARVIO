package androidx.media3.container;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.media3.common.util.ParsableByteArray;

/* JADX INFO: loaded from: classes3.dex */
public final class DolbyVisionConfig {
    public final String codecs;
    public final int level;
    public final int profile;

    private DolbyVisionConfig(int i10, int i11, String str) {
        this.profile = i10;
        this.level = i11;
        this.codecs = str;
    }

    public static DolbyVisionConfig parse(ParsableByteArray parsableByteArray) {
        String str;
        parsableByteArray.skipBytes(2);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i10 = unsignedByte >> 1;
        int unsignedByte2 = ((parsableByteArray.readUnsignedByte() >> 3) & 31) | ((unsignedByte & 1) << 5);
        if (i10 == 4 || i10 == 5 || i10 == 7 || i10 == 8) {
            str = "dvhe";
        } else if (i10 == 9) {
            str = "dvav";
        } else {
            if (i10 != 10) {
                return null;
            }
            str = "dav1";
        }
        StringBuilder sbS = d.s(str);
        sbS.append(i10 < 10 ? ".0" : ".");
        sbS.append(i10);
        return new DolbyVisionConfig(i10, unsignedByte2, c.p(unsignedByte2, unsignedByte2 < 10 ? ".0" : ".", sbS));
    }
}
