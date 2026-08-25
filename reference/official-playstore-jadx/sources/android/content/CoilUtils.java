package android.content;

import android.content.Context;
import android.view.View;
import coil.request.ImageResult;
import gb.g;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import q7.n;
import x6.e;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcoil/util/CoilUtils;", "", "<init>", "()V", "Landroid/view/View;", "view", "Lx6/t0;", "dispose", "(Landroid/view/View;)V", "Lcoil/request/ImageResult;", "result", "(Landroid/view/View;)Lcoil/request/ImageResult;", "Landroid/content/Context;", "context", "Lgb/g;", "createDefaultCache", "(Landroid/content/Context;)Lgb/g;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CoilUtils {
    public static final CoilUtils INSTANCE = new CoilUtils();

    private CoilUtils() {
    }

    @n
    @e
    public static final g createDefaultCache(Context context) {
        Utils.unsupported();
        throw new KotlinNothingValueException();
    }

    @n
    public static final void dispose(View view) {
        Utils.getRequestManager(view).dispose();
    }

    @n
    public static final ImageResult result(View view) {
        return Utils.getRequestManager(view).getResult();
    }
}
