package io.ktor.server.http.content;

import io.ktor.http.ContentType;
import java.io.File;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentType;", "url", "Ljava/net/URL;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PreCompressedKt$bestCompressionFit$4$resolved$1 extends r implements l<URL, ContentType> {
    final /* synthetic */ String $compressed;
    final /* synthetic */ l<URL, ContentType> $contentType;
    final /* synthetic */ String $resource;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PreCompressedKt$bestCompressionFit$4$resolved$1(String str, String str2, l<? super URL, ContentType> lVar) {
        super(1);
        this.$compressed = str;
        this.$resource = str2;
        this.$contentType = lVar;
    }

    @Override // r7.l
    public final ContentType invoke(URL url) {
        String path = url.getPath();
        String str = this.$compressed;
        String str2 = File.separator;
        Pattern patternCompile = Pattern.compile(Matcher.quoteReplacement(o.F0(str, str2)).concat("$"));
        return (ContentType) this.$contentType.invoke(new URL(url.getProtocol(), url.getHost(), url.getPort(), patternCompile.matcher(path).replaceAll(o.F0(this.$resource, str2))));
    }
}
