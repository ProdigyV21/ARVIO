package androidx.media3.decoder;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DecoderOutputBuffer extends Buffer {
    public boolean shouldBeSkipped;
    public int skippedOutputBufferCount;
    public long timeUs;

    public interface Owner<S extends DecoderOutputBuffer> {
        void releaseOutputBuffer(S s10);
    }

    @Override // androidx.media3.decoder.Buffer
    public void clear() {
        super.clear();
        this.timeUs = 0L;
        this.skippedOutputBufferCount = 0;
        this.shouldBeSkipped = false;
    }

    public abstract void release();
}
