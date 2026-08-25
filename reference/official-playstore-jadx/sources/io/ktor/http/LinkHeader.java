package io.ktor.http;

import androidx.compose.material3.d;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.text.o;
import t7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006\"\u00020\u0003¢\u0006\u0002\u0010\u0007B%\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b¢\u0006\u0002\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lio/ktor/http/LinkHeader;", "Lio/ktor/http/HeaderValueWithParameters;", "uri", "", Parameters.Rel, "(Ljava/lang/String;Ljava/lang/String;)V", "", "(Ljava/lang/String;[Ljava/lang/String;)V", "", Parameters.Type, "Lio/ktor/http/ContentType;", "(Ljava/lang/String;Ljava/util/List;Lio/ktor/http/ContentType;)V", "params", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Ljava/util/List;)V", "getUri", "()Ljava/lang/String;", "Parameters", "Rel", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LinkHeader extends HeaderValueWithParameters {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lio/ktor/http/LinkHeader$Parameters;", "", "()V", "Anchor", "", "HrefLang", "Media", "Rel", Parameters.Rev, "Title", "Type", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Parameters {
        public static final String Anchor = "anchor";
        public static final String HrefLang = "hreflang";
        public static final Parameters INSTANCE = new Parameters();
        public static final String Media = "media";
        public static final String Rel = "rel";
        public static final String Rev = "Rev";
        public static final String Title = "title";
        public static final String Type = "type";

        private Parameters() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lio/ktor/http/LinkHeader$Rel;", "", "()V", "DnsPrefetch", "", "Next", "PreConnect", "PreLoad", "PreRender", "Prefetch", "Stylesheet", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Rel {
        public static final String DnsPrefetch = "dns-prefetch";
        public static final Rel INSTANCE = new Rel();
        public static final String Next = "next";
        public static final String PreConnect = "preconnect";
        public static final String PreLoad = "preload";
        public static final String PreRender = "prerender";
        public static final String Prefetch = "prefetch";
        public static final String Stylesheet = "stylesheet";

        private Rel() {
        }
    }

    public LinkHeader(String str, List<HeaderValueParam> list) {
        super(d.i('>', "<", str), list);
    }

    public final String getUri() {
        return o.s0(o.r0(getContent(), "<"), ">");
    }

    public LinkHeader(String str, String str2) {
        this(str, (List<HeaderValueParam>) Collections.singletonList(new HeaderValueParam(Parameters.Rel, str2)));
    }

    public LinkHeader(String str, String... strArr) {
        this(str, (List<HeaderValueParam>) Collections.singletonList(new HeaderValueParam(Parameters.Rel, r.f0(strArr, " ", null, null, null, 62))));
    }

    public LinkHeader(String str, List<String> list, ContentType contentType) {
        this(str, (List<HeaderValueParam>) a.E(new HeaderValueParam(Parameters.Rel, x.u0(list, " ", null, null, null, 62)), new HeaderValueParam(Parameters.Type, contentType.toString())));
    }
}
