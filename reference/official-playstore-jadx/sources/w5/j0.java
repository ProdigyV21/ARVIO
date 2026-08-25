package w5;

import android.os.Message;
import com.google.common.util.concurrent.r0;
import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
public final class j0 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return r0.e(Long.valueOf(((Message) obj).getWhen()), Long.valueOf(((Message) obj2).getWhen()));
    }
}
