package coil.map;

import android.net.Uri;
import coil.request.Options;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcoil/map/StringMapper;", "Lcoil/map/Mapper;", "", "Landroid/net/Uri;", "()V", "map", "data", "options", "Lcoil/request/Options;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class StringMapper implements Mapper<String, Uri> {
    @Override // coil.map.Mapper
    public Uri map(String data, Options options) {
        return Uri.parse(data);
    }
}
