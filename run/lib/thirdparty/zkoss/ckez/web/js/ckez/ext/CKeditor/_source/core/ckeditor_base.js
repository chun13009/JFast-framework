if(!window.CKEDITOR){window.CKEDITOR=(function(){var b={timestamp:"C6HH5UF",version:"3.6.4",revision:"7575",rnd:Math.floor(Math.random()*(999-100+1))+100,_:{},status:"unloaded",basePath:(function(){var g=window.CKEDITOR_BASEPATH||"";if(!g){var d=document.getElementsByTagName("script");for(var f=0;f<d.length;f++){var e=d[f].src.match(/(^|.*[\\\/])ckeditor(?:_basic)?(?:_source)?.js(?:\?.*)?$/i);if(e){g=e[1];break}}}if(g.indexOf(":/")==-1){if(g.indexOf("/")===0){g=location.href.match(/^.*?:\/\/[^\/]*/)[0]+g}else{g=location.href.match(/^[^\?]*\/(?:)/)[0]+g}}if(!g){throw'The CKEditor installation path could not be automatically detected. Please set the global variable "CKEDITOR_BASEPATH" before creating editor instances.'}return g})(),getUrl:function(d){if(d.indexOf(":/")==-1&&d.indexOf("/")!==0){d=this.basePath+d}if(this.timestamp&&d.charAt(d.length-1)!="/"&&!(/[&?]t=/).test(d)){d+=(d.indexOf("?")>=0?"&":"?")+"t="+this.timestamp}return d}};var a=window.CKEDITOR_GETURL;if(a){var c=b.getUrl;b.getUrl=function(d){return a.call(b,d)||c.call(b,d)}}return b})()};