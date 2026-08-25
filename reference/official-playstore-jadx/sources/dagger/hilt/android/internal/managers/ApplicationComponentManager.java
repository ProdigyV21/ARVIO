package dagger.hilt.android.internal.managers;

import dagger.hilt.internal.GeneratedComponentManager;

/* JADX INFO: loaded from: classes4.dex */
public final class ApplicationComponentManager implements GeneratedComponentManager<Object> {
    private volatile Object component;
    private final ComponentSupplier componentCreator;
    private final Object componentLock = new Object();

    public ApplicationComponentManager(ComponentSupplier componentSupplier) {
        this.componentCreator = componentSupplier;
    }

    @Override // dagger.hilt.internal.GeneratedComponentManager
    public Object generatedComponent() {
        if (this.component == null) {
            synchronized (this.componentLock) {
                try {
                    if (this.component == null) {
                        this.component = this.componentCreator.get();
                    }
                } finally {
                }
            }
        }
        return this.component;
    }
}
