package androidx.credentials.provider;

import android.app.PendingIntent;
import android.app.slice.Slice;
import android.app.slice.SliceItem;
import android.app.slice.SliceSpec;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.credentials.d;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.f;
import io.ktor.http.LinkHeader;
import java.util.Collections;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u000f2\u00020\u0001:\u0003\r\u000e\u000fB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0010"}, d2 = {"Landroidx/credentials/provider/Action;", "", LinkHeader.Parameters.Title, "", f.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", MediaTrack.ROLE_SUBTITLE, "(Ljava/lang/CharSequence;Landroid/app/PendingIntent;Ljava/lang/CharSequence;)V", "getPendingIntent", "()Landroid/app/PendingIntent;", "getSubtitle", "()Ljava/lang/CharSequence;", "getTitle", "Api34Impl", "Builder", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Action {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String SLICE_HINT_PENDING_INTENT = "androidx.credentials.provider.action.SLICE_HINT_PENDING_INTENT";
    private static final String SLICE_HINT_SUBTITLE = "androidx.credentials.provider.action.HINT_ACTION_SUBTEXT";
    private static final String SLICE_HINT_TITLE = "androidx.credentials.provider.action.HINT_ACTION_TITLE";
    private static final int SLICE_SPEC_REVISION = 0;
    private static final String SLICE_SPEC_TYPE = "Action";
    private static final String TAG = "Action";
    private final PendingIntent pendingIntent;
    private final CharSequence subtitle;
    private final CharSequence title;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/credentials/provider/Action$Api34Impl;", "", "()V", "fromAction", "Landroidx/credentials/provider/Action;", "action", "Landroid/service/credentials/Action;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();

        private Api34Impl() {
        }

        @n
        public static final Action fromAction(android.service.credentials.Action action) {
            return Action.INSTANCE.fromSlice(action.getSlice());
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/credentials/provider/Action$Builder;", "", LinkHeader.Parameters.Title, "", f.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "(Ljava/lang/CharSequence;Landroid/app/PendingIntent;)V", MediaTrack.ROLE_SUBTITLE, "build", "Landroidx/credentials/provider/Action;", "setSubtitle", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private final PendingIntent pendingIntent;
        private CharSequence subtitle;
        private final CharSequence title;

        public Builder(CharSequence charSequence, PendingIntent pendingIntent) {
            this.title = charSequence;
            this.pendingIntent = pendingIntent;
        }

        public final Action build() {
            return new Action(this.title, this.pendingIntent, this.subtitle);
        }

        public final Builder setSubtitle(CharSequence subtitle) {
            this.subtitle = subtitle;
            return this;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/credentials/provider/Action$Companion;", "", "()V", "SLICE_HINT_PENDING_INTENT", "", "SLICE_HINT_SUBTITLE", "SLICE_HINT_TITLE", "SLICE_SPEC_REVISION", "", "SLICE_SPEC_TYPE", "TAG", "fromAction", "Landroidx/credentials/provider/Action;", "action", "Landroid/service/credentials/Action;", "fromSlice", "slice", "Landroid/app/slice/Slice;", "toSlice", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final Action fromAction(android.service.credentials.Action action) {
            if (Build.VERSION.SDK_INT >= 34) {
                return Api34Impl.fromAction(action);
            }
            return null;
        }

        @n
        public final Action fromSlice(Slice slice) {
            Iterator<T> it = slice.getItems().iterator();
            CharSequence text = "";
            PendingIntent action = null;
            CharSequence text2 = null;
            while (it.hasNext()) {
                SliceItem sliceItemA = d.a(it.next());
                if (sliceItemA.hasHint(Action.SLICE_HINT_TITLE)) {
                    text = sliceItemA.getText();
                } else if (sliceItemA.hasHint(Action.SLICE_HINT_SUBTITLE)) {
                    text2 = sliceItemA.getText();
                } else if (sliceItemA.hasHint(Action.SLICE_HINT_PENDING_INTENT)) {
                    action = sliceItemA.getAction();
                }
            }
            try {
                return new Action(text, action, text2);
            } catch (Exception e5) {
                Log.i("Action", "fromSlice failed with: " + e5.getMessage());
                return null;
            }
        }

        @n
        public final Slice toSlice(Action action) {
            CharSequence title = action.getTitle();
            CharSequence subtitle = action.getSubtitle();
            PendingIntent pendingIntent = action.getPendingIntent();
            Slice.Builder builderAddText = new Slice.Builder(Uri.EMPTY, new SliceSpec("Action", 0)).addText(title, null, Collections.singletonList(Action.SLICE_HINT_TITLE)).addText(subtitle, null, Collections.singletonList(Action.SLICE_HINT_SUBTITLE));
            builderAddText.addAction(pendingIntent, new Slice.Builder(builderAddText).addHints(Collections.singletonList(Action.SLICE_HINT_PENDING_INTENT)).build(), null);
            return builderAddText.build();
        }

        private Companion() {
        }
    }

    public Action(CharSequence charSequence, PendingIntent pendingIntent, CharSequence charSequence2) {
        this.title = charSequence;
        this.pendingIntent = pendingIntent;
        this.subtitle = charSequence2;
        if (charSequence.length() <= 0) {
            throw new IllegalArgumentException("title must not be empty");
        }
    }

    @n
    public static final Action fromAction(android.service.credentials.Action action) {
        return INSTANCE.fromAction(action);
    }

    @n
    public static final Action fromSlice(Slice slice) {
        return INSTANCE.fromSlice(slice);
    }

    @n
    public static final Slice toSlice(Action action) {
        return INSTANCE.toSlice(action);
    }

    public final PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public final CharSequence getSubtitle() {
        return this.subtitle;
    }

    public final CharSequence getTitle() {
        return this.title;
    }

    public /* synthetic */ Action(CharSequence charSequence, PendingIntent pendingIntent, CharSequence charSequence2, int i10, h hVar) {
        this(charSequence, pendingIntent, (i10 & 4) != 0 ? null : charSequence2);
    }
}
