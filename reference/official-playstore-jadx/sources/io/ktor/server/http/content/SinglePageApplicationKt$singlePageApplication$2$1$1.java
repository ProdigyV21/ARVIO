package io.ktor.server.http.content;

import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "url", "Ljava/net/URL;", "invoke", "(Ljava/net/URL;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SinglePageApplicationKt$singlePageApplication$2$1$1 extends r implements l<URL, Boolean> {
    final /* synthetic */ l<String, Boolean> $ignoreConfig;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SinglePageApplicationKt$singlePageApplication$2$1$1(l<? super String, Boolean> lVar) {
        super(1);
        this.$ignoreConfig = lVar;
    }

    @Override // r7.l
    public final Boolean invoke(URL url) {
        return (Boolean) this.$ignoreConfig.invoke(url.getPath());
    }
}
