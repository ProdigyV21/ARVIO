package c2;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.io.Closeable;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Lc2/d;", "Ljava/io/Closeable;", "androidx/appcompat/widget/f0", "c2/c", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "sqlite_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface d extends Closeable {

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Lc2/d$a;", "", "sqlite_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public interface a {
        d b(c cVar);
    }

    b getWritableDatabase();

    void setWriteAheadLoggingEnabled(boolean z);
}
