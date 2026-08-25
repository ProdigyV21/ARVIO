package androidx.media3.common;

import android.util.Pair;
import java.lang.Throwable;

/* JADX INFO: loaded from: classes3.dex */
public interface ErrorMessageProvider<T extends Throwable> {
    Pair<Integer, String> getErrorMessage(T t2);
}
