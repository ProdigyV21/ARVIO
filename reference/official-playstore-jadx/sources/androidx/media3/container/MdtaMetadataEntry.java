package androidx.media3.container;

import ac.b;
import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.media3.common.Format;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.g;
import com.google.common.base.o;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class MdtaMetadataEntry implements Metadata.Entry {
    public static final byte AUXILIARY_TRACKS_SAMPLES_INTERLEAVED = 1;
    public static final byte AUXILIARY_TRACKS_SAMPLES_NOT_INTERLEAVED = 0;
    public static final int DEFAULT_LOCALE_INDICATOR = 0;
    public static final String KEY_ANDROID_CAPTURE_FPS = "com.android.capture.fps";
    public static final String KEY_AUXILIARY_TRACKS_INTERLEAVED = "auxiliary.tracks.interleaved";
    public static final String KEY_AUXILIARY_TRACKS_LENGTH = "auxiliary.tracks.length";
    public static final String KEY_AUXILIARY_TRACKS_MAP = "auxiliary.tracks.map";
    public static final String KEY_AUXILIARY_TRACKS_OFFSET = "auxiliary.tracks.offset";
    public static final int TYPE_INDICATOR_8_BIT_UNSIGNED_INT = 75;
    public static final int TYPE_INDICATOR_FLOAT32 = 23;
    public static final int TYPE_INDICATOR_INT32 = 67;
    public static final int TYPE_INDICATOR_RESERVED = 0;
    public static final int TYPE_INDICATOR_STRING = 1;
    public static final int TYPE_INDICATOR_UNSIGNED_INT64 = 78;
    public final String key;
    public final int localeIndicator;
    public final int typeIndicator;
    public final byte[] value;

    public MdtaMetadataEntry(String str, byte[] bArr, int i10) {
        this(str, bArr, 0, i10);
    }

    private static String getFormattedValueForAuxiliaryTracksMap(List<Integer> list) {
        StringBuilder sbS = d.s("track types = ");
        new o(String.valueOf(',')).a(sbS, list.iterator());
        return sbS.toString();
    }

    private static void validateData(String str, byte[] bArr, int i10) {
        boolean z;
        byte b10;
        str.getClass();
        switch (str) {
            case "com.android.capture.fps":
                if (i10 == 23 && bArr.length == 4) {
                    z = true;
                }
                b.j(z);
                break;
            case "auxiliary.tracks.interleaved":
                if (i10 == 75 && bArr.length == 1 && ((b10 = bArr[0]) == 0 || b10 == 1)) {
                    z = true;
                }
                b.j(z);
                break;
            case "auxiliary.tracks.length":
            case "auxiliary.tracks.offset":
                if (i10 == 78 && bArr.length == 8) {
                    z = true;
                }
                b.j(z);
                break;
            case "auxiliary.tracks.map":
                b.j(i10 == 0);
                break;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && MdtaMetadataEntry.class == obj.getClass()) {
            MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) obj;
            if (this.key.equals(mdtaMetadataEntry.key) && Arrays.equals(this.value, mdtaMetadataEntry.value) && this.localeIndicator == mdtaMetadataEntry.localeIndicator && this.typeIndicator == mdtaMetadataEntry.typeIndicator) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> getAuxiliaryTrackTypesFromMap() {
        b.t(this.key.equals(KEY_AUXILIARY_TRACKS_MAP), "Metadata is not an auxiliary tracks map");
        byte b10 = this.value[1];
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < b10; i10++) {
            arrayList.add(Integer.valueOf(this.value[i10 + 2]));
        }
        return arrayList;
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ byte[] getWrappedMetadataBytes() {
        return g.a(this);
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ Format getWrappedMetadataFormat() {
        return g.b(this);
    }

    public int hashCode() {
        return ((((Arrays.hashCode(this.value) + c.c(527, 31, this.key)) * 31) + this.localeIndicator) * 31) + this.typeIndicator;
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ void populateMediaMetadata(MediaMetadata.Builder builder) {
        g.c(this, builder);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r9 = this;
            int r0 = r9.typeIndicator
            if (r0 == 0) goto L80
            r1 = 1
            if (r0 == r1) goto L79
            r2 = 23
            r3 = 3
            r4 = 2
            java.lang.String r5 = "array too small: %s < %s"
            r6 = 4
            r7 = 0
            if (r0 == r2) goto L58
            r2 = 67
            if (r0 == r2) goto L3b
            r1 = 75
            if (r0 == r1) goto L30
            r1 = 78
            if (r0 == r1) goto L1f
            goto L93
        L1f:
            androidx.media3.common.util.ParsableByteArray r0 = new androidx.media3.common.util.ParsableByteArray
            byte[] r1 = r9.value
            r0.<init>(r1)
            long r0 = r0.readUnsignedLongToLong()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            goto L99
        L30:
            byte[] r0 = r9.value
            r0 = r0[r7]
            r0 = r0 & 255(0xff, float:3.57E-43)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            goto L99
        L3b:
            byte[] r0 = r9.value
            int r2 = r0.length
            if (r2 < r6) goto L42
            r2 = r1
            goto L43
        L42:
            r2 = r7
        L43:
            int r8 = r0.length
            ac.b.h(r5, r8, r6, r2)
            r2 = r0[r7]
            r1 = r0[r1]
            r4 = r0[r4]
            r0 = r0[r3]
            int r0 = m2.f0.n(r2, r1, r4, r0)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            goto L99
        L58:
            byte[] r0 = r9.value
            int r2 = r0.length
            if (r2 < r6) goto L5f
            r2 = r1
            goto L60
        L5f:
            r2 = r7
        L60:
            int r8 = r0.length
            ac.b.h(r5, r8, r6, r2)
            r2 = r0[r7]
            r1 = r0[r1]
            r4 = r0[r4]
            r0 = r0[r3]
            int r0 = m2.f0.n(r2, r1, r4, r0)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            goto L99
        L79:
            byte[] r0 = r9.value
            java.lang.String r0 = androidx.media3.common.util.Util.fromUtf8Bytes(r0)
            goto L99
        L80:
            java.lang.String r0 = r9.key
            java.lang.String r1 = "auxiliary.tracks.map"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L93
            java.util.List r0 = r9.getAuxiliaryTrackTypesFromMap()
            java.lang.String r0 = getFormattedValueForAuxiliaryTracksMap(r0)
            goto L99
        L93:
            byte[] r0 = r9.value
            java.lang.String r0 = androidx.media3.common.util.Util.toHexString(r0)
        L99:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "mdta: key="
            r1.<init>(r2)
            java.lang.String r2 = r9.key
            java.lang.String r3 = ", value="
            java.lang.String r0 = androidx.compose.material3.d.q(r1, r2, r3, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.MdtaMetadataEntry.toString():java.lang.String");
    }

    public MdtaMetadataEntry(String str, byte[] bArr, int i10, int i11) {
        validateData(str, bArr, i11);
        this.key = str;
        this.value = bArr;
        this.localeIndicator = i10;
        this.typeIndicator = i11;
    }
}
