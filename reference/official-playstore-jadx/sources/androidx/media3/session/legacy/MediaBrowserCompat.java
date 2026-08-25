package androidx.media3.session.legacy;

import a0.c;
import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.support.v4.os.f;
import android.text.TextUtils;
import androidx.media3.common.util.Log;
import androidx.media3.session.legacy.IMediaSession;
import androidx.media3.session.legacy.MediaSessionCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import t.e;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaBrowserCompat {
    public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
    public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
    public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    static final String TAG = "MediaBrowserCompat";
    private final MediaBrowserImpl impl;

    public static class CallbackHandler extends Handler {
        private final WeakReference<MediaBrowserServiceCallbackImpl> callbackImplRef;
        private WeakReference<Messenger> callbacksMessengerRef;

        public CallbackHandler(MediaBrowserServiceCallbackImpl mediaBrowserServiceCallbackImpl) {
            this.callbackImplRef = new WeakReference<>(mediaBrowserServiceCallbackImpl);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<Messenger> weakReference = this.callbacksMessengerRef;
            if (weakReference == null) {
                return;
            }
            Messenger messenger = weakReference.get();
            MediaBrowserServiceCallbackImpl mediaBrowserServiceCallbackImpl = this.callbackImplRef.get();
            if (messenger == null || mediaBrowserServiceCallbackImpl == null) {
                return;
            }
            Bundle data = message.getData();
            MediaSessionCompat.ensureClassLoader(data);
            try {
                if (message.what != 3) {
                    Log.w(MediaBrowserCompat.TAG, "Unhandled message: " + message + "\n  Client version: 1\n  Service version: " + message.arg1);
                    return;
                }
                Bundle bundle = data.getBundle(MediaBrowserProtocol.DATA_OPTIONS);
                MediaSessionCompat.ensureClassLoader(bundle);
                Bundle bundle2 = data.getBundle(MediaBrowserProtocol.DATA_NOTIFY_CHILDREN_CHANGED_OPTIONS);
                MediaSessionCompat.ensureClassLoader(bundle2);
                mediaBrowserServiceCallbackImpl.onLoadChildren(messenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), LegacyParcelableUtil.convertList(data.getParcelableArrayList(MediaBrowserProtocol.DATA_MEDIA_ITEM_LIST), MediaItem.CREATOR), bundle, bundle2);
            } catch (BadParcelableException unused) {
                Log.e(MediaBrowserCompat.TAG, "Could not unparcel the data.");
            }
        }

        public void setCallbacksMessenger(Messenger messenger) {
            this.callbacksMessengerRef = new WeakReference<>(messenger);
        }
    }

    public static class ConnectionCallback {
        final MediaBrowser.ConnectionCallback connectionCallbackFwk = new ConnectionCallbackImpl();
        ConnectionCallbackInternal connectionCallbackInternal;

        public class ConnectionCallbackImpl extends MediaBrowser.ConnectionCallback {
            public ConnectionCallbackImpl() {
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnected() {
                ConnectionCallbackInternal connectionCallbackInternal = ConnectionCallback.this.connectionCallbackInternal;
                if (connectionCallbackInternal != null) {
                    connectionCallbackInternal.onConnected();
                }
                ConnectionCallback.this.onConnected();
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnectionFailed() {
                ConnectionCallbackInternal connectionCallbackInternal = ConnectionCallback.this.connectionCallbackInternal;
                if (connectionCallbackInternal != null) {
                    connectionCallbackInternal.onConnectionFailed();
                }
                ConnectionCallback.this.onConnectionFailed();
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnectionSuspended() {
                ConnectionCallbackInternal connectionCallbackInternal = ConnectionCallback.this.connectionCallbackInternal;
                if (connectionCallbackInternal != null) {
                    connectionCallbackInternal.onConnectionSuspended();
                }
                ConnectionCallback.this.onConnectionSuspended();
            }
        }

        public interface ConnectionCallbackInternal {
            void onConnected();

            void onConnectionFailed();

            void onConnectionSuspended();
        }

        public void onConnected() {
        }

        public void onConnectionFailed() {
        }

        public void onConnectionSuspended() {
        }

        public void setInternalConnectionCallback(ConnectionCallbackInternal connectionCallbackInternal) {
            this.connectionCallbackInternal = connectionCallbackInternal;
        }
    }

    public static abstract class CustomActionCallback {
        public void onError(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onProgressUpdate(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onResult(String str, Bundle bundle, Bundle bundle2) {
        }
    }

    public static class CustomActionResultReceiver extends f {
        private final String action;
        private final CustomActionCallback callback;
        private final Bundle extras;

        public CustomActionResultReceiver(String str, Bundle bundle, CustomActionCallback customActionCallback, Handler handler) {
            super(handler);
            this.action = str;
            this.extras = bundle;
            this.callback = customActionCallback;
        }

        @Override // android.support.v4.os.f
        public void onReceiveResult(int i10, Bundle bundle) {
            if (this.callback == null) {
                return;
            }
            MediaSessionCompat.ensureClassLoader(bundle);
            if (i10 == -1) {
                this.callback.onError(this.action, this.extras, bundle);
                return;
            }
            if (i10 == 0) {
                this.callback.onResult(this.action, this.extras, bundle);
                return;
            }
            if (i10 != 1) {
                StringBuilder sbS = c.s(i10, "Unknown result code: ", " (extras=");
                sbS.append(this.extras);
                sbS.append(", resultData=");
                sbS.append(bundle);
                sbS.append(")");
                Log.w(MediaBrowserCompat.TAG, sbS.toString());
                return;
            }
            CustomActionCallback customActionCallback = this.callback;
            String str = this.action;
            Bundle bundle2 = this.extras;
            if (bundle2 == null) {
                bundle2 = Bundle.EMPTY;
            }
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            customActionCallback.onProgressUpdate(str, bundle2, bundle);
        }
    }

    public static abstract class ItemCallback {
        final MediaBrowser.ItemCallback itemCallbackFwk = new ItemCallbackImpl();

        public class ItemCallbackImpl extends MediaBrowser.ItemCallback {
            public ItemCallbackImpl() {
            }

            @Override // android.media.browse.MediaBrowser.ItemCallback
            public void onError(String str) {
                ItemCallback.this.onError(str);
            }

            @Override // android.media.browse.MediaBrowser.ItemCallback
            public void onItemLoaded(MediaBrowser.MediaItem mediaItem) {
                ItemCallback.this.onItemLoaded(MediaItem.fromMediaItem(mediaItem));
            }
        }

        public void onError(String str) {
        }

        public void onItemLoaded(MediaItem mediaItem) {
        }
    }

    public static class ItemReceiver extends f {
        private final ItemCallback callback;
        private final String mediaId;

        public ItemReceiver(String str, ItemCallback itemCallback, Handler handler) {
            super(handler);
            this.mediaId = str;
            this.callback = itemCallback;
        }

        @Override // android.support.v4.os.f
        public void onReceiveResult(int i10, Bundle bundle) {
            if (bundle != null) {
                bundle = MediaSessionCompat.unparcelWithClassLoader(bundle);
            }
            if (i10 != 0 || bundle == null || !bundle.containsKey(MediaBrowserServiceCompat.KEY_MEDIA_ITEM)) {
                this.callback.onError(this.mediaId);
            } else {
                this.callback.onItemLoaded((MediaItem) LegacyParcelableUtil.convert(bundle.getParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM), MediaItem.CREATOR));
            }
        }
    }

    public interface MediaBrowserImpl {
        void connect();

        void disconnect();

        Bundle getExtras();

        void getItem(String str, ItemCallback itemCallback);

        Bundle getNotifyChildrenChangedOptions();

        String getRoot();

        MediaSessionCompat.Token getSessionToken();

        boolean isConnected();

        void search(String str, Bundle bundle, SearchCallback searchCallback);

        void sendCustomAction(String str, Bundle bundle, CustomActionCallback customActionCallback);

        void subscribe(String str, Bundle bundle, SubscriptionCallback subscriptionCallback);

        void unsubscribe(String str, SubscriptionCallback subscriptionCallback);
    }

    public static class MediaBrowserImplApi23 implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl, ConnectionCallback.ConnectionCallbackInternal {
        protected final MediaBrowser browserFwk;
        protected Messenger callbacksMessenger;
        final Context context;
        private MediaSessionCompat.Token mediaSessionToken;
        private Bundle notifyChildrenChangedOptions;
        protected final Bundle rootHints;
        protected ServiceBinderWrapper serviceBinderWrapper;
        protected int serviceVersion;
        protected final CallbackHandler handler = new CallbackHandler(this);
        private final e subscriptions = new e(0);

        public MediaBrowserImplApi23(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            this.context = context;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            this.rootHints = bundle2;
            bundle2.putInt(MediaBrowserProtocol.EXTRA_CLIENT_VERSION, 1);
            bundle2.putInt(MediaBrowserProtocol.EXTRA_CALLING_PID, Process.myPid());
            connectionCallback.setInternalConnectionCallback(this);
            MediaBrowser.ConnectionCallback connectionCallback2 = connectionCallback.connectionCallbackFwk;
            connectionCallback2.getClass();
            this.browserFwk = new MediaBrowser(context, componentName, connectionCallback2, bundle2);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void connect() {
            this.browserFwk.connect();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void disconnect() {
            Messenger messenger;
            ServiceBinderWrapper serviceBinderWrapper = this.serviceBinderWrapper;
            if (serviceBinderWrapper != null && (messenger = this.callbacksMessenger) != null) {
                try {
                    serviceBinderWrapper.unregisterCallbackMessenger(messenger);
                } catch (RemoteException unused) {
                    Log.i(MediaBrowserCompat.TAG, "Remote error unregistering client messenger.");
                }
            }
            this.browserFwk.disconnect();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public Bundle getExtras() {
            return this.browserFwk.getExtras();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void getItem(final String str, final ItemCallback itemCallback) {
            if (this.serviceBinderWrapper == null) {
                this.browserFwk.getItem(str, itemCallback.itemCallbackFwk);
                return;
            }
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("mediaId is empty");
            }
            if (!this.browserFwk.isConnected()) {
                Log.i(MediaBrowserCompat.TAG, "Not connected, unable to retrieve the MediaItem.");
                this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.1
                    @Override // java.lang.Runnable
                    public void run() {
                        itemCallback.onError(str);
                    }
                });
                return;
            }
            if (this.serviceBinderWrapper == null) {
                this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.2
                    @Override // java.lang.Runnable
                    public void run() {
                        itemCallback.onError(str);
                    }
                });
                return;
            }
            ItemReceiver itemReceiver = new ItemReceiver(str, itemCallback, this.handler);
            try {
                ServiceBinderWrapper serviceBinderWrapper = this.serviceBinderWrapper;
                Messenger messenger = this.callbacksMessenger;
                messenger.getClass();
                serviceBinderWrapper.getMediaItem(str, itemReceiver, messenger);
            } catch (RemoteException unused) {
                Log.i(MediaBrowserCompat.TAG, "Remote error getting media item: " + str);
                this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.3
                    @Override // java.lang.Runnable
                    public void run() {
                        itemCallback.onError(str);
                    }
                });
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public Bundle getNotifyChildrenChangedOptions() {
            return this.notifyChildrenChangedOptions;
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public String getRoot() {
            return this.browserFwk.getRoot();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public MediaSessionCompat.Token getSessionToken() {
            if (this.mediaSessionToken == null) {
                this.mediaSessionToken = MediaSessionCompat.Token.fromToken(this.browserFwk.getSessionToken());
            }
            return this.mediaSessionToken;
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public boolean isConnected() {
            return this.browserFwk.isConnected();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
        public void onConnected() {
            try {
                Bundle extras = this.browserFwk.getExtras();
                if (extras == null) {
                    return;
                }
                this.serviceVersion = extras.getInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, 0);
                IBinder binder = extras.getBinder(MediaBrowserProtocol.EXTRA_MESSENGER_BINDER);
                if (binder != null) {
                    ServiceBinderWrapper serviceBinderWrapper = new ServiceBinderWrapper(binder, this.rootHints);
                    this.serviceBinderWrapper = serviceBinderWrapper;
                    Messenger messenger = new Messenger(this.handler);
                    this.callbacksMessenger = messenger;
                    this.handler.setCallbacksMessenger(messenger);
                    try {
                        serviceBinderWrapper.registerCallbackMessenger(this.context, messenger);
                    } catch (RemoteException unused) {
                        Log.i(MediaBrowserCompat.TAG, "Remote error registering client messenger.");
                    }
                }
                IMediaSession iMediaSessionAsInterface = IMediaSession.Stub.asInterface(extras.getBinder(MediaBrowserProtocol.EXTRA_SESSION_BINDER));
                if (iMediaSessionAsInterface != null) {
                    this.mediaSessionToken = MediaSessionCompat.Token.fromToken(this.browserFwk.getSessionToken(), iMediaSessionAsInterface);
                }
            } catch (IllegalStateException e5) {
                Log.e(MediaBrowserCompat.TAG, "Unexpected IllegalStateException", e5);
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
        public void onConnectionFailed() {
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
        public void onConnectionSuspended() {
            this.serviceBinderWrapper = null;
            this.callbacksMessenger = null;
            this.mediaSessionToken = null;
            this.handler.setCallbacksMessenger(null);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserServiceCallbackImpl
        public void onLoadChildren(Messenger messenger, String str, List<MediaItem> list, Bundle bundle, Bundle bundle2) {
            if (this.callbacksMessenger != messenger) {
                return;
            }
            Subscription subscription = str == null ? null : (Subscription) this.subscriptions.get(str);
            if (subscription == null) {
                Log.d(MediaBrowserCompat.TAG, "onLoadChildren for id that isn't subscribed id=" + str);
                return;
            }
            SubscriptionCallback callback = subscription.getCallback(bundle);
            if (callback != null) {
                if (bundle == null) {
                    if (list == null) {
                        callback.onError(str);
                        return;
                    }
                    this.notifyChildrenChangedOptions = bundle2;
                    callback.onChildrenLoaded(str, list);
                    this.notifyChildrenChangedOptions = null;
                    return;
                }
                if (list == null) {
                    callback.onError(str, bundle);
                    return;
                }
                this.notifyChildrenChangedOptions = bundle2;
                callback.onChildrenLoaded(str, list, bundle);
                this.notifyChildrenChangedOptions = null;
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void search(final String str, final Bundle bundle, final SearchCallback searchCallback) {
            if (!isConnected()) {
                throw new IllegalStateException("search() called while not connected");
            }
            if (this.serviceBinderWrapper == null) {
                Log.i(MediaBrowserCompat.TAG, "The connected service doesn't support search.");
                this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.4
                    @Override // java.lang.Runnable
                    public void run() {
                        searchCallback.onError(str, bundle);
                    }
                });
                return;
            }
            SearchResultReceiver searchResultReceiver = new SearchResultReceiver(str, bundle, searchCallback, this.handler);
            try {
                ServiceBinderWrapper serviceBinderWrapper = this.serviceBinderWrapper;
                Messenger messenger = this.callbacksMessenger;
                messenger.getClass();
                serviceBinderWrapper.search(str, bundle, searchResultReceiver, messenger);
            } catch (RemoteException e5) {
                Log.i(MediaBrowserCompat.TAG, "Remote error searching items with query: " + str, e5);
                this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.5
                    @Override // java.lang.Runnable
                    public void run() {
                        searchCallback.onError(str, bundle);
                    }
                });
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void sendCustomAction(final String str, final Bundle bundle, final CustomActionCallback customActionCallback) {
            if (!isConnected()) {
                throw new IllegalStateException("Cannot send a custom action (" + str + ") with extras " + bundle + " because the browser is not connected to the service.");
            }
            ServiceBinderWrapper serviceBinderWrapper = this.serviceBinderWrapper;
            if (serviceBinderWrapper == null) {
                Log.i(MediaBrowserCompat.TAG, "The connected service doesn't support sendCustomAction.");
                if (customActionCallback != null) {
                    this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.6
                        @Override // java.lang.Runnable
                        public void run() {
                            customActionCallback.onError(str, bundle, null);
                        }
                    });
                    return;
                }
                return;
            }
            CustomActionResultReceiver customActionResultReceiver = new CustomActionResultReceiver(str, bundle, customActionCallback, this.handler);
            try {
                Messenger messenger = this.callbacksMessenger;
                messenger.getClass();
                serviceBinderWrapper.sendCustomAction(str, bundle, customActionResultReceiver, messenger);
            } catch (RemoteException e5) {
                Log.i(MediaBrowserCompat.TAG, "Remote error sending a custom action: action=" + str + ", extras=" + bundle, e5);
                if (customActionCallback != null) {
                    this.handler.post(new Runnable() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23.7
                        @Override // java.lang.Runnable
                        public void run() {
                            customActionCallback.onError(str, bundle, null);
                        }
                    });
                }
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void subscribe(String str, Bundle bundle, SubscriptionCallback subscriptionCallback) {
            Subscription subscription = (Subscription) this.subscriptions.get(str);
            if (subscription == null) {
                subscription = new Subscription();
                this.subscriptions.put(str, subscription);
            }
            subscriptionCallback.setSubscription(subscription);
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            subscription.putCallback(bundle2, subscriptionCallback);
            ServiceBinderWrapper serviceBinderWrapper = this.serviceBinderWrapper;
            if (serviceBinderWrapper == null) {
                this.browserFwk.subscribe(str, subscriptionCallback.subscriptionCallbackFwk);
                return;
            }
            try {
                IBinder iBinder = subscriptionCallback.token;
                Messenger messenger = this.callbacksMessenger;
                messenger.getClass();
                serviceBinderWrapper.addSubscription(str, iBinder, bundle2, messenger);
            } catch (RemoteException unused) {
                Log.i(MediaBrowserCompat.TAG, "Remote error subscribing media item: " + str);
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void unsubscribe(String str, SubscriptionCallback subscriptionCallback) {
            Subscription subscription = (Subscription) this.subscriptions.get(str);
            if (subscription == null) {
                return;
            }
            ServiceBinderWrapper serviceBinderWrapper = this.serviceBinderWrapper;
            if (serviceBinderWrapper != null) {
                try {
                    if (subscriptionCallback == null) {
                        Messenger messenger = this.callbacksMessenger;
                        messenger.getClass();
                        serviceBinderWrapper.removeSubscription(str, null, messenger);
                    } else {
                        List<SubscriptionCallback> callbacks = subscription.getCallbacks();
                        List<Bundle> optionsList = subscription.getOptionsList();
                        for (int size = callbacks.size() - 1; size >= 0; size--) {
                            if (callbacks.get(size) == subscriptionCallback) {
                                IBinder iBinder = subscriptionCallback.token;
                                Messenger messenger2 = this.callbacksMessenger;
                                messenger2.getClass();
                                serviceBinderWrapper.removeSubscription(str, iBinder, messenger2);
                                callbacks.remove(size);
                                optionsList.remove(size);
                            }
                        }
                    }
                } catch (RemoteException unused) {
                    Log.d(MediaBrowserCompat.TAG, "removeSubscription failed with RemoteException parentId=" + str);
                }
            } else if (subscriptionCallback == null) {
                this.browserFwk.unsubscribe(str);
            } else {
                List<SubscriptionCallback> callbacks2 = subscription.getCallbacks();
                List<Bundle> optionsList2 = subscription.getOptionsList();
                for (int size2 = callbacks2.size() - 1; size2 >= 0; size2--) {
                    if (callbacks2.get(size2) == subscriptionCallback) {
                        callbacks2.remove(size2);
                        optionsList2.remove(size2);
                    }
                }
                if (callbacks2.isEmpty()) {
                    this.browserFwk.unsubscribe(str);
                }
            }
            if (subscription.isEmpty() || subscriptionCallback == null) {
                this.subscriptions.remove(str);
            }
        }
    }

    public static class MediaBrowserImplApi26 extends MediaBrowserImplApi23 {
        public MediaBrowserImplApi26(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23, androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void subscribe(String str, Bundle bundle, SubscriptionCallback subscriptionCallback) {
            if (this.serviceBinderWrapper != null && this.serviceVersion >= 2) {
                super.subscribe(str, bundle, subscriptionCallback);
            } else if (bundle == null) {
                this.browserFwk.subscribe(str, subscriptionCallback.subscriptionCallbackFwk);
            } else {
                this.browserFwk.subscribe(str, bundle, subscriptionCallback.subscriptionCallbackFwk);
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImplApi23, androidx.media3.session.legacy.MediaBrowserCompat.MediaBrowserImpl
        public void unsubscribe(String str, SubscriptionCallback subscriptionCallback) {
            if (this.serviceBinderWrapper != null && this.serviceVersion >= 2) {
                super.unsubscribe(str, subscriptionCallback);
            } else if (subscriptionCallback == null) {
                this.browserFwk.unsubscribe(str);
            } else {
                this.browserFwk.unsubscribe(str, subscriptionCallback.subscriptionCallbackFwk);
            }
        }
    }

    public interface MediaBrowserServiceCallbackImpl {
        void onLoadChildren(Messenger messenger, String str, List<MediaItem> list, Bundle bundle, Bundle bundle2);
    }

    public static abstract class SearchCallback {
        public void onError(String str, Bundle bundle) {
        }

        public void onSearchResult(String str, Bundle bundle, List<MediaItem> list) {
        }
    }

    public static class SearchResultReceiver extends f {
        private final SearchCallback callback;
        private final Bundle extras;
        private final String query;

        public SearchResultReceiver(String str, Bundle bundle, SearchCallback searchCallback, Handler handler) {
            super(handler);
            this.query = str;
            this.extras = bundle;
            this.callback = searchCallback;
        }

        @Override // android.support.v4.os.f
        public void onReceiveResult(int i10, Bundle bundle) {
            if (bundle != null) {
                bundle = MediaSessionCompat.unparcelWithClassLoader(bundle);
            }
            if (i10 != 0 || bundle == null || !bundle.containsKey(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS)) {
                this.callback.onError(this.query, this.extras);
                return;
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS);
            if (parcelableArray == null) {
                this.callback.onError(this.query, this.extras);
                return;
            }
            ArrayList arrayList = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                arrayList.add((MediaItem) LegacyParcelableUtil.convert(parcelable, MediaItem.CREATOR));
            }
            this.callback.onSearchResult(this.query, this.extras, arrayList);
        }
    }

    public static class ServiceBinderWrapper {
        private final Messenger messenger;
        private final Bundle rootHints;

        public ServiceBinderWrapper(IBinder iBinder, Bundle bundle) {
            this.messenger = new Messenger(iBinder);
            this.rootHints = bundle;
        }

        private void sendRequest(int i10, Bundle bundle, Messenger messenger) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i10;
            messageObtain.arg1 = 1;
            if (bundle != null) {
                messageObtain.setData(bundle);
            }
            messageObtain.replyTo = messenger;
            this.messenger.send(messageObtain);
        }

        public void addSubscription(String str, IBinder iBinder, Bundle bundle, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle2.putBinder(MediaBrowserProtocol.DATA_CALLBACK_TOKEN, iBinder);
            bundle2.putBundle(MediaBrowserProtocol.DATA_OPTIONS, bundle);
            sendRequest(3, bundle2, messenger);
        }

        public void getMediaItem(String str, f fVar, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, LegacyParcelableUtil.convert(fVar, f.CREATOR));
            sendRequest(5, bundle, messenger);
        }

        public void registerCallbackMessenger(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            bundle.putInt(MediaBrowserProtocol.DATA_CALLING_PID, Process.myPid());
            bundle.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, this.rootHints);
            sendRequest(6, bundle, messenger);
        }

        public void removeSubscription(String str, IBinder iBinder, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle.putBinder(MediaBrowserProtocol.DATA_CALLBACK_TOKEN, iBinder);
            sendRequest(4, bundle, messenger);
        }

        public void search(String str, Bundle bundle, f fVar, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_SEARCH_QUERY, str);
            bundle2.putBundle(MediaBrowserProtocol.DATA_SEARCH_EXTRAS, bundle);
            bundle2.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, LegacyParcelableUtil.convert(fVar, f.CREATOR));
            sendRequest(8, bundle2, messenger);
        }

        public void sendCustomAction(String str, Bundle bundle, f fVar, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_CUSTOM_ACTION, str);
            bundle2.putBundle(MediaBrowserProtocol.DATA_CUSTOM_ACTION_EXTRAS, bundle);
            bundle2.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, LegacyParcelableUtil.convert(fVar, f.CREATOR));
            sendRequest(9, bundle2, messenger);
        }

        public void unregisterCallbackMessenger(Messenger messenger) throws RemoteException {
            sendRequest(7, null, messenger);
        }
    }

    public static class Subscription {
        private final List<SubscriptionCallback> callbacks = new ArrayList();
        private final List<Bundle> optionsList = new ArrayList();

        public SubscriptionCallback getCallback(Bundle bundle) {
            for (int i10 = 0; i10 < this.optionsList.size(); i10++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.optionsList.get(i10), bundle)) {
                    return this.callbacks.get(i10);
                }
            }
            return null;
        }

        public List<SubscriptionCallback> getCallbacks() {
            return this.callbacks;
        }

        public List<Bundle> getOptionsList() {
            return this.optionsList;
        }

        public boolean isEmpty() {
            return this.callbacks.isEmpty();
        }

        public void putCallback(Bundle bundle, SubscriptionCallback subscriptionCallback) {
            for (int i10 = 0; i10 < this.optionsList.size(); i10++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.optionsList.get(i10), bundle)) {
                    this.callbacks.set(i10, subscriptionCallback);
                    return;
                }
            }
            this.callbacks.add(subscriptionCallback);
            this.optionsList.add(bundle);
        }
    }

    public static abstract class SubscriptionCallback {
        final MediaBrowser.SubscriptionCallback subscriptionCallbackFwk;
        WeakReference<Subscription> subscriptionRef;
        final IBinder token = new Binder();

        public class SubscriptionCallbackApi23 extends MediaBrowser.SubscriptionCallback {
            public SubscriptionCallbackApi23() {
            }

            public List<MediaItem> applyOptions(List<MediaItem> list, Bundle bundle) {
                int i10 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
                int i11 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
                if (i10 == -1 && i11 == -1) {
                    return list;
                }
                int i12 = i11 * i10;
                int size = i12 + i11;
                if (i10 < 0 || i11 < 1 || i12 >= list.size()) {
                    return Collections.EMPTY_LIST;
                }
                if (size > list.size()) {
                    size = list.size();
                }
                return list.subList(i12, size);
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list) {
                WeakReference<Subscription> weakReference = SubscriptionCallback.this.subscriptionRef;
                Subscription subscription = weakReference == null ? null : weakReference.get();
                if (subscription == null) {
                    SubscriptionCallback.this.onChildrenLoaded(str, MediaItem.fromMediaItemList(list));
                    return;
                }
                List<MediaItem> listFromMediaItemList = MediaItem.fromMediaItemList(list);
                listFromMediaItemList.getClass();
                List<SubscriptionCallback> callbacks = subscription.getCallbacks();
                List<Bundle> optionsList = subscription.getOptionsList();
                for (int i10 = 0; i10 < callbacks.size(); i10++) {
                    Bundle bundle = optionsList.get(i10);
                    if (bundle == null) {
                        SubscriptionCallback.this.onChildrenLoaded(str, listFromMediaItemList);
                    } else {
                        SubscriptionCallback.this.onChildrenLoaded(str, applyOptions(listFromMediaItemList, bundle), bundle);
                    }
                }
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onError(String str) {
                SubscriptionCallback.this.onError(str);
            }
        }

        public class SubscriptionCallbackApi26 extends SubscriptionCallbackApi23 {
            public SubscriptionCallbackApi26() {
                super();
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list, Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                SubscriptionCallback.this.onChildrenLoaded(str, MediaItem.fromMediaItemList(list), bundle);
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onError(String str, Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                SubscriptionCallback.this.onError(str, bundle);
            }
        }

        public SubscriptionCallback() {
            if (Build.VERSION.SDK_INT >= 26) {
                this.subscriptionCallbackFwk = new SubscriptionCallbackApi26();
            } else {
                this.subscriptionCallbackFwk = new SubscriptionCallbackApi23();
            }
        }

        public void onChildrenLoaded(String str, List<MediaItem> list) {
        }

        public void onError(String str) {
        }

        public void setSubscription(Subscription subscription) {
            this.subscriptionRef = new WeakReference<>(subscription);
        }

        public void onChildrenLoaded(String str, List<MediaItem> list, Bundle bundle) {
        }

        public void onError(String str, Bundle bundle) {
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.impl = new MediaBrowserImplApi26(context, componentName, connectionCallback, bundle);
        } else {
            this.impl = new MediaBrowserImplApi23(context, componentName, connectionCallback, bundle);
        }
    }

    public void connect() {
        Log.d(TAG, "Connecting to a MediaBrowserService.");
        this.impl.connect();
    }

    public void disconnect() {
        this.impl.disconnect();
    }

    public Bundle getExtras() {
        return this.impl.getExtras();
    }

    public void getItem(String str, ItemCallback itemCallback) {
        this.impl.getItem(str, itemCallback);
    }

    public Bundle getNotifyChildrenChangedOptions() {
        return this.impl.getNotifyChildrenChangedOptions();
    }

    public String getRoot() {
        return this.impl.getRoot();
    }

    public MediaSessionCompat.Token getSessionToken() {
        return this.impl.getSessionToken();
    }

    public boolean isConnected() {
        return this.impl.isConnected();
    }

    public void search(String str, Bundle bundle, SearchCallback searchCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query cannot be empty");
        }
        this.impl.search(str, bundle, searchCallback);
    }

    public void sendCustomAction(String str, Bundle bundle, CustomActionCallback customActionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("action cannot be empty");
        }
        this.impl.sendCustomAction(str, bundle, customActionCallback);
    }

    public void subscribe(String str, Bundle bundle, SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        this.impl.subscribe(str, bundle, subscriptionCallback);
    }

    public void unsubscribe(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        this.impl.unsubscribe(str, null);
    }

    public void unsubscribe(String str, SubscriptionCallback subscriptionCallback) {
        if (!TextUtils.isEmpty(str)) {
            this.impl.unsubscribe(str, subscriptionCallback);
            return;
        }
        throw new IllegalArgumentException("parentId is empty");
    }

    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new Parcelable.Creator<MediaItem>() { // from class: androidx.media3.session.legacy.MediaBrowserCompat.MediaItem.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MediaItem[] newArray(int i10) {
                return new MediaItem[i10];
            }
        };
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat description;
        private final int flags;

        public MediaItem(MediaDescriptionCompat mediaDescriptionCompat, int i10) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty(mediaDescriptionCompat.getMediaId())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.flags = i10;
            this.description = mediaDescriptionCompat;
        }

        public static MediaItem fromMediaItem(MediaBrowser.MediaItem mediaItem) {
            if (mediaItem == null) {
                return null;
            }
            return new MediaItem(MediaDescriptionCompat.fromMediaDescription(mediaItem.getDescription()), mediaItem.getFlags());
        }

        public static List<MediaItem> fromMediaItemList(List<MediaBrowser.MediaItem> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<MediaBrowser.MediaItem> it = list.iterator();
            while (it.hasNext()) {
                MediaItem mediaItemFromMediaItem = fromMediaItem(it.next());
                if (mediaItemFromMediaItem != null) {
                    arrayList.add(mediaItemFromMediaItem);
                }
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public MediaDescriptionCompat getDescription() {
            return this.description;
        }

        public int getFlags() {
            return this.flags;
        }

        public String getMediaId() {
            return this.description.getMediaId();
        }

        public boolean isBrowsable() {
            return (this.flags & 1) != 0;
        }

        public boolean isPlayable() {
            return (this.flags & 2) != 0;
        }

        public String toString() {
            return "MediaItem{mFlags=" + this.flags + ", mDescription=" + this.description + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i10) {
            parcel.writeInt(this.flags);
            this.description.writeToParcel(parcel, i10);
        }

        public MediaItem(Parcel parcel) {
            this.flags = parcel.readInt();
            this.description = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }
    }
}
