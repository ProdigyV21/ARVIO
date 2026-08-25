package androidx.media3.datasource;

import androidx.media3.datasource.DefaultHttpDataSource;
import com.google.common.base.r;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3453i;

    public /* synthetic */ d(int i10) {
        this.f3453i = i10;
    }

    @Override // com.google.common.base.r
    public final boolean apply(Object obj) {
        switch (this.f3453i) {
            case 0:
                return DefaultHttpDataSource.NullFilteringHeadersMap.lambda$entrySet$1((Map.Entry) obj);
            case 1:
                return DefaultHttpDataSource.NullFilteringHeadersMap.lambda$keySet$0((String) obj);
            default:
                return e.a((String) obj);
        }
    }
}
