package androidx.media3.session;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Log;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.legacy.MediaBrowserCompat;
import com.google.common.collect.t3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class MediaBrowserImplLegacy extends MediaControllerImplLegacy implements MediaBrowser.MediaBrowserImpl {
    private static final String TAG = "MB2ImplLegacy";
    private final HashMap<MediaLibraryService.LibraryParams, MediaBrowserCompat> browserCompats;
    private com.google.common.collect.l1 commandButtonsForMediaItems;
    private final MediaBrowser instance;
    private final HashMap<String, List<SubscribeCallback>> subscribeCallbacks;

    /* JADX INFO: renamed from: androidx.media3.session.MediaBrowserImplLegacy$2, reason: invalid class name */
    public class AnonymousClass2 extends MediaBrowserCompat.SearchCallback {
        public AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onError$1(String str, MediaBrowser.Listener listener) {
            listener.onSearchResultChanged(MediaBrowserImplLegacy.this.getInstance(), str, 0, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSearchResult$0(String str, List list, MediaBrowser.Listener listener) {
            listener.onSearchResultChanged(MediaBrowserImplLegacy.this.getInstance(), str, list.size(), null);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SearchCallback
        public void onError(String str, Bundle bundle) {
            MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new k(this, str, 0));
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SearchCallback
        public void onSearchResult(String str, Bundle bundle, List<MediaBrowserCompat.MediaItem> list) {
            MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new j(this, str, list, 0));
        }
    }

    public class GetChildrenCallback extends MediaBrowserCompat.SubscriptionCallback {
        private final com.google.common.util.concurrent.q1 future;
        private final String parentId;

        public GetChildrenCallback(com.google.common.util.concurrent.q1 q1Var, String str) {
            this.future = q1Var;
            this.parentId = str;
        }

        private void onChildrenLoadedInternal(String str, List<MediaBrowserCompat.MediaItem> list) {
            if (TextUtils.isEmpty(str)) {
                Log.w(MediaBrowserImplLegacy.TAG, "GetChildrenCallback.onChildrenLoaded(): Ignoring empty parentId");
                return;
            }
            MediaBrowserCompat browserCompat = MediaBrowserImplLegacy.this.getBrowserCompat();
            if (browserCompat == null) {
                this.future.set(LibraryResult.ofError(-100));
                return;
            }
            browserCompat.unsubscribe(this.parentId, this);
            if (list == null) {
                this.future.set(LibraryResult.ofError(-1));
            } else {
                this.future.set(LibraryResult.ofItemList(LegacyConversions.convertBrowserItemListToMediaItemList(list), null));
            }
        }

        private void onErrorInternal() {
            this.future.set(LibraryResult.ofError(-1));
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list) {
            onChildrenLoadedInternal(str, list);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str) {
            onErrorInternal();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            onChildrenLoadedInternal(str, list);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            onErrorInternal();
        }
    }

    public class GetLibraryRootCallback extends MediaBrowserCompat.ConnectionCallback {
        private final MediaLibraryService.LibraryParams params;
        private final com.google.common.util.concurrent.q1 result;

        public GetLibraryRootCallback(com.google.common.util.concurrent.q1 q1Var, MediaLibraryService.LibraryParams libraryParams) {
            this.result = q1Var;
            this.params = libraryParams;
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnected() {
            ArrayList parcelableArrayList;
            MediaBrowserCompat mediaBrowserCompat = (MediaBrowserCompat) MediaBrowserImplLegacy.this.browserCompats.get(this.params);
            if (mediaBrowserCompat == null) {
                this.result.set(LibraryResult.ofError(-1));
                return;
            }
            Bundle extras = mediaBrowserCompat.getExtras();
            if (extras != null && (parcelableArrayList = extras.getParcelableArrayList(androidx.media3.session.legacy.MediaConstants.BROWSER_SERVICE_EXTRAS_KEY_CUSTOM_BROWSER_ACTION_ROOT_LIST)) != null) {
                com.google.common.collect.k1 k1Var = null;
                for (int i10 = 0; i10 < parcelableArrayList.size(); i10++) {
                    CommandButton commandButtonConvertCustomBrowseActionToCommandButton = LegacyConversions.convertCustomBrowseActionToCommandButton((Bundle) parcelableArrayList.get(i10));
                    if (commandButtonConvertCustomBrowseActionToCommandButton != null) {
                        if (k1Var == null) {
                            k1Var = new com.google.common.collect.k1(4);
                            k1Var.e(MediaBrowserImplLegacy.this.commandButtonsForMediaItems.entrySet());
                        }
                        SessionCommand sessionCommand = commandButtonConvertCustomBrowseActionToCommandButton.sessionCommand;
                        sessionCommand.getClass();
                        k1Var.c(sessionCommand.customAction, commandButtonConvertCustomBrowseActionToCommandButton);
                    }
                }
                if (k1Var != null) {
                    MediaBrowserImplLegacy.this.commandButtonsForMediaItems = k1Var.b();
                }
            }
            this.result.set(LibraryResult.ofItem(MediaBrowserImplLegacy.this.createRootMediaItem(mediaBrowserCompat), LegacyConversions.convertToLibraryParams(MediaBrowserImplLegacy.this.context, extras)));
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnectionFailed() {
            this.result.set(LibraryResult.ofError(-3));
            MediaBrowserImplLegacy.this.release();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnectionSuspended() {
            onConnectionFailed();
        }
    }

    public class SubscribeCallback extends MediaBrowserCompat.SubscriptionCallback {
        private final com.google.common.util.concurrent.q1 future;
        private List<MediaBrowserCompat.MediaItem> receivedChildren;
        private final Bundle subscriptionOptions;
        private final String subscriptionParentId;

        public SubscribeCallback(String str, Bundle bundle, com.google.common.util.concurrent.q1 q1Var) {
            this.subscriptionParentId = str;
            this.subscriptionOptions = bundle;
            this.future = q1Var;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onChildrenLoadedInternal$1(String str, List list, MediaLibraryService.LibraryParams libraryParams, MediaBrowser.Listener listener) {
            listener.onChildrenChanged(MediaBrowserImplLegacy.this.getInstance(), str, list.size(), libraryParams);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onErrorInternal$0(String str, Bundle bundle, MediaBrowser.Listener listener) {
            listener.onChildrenChanged(MediaBrowserImplLegacy.this.getInstance(), str, Integer.MAX_VALUE, new MediaLibraryService.LibraryParams.Builder().setExtras(bundle).build());
        }

        private void onChildrenLoadedInternal(String str, List<MediaBrowserCompat.MediaItem> list) {
            if (TextUtils.isEmpty(str)) {
                Log.w(MediaBrowserImplLegacy.TAG, "SubscribeCallback.onChildrenLoaded(): Ignoring empty parentId");
                return;
            }
            MediaBrowserCompat browserCompat = MediaBrowserImplLegacy.this.getBrowserCompat();
            if (browserCompat == null) {
                return;
            }
            if (list == null) {
                onError(this.subscriptionParentId, this.subscriptionOptions);
                return;
            }
            MediaLibraryService.LibraryParams libraryParamsConvertToLibraryParams = LegacyConversions.convertToLibraryParams(MediaBrowserImplLegacy.this.context, browserCompat.getNotifyChildrenChangedOptions());
            this.receivedChildren = list;
            MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new l(this, str, list, libraryParamsConvertToLibraryParams));
            this.future.set(LibraryResult.ofVoid());
        }

        private void onErrorInternal(String str, Bundle bundle) {
            if (this.future.isDone()) {
                MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new j(this, str, bundle, 1));
            }
            this.future.set(LibraryResult.ofError(-1));
        }

        public boolean canServeGetChildrenRequest(String str, int i10) {
            return this.subscriptionParentId.equals(str) && this.receivedChildren != null && i10 == this.subscriptionOptions.getInt(MediaBrowserCompat.EXTRA_PAGE, 0);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list) {
            onChildrenLoadedInternal(this.subscriptionParentId, list);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str) {
            onError(this.subscriptionParentId, this.subscriptionOptions);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            onChildrenLoadedInternal(this.subscriptionParentId, list);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            onErrorInternal(this.subscriptionParentId, this.subscriptionOptions);
        }
    }

    public MediaBrowserImplLegacy(Context context, MediaBrowser mediaBrowser, SessionToken sessionToken, Bundle bundle, Looper looper, androidx.media3.common.util.BitmapLoader bitmapLoader, long j10) {
        super(context, mediaBrowser, sessionToken, bundle, looper, bitmapLoader, j10);
        this.browserCompats = new HashMap<>();
        this.subscribeCallbacks = new HashMap<>();
        this.instance = mediaBrowser;
        this.commandButtonsForMediaItems = t3.f14114q;
    }

    private static Bundle createOptionsForSubscription(MediaLibraryService.LibraryParams libraryParams) {
        return libraryParams == null ? new Bundle() : new Bundle(libraryParams.extras);
    }

    private static Bundle createOptionsWithPagingInfo(MediaLibraryService.LibraryParams libraryParams, int i10, int i11) {
        Bundle bundleCreateOptionsForSubscription = createOptionsForSubscription(libraryParams);
        bundleCreateOptionsForSubscription.putInt(MediaBrowserCompat.EXTRA_PAGE, i10);
        bundleCreateOptionsForSubscription.putInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, i11);
        return bundleCreateOptionsForSubscription;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaItem createRootMediaItem(MediaBrowserCompat mediaBrowserCompat) {
        String root = mediaBrowserCompat.getRoot();
        return new MediaItem.Builder().setMediaId(root).setMediaMetadata(new MediaMetadata.Builder().setIsBrowsable(Boolean.TRUE).setMediaType(20).setIsPlayable(Boolean.FALSE).setExtras(mediaBrowserCompat.getExtras()).build()).build();
    }

    private void evictChildrenFromSubscription(String str) {
        List<SubscribeCallback> list = this.subscribeCallbacks.get(str);
        if (list == null) {
            return;
        }
        for (int i10 = 0; i10 < list.size(); i10++) {
            if (list.get(i10).receivedChildren != null) {
                list.get(i10).receivedChildren = null;
                return;
            }
        }
    }

    private MediaBrowserCompat getBrowserCompat(MediaLibraryService.LibraryParams libraryParams) {
        return this.browserCompats.get(libraryParams);
    }

    private List<MediaBrowserCompat.MediaItem> getChildrenFromSubscription(String str, int i10) {
        List<SubscribeCallback> list = this.subscribeCallbacks.get(str);
        if (list == null) {
            return null;
        }
        for (int i11 = 0; i11 < list.size(); i11++) {
            if (list.get(i11).canServeGetChildrenRequest(str, i10)) {
                return list.get(i11).receivedChildren;
            }
        }
        return null;
    }

    private static Bundle getExtras(MediaLibraryService.LibraryParams libraryParams) {
        if (libraryParams != null) {
            return libraryParams.extras;
        }
        return null;
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public SessionCommands getAvailableSessionCommands() {
        return getBrowserCompat() != null ? super.getAvailableSessionCommands().buildUpon().addAllLibraryCommands().build() : super.getAvailableSessionCommands();
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> getChildren(String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN)) {
            return xc.d.s0(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return xc.d.s0(LibraryResult.ofError(-100));
        }
        Bundle bundleCreateOptionsWithPagingInfo = createOptionsWithPagingInfo(libraryParams, i10, i11);
        com.google.common.util.concurrent.q1 q1Var = new com.google.common.util.concurrent.q1();
        List<MediaBrowserCompat.MediaItem> childrenFromSubscription = getChildrenFromSubscription(str, i10);
        evictChildrenFromSubscription(str);
        if (childrenFromSubscription != null) {
            q1Var.set(LibraryResult.ofItemList(LegacyConversions.convertBrowserItemListToMediaItemList(childrenFromSubscription), new MediaLibraryService.LibraryParams.Builder().setExtras(bundleCreateOptionsWithPagingInfo).build()));
            return q1Var;
        }
        browserCompat.subscribe(str, bundleCreateOptionsWithPagingInfo, new GetChildrenCallback(q1Var, str));
        return q1Var;
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.collect.h1 getCommandButtonsForMediaItem(MediaItem mediaItem) {
        com.google.common.collect.h1 h1Var = mediaItem.mediaMetadata.supportedCommands;
        com.google.common.collect.a0.c(4, "initialCapacity");
        Object[] objArrCopyOf = new Object[4];
        int i10 = 0;
        for (int i11 = 0; i11 < h1Var.size(); i11++) {
            CommandButton commandButton = (CommandButton) this.commandButtonsForMediaItems.get(h1Var.get(i11));
            if (commandButton != null && commandButton.sessionCommand != null) {
                int i12 = i10 + 1;
                int iB = com.google.common.collect.b1.b(objArrCopyOf.length, i12);
                if (iB > objArrCopyOf.length) {
                    objArrCopyOf = Arrays.copyOf(objArrCopyOf, iB);
                }
                objArrCopyOf[i10] = commandButton;
                i10 = i12;
            }
        }
        return com.google.common.collect.h1.h(i10, objArrCopyOf);
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> getItem(String str) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM)) {
            return xc.d.s0(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return xc.d.s0(LibraryResult.ofError(-100));
        }
        final com.google.common.util.concurrent.q1 q1Var = new com.google.common.util.concurrent.q1();
        browserCompat.getItem(str, new MediaBrowserCompat.ItemCallback() { // from class: androidx.media3.session.MediaBrowserImplLegacy.1
            @Override // androidx.media3.session.legacy.MediaBrowserCompat.ItemCallback
            public void onError(String str2) {
                q1Var.set(LibraryResult.ofError(-1));
            }

            @Override // androidx.media3.session.legacy.MediaBrowserCompat.ItemCallback
            public void onItemLoaded(MediaBrowserCompat.MediaItem mediaItem) {
                if (mediaItem != null) {
                    q1Var.set(LibraryResult.ofItem(LegacyConversions.convertToMediaItem(mediaItem), null));
                } else {
                    q1Var.set(LibraryResult.ofError(-3));
                }
            }
        });
        return q1Var;
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> getLibraryRoot(MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(50000)) {
            return xc.d.s0(LibraryResult.ofError(-4));
        }
        com.google.common.util.concurrent.q1 q1Var = new com.google.common.util.concurrent.q1();
        MediaBrowserCompat browserCompat = getBrowserCompat(libraryParams);
        if (browserCompat != null) {
            q1Var.set(LibraryResult.ofItem(createRootMediaItem(browserCompat), null));
            return q1Var;
        }
        Bundle bundle = libraryParams == null ? new Bundle() : LegacyConversions.convertToRootHints(libraryParams);
        bundle.putInt(androidx.media3.session.legacy.MediaConstants.BROWSER_ROOT_HINTS_KEY_CUSTOM_BROWSER_ACTION_LIMIT, getInstance().getMaxCommandsForMediaItems());
        MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(getContext(), getConnectedToken().getComponentName(), new GetLibraryRootCallback(q1Var, libraryParams), bundle);
        this.browserCompats.put(libraryParams, mediaBrowserCompat);
        mediaBrowserCompat.connect();
        return q1Var;
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> getSearchResult(String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT)) {
            return xc.d.s0(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return xc.d.s0(LibraryResult.ofError(-100));
        }
        final com.google.common.util.concurrent.q1 q1Var = new com.google.common.util.concurrent.q1();
        Bundle bundleCreateOptionsWithPagingInfo = createOptionsWithPagingInfo(libraryParams, i10, i11);
        bundleCreateOptionsWithPagingInfo.putInt(MediaBrowserCompat.EXTRA_PAGE, i10);
        bundleCreateOptionsWithPagingInfo.putInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, i11);
        browserCompat.search(str, bundleCreateOptionsWithPagingInfo, new MediaBrowserCompat.SearchCallback() { // from class: androidx.media3.session.MediaBrowserImplLegacy.3
            @Override // androidx.media3.session.legacy.MediaBrowserCompat.SearchCallback
            public void onError(String str2, Bundle bundle) {
                q1Var.set(LibraryResult.ofError(-1));
            }

            @Override // androidx.media3.session.legacy.MediaBrowserCompat.SearchCallback
            public void onSearchResult(String str2, Bundle bundle, List<MediaBrowserCompat.MediaItem> list) {
                q1Var.set(LibraryResult.ofItemList(LegacyConversions.convertBrowserItemListToMediaItemList(list), null));
            }
        });
        return q1Var;
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public void release() {
        Iterator<MediaBrowserCompat> it = this.browserCompats.values().iterator();
        while (it.hasNext()) {
            it.next().disconnect();
        }
        this.browserCompats.clear();
        super.release();
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<Void>> search(String str, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_SEARCH)) {
            return xc.d.s0(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return xc.d.s0(LibraryResult.ofError(-100));
        }
        browserCompat.search(str, getExtras(libraryParams), new AnonymousClass2());
        return xc.d.s0(LibraryResult.ofVoid());
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        return sendCustomCommand(sessionCommand, bundle, null);
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<Void>> subscribe(String str, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE)) {
            return xc.d.s0(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return xc.d.s0(LibraryResult.ofError(-100));
        }
        Bundle bundleCreateOptionsForSubscription = createOptionsForSubscription(libraryParams);
        com.google.common.util.concurrent.q1 q1Var = new com.google.common.util.concurrent.q1();
        SubscribeCallback subscribeCallback = new SubscribeCallback(str, bundleCreateOptionsForSubscription, q1Var);
        List<SubscribeCallback> arrayList = this.subscribeCallbacks.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.subscribeCallbacks.put(str, arrayList);
        }
        arrayList.add(subscribeCallback);
        browserCompat.subscribe(str, bundleCreateOptionsForSubscription, subscribeCallback);
        return q1Var;
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<Void>> unsubscribe(String str) {
        if (!getInstance().isSessionCommandAvailable(SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE)) {
            return xc.d.s0(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return xc.d.s0(LibraryResult.ofError(-100));
        }
        List<SubscribeCallback> list = this.subscribeCallbacks.get(str);
        if (list == null) {
            return xc.d.s0(LibraryResult.ofError(-3));
        }
        for (int i10 = 0; i10 < list.size(); i10++) {
            browserCompat.unsubscribe(str, list.get(i10));
        }
        return xc.d.s0(LibraryResult.ofVoid());
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy
    public MediaBrowser getInstance() {
        return this.instance;
    }

    @Override // androidx.media3.session.MediaControllerImplLegacy, androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(final SessionCommand sessionCommand, final Bundle bundle, final MediaController.ProgressListener progressListener) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (getAvailableSessionCommands().contains(sessionCommand)) {
            return super.sendCustomCommand(sessionCommand, bundle);
        }
        if (browserCompat == null) {
            return xc.d.s0(new SessionResult(-4));
        }
        final com.google.common.util.concurrent.q1 q1Var = new com.google.common.util.concurrent.q1();
        browserCompat.sendCustomAction(sessionCommand.customAction, bundle, new MediaBrowserCompat.CustomActionCallback() { // from class: androidx.media3.session.MediaBrowserImplLegacy.4
            @Override // androidx.media3.session.legacy.MediaBrowserCompat.CustomActionCallback
            public void onError(String str, Bundle bundle2, Bundle bundle3) {
                Bundle bundle4 = new Bundle(bundle2);
                bundle4.putAll(bundle3);
                q1Var.set(new SessionResult(-1, bundle4));
            }

            @Override // androidx.media3.session.legacy.MediaBrowserCompat.CustomActionCallback
            public void onProgressUpdate(String str, Bundle bundle2, Bundle bundle3) {
                MediaController.ProgressListener progressListener2 = progressListener;
                if (progressListener2 != null) {
                    progressListener2.onProgress(MediaBrowserImplLegacy.this.getInstance(), sessionCommand, bundle, bundle3);
                }
            }

            @Override // androidx.media3.session.legacy.MediaBrowserCompat.CustomActionCallback
            public void onResult(String str, Bundle bundle2, Bundle bundle3) {
                Bundle bundle4 = new Bundle(bundle2);
                bundle4.putAll(bundle3);
                q1Var.set(new SessionResult(0, bundle4));
            }
        });
        return q1Var;
    }
}
