package gb;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lgb/i;", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface i extends Cloneable {

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lgb/i$a;", "", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface a {
        lb.i a(k0 k0Var);
    }

    void K0(j jVar);

    void cancel();

    boolean k();

    k0 request();
}
