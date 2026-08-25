package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.common.C;
import com.arflix.tv.data.api.StreamApi;
import com.arflix.tv.data.api.StremioSubtitle;
import com.arflix.tv.data.api.StremioSubtitleResponse;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.Subtitle;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/Subtitle;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$2$1", f = "StreamRepository.kt", l = {3051}, m = "invokeSuspend", v = 2)
public final class StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends Subtitle>>, Object> {
    final /* synthetic */ Addon $addon;
    final /* synthetic */ String $contentId;
    final /* synthetic */ String $type;
    final /* synthetic */ String $videoHash;
    final /* synthetic */ Long $videoSize;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$2$1(Addon addon, StreamRepository streamRepository, String str, String str2, String str3, Long l10, d7.d<? super StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$2$1> dVar) {
        super(2, dVar);
        this.$addon = addon;
        this.this$0 = streamRepository;
        this.$type = str;
        this.$contentId = str2;
        this.$videoHash = str3;
        this.$videoSize = l10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$2$1(this.$addon, this.this$0, this.$type, this.$contentId, this.$videoHash, this.$videoSize, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws UnsupportedEncodingException {
        Object subtitles;
        String string;
        int i10 = this.label;
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        if (i10 == 0) {
            k2.c.G(obj);
            String url = this.$addon.getUrl();
            if (url == null) {
                return zVar;
            }
            x6.x addonBaseUrl = this.this$0.getAddonBaseUrl(url);
            String strBuildSubtitlesUrl = this.this$0.buildSubtitlesUrl((String) addonBaseUrl.f22608i, this.$type, this.$contentId, (String) addonBaseUrl.f22609l, this.$videoHash, this.$videoSize);
            StreamApi streamApi = this.this$0.streamApi;
            this.L$0 = null;
            this.L$1 = null;
            this.L$2 = null;
            this.L$3 = null;
            this.label = 1;
            subtitles = streamApi.getSubtitles(strBuildSubtitlesUrl, this);
            e7.a aVar = e7.a.f15033i;
            if (subtitles == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            subtitles = obj;
        }
        List<StremioSubtitle> subtitles2 = ((StremioSubtitleResponse) subtitles).getSubtitles();
        if (subtitles2 == null) {
            return zVar;
        }
        StreamRepository streamRepository = this.this$0;
        Addon addon = this.$addon;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(subtitles2, 10));
        int i11 = 0;
        for (Object obj2 : subtitles2) {
            int i12 = i11 + 1;
            if (i11 < 0) {
                t7.a.Q();
                throw null;
            }
            StremioSubtitle stremioSubtitle = (StremioSubtitle) obj2;
            String strNormalizeLanguageCode = streamRepository.normalizeLanguageCode(stremioSubtitle.getLang());
            String lang = stremioSubtitle.getLang();
            String strI0 = (lang == null || (string = kotlin.text.o.L0(lang).toString()) == null) ? C.LANGUAGE_UNDETERMINED : kotlin.text.o.I0(2, string.toLowerCase(Locale.ROOT));
            String id = stremioSubtitle.getId();
            if (id == null) {
                id = a2.j(i11, addon.getId(), "_sub_hint_");
            }
            String str = id;
            String url2 = stremioSubtitle.getUrl();
            if (url2 == null) {
                url2 = "";
            }
            String str2 = url2;
            String str3 = kotlin.text.o.h0(strNormalizeLanguageCode) ? strI0 : strNormalizeLanguageCode;
            if (kotlin.text.o.h0(strNormalizeLanguageCode)) {
                strNormalizeLanguageCode = strI0;
            }
            arrayList.add(new Subtitle(str, str2, str3, streamRepository.buildSubtitleLabel(strNormalizeLanguageCode, stremioSubtitle.getLabel(), addon.getName(), stremioSubtitle.getId()), addon.getName(), false, null, null, false, false, 992, null));
            i11 = i12;
        }
        return arrayList;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<Subtitle>> dVar) {
        return ((StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
