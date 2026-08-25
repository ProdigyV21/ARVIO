package io.ktor.util.cio;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.utils.io.ByteReadChannel;
import java.nio.file.Path;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"readChannel", "Lio/ktor/utils/io/ByteReadChannel;", "Ljava/nio/file/Path;", TtmlNode.START, "", "endInclusive", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FileChannelsAtNioPathKt {
    public static final ByteReadChannel readChannel(Path path, long j10, long j11) {
        return FileChannelsKt.readChannel$default(path.toFile(), j10, j11, null, 4, null);
    }

    public static final ByteReadChannel readChannel(Path path) {
        return FileChannelsKt.readChannel$default(path.toFile(), 0L, 0L, null, 7, null);
    }
}
