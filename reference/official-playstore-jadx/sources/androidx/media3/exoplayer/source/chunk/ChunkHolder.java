package androidx.media3.exoplayer.source.chunk;

/* JADX INFO: loaded from: classes3.dex */
public final class ChunkHolder {
    public Chunk chunk;
    public boolean endOfStream;

    public void clear() {
        this.chunk = null;
        this.endOfStream = false;
    }
}
