package androidx.media3.exoplayer.offline;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface Downloader {

    public interface ProgressListener {
        void onProgress(long j10, long j11, float f10);
    }

    void cancel();

    void download(ProgressListener progressListener) throws InterruptedException, IOException;

    void remove();
}
