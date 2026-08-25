package io.ktor.client.request.forms;

import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.HttpUrlEncodedKt;
import io.ktor.http.Parameters;
import io.ktor.http.content.OutgoingContent;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lio/ktor/client/request/forms/FormDataContent;", "Lio/ktor/http/content/OutgoingContent$ByteArrayContent;", "formData", "Lio/ktor/http/Parameters;", "(Lio/ktor/http/Parameters;)V", "content", "", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "contentType", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "getFormData", "()Lio/ktor/http/Parameters;", "bytes", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FormDataContent extends OutgoingContent.ByteArrayContent {
    private final byte[] content;
    private final long contentLength;
    private final ContentType contentType;
    private final Parameters formData;

    public FormDataContent(Parameters parameters) {
        this.formData = parameters;
        String strFormUrlEncode = HttpUrlEncodedKt.formUrlEncode(parameters);
        Charset charset = a.f19924a;
        this.content = p.a(charset, charset) ? strFormUrlEncode.getBytes(charset) : CharsetJVMKt.encodeToByteArray(charset.newEncoder(), strFormUrlEncode, 0, strFormUrlEncode.length());
        this.contentLength = r5.length;
        this.contentType = ContentTypesKt.withCharset(ContentType.Application.INSTANCE.getFormUrlEncoded(), charset);
    }

    @Override // io.ktor.http.content.OutgoingContent.ByteArrayContent
    /* JADX INFO: renamed from: bytes, reason: from getter */
    public byte[] getContent() {
        return this.content;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Long getContentLength() {
        return Long.valueOf(this.contentLength);
    }

    @Override // io.ktor.http.content.OutgoingContent
    public ContentType getContentType() {
        return this.contentType;
    }

    public final Parameters getFormData() {
        return this.formData;
    }
}
