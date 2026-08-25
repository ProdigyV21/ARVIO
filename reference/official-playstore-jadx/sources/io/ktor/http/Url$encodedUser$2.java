package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class Url$encodedUser$2 extends r implements a<String> {
    final /* synthetic */ Url this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Url$encodedUser$2(Url url) {
        super(0);
        this.this$0 = url;
    }

    @Override // r7.a
    public final String invoke() {
        if (this.this$0.getUser() == null) {
            return null;
        }
        if (this.this$0.getUser().length() == 0) {
            return "";
        }
        int length = this.this$0.getProtocol().getName().length() + 3;
        return this.this$0.urlString.substring(length, o.g0(this.this$0.urlString, new char[]{':', '@'}, length, false));
    }
}
