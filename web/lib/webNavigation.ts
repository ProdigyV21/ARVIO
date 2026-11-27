import type { NavSection } from './types';
export type WebRoute = { section: NavSection; query: string; title: {id:number;mediaType:'movie'|'tv'} | null };
const sections = new Set(['home','search','watchlist','tv','music','settings','addons']);
export function readWebRoute(url: URL):WebRoute {
  const section=url.searchParams.get('view')||'home';
  const match=url.searchParams.get('title')?.match(/^(movie|tv):(\d+)$/);
  return {section:(sections.has(section)?section:'home') as NavSection,query:section==='search'?(url.searchParams.get('q')||'').slice(0,500):'',
    title:match&&Number.isSafeInteger(Number(match[2]))&&Number(match[2])>0?{id:Number(match[2]),mediaType:match[1] as 'movie'|'tv'}:null};
}
export function writeWebRoute(url: URL,route:WebRoute){
  const next=new URL(url);next.searchParams.delete('title');next.searchParams.delete('q');next.searchParams.delete('view');
  if(route.section!=='home')next.searchParams.set('view',route.section);
  if(route.query&&route.section==='search')next.searchParams.set('q',route.query);
  if(route.title)next.searchParams.set('title',`${route.title.mediaType}:${route.title.id}`);
  return next;
}
