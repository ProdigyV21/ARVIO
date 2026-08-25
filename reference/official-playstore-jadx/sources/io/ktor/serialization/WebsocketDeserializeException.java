package io.ktor.serialization;

import io.ktor.websocket.Frame;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ktor/serialization/WebsocketDeserializeException;", "Lio/ktor/serialization/WebsocketContentConvertException;", "message", "", "cause", "", "frame", "Lio/ktor/websocket/Frame;", "(Ljava/lang/String;Ljava/lang/Throwable;Lio/ktor/websocket/Frame;)V", "getFrame", "()Lio/ktor/websocket/Frame;", "ktor-serialization"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WebsocketDeserializeException extends WebsocketContentConvertException {
    private final Frame frame;

    public /* synthetic */ WebsocketDeserializeException(String str, Throwable th, Frame frame, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : th, frame);
    }

    public final Frame getFrame() {
        return this.frame;
    }

    public WebsocketDeserializeException(String str, Throwable th, Frame frame) {
        super(str, th);
        this.frame = frame;
    }
}
