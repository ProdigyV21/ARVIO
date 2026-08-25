package hb;

import androidx.compose.material3.d;
import gb.t;
import java.net.UnknownHostException;
import java.util.List;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f15965b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f15966c;

    public a(String str, List list) {
        this.f15965b = str;
        this.f15966c = list;
    }

    @Override // gb.t
    public final List lookup(String str) throws UnknownHostException {
        String str2 = this.f15965b;
        if (p.a(str2, str)) {
            return this.f15966c;
        }
        throw new UnknownHostException(d.n("BootstrapDns called for ", str, " instead of ", str2));
    }
}
