package io.ktor.http;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.URLBuilder;
import io.ktor.http.Url;
import java.net.URI;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0086\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\t"}, d2 = {TtmlNode.ATTR_TTS_ORIGIN, "", "Lio/ktor/http/URLBuilder$Companion;", "getOrigin", "(Lio/ktor/http/URLBuilder$Companion;)Ljava/lang/String;", "invoke", "Lio/ktor/http/Url;", "Lio/ktor/http/Url$Companion;", "fullUrl", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class URLBuilderJvmKt {
    public static final String getOrigin(URLBuilder.Companion companion) {
        return "http://localhost";
    }

    public static final Url invoke(Url.Companion companion, String str) {
        URLBuilder uRLBuilder = new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null);
        URLUtilsJvmKt.takeFrom(uRLBuilder, new URI(str));
        return uRLBuilder.build();
    }
}
