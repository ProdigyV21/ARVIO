package io.ktor.util.cio;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/util/cio/ChannelWriteException;", "Lio/ktor/util/cio/ChannelIOException;", "message", "", "exception", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ChannelWriteException extends ChannelIOException {
    public /* synthetic */ ChannelWriteException(String str, Throwable th, int i10, h hVar) {
        this((i10 & 1) != 0 ? "Cannot write to a channel" : str, th);
    }

    public ChannelWriteException(String str, Throwable th) {
        super(str, th);
    }
}
