import fixtures from "../../../app/src/androidTest/assets/library/titles.json";
const asset=(file?:string)=>file?`/fixtures/${file}`:"";
export const titles=Array.from({length:12},(_,batch)=>fixtures.map((row)=>({id:row.id+batch*1000000,tmdbId:row.id,title:row.title,mediaType:row.mediaType.toLowerCase()==="movie"?"movie":"tv",year:row.year,image:asset(row.poster),backdrop:asset(row.backdrop)}))).flat();
export const app={
 watchlist:titles, traktConnected:true,simklConnected:true,mdblistConnected:true,
 settings:{cardLayoutMode:new URLSearchParams(location.search).get("poster")?"poster":"landscape",homeServers:[{id:"fixture",name:"Home NAS",type:"jellyfin",url:"http://fixture.invalid",enabled:true}],language:"en"},
 auth:{userId:"visual-fixture"},activeProfile:{id:"fixture"},trackingPreferences:{watchlistReadMode:"both"},catalogConfigs:[],
 loadTraktLists:async()=>["Friday night","Science fiction essentials","Family favourites","Hidden gems","Weekend series","Award winners"].map((name,index)=>({id:String(index),name})),
 loadTraktListItems:async(id:string)=>{const offset=Number(id.split(":").at(-1))||0;return [...titles.slice(offset,offset+24)];},
 loadTrackerLibrary:async()=>titles, loadCatalogRow:async()=>({items:titles}),
 openDetails:(item:unknown)=>{(window as any).openedLibraryItem=item;},isWatched:()=>false,openContextMenu:()=>{},setSection:()=>{}
};
export const useApp=()=>app;
export const authClient={session:null};
export const traktClient={watchlist:async()=>[],calendarSeason:async()=>[]};
export const getLogoUrl=async(item:{id:number})=>asset(fixtures.find((row)=>row.id===item.id)?.logo);
export const getCardMeta=async()=>({});
export const getCardProviders=async()=>[];
export const prefetchDetails=()=>{};
export const resolveTmdbId=async(item:{id:number})=>item.id;
export const getImdbRating=async()=>null;
export const searchMediaPage=async()=>({items:titles,hasMore:false});
export const listHomeServerLibraries=async()=>[{value:"fixture-movies",serverId:"fixture",serverName:"Home NAS",serverType:"jellyfin",libraryName:"Movies",mediaType:"movie"}];
export const loadHomeServerLibraryPage=async(_servers:unknown,_source:unknown,options:{offset:number,limit:number})=>({items:titles.slice(options.offset,options.offset+options.limit),hasMore:options.offset+options.limit<titles.length,total:titles.length});
