package dagger.hilt.android.internal.managers;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.fragment.app.c0;
import dagger.hilt.EntryPoints;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.internal.GeneratedComponentManager;
import dagger.hilt.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public class FragmentComponentManager implements GeneratedComponentManager<Object> {
    private volatile Object component;
    private final Object componentLock = new Object();
    private final c0 fragment;

    public interface FragmentComponentBuilderEntryPoint {
        FragmentComponentBuilder fragmentComponentBuilder();
    }

    public FragmentComponentManager(c0 c0Var) {
        this.fragment = c0Var;
    }

    private Object createComponent() {
        Preconditions.checkNotNull(this.fragment.getHost(), "Hilt Fragments must be attached before creating the component.");
        Preconditions.checkState(this.fragment.getHost() instanceof GeneratedComponentManager, "Hilt Fragments must be attached to an @AndroidEntryPoint Activity. Found: %s", this.fragment.getHost().getClass());
        validate(this.fragment);
        return ((FragmentComponentBuilderEntryPoint) EntryPoints.get(this.fragment.getHost(), FragmentComponentBuilderEntryPoint.class)).fragmentComponentBuilder().fragment(this.fragment).build();
    }

    public static ContextWrapper createContextWrapper(Context context, c0 c0Var) {
        return new ViewComponentManager.FragmentContextWrapper(context, c0Var);
    }

    public static final Context findActivity(Context context) {
        while ((context instanceof ContextWrapper) && !(context instanceof Activity)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return context;
    }

    public static final void initializeArguments(c0 c0Var) {
        Preconditions.checkNotNull(c0Var);
        if (c0Var.getArguments() == null) {
            c0Var.setArguments(new Bundle());
        }
    }

    @Override // dagger.hilt.internal.GeneratedComponentManager
    public Object generatedComponent() {
        if (this.component == null) {
            synchronized (this.componentLock) {
                try {
                    if (this.component == null) {
                        this.component = createComponent();
                    }
                } finally {
                }
            }
        }
        return this.component;
    }

    public void validate(c0 c0Var) {
    }

    public static ContextWrapper createContextWrapper(LayoutInflater layoutInflater, c0 c0Var) {
        return new ViewComponentManager.FragmentContextWrapper(layoutInflater, c0Var);
    }
}
