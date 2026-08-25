package io.ktor.websocket;

import io.ktor.util.internal.ExceptionUtilsJvmKt;
import ka.y;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0010\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lio/ktor/websocket/FrameTooBigException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Lka/y;", "", "frameSize", "<init>", "(J)V", "createCopy", "()Lio/ktor/websocket/FrameTooBigException;", "J", "getFrameSize", "()J", "", "getMessage", "()Ljava/lang/String;", "message", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FrameTooBigException extends Exception implements y<FrameTooBigException> {
    private final long frameSize;

    public FrameTooBigException(long j10) {
        this.frameSize = j10;
    }

    public final long getFrameSize() {
        return this.frameSize;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Frame is too big: " + this.frameSize;
    }

    @Override // ka.y
    public FrameTooBigException createCopy() {
        FrameTooBigException frameTooBigException = new FrameTooBigException(this.frameSize);
        ExceptionUtilsJvmKt.initCauseBridge(frameTooBigException, this);
        return frameTooBigException;
    }
}
