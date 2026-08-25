package androidx.compose.foundation;

import android.view.Surface;
import d7.d;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.s;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001Jw\u0010\u000e\u001a\u00020\f2f\u0010\u000e\u001ab\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0002¢\u0006\u0002\b\rH&¢\u0006\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/AndroidExternalSurfaceScope;", "", "Lkotlin/Function5;", "Landroidx/compose/foundation/SurfaceCoroutineScope;", "Landroid/view/Surface;", "Lx6/y;", ContentDisposition.Parameters.Name, "surface", "", "width", "height", "Ld7/d;", "Lx6/t0;", "Lx6/n;", "onSurface", "(Lr7/s;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface AndroidExternalSurfaceScope {
    void onSurface(s<? super SurfaceCoroutineScope, ? super Surface, ? super Integer, ? super Integer, ? super d<? super t0>, ? extends Object> onSurface);
}
