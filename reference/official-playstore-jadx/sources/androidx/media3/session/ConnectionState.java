package androidx.media3.session;

import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import androidx.media3.common.Player;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import androidx.media3.session.IMediaSession;
import com.google.common.collect.o3;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
class ConnectionState {
    public final com.google.common.collect.h1 commandButtonsForMediaItems;
    public final com.google.common.collect.h1 customLayout;
    public final int libraryVersion;
    public final com.google.common.collect.h1 mediaButtonPreferences;
    public final MediaSession.Token platformToken;
    public final Player.Commands playerCommandsFromPlayer;
    public final Player.Commands playerCommandsFromSession;
    public final PlayerInfo playerInfo;
    public final PendingIntent sessionActivity;
    public final IMediaSession sessionBinder;
    public final SessionCommands sessionCommands;
    public final Bundle sessionExtras;
    public final int sessionInterfaceVersion;
    public final Bundle tokenExtras;
    private static final String FIELD_LIBRARY_VERSION = Util.intToStringMaxRadix(0);
    private static final String FIELD_SESSION_BINDER = Util.intToStringMaxRadix(1);
    private static final String FIELD_SESSION_ACTIVITY = Util.intToStringMaxRadix(2);
    private static final String FIELD_CUSTOM_LAYOUT = Util.intToStringMaxRadix(9);
    private static final String FIELD_MEDIA_BUTTON_PREFERENCES = Util.intToStringMaxRadix(14);
    private static final String FIELD_COMMAND_BUTTONS_FOR_MEDIA_ITEMS = Util.intToStringMaxRadix(13);
    private static final String FIELD_SESSION_COMMANDS = Util.intToStringMaxRadix(3);
    private static final String FIELD_PLAYER_COMMANDS_FROM_SESSION = Util.intToStringMaxRadix(4);
    private static final String FIELD_PLAYER_COMMANDS_FROM_PLAYER = Util.intToStringMaxRadix(5);
    private static final String FIELD_TOKEN_EXTRAS = Util.intToStringMaxRadix(6);
    private static final String FIELD_SESSION_EXTRAS = Util.intToStringMaxRadix(11);
    private static final String FIELD_PLAYER_INFO = Util.intToStringMaxRadix(7);
    private static final String FIELD_SESSION_INTERFACE_VERSION = Util.intToStringMaxRadix(8);
    private static final String FIELD_IN_PROCESS_BINDER = Util.intToStringMaxRadix(10);
    private static final String FIELD_PLATFORM_TOKEN = Util.intToStringMaxRadix(12);

    public final class InProcessBinder extends Binder {
        private InProcessBinder() {
        }

        public ConnectionState getConnectionState() {
            return ConnectionState.this;
        }
    }

    public ConnectionState(int i10, int i11, IMediaSession iMediaSession, PendingIntent pendingIntent, com.google.common.collect.h1 h1Var, com.google.common.collect.h1 h1Var2, com.google.common.collect.h1 h1Var3, SessionCommands sessionCommands, Player.Commands commands, Player.Commands commands2, Bundle bundle, Bundle bundle2, PlayerInfo playerInfo, MediaSession.Token token) {
        this.libraryVersion = i10;
        this.sessionInterfaceVersion = i11;
        this.sessionBinder = iMediaSession;
        this.sessionActivity = pendingIntent;
        this.customLayout = h1Var;
        this.mediaButtonPreferences = h1Var2;
        this.commandButtonsForMediaItems = h1Var3;
        this.sessionCommands = sessionCommands;
        this.playerCommandsFromSession = commands;
        this.playerCommandsFromPlayer = commands2;
        this.tokenExtras = bundle;
        this.sessionExtras = bundle2;
        this.playerInfo = playerInfo;
        this.platformToken = token;
    }

