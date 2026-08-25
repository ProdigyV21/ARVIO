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
import io.ktor.http.LinkHeader;
import java.util.Collections;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \r2\u00020\u0001:\u0003\u000b\f\rB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Landroidx/credentials/provider/AuthenticationAction;", "", LinkHeader.Parameters.Title, "", f.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "(Ljava/lang/CharSequence;Landroid/app/PendingIntent;)V", "getPendingIntent", "()Landroid/app/PendingIntent;", "getTitle", "()Ljava/lang/CharSequence;", "Api34Impl", "Builder", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AuthenticationAction {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String SLICE_HINT_PENDING_INTENT = "androidx.credentials.provider.authenticationAction.SLICE_HINT_PENDING_INTENT";
    private static final String SLICE_HINT_TITLE = "androidx.credentials.provider.authenticationAction.SLICE_HINT_TITLE";
    private static final int SLICE_SPEC_REVISION = 0;
    private static final String SLICE_SPEC_TYPE = "AuthenticationAction";
    private static final String TAG = "AuthenticationAction";
    private final PendingIntent pendingIntent;
    private final CharSequence title;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/credentials/provider/AuthenticationAction$Api34Impl;", "", "()V", "fromAction", "Landroidx/credentials/provider/AuthenticationAction;", "authenticationAction", "Landroid/service/credentials/Action;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();

        private Api34Impl() {
        }

        @n
        public static final AuthenticationAction fromAction(android.service.credentials.Action authenticationAction) {
            return AuthenticationAction.INSTANCE.fromSlice(authenticationAction.getSlice());
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/credentials/provider/AuthenticationAction$Builder;", "", LinkHeader.Parameters.Title, "", f.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "(Ljava/lang/CharSequence;Landroid/app/PendingIntent;)V", "build", "Landroidx/credentials/provider/AuthenticationAction;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private final PendingIntent pendingIntent;
        private final CharSequence title;

        public Builder(CharSequence charSequence, PendingIntent pendingIntent) {
            this.title = charSequence;
            this.pendingIntent = pendingIntent;
        }

        public final AuthenticationAction build() {
            return new AuthenticationAction(this.title, this.pendingIntent);
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/credentials/provider/AuthenticationAction$Companion;", "", "()V", "SLICE_HINT_PENDING_INTENT", "", "SLICE_HINT_TITLE", "SLICE_SPEC_REVISION", "", "SLICE_SPEC_TYPE", "TAG", "fromAction", "Landroidx/credentials/provider/AuthenticationAction;", "authenticationAction", "Landroid/service/credentials/Action;", "fromSlice", "slice", "Landroid/app/slice/Slice;", "toSlice", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final AuthenticationAction fromAction(android.service.credentials.Action authenticationAction) {
            if (Build.VERSION.SDK_INT >= 34) {
                return Api34Impl.fromAction(authenticationAction);
            }
            return null;
        }

        @n
        public final AuthenticationAction fromSlice(Slice slice) {
            Iterator<T> it = slice.getItems().iterator();
            CharSequence text = null;
            PendingIntent action = null;
            while (it.hasNext()) {
                SliceItem sliceItemA = d.a(it.next());
                if (sliceItemA.hasHint(AuthenticationAction.SLICE_HINT_PENDING_INTENT)) {
                    action = sliceItemA.getAction();
                } else if (sliceItemA.hasHint(AuthenticationAction.SLICE_HINT_TITLE)) {
                    text = sliceItemA.getText();
                }
            }
            try {
                return new AuthenticationAction(text, action);
            } catch (Exception e5) {
                Log.i("AuthenticationAction", "fromSlice failed with: " + e5.getMessage());
                return null;
            }
        }

        @n
        public final Slice toSlice(AuthenticationAction authenticationAction) {
            CharSequence title = authenticationAction.getTitle();
            PendingIntent pendingIntent = authenticationAction.getPendingIntent();
            Slice.Builder builder = new Slice.Builder(Uri.EMPTY, new SliceSpec("AuthenticationAction", 0));
            builder.addAction(pendingIntent, new Slice.Builder(builder).addHints(Collections.singletonList(AuthenticationAction.SLICE_HINT_PENDING_INTENT)).build(), null).addText(title, null, Collections.singletonList(AuthenticationAction.SLICE_HINT_TITLE));
            return builder.build();
        }

        private Companion() {
        }
    }

    public AuthenticationAction(CharSequence charSequence, PendingIntent pendingIntent) {
        this.title = charSequence;
        this.pendingIntent = pendingIntent;
        if (charSequence.length() <= 0) {
            throw new IllegalArgumentException("title must not be empty");
        }
    }

    @n
    public static final AuthenticationAction fromAction(android.service.credentials.Action action) {
        return INSTANCE.fromAction(action);
    }

    @n
    public static final AuthenticationAction fromSlice(Slice slice) {
        return INSTANCE.fromSlice(slice);
    }

    @n
    public static final Slice toSlice(AuthenticationAction authenticationAction) {
        return INSTANCE.toSlice(authenticationAction);
    }

    public final PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public final CharSequence getTitle() {
        return this.title;
    }
}
