package androidx.compose.runtime;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B/\u0012&\u0010\t\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0003¢\u0006\u0002\b\b¢\u0006\u0004\b\n\u0010\u000bR7\u0010\t\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0003¢\u0006\u0002\b\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/MovableContent;", "P", "", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "parameter", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "<init>", "(Lr7/q;)V", "Lr7/q;", "getContent", "()Lr7/q;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MovableContent<P> {
    public static final int $stable = 0;
    private final q<P, Composer, Integer, t0> content;

    /* JADX WARN: Multi-variable type inference failed */
    public MovableContent(q<? super P, ? super Composer, ? super Integer, t0> qVar) {
        this.content = qVar;
    }

    public final q<P, Composer, Integer, t0> getContent() {
        return this.content;
    }
}
