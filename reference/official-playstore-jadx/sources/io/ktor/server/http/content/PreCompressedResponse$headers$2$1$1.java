package io.ktor.server.http.content;

import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", ContentDisposition.Parameters.Name, "", "<anonymous parameter 1>", "invoke", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PreCompressedResponse$headers$2$1$1 extends r implements p<String, String, Boolean> {
    public static final PreCompressedResponse$headers$2$1$1 INSTANCE = new PreCompressedResponse$headers$2$1$1();

    public PreCompressedResponse$headers$2$1$1() {
        super(2);
    }

    @Override // r7.p
    public final Boolean invoke(String str, String str2) {
        return Boolean.valueOf(!str.equalsIgnoreCase(HttpHeaders.INSTANCE.getContentLength()));
    }
}
