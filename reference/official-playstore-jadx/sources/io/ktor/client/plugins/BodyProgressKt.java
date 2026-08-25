package io.ktor.client.plugins;

import d7.d;
import io.ktor.client.plugins.observer.DelegatedCallKt;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.utils.ByteChannelUtilsKt;
import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a^\u0010\u000b\u001a\u00020\u0000*\u00020\u00002F\u0010\n\u001aB\b\u0001\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a^\u0010\u000e\u001a\u00020\b*\u00020\r2H\u0010\n\u001aD\b\u0001\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0001ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a^\u0010\u0010\u001a\u00020\b*\u00020\r2H\u0010\n\u001aD\b\u0001\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000f\"]\u0010\u0012\u001aH\u0012D\u0012B\b\u0001\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00010\u00118\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013\"]\u0010\u0014\u001aH\u0012D\u0012B\b\u0001\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00010\u00118\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lio/ktor/client/statement/HttpResponse;", "Lkotlin/Function3;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "bytesSentTotal", "contentLength", "Ld7/d;", "Lx6/t0;", "", "listener", "withObservableDownload", "(Lio/ktor/client/statement/HttpResponse;Lr7/q;)Lio/ktor/client/statement/HttpResponse;", "Lio/ktor/client/request/HttpRequestBuilder;", "onDownload", "(Lio/ktor/client/request/HttpRequestBuilder;Lr7/q;)V", "onUpload", "Lio/ktor/util/AttributeKey;", "UploadProgressListenerAttributeKey", "Lio/ktor/util/AttributeKey;", "DownloadProgressListenerAttributeKey", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BodyProgressKt {
    private static final AttributeKey<q<Long, Long, d<? super t0>, Object>> UploadProgressListenerAttributeKey = new AttributeKey<>("UploadProgressListenerAttributeKey");
    private static final AttributeKey<q<Long, Long, d<? super t0>, Object>> DownloadProgressListenerAttributeKey = new AttributeKey<>("DownloadProgressListenerAttributeKey");

    public static final void onDownload(HttpRequestBuilder httpRequestBuilder, q<? super Long, ? super Long, ? super d<? super t0>, ? extends Object> qVar) {
        if (qVar == null) {
            httpRequestBuilder.getAttributes().remove(DownloadProgressListenerAttributeKey);
        } else {
            httpRequestBuilder.getAttributes().put(DownloadProgressListenerAttributeKey, qVar);
        }
    }

    public static final void onUpload(HttpRequestBuilder httpRequestBuilder, q<? super Long, ? super Long, ? super d<? super t0>, ? extends Object> qVar) {
        if (qVar == null) {
            httpRequestBuilder.getAttributes().remove(UploadProgressListenerAttributeKey);
        } else {
            httpRequestBuilder.getAttributes().put(UploadProgressListenerAttributeKey, qVar);
        }
    }

    public static final HttpResponse withObservableDownload(HttpResponse httpResponse, q<? super Long, ? super Long, ? super d<? super t0>, ? extends Object> qVar) {
        return DelegatedCallKt.wrapWithContent(httpResponse.getCall(), ByteChannelUtilsKt.observable(httpResponse.getContent(), httpResponse.getCoroutineContext(), HttpMessagePropertiesKt.contentLength(httpResponse), qVar)).getResponse();
    }
}
