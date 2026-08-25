package com.arflix.tv.data.api;

import f7.j;
import java.util.concurrent.CancellationException;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/api/ManifestCandidate;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/api/ManifestCandidate;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.api.InAppYouTubeExtractor$extractPlaybackSourceInternal$3$manifestJobs$1$1", f = "YouTubeExtractor.kt", l = {}, m = "invokeSuspend", v = 2)
public final class InAppYouTubeExtractor$extractPlaybackSourceInternal$3$manifestJobs$1$1 extends j implements p<k0, d7.d<? super ManifestCandidate>, Object> {
    final /* synthetic */ String $clientKey;
    final /* synthetic */ String $manifestUrl;
    final /* synthetic */ int $priority;
    int label;
    final /* synthetic */ InAppYouTubeExtractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InAppYouTubeExtractor$extractPlaybackSourceInternal$3$manifestJobs$1$1(InAppYouTubeExtractor inAppYouTubeExtractor, String str, String str2, int i10, d7.d<? super InAppYouTubeExtractor$extractPlaybackSourceInternal$3$manifestJobs$1$1> dVar) {
        super(2, dVar);
        this.this$0 = inAppYouTubeExtractor;
        this.$manifestUrl = str;
        this.$clientKey = str2;
        this.$priority = i10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new InAppYouTubeExtractor$extractPlaybackSourceInternal$3$manifestJobs$1$1(this.this$0, this.$manifestUrl, this.$clientKey, this.$priority, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        try {
            ManifestBestVariant hlsManifest = this.this$0.parseHlsManifest(this.$manifestUrl);
            if (hlsManifest == null) {
                return null;
            }
            return new ManifestCandidate(this.$clientKey, this.$priority, this.$manifestUrl, hlsManifest.getUrl(), hlsManifest.getHeight(), hlsManifest.getBandwidth());
        } catch (CancellationException e5) {
            throw e5;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super ManifestCandidate> dVar) {
        return ((InAppYouTubeExtractor$extractPlaybackSourceInternal$3$manifestJobs$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
