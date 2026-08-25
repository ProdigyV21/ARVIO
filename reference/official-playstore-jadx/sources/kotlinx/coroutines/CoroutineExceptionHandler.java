package kotlinx.coroutines;

import d7.j;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineExceptionHandler;", "Ld7/j$a;", "ka/g0", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface CoroutineExceptionHandler extends j.a {
    void handleException(j jVar, Throwable th);
}
