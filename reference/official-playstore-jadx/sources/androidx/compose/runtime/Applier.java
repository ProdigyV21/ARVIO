package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0011\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u000f\u0010\u0004\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0006\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00028\u0000H&¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0003H&¢\u0006\u0004\b\n\u0010\u0005J\u001f\u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00028\u0000H&¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00028\u0000H&¢\u0006\u0004\b\u0010\u0010\u000fJ\u001f\u0010\u0012\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000bH&¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000bH&¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0003H&¢\u0006\u0004\b\u0018\u0010\u0005R\u0014\u0010\u001b\u001a\u00028\u00008&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001cÀ\u0006\u0003"}, d2 = {"Landroidx/compose/runtime/Applier;", "N", "", "Lx6/t0;", "onBeginChanges", "()V", "onEndChanges", "node", "down", "(Ljava/lang/Object;)V", "up", "", "index", "instance", "insertTopDown", "(ILjava/lang/Object;)V", "insertBottomUp", "count", "remove", "(II)V", "from", "to", "move", "(III)V", "clear", "getCurrent", "()Ljava/lang/Object;", "current", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Applier<N> {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <N> void onBeginChanges(Applier<N> applier) {
            a.a(applier);
        }

        @Deprecated
        public static <N> void onEndChanges(Applier<N> applier) {
            a.b(applier);
        }
    }

    void clear();

    void down(N node);

    N getCurrent();

    void insertBottomUp(int index, N instance);

    void insertTopDown(int index, N instance);

    void move(int from, int to, int count);

    void onBeginChanges();

    void onEndChanges();

    void remove(int index, int count);

    void up();
}
