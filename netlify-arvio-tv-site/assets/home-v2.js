// Server-rendered translations keep first paint and interactive labels in the same language.
const homeCopy = JSON.parse(document.getElementById('home-copy').textContent);
const t = text => homeCopy[text] || text;
// Transparent over the hero; a soft fade keeps navigation readable after scrolling.
const siteNav = document.querySelector('.nav');
let navFrame;
function updateNavigation() { siteNav.classList.toggle('is-scrolled', window.scrollY > 24); }
window.addEventListener('scroll', () => {
  if (navFrame) return;
  navFrame = requestAnimationFrame(() => { updateNavigation(); navFrame = null; });
}, {passive:true});
updateNavigation();
/* Keep page motion and the hero video under the same visitor control. */
const reduceMotion = window.matchMedia('(prefers-reduced-motion: reduce)');
let paused = reduceMotion.matches;
let heroVisible = true;
const motionButton = document.querySelector('#motion');
function updateMotion() {
  document.body.classList.toggle('paused', paused);
  document.documentElement.classList.toggle('motion-allowed', !paused);
  motionButton.setAttribute('aria-pressed', String(paused));
  motionButton.textContent = paused ? t('Enable motion ↗') : t('Pause motion Ⅱ');
  document.dispatchEvent(new CustomEvent('arvio:motionchange', {detail:{paused}}));
}
updateMotion();
motionButton.addEventListener('click', () => { paused = !paused; updateMotion(); });
reduceMotion.addEventListener('change', event => { paused = event.matches; updateMotion(); });
if ('IntersectionObserver' in window) {
  document.documentElement.classList.add('js');
  const observer = new IntersectionObserver(entries => entries.forEach(entry => {
    if (entry.isIntersecting) { entry.target.classList.add('seen'); observer.unobserve(entry.target); }
  }), {threshold:0.08});
  document.querySelectorAll('.reveal').forEach(element=>observer.observe(element));
  const heroObserver = new IntersectionObserver(entries=>entries.forEach(entry=>{
    heroVisible=entry.isIntersecting;
    document.body.classList.toggle('hero-offscreen',!heroVisible || document.hidden);
  }),{threshold:0});
  heroObserver.observe(document.querySelector('.hero'));
}
document.addEventListener('visibilitychange',()=>{
  document.body.classList.toggle('hero-offscreen',document.hidden || !heroVisible);
});
async function swapImage(element, src, alt, isCurrent) {
  const next = new Image(); next.src = src;
  try { await next.decode(); } catch { return false; }
  if (!isCurrent()) return false;
  element.src = src; element.alt = t(alt);
  if (!paused) element.animate([{opacity:.35},{opacity:1}],{duration:320,easing:'ease-out'});
  return true;
}
const features = {
  library:{title:'Everything you saved.\nRight where it belongs.',copy:'Watchlists, personal lists and home servers. Browse your collections in one place, with layouts that feel at home on TV.',src:'/assets/screenshots-v2/tv/03-library.webp',alt:'ARVIO TV library with watchlists, personal lists and home servers',number:'01'},
  live:{title:'Your channels.\nA guide that feels right.',copy:'Bring your M3U or Xtream playlist. Explore channels, see what’s on next and keep your favorites close, all with your remote.',src:'/assets/screenshots-v2/tv/05-tv.webp',alt:'ARVIO live TV guide with channel listings and programme information',number:'02'},
  sports:{title:'Stay close\nto the action.',copy:'Browse sports and upcoming highlights in a dedicated view. Connect your own supported live TV sources to watch.',src:'/assets/screenshots-v2/tv/06-sports.webp',alt:'ARVIO sports page with featured channels and upcoming highlights',number:'03'}
};
const featureTabs = [...document.querySelectorAll('[data-feature]')];
let featureRequest = 0;
async function setFeature(button) {
  const request = ++featureRequest;
  const value = features[button.dataset.feature];
  if (!(await swapImage(document.querySelector('#feature-screen'),value.src,value.alt,()=>request===featureRequest))) return;
  featureTabs.forEach(b=>{b.setAttribute('aria-selected',String(b===button));b.tabIndex=b===button?0:-1;});
  document.querySelector('#feature-title').textContent=t(value.title);
  document.querySelector('#feature-title').style.whiteSpace='pre-line';
  document.querySelector('#feature-copy').textContent=t(value.copy);
  document.querySelector('.feature-number').textContent=value.number;
  document.querySelector('#feature-panel').setAttribute('aria-labelledby',button.id);
}
featureTabs.forEach((button,index)=>{
  button.addEventListener('click',()=>setFeature(button));
  button.addEventListener('keydown',event=>{
    let next;
    if(event.key==='ArrowRight') next=(index+1)%featureTabs.length;
    if(event.key==='ArrowLeft') next=(index-1+featureTabs.length)%featureTabs.length;
    if(event.key==='Home') next=0;
    if(event.key==='End') next=featureTabs.length-1;
    if(next!==undefined){event.preventDefault();featureTabs[next].focus();setFeature(featureTabs[next]);}
  });
});

function openLinkedGallery() {
  if (location.hash !== '#screens') return;
  const gallery = document.getElementById('screens');
  gallery.open = true;
  const firstGroup = gallery.querySelector('.capture-group');
  if(firstGroup) firstGroup.open = true;
  requestAnimationFrame(()=>gallery.scrollIntoView({block:'start',behavior:'instant'}));
}
window.addEventListener('hashchange',openLinkedGallery);
openLinkedGallery();

/* Show the latest GitHub release in the hero badge */
(async function() {
  const link = document.querySelector('.release');
  if (!link) return;
  try {
    const res = await fetch('https://api.github.com/repos/ProdigyV21/ARVIO/releases/latest');
    if (!res.ok) return;
    const release = await res.json();
    if (release && release.tag_name) {
      if (release.html_url) link.href = release.html_url;
      const tag = release.tag_name;
      const label = link.dataset.releaseLabel || 'LATEST RELEASE';
      const dot = link.querySelector('.dot') ? '<span class="dot"></span> ' : '';
      link.replaceChildren();
      if (dot) {
        const indicator = document.createElement('span');
        indicator.className = 'dot';
        link.append(indicator, ' ');
      }
      link.append(label + ' · ' + tag + ' ');
      const arrow = document.createElement('span');
      arrow.setAttribute('aria-hidden', 'true');
      arrow.textContent = '↗';
      link.append(arrow);
    }
  } catch {}
})();
