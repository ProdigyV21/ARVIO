package androidx.mediarouter.app;

import androidx.mediarouter.media.i0;
import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
public final class f implements Comparator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final f f4282i = new f();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((i0) obj).f4389d.compareToIgnoreCase(((i0) obj2).f4389d);
    }
}
