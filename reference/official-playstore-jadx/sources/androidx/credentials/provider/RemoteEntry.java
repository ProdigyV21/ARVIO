package androidx.credentials.provider;

import android.app.PendingIntent;
import android.app.slice.Slice;
import android.app.slice.SliceItem;
import android.app.slice.SliceSpec;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.credentials.d;
import com.google.android.gms.common.internal.f;
import java.util.Collections;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0003\u0007\b\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Landroidx/credentials/provider/RemoteEntry;", "", f.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "(Landroid/app/PendingIntent;)V", "getPendingIntent", "()Landroid/app/PendingIntent;", "Api34Impl", "Builder", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RemoteEntry {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int REVISION_ID = 1;
    private static final String SLICE_HINT_PENDING_INTENT = "androidx.credentials.provider.remoteEntry.SLICE_HINT_PENDING_INTENT";
    private static final String SLICE_SPEC_TYPE = "RemoteEntry";
    private static final String TAG = "RemoteEntry";
    private final PendingIntent pendingIntent;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/credentials/provider/RemoteEntry$Api34Impl;", "", "()V", "fromRemoteEntry", "Landroidx/credentials/provider/RemoteEntry;", "remoteEntry", "Landroid/service/credentials/RemoteEntry;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();

        private Api34Impl() {
        }

        @n
        public static final RemoteEntry fromRemoteEntry(android.service.credentials.RemoteEntry remoteEntry) {
            return RemoteEntry.INSTANCE.fromSlice(remoteEntry.getSlice());
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/credentials/provider/RemoteEntry$Builder;", "", f.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "(Landroid/app/PendingIntent;)V", "build", "Landroidx/credentials/provider/RemoteEntry;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private final PendingIntent pendingIntent;

        public Builder(PendingIntent pendingIntent) {
            this.pendingIntent = pendingIntent;
        }

        public final RemoteEntry build() {
            return new RemoteEntry(this.pendingIntent);
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0012\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\nH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/credentials/provider/RemoteEntry$Companion;", "", "()V", "REVISION_ID", "", "SLICE_HINT_PENDING_INTENT", "", "SLICE_SPEC_TYPE", "TAG", "fromRemoteEntry", "Landroidx/credentials/provider/RemoteEntry;", "remoteEntry", "Landroid/service/credentials/RemoteEntry;", "fromSlice", "slice", "Landroid/app/slice/Slice;", "toSlice", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final RemoteEntry fromRemoteEntry(android.service.credentials.RemoteEntry remoteEntry) {
            if (Build.VERSION.SDK_INT >= 34) {
                return Api34Impl.fromRemoteEntry(remoteEntry);
            }
            return null;
        }

        @n
        public final RemoteEntry fromSlice(Slice slice) {
            Iterator<T> it = slice.getItems().iterator();
            PendingIntent action = null;
            while (it.hasNext()) {
                SliceItem sliceItemA = d.a(it.next());
                if (sliceItemA.hasHint(RemoteEntry.SLICE_HINT_PENDING_INTENT)) {
                    action = sliceItemA.getAction();
                }
            }
            try {
                return new RemoteEntry(action);
            } catch (Exception e5) {
                Log.i("RemoteEntry", "fromSlice failed with: " + e5.getMessage());
                return null;
            }
        }

        @n
        public final Slice toSlice(RemoteEntry remoteEntry) {
            PendingIntent pendingIntent = remoteEntry.getPendingIntent();
            Slice.Builder builder = new Slice.Builder(Uri.EMPTY, new SliceSpec("RemoteEntry", 1));
            builder.addAction(pendingIntent, new Slice.Builder(builder).addHints(Collections.singletonList(RemoteEntry.SLICE_HINT_PENDING_INTENT)).build(), null);
            return builder.build();
        }

        private Companion() {
        }
    }

    public RemoteEntry(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    @n
    public static final RemoteEntry fromRemoteEntry(android.service.credentials.RemoteEntry remoteEntry) {
        return INSTANCE.fromRemoteEntry(remoteEntry);
    }

    @n
    public static final RemoteEntry fromSlice(Slice slice) {
        return INSTANCE.fromSlice(slice);
    }

    @n
    public static final Slice toSlice(RemoteEntry remoteEntry) {
        return INSTANCE.toSlice(remoteEntry);
    }

    public final PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }
}
