package gb;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lgb/c0;", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface c0 {

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lgb/c0$a;", "", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface a {
        p0 a(k0 k0Var);

        k0 request();
    }

    p0 intercept(a aVar);
}
