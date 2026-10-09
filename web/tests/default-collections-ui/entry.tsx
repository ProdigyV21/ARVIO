import React from 'react';
import { createRoot } from 'react-dom/client';
import { CustomCollectionRail } from '../../components/media/CustomCollectionRail';
import { defaultCatalogs, mergeCatalogs } from '../../lib/catalogs';
import { collectionFolders } from '../../lib/collectionPresentation';

const catalogs = mergeCatalogs(defaultCatalogs);
createRoot(document.getElementById('root')!).render(<main style={{ padding: 24 }}>
  {catalogs.filter(c => c.kind === 'COLLECTION_RAIL').map(rail => <CustomCollectionRail key={rail.id}
    catalog={rail} folders={collectionFolders(rail, catalogs)} onOpen={item => { (window as any).openedCollectionItem = item; }} />)}
</main>);
