package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.util.ParsableByteArray;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class e {
    public static int b(TrackOutput trackOutput, DataReader dataReader, int i10, boolean z) {
        return trackOutput.sampleData(dataReader, i10, z, 0);
    }

    public static void c(TrackOutput trackOutput, ParsableByteArray parsableByteArray, int i10) {
        trackOutput.sampleData(parsableByteArray, i10, 0);
    }

    public static void a(TrackOutput trackOutput, long j10) {
    }
}
