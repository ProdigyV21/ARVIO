package coil.map;

import android.content.Context;
import android.net.Uri;
import coil.request.Options;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0002J\u001c\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcoil/map/ResourceIntMapper;", "Lcoil/map/Mapper;", "", "Landroid/net/Uri;", "()V", "isApplicable", "", "data", "context", "Landroid/content/Context;", "map", "options", "Lcoil/request/Options;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ResourceIntMapper implements Mapper<Integer, Uri> {
    private final boolean isApplicable(int data, Context context) {
        return context.getResources().getResourceEntryName(data) != null;
    }

    @Override // coil.map.Mapper
    public /* bridge */ /* synthetic */ Uri map(Integer num, Options options) {
        return map(num.intValue(), options);
    }

    public Uri map(int data, Options options) {
        if (!isApplicable(data, options.getContext())) {
            return null;
        }
        return Uri.parse("android.resource://" + options.getContext().getPackageName() + '/' + data);
    }
}
