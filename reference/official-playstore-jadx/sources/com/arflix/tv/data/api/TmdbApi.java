package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import zc.s;
import zc.t;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J0\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\b\u0003\u0010\u0006\u001a\u00020\u0005H§@¢\u0006\u0004\b\b\u0010\tJ0\u0010\n\u001a\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\b\u0003\u0010\u0006\u001a\u00020\u0005H§@¢\u0006\u0004\b\n\u0010\tJ°\u0001\u0010\u0016\u001a\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\f\u001a\u00020\u00022\b\b\u0003\u0010\r\u001a\u00020\u00022\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\b\u0003\u0010\u0006\u001a\u00020\u0005H§@¢\u0006\u0004\b\u0016\u0010\u0017J²\u0001\u0010\u001b\u001a\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u00022\b\b\u0003\u0010\r\u001a\u00020\u00022\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u001a\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\b\u0003\u0010\u0006\u001a\u00020\u0005H§@¢\u0006\u0004\b\u001b\u0010\u001cJ0\u0010\u001f\u001a\u00020\u001e2\b\b\u0001\u0010\u001d\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002H§@¢\u0006\u0004\b\u001f\u0010 J0\u0010#\u001a\u00020\"2\b\b\u0001\u0010!\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002H§@¢\u0006\u0004\b#\u0010 J:\u0010&\u001a\u00020%2\b\b\u0001\u0010!\u001a\u00020\u00052\b\b\u0001\u0010$\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002H§@¢\u0006\u0004\b&\u0010'J8\u0010*\u001a\u00020)2\b\b\u0001\u0010!\u001a\u00020\u00052\b\b\u0001\u0010$\u001a\u00020\u00052\b\b\u0001\u0010(\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b*\u0010+J:\u0010/\u001a\u00020.2\b\b\u0001\u0010,\u001a\u00020\u00022\b\b\u0001\u0010-\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002H§@¢\u0006\u0004\b/\u00100J:\u00101\u001a\u00020\u00072\b\b\u0001\u0010,\u001a\u00020\u00022\b\b\u0001\u0010-\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002H§@¢\u0006\u0004\b1\u00100J:\u00102\u001a\u00020\u00072\b\b\u0001\u0010,\u001a\u00020\u00022\b\b\u0001\u0010-\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002H§@¢\u0006\u0004\b2\u00100J.\u00104\u001a\u0002032\b\b\u0001\u0010,\u001a\u00020\u00022\b\b\u0001\u0010-\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b4\u00105J:\u00107\u001a\u0002062\b\b\u0001\u0010,\u001a\u00020\u00022\b\b\u0001\u0010-\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002H§@¢\u0006\u0004\b7\u00100J:\u0010;\u001a\u00020:2\b\b\u0001\u00108\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0003\u00109\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002H§@¢\u0006\u0004\b;\u0010<J$\u0010=\u001a\u00020)2\b\b\u0001\u0010\u001d\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b=\u0010>J$\u0010?\u001a\u00020)2\b\b\u0001\u0010!\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b?\u0010>J$\u0010A\u001a\u00020@2\b\b\u0001\u0010\u001d\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\bA\u0010>J$\u0010B\u001a\u00020@2\b\b\u0001\u0010!\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\bB\u0010>J:\u0010D\u001a\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010C\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\b\u0003\u0010\u0006\u001a\u00020\u0005H§@¢\u0006\u0004\bD\u0010EJR\u0010G\u001a\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010C\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\b\u0003\u0010\u0006\u001a\u00020\u00052\n\b\u0003\u0010F\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u0005H§@¢\u0006\u0004\bG\u0010HJF\u0010J\u001a\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010C\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\b\u0003\u0010\u0006\u001a\u00020\u00052\n\b\u0003\u0010I\u001a\u0004\u0018\u00010\u0005H§@¢\u0006\u0004\bJ\u0010KJ.\u0010O\u001a\u00020N2\b\b\u0001\u0010L\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0003\u0010M\u001a\u00020\u0002H§@¢\u0006\u0004\bO\u0010PJ:\u0010R\u001a\u00020Q2\b\b\u0001\u0010,\u001a\u00020\u00022\b\b\u0001\u0010-\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002H§@¢\u0006\u0004\bR\u00100J0\u0010U\u001a\u00020T2\b\b\u0001\u0010S\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002H§@¢\u0006\u0004\bU\u0010 ¨\u0006VÀ\u0006\u0003"}, d2 = {"Lcom/arflix/tv/data/api/TmdbApi;", "", "", "apiKey", "language", "", "page", "Lcom/arflix/tv/data/api/TmdbListResponse;", "getTrendingMovies", "(Ljava/lang/String;Ljava/lang/String;ILd7/d;)Ljava/lang/Object;", "getTrendingTv", "watchProviders", "watchRegion", "sortBy", "genres", "people", "originalLanguage", "year", "minVoteCount", "keywords", "airDateGte", "airDateLte", "discoverTv", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILd7/d;)Ljava/lang/Object;", "crew", "releaseDateGte", "releaseDateLte", "discoverMovies", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;ILd7/d;)Ljava/lang/Object;", "movieId", "Lcom/arflix/tv/data/api/TmdbMovieDetails;", "getMovieDetails", "(ILjava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "tvId", "Lcom/arflix/tv/data/api/TmdbTvDetails;", "getTvDetails", "seasonNumber", "Lcom/arflix/tv/data/api/TmdbSeasonDetails;", "getTvSeason", "(IILjava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "episodeNumber", "Lcom/arflix/tv/data/api/TmdbExternalIds;", "getTvEpisodeExternalIds", "(IIILjava/lang/String;Ld7/d;)Ljava/lang/Object;", "mediaType", TtmlNode.ATTR_ID, "Lcom/arflix/tv/data/api/TmdbCreditsResponse;", "getCredits", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "getSimilar", "getRecommendations", "Lcom/arflix/tv/data/api/TmdbImagesResponse;", "getImages", "(Ljava/lang/String;ILjava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/TmdbVideosResponse;", "getVideos", "personId", "appendToResponse", "Lcom/arflix/tv/data/api/TmdbPersonDetails;", "getPersonDetails", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "getMovieExternalIds", "(ILjava/lang/String;Ld7/d;)Ljava/lang/Object;", "getTvExternalIds", "Lcom/arflix/tv/data/api/TmdbWatchProvidersResponse;", "getMovieWatchProviders", "getTvWatchProviders", "query", "searchMulti", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILd7/d;)Ljava/lang/Object;", "primaryReleaseYear", "searchMovies", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "firstAirDateYear", "searchTv", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "externalId", "externalSource", "Lcom/arflix/tv/data/api/TmdbFindResponse;", "findByExternalId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/TmdbReviewsResponse;", "getReviews", "collectionId", "Lcom/arflix/tv/data/api/TmdbCollectionResponse;", "getTmdbCollection", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface TmdbApi {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    @zc.f("discover/movie")
    Object discoverMovies(@t("api_key") String str, @t("with_genres") String str2, @t("with_crew") String str3, @t("sort_by") String str4, @t("vote_count.gte") Integer num, @t("with_keywords") String str5, @t("with_original_language") String str6, @t("primary_release_year") Integer num2, @t("release_date.gte") String str7, @t("release_date.lte") String str8, @t("with_watch_providers") Integer num3, @t("watch_region") String str9, @t("language") String str10, @t("page") int i10, d7.d<? super TmdbListResponse> dVar);

    @zc.f("discover/tv")
    Object discoverTv(@t("api_key") String str, @t("with_watch_providers") Integer num, @t("watch_region") String str2, @t("sort_by") String str3, @t("with_genres") String str4, @t("with_people") String str5, @t("with_original_language") String str6, @t("first_air_date_year") Integer num2, @t("vote_count.gte") Integer num3, @t("with_keywords") String str7, @t("air_date.gte") String str8, @t("air_date.lte") String str9, @t("language") String str10, @t("page") int i10, d7.d<? super TmdbListResponse> dVar);

    @zc.f("find/{external_id}")
    Object findByExternalId(@s("external_id") String str, @t("api_key") String str2, @t("external_source") String str3, d7.d<? super TmdbFindResponse> dVar);

    @zc.f("{media_type}/{id}/credits")
    Object getCredits(@s("media_type") String str, @s(TtmlNode.ATTR_ID) int i10, @t("api_key") String str2, @t("language") String str3, d7.d<? super TmdbCreditsResponse> dVar);

    @zc.f("{media_type}/{id}/images")
    Object getImages(@s("media_type") String str, @s(TtmlNode.ATTR_ID) int i10, @t("api_key") String str2, d7.d<? super TmdbImagesResponse> dVar);

    @zc.f("movie/{movie_id}")
    Object getMovieDetails(@s("movie_id") int i10, @t("api_key") String str, @t("language") String str2, d7.d<? super TmdbMovieDetails> dVar);

    @zc.f("movie/{movie_id}/external_ids")
    Object getMovieExternalIds(@s("movie_id") int i10, @t("api_key") String str, d7.d<? super TmdbExternalIds> dVar);

    @zc.f("movie/{movie_id}/watch/providers")
    Object getMovieWatchProviders(@s("movie_id") int i10, @t("api_key") String str, d7.d<? super TmdbWatchProvidersResponse> dVar);

    @zc.f("person/{person_id}")
    Object getPersonDetails(@s("person_id") int i10, @t("api_key") String str, @t("append_to_response") String str2, @t("language") String str3, d7.d<? super TmdbPersonDetails> dVar);

    @zc.f("{media_type}/{id}/recommendations")
    Object getRecommendations(@s("media_type") String str, @s(TtmlNode.ATTR_ID) int i10, @t("api_key") String str2, @t("language") String str3, d7.d<? super TmdbListResponse> dVar);

    @zc.f("{media_type}/{id}/reviews")
    Object getReviews(@s("media_type") String str, @s(TtmlNode.ATTR_ID) int i10, @t("api_key") String str2, @t("language") String str3, d7.d<? super TmdbReviewsResponse> dVar);

    @zc.f("{media_type}/{id}/similar")
    Object getSimilar(@s("media_type") String str, @s(TtmlNode.ATTR_ID) int i10, @t("api_key") String str2, @t("language") String str3, d7.d<? super TmdbListResponse> dVar);

    @zc.f("collection/{collection_id}")
    Object getTmdbCollection(@s("collection_id") int i10, @t("api_key") String str, @t("language") String str2, d7.d<? super TmdbCollectionResponse> dVar);

    @zc.f("trending/movie/day")
    Object getTrendingMovies(@t("api_key") String str, @t("language") String str2, @t("page") int i10, d7.d<? super TmdbListResponse> dVar);

    @zc.f("trending/tv/day")
    Object getTrendingTv(@t("api_key") String str, @t("language") String str2, @t("page") int i10, d7.d<? super TmdbListResponse> dVar);

    @zc.f("tv/{tv_id}")
    Object getTvDetails(@s("tv_id") int i10, @t("api_key") String str, @t("language") String str2, d7.d<? super TmdbTvDetails> dVar);

    @zc.f("tv/{tv_id}/season/{season_number}/episode/{episode_number}/external_ids")
    Object getTvEpisodeExternalIds(@s("tv_id") int i10, @s("season_number") int i11, @s("episode_number") int i12, @t("api_key") String str, d7.d<? super TmdbExternalIds> dVar);

    @zc.f("tv/{tv_id}/external_ids")
    Object getTvExternalIds(@s("tv_id") int i10, @t("api_key") String str, d7.d<? super TmdbExternalIds> dVar);

    @zc.f("tv/{tv_id}/season/{season_number}")
    Object getTvSeason(@s("tv_id") int i10, @s("season_number") int i11, @t("api_key") String str, @t("language") String str2, d7.d<? super TmdbSeasonDetails> dVar);

    @zc.f("tv/{tv_id}/watch/providers")
    Object getTvWatchProviders(@s("tv_id") int i10, @t("api_key") String str, d7.d<? super TmdbWatchProvidersResponse> dVar);

    @zc.f("{media_type}/{id}/videos")
    Object getVideos(@s("media_type") String str, @s(TtmlNode.ATTR_ID) int i10, @t("api_key") String str2, @t("language") String str3, d7.d<? super TmdbVideosResponse> dVar);

    @zc.f("search/movie")
    Object searchMovies(@t("api_key") String str, @t("query") String str2, @t("language") String str3, @t("page") int i10, @t("primary_release_year") Integer num, @t("year") Integer num2, d7.d<? super TmdbListResponse> dVar);

    @zc.f("search/multi")
    Object searchMulti(@t("api_key") String str, @t("query") String str2, @t("language") String str3, @t("page") int i10, d7.d<? super TmdbListResponse> dVar);

    @zc.f("search/tv")
    Object searchTv(@t("api_key") String str, @t("query") String str2, @t("language") String str3, @t("page") int i10, @t("first_air_date_year") Integer num, d7.d<? super TmdbListResponse> dVar);
}
