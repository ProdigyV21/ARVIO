package androidx.media3.datasource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import com.google.common.base.c0;
import com.google.common.util.concurrent.d1;
import com.google.common.util.concurrent.g1;
import com.google.common.util.concurrent.p0;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes3.dex */
public final class DataSourceBitmapLoader implements BitmapLoader {
    public static final c0<g1> DEFAULT_EXECUTOR_SERVICE = p0.v(new b());
    private final DataSource.Factory dataSourceFactory;
    private final g1 listeningExecutorService;
    private final boolean makeShared;
    private final int maximumOutputDimension;
    private final BitmapFactory.Options options;

    public static final class Builder {
        private final Context context;
        private DataSource.Factory dataSourceFactory;
        private g1 listeningExecutorService;
        private boolean makeShared;
        private int maximumOutputDimension = -1;
        private BitmapFactory.Options options;

        public Builder(Context context) {
            this.context = context;
        }

        public DataSourceBitmapLoader build() {
            return new DataSourceBitmapLoader(this);
        }

        public Builder setBitmapFactoryOptions(BitmapFactory.Options options) {
            this.options = options;
            return this;
        }

        public Builder setDataSourceFactory(DataSource.Factory factory) {
            this.dataSourceFactory = factory;
            return this;
        }

        public Builder setExecutorService(g1 g1Var) {
            this.listeningExecutorService = g1Var;
            return this;
        }

        public Builder setMakeShared(boolean z) {
            this.makeShared = z;
            return this;
        }

        public Builder setMaximumOutputDimension(int i10) {
            this.maximumOutputDimension = i10;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Bitmap lambda$decodeBitmap$1(byte[] bArr) throws Exception {
        return maybeAsShared(this.makeShared, BitmapUtil.decode(bArr, bArr.length, this.options, this.maximumOutputDimension));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Bitmap lambda$loadBitmap$2(Uri uri) throws Exception {
        return load(this.dataSourceFactory.createDataSource(), uri, this.options, this.maximumOutputDimension, this.makeShared);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ g1 lambda$static$0() {
        return a.a.U(Executors.newSingleThreadExecutor());
    }

    private static Bitmap load(DataSource dataSource, Uri uri, BitmapFactory.Options options, int i10, boolean z) throws IOException {
        try {
            dataSource.open(new DataSpec(uri));
            byte[] toEnd = DataSourceUtil.readToEnd(dataSource);
            return maybeAsShared(z, BitmapUtil.decode(toEnd, toEnd.length, options, i10));
        } finally {
            dataSource.close();
        }
    }

    private static Bitmap maybeAsShared(boolean z, Bitmap bitmap) {
        return z ? BitmapUtil.makeShared(bitmap) : bitmap;
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public d1<Bitmap> decodeBitmap(byte[] bArr) {
        return this.listeningExecutorService.submit((Callable) new c(this, bArr, 0));
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public d1<Bitmap> loadBitmap(Uri uri) {
        return this.listeningExecutorService.submit((Callable) new c(this, uri, 1));
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public final /* synthetic */ d1 loadBitmapFromMetadata(MediaMetadata mediaMetadata) {
        return androidx.media3.common.util.b.a(this, mediaMetadata);
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public boolean supportsMimeType(String str) {
        return Util.isBitmapFactorySupportedMimeType(str);
    }

    @Deprecated
    public DataSourceBitmapLoader(Context context) {
        this(new Builder(context));
    }

    @Deprecated
    public DataSourceBitmapLoader(Context context, int i10) {
        this(new Builder(context).setMaximumOutputDimension(i10));
    }

    @Deprecated
    public DataSourceBitmapLoader(g1 g1Var, DataSource.Factory factory) {
        this(g1Var, factory, null);
    }

    @Deprecated
    public DataSourceBitmapLoader(g1 g1Var, DataSource.Factory factory, BitmapFactory.Options options) {
        this(g1Var, factory, options, -1);
    }

    @Deprecated
    public DataSourceBitmapLoader(g1 g1Var, DataSource.Factory factory, BitmapFactory.Options options, int i10) {
        this.listeningExecutorService = g1Var;
        this.dataSourceFactory = factory;
        this.options = options;
        this.maximumOutputDimension = i10;
        this.makeShared = false;
    }

    private DataSourceBitmapLoader(Builder builder) {
        DataSource.Factory factory;
        g1 g1Var;
        if (builder.dataSourceFactory != null) {
            factory = builder.dataSourceFactory;
        } else {
            factory = new DefaultDataSource.Factory(builder.context);
        }
        this.dataSourceFactory = factory;
        if (builder.listeningExecutorService != null) {
            g1Var = builder.listeningExecutorService;
        } else {
            g1Var = (g1) DEFAULT_EXECUTOR_SERVICE.get();
            g1Var.getClass();
        }
        this.listeningExecutorService = g1Var;
        this.options = builder.options;
        this.maximumOutputDimension = builder.maximumOutputDimension;
        this.makeShared = builder.makeShared;
    }
}
