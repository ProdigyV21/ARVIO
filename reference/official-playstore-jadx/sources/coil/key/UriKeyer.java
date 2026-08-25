package coil.key;

import android.content.Utils;
import android.net.Uri;
import coil.request.Options;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcoil/key/UriKeyer;", "Lcoil/key/Keyer;", "Landroid/net/Uri;", "()V", "key", "", "data", "options", "Lcoil/request/Options;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class UriKeyer implements Keyer<Uri> {
    @Override // coil.key.Keyer
    public String key(Uri data, Options options) {
        if (!p.a(data.getScheme(), "android.resource")) {
            return data.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(data);
        sb2.append('-');
        sb2.append(Utils.getNightMode(options.getContext().getResources().getConfiguration()));
        return sb2.toString();
    }
}
