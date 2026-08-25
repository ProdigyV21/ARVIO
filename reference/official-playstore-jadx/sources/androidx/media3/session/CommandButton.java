package androidx.media3.session;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.util.Util;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class CommandButton {
    private static final String CUSTOM_COMMAND_PARAMETER_EXTRAS_KEY = "androidx.media3.session.CUSTOM_COMMAND_PARAMETER";
    private static final String CUSTOM_COMMAND_PLAYER_COMMAND_PREFIX = "androidx.media3.session.PLAYER_COMMAND_";
    private static final String CUSTOM_COMMAND_SESSION_COMMAND_PREFIX = "androidx.media3.session.SESSION_COMMAND_";
    public static final int ICON_ALBUM = 57369;
    public static final int ICON_ARTIST = 57370;
    public static final int ICON_BLOCK = 57675;
    public static final int ICON_BOOKMARK_FILLED = 1042534;
    public static final int ICON_BOOKMARK_UNFILLED = 59494;
    public static final int ICON_CHECK_CIRCLE_FILLED = 1042540;
    public static final int ICON_CHECK_CIRCLE_UNFILLED = 59500;
    public static final int ICON_CLOSED_CAPTIONS = 57372;
    public static final int ICON_CLOSED_CAPTIONS_OFF = 61916;
    public static final int ICON_FAST_FORWARD = 57375;
    public static final int ICON_FEED = 57573;
    public static final int ICON_FLAG_FILLED = 1040723;
    public static final int ICON_FLAG_UNFILLED = 57683;
    public static final int ICON_HEART_FILLED = 1042557;
    public static final int ICON_HEART_UNFILLED = 59517;
    public static final int ICON_MINUS = 57691;
    public static final int ICON_MINUS_CIRCLE_FILLED = 1040712;
    public static final int ICON_MINUS_CIRCLE_UNFILLED = 1040713;
    public static final int ICON_NEXT = 57412;
    public static final int ICON_PAUSE = 57396;
    public static final int ICON_PLAY = 57399;
    public static final int ICON_PLAYBACK_SPEED = 57448;
    public static final int ICON_PLAYBACK_SPEED_0_5 = 62690;
    public static final int ICON_PLAYBACK_SPEED_0_8 = 1045730;
    public static final int ICON_PLAYBACK_SPEED_1_0 = 61389;
    public static final int ICON_PLAYBACK_SPEED_1_2 = 62689;
    public static final int ICON_PLAYBACK_SPEED_1_5 = 62688;
    public static final int ICON_PLAYBACK_SPEED_1_8 = 1045728;
    public static final int ICON_PLAYBACK_SPEED_2_0 = 62699;
    public static final int ICON_PLAYLIST_ADD = 57403;
    public static final int ICON_PLAYLIST_REMOVE = 60288;
    public static final int ICON_PLUS = 57669;
    public static final int ICON_PLUS_CIRCLE_FILLED = 1040711;
    public static final int ICON_PLUS_CIRCLE_UNFILLED = 57671;
    public static final int ICON_PREVIOUS = 57413;
    public static final int ICON_QUALITY = 58409;
    public static final int ICON_QUEUE_ADD = 57436;
    public static final int ICON_QUEUE_NEXT = 57446;
    public static final int ICON_QUEUE_REMOVE = 57447;
    public static final int ICON_RADIO = 58654;
    public static final int ICON_REPEAT_ALL = 57408;
    public static final int ICON_REPEAT_OFF = 1040448;
    public static final int ICON_REPEAT_ONE = 57409;
    public static final int ICON_REWIND = 57376;
    public static final int ICON_SETTINGS = 59576;
    public static final int ICON_SHARE = 59405;
    public static final int ICON_SHUFFLE_OFF = 1040452;
    public static final int ICON_SHUFFLE_ON = 57411;
    public static final int ICON_SHUFFLE_STAR = 1040451;
    public static final int ICON_SIGNAL = 61512;
    public static final int ICON_SKIP_BACK = 57410;
    public static final int ICON_SKIP_BACK_10 = 57433;
    public static final int ICON_SKIP_BACK_15 = 1040473;
    public static final int ICON_SKIP_BACK_30 = 57434;
    public static final int ICON_SKIP_BACK_5 = 57435;
    public static final int ICON_SKIP_FORWARD = 63220;
    public static final int ICON_SKIP_FORWARD_10 = 57430;
    public static final int ICON_SKIP_FORWARD_15 = 1040470;
    public static final int ICON_SKIP_FORWARD_30 = 57431;
    public static final int ICON_SKIP_FORWARD_5 = 57432;
    public static final int ICON_STAR_FILLED = 1042488;
    public static final int ICON_STAR_UNFILLED = 59448;
    public static final int ICON_STOP = 57415;
    public static final int ICON_SUBTITLES = 57416;
    public static final int ICON_SUBTITLES_OFF = 61298;
    public static final int ICON_SYNC = 58919;
    public static final int ICON_THUMB_DOWN_FILLED = 1042651;
    public static final int ICON_THUMB_DOWN_UNFILLED = 59611;
    public static final int ICON_THUMB_UP_FILLED = 1042652;
    public static final int ICON_THUMB_UP_UNFILLED = 59612;
    public static final int ICON_UNDEFINED = 0;
    public static final int ICON_VOLUME_DOWN = 57421;
    public static final int ICON_VOLUME_OFF = 57423;
    public static final int ICON_VOLUME_UP = 57424;
    private static final String INCORRECT_PARAMETER_TYPE_MESSAGE = "Parameter has incorrect type.";
    private static final int PARAMETER_TYPE_BOOLEAN = 3;
    private static final int PARAMETER_TYPE_FLOAT = 4;
    private static final int PARAMETER_TYPE_INT = 2;
    private static final int PARAMETER_TYPE_LONG = 1;
    private static final int PARAMETER_TYPE_MEDIA_ITEM = 6;
    private static final int PARAMETER_TYPE_MEDIA_METADATA = 7;
    private static final int PARAMETER_TYPE_NULL = 0;
    private static final int PARAMETER_TYPE_RATING = 5;
    private static final int PARAMETER_TYPE_TRACK_SELECTION_PARAMETERS = 8;
    public static final int SLOT_BACK = 2;
    public static final int SLOT_BACK_SECONDARY = 4;
    public static final int SLOT_CENTRAL = 1;
    public static final int SLOT_FORWARD = 3;
    public static final int SLOT_FORWARD_SECONDARY = 5;
    public static final int SLOT_OVERFLOW = 6;
    public final CharSequence displayName;
    public final Bundle extras;
    public final int icon;
    public final int iconResId;
    public final Uri iconUri;
    public final boolean isEnabled;
    public final Object parameter;
    public final int playerCommand;
    public final SessionCommand sessionCommand;
    public final com.google.common.primitives.b slots;
    private static final String FIELD_SESSION_COMMAND = Util.intToStringMaxRadix(0);
    private static final String FIELD_PLAYER_COMMAND = Util.intToStringMaxRadix(1);
    private static final String FIELD_ICON_RES_ID = Util.intToStringMaxRadix(2);
    private static final String FIELD_DISPLAY_NAME = Util.intToStringMaxRadix(3);
    private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(4);
    private static final String FIELD_ENABLED = Util.intToStringMaxRadix(5);
    private static final String FIELD_ICON_URI = Util.intToStringMaxRadix(6);
    private static final String FIELD_ICON = Util.intToStringMaxRadix(7);
    private static final String FIELD_SLOTS = Util.intToStringMaxRadix(8);
    private static final String FIELD_PARAMETER = Util.intToStringMaxRadix(9);

    public static final class Builder {
        private CharSequence displayName;
        private boolean enabled;
        private Bundle extras;
        private final int icon;
        private int iconResId;
        private Uri iconUri;
        private Object parameter;
        private int playerCommand;
        private SessionCommand sessionCommand;
        private com.google.common.primitives.b slots;

        @Deprecated
        public Builder() {
            this(0);
        }

        public CommandButton build() {
            ac.b.t((this.sessionCommand == null) != (this.playerCommand == -1), "Exactly one of sessionCommand and playerCommand should be set");
            if (this.slots == null) {
                this.slots = new com.google.common.primitives.b(new int[]{CommandButton.getDefaultSlot(this.playerCommand, this.icon)});
            }
            return new CommandButton(this.sessionCommand, this.playerCommand, this.icon, this.iconResId, this.iconUri, this.displayName, this.extras, this.enabled, this.slots, this.parameter);
        }

        public Builder setCustomIconResId(int i10) {
            this.iconResId = i10;
            return this;
        }

        public Builder setDisplayName(CharSequence charSequence) {
            this.displayName = charSequence;
            return this;
        }

        public Builder setEnabled(boolean z) {
            this.enabled = z;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.extras = new Bundle(bundle);
            return this;
        }

        @Deprecated
        public Builder setIconResId(int i10) {
            return setCustomIconResId(i10);
        }

        public Builder setIconUri(Uri uri) {
            ac.b.k(Objects.equals(uri.getScheme(), "content") || Objects.equals(uri.getScheme(), "android.resource"), "Only content or resource Uris are supported for CommandButton");
            this.iconUri = uri;
            return this;
        }

        public Builder setPlayerCommand(int i10) {
            ac.b.k(this.sessionCommand == null, "sessionCommand is already set. Only one of sessionCommand and playerCommand should be set.");
            this.playerCommand = i10;
            this.parameter = null;
            return this;
        }

        public Builder setSessionCommand(SessionCommand sessionCommand) {
            ac.b.p(sessionCommand, "sessionCommand should not be null.");
            ac.b.k(this.playerCommand == -1, "playerCommands is already set. Only one of sessionCommand and playerCommand should be set.");
            this.sessionCommand = sessionCommand;
            this.parameter = null;
            return this;
        }

        public Builder setSlots(int... iArr) {
            ac.b.j(iArr.length != 0);
            com.google.common.primitives.b bVar = com.google.common.primitives.b.f14177m;
            this.slots = iArr.length == 0 ? com.google.common.primitives.b.f14177m : new com.google.common.primitives.b(Arrays.copyOf(iArr, iArr.length));
            return this;
        }

        public Builder(int i10) {
            this(i10, CommandButton.getIconResIdForIconConstant(i10));
        }

        public Builder(int i10, int i11) {
            this.icon = i10;
            this.iconResId = i11;
            this.displayName = "";
            this.extras = Bundle.EMPTY;
            this.playerCommand = -1;
            this.enabled = true;
        }

        public Builder setPlayerCommand(int i10, Object obj) {
            ac.b.k(this.sessionCommand == null, "sessionCommand is already set. Only one of sessionCommand and playerCommand should be set.");
            this.playerCommand = i10;
            this.parameter = CommandButton.verifyParameterType(obj, CommandButton.getParameterTypeForPlayerCommand(i10));
            return this;
        }

        public Builder setSessionCommand(SessionCommand sessionCommand, Object obj) {
            ac.b.p(sessionCommand, "sessionCommand should not be null.");
            ac.b.k(this.playerCommand == -1, "playerCommands is already set. Only one of sessionCommand and playerCommand should be set.");
            this.sessionCommand = sessionCommand;
            this.parameter = CommandButton.verifyParameterType(obj, CommandButton.getParameterTypeForSessionCommand(sessionCommand.commandCode));
            return this;
        }
    }

    public static final class DisplayConstraints {
        private final SparseArray<Player.Commands> allowedPlayerCommandsPerSlot;
        private final SparseArray<SessionCommands> allowedSessionCommandsPerSlot;
        private final SparseBooleanArray areCustomCommandsAllowedPerSlot;
        private final SparseIntArray maxButtonsPerSlot;

        public static final class Builder {
            private final SparseArray<Player.Commands> allowedPlayerCommandsPerSlot;
            private final SparseArray<SessionCommands> allowedSessionCommandsPerSlot;
            private final SparseBooleanArray areCustomCommandsAllowedPerSlot;
            private boolean buildCalled;
            private final SparseIntArray maxButtonsPerSlot;

            public Builder() {
                SparseIntArray sparseIntArray = new SparseIntArray();
                this.maxButtonsPerSlot = sparseIntArray;
                sparseIntArray.put(1, 1);
                sparseIntArray.put(2, 1);
                sparseIntArray.put(3, 1);
                sparseIntArray.put(6, Integer.MAX_VALUE);
                this.allowedPlayerCommandsPerSlot = new SparseArray<>();
                this.allowedSessionCommandsPerSlot = new SparseArray<>();
                this.areCustomCommandsAllowedPerSlot = new SparseBooleanArray();
            }

            public DisplayConstraints build() {
                ac.b.s(!this.buildCalled);
                this.buildCalled = true;
                return new DisplayConstraints(this);
            }

            public Builder setAllowCustomCommandsForSlot(int i10, boolean z) {
                this.areCustomCommandsAllowedPerSlot.put(i10, z);
                return this;
            }

            public Builder setAllowedPlayerCommandsForSlot(int i10, Player.Commands commands) {
                this.allowedPlayerCommandsPerSlot.put(i10, commands);
                return this;
            }

            public Builder setAllowedSessionCommandsForSlot(int i10, SessionCommands sessionCommands) {
                this.allowedSessionCommandsPerSlot.put(i10, sessionCommands);
                return this;
            }

            public Builder setMaxButtonsForSlot(int i10, int i11) {
                ac.b.j(i11 >= 0);
                this.maxButtonsPerSlot.put(i10, i11);
                return this;
            }
        }

        private static CommandButton createButton(int i10, int i11, Player.Commands commands) {
            return new Builder(i10).setPlayerCommand(i11).setEnabled(commands.contains(i11)).build();
        }

        private static CommandButton createOppositeButton(CommandButton commandButton, int i10, Player player) {
            Player.Commands availableCommands = player.getAvailableCommands();
            int oppositePlayerCommand = getOppositePlayerCommand(commandButton, i10, availableCommands);
            int oppositeIcon = getOppositeIcon(commandButton);
            if (oppositeIcon == 0) {
                oppositeIcon = getIconForPlayerCommand(oppositePlayerCommand, player);
            }
            return createButton(oppositeIcon, oppositePlayerCommand, availableCommands);
        }

        private static int getFirstAvailableOrFirstCommand(Player.Commands commands, int... iArr) {
            for (int i10 : iArr) {
                if (commands.contains(i10)) {
                    return i10;
                }
            }
            return iArr[0];
        }

        private static int getIconForPlayerCommand(int i10, Player player) {
            switch (i10) {
                case 6:
                case 7:
                    return CommandButton.ICON_PREVIOUS;
                case 8:
                case 9:
                    return CommandButton.ICON_NEXT;
                case 10:
                default:
                    throw new UnsupportedOperationException();
                case 11:
                    long seekBackIncrement = player.getSeekBackIncrement();
                    return (seekBackIncrement < 2500 || seekBackIncrement >= 7500) ? (seekBackIncrement < 7500 || seekBackIncrement >= 12500) ? (seekBackIncrement < 12500 || seekBackIncrement >= 20000) ? (seekBackIncrement < 20000 || seekBackIncrement >= 40000) ? CommandButton.ICON_SKIP_BACK : CommandButton.ICON_SKIP_BACK_30 : CommandButton.ICON_SKIP_BACK_15 : CommandButton.ICON_SKIP_BACK_10 : CommandButton.ICON_SKIP_BACK_5;
                case 12:
                    long seekForwardIncrement = player.getSeekForwardIncrement();
                    return (seekForwardIncrement < 2500 || seekForwardIncrement >= 7500) ? (seekForwardIncrement < 7500 || seekForwardIncrement >= 12500) ? (seekForwardIncrement < 12500 || seekForwardIncrement >= 20000) ? (seekForwardIncrement < 20000 || seekForwardIncrement >= 40000) ? CommandButton.ICON_SKIP_FORWARD : CommandButton.ICON_SKIP_FORWARD_30 : CommandButton.ICON_SKIP_FORWARD_15 : CommandButton.ICON_SKIP_FORWARD_10 : CommandButton.ICON_SKIP_FORWARD_5;
            }
        }

        private static int getOppositeIcon(CommandButton commandButton) {
            if (commandButton == null) {
                return 0;
            }
            switch (commandButton.icon) {
                case CommandButton.ICON_FAST_FORWARD /* 57375 */:
                    break;
                case CommandButton.ICON_REWIND /* 57376 */:
                    break;
                case CommandButton.ICON_SKIP_BACK /* 57410 */:
                    break;
                case CommandButton.ICON_NEXT /* 57412 */:
                    break;
                case CommandButton.ICON_PREVIOUS /* 57413 */:
                    break;
                case CommandButton.ICON_SKIP_FORWARD /* 63220 */:
                    break;
            }
            return 0;
        }

        private static int getOppositePlayerCommand(CommandButton commandButton, int i10, Player.Commands commands) {
            if (commandButton != null) {
                switch (commandButton.playerCommand) {
                    case 6:
                        return 8;
                    case 7:
                        return 9;
                    case 8:
                        return 6;
                    case 9:
                        return 7;
                    case 11:
                        return 12;
                    case 12:
                        return 11;
                }
            }
            return i10 == 2 ? getFirstAvailableOrFirstCommand(commands, 7, 6, 11) : getFirstAvailableOrFirstCommand(commands, 9, 8, 12);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0020  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private boolean reserveSlotForButton(androidx.media3.session.CommandButton r5, int r6, android.util.SparseIntArray r7) {
            /*
                r4 = this;
                int r0 = r7.get(r6)
                r1 = 0
                if (r0 != 0) goto L8
                return r1
            L8:
                int r0 = r5.playerCommand
                r2 = -1
                r3 = 1
                if (r0 == r2) goto L22
                android.util.SparseArray<androidx.media3.common.Player$Commands> r0 = r4.allowedPlayerCommandsPerSlot
                java.lang.Object r0 = r0.get(r6)
                androidx.media3.common.Player$Commands r0 = (androidx.media3.common.Player.Commands) r0
                if (r0 == 0) goto L20
                int r5 = r5.playerCommand
                boolean r5 = r0.contains(r5)
                if (r5 == 0) goto L45
            L20:
                r1 = r3
                goto L45
            L22:
                androidx.media3.session.SessionCommand r0 = r5.sessionCommand
                r0.getClass()
                int r0 = r0.commandCode
                if (r0 != 0) goto L32
                android.util.SparseBooleanArray r5 = r4.areCustomCommandsAllowedPerSlot
                boolean r1 = r5.get(r6, r3)
                goto L45
            L32:
                android.util.SparseArray<androidx.media3.session.SessionCommands> r0 = r4.allowedSessionCommandsPerSlot
                java.lang.Object r0 = r0.get(r6)
                androidx.media3.session.SessionCommands r0 = (androidx.media3.session.SessionCommands) r0
                if (r0 == 0) goto L20
                androidx.media3.session.SessionCommand r5 = r5.sessionCommand
                boolean r5 = r0.contains(r5)
                if (r5 == 0) goto L45
                goto L20
            L45:
                if (r1 == 0) goto L4f
                int r5 = r7.get(r6)
                int r5 = r5 - r3
                r7.put(r6, r5)
            L4f:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.CommandButton.DisplayConstraints.reserveSlotForButton(androidx.media3.session.CommandButton, int, android.util.SparseIntArray):boolean");
        }

        public com.google.common.collect.h1 resolve(List<CommandButton> list, Player player) {
            SparseIntArray sparseIntArrayClone = this.maxButtonsPerSlot.clone();
            com.google.common.collect.e1 e1VarK = com.google.common.collect.h1.k();
            CommandButton commandButton = null;
            CommandButton commandButton2 = null;
            int i10 = 0;
            while (true) {
                if (i10 >= list.size()) {
                    break;
                }
                CommandButton commandButton3 = list.get(i10);
                int i11 = 0;
                while (true) {
                    com.google.common.primitives.b bVar = commandButton3.slots;
                    if (i11 < bVar.f14179l) {
                        int iA = bVar.a(i11);
                        if (reserveSlotForButton(commandButton3, iA, sparseIntArrayClone)) {
                            e1VarK.c(commandButton3.copyWithSlots(new com.google.common.primitives.b(new int[]{iA})));
                            if (commandButton == null && iA == 3) {
                                commandButton = commandButton3;
                            } else if (commandButton2 == null && iA == 2) {
                                commandButton2 = commandButton3;
                            }
                        } else {
                            i11++;
                        }
                    }
                }
                i10++;
            }
            Player.Commands availableCommands = player.getAvailableCommands();
            if (this.maxButtonsPerSlot.get(1) == sparseIntArrayClone.get(1)) {
                CommandButton commandButtonCreateButton = createButton(Util.shouldShowPlayButton(player) ? CommandButton.ICON_PLAY : CommandButton.ICON_PAUSE, 1, availableCommands);
                if (reserveSlotForButton(commandButtonCreateButton, 1, sparseIntArrayClone)) {
                    e1VarK.c(commandButtonCreateButton);
                }
            }
            boolean z = commandButton2 == null && this.maxButtonsPerSlot.get(2) > 0;
            boolean z5 = commandButton == null && this.maxButtonsPerSlot.get(3) > 0;
            if (z && z5) {
                int firstAvailableOrFirstCommand = getFirstAvailableOrFirstCommand(availableCommands, 7, 9, 6, 8, 11, 12);
                CommandButton commandButtonCreateButton2 = createButton(getIconForPlayerCommand(firstAvailableOrFirstCommand, player), firstAvailableOrFirstCommand, availableCommands);
                int iA2 = commandButtonCreateButton2.slots.a(0);
                if (reserveSlotForButton(commandButtonCreateButton2, iA2, sparseIntArrayClone)) {
                    e1VarK.c(commandButtonCreateButton2);
                }
                int i12 = iA2 != 2 ? 2 : 3;
                CommandButton commandButtonCreateOppositeButton = createOppositeButton(commandButtonCreateButton2, i12, player);
                if (reserveSlotForButton(commandButtonCreateOppositeButton, i12, sparseIntArrayClone)) {
                    e1VarK.c(commandButtonCreateOppositeButton);
                }
            } else if (z) {
                CommandButton commandButtonCreateOppositeButton2 = createOppositeButton(commandButton, 2, player);
                if (reserveSlotForButton(commandButtonCreateOppositeButton2, 2, sparseIntArrayClone)) {
                    e1VarK.c(commandButtonCreateOppositeButton2);
                }
            } else if (z5) {
                CommandButton commandButtonCreateOppositeButton3 = createOppositeButton(commandButton2, 3, player);
                if (reserveSlotForButton(commandButtonCreateOppositeButton3, 3, sparseIntArrayClone)) {
                    e1VarK.c(commandButtonCreateOppositeButton3);
                }
            }
            return e1VarK.f();
        }

        private DisplayConstraints(Builder builder) {
            this.maxButtonsPerSlot = builder.maxButtonsPerSlot;
            this.allowedPlayerCommandsPerSlot = builder.allowedPlayerCommandsPerSlot;
            this.allowedSessionCommandsPerSlot = builder.allowedSessionCommandsPerSlot;
            this.areCustomCommandsAllowedPerSlot = builder.areCustomCommandsAllowedPerSlot;
        }
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Icon {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Slot {
    }

    public static boolean containsButtonForSlot(List<CommandButton> list, int i10) {
        for (int i11 = 0; i11 < list.size(); i11++) {
            if (list.get(i11).slots.a(0) == i10) {
                return true;
            }
        }
        return false;
    }

    public static CommandButton convertFromPredefinedCustomCommand(SessionCommand sessionCommand) {
        if (isPredefinedPlayerCustomCommandButtonCode(sessionCommand.customAction)) {
            int predefinedCustomCommandCode = getPredefinedCustomCommandCode(sessionCommand.customAction, CUSTOM_COMMAND_PLAYER_COMMAND_PREFIX);
            return new Builder(0).setPlayerCommand(predefinedCustomCommandCode, getParameterFromBundle(sessionCommand.customExtras, CUSTOM_COMMAND_PARAMETER_EXTRAS_KEY, getParameterTypeForPlayerCommand(predefinedCustomCommandCode))).build();
        }
        int predefinedCustomCommandCode2 = getPredefinedCustomCommandCode(sessionCommand.customAction, CUSTOM_COMMAND_SESSION_COMMAND_PREFIX);
        return new Builder(0).setSessionCommand(new SessionCommand(predefinedCustomCommandCode2), getParameterFromBundle(sessionCommand.customExtras, CUSTOM_COMMAND_PARAMETER_EXTRAS_KEY, getParameterTypeForSessionCommand(predefinedCustomCommandCode2))).build();
    }

    private CommandButton convertToPredefinedCustomCommandButton(int i10) {
        String str;
        SessionCommand sessionCommand = this.sessionCommand;
        if (sessionCommand != null && sessionCommand.commandCode == 0) {
            return copyWithSlots(new com.google.common.primitives.b(new int[]{i10}));
        }
        Bundle bundle = Bundle.EMPTY;
        if (this.parameter != null) {
            bundle = new Bundle();
            writeParameterToBundle(bundle, CUSTOM_COMMAND_PARAMETER_EXTRAS_KEY);
        }
        if (this.sessionCommand != null) {
            str = CUSTOM_COMMAND_SESSION_COMMAND_PREFIX + this.sessionCommand.commandCode;
        } else {
            str = CUSTOM_COMMAND_PLAYER_COMMAND_PREFIX + this.playerCommand;
        }
        return new CommandButton(new SessionCommand(str, bundle), -1, this.icon, this.iconResId, this.iconUri, this.displayName, this.extras, this.isEnabled, new com.google.common.primitives.b(new int[]{i10}), null);
    }

    public static com.google.common.collect.h1 copyWithUnavailableButtonsDisabled(List<CommandButton> list, SessionCommands sessionCommands, Player.Commands commands) {
        com.google.common.collect.e1 e1Var = new com.google.common.collect.e1(4);
        for (int i10 = 0; i10 < list.size(); i10++) {
            CommandButton commandButton = list.get(i10);
            if (isButtonCommandAvailable(commandButton, sessionCommands, commands)) {
                e1Var.c(commandButton);
            } else {
                e1Var.c(commandButton.copyWithIsEnabled(false));
            }
        }
        return e1Var.f();
    }

    @Deprecated
    public static CommandButton fromBundle(Bundle bundle) {
        return fromBundle(bundle, 8);
    }

    public static com.google.common.collect.h1 getCustomLayoutFromMediaButtonPreferences(List<CommandButton> list, boolean z, boolean z5) {
        int iA;
        if (list.isEmpty()) {
            com.google.common.collect.f1 f1Var = com.google.common.collect.h1.f14020l;
            return o3.f14078o;
        }
        int i10 = -1;
        int i11 = -1;
        for (int i12 = 0; i12 < list.size(); i12++) {
            CommandButton commandButton = list.get(i12);
            if (commandButton.isEnabled && commandButton.canExecuteAction()) {
                int i13 = 0;
                while (true) {
                    com.google.common.primitives.b bVar = commandButton.slots;
                    if (i13 >= bVar.f14179l || (iA = bVar.a(i13)) == 6) {
                        break;
                    }
                    if (z && i10 == -1 && iA == 2) {
                        i10 = i12;
                        break;
                    }
                    if (z5 && i11 == -1 && iA == 3) {
                        i11 = i12;
                        break;
                    }
                    i13++;
                }
            }
        }
        com.google.common.collect.e1 e1VarK = com.google.common.collect.h1.k();
        if (i10 != -1) {
            e1VarK.c(list.get(i10).convertToPredefinedCustomCommandButton(2));
        }
        if (i11 != -1) {
            e1VarK.c(list.get(i11).convertToPredefinedCustomCommandButton(3));
        }
        for (int i14 = 0; i14 < list.size(); i14++) {
            CommandButton commandButton2 = list.get(i14);
            if (commandButton2.isEnabled && commandButton2.canExecuteAction() && i14 != i10 && i14 != i11) {
                com.google.common.primitives.b bVar2 = commandButton2.slots;
                bVar2.getClass();
                int i15 = 0;
                while (true) {
                    if (i15 >= bVar2.f14179l) {
                        i15 = -1;
                        break;
                    }
                    if (bVar2.f14178i[i15] == 6) {
                        break;
                    }
                    i15++;
                }
                if (i15 >= 0) {
                    e1VarK.c(commandButton2.convertToPredefinedCustomCommandButton(6));
                }
            }
        }
        return e1VarK.f();
    }

    public static int getDefaultSlot(int i10, int i11) {
        if (i10 == 1 || i11 == 57399 || i11 == 57396) {
            return 1;
        }
        if (i10 == 11 || i10 == 7 || i10 == 6 || i11 == 57413 || i11 == 57376 || i11 == 57410 || i11 == 57435 || i11 == 57433 || i11 == 1040473 || i11 == 57434) {
            return 2;
        }
        return (i10 == 12 || i10 == 9 || i10 == 8 || i11 == 57412 || i11 == 57375 || i11 == 63220 || i11 == 57432 || i11 == 57430 || i11 == 1040470 || i11 == 57431) ? 3 : 6;
    }

    public static int getIconResIdForIconConstant(int i10) {
        switch (i10) {
            case ICON_ALBUM /* 57369 */:
                return R.drawable.media3_icon_album;
            case ICON_ARTIST /* 57370 */:
                return R.drawable.media3_icon_artist;
            case ICON_CLOSED_CAPTIONS /* 57372 */:
                return R.drawable.media3_icon_closed_captions;
            case ICON_FAST_FORWARD /* 57375 */:
                return R.drawable.media3_icon_fast_forward;
            case ICON_REWIND /* 57376 */:
                return R.drawable.media3_icon_rewind;
            case ICON_PAUSE /* 57396 */:
                return R.drawable.media3_icon_pause;
            case ICON_PLAY /* 57399 */:
                return R.drawable.media3_icon_play;
            case ICON_PLAYLIST_ADD /* 57403 */:
                return R.drawable.media3_icon_playlist_add;
            case ICON_REPEAT_ALL /* 57408 */:
                return R.drawable.media3_icon_repeat_all;
            case ICON_REPEAT_ONE /* 57409 */:
                return R.drawable.media3_icon_repeat_one;
            case ICON_SKIP_BACK /* 57410 */:
                return R.drawable.media3_icon_skip_back;
            case ICON_SHUFFLE_ON /* 57411 */:
                return R.drawable.media3_icon_shuffle_on;
            case ICON_NEXT /* 57412 */:
                return R.drawable.media3_icon_next;
            case ICON_PREVIOUS /* 57413 */:
                return R.drawable.media3_icon_previous;
            case ICON_STOP /* 57415 */:
                return R.drawable.media3_icon_stop;
            case ICON_SUBTITLES /* 57416 */:
                return R.drawable.media3_icon_subtitles;
            case ICON_VOLUME_DOWN /* 57421 */:
                return R.drawable.media3_icon_volume_down;
            case ICON_VOLUME_OFF /* 57423 */:
                return R.drawable.media3_icon_volume_off;
            case ICON_VOLUME_UP /* 57424 */:
                return R.drawable.media3_icon_volume_up;
            case ICON_SKIP_FORWARD_10 /* 57430 */:
                return R.drawable.media3_icon_skip_forward_10;
            case ICON_SKIP_FORWARD_30 /* 57431 */:
                return R.drawable.media3_icon_skip_forward_30;
            case ICON_SKIP_FORWARD_5 /* 57432 */:
                return R.drawable.media3_icon_skip_forward_5;
            case ICON_SKIP_BACK_10 /* 57433 */:
                return R.drawable.media3_icon_skip_back_10;
            case ICON_SKIP_BACK_30 /* 57434 */:
                return R.drawable.media3_icon_skip_back_30;
            case ICON_SKIP_BACK_5 /* 57435 */:
                return R.drawable.media3_icon_skip_back_5;
            case ICON_QUEUE_ADD /* 57436 */:
                return R.drawable.media3_icon_queue_add;
            case ICON_QUEUE_NEXT /* 57446 */:
                return R.drawable.media3_icon_queue_next;
            case ICON_QUEUE_REMOVE /* 57447 */:
                return R.drawable.media3_icon_queue_remove;
            case ICON_PLAYBACK_SPEED /* 57448 */:
                return R.drawable.media3_icon_playback_speed;
            case ICON_FEED /* 57573 */:
                return R.drawable.media3_icon_feed;
            case ICON_PLUS /* 57669 */:
                return R.drawable.media3_icon_plus;
            case ICON_PLUS_CIRCLE_UNFILLED /* 57671 */:
                return R.drawable.media3_icon_plus_circle_unfilled;
            case ICON_BLOCK /* 57675 */:
                return R.drawable.media3_icon_block;
            case ICON_FLAG_UNFILLED /* 57683 */:
                return R.drawable.media3_icon_flag_unfilled;
            case ICON_MINUS /* 57691 */:
                return R.drawable.media3_icon_minus;
            case ICON_QUALITY /* 58409 */:
                return R.drawable.media3_icon_quality;
            case ICON_RADIO /* 58654 */:
                return R.drawable.media3_icon_radio;
            case ICON_SYNC /* 58919 */:
                return R.drawable.media3_icon_sync;
            case ICON_SHARE /* 59405 */:
                return R.drawable.media3_icon_share;
            case ICON_STAR_UNFILLED /* 59448 */:
                return R.drawable.media3_icon_star_unfilled;
            case ICON_BOOKMARK_UNFILLED /* 59494 */:
                return R.drawable.media3_icon_bookmark_unfilled;
            case ICON_CHECK_CIRCLE_UNFILLED /* 59500 */:
                return R.drawable.media3_icon_check_circle_unfilled;
            case ICON_HEART_UNFILLED /* 59517 */:
                return R.drawable.media3_icon_heart_unfilled;
            case ICON_SETTINGS /* 59576 */:
                return R.drawable.media3_icon_settings;
            case ICON_THUMB_DOWN_UNFILLED /* 59611 */:
                return R.drawable.media3_icon_thumb_down_unfilled;
            case ICON_THUMB_UP_UNFILLED /* 59612 */:
                return R.drawable.media3_icon_thumb_up_unfilled;
            case ICON_PLAYLIST_REMOVE /* 60288 */:
                return R.drawable.media3_icon_playlist_remove;
            case ICON_SUBTITLES_OFF /* 61298 */:
                return R.drawable.media3_icon_subtitles_off;
            case ICON_PLAYBACK_SPEED_1_0 /* 61389 */:
                return R.drawable.media3_icon_playback_speed_1_0;
            case ICON_SIGNAL /* 61512 */:
                return R.drawable.media3_icon_signal;
            case ICON_CLOSED_CAPTIONS_OFF /* 61916 */:
                return R.drawable.media3_icon_closed_captions_off;
            case ICON_PLAYBACK_SPEED_1_5 /* 62688 */:
                return R.drawable.media3_icon_playback_speed_1_5;
            case ICON_PLAYBACK_SPEED_1_2 /* 62689 */:
                return R.drawable.media3_icon_playback_speed_1_2;
            case ICON_PLAYBACK_SPEED_0_5 /* 62690 */:
                return R.drawable.media3_icon_playback_speed_0_5;
            case ICON_PLAYBACK_SPEED_2_0 /* 62699 */:
                return R.drawable.media3_icon_playback_speed_2_0;
            case ICON_SKIP_FORWARD /* 63220 */:
                return R.drawable.media3_icon_skip_forward;
            case ICON_REPEAT_OFF /* 1040448 */:
                return R.drawable.media3_icon_repeat_off;
            case ICON_SHUFFLE_STAR /* 1040451 */:
                return R.drawable.media3_icon_shuffle_star;
            case ICON_SHUFFLE_OFF /* 1040452 */:
                return R.drawable.media3_icon_shuffle_off;
            case ICON_SKIP_FORWARD_15 /* 1040470 */:
                return R.drawable.media3_icon_skip_forward_15;
            case ICON_SKIP_BACK_15 /* 1040473 */:
                return R.drawable.media3_icon_skip_back_15;
            case ICON_PLUS_CIRCLE_FILLED /* 1040711 */:
                return R.drawable.media3_icon_plus_circle_filled;
            case ICON_MINUS_CIRCLE_FILLED /* 1040712 */:
                return R.drawable.media3_icon_minus_circle_filled;
            case ICON_MINUS_CIRCLE_UNFILLED /* 1040713 */:
                return R.drawable.media3_icon_minus_circle_unfilled;
            case ICON_FLAG_FILLED /* 1040723 */:
                return R.drawable.media3_icon_flag_filled;
            case ICON_STAR_FILLED /* 1042488 */:
                return R.drawable.media3_icon_star_filled;
            case ICON_BOOKMARK_FILLED /* 1042534 */:
                return R.drawable.media3_icon_bookmark_filled;
            case ICON_CHECK_CIRCLE_FILLED /* 1042540 */:
                return R.drawable.media3_icon_check_circle_filled;
            case ICON_HEART_FILLED /* 1042557 */:
                return R.drawable.media3_icon_heart_filled;
            case ICON_THUMB_DOWN_FILLED /* 1042651 */:
                return R.drawable.media3_icon_thumb_down_filled;
            case ICON_THUMB_UP_FILLED /* 1042652 */:
                return R.drawable.media3_icon_thumb_up_filled;
            case ICON_PLAYBACK_SPEED_1_8 /* 1045728 */:
                return R.drawable.media3_icon_playback_speed_1_8;
            case ICON_PLAYBACK_SPEED_0_8 /* 1045730 */:
                return R.drawable.media3_icon_playback_speed_0_8;
            default:
                return 0;
        }
    }

    public static com.google.common.collect.h1 getMediaButtonPreferencesFromCustomLayout(List<CommandButton> list, Player.Commands commands, Bundle bundle) {
        if (list.isEmpty()) {
            com.google.common.collect.f1 f1Var = com.google.common.collect.h1.f14020l;
            return o3.f14078o;
        }
        boolean zContainsAny = commands.containsAny(7, 6);
        boolean zContainsAny2 = commands.containsAny(9, 8);
        boolean z = bundle.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false);
        boolean z5 = bundle.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false);
        int i10 = (zContainsAny || z) ? -1 : 0;
        int i11 = (zContainsAny2 || z5) ? -1 : i10 == 0 ? 1 : 0;
        com.google.common.collect.e1 e1VarK = com.google.common.collect.h1.k();
        for (int i12 = 0; i12 < list.size(); i12++) {
            CommandButton commandButton = list.get(i12);
            if (i12 == i10) {
                if (i11 == -1) {
                    e1VarK.c(commandButton.copyWithSlots(new com.google.common.primitives.b(new int[]{2, 6})));
                } else {
                    e1VarK.c(commandButton.copyWithSlots(new com.google.common.primitives.b(new int[]{2, 3, 6})));
                }
            } else if (i12 == i11) {
                e1VarK.c(commandButton.copyWithSlots(new com.google.common.primitives.b(new int[]{3, 6})));
            } else {
                e1VarK.c(commandButton.copyWithSlots(new com.google.common.primitives.b(new int[]{6})));
            }
        }
        return e1VarK.f();
    }

    private static Object getParameterFromBundle(Bundle bundle, String str, int i10) {
        if (!bundle.containsKey(str)) {
            return null;
        }
        switch (i10) {
            case 5:
                Bundle bundle2 = bundle.getBundle(str);
                bundle2.getClass();
                break;
            case 6:
                Bundle bundle3 = bundle.getBundle(str);
                bundle3.getClass();
                break;
            case 7:
                Bundle bundle4 = bundle.getBundle(str);
                bundle4.getClass();
                break;
            case 8:
                Bundle bundle5 = bundle.getBundle(str);
                bundle5.getClass();
                break;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getParameterTypeForPlayerCommand(int i10) {
        if (i10 == 1) {
            return 3;
        }
        if (i10 == 5) {
            return 1;
        }
        if (i10 == 10) {
            return 2;
        }
        if (i10 == 19) {
            return 7;
        }
        if (i10 == 24) {
            return 4;
        }
        if (i10 == 29) {
            return 8;
        }
        if (i10 == 31) {
            return 6;
        }
        switch (i10) {
            case 13:
                return 4;
            case 14:
                return 3;
            case 15:
                return 2;
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getParameterTypeForSessionCommand(int i10) {
        return i10 == 40010 ? 5 : 0;
    }

    private static int getPredefinedCustomCommandCode(String str, String str2) {
        return Integer.parseInt(str.substring(str2.length()));
    }

    public static boolean isButtonCommandAvailable(CommandButton commandButton, SessionCommands sessionCommands, Player.Commands commands) {
        SessionCommand sessionCommand = commandButton.sessionCommand;
        if (sessionCommand != null && sessionCommands.contains(sessionCommand)) {
            return true;
        }
        int i10 = commandButton.playerCommand;
        return i10 != -1 && commands.contains(i10);
    }

    public static boolean isPredefinedCustomCommandButtonCode(String str) {
        return isPredefinedPlayerCustomCommandButtonCode(str) || isPredefinedSessionCustomCommandButtonCode(str);
    }

    private static boolean isPredefinedPlayerCustomCommandButtonCode(String str) {
        return str.startsWith(CUSTOM_COMMAND_PLAYER_COMMAND_PREFIX);
    }

    private static boolean isPredefinedSessionCustomCommandButtonCode(String str) {
        return str.startsWith(CUSTOM_COMMAND_SESSION_COMMAND_PREFIX);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object verifyParameterType(Object obj, int i10) {
        if (obj == null) {
            return null;
        }
        switch (i10) {
            case 1:
                if (obj instanceof Integer) {
                    obj = Long.valueOf(((Integer) obj).longValue());
                }
                ac.b.k(obj instanceof Long, INCORRECT_PARAMETER_TYPE_MESSAGE);
                break;
            case 2:
                ac.b.k(obj instanceof Integer, INCORRECT_PARAMETER_TYPE_MESSAGE);
                break;
            case 3:
                ac.b.k(obj instanceof Boolean, INCORRECT_PARAMETER_TYPE_MESSAGE);
                break;
            case 4:
                if (obj instanceof Double) {
                    obj = Float.valueOf(((Double) obj).floatValue());
                }
                ac.b.k(obj instanceof Float, INCORRECT_PARAMETER_TYPE_MESSAGE);
                break;
            case 5:
                ac.b.k(obj instanceof Rating, INCORRECT_PARAMETER_TYPE_MESSAGE);
                break;
            case 6:
                ac.b.k(obj instanceof MediaItem, INCORRECT_PARAMETER_TYPE_MESSAGE);
                break;
            case 7:
                ac.b.k(obj instanceof MediaMetadata, INCORRECT_PARAMETER_TYPE_MESSAGE);
                break;
            case 8:
                ac.b.k(obj instanceof TrackSelectionParameters, INCORRECT_PARAMETER_TYPE_MESSAGE);
                break;
        }
        return null;
    }

    @RequiresNonNull({"parameter"})
    private void writeParameterToBundle(Bundle bundle, String str) {
        SessionCommand sessionCommand = this.sessionCommand;
        switch (sessionCommand != null ? getParameterTypeForSessionCommand(sessionCommand.commandCode) : getParameterTypeForPlayerCommand(this.playerCommand)) {
            case 1:
                bundle.putLong(str, ((Long) this.parameter).longValue());
                break;
            case 2:
                bundle.putInt(str, ((Integer) this.parameter).intValue());
                break;
            case 3:
                bundle.putBoolean(str, ((Boolean) this.parameter).booleanValue());
                break;
            case 4:
                bundle.putFloat(str, ((Float) this.parameter).floatValue());
                break;
            case 5:
                bundle.putBundle(str, ((Rating) this.parameter).toBundle());
                break;
            case 6:
                bundle.putBundle(str, ((MediaItem) this.parameter).toBundle());
                break;
            case 7:
                bundle.putBundle(str, ((MediaMetadata) this.parameter).toBundle());
                break;
            case 8:
                bundle.putBundle(str, ((TrackSelectionParameters) this.parameter).toBundle());
                break;
        }
    }

    public boolean canExecuteAction() {
        SessionCommand sessionCommand = this.sessionCommand;
        if (sessionCommand != null) {
            int i10 = sessionCommand.commandCode;
            if (i10 != 0) {
                return i10 == 40010 && this.parameter != null;
            }
            return true;
        }
        int i11 = this.playerCommand;
        if (i11 != 19) {
            if (i11 != 24) {
                if (i11 != 29 && i11 != 31) {
                    switch (i11) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 11:
                        case 12:
                        case 14:
                            break;
                        case 5:
                        case 10:
                        case 13:
                        case 15:
                            break;
                        default:
                            return false;
                    }
                }
            }
            return true;
        }
        return this.parameter != null;
    }

    @x4.b
    public CommandButton copyWithIsEnabled(boolean z) {
        return this.isEnabled == z ? this : new CommandButton(this.sessionCommand, this.playerCommand, this.icon, this.iconResId, this.iconUri, this.displayName, new Bundle(this.extras), z, this.slots, this.parameter);
    }

    @x4.b
    public CommandButton copyWithSlots(com.google.common.primitives.b bVar) {
        return this.slots.equals(bVar) ? this : new CommandButton(this.sessionCommand, this.playerCommand, this.icon, this.iconResId, this.iconUri, this.displayName, new Bundle(this.extras), this.isEnabled, bVar, this.parameter);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommandButton)) {
            return false;
        }
        CommandButton commandButton = (CommandButton) obj;
        return Objects.equals(this.sessionCommand, commandButton.sessionCommand) && this.playerCommand == commandButton.playerCommand && this.icon == commandButton.icon && this.iconResId == commandButton.iconResId && Objects.equals(this.iconUri, commandButton.iconUri) && TextUtils.equals(this.displayName, commandButton.displayName) && this.isEnabled == commandButton.isEnabled && this.slots.equals(commandButton.slots) && Objects.equals(this.parameter, commandButton.parameter);
    }

    public void executeAction(MediaController mediaController) {
        Object obj;
        if (this.isEnabled) {
            SessionCommand sessionCommand = this.sessionCommand;
            if (sessionCommand == null) {
                executePlayerAction(mediaController);
                return;
            }
            int i10 = sessionCommand.commandCode;
            if (i10 == 0) {
                sessionCommand.getClass();
                mediaController.sendCustomCommand(sessionCommand, this.extras);
            } else if (i10 == 40010 && (obj = this.parameter) != null) {
                mediaController.setRating((Rating) obj);
            }
        }
    }

    public void executePlayerAction(Player player) {
        if (this.isEnabled) {
            int i10 = this.playerCommand;
            if (i10 == 19) {
                Object obj = this.parameter;
                if (obj != null) {
                    player.setPlaylistMetadata((MediaMetadata) obj);
                    return;
                }
                return;
            }
            if (i10 == 24) {
                Object obj2 = this.parameter;
                if (obj2 != null) {
                    player.setVolume(((Float) obj2).floatValue());
                    return;
                } else if (player.getVolume() == 0.0f) {
                    player.unmute();
                    return;
                } else {
                    player.mute();
                    return;
                }
            }
            if (i10 == 29) {
                Object obj3 = this.parameter;
                if (obj3 != null) {
                    player.setTrackSelectionParameters((TrackSelectionParameters) obj3);
                    return;
                }
                return;
            }
            if (i10 == 31) {
                Object obj4 = this.parameter;
                if (obj4 != null) {
                    player.setMediaItem((MediaItem) obj4);
                    return;
                }
                return;
            }
            switch (i10) {
                case 1:
                    Object obj5 = this.parameter;
                    if (obj5 == null) {
                        player.setPlayWhenReady(!player.getPlayWhenReady());
                    } else {
                        player.setPlayWhenReady(((Boolean) obj5).booleanValue());
                    }
                    break;
                case 2:
                    player.prepare();
                    break;
                case 3:
                    player.stop();
                    break;
                case 4:
                    player.seekToDefaultPosition();
                    break;
                case 5:
                    Object obj6 = this.parameter;
                    if (obj6 != null) {
                        player.seekTo(((Long) obj6).longValue());
                    }
                    break;
                case 6:
                    player.seekToPreviousMediaItem();
                    break;
                case 7:
                    player.seekToPrevious();
                    break;
                case 8:
                    player.seekToNextMediaItem();
                    break;
                case 9:
                    player.seekToNext();
                    break;
                case 10:
                    Object obj7 = this.parameter;
                    if (obj7 != null) {
                        player.seekToDefaultPosition(((Integer) obj7).intValue());
                    }
                    break;
                case 11:
                    player.seekBack();
                    break;
                case 12:
                    player.seekForward();
                    break;
                case 13:
                    Object obj8 = this.parameter;
                    if (obj8 != null) {
                        player.setPlaybackSpeed(((Float) obj8).floatValue());
                    }
                    break;
                case 14:
                    Object obj9 = this.parameter;
                    if (obj9 == null) {
                        player.setShuffleModeEnabled(!player.getShuffleModeEnabled());
                    } else {
                        player.setShuffleModeEnabled(((Boolean) obj9).booleanValue());
                    }
                    break;
                case 15:
                    Object obj10 = this.parameter;
                    if (obj10 != null) {
                        player.setRepeatMode(((Integer) obj10).intValue());
                    }
                    break;
            }
        }
    }

    public int hashCode() {
        return Objects.hash(this.sessionCommand, Integer.valueOf(this.playerCommand), Integer.valueOf(this.icon), Integer.valueOf(this.iconResId), this.displayName, Boolean.valueOf(this.isEnabled), this.iconUri, this.slots, this.parameter);
    }

    public boolean isPlayRequestPlayerAction(Player player) {
        if (this.playerCommand != 1) {
            return false;
        }
        Object obj = this.parameter;
        return obj == null ? !player.getPlayWhenReady() : ((Boolean) obj).booleanValue();
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        SessionCommand sessionCommand = this.sessionCommand;
        if (sessionCommand != null) {
            bundle.putBundle(FIELD_SESSION_COMMAND, sessionCommand.toBundle());
        }
        int i10 = this.playerCommand;
        if (i10 != -1) {
            bundle.putInt(FIELD_PLAYER_COMMAND, i10);
        }
        int i11 = this.icon;
        if (i11 != 0) {
            bundle.putInt(FIELD_ICON, i11);
        }
        int i12 = this.iconResId;
        if (i12 != 0) {
            bundle.putInt(FIELD_ICON_RES_ID, i12);
        }
        CharSequence charSequence = this.displayName;
        if (charSequence != "") {
            bundle.putCharSequence(FIELD_DISPLAY_NAME, charSequence);
        }
        if (!this.extras.isEmpty()) {
            bundle.putBundle(FIELD_EXTRAS, this.extras);
        }
        Uri uri = this.iconUri;
        if (uri != null) {
            bundle.putParcelable(FIELD_ICON_URI, uri);
        }
        boolean z = this.isEnabled;
        if (!z) {
            bundle.putBoolean(FIELD_ENABLED, z);
        }
        com.google.common.primitives.b bVar = this.slots;
        if (bVar.f14179l != 1 || bVar.a(0) != 6) {
            String str = FIELD_SLOTS;
            com.google.common.primitives.b bVar2 = this.slots;
            bundle.putIntArray(str, Arrays.copyOfRange(bVar2.f14178i, 0, bVar2.f14179l));
        }
        if (this.parameter != null) {
            writeParameterToBundle(bundle, FIELD_PARAMETER);
        }
        return bundle;
    }

    private CommandButton(SessionCommand sessionCommand, int i10, int i11, int i12, Uri uri, CharSequence charSequence, Bundle bundle, boolean z, com.google.common.primitives.b bVar, Object obj) {
        this.sessionCommand = sessionCommand;
        this.playerCommand = i10;
        this.icon = i11;
        this.iconResId = i12;
        this.iconUri = uri;
        this.displayName = charSequence;
        this.extras = new Bundle(bundle);
        this.isEnabled = z;
        this.slots = bVar;
        this.parameter = obj;
    }

    public static CommandButton fromBundle(Bundle bundle, int i10) {
        Bundle bundle2 = bundle.getBundle(FIELD_SESSION_COMMAND);
        SessionCommand sessionCommandFromBundle = bundle2 == null ? null : SessionCommand.fromBundle(bundle2);
        int i11 = bundle.getInt(FIELD_PLAYER_COMMAND, -1);
        int i12 = bundle.getInt(FIELD_ICON_RES_ID, 0);
        CharSequence charSequence = bundle.getCharSequence(FIELD_DISPLAY_NAME, "");
        Bundle bundle3 = bundle.getBundle(FIELD_EXTRAS);
        boolean z = i10 < 3 || bundle.getBoolean(FIELD_ENABLED, true);
        Uri uri = (Uri) bundle.getParcelable(FIELD_ICON_URI);
        int i13 = bundle.getInt(FIELD_ICON, 0);
        int[] intArray = bundle.getIntArray(FIELD_SLOTS);
        Builder builder = new Builder(i13, i12);
        if (sessionCommandFromBundle != null) {
            builder.setSessionCommand(sessionCommandFromBundle, getParameterFromBundle(bundle, FIELD_PARAMETER, getParameterTypeForSessionCommand(sessionCommandFromBundle.commandCode)));
        }
        if (i11 != -1) {
            builder.setPlayerCommand(i11, getParameterFromBundle(bundle, FIELD_PARAMETER, getParameterTypeForPlayerCommand(i11)));
        }
        if (uri != null && (Objects.equals(uri.getScheme(), "content") || Objects.equals(uri.getScheme(), "android.resource"))) {
            builder.setIconUri(uri);
        }
        Builder displayName = builder.setDisplayName(charSequence);
        if (bundle3 == null) {
            bundle3 = Bundle.EMPTY;
        }
        Builder enabled = displayName.setExtras(bundle3).setEnabled(z);
        if (intArray == null) {
            intArray = new int[]{6};
        }
        return enabled.setSlots(intArray).build();
    }
}
