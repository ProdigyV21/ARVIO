package androidx.media3.session;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.collect.o4;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class SessionCommands {
    public static final SessionCommands EMPTY = new Builder().build();
    private static final String FIELD_SESSION_COMMANDS = Util.intToStringMaxRadix(0);
    private static final String TAG = "SessionCommands";
    public final com.google.common.collect.w1 commands;

    public static final class Builder {
        private final Set<SessionCommand> commands;

        private void addCommandCodes(List<Integer> list) {
            for (int i10 = 0; i10 < list.size(); i10++) {
                add(new SessionCommand(list.get(i10).intValue()));
            }
        }

        public Builder add(SessionCommand sessionCommand) {
            Set<SessionCommand> set = this.commands;
            sessionCommand.getClass();
            set.add(sessionCommand);
            return this;
        }

        public Builder addAllLibraryCommands() {
            addCommandCodes(SessionCommand.LIBRARY_COMMANDS);
            return this;
        }

        public Builder addAllPredefinedCommands() {
            addAllSessionCommands();
            addAllLibraryCommands();
            return this;
        }

        public Builder addAllSessionCommands() {
            addCommandCodes(SessionCommand.SESSION_COMMANDS);
            return this;
        }

        public Builder addSessionCommands(Collection<SessionCommand> collection) {
            this.commands.addAll(collection);
            return this;
        }

        public SessionCommands build() {
            return new SessionCommands(this.commands);
        }

        public Builder remove(SessionCommand sessionCommand) {
            Set<SessionCommand> set = this.commands;
            sessionCommand.getClass();
            set.remove(sessionCommand);
            return this;
        }

        public Builder() {
            this.commands = new HashSet();
        }

        private Builder(SessionCommands sessionCommands) {
            sessionCommands.getClass();
            this.commands = new HashSet(sessionCommands.commands);
        }

        public Builder add(int i10) {
            ac.b.j(i10 != 0);
            this.commands.add(new SessionCommand(i10));
            return this;
        }

        public Builder remove(int i10) {
            ac.b.j(i10 != 0);
            Iterator<SessionCommand> it = this.commands.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                SessionCommand next = it.next();
                if (next.commandCode == i10) {
                    this.commands.remove(next);
                    break;
                }
            }
            return this;
        }
    }

    private static boolean containsCommandCode(Collection<SessionCommand> collection, int i10) {
        Iterator<SessionCommand> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next().commandCode == i10) {
                return true;
            }
        }
        return false;
    }

    public static SessionCommands fromBundle(Bundle bundle) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_SESSION_COMMANDS);
        if (parcelableArrayList == null) {
            Log.w(TAG, "Missing commands. Creating an empty SessionCommands");
            return EMPTY;
        }
        Builder builder = new Builder();
        for (int i10 = 0; i10 < parcelableArrayList.size(); i10++) {
            builder.add(SessionCommand.fromBundle((Bundle) parcelableArrayList.get(i10)));
        }
        return builder.build();
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean contains(SessionCommand sessionCommand) {
        com.google.common.collect.w1 w1Var = this.commands;
        sessionCommand.getClass();
        return w1Var.contains(sessionCommand);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SessionCommands) {
            return this.commands.equals(((SessionCommands) obj).commands);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.commands);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        o4 it = this.commands.iterator();
        while (it.hasNext()) {
            arrayList.add(((SessionCommand) it.next()).toBundle());
        }
        bundle.putParcelableArrayList(FIELD_SESSION_COMMANDS, arrayList);
        return bundle;
    }

    private SessionCommands(Collection<SessionCommand> collection) {
        this.commands = com.google.common.collect.w1.m(collection);
    }

    public boolean contains(int i10) {
        ac.b.k(i10 != 0, "Use contains(Command) for custom command");
        return containsCommandCode(this.commands, i10);
    }
}
