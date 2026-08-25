package io.ktor.http.content;

import io.ktor.http.ContentType;
import io.ktor.http.FileContentTypeKt;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.cio.ByteBufferPoolKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.jvm.javaio.ReadingKt;
import java.net.URI;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B#\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0018\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lio/ktor/http/content/URIFileContent;", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "url", "Ljava/net/URL;", "contentType", "Lio/ktor/http/ContentType;", "(Ljava/net/URL;Lio/ktor/http/ContentType;)V", "uri", "Ljava/net/URI;", "contentLength", "", "(Ljava/net/URI;Lio/ktor/http/ContentType;Ljava/lang/Long;)V", "getContentLength", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getContentType", "()Lio/ktor/http/ContentType;", "getUri", "()Ljava/net/URI;", "readFrom", "Lio/ktor/utils/io/ByteReadChannel;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class URIFileContent extends OutgoingContent.ReadChannelContent {
    private final Long contentLength;
    private final ContentType contentType;
    private final URI uri;

    public /* synthetic */ URIFileContent(URI uri, ContentType contentType, Long l10, int i10, h hVar) {
        this(uri, (i10 & 2) != 0 ? FileContentTypeKt.defaultForFilePath(ContentType.INSTANCE, uri.getPath()) : contentType, (i10 & 4) != 0 ? null : l10);
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Long getContentLength() {
        return this.contentLength;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public ContentType getContentType() {
        return this.contentType;
    }

    public final URI getUri() {
        return this.uri;
    }

    @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
    public ByteReadChannel readFrom() {
        return ReadingKt.toByteReadChannel$default(this.uri.toURL().openStream(), null, ByteBufferPoolKt.getKtorDefaultPool(), 1, null);
    }

    public URIFileContent(URI uri, ContentType contentType, Long l10) {
        this.uri = uri;
        this.contentType = contentType;
        this.contentLength = l10;
    }

    public /* synthetic */ URIFileContent(URL url, ContentType contentType, int i10, h hVar) {
        this(url, (i10 & 2) != 0 ? FileContentTypeKt.defaultForFilePath(ContentType.INSTANCE, url.getPath()) : contentType);
    }

    public URIFileContent(URL url, ContentType contentType) {
        this(url.toURI(), contentType, null, 4, null);
    }
}
