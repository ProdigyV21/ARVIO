package androidx.media3.exoplayer.drm;

import androidx.media3.common.DrmInitData;
import androidx.media3.exoplayer.source.LoadEventInfo;
import com.google.common.collect.e1;
import com.google.common.collect.h1;
import java.util.List;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* JADX INFO: loaded from: classes3.dex */
public final class KeyRequestInfo {
    public final h1 loadInfos;
    public final h1 schemeDatas;

    public static final class Builder {
        private final e1 loadEventInfos = h1.k();
        private h1 schemeDatas;

        public Builder addLoadInfo(LoadEventInfo loadEventInfo) {
            this.loadEventInfos.c(loadEventInfo);
            return this;
        }

        @SideEffectFree
        public KeyRequestInfo build() {
            return new KeyRequestInfo(this);
        }

        public Builder setSchemeDatas(List<DrmInitData.SchemeData> list) {
            this.schemeDatas = h1.n(list);
            return this;
        }
    }

    private KeyRequestInfo(Builder builder) {
        this.loadInfos = builder.loadEventInfos.f();
        this.schemeDatas = builder.schemeDatas;
    }
}
