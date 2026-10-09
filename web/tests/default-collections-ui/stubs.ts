import { titles } from '../library-ui/stubs';
export * from '../library-ui/stubs';
export const getCollectionPreview = async (item: unknown) => item;
export const loadCatalog = async () => ({ items: titles.slice(0, 12) });
export const loadCollectionSource = async (source: { mediaType: string }) => titles
  .filter(item => item.mediaType === source.mediaType).slice(0, 12);
