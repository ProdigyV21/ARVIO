package dagger.hilt.android.flags;

import android.content.Context;
import dagger.Module;
import dagger.hilt.android.EntryPointAccessors;
import dagger.hilt.internal.Preconditions;
import dagger.multibindings.Multibinds;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.Set;
import javax.inject.Qualifier;

/* JADX INFO: loaded from: classes4.dex */
public final class FragmentGetContextFix {

    @Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
    @Qualifier
    public @interface DisableFragmentGetContextFix {
    }

    public interface FragmentGetContextFixEntryPoint {
        Set<Boolean> getDisableFragmentGetContextFix();
    }

    @Module
    public static abstract class FragmentGetContextFixModule {
        @Multibinds
        public abstract Set<Boolean> disableFragmentGetContextFix();
    }

    private FragmentGetContextFix() {
    }

    public static boolean isFragmentGetContextFixDisabled(Context context) {
        Set<Boolean> disableFragmentGetContextFix = ((FragmentGetContextFixEntryPoint) EntryPointAccessors.fromApplication(context, FragmentGetContextFixEntryPoint.class)).getDisableFragmentGetContextFix();
        Preconditions.checkState(disableFragmentGetContextFix.size() <= 1, "Cannot bind the flag @DisableFragmentGetContextFix more than once.", new Object[0]);
        if (disableFragmentGetContextFix.isEmpty()) {
            return true;
        }
        return disableFragmentGetContextFix.iterator().next().booleanValue();
    }
}
