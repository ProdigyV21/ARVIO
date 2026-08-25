package io.ktor.serialization;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/serialization/WebsocketConverterNotFoundException;", "Lio/ktor/serialization/WebsocketContentConvertException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "ktor-serialization"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WebsocketConverterNotFoundException extends WebsocketContentConvertException {
    public /* synthetic */ WebsocketConverterNotFoundException(String str, Throwable th, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : th);
    }

    public WebsocketConverterNotFoundException(String str, Throwable th) {
        super(str, th);
    }
}
