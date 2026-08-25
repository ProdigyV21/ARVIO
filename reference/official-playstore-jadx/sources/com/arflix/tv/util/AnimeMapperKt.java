package com.arflix.tv.util;

import com.arflix.tv.data.api.ArmMappingEntry;
import d7.d;
import f7.c;
import f7.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u001a%\u0010\u0006\u001a\u00020\u00052\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a+\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\b\u0010\t\u001aP\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032$\u0010\u000f\u001a \b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\fH\u0080@¢\u0006\u0004\b\u0011\u0010\u0012\u001aL\u0010\u0014\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00002\u0006\u0010\u000b\u001a\u00020\u00032$\u0010\u000f\u001a \b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\fH\u0082@¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"", "Lcom/arflix/tv/data/api/ArmMappingEntry;", "entries", "", "season", "Lcom/arflix/tv/util/ArmSeasonKitsuCandidates;", "armSeasonKitsuCandidates", "(Ljava/util/List;I)Lcom/arflix/tv/util/ArmSeasonKitsuCandidates;", "armSeasonKitsuIds", "(Ljava/util/List;I)Ljava/util/List;", "candidates", "episode", "Lkotlin/Function2;", "Ld7/d;", "", "episodeCountProvider", "", "armEpisodeQueryFromSeasonCandidates", "(Lcom/arflix/tv/util/ArmSeasonKitsuCandidates;IILr7/p;Ld7/d;)Ljava/lang/Object;", "kitsuIds", "walkArmKitsuIds", "(Ljava/util/List;ILr7/p;Ld7/d;)Ljava/lang/Object;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AnimeMapperKt {

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapperKt$armEpisodeQueryFromSeasonCandidates$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.util.AnimeMapperKt", f = "AnimeMapper.kt", l = {52, 57, 64, 66}, m = "armEpisodeQueryFromSeasonCandidates", v = 2)
    public static final class AnonymousClass1 extends c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapperKt.armEpisodeQueryFromSeasonCandidates(null, 0, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapperKt$walkArmKitsuIds$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.util.AnimeMapperKt", f = "AnimeMapper.kt", l = {79}, m = "walkArmKitsuIds", v = 2)
    public static final class C16571 extends c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C16571(d<? super C16571> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapperKt.walkArmKitsuIds(null, 0, null, this);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b7, code lost:
    
        if (r15 == r9) goto L58;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object armEpisodeQueryFromSeasonCandidates(com.arflix.tv.util.ArmSeasonKitsuCandidates r11, int r12, int r13, r7.p<? super java.lang.Integer, ? super d7.d<? super java.lang.Integer>, ? extends java.lang.Object> r14, d7.d<? super java.lang.String> r15) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapperKt.armEpisodeQueryFromSeasonCandidates(com.arflix.tv.util.ArmSeasonKitsuCandidates, int, int, r7.p, d7.d):java.lang.Object");
    }

    public static final ArmSeasonKitsuCandidates armSeasonKitsuCandidates(List<ArmMappingEntry> list, int i10) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            Integer themoviedbSeason = ((ArmMappingEntry) obj).getThemoviedbSeason();
            if (themoviedbSeason != null && themoviedbSeason.intValue() == i10) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Integer kitsu = ((ArmMappingEntry) it.next()).getKitsu();
            if (kitsu != null) {
                arrayList2.add(kitsu);
            }
        }
        List listG0 = x.g0(arrayList2);
        if (!listG0.isEmpty()) {
            return new ArmSeasonKitsuCandidates(listG0, true);
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : list) {
            if (((ArmMappingEntry) obj2).getThemoviedbSeason() == null) {
                arrayList3.add(obj2);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            Integer kitsu2 = ((ArmMappingEntry) it2.next()).getKitsu();
            if (kitsu2 != null) {
                arrayList4.add(kitsu2);
            }
        }
        return new ArmSeasonKitsuCandidates(x.g0(arrayList4), false);
    }

    public static final List<Integer> armSeasonKitsuIds(List<ArmMappingEntry> list, int i10) {
        return armSeasonKitsuCandidates(list, i10).getKitsuIds();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x008d -> B:21:0x0095). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object walkArmKitsuIds(java.util.List<java.lang.Integer> r11, int r12, r7.p<? super java.lang.Integer, ? super d7.d<? super java.lang.Integer>, ? extends java.lang.Object> r13, d7.d<? super java.lang.String> r14) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapperKt.walkArmKitsuIds(java.util.List, int, r7.p, d7.d):java.lang.Object");
    }
}
