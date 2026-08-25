package androidx.core.app;

import android.app.Notification;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class c0 extends y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1960a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f1961b;

    public c0(int i10) {
        this.f1960a = i10;
        switch (i10) {
            case 1:
                this.f1961b = new ArrayList();
                break;
        }
    }

    @Override // androidx.core.app.y0
    public final void apply(t tVar) {
        switch (this.f1960a) {
            case 0:
                Notification.BigTextStyle bigTextStyleBigText = new Notification.BigTextStyle(((h1) tVar).f1994b).setBigContentTitle(this.mBigContentTitle).bigText((CharSequence) this.f1961b);
                if (this.mSummaryTextSet) {
                    bigTextStyleBigText.setSummaryText(this.mSummaryText);
                }
                break;
            default:
                Notification.InboxStyle bigContentTitle = new Notification.InboxStyle(((h1) tVar).f1994b).setBigContentTitle(this.mBigContentTitle);
                if (this.mSummaryTextSet) {
                    bigContentTitle.setSummaryText(this.mSummaryText);
                }
                Iterator it = ((ArrayList) this.f1961b).iterator();
                while (it.hasNext()) {
                    bigContentTitle.addLine((CharSequence) it.next());
                }
                break;
        }
    }

    @Override // androidx.core.app.y0
    public final void clearCompatExtraKeys(Bundle bundle) {
        switch (this.f1960a) {
            case 0:
                super.clearCompatExtraKeys(bundle);
                bundle.remove("android.bigText");
                break;
            default:
                super.clearCompatExtraKeys(bundle);
                bundle.remove("android.textLines");
                break;
        }
    }

    @Override // androidx.core.app.y0
    public final String getClassName() {
        switch (this.f1960a) {
            case 0:
                return "androidx.core.app.NotificationCompat$BigTextStyle";
            default:
                return "androidx.core.app.NotificationCompat$InboxStyle";
        }
    }

    @Override // androidx.core.app.y0
    public final void restoreFromCompatExtras(Bundle bundle) {
        switch (this.f1960a) {
            case 0:
                super.restoreFromCompatExtras(bundle);
                this.f1961b = bundle.getCharSequence("android.bigText");
                break;
            default:
                super.restoreFromCompatExtras(bundle);
                ArrayList arrayList = (ArrayList) this.f1961b;
                arrayList.clear();
                if (bundle.containsKey("android.textLines")) {
                    Collections.addAll(arrayList, bundle.getCharSequenceArray("android.textLines"));
                }
                break;
        }
    }
}
