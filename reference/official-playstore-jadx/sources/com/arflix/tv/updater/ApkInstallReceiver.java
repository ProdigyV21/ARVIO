package com.arflix.tv.updater;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import androidx.compose.material3.d;
import com.arflix.tv.R;
import com.arflix.tv.updater.UpdateStatus;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\r\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eR\"\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/updater/ApkInstallReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "Landroid/content/Context;", "context", "", "text", "Lx6/t0;", "showToast", "(Landroid/content/Context;Ljava/lang/String;)V", "Landroid/content/Intent;", "intent", "onReceive", "(Landroid/content/Context;Landroid/content/Intent;)V", "Lcom/arflix/tv/updater/UpdateStatusManager;", "updateStatusManager", "Lcom/arflix/tv/updater/UpdateStatusManager;", "getUpdateStatusManager", "()Lcom/arflix/tv/updater/UpdateStatusManager;", "setUpdateStatusManager", "(Lcom/arflix/tv/updater/UpdateStatusManager;)V", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@AndroidEntryPoint
public final class ApkInstallReceiver extends Hilt_ApkInstallReceiver {
    private static final String TAG = "ApkInstallReceiver";

    @Inject
    public UpdateStatusManager updateStatusManager;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/arflix/tv/updater/ApkInstallReceiver$Companion;", "", "<init>", "()V", "TAG", "", "actionFor", "context", "Landroid/content/Context;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final String actionFor(Context context) {
            return d.m(context.getPackageName(), ".INSTALL_COMPLETE");
        }

        private Companion() {
        }
    }

    private final void showToast(Context context, String text) {
        try {
            Toast.makeText(context.getApplicationContext(), text, 1).show();
        } catch (Exception unused) {
        }
    }

    public final UpdateStatusManager getUpdateStatusManager() {
        UpdateStatusManager updateStatusManager = this.updateStatusManager;
        if (updateStatusManager != null) {
            return updateStatusManager;
        }
        p.i("updateStatusManager");
        throw null;
    }

    @Override // com.arflix.tv.updater.Hilt_ApkInstallReceiver, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", -999);
        String stringExtra = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
        switch (intExtra) {
            case -1:
                Intent intent2 = Build.VERSION.SDK_INT >= 33 ? (Intent) intent.getParcelableExtra("android.intent.extra.INTENT", Intent.class) : (Intent) intent.getParcelableExtra("android.intent.extra.INTENT");
                if (intent2 == null) {
                    Log.e(TAG, "STATUS_PENDING_USER_ACTION without EXTRA_INTENT — cannot prompt user.");
                } else {
                    intent2.addFlags(335544321);
                    try {
                        context.startActivity(intent2);
                        getUpdateStatusManager().updateStatus(new UpdateStatus.Installing(null));
                    } catch (Exception e5) {
                        Log.e(TAG, "Failed to launch install confirmation Activity: " + e5.getMessage(), e5);
                        showToast(context, context.getString(R.string.update_install_manual_confirm));
                        return;
                    }
                }
                break;
            case 0:
                Log.i(TAG, "Update installed successfully.");
                getUpdateStatusManager().updateStatus(UpdateStatus.Success.INSTANCE);
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                Log.e(TAG, "Update install failed: status=" + intExtra + " message=" + stringExtra);
                switch (intExtra) {
                    case 2:
                        stringExtra = context.getString(R.string.update_install_blocked);
                        break;
                    case 3:
                        stringExtra = context.getString(R.string.update_install_cancelled);
                        break;
                    case 4:
                        stringExtra = context.getString(R.string.update_install_invalid);
                        break;
                    case 5:
                        stringExtra = context.getString(R.string.update_install_conflict);
                        break;
                    case 6:
                        stringExtra = context.getString(R.string.update_install_storage);
                        break;
                    case 7:
                        stringExtra = context.getString(R.string.update_install_incompatible);
                        break;
                    default:
                        if (stringExtra == null) {
                            stringExtra = context.getString(R.string.update_install_failed);
                        }
                        break;
                }
                getUpdateStatusManager().updateStatus(new UpdateStatus.Failure(stringExtra, null, 2, null));
                showToast(context, stringExtra);
                break;
            default:
                Log.w(TAG, "Unexpected PackageInstaller status=" + intExtra + " message=" + stringExtra);
                break;
        }
    }

    public final void setUpdateStatusManager(UpdateStatusManager updateStatusManager) {
        this.updateStatusManager = updateStatusManager;
    }
}
