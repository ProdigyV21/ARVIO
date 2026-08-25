package io.ktor.http;

import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.text.u;
import r7.p;
import t7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u0003J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lio/ktor/http/HeaderValueWithParameters;", "", "content", "", "parameters", "", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Ljava/util/List;)V", "getContent", "()Ljava/lang/String;", "getParameters", "()Ljava/util/List;", "parameter", ContentDisposition.Parameters.Name, "toString", "Companion", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class HeaderValueWithParameters {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String content;
    private final List<HeaderValueParam> parameters;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JA\u0010\u000b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0006\u001a\u00020\u00052\u001e\u0010\n\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"Lio/ktor/http/HeaderValueWithParameters$Companion;", "", "<init>", "()V", "R", "", "value", "Lkotlin/Function2;", "", "Lio/ktor/http/HeaderValueParam;", "init", "parse", "(Ljava/lang/String;Lr7/p;)Ljava/lang/Object;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final <R> R parse(String value, p<? super String, ? super List<HeaderValueParam>, ? extends R> init) {
            HeaderValue headerValue = (HeaderValue) x.w0(HttpHeaderValueParserKt.parseHeaderValue(value));
            return (R) init.invoke(headerValue.getValue(), headerValue.getParams());
        }

        private Companion() {
        }
    }

    public HeaderValueWithParameters(String str, List<HeaderValueParam> list) {
        this.content = str;
        this.parameters = list;
    }

    public final String getContent() {
        return this.content;
    }

    public final List<HeaderValueParam> getParameters() {
        return this.parameters;
    }

    public final String parameter(String name) {
        int iW = a.w(this.parameters);
        if (iW < 0) {
            return null;
        }
        int i10 = 0;
        while (true) {
            HeaderValueParam headerValueParam = this.parameters.get(i10);
            if (u.L(headerValueParam.getName(), name, true)) {
                return headerValueParam.getValue();
            }
            if (i10 == iW) {
                return null;
            }
            i10++;
        }
    }

    public String toString() {
        if (this.parameters.isEmpty()) {
            return this.content;
        }
        int length = this.content.length();
        int i10 = 0;
        int length2 = 0;
        for (HeaderValueParam headerValueParam : this.parameters) {
            length2 += headerValueParam.getValue().length() + headerValueParam.getName().length() + 3;
        }
        StringBuilder sb2 = new StringBuilder(length + length2);
        sb2.append(this.content);
        int iW = a.w(this.parameters);
        if (iW >= 0) {
            while (true) {
                HeaderValueParam headerValueParam2 = this.parameters.get(i10);
                sb2.append("; ");
                sb2.append(headerValueParam2.getName());
                sb2.append("=");
                String value = headerValueParam2.getValue();
                if (HeaderValueWithParametersKt.needQuotes(value)) {
                    sb2.append(HeaderValueWithParametersKt.quote(value));
                } else {
                    sb2.append(value);
                }
                if (i10 == iW) {
                    break;
                }
                i10++;
            }
        }
        return sb2.toString();
    }

    public /* synthetic */ HeaderValueWithParameters(String str, List list, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? z.f19728i : list);
    }
}
