package androidx.media3.datasource;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface DataSink {

    public interface Factory {
        DataSink createDataSink();
    }

    void close() throws IOException;

    void open(DataSpec dataSpec) throws IOException;

    void write(byte[] bArr, int i10, int i11) throws IOException;
}
