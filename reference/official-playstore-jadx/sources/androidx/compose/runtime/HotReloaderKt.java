package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.s;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a%\u0010\u000e\u001a\u0018\u0012\u0014\u0012\u0012\u0012\b\u0012\u00060\u000bj\u0002`\f\u0012\u0004\u0012\u00020\r0\n0\tH\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u000f\u0010\u0010\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"", "context", "Lx6/t0;", "simulateHotReload", "(Ljava/lang/Object;)V", "", "key", "invalidateGroupsWithKey", "(I)V", "", "Lx6/x;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "", "currentCompositionErrors", "()Ljava/util/List;", "clearCompositionErrors", "()V", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HotReloaderKt {
    public static final void clearCompositionErrors() {
        HotReloader.INSTANCE.clearErrors$runtime_release();
    }

    public static final List<x> currentCompositionErrors() {
        List<RecomposerErrorInfo> currentErrors$runtime_release = HotReloader.INSTANCE.getCurrentErrors$runtime_release();
        ArrayList arrayList = new ArrayList(s.U(currentErrors$runtime_release, 10));
        for (RecomposerErrorInfo recomposerErrorInfo : currentErrors$runtime_release) {
            arrayList.add(new x(recomposerErrorInfo.getCause(), Boolean.valueOf(recomposerErrorInfo.getRecoverable())));
        }
        return arrayList;
    }

    public static final void invalidateGroupsWithKey(int i10) {
        HotReloader.INSTANCE.invalidateGroupsWithKey$runtime_release(i10);
    }

    public static final void simulateHotReload(Object obj) {
        HotReloader.INSTANCE.simulateHotReload$runtime_release(obj);
    }
}
