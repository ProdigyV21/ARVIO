package com.arflix.tv.util;

import com.arflix.tv.data.model.EpisodeIdentity;
import ga.h;
import ga.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.i0;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0081\b\u0018\u00002\u00020\u0001B!\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\fJ\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000e\u0010\fJ\u001f\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\fJ'\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00040\u00022\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\u0010\u0010\u0011J?\u0010\u0015\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00130\u00022\u0018\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00130\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\"\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J,\u0010\u0019\u001a\u00020\u00002\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002HÆ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u001bHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010\"\u001a\u00020!2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\"\u0010#R)\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010$\u001a\u0004\b%\u0010\u0018R\u0011\u0010'\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b&\u0010\u001f¨\u0006("}, d2 = {"Lcom/arflix/tv/util/AnimeSeasonStructure;", "", "", "", "", "Lcom/arflix/tv/data/model/EpisodeIdentity;", "seasons", "<init>", "(Ljava/util/Map;)V", "season", "episode", "identityForDisplay", "(II)Lcom/arflix/tv/data/model/EpisodeIdentity;", "identityForTmdb", "nextAfterDisplay", "previousBeforeDisplay", "canonicalEpisodesForDisplaySeason", "(I)Ljava/util/Map;", "", "Lx6/x;", "watched", "progressForCanonicalEpisodes", "(Ljava/util/Set;)Ljava/util/Map;", "component1", "()Ljava/util/Map;", "copy", "(Ljava/util/Map;)Lcom/arflix/tv/util/AnimeSeasonStructure;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/Map;", "getSeasons", "getSeasonCount", "seasonCount", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AnimeSeasonStructure {
    public static final int $stable = 0;
    private final Map<Integer, List<EpisodeIdentity>> seasons;

    /* JADX WARN: Multi-variable type inference failed */
    public AnimeSeasonStructure(Map<Integer, ? extends List<EpisodeIdentity>> map) {
        this.seasons = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AnimeSeasonStructure copy$default(AnimeSeasonStructure animeSeasonStructure, Map map, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            map = animeSeasonStructure.seasons;
        }
        return animeSeasonStructure.copy(map);
    }

    public final Map<Integer, List<Integer>> canonicalEpisodesForDisplaySeason(int season) {
        List<EpisodeIdentity> list = this.seasons.get(Integer.valueOf(season));
        if (list == null) {
            list = z.f19728i;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : list) {
            Integer numValueOf = Integer.valueOf(((EpisodeIdentity) obj).getTmdbSeason());
            Object arrayList = linkedHashMap.get(numValueOf);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(numValueOf, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(i0.q0(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Object key = entry.getKey();
            List list2 = (List) entry.getValue();
            ArrayList arrayList2 = new ArrayList(s.U(list2, 10));
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                arrayList2.add(Integer.valueOf(((EpisodeIdentity) it.next()).getTmdbEpisode()));
            }
            linkedHashMap2.put(key, arrayList2);
        }
        return linkedHashMap2;
    }

    public final Map<Integer, List<EpisodeIdentity>> component1() {
        return this.seasons;
    }

    public final AnimeSeasonStructure copy(Map<Integer, ? extends List<EpisodeIdentity>> seasons) {
        return new AnimeSeasonStructure(seasons);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AnimeSeasonStructure) && p.a(this.seasons, ((AnimeSeasonStructure) other).seasons);
    }

    public final int getSeasonCount() {
        return this.seasons.size();
    }

    public final Map<Integer, List<EpisodeIdentity>> getSeasons() {
        return this.seasons;
    }

    public int hashCode() {
        return this.seasons.hashCode();
    }

    public final EpisodeIdentity identityForDisplay(int season, int episode) {
        List<EpisodeIdentity> list = this.seasons.get(Integer.valueOf(season));
        Object obj = null;
        if (list == null) {
            return null;
        }
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((EpisodeIdentity) next).getDisplayEpisode() == episode) {
                obj = next;
                break;
            }
        }
        return (EpisodeIdentity) obj;
    }

    public final EpisodeIdentity identityForTmdb(int season, int episode) {
        Object next;
        h hVar = new h(new j(new ga.p(this.seasons.values(), 3), new a(3), new a(2)));
        while (true) {
            if (!hVar.hasNext()) {
                next = null;
                break;
            }
            next = hVar.next();
            EpisodeIdentity episodeIdentity = (EpisodeIdentity) next;
            if (episodeIdentity.getTmdbSeason() == season && episodeIdentity.getTmdbEpisode() == episode) {
                break;
            }
        }
        return (EpisodeIdentity) next;
    }

    public final EpisodeIdentity nextAfterDisplay(int season, int episode) {
        EpisodeIdentity episodeIdentityIdentityForDisplay = identityForDisplay(season, episode + 1);
        if (episodeIdentityIdentityForDisplay != null) {
            return episodeIdentityIdentityForDisplay;
        }
        List<EpisodeIdentity> list = this.seasons.get(Integer.valueOf(season + 1));
        if (list != null) {
            return (EpisodeIdentity) x.o0(list);
        }
        return null;
    }

    public final EpisodeIdentity previousBeforeDisplay(int season, int episode) {
        EpisodeIdentity episodeIdentityIdentityForDisplay = identityForDisplay(season, episode - 1);
        if (episodeIdentityIdentityForDisplay != null) {
            return episodeIdentityIdentityForDisplay;
        }
        List<EpisodeIdentity> list = this.seasons.get(Integer.valueOf(season - 1));
        if (list != null) {
            return (EpisodeIdentity) x.y0(list);
        }
        return null;
    }

    public final Map<Integer, x6.x> progressForCanonicalEpisodes(Set<x6.x> watched) {
        Map<Integer, List<EpisodeIdentity>> map = this.seasons;
        LinkedHashMap linkedHashMap = new LinkedHashMap(i0.q0(map.size()));
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            List<EpisodeIdentity> list = (List) entry.getValue();
            int i10 = 0;
            if (list == null || !list.isEmpty()) {
                for (EpisodeIdentity episodeIdentity : list) {
                    if (watched.contains(new x6.x(Integer.valueOf(episodeIdentity.getTmdbSeason()), Integer.valueOf(episodeIdentity.getTmdbEpisode()))) && (i10 = i10 + 1) < 0) {
                        t7.a.P();
                        throw null;
                    }
                }
            }
            linkedHashMap.put(key, new x6.x(Integer.valueOf(i10), Integer.valueOf(list.size())));
        }
        return linkedHashMap;
    }

    public String toString() {
        return "AnimeSeasonStructure(seasons=" + this.seasons + ")";
    }
}
