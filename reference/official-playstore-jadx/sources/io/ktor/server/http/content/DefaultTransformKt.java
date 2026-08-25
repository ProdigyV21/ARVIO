package io.ktor.server.http.content;

import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.ByteArrayContent;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.TextContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.response.ApplicationResponseFunctionsKt;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"transformDefaultContent", "Lio/ktor/http/content/OutgoingContent;", "call", "Lio/ktor/server/application/ApplicationCall;", "value", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DefaultTransformKt {
    public static final OutgoingContent transformDefaultContent(ApplicationCall applicationCall, final Object obj) {
        if (obj instanceof OutgoingContent) {
            return (OutgoingContent) obj;
        }
        if (obj instanceof String) {
            return new TextContent((String) obj, ApplicationResponseFunctionsKt.defaultTextContentType(applicationCall, null), null);
        }
        return obj instanceof byte[] ? new ByteArrayContent((byte[]) obj, null, null, 6, null) : obj instanceof HttpStatusCode ? new HttpStatusCodeContent((HttpStatusCode) obj) : obj instanceof ByteReadChannel ? new OutgoingContent.ReadChannelContent() { // from class: io.ktor.server.http.content.DefaultTransformKt.transformDefaultContent.1
            @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
            public ByteReadChannel readFrom() {
                return (ByteReadChannel) obj;
            }
        } : DefaultTransformJvmKt.platformTransformDefaultContent(applicationCall, obj);
    }
}
