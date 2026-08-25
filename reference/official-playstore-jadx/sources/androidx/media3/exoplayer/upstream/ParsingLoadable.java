package androidx.media3.exoplayer.upstream;

import android.net.Uri;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceInputStream;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.upstream.Loader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class ParsingLoadable<T> implements Loader.Loadable {
    private final StatsDataSource dataSource;
    public final DataSpec dataSpec;
    public final long loadTaskId;
    private final Parser<? extends T> parser;
    private volatile T result;
    public final int type;

    public interface Parser<T> {
        T parse(Uri uri, InputStream inputStream) throws IOException;
    }

    public ParsingLoadable(DataSource dataSource, Uri uri, int i10, Parser<? extends T> parser) {
        this(dataSource, new DataSpec.Builder().setUri(uri).setFlags(1).build(), i10, parser);
    }

    public static <T> T load(DataSource dataSource, Parser<? extends T> parser, Uri uri, int i10) throws IOException {
        ParsingLoadable parsingLoadable = new ParsingLoadable(dataSource, uri, i10, parser);
        parsingLoadable.load();
        T t2 = (T) parsingLoadable.getResult();
        t2.getClass();
        return t2;
    }

    public long bytesLoaded() {
        return this.dataSource.getBytesRead();
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public final void cancelLoad() {
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.dataSource.getLastResponseHeaders();
    }

    public final T getResult() {
        return this.result;
    }

    public Uri getUri() {
        return this.dataSource.getLastOpenedUri();
    }

    public ParsingLoadable(DataSource dataSource, DataSpec dataSpec, int i10, Parser<? extends T> parser) {
        this.dataSource = new StatsDataSource(dataSource);
        this.dataSpec = dataSpec;
        this.type = i10;
        this.parser = parser;
        this.loadTaskId = LoadEventInfo.getNewId();
    }

    public static <T> T load(DataSource dataSource, Parser<? extends T> parser, DataSpec dataSpec, int i10) throws IOException {
        ParsingLoadable parsingLoadable = new ParsingLoadable(dataSource, dataSpec, i10, parser);
        parsingLoadable.load();
        T t2 = (T) parsingLoadable.getResult();
        t2.getClass();
        return t2;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public final void load() throws IOException {
        this.dataSource.resetBytesRead();
        DataSourceInputStream dataSourceInputStream = new DataSourceInputStream(this.dataSource, this.dataSpec);
        try {
            dataSourceInputStream.open();
            Uri uri = this.dataSource.getUri();
            uri.getClass();
            this.result = this.parser.parse(uri, dataSourceInputStream);
        } finally {
            Util.closeQuietly(dataSourceInputStream);
        }
    }
}
