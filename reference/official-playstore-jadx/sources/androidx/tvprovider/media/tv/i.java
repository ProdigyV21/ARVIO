package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.os.Build;
import io.ktor.http.LinkHeader;

/* JADX INFO: loaded from: classes3.dex */
public abstract class i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f6608b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ContentValues f6609a;

    static {
        int i10 = Build.VERSION.SDK_INT;
        String[] strArr = {"_id", "package_name", LinkHeader.Parameters.Title, "episode_title", i10 >= 24 ? "season_display_number" : "season_number", i10 >= 24 ? "episode_display_number" : "episode_number", "short_description", "long_description", "poster_art_uri", "thumbnail_uri", "audio_language", "canonical_genre", "content_rating", "video_width", "video_height", "internal_provider_data", "series_id"};
        String[] strArr2 = {"searchable", "internal_provider_flag1", "internal_provider_flag2", "internal_provider_flag3", "internal_provider_flag4"};
        String[] strArr3 = {"season_title"};
        f6608b = i10 >= 26 ? (String[]) t7.a.j(strArr, strArr2, strArr3, new String[]{"review_rating", "review_rating_style"}) : i10 >= 24 ? (String[]) t7.a.j(strArr, strArr2, strArr3) : (String[]) t7.a.j(strArr, strArr2);
    }

    public final int hashCode() {
        return this.f6609a.hashCode();
    }
}
