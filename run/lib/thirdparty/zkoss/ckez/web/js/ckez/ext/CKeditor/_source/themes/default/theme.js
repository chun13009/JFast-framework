CKEDITOR.themes.add("default",(function(){var b={};function a(h,e){var d,g;g=h.config.sharedSpaces;g=g&&g[e];g=g&&CKEDITOR.document.getById(g);if(g){var f='<span class="cke_shared " dir="'+h.lang.dir+'"><span class="'+h.skinClass+" "+h.id+" cke_editor_"+h.name+'"><span class="'+CKEDITOR.env.cssClass+'"><span class="cke_wrapper cke_'+h.lang.dir+'"><span class="cke_editor"><div class="cke_'+e+'"></div></span></span></span></span></span>';var c=g.append(CKEDITOR.dom.element.createFromHtml(f,g.getDocument()));if(g.getCustomData("cke_hasshared")){c.hide()}else{g.setCustomData("cke_hasshared",1)}d=c.getChild([0,0,0,0]);!h.sharedSpaces&&(h.sharedSpaces={});h.sharedSpaces[e]=d;h.on("focus",function(){for(var k=0,l,j=g.getChildren();(l=j.getItem(k));k++){if(l.type==CKEDITOR.NODE_ELEMENT&&!l.equals(c)&&l.hasClass("cke_shared")){l.hide()}}c.show()});h.on("destroy",function(){c.remove()})}return d}return{build:function(m,k){var e=m.name,j=m.element,i=m.elementMode;if(!j||i==CKEDITOR.ELEMENT_MODE_NONE){return}if(i==CKEDITOR.ELEMENT_MODE_REPLACE){j.hide()}var f=m.fire("themeSpace",{space:"top",html:""}).html;var r=m.fire("themeSpace",{space:"contents",html:""}).html;var p=m.fireOnce("themeSpace",{space:"bottom",html:""}).html;var q=r&&m.config.height;var h=m.config.tabIndex||m.element.getAttribute("tabindex")||0;if(!r){q="auto"}else{if(!isNaN(q)){q+="px"}}var d="";var g=m.config.width;if(g){if(!isNaN(g)){g+="px"}d+="width: "+g+";"}var n=f&&a(m,"top"),o=a(m,"bottom");n&&(n.setHtml(f),f="");o&&(o.setHtml(p),p="");var l="<style>."+m.skinClass+"{visibility:hidden;}</style>";if(b[m.skinClass]){l=""}else{b[m.skinClass]=1}var c=CKEDITOR.dom.element.createFromHtml(['<span id="cke_',e,'" class="',m.skinClass," ",m.id," cke_editor_",e,'" dir="',m.lang.dir,'" title="',(CKEDITOR.env.gecko?" ":""),'" lang="',m.langCode,'"'+(CKEDITOR.env.webkit?' tabindex="'+h+'"':"")+' role="application" aria-labelledby="cke_',e,'_arialbl"'+(d?' style="'+d+'"':"")+'><span id="cke_',e,'_arialbl" class="cke_voice_label">'+m.lang.editor+'</span><span class="',CKEDITOR.env.cssClass,'" role="presentation"><span class="cke_wrapper cke_',m.lang.dir,'" role="presentation"><table class="cke_editor" border="0" cellspacing="0" cellpadding="0" role="presentation"><tbody><tr',f?"":' style="display:none"',' role="presentation"><td id="cke_top_',e,'" class="cke_top" role="presentation">',f,"</td></tr><tr",r?"":' style="display:none"',' role="presentation"><td id="cke_contents_',e,'" class="cke_contents" style="height:',q,'" role="presentation">',r,"</td></tr><tr",p?"":' style="display:none"',' role="presentation"><td id="cke_bottom_',e,'" class="cke_bottom" role="presentation">',p,"</td></tr></tbody></table>"+l+"</span></span></span>"].join(""));c.getChild([1,0,0,0,0]).unselectable();c.getChild([1,0,0,0,2]).unselectable();if(i==CKEDITOR.ELEMENT_MODE_REPLACE){c.insertAfter(j)}else{j.append(c)}m.container=c;c.disableContextMenu();m.on("contentDirChanged",function(s){var u=(m.lang.dir!=s.data?"add":"remove")+"Class";c.getChild(1)[u]("cke_mixed_dir_content");var t=this.sharedSpaces&&this.sharedSpaces[this.config.toolbarLocation];t&&t.getParent().getParent()[u]("cke_mixed_dir_content")});m.fireOnce("themeLoaded");m.fireOnce("uiReady")},buildDialog:function(h){var j=CKEDITOR.tools.getNextNumber();var e=CKEDITOR.dom.element.createFromHtml(['<div class="',h.id,"_dialog cke_editor_",h.name.replace(".","\\."),"_dialog cke_skin_",h.skinName,'" dir="',h.lang.dir,'" lang="',h.langCode,'" role="dialog" aria-labelledby="%title#"><table class="cke_dialog'," "+CKEDITOR.env.cssClass," cke_",h.lang.dir,'" style="position:absolute" role="presentation"><tr><td role="presentation"><div class="%body" role="presentation"><div id="%title#" class="%title" role="presentation"></div><a id="%close_button#" class="%close_button" href="javascript:void(0)" title="'+h.lang.common.close+'" role="button"><span class="cke_label">X</span></a><div id="%tabs#" class="%tabs" role="tablist"></div><table class="%contents" role="presentation"><tr><td id="%contents#" class="%contents" role="presentation"></td></tr><tr><td id="%footer#" class="%footer" role="presentation"></td></tr></table></div><div id="%tl#" class="%tl"></div><div id="%tc#" class="%tc"></div><div id="%tr#" class="%tr"></div><div id="%ml#" class="%ml"></div><div id="%mr#" class="%mr"></div><div id="%bl#" class="%bl"></div><div id="%bc#" class="%bc"></div><div id="%br#" class="%br"></div></td></tr></table>',(CKEDITOR.env.ie?"":"<style>.cke_dialog{visibility:hidden;}</style>"),"</div>"].join("").replace(/#/g,"_"+j).replace(/%/g,"cke_dialog_"));var g=e.getChild([0,0,0,0,0]),i=g.getChild(0),k=g.getChild(1);if(CKEDITOR.env.ie&&!CKEDITOR.env.ie6Compat){var f=CKEDITOR.env.isCustomDomain(),c="javascript:void(function(){"+encodeURIComponent("document.open();"+(f?('document.domain="'+document.domain+'";'):"")+"document.close();")+"}())",d=CKEDITOR.dom.element.createFromHtml('<iframe frameBorder="0" class="cke_iframe_shim" src="'+c+'" tabIndex="-1"></iframe>');d.appendTo(g.getParent())}i.unselectable();k.unselectable();return{element:e,parts:{dialog:e.getChild(0),title:i,close:k,tabs:g.getChild(2),contents:g.getChild([3,0,0,0]),footer:g.getChild([3,0,1,0])}}},destroy:function(e){var c=e.container,d=e.element;if(c){c.clearCustomData();c.remove()}if(d){d.clearCustomData();e.elementMode==CKEDITOR.ELEMENT_MODE_REPLACE&&d.show();delete e.element}}}})());CKEDITOR.editor.prototype.getThemeSpace=function(b){var a="cke_"+b;var c=this._[a]||(this._[a]=CKEDITOR.document.getById(a+"_"+this.name));return c};CKEDITOR.editor.prototype.resize=function(d,h,a,f){var b=this.container,e=CKEDITOR.document.getById("cke_contents_"+this.name),c=CKEDITOR.env.webkit&&this.document&&this.document.getWindow().$.frameElement,i=f?b.getChild(1):b;i.setSize("width",d,true);c&&(c.style.width="1%");var g=a?0:(i.$.offsetHeight||0)-(e.$.clientHeight||0);e.setStyle("height",Math.max(h-g,0)+"px");c&&(c.style.width="100%");this.fire("resize")};CKEDITOR.editor.prototype.getResizable=function(a){return a?CKEDITOR.document.getById("cke_contents_"+this.name):this.container};