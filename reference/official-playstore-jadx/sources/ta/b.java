package ta;

import d7.j;
import java.util.Map;
import ka.q2;
import vc.d;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends d7.a implements q2 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a f22006l = new a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Map f22007i;

    public b(Map map) {
        super(f22006l);
        this.f22007i = map;
    }

    public static void q0(Map map) {
        if (map == null) {
            if (d.f22336a == null) {
                throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
            }
        } else if (d.f22336a == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
    }

    @Override // ka.q2
    public final void restoreThreadContext(j jVar, Object obj) {
        q0((Map) obj);
    }

    @Override // ka.q2
    public final Object updateThreadContext(j jVar) {
        if (d.f22336a == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        q0(this.f22007i);
        return null;
    }
}
