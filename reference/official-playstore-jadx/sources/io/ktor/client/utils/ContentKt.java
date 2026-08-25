package io.ktor.client.utils;

import d7.d;
import d7.j;
import e7.a;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import ka.v1;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a%\u0010\u0004\u001a\u00020\u0000*\u00020\u00002\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/http/content/OutgoingContent;", "Lkotlin/Function1;", "Lio/ktor/http/Headers;", "block", "wrapHeaders", "(Lio/ktor/http/content/OutgoingContent;Lr7/l;)Lio/ktor/http/content/OutgoingContent;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContentKt {
    public static final OutgoingContent wrapHeaders(OutgoingContent outgoingContent, l<? super Headers, ? extends Headers> lVar) {
        if (outgoingContent instanceof OutgoingContent.NoContent) {
            return new OutgoingContent.NoContent(lVar, outgoingContent) { // from class: io.ktor.client.utils.ContentKt.wrapHeaders.1
                final /* synthetic */ OutgoingContent $this_wrapHeaders;
                private final Headers headers;

                {
                    this.$this_wrapHeaders = outgoingContent;
                    this.headers = (Headers) lVar.invoke(outgoingContent.getHeaders());
                }

                @Override // io.ktor.http.content.OutgoingContent
                public Long getContentLength() {
                    return this.$this_wrapHeaders.getContentLength();
                }

                @Override // io.ktor.http.content.OutgoingContent
                public ContentType getContentType() {
                    return this.$this_wrapHeaders.getContentType();
                }

                @Override // io.ktor.http.content.OutgoingContent
                public Headers getHeaders() {
                    return this.headers;
                }

                @Override // io.ktor.http.content.OutgoingContent
                public HttpStatusCode getStatus() {
                    return this.$this_wrapHeaders.getStatus();
                }
            };
        }
        if (outgoingContent instanceof OutgoingContent.ReadChannelContent) {
            return new OutgoingContent.ReadChannelContent(lVar, outgoingContent) { // from class: io.ktor.client.utils.ContentKt.wrapHeaders.2
                final /* synthetic */ OutgoingContent $this_wrapHeaders;
                private final Headers headers;

                {
                    this.$this_wrapHeaders = outgoingContent;
                    this.headers = (Headers) lVar.invoke(outgoingContent.getHeaders());
                }

                @Override // io.ktor.http.content.OutgoingContent
                public Long getContentLength() {
                    return this.$this_wrapHeaders.getContentLength();
                }

                @Override // io.ktor.http.content.OutgoingContent
                public ContentType getContentType() {
                    return this.$this_wrapHeaders.getContentType();
                }

                @Override // io.ktor.http.content.OutgoingContent
                public Headers getHeaders() {
                    return this.headers;
                }

                @Override // io.ktor.http.content.OutgoingContent
                public HttpStatusCode getStatus() {
                    return this.$this_wrapHeaders.getStatus();
                }

                @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
                public ByteReadChannel readFrom() {
                    return ((OutgoingContent.ReadChannelContent) this.$this_wrapHeaders).readFrom();
                }

                @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
                public ByteReadChannel readFrom(x7.l range) {
                    return ((OutgoingContent.ReadChannelContent) this.$this_wrapHeaders).readFrom(range);
                }
            };
        }
        if (outgoingContent instanceof OutgoingContent.WriteChannelContent) {
            return new OutgoingContent.WriteChannelContent(lVar, outgoingContent) { // from class: io.ktor.client.utils.ContentKt.wrapHeaders.3
                final /* synthetic */ OutgoingContent $this_wrapHeaders;
                private final Headers headers;

                {
                    this.$this_wrapHeaders = outgoingContent;
                    this.headers = (Headers) lVar.invoke(outgoingContent.getHeaders());
                }

                @Override // io.ktor.http.content.OutgoingContent
                public Long getContentLength() {
                    return this.$this_wrapHeaders.getContentLength();
                }

                @Override // io.ktor.http.content.OutgoingContent
                public ContentType getContentType() {
                    return this.$this_wrapHeaders.getContentType();
                }

                @Override // io.ktor.http.content.OutgoingContent
                public Headers getHeaders() {
                    return this.headers;
                }

                @Override // io.ktor.http.content.OutgoingContent
                public HttpStatusCode getStatus() {
                    return this.$this_wrapHeaders.getStatus();
                }

                @Override // io.ktor.http.content.OutgoingContent.WriteChannelContent
                public Object writeTo(ByteWriteChannel byteWriteChannel, d<? super t0> dVar) {
                    Object objWriteTo = ((OutgoingContent.WriteChannelContent) this.$this_wrapHeaders).writeTo(byteWriteChannel, dVar);
                    return objWriteTo == a.f15033i ? objWriteTo : t0.f22605a;
                }
            };
        }
        if (outgoingContent instanceof OutgoingContent.ByteArrayContent) {
            return new OutgoingContent.ByteArrayContent(lVar, outgoingContent) { // from class: io.ktor.client.utils.ContentKt.wrapHeaders.4
                final /* synthetic */ OutgoingContent $this_wrapHeaders;
                private final Headers headers;

                {
                    this.$this_wrapHeaders = outgoingContent;
                    this.headers = (Headers) lVar.invoke(outgoingContent.getHeaders());
                }

                @Override // io.ktor.http.content.OutgoingContent.ByteArrayContent
                /* JADX INFO: renamed from: bytes */
                public byte[] getContent() {
                    return ((OutgoingContent.ByteArrayContent) this.$this_wrapHeaders).getContent();
                }

                @Override // io.ktor.http.content.OutgoingContent
                public Long getContentLength() {
                    return this.$this_wrapHeaders.getContentLength();
                }

                @Override // io.ktor.http.content.OutgoingContent
                public ContentType getContentType() {
                    return this.$this_wrapHeaders.getContentType();
                }

                @Override // io.ktor.http.content.OutgoingContent
                public Headers getHeaders() {
                    return this.headers;
                }

                @Override // io.ktor.http.content.OutgoingContent
                public HttpStatusCode getStatus() {
                    return this.$this_wrapHeaders.getStatus();
                }
            };
        }
        if (outgoingContent instanceof OutgoingContent.ProtocolUpgrade) {
            return new OutgoingContent.ProtocolUpgrade(lVar, outgoingContent) { // from class: io.ktor.client.utils.ContentKt.wrapHeaders.5
                final /* synthetic */ OutgoingContent $this_wrapHeaders;
                private final Headers headers;

                {
                    this.$this_wrapHeaders = outgoingContent;
                    this.headers = (Headers) lVar.invoke(outgoingContent.getHeaders());
                }

                @Override // io.ktor.http.content.OutgoingContent
                public Long getContentLength() {
                    return this.$this_wrapHeaders.getContentLength();
                }

                @Override // io.ktor.http.content.OutgoingContent
                public ContentType getContentType() {
                    return this.$this_wrapHeaders.getContentType();
                }

                @Override // io.ktor.http.content.OutgoingContent
                public Headers getHeaders() {
                    return this.headers;
                }

                @Override // io.ktor.http.content.OutgoingContent.ProtocolUpgrade
                public Object upgrade(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, j jVar, j jVar2, d<? super v1> dVar) {
                    return ((OutgoingContent.ProtocolUpgrade) this.$this_wrapHeaders).upgrade(byteReadChannel, byteWriteChannel, jVar, jVar2, dVar);
                }
            };
        }
        throw new NoWhenBranchMatchedException();
    }
}
