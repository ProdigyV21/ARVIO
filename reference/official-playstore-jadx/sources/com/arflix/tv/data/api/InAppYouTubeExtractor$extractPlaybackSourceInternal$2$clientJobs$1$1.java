package com.arflix.tv.data.api;

import f7.j;
import java.util.concurrent.CancellationException;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.text.o;
import r7.p;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0002\u0010\u0004\u001a\u0018\u0012\u0004\u0012\u00020\u0002\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Lx6/x;", "Lcom/arflix/tv/data/api/YouTubeClient;", "", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.api.InAppYouTubeExtractor$extractPlaybackSourceInternal$2$clientJobs$1$1", f = "YouTubeExtractor.kt", l = {}, m = "invokeSuspend", v = 2)
public final class InAppYouTubeExtractor$extractPlaybackSourceInternal$2$clientJobs$1$1 extends j implements p<k0, d7.d<? super x>, Object> {
    final /* synthetic */ String $apiKey;
    final /* synthetic */ YouTubeClient $client;
    final /* synthetic */ f0 $keyRejected;
    final /* synthetic */ String $videoId;
    final /* synthetic */ WatchConfig $watchConfig;
    int label;
    final /* synthetic */ InAppYouTubeExtractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InAppYouTubeExtractor$extractPlaybackSourceInternal$2$clientJobs$1$1(InAppYouTubeExtractor inAppYouTubeExtractor, String str, String str2, YouTubeClient youTubeClient, WatchConfig watchConfig, f0 f0Var, d7.d<? super InAppYouTubeExtractor$extractPlaybackSourceInternal$2$clientJobs$1$1> dVar) {
        super(2, dVar);
        this.this$0 = inAppYouTubeExtractor;
        this.$apiKey = str;
        this.$videoId = str2;
        this.$client = youTubeClient;
        this.$watchConfig = watchConfig;
        this.$keyRejected = f0Var;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new InAppYouTubeExtractor$extractPlaybackSourceInternal$2$clientJobs$1$1(this.this$0, this.$apiKey, this.$videoId, this.$client, this.$watchConfig, this.$keyRejected, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Exception {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        try {
            return new x(this.$client, this.this$0.fetchPlayerResponse(this.$apiKey, this.$videoId, this.$client, this.$watchConfig.getVisitorData(), null));
        } catch (CancellationException e5) {
            throw e5;
        } catch (Exception e6) {
            if (e6 instanceof CancellationException) {
                throw e6;
            }
            String message = e6.getMessage();
            if (message == null) {
                message = "";
            }
            if (!o.T(message, "401", false) && !o.T(message, "403", false)) {
                return null;
            }
            this.$keyRejected.f19738i = true;
            return null;
        }
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super x> dVar) {
        return ((InAppYouTubeExtractor$extractPlaybackSourceInternal$2$clientJobs$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
