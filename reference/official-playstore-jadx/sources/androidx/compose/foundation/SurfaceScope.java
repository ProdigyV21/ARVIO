package androidx.compose.foundation;

import android.view.Surface;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001JV\u0010\u000b\u001a\u00020\t*\u00020\u00022A\u0010\u000b\u001a=\u0012\u0004\u0012\u00020\u0002\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0003¢\u0006\u0002\b\nH&¢\u0006\u0004\b\u000b\u0010\fJ,\u0010\u000e\u001a\u00020\t*\u00020\u00022\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\r¢\u0006\u0002\b\nH&¢\u0006\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/SurfaceScope;", "", "Landroid/view/Surface;", "Lkotlin/Function3;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "width", "height", "Lx6/t0;", "Lx6/n;", "onChanged", "(Landroid/view/Surface;Lr7/q;)V", "Lkotlin/Function1;", "onDestroyed", "(Landroid/view/Surface;Lr7/l;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface SurfaceScope {
    void onChanged(Surface surface, q<? super Surface, ? super Integer, ? super Integer, t0> qVar);

    void onDestroyed(Surface surface, l<? super Surface, t0> lVar);
}
