package com.arflix.tv.data.repository;

import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import cb.b1;
import cb.d1;
import cb.l1;
import cb.q0;
import cb.r1;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlinx.serialization.UnknownFieldException;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/arflix/tv/data/repository/WatchHistoryEntry.$serializer", "Lcb/e0;", "Lcom/arflix/tv/data/repository/WatchHistoryEntry;", "<init>", "()V", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lcom/arflix/tv/data/repository/WatchHistoryEntry;)V", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lcom/arflix/tv/data/repository/WatchHistoryEntry;", "", "Lya/h;", "childSerializers", "()[Lya/h;", "Lab/g;", "descriptor", "Lab/g;", "getDescriptor", "()Lab/g;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@x6.e
public final /* synthetic */ class WatchHistoryEntry$$serializer implements cb.e0<WatchHistoryEntry> {
    public static final int $stable;
    public static final WatchHistoryEntry$$serializer INSTANCE;
    private static final ab.g descriptor;

    static {
        WatchHistoryEntry$$serializer watchHistoryEntry$$serializer = new WatchHistoryEntry$$serializer();
        INSTANCE = watchHistoryEntry$$serializer;
        $stable = 8;
        d1 d1Var = new d1("com.arflix.tv.data.repository.WatchHistoryEntry", watchHistoryEntry$$serializer, 23);
        d1Var.j(TtmlNode.ATTR_ID, true);
        d1Var.j("user_id", false);
        d1Var.j("profile_id", true);
        d1Var.j("media_type", false);
        d1Var.j("show_tmdb_id", false);
        d1Var.j("show_trakt_id", true);
        d1Var.j("season", true);
        d1Var.j("episode", true);
        d1Var.j("trakt_episode_id", true);
        d1Var.j("tmdb_episode_id", true);
        d1Var.j(LinkHeader.Parameters.Title, true);
        d1Var.j("episode_title", true);
        d1Var.j("progress", true);
        d1Var.j("duration_seconds", true);
        d1Var.j("position_seconds", true);
        d1Var.j("paused_at", true);
        d1Var.j("updated_at", true);
        d1Var.j("source", true);
        d1Var.j("backdrop_path", true);
        d1Var.j("poster_path", true);
        d1Var.j("stream_key", true);
        d1Var.j("stream_addon_id", true);
        d1Var.j("stream_title", true);
        descriptor = d1Var;
    }

    private WatchHistoryEntry$$serializer() {
    }

    @Override // cb.e0
    public final ya.h<?>[] childSerializers() {
        r1 r1Var = r1.f7553a;
        ya.h<?> hVarQ = a.a.Q(r1Var);
        ya.h<?> hVarQ2 = a.a.Q(r1Var);
        cb.l0 l0Var = cb.l0.f7529a;
        ya.h<?> hVarQ3 = a.a.Q(l0Var);
        ya.h<?> hVarQ4 = a.a.Q(l0Var);
        ya.h<?> hVarQ5 = a.a.Q(l0Var);
        ya.h<?> hVarQ6 = a.a.Q(l0Var);
        ya.h<?> hVarQ7 = a.a.Q(l0Var);
        ya.h<?> hVarQ8 = a.a.Q(r1Var);
        ya.h<?> hVarQ9 = a.a.Q(r1Var);
        ya.h<?> hVarQ10 = a.a.Q(r1Var);
        ya.h<?> hVarQ11 = a.a.Q(r1Var);
        ya.h<?> hVarQ12 = a.a.Q(r1Var);
        ya.h<?> hVarQ13 = a.a.Q(r1Var);
        ya.h<?> hVarQ14 = a.a.Q(r1Var);
        ya.h<?> hVarQ15 = a.a.Q(r1Var);
        ya.h<?> hVarQ16 = a.a.Q(r1Var);
        ya.h<?> hVarQ17 = a.a.Q(r1Var);
        q0 q0Var = q0.f7547a;
        return new ya.h[]{hVarQ, r1Var, hVarQ2, r1Var, l0Var, hVarQ3, hVarQ4, hVarQ5, hVarQ6, hVarQ7, hVarQ8, hVarQ9, cb.d0.f7475a, q0Var, q0Var, hVarQ10, hVarQ11, hVarQ12, hVarQ13, hVarQ14, hVarQ15, hVarQ16, hVarQ17};
    }

    @Override // ya.c
    public final WatchHistoryEntry deserialize(bb.d decoder) {
        Integer num;
        int i10;
        String str;
        String str2;
        String str3;
        Integer num2;
        ab.g gVar = descriptor;
        bb.b bVarB = decoder.b(gVar);
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String strF = null;
        String strF2 = null;
        String str14 = null;
        String str15 = null;
        Integer num3 = null;
        Integer num4 = null;
        float fL = 0.0f;
        long jK = 0;
        long jK2 = 0;
        boolean z = true;
        int iC = 0;
        Integer num5 = null;
        Integer num6 = null;
        Integer num7 = null;
        int i11 = 0;
        while (z) {
            Integer num8 = num5;
            int iW = bVarB.w(gVar);
            switch (iW) {
                case -1:
                    str = str4;
                    str2 = str14;
                    z = false;
                    str4 = str;
                    str14 = str2;
                    num5 = num8;
                    break;
                case 0:
                    str = str4;
                    str2 = (String) bVarB.j(gVar, 0, r1.f7553a, str14);
                    i11 |= 1;
                    str15 = str15;
                    num6 = num6;
                    str4 = str;
                    str14 = str2;
                    num5 = num8;
                    break;
                case 1:
                    str3 = str4;
                    strF = bVarB.f(gVar, 1);
                    i11 |= 2;
                    num5 = num8;
                    str4 = str3;
                    break;
                case 2:
                    str3 = str4;
                    num2 = num6;
                    str15 = (String) bVarB.j(gVar, 2, r1.f7553a, str15);
                    i11 |= 4;
                    num5 = num8;
                    num6 = num2;
                    str4 = str3;
                    break;
                case 3:
                    str3 = str4;
                    strF2 = bVarB.f(gVar, 3);
                    i11 |= 8;
                    num5 = num8;
                    str4 = str3;
                    break;
                case 4:
                    str3 = str4;
                    iC = bVarB.c(gVar, 4);
                    i11 |= 16;
                    num5 = num8;
                    str4 = str3;
                    break;
                case 5:
                    str3 = str4;
                    num2 = num6;
                    num3 = (Integer) bVarB.j(gVar, 5, cb.l0.f7529a, num3);
                    i11 |= 32;
                    num5 = num8;
                    num6 = num2;
                    str4 = str3;
                    break;
                case 6:
                    str3 = str4;
                    num2 = num6;
                    num4 = (Integer) bVarB.j(gVar, 6, cb.l0.f7529a, num4);
                    i11 |= 64;
                    num5 = num8;
                    num6 = num2;
                    str4 = str3;
                    break;
                case 7:
                    str3 = str4;
                    num2 = num6;
                    num5 = (Integer) bVarB.j(gVar, 7, cb.l0.f7529a, num8);
                    i11 |= 128;
                    num6 = num2;
                    str4 = str3;
                    break;
                case 8:
                    str3 = str4;
                    num6 = (Integer) bVarB.j(gVar, 8, cb.l0.f7529a, num6);
                    i11 |= 256;
                    num5 = num8;
                    str4 = str3;
                    break;
                case 9:
                    num = num6;
                    num7 = (Integer) bVarB.j(gVar, 9, cb.l0.f7529a, num7);
                    i11 |= 512;
                    num5 = num8;
                    num6 = num;
                    break;
                case 10:
                    num = num6;
                    str6 = (String) bVarB.j(gVar, 10, r1.f7553a, str6);
                    i11 |= 1024;
                    num5 = num8;
                    num6 = num;
                    break;
                case 11:
                    num = num6;
                    str7 = (String) bVarB.j(gVar, 11, r1.f7553a, str7);
                    i11 |= 2048;
                    num5 = num8;
                    num6 = num;
                    break;
                case 12:
                    fL = bVarB.l(gVar, 12);
                    i11 |= 4096;
                    num5 = num8;
                    break;
                case 13:
                    jK = bVarB.k(gVar, 13);
                    i11 |= 8192;
                    num5 = num8;
                    break;
                case 14:
                    jK2 = bVarB.k(gVar, 14);
                    i11 |= 16384;
                    num5 = num8;
                    break;
                case 15:
                    num = num6;
                    str12 = (String) bVarB.j(gVar, 15, r1.f7553a, str12);
                    i10 = 32768;
                    i11 |= i10;
                    num5 = num8;
                    num6 = num;
                    break;
                case 16:
                    num = num6;
                    str5 = (String) bVarB.j(gVar, 16, r1.f7553a, str5);
                    i10 = 65536;
                    i11 |= i10;
                    num5 = num8;
                    num6 = num;
                    break;
                case 17:
                    num = num6;
                    str4 = (String) bVarB.j(gVar, 17, r1.f7553a, str4);
                    i10 = 131072;
                    i11 |= i10;
                    num5 = num8;
                    num6 = num;
                    break;
                case 18:
                    num = num6;
                    str9 = (String) bVarB.j(gVar, 18, r1.f7553a, str9);
                    i10 = MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES;
                    i11 |= i10;
                    num5 = num8;
                    num6 = num;
                    break;
                case 19:
                    num = num6;
                    str11 = (String) bVarB.j(gVar, 19, r1.f7553a, str11);
                    i10 = 524288;
                    i11 |= i10;
                    num5 = num8;
                    num6 = num;
                    break;
                case 20:
                    num = num6;
                    str8 = (String) bVarB.j(gVar, 20, r1.f7553a, str8);
                    i10 = ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;
                    i11 |= i10;
                    num5 = num8;
                    num6 = num;
                    break;
                case 21:
                    num = num6;
                    str10 = (String) bVarB.j(gVar, 21, r1.f7553a, str10);
                    i10 = 2097152;
                    i11 |= i10;
                    num5 = num8;
                    num6 = num;
                    break;
                case 22:
                    num = num6;
                    str13 = (String) bVarB.j(gVar, 22, r1.f7553a, str13);
                    i10 = 4194304;
                    i11 |= i10;
                    num5 = num8;
                    num6 = num;
                    break;
                default:
                    throw new UnknownFieldException(iW);
            }
        }
        Integer num9 = num5;
        Integer num10 = num6;
        String str16 = str14;
        String str17 = str15;
        bVarB.u(gVar);
        String str18 = str13;
        return new WatchHistoryEntry(i11, str16, strF, str17, strF2, iC, num3, num4, num9, num10, num7, str6, str7, fL, jK, jK2, str12, str5, str4, str9, str11, str8, str10, str18, (l1) null);
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return descriptor;
    }

    @Override // ya.s
    public final void serialize(bb.e encoder, WatchHistoryEntry value) {
        ab.g gVar = descriptor;
        bb.c cVarB = encoder.b(gVar);
        WatchHistoryEntry.write$Self$app_playRelease(value, cVarB, gVar);
        cVarB.e();
    }

    @Override // cb.e0
    public /* bridge */ ya.h<?>[] typeParametersSerializers() {
        return b1.f7464b;
    }
}
