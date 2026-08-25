package io.ktor.server.config;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class MergedApplicationConfig$secondKeys$2 extends r implements a<Set<? extends String>> {
    final /* synthetic */ MergedApplicationConfig this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MergedApplicationConfig$secondKeys$2(MergedApplicationConfig mergedApplicationConfig) {
        super(0);
        this.this$0 = mergedApplicationConfig;
    }

    @Override // r7.a
    public final Set<String> invoke() {
        return this.this$0.getSecond().keys();
    }
}
