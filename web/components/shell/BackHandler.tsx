"use client";
import { useEffect, useRef } from 'react';
import { useAppPick } from '@/lib/store';
import { readWebRoute, writeWebRoute, type WebRoute } from '@/lib/webNavigation';
import type { MediaItem } from '@/lib/types';
import { parsePartnerLink } from '@/lib/partnerLinks';
export function BackHandler(){
  const app=useAppPick('view','section','query','selected','activeStream','closePlayer','setSection','setQuery','closeDetails','openDetails');const latest=useRef(app);latest.current=app;
  const initialized=useRef(false);
  const applying=useRef<WebRoute|null>(null);
  const previous=useRef('');
  const playerWasOpen=useRef(false);
  useEffect(()=>{
    if(app.view!=='app')return;
    const apply=()=>{
      // The partner handler resolves metadata after access/profile readiness.
      // Do not race it by opening an unrelated legacy title= or closing details.
      if(parsePartnerLink(location.search).status!=='none')return;
      const route=readWebRoute(new URL(location.href));
      const current=latest.current;
      // In-screen history entries (e.g. a settings section on phones) pop back to
      // the route already shown. Nothing to apply, and recording it as pending
      // would swallow the next navigation.
      const shownQuery=current.section==='search'?current.query:'';
      if(!current.selected&&!current.activeStream&&!route.title&&route.section===current.section&&route.query===shownQuery)return;
      applying.current=route;
      current.closePlayer();
      current.setSection(route.section);current.setQuery(route.query);
      if(!route.title){current.closeDetails();}
      else if(current.selected?.id!==route.title.id||current.selected?.mediaType!==route.title.mediaType){
        void current.openDetails({...route.title,title:'',image:'',backdrop:'',year:''} as MediaItem).catch(()=>{
          applying.current=null;current.closeDetails();
        });
      }
    };
    if(!initialized.current){initialized.current=true;apply();}
    window.addEventListener('popstate',apply);
    return()=>window.removeEventListener('popstate',apply);
  },[app.view]);
  useEffect(()=>{
    if(app.view!=='app'||!initialized.current)return;
    // Retain pending/failed title links unchanged until success or explicit cancel.
    if(parsePartnerLink(location.search).status!=='none')return;
    const title=app.selected&&!app.selected.isHomeServer&&app.selected.id>0?{id:app.selected.id,mediaType:app.selected.mediaType}:null;
    const route:WebRoute={section:app.section,query:app.section==='search'?app.query:'',title};
    const signature=JSON.stringify(route);const player=!!app.activeStream;
    if(applying.current){
      const target=applying.current;
      if(target.section!==route.section||target.query!==route.query||JSON.stringify(target.title)!==JSON.stringify(route.title))return;
      applying.current=null;previous.current=signature;playerWasOpen.current=false;
      history.replaceState({arvio:true},'',writeWebRoute(new URL(location.href),route));return;
    }
    if(signature===previous.current&&player===playerWasOpen.current)return;
    const before=previous.current?JSON.parse(previous.current) as WebRoute:null;
    const queryOnly=before&&before.section===route.section&&JSON.stringify(before.title)===JSON.stringify(route.title)&&player===playerWasOpen.current;
    history[queryOnly?'replaceState':'pushState']({arvio:true},'',writeWebRoute(new URL(location.href),route));
    previous.current=signature;playerWasOpen.current=player;
  },[app.view,app.section,app.query,app.selected,app.activeStream]);
  return null;
}
