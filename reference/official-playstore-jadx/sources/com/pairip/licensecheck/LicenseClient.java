package com.pairip.licensecheck;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.InstallSourceInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.media3.common.C;
import com.pairip.licensecheck.ILicenseV2ResultListener;
import com.pairip.licensecheck.LicenseActivity;
import com.pairip.licensecheck.LicenseClient;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class LicenseClient implements ServiceConnection {
    private static final String BACKGROUND_SERVICE_INTERFACE_CLASS_NAME = "com.android.vending.licensing.IBackgroundLicensingService";
    private static final int ERROR_INVALID_PACKAGE_NAME = 3;
    private static final int EVENTUAL_SHUTDOWN_DELAY_MILLIS = 30000;
    private static final String EXTRA_END_CUSTOM_TRIAL = "end_custom_trial";
    private static final int FIRST_ISOLATED_UID = 99000;
    private static final int FLAG_RPC_CALL = 0;
    private static final int LAST_ISOLATED_UID = 99999;
    private static final int LICENSED = 0;
    private static final int MAX_RETRIES = 3;
    private static final int MILLIS_PER_SEC = 1000;
    private static final long MIN_TRIAL_END_INTERVAL_MILLIS = 3000;
    private static final int NOT_LICENSED = 2;
    private static final String PAYLOAD_PAYWALL = "PAYWALL_INTENT";
    private static final int PER_USER_RANGE = 100000;
    private static final int REPEATED_CHECK_RETRY_DELAY_MILLIS = 300000;
    private static final int RETRY_DELAY_MILLIS = 1000;
    private static final String SERVICE_INTERFACE_CLASS_NAME = "com.android.vending.licensing.ILicensingService";
    private static final String SERVICE_PACKAGE = "com.android.vending";
    private static final String TAG = "LicenseClient";
    private static final int TRANSACTION_CHECK_LICENSE_V2 = 2;
    private static final int TRANSACTION_REPORT_SUCCESSFUL_LICENSE_CHECK = 3;
    protected static boolean backgroundLicensingServiceEnabled = false;
    protected static boolean customTrialEndTriggered = false;
    protected static boolean eventualShutdownEnabled = true;
    public static boolean gracefulShutdownEnabled = true;
    private static final Handler handler;
    private static LicenseClient instance = null;
    protected static long lastTrialEndElapsedRealtimeMillis = 0;
    protected static String licensePubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA895lUIUvO+2q7DGEdwgKtJo3I63lzSbJkK9hIys+kMCkfqNAPQBVEVyWzuhEeuZ9Yezy8tOFlg6SRM6bHOc7jGbPaDzxdV95MR4bPtPZMZAbhhpY4MULLyxYDG+RZnDDAqkPvRNGZGpuWqoAt6o87fmPYPBD0OyaoXvFnYScIO/25Na1Y/19kF2R1XZ1CC09lYnoVbqESx/2ZIpUt9UhyBIi6OIWabEuuMLAvV00KtErktIqNVN1zV++6zKlNECFMM7fbJtu969ERIRM3LK2afWpiMCi3UKwjrcP/3o0uLxhbGGqVnB57P9Ruj5CHsE2sGxjR2XNHMmuzeGYOHjCawIDAQAB";
    protected static boolean localCheckEnabled = true;
    protected static ImmediateTaskExecutor mainThreadRunner = null;
    protected static String packageName = "com.arvio.tv";
    protected static boolean repeatedCheckEnabled = true;
    protected static Bundle responsePayload;
    private final Context context;
    protected static Runnable exitAction = new Runnable() { // from class: com.pairip.licensecheck.LicenseClient.1
        @Override // java.lang.Runnable
        public void run() {
            System.exit(0);
        }
    };
    protected static LicenseCheckState licenseCheckState = LicenseCheckState.CHECK_REQUIRED;
    protected static ImmediateTaskExecutor backgroundRunner = new ImmediateTaskExecutor() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda4
        @Override // com.pairip.licensecheck.LicenseClient.ImmediateTaskExecutor
        public final void run(Runnable runnable) {
            new Thread(runnable).start();
        }
    };
    protected DelayedTaskExecutor delayedTaskExecutor = new DelayedTaskExecutorImpl();
    protected int retryNum = 0;
    protected boolean waitingForRepeatedCheck = false;
    private long repeatedCheckStartElapsedRealtime = 0;

    public interface DelayedTaskExecutor {
        void schedule(Runnable task, long delayMillis);
    }

    public interface ImmediateTaskExecutor {
        void run(Runnable task);
    }

    public enum LicenseCheckState {
        CHECK_REQUIRED,
        FULL_CHECK_OK,
        LOCAL_CHECK_OK,
        LOCAL_CHECK_REPORTED,
        REPEATED_CHECK_REQUIRED
    }

    static {
        final Handler handler2 = new Handler(Looper.getMainLooper());
        handler = handler2;
        Objects.requireNonNull(handler2);
        mainThreadRunner = new ImmediateTaskExecutor() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda5
            @Override // com.pairip.licensecheck.LicenseClient.ImmediateTaskExecutor
            public final void run(Runnable runnable) {
                handler2.post(runnable);
            }
        };
        customTrialEndTriggered = false;
        lastTrialEndElapsedRealtimeMillis = 0L;
    }

    private static synchronized LicenseClient getInstance(Context context) {
        LicenseClient licenseClient;
        licenseClient = instance;
        if (licenseClient == null) {
            licenseClient = new LicenseClient(context);
            instance = licenseClient;
        }
        return licenseClient;
    }

    public static void checkLicense(final Context context) {
        if (context == null) {
            Log.w(TAG, "Cannot check license with null context.");
        } else if (isIsolatedProcess()) {
            Log.i(TAG, "Skipping license check in isolated process.");
        } else {
            mainThreadRunner.run(new Runnable() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    LicenseClient.getInstance(context).initializeLicenseCheck();
                }
            });
        }
    }

    static void stopTrial(final Context context) {
        if (context == null) {
            Log.w(TAG, "Cannot trigger trial end with null context.");
        } else if (isIsolatedProcess()) {
            Log.i(TAG, "Skipping trial end in isolated process.");
        } else {
            mainThreadRunner.run(new Runnable() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    LicenseClient.getInstance(context).handleTrialEnd();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleTrialEnd() {
        long elapsedRealtimeMillis = getElapsedRealtimeMillis();
        long j10 = lastTrialEndElapsedRealtimeMillis;
        long j11 = elapsedRealtimeMillis - j10;
        if (j10 <= 0 || j11 >= 3000) {
            Log.i(TAG, "Trial end event triggered; initiating full license check.");
            lastTrialEndElapsedRealtimeMillis = elapsedRealtimeMillis;
            customTrialEndTriggered = true;
            licenseCheckState = LicenseCheckState.CHECK_REQUIRED;
            this.waitingForRepeatedCheck = false;
            initiateFreshLicensingServiceConnection(false);
            return;
        }
        Log.w(TAG, String.format("Trial end trigger throttled. Ignoring request (sent %d ms ago).", Long.valueOf(j11)));
    }

    private static boolean isIsolatedProcess() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Process.isIsolated();
        }
        int iMyUid = Process.myUid() % 100000;
        return iMyUid >= FIRST_ISOLATED_UID && iMyUid <= LAST_ISOLATED_UID;
    }

    public static String getLicensePubKey() {
        return licensePubKey;
    }

    public LicenseClient(Context context) {
        this.context = context;
    }

    public void initializeLicenseCheck() {
        int iOrdinal = licenseCheckState.ordinal();
        if (iOrdinal == 0) {
            if (localCheckEnabled && !customTrialEndTriggered) {
                backgroundRunner.run(new Runnable() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$initializeLicenseCheck$0();
                    }
                });
                return;
            } else {
                initiateFreshLicensingServiceConnection(false);
                return;
            }
        }
        if (iOrdinal != 1) {
            if (iOrdinal != 4) {
                return;
            }
            initiateFreshLicensingServiceConnection(false);
        } else {
            try {
                LicenseResponseHelper.validateResponse(responsePayload, packageName);
            } catch (LicenseCheckException e5) {
                handleError(e5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeLicenseCheck$0() {
        final boolean zPerformLocalInstallerCheck = performLocalInstallerCheck();
        mainThreadRunner.run(new Runnable() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$initializeLicenseCheck$1(zPerformLocalInstallerCheck);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeLicenseCheck$1(boolean z) {
        if (z) {
            licenseCheckState = LicenseCheckState.LOCAL_CHECK_OK;
        }
        initiateFreshLicensingServiceConnection(z && backgroundLicensingServiceEnabled);
    }

    private boolean performLocalInstallerCheck() {
        try {
            if (Build.VERSION.SDK_INT < 30) {
                Log.i(TAG, "Local install check bypassed due to old SDK version.");
                return false;
            }
            PackageManager packageManager = this.context.getPackageManager();
            if (packageManager == null) {
                Log.i(TAG, "Local install check bypassed due to package manager not found.");
                return false;
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            if (packageInfo != null && packageInfo.applicationInfo != null) {
                int i10 = packageInfo.applicationInfo.flags;
                if ((i10 & 1) == 0 && (i10 & 128) == 0) {
                    InstallSourceInfo installSourceInfo = packageManager.getInstallSourceInfo(packageName);
                    if (installSourceInfo == null) {
                        Log.i(TAG, "Local install check bypassed due to install source info not found.");
                        return false;
                    }
                    String installingPackageName = installSourceInfo.getInstallingPackageName();
                    if (installingPackageName != null && installingPackageName.equals(SERVICE_PACKAGE)) {
                        return true;
                    }
                    Log.i(TAG, "Local install check failed due to wrong installer.");
                    return false;
                }
                Log.i(TAG, "Local install check passed due to system app.");
                return true;
            }
            Log.i(TAG, "Local install check bypassed due to app package info not found.");
            return false;
        } catch (Exception e5) {
            Log.w(TAG, "Could not obtain package info for local installer check.", e5);
            return false;
        }
    }

    private void initiateFreshLicensingServiceConnection(boolean useBackgroundService) {
        this.retryNum = 0;
        lambda$retryOrThrow$1(useBackgroundService);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindToLicensingService, reason: merged with bridge method [inline-methods] */
    public void lambda$retryOrThrow$1(boolean useBackgroundService) {
        String str;
        String str2;
        if (useBackgroundService) {
            str = "Connecting to the background licensing service...";
        } else {
            str = "Connecting to the main licensing service...";
        }
        Log.d(TAG, str);
        if (useBackgroundService) {
            str2 = BACKGROUND_SERVICE_INTERFACE_CLASS_NAME;
        } else {
            str2 = SERVICE_INTERFACE_CLASS_NAME;
        }
        try {
            if (this.context.bindService(new Intent(str2).setPackage(SERVICE_PACKAGE).setAction(str2), this, 1)) {
                return;
            }
            retryOrThrow(new LicenseCheckException("Could not bind with the licensing service: ".concat(str2)), useBackgroundService, useBackgroundService);
        } catch (SecurityException e5) {
            retryOrThrow(new LicenseCheckException("Not allowed to bind with the licensing service: ".concat(str2), e5), useBackgroundService, useBackgroundService);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, final IBinder licensingServiceBinder) {
        Log.d(TAG, "Connected to the licensing service.");
        int iOrdinal = licenseCheckState.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 2) {
                backgroundRunner.run(new Runnable() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onServiceConnected$1(licensingServiceBinder);
                    }
                });
                return;
            } else if (iOrdinal != 4) {
                return;
            }
        }
        backgroundRunner.run(new Runnable() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onServiceConnected$0(licensingServiceBinder);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onServiceConnected$0(IBinder iBinder) {
        try {
            checkLicenseInternal(iBinder);
        } catch (RemoteException e5) {
            handleError(new LicenseCheckException("Error when getting interface descriptor.", e5));
        } catch (LicenseCheckException e6) {
            handleError(e6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onServiceConnected$1(IBinder iBinder) {
        try {
            reportSuccessfulLicenseCheck(iBinder);
        } catch (Exception e5) {
            Log.e(TAG, "Error while reporting license check: " + Log.getStackTraceString(e5));
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        if (licenseCheckState.equals(LicenseCheckState.REPEATED_CHECK_REQUIRED) && this.waitingForRepeatedCheck) {
            Log.d(TAG, "Ignoring service disconnection in REPEATED_CHECK_REQUIRED state.");
        } else {
            Log.w(TAG, "Unexpectedly disconnected from the licensing service.");
            retryOrThrow(new LicenseCheckException("Licensing service unexpectedly disconnected."));
        }
    }

    private void checkLicenseInternal(IBinder licensingServiceBinder) throws LicenseCheckException, RemoteException {
        if (licensingServiceBinder == null) {
            retryOrThrow(new LicenseCheckException("Received a null binder."));
            return;
        }
        if (licensingServiceBinder.getInterfaceDescriptor().equals(BACKGROUND_SERVICE_INTERFACE_CLASS_NAME)) {
            throw new LicenseCheckException("Background licensing service does not support full license check.");
        }
        Log.d(TAG, "Sending request to licensing service...");
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                populateInputDataForLicenseCheckV2(parcelObtain, licensingServiceBinder);
                if (!licensingServiceBinder.transact(2, parcelObtain, parcelObtain2, 0)) {
                    handleError(new LicenseCheckException("Licensing service could not process request."));
                }
            } catch (DeadObjectException e5) {
                retryOrThrow(new LicenseCheckException("Licensing service process died.", e5));
            } catch (RemoteException e6) {
                handleError(new LicenseCheckException("Error when calling licensing service.", e6));
            }
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
            Log.d(TAG, "Request to licensing service sent.");
        }
    }

    public void reportSuccessfulLicenseCheck(IBinder licensingServiceBinder) throws LicenseCheckException {
        if (licensingServiceBinder == null) {
            retryOrThrow(new LicenseCheckException("Received a null binder."), true, backgroundLicensingServiceEnabled);
            return;
        }
        Log.d(TAG, "Sending request to license reporting service...");
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                try {
                    populateInputDataForReportAutoVerifiedLicense(parcelObtain, licensingServiceBinder);
                    boolean zTransact = licensingServiceBinder.transact(3, parcelObtain, parcelObtain2, 0);
                    if (!zTransact) {
                        Log.e(TAG, "Error sending request to license reporting service.");
                    }
                    if (zTransact) {
                        mainThreadRunner.run(new Runnable() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda11
                            @Override // java.lang.Runnable
                            public final void run() {
                                LicenseClient.licenseCheckState = LicenseClient.LicenseCheckState.LOCAL_CHECK_REPORTED;
                            }
                        });
                    }
                } catch (RemoteException e5) {
                    Log.e(TAG, "Error when calling licensing service." + String.valueOf(e5));
                }
            } catch (DeadObjectException e6) {
                retryOrThrow(new LicenseCheckException("Licensing service process died.", e6), true, backgroundLicensingServiceEnabled);
            }
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
            Log.d(TAG, "Request to licensing reporting service sent.");
        }
    }

    private void populateInputDataForLicenseCheckV2(Parcel inputData, IBinder licensingService) throws RemoteException {
        inputData.writeInterfaceToken(licensingService.getInterfaceDescriptor());
        inputData.writeString(packageName);
        inputData.writeStrongBinder(createResultListener(this).asBinder());
        Bundle bundle = new Bundle();
        if (customTrialEndTriggered) {
            bundle.putBoolean(EXTRA_END_CUSTOM_TRIAL, true);
        }
        if (!bundle.isEmpty()) {
            inputData.writeInt(1);
            bundle.writeToParcel(inputData, 0);
        } else {
            inputData.writeInt(0);
        }
    }

    private void populateInputDataForReportAutoVerifiedLicense(Parcel inputData, IBinder licensingService) throws RemoteException {
        inputData.writeInterfaceToken(licensingService.getInterfaceDescriptor());
        inputData.writeString(packageName);
        inputData.writeInt(0);
    }

    private static ILicenseV2ResultListener createResultListener(LicenseClient client) {
        return new ILicenseV2ResultListener.Stub() { // from class: com.pairip.licensecheck.LicenseClient.2
            @Override // com.pairip.licensecheck.ILicenseV2ResultListener
            public void verifyLicense(int responseCode, Bundle responsePayload2) {
                LicenseClient.this.processResponse(responseCode, responsePayload2);
            }
        };
    }

    private void retryOrThrow(LicenseCheckException error) {
        retryOrThrow(error, false, false);
    }

    private void retryOrThrow(final LicenseCheckException error, final boolean ignoreErrorOnFinalFailure, final boolean useBackgroundService) {
        mainThreadRunner.run(new Runnable() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$retryOrThrow$0(useBackgroundService, error, ignoreErrorOnFinalFailure);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$retryOrThrow$0(final boolean z, LicenseCheckException licenseCheckException, boolean z5) {
        int i10 = this.retryNum;
        if (i10 < 3) {
            this.retryNum = i10 + 1;
            this.delayedTaskExecutor.schedule(new Runnable() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda12
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$retryOrThrow$1(z);
                }
            }, 1000L);
            Log.d(TAG, String.format("Retry #%d. License check failed with error '%s'. Next try in %ds...", Integer.valueOf(this.retryNum), licenseCheckException == null ? "null" : licenseCheckException.getMessage(), 1L));
        } else {
            if (z5) {
                Log.e(TAG, "Retry limit reached for: " + String.valueOf(licenseCheckException));
                return;
            }
            handleError(licenseCheckException);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processResponse(int responseCode, final Bundle responsePayload2) {
        try {
            if (responseCode == 3) {
                throw new LicenseCheckException("Request package name invalid.");
            }
            if (responseCode != 0) {
                if (responseCode == 2) {
                    startPaywallActivity((PendingIntent) responsePayload2.getParcelable(PAYLOAD_PAYWALL));
                    return;
                }
                throw new LicenseCheckException(String.format("Unexpected response code %d received.", Integer.valueOf(responseCode)));
            }
            LicenseResponseHelper.validateResponse(responsePayload2, packageName);
            Log.i(TAG, "License check succeeded.");
            final RepeatedCheckMetadata repeatedCheckMetadata = repeatedCheckEnabled ? LicenseResponseHelper.getRepeatedCheckMetadata(responsePayload2) : null;
            mainThreadRunner.run(new Runnable() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$processResponse$0(repeatedCheckMetadata, responsePayload2);
                }
            });
        } catch (LicenseCheckException e5) {
            handleError(e5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$processResponse$0(RepeatedCheckMetadata repeatedCheckMetadata, Bundle bundle) {
        if (repeatedCheckMetadata != null) {
            licenseCheckState = LicenseCheckState.REPEATED_CHECK_REQUIRED;
            this.repeatedCheckStartElapsedRealtime = getElapsedRealtimeMillis();
            scheduleRepeatedLicenseCheck(repeatedCheckMetadata);
        } else {
            licenseCheckState = LicenseCheckState.FULL_CHECK_OK;
        }
        responsePayload = bundle;
    }

    private void scheduleRepeatedLicenseCheck(final RepeatedCheckMetadata repeatedCheckMetadata) {
        long jMin = Math.min(Math.min(repeatedCheckMetadata.getDurationToRetryMillis(), Math.max(0L, repeatedCheckMetadata.getTimeToRetryMillis() - getCurrentTimeMillis())), 300000L);
        if (!this.waitingForRepeatedCheck) {
            this.waitingForRepeatedCheck = true;
            try {
                this.context.unbindService(this);
            } catch (RuntimeException e5) {
                Log.e(TAG, "Failed to unbind service for repeated license check.", e5);
            }
        }
        this.delayedTaskExecutor.schedule(new Runnable() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$scheduleRepeatedLicenseCheck$0(repeatedCheckMetadata);
            }
        }, jMin);
        Log.d(TAG, String.format("Repeated license check is scheduled in %d ms...", Long.valueOf(jMin)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleRepeatedLicenseCheck$0(RepeatedCheckMetadata repeatedCheckMetadata) {
        long elapsedRealtimeMillis = getElapsedRealtimeMillis() - this.repeatedCheckStartElapsedRealtime;
        if (getCurrentTimeMillis() >= repeatedCheckMetadata.getTimeToRetryMillis() || elapsedRealtimeMillis >= repeatedCheckMetadata.getDurationToRetryMillis()) {
            this.waitingForRepeatedCheck = false;
            initiateFreshLicensingServiceConnection(false);
        } else {
            Log.d(TAG, "Repeated license check is rescheduled.");
            scheduleRepeatedLicenseCheck(repeatedCheckMetadata);
        }
    }

    private void handleError(final LicenseCheckException ex) {
        mainThreadRunner.run(new Runnable() { // from class: com.pairip.licensecheck.LicenseClient$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$handleError$0(ex);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleError$0(LicenseCheckException licenseCheckException) {
        Log.e(TAG, "Error while checking license: " + Log.getStackTraceString(licenseCheckException));
        if (licenseCheckState.equals(LicenseCheckState.FULL_CHECK_OK)) {
            return;
        }
        startErrorDialogActivity();
    }

    private void startPaywallActivity(PendingIntent paywallIntent) {
        Intent intentCreateCloseAppIntentOrExitIfAppInBackground = createCloseAppIntentOrExitIfAppInBackground();
        intentCreateCloseAppIntentOrExitIfAppInBackground.putExtra(LicenseActivity.PAYWALL_INTENT_ARG_NAME, paywallIntent);
        intentCreateCloseAppIntentOrExitIfAppInBackground.putExtra(LicenseActivity.ACTIVITY_TYPE_ARG_NAME, LicenseActivity.ActivityType.PAYWALL);
        scheduleAppShutdown();
        this.context.startActivity(intentCreateCloseAppIntentOrExitIfAppInBackground);
    }

    private void startErrorDialogActivity() {
        Intent intentCreateCloseAppIntentOrExitIfAppInBackground = createCloseAppIntentOrExitIfAppInBackground();
        intentCreateCloseAppIntentOrExitIfAppInBackground.putExtra(LicenseActivity.ACTIVITY_TYPE_ARG_NAME, LicenseActivity.ActivityType.ERROR_DIALOG);
        scheduleAppShutdown();
        this.context.startActivity(intentCreateCloseAppIntentOrExitIfAppInBackground);
    }

    private Intent createCloseAppIntentOrExitIfAppInBackground() {
        if (!isForeground()) {
            exitAction.run();
        }
        Intent intent = new Intent(this.context, (Class<?>) LicenseActivity.class);
        if (gracefulShutdownEnabled) {
            intent.addFlags(65536);
        } else {
            intent.addFlags(C.BUFFER_FLAG_NOT_DEPENDED_ON);
            intent.addFlags(32768);
        }
        intent.addFlags(268435456);
        return intent;
    }

    private boolean isForeground() {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        return runningAppProcessInfo.importance <= 100;
    }

    protected long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    protected long getElapsedRealtimeMillis() {
        return SystemClock.elapsedRealtime();
    }

    private void scheduleAppShutdown() {
        if (eventualShutdownEnabled) {
            this.delayedTaskExecutor.schedule(exitAction, 30000L);
        }
    }

    private static class DelayedTaskExecutorImpl implements DelayedTaskExecutor {
        private final Handler handler;

        private DelayedTaskExecutorImpl() {
            this.handler = new Handler(Looper.getMainLooper());
        }

        @Override // com.pairip.licensecheck.LicenseClient.DelayedTaskExecutor
        public void schedule(Runnable task, long delayMillis) {
            this.handler.postDelayed(task, delayMillis);
        }
    }
}
