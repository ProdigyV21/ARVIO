package io.ktor.client.call;

import g8.b;
import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/client/call/UnsupportedContentTypeException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "content", "Lio/ktor/http/content/OutgoingContent;", "(Lio/ktor/http/content/OutgoingContent;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UnsupportedContentTypeException extends IllegalStateException {
    public UnsupportedContentTypeException(OutgoingContent outgoingContent) {
        StringBuilder sb2 = new StringBuilder("Failed to write body: ");
        super(b.h(l0.f19747a, outgoingContent.getClass(), sb2));
    }
}
