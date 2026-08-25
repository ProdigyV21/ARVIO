package io.ktor.server.plugins;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.RequestConnectionPoint;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.request.ApplicationRequest;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001b\u0010\u0007\u001a\u00020\u0002*\u00020\b8F¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u001b\u0010\r\u001a\u00020\u000e*\u00020\u000f8F¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"MutableOriginConnectionPointKey", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/plugins/MutableOriginConnectionPoint;", "getMutableOriginConnectionPointKey$annotations", "()V", "getMutableOriginConnectionPointKey", "()Lio/ktor/util/AttributeKey;", "mutableOriginConnectionPoint", "Lio/ktor/server/application/ApplicationCall;", "getMutableOriginConnectionPoint$annotations", "(Lio/ktor/server/application/ApplicationCall;)V", "getMutableOriginConnectionPoint", "(Lio/ktor/server/application/ApplicationCall;)Lio/ktor/server/plugins/MutableOriginConnectionPoint;", TtmlNode.ATTR_TTS_ORIGIN, "Lio/ktor/http/RequestConnectionPoint;", "Lio/ktor/server/request/ApplicationRequest;", "getOrigin$annotations", "(Lio/ktor/server/request/ApplicationRequest;)V", "getOrigin", "(Lio/ktor/server/request/ApplicationRequest;)Lio/ktor/http/RequestConnectionPoint;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OriginConnectionPointKt {
    private static final AttributeKey<MutableOriginConnectionPoint> MutableOriginConnectionPointKey = new AttributeKey<>("MutableOriginConnectionPointKey");

    public static final MutableOriginConnectionPoint getMutableOriginConnectionPoint(ApplicationCall applicationCall) {
        return (MutableOriginConnectionPoint) applicationCall.getAttributes().computeIfAbsent(MutableOriginConnectionPointKey, new OriginConnectionPointKt$mutableOriginConnectionPoint$1(applicationCall));
    }

    public static /* synthetic */ void getMutableOriginConnectionPoint$annotations(ApplicationCall applicationCall) {
    }

    public static final AttributeKey<MutableOriginConnectionPoint> getMutableOriginConnectionPointKey() {
        return MutableOriginConnectionPointKey;
    }

    @e
    public static /* synthetic */ void getMutableOriginConnectionPointKey$annotations() {
    }

    public static final RequestConnectionPoint getOrigin(ApplicationRequest applicationRequest) {
        MutableOriginConnectionPoint mutableOriginConnectionPoint = (MutableOriginConnectionPoint) applicationRequest.getCall().getAttributes().getOrNull(MutableOriginConnectionPointKey);
        return mutableOriginConnectionPoint != null ? mutableOriginConnectionPoint : applicationRequest.getLocal();
    }

    public static /* synthetic */ void getOrigin$annotations(ApplicationRequest applicationRequest) {
    }
}
