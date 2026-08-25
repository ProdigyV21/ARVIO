package io.github.jan.supabase.network;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class KtorSupabaseHttpClient$prepareRequest$response$1 extends r implements a<String> {
    final /* synthetic */ KtorSupabaseHttpClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtorSupabaseHttpClient$prepareRequest$response$1(KtorSupabaseHttpClient ktorSupabaseHttpClient) {
        super(0);
        this.this$0 = ktorSupabaseHttpClient;
    }

    @Override // r7.a
    public final String invoke() {
        return "Request timed out after " + this.this$0.requestTimeout + " ms";
    }
}
