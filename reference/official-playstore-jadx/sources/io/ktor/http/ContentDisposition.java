package io.ktor.http;

import io.ktor.http.HeaderValueWithParameters;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 \u00182\u00020\u0001:\u0002\u0018\u0019B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J \u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\rJ\u0014\u0010\u0016\u001a\u00020\u00002\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t¨\u0006\u001a"}, d2 = {"Lio/ktor/http/ContentDisposition;", "Lio/ktor/http/HeaderValueWithParameters;", "disposition", "", "parameters", "", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Ljava/util/List;)V", "getDisposition", "()Ljava/lang/String;", Parameters.Name, "getName", "equals", "", "other", "", "hashCode", "", "withParameter", "key", "value", "encodeValue", "withParameters", "newParameters", "Companion", "Parameters", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ContentDisposition extends HeaderValueWithParameters {
    private static final ContentDisposition Attachment;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ContentDisposition File;
    private static final ContentDisposition Inline;
    private static final ContentDisposition Mixed;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\u0010"}, d2 = {"Lio/ktor/http/ContentDisposition$Companion;", "", "()V", "Attachment", "Lio/ktor/http/ContentDisposition;", "getAttachment", "()Lio/ktor/http/ContentDisposition;", "File", "getFile", "Inline", "getInline", "Mixed", "getMixed", "parse", "value", "", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final ContentDisposition getAttachment() {
            return ContentDisposition.Attachment;
        }

        public final ContentDisposition getFile() {
            return ContentDisposition.File;
        }

        public final ContentDisposition getInline() {
            return ContentDisposition.Inline;
        }

        public final ContentDisposition getMixed() {
            return ContentDisposition.Mixed;
        }

        public final ContentDisposition parse(String value) {
            HeaderValueWithParameters.Companion companion = HeaderValueWithParameters.INSTANCE;
            HeaderValue headerValue = (HeaderValue) x.w0(HttpHeaderValueParserKt.parseHeaderValue(value));
            return new ContentDisposition(headerValue.getValue(), headerValue.getParams());
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lio/ktor/http/ContentDisposition$Parameters;", "", "()V", "CreationDate", "", "FileName", "FileNameAsterisk", "Handling", "ModificationDate", "Name", "ReadDate", "Size", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Parameters {
        public static final String CreationDate = "creation-date";
        public static final String FileName = "filename";
        public static final String FileNameAsterisk = "filename*";
        public static final String Handling = "handling";
        public static final Parameters INSTANCE = new Parameters();
        public static final String ModificationDate = "modification-date";
        public static final String Name = "name";
        public static final String ReadDate = "read-date";
        public static final String Size = "size";

        private Parameters() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        int i10 = 2;
        File = new ContentDisposition("file", 0 == true ? 1 : 0, i10, 0 == true ? 1 : 0);
        Mixed = new ContentDisposition("mixed", 0 == true ? 1 : 0, i10, 0 == true ? 1 : 0);
        Attachment = new ContentDisposition("attachment", 0 == true ? 1 : 0, i10, 0 == true ? 1 : 0);
        Inline = new ContentDisposition("inline", 0 == true ? 1 : 0, i10, 0 == true ? 1 : 0);
    }

    public /* synthetic */ ContentDisposition(String str, List list, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? z.f19728i : list);
    }

    public static /* synthetic */ ContentDisposition withParameter$default(ContentDisposition contentDisposition, String str, String str2, boolean z, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = true;
        }
        return contentDisposition.withParameter(str, str2, z);
    }

    public boolean equals(Object other) {
        if (!(other instanceof ContentDisposition)) {
            return false;
        }
        ContentDisposition contentDisposition = (ContentDisposition) other;
        return p.a(getDisposition(), contentDisposition.getDisposition()) && p.a(getParameters(), contentDisposition.getParameters());
    }

    public final String getDisposition() {
        return getContent();
    }

    public final String getName() {
        return parameter(Parameters.Name);
    }

    public int hashCode() {
        return getParameters().hashCode() + (getDisposition().hashCode() * 31);
    }

    public final ContentDisposition withParameter(String key, String value, boolean encodeValue) {
        if (encodeValue) {
            value = ContentDispositionKt.encodeContentDispositionAttribute(key, value);
        }
        return new ContentDisposition(getDisposition(), x.H0(new HeaderValueParam(key, value), getParameters()));
    }

    public final ContentDisposition withParameters(List<HeaderValueParam> newParameters) {
        return new ContentDisposition(getDisposition(), x.I0(getParameters(), newParameters));
    }

    public ContentDisposition(String str, List<HeaderValueParam> list) {
        super(str, list);
    }
}
