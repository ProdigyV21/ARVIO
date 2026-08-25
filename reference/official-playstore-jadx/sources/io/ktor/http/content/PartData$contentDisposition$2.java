package io.ktor.http.content;

import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentDisposition;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PartData$contentDisposition$2 extends r implements a<ContentDisposition> {
    final /* synthetic */ PartData this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PartData$contentDisposition$2(PartData partData) {
        super(0);
        this.this$0 = partData;
    }

    @Override // r7.a
    public final ContentDisposition invoke() {
        String str = this.this$0.getHeaders().get(HttpHeaders.INSTANCE.getContentDisposition());
        if (str != null) {
            return ContentDisposition.INSTANCE.parse(str);
        }
        return null;
    }
}
