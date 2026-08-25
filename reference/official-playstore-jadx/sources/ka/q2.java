package ka;

import d7.j;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Lka/q2;", "S", "Ld7/j$a;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface q2<S> extends j.a {
    void restoreThreadContext(d7.j jVar, Object obj);

    Object updateThreadContext(d7.j jVar);
}
