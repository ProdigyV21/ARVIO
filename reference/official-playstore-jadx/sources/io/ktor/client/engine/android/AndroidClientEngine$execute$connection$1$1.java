package io.ktor.client.engine.android;

import java.net.HttpURLConnection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "key", "value", "Lx6/t0;", "invoke", "(Ljava/lang/String;Ljava/lang/String;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidClientEngine$execute$connection$1$1 extends r implements p<String, String, t0> {
    final /* synthetic */ HttpURLConnection $this_apply;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidClientEngine$execute$connection$1$1(HttpURLConnection httpURLConnection) {
        super(2);
        this.$this_apply = httpURLConnection;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (String) obj2);
        return t0.f22605a;
    }

    public final void invoke(String str, String str2) {
        this.$this_apply.addRequestProperty(str, str2);
    }
}
