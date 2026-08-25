package coil;

import android.graphics.Bitmap;
import android.graphics.DecodeResult;
import android.graphics.Decoder;
import android.view.Size;
import coil.fetch.FetchResult;
import coil.fetch.Fetcher;
import coil.request.ErrorResult;
import coil.request.ImageRequest;
import coil.request.Options;
import coil.request.SuccessResult;
import coil.transition.Transition;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u0000 72\u00020\u0001:\u000278J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u0007\u0010\u0006J\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0017¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\fH\u0017¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\fH\u0017¢\u0006\u0004\b\u0011\u0010\u000fJ\u001f\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\fH\u0017¢\u0006\u0004\b\u0012\u0010\u000fJ!\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u0013H\u0017¢\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0017¢\u0006\u0004\b\u001a\u0010\u001bJ1\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0017¢\u0006\u0004\b\u001e\u0010\u001fJ'\u0010\"\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010!\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u0018H\u0017¢\u0006\u0004\b\"\u0010#J1\u0010%\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010!\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010$H\u0017¢\u0006\u0004\b%\u0010&J\u001f\u0010(\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020'H\u0017¢\u0006\u0004\b(\u0010)J\u001f\u0010*\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020'H\u0017¢\u0006\u0004\b*\u0010)J\u001f\u0010-\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010,\u001a\u00020+H\u0017¢\u0006\u0004\b-\u0010.J\u001f\u0010/\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010,\u001a\u00020+H\u0017¢\u0006\u0004\b/\u0010.J\u0017\u00100\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0017¢\u0006\u0004\b0\u0010\u0006J\u001f\u00102\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u000201H\u0017¢\u0006\u0004\b2\u00103J\u001f\u00105\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u000204H\u0017¢\u0006\u0004\b5\u00106ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00069À\u0006\u0003"}, d2 = {"Lcoil/EventListener;", "Lcoil/request/ImageRequest$Listener;", "Lcoil/request/ImageRequest;", "request", "Lx6/t0;", "onStart", "(Lcoil/request/ImageRequest;)V", "resolveSizeStart", "Lcoil/size/Size;", ContentDisposition.Parameters.Size, "resolveSizeEnd", "(Lcoil/request/ImageRequest;Lcoil/size/Size;)V", "", "input", "mapStart", "(Lcoil/request/ImageRequest;Ljava/lang/Object;)V", "output", "mapEnd", "keyStart", "", "keyEnd", "(Lcoil/request/ImageRequest;Ljava/lang/String;)V", "Lcoil/fetch/Fetcher;", "fetcher", "Lcoil/request/Options;", "options", "fetchStart", "(Lcoil/request/ImageRequest;Lcoil/fetch/Fetcher;Lcoil/request/Options;)V", "Lcoil/fetch/FetchResult;", "result", "fetchEnd", "(Lcoil/request/ImageRequest;Lcoil/fetch/Fetcher;Lcoil/request/Options;Lcoil/fetch/FetchResult;)V", "Lcoil/decode/Decoder;", "decoder", "decodeStart", "(Lcoil/request/ImageRequest;Lcoil/decode/Decoder;Lcoil/request/Options;)V", "Lcoil/decode/DecodeResult;", "decodeEnd", "(Lcoil/request/ImageRequest;Lcoil/decode/Decoder;Lcoil/request/Options;Lcoil/decode/DecodeResult;)V", "Landroid/graphics/Bitmap;", "transformStart", "(Lcoil/request/ImageRequest;Landroid/graphics/Bitmap;)V", "transformEnd", "Lcoil/transition/Transition;", "transition", "transitionStart", "(Lcoil/request/ImageRequest;Lcoil/transition/Transition;)V", "transitionEnd", "onCancel", "Lcoil/request/ErrorResult;", "onError", "(Lcoil/request/ImageRequest;Lcoil/request/ErrorResult;)V", "Lcoil/request/SuccessResult;", "onSuccess", "(Lcoil/request/ImageRequest;Lcoil/request/SuccessResult;)V", "Companion", "Factory", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface EventListener extends ImageRequest.Listener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final EventListener NONE = new EventListener() { // from class: coil.EventListener$Companion$NONE$1
        @Override // coil.EventListener
        public final /* synthetic */ void decodeEnd(ImageRequest imageRequest, Decoder decoder, Options options, DecodeResult decodeResult) {
            a.a(this, imageRequest, decoder, options, decodeResult);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void decodeStart(ImageRequest imageRequest, Decoder decoder, Options options) {
            a.b(this, imageRequest, decoder, options);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void fetchEnd(ImageRequest imageRequest, Fetcher fetcher, Options options, FetchResult fetchResult) {
            a.c(this, imageRequest, fetcher, options, fetchResult);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void fetchStart(ImageRequest imageRequest, Fetcher fetcher, Options options) {
            a.d(this, imageRequest, fetcher, options);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void keyEnd(ImageRequest imageRequest, String str) {
            a.e(this, imageRequest, str);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void keyStart(ImageRequest imageRequest, Object obj) {
            a.f(this, imageRequest, obj);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void mapEnd(ImageRequest imageRequest, Object obj) {
            a.g(this, imageRequest, obj);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void mapStart(ImageRequest imageRequest, Object obj) {
            a.h(this, imageRequest, obj);
        }

        @Override // coil.EventListener, coil.request.ImageRequest.Listener
        public final /* synthetic */ void onCancel(ImageRequest imageRequest) {
            a.i(this, imageRequest);
        }

        @Override // coil.EventListener, coil.request.ImageRequest.Listener
        public final /* synthetic */ void onError(ImageRequest imageRequest, ErrorResult errorResult) {
            a.j(this, imageRequest, errorResult);
        }

        @Override // coil.EventListener, coil.request.ImageRequest.Listener
        public final /* synthetic */ void onStart(ImageRequest imageRequest) {
            a.k(this, imageRequest);
        }

        @Override // coil.EventListener, coil.request.ImageRequest.Listener
        public final /* synthetic */ void onSuccess(ImageRequest imageRequest, SuccessResult successResult) {
            a.l(this, imageRequest, successResult);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void resolveSizeEnd(ImageRequest imageRequest, Size size) {
            a.m(this, imageRequest, size);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void resolveSizeStart(ImageRequest imageRequest) {
            a.n(this, imageRequest);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void transformEnd(ImageRequest imageRequest, Bitmap bitmap) {
            a.o(this, imageRequest, bitmap);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void transformStart(ImageRequest imageRequest, Bitmap bitmap) {
            a.p(this, imageRequest, bitmap);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void transitionEnd(ImageRequest imageRequest, Transition transition) {
            a.q(this, imageRequest, transition);
        }

        @Override // coil.EventListener
        public final /* synthetic */ void transitionStart(ImageRequest imageRequest, Transition transition) {
            a.r(this, imageRequest, transition);
        }
    };

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\u0005"}, d2 = {"Lcoil/EventListener$Companion;", "", "()V", "NONE", "Lcoil/EventListener;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void decodeEnd(EventListener eventListener, ImageRequest imageRequest, Decoder decoder, Options options, DecodeResult decodeResult) {
            a.a(eventListener, imageRequest, decoder, options, decodeResult);
        }

        @Deprecated
        public static void decodeStart(EventListener eventListener, ImageRequest imageRequest, Decoder decoder, Options options) {
            a.b(eventListener, imageRequest, decoder, options);
        }

        @Deprecated
        public static void fetchEnd(EventListener eventListener, ImageRequest imageRequest, Fetcher fetcher, Options options, FetchResult fetchResult) {
            a.c(eventListener, imageRequest, fetcher, options, fetchResult);
        }

        @Deprecated
        public static void fetchStart(EventListener eventListener, ImageRequest imageRequest, Fetcher fetcher, Options options) {
            a.d(eventListener, imageRequest, fetcher, options);
        }

        @Deprecated
        public static void keyEnd(EventListener eventListener, ImageRequest imageRequest, String str) {
            a.e(eventListener, imageRequest, str);
        }

        @Deprecated
        public static void keyStart(EventListener eventListener, ImageRequest imageRequest, Object obj) {
            a.f(eventListener, imageRequest, obj);
        }

        @Deprecated
        public static void mapEnd(EventListener eventListener, ImageRequest imageRequest, Object obj) {
            a.g(eventListener, imageRequest, obj);
        }

        @Deprecated
        public static void mapStart(EventListener eventListener, ImageRequest imageRequest, Object obj) {
            a.h(eventListener, imageRequest, obj);
        }

        @Deprecated
        public static void onCancel(EventListener eventListener, ImageRequest imageRequest) {
            a.i(eventListener, imageRequest);
        }

        @Deprecated
        public static void onError(EventListener eventListener, ImageRequest imageRequest, ErrorResult errorResult) {
            a.j(eventListener, imageRequest, errorResult);
        }

        @Deprecated
        public static void onStart(EventListener eventListener, ImageRequest imageRequest) {
            a.k(eventListener, imageRequest);
        }

        @Deprecated
        public static void onSuccess(EventListener eventListener, ImageRequest imageRequest, SuccessResult successResult) {
            a.l(eventListener, imageRequest, successResult);
        }

        @Deprecated
        public static void resolveSizeEnd(EventListener eventListener, ImageRequest imageRequest, Size size) {
            a.m(eventListener, imageRequest, size);
        }

        @Deprecated
        public static void resolveSizeStart(EventListener eventListener, ImageRequest imageRequest) {
            a.n(eventListener, imageRequest);
        }

        @Deprecated
        public static void transformEnd(EventListener eventListener, ImageRequest imageRequest, Bitmap bitmap) {
            a.o(eventListener, imageRequest, bitmap);
        }

        @Deprecated
        public static void transformStart(EventListener eventListener, ImageRequest imageRequest, Bitmap bitmap) {
            a.p(eventListener, imageRequest, bitmap);
        }

        @Deprecated
        public static void transitionEnd(EventListener eventListener, ImageRequest imageRequest, Transition transition) {
            a.q(eventListener, imageRequest, transition);
        }

        @Deprecated
        public static void transitionStart(EventListener eventListener, ImageRequest imageRequest, Transition transition) {
            a.r(eventListener, imageRequest, transition);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lcoil/EventListener$Factory;", "", "create", "Lcoil/EventListener;", "request", "Lcoil/request/ImageRequest;", "Companion", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final Factory NONE = new androidx.media3.extractor.mp4.b(12);

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\u0005"}, d2 = {"Lcoil/EventListener$Factory$Companion;", "", "()V", "NONE", "Lcoil/EventListener$Factory;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
            }
        }

        EventListener create(ImageRequest request);
    }

    void decodeEnd(ImageRequest request, Decoder decoder, Options options, DecodeResult result);

    void decodeStart(ImageRequest request, Decoder decoder, Options options);

    void fetchEnd(ImageRequest request, Fetcher fetcher, Options options, FetchResult result);

    void fetchStart(ImageRequest request, Fetcher fetcher, Options options);

    void keyEnd(ImageRequest request, String output);

    void keyStart(ImageRequest request, Object input);

    void mapEnd(ImageRequest request, Object output);

    void mapStart(ImageRequest request, Object input);

    @Override // coil.request.ImageRequest.Listener
    void onCancel(ImageRequest request);

    @Override // coil.request.ImageRequest.Listener
    void onError(ImageRequest request, ErrorResult result);

    @Override // coil.request.ImageRequest.Listener
    void onStart(ImageRequest request);

    @Override // coil.request.ImageRequest.Listener
    void onSuccess(ImageRequest request, SuccessResult result);

    void resolveSizeEnd(ImageRequest request, Size size);

    void resolveSizeStart(ImageRequest request);

    void transformEnd(ImageRequest request, Bitmap output);

    void transformStart(ImageRequest request, Bitmap input);

    void transitionEnd(ImageRequest request, Transition transition);

    void transitionStart(ImageRequest request, Transition transition);
}