    public static ConnectionState fromBundle(Bundle bundle) {
        com.google.common.collect.h1 h1VarFromBundleList;
        com.google.common.collect.h1 h1VarFromBundleList2;
        com.google.common.collect.h1 h1VarFromBundleList3;
        IBinder binder = bundle.getBinder(FIELD_IN_PROCESS_BINDER);
        if (binder instanceof InProcessBinder) {
            return ((InProcessBinder) binder).getConnectionState();
        }
        int i10 = 0;
        int i11 = bundle.getInt(FIELD_LIBRARY_VERSION, 0);
        int i12 = bundle.getInt(FIELD_SESSION_INTERFACE_VERSION, 0);
        IBinder binder2 = bundle.getBinder(FIELD_SESSION_BINDER);
        binder2.getClass();
        IBinder iBinder = binder2;
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable(FIELD_SESSION_ACTIVITY);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_CUSTOM_LAYOUT);
        if (parcelableArrayList != null) {
            h1VarFromBundleList = BundleCollectionUtil.fromBundleList(new e(i12, i10), parcelableArrayList);
        } else {
            com.google.common.collect.f1 f1Var = com.google.common.collect.h1.f14020l;
            h1VarFromBundleList = o3.f14078o;
        }
        com.google.common.collect.h1 h1Var = h1VarFromBundleList;
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(FIELD_MEDIA_BUTTON_PREFERENCES);
        if (parcelableArrayList2 != null) {
            h1VarFromBundleList2 = BundleCollectionUtil.fromBundleList(new e(i12, 1), parcelableArrayList2);
        } else {
            com.google.common.collect.f1 f1Var2 = com.google.common.collect.h1.f14020l;
            h1VarFromBundleList2 = o3.f14078o;
        }
        com.google.common.collect.h1 h1Var2 = h1VarFromBundleList2;
        ArrayList parcelableArrayList3 = bundle.getParcelableArrayList(FIELD_COMMAND_BUTTONS_FOR_MEDIA_ITEMS);
        if (parcelableArrayList3 != null) {
            h1VarFromBundleList3 = BundleCollectionUtil.fromBundleList(new e(i12, 2), parcelableArrayList3);
        } else {
            com.google.common.collect.f1 f1Var3 = com.google.common.collect.h1.f14020l;
            h1VarFromBundleList3 = o3.f14078o;
        }
        com.google.common.collect.h1 h1Var3 = h1VarFromBundleList3;
        Bundle bundle2 = bundle.getBundle(FIELD_SESSION_COMMANDS);
        SessionCommands sessionCommandsFromBundle = bundle2 == null ? SessionCommands.EMPTY : SessionCommands.fromBundle(bundle2);
        Bundle bundle3 = bundle.getBundle(FIELD_PLAYER_COMMANDS_FROM_PLAYER);
        Player.Commands commandsFromBundle = bundle3 == null ? Player.Commands.EMPTY : Player.Commands.fromBundle(bundle3);
        Bundle bundle4 = bundle.getBundle(FIELD_PLAYER_COMMANDS_FROM_SESSION);
        Player.Commands commandsFromBundle2 = bundle4 == null ? Player.Commands.EMPTY : Player.Commands.fromBundle(bundle4);
        Bundle bundle5 = bundle.getBundle(FIELD_TOKEN_EXTRAS);
        Bundle bundle6 = bundle.getBundle(FIELD_SESSION_EXTRAS);
        Bundle bundle7 = bundle.getBundle(FIELD_PLAYER_INFO);
        PlayerInfo playerInfoFromBundle = bundle7 == null ? PlayerInfo.DEFAULT : PlayerInfo.fromBundle(bundle7, i12);
        MediaSession.Token token = (MediaSession.Token) bundle.getParcelable(FIELD_PLATFORM_TOKEN);
        Bundle bundle8 = bundle6;
        IMediaSession iMediaSessionAsInterface = IMediaSession.Stub.asInterface(iBinder);
        if (bundle5 == null) {
            bundle5 = Bundle.EMPTY;
        }
        Bundle bundle9 = bundle5;
        if (bundle8 == null) {
            bundle8 = Bundle.EMPTY;
        }
        return new ConnectionState(i11, i12, iMediaSessionAsInterface, pendingIntent, h1Var, h1Var2, h1Var3, sessionCommandsFromBundle, commandsFromBundle2, commandsFromBundle, bundle9, bundle8, playerInfoFromBundle, token);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ CommandButton lambda$fromBundle$0(int i10, Bundle bundle) {
        return CommandButton.fromBundle(bundle, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ CommandButton lambda$fromBundle$1(int i10, Bundle bundle) {
        return CommandButton.fromBundle(bundle, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ CommandButton lambda$fromBundle$2(int i10, Bundle bundle) {
        return CommandButton.fromBundle(bundle, i10);
    }

    public Bundle toBundleForRemoteProcess(int i10) {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_LIBRARY_VERSION, this.libraryVersion);
        bundle.putBinder(FIELD_SESSION_BINDER, this.sessionBinder.asBinder());
        bundle.putParcelable(FIELD_SESSION_ACTIVITY, this.sessionActivity);
        if (!this.customLayout.isEmpty()) {
            bundle.putParcelableArrayList(FIELD_CUSTOM_LAYOUT, BundleCollectionUtil.toBundleArrayList(this.customLayout, new f(0)));
        }
        if (!this.mediaButtonPreferences.isEmpty()) {
            if (i10 >= 7) {
                bundle.putParcelableArrayList(FIELD_MEDIA_BUTTON_PREFERENCES, BundleCollectionUtil.toBundleArrayList(this.mediaButtonPreferences, new f(0)));
            } else {
                bundle.putParcelableArrayList(FIELD_CUSTOM_LAYOUT, BundleCollectionUtil.toBundleArrayList(CommandButton.getCustomLayoutFromMediaButtonPreferences(this.mediaButtonPreferences, true, true), new f(0)));
            }
        }
        if (!this.commandButtonsForMediaItems.isEmpty()) {
            bundle.putParcelableArrayList(FIELD_COMMAND_BUTTONS_FOR_MEDIA_ITEMS, BundleCollectionUtil.toBundleArrayList(this.commandButtonsForMediaItems, new f(0)));
        }
        bundle.putBundle(FIELD_SESSION_COMMANDS, this.sessionCommands.toBundle());
        bundle.putBundle(FIELD_PLAYER_COMMANDS_FROM_SESSION, this.playerCommandsFromSession.toBundle());
        bundle.putBundle(FIELD_PLAYER_COMMANDS_FROM_PLAYER, this.playerCommandsFromPlayer.toBundle());
        bundle.putBundle(FIELD_TOKEN_EXTRAS, this.tokenExtras);
        bundle.putBundle(FIELD_SESSION_EXTRAS, this.sessionExtras);
        bundle.putBundle(FIELD_PLAYER_INFO, this.playerInfo.filterByAvailableCommands(MediaUtils.intersect(this.playerCommandsFromSession, this.playerCommandsFromPlayer), false, false).toBundleForRemoteProcess(i10));
        bundle.putInt(FIELD_SESSION_INTERFACE_VERSION, this.sessionInterfaceVersion);
        MediaSession.Token token = this.platformToken;
        if (token != null) {
            bundle.putParcelable(FIELD_PLATFORM_TOKEN, token);
        }
        return bundle;
    }

    public Bundle toBundleInProcess() {
        Bundle bundle = new Bundle();
        bundle.putBinder(FIELD_IN_PROCESS_BINDER, new InProcessBinder());
        return bundle;
    }
}
