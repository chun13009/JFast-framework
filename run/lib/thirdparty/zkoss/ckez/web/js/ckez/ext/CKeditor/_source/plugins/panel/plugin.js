CKEDITOR.plugins.add("panel",{beforeInit:function(a){a.ui.addHandler(CKEDITOR.UI_PANEL,CKEDITOR.ui.panel.handler)}});CKEDITOR.UI_PANEL="panel";CKEDITOR.ui.panel=function(a,b){if(b){CKEDITOR.tools.extend(this,b)}CKEDITOR.tools.extend(this,{className:"",css:[]});this.id=CKEDITOR.tools.getNextId();this.document=a;this._={blocks:{}}};CKEDITOR.ui.panel.handler={create:function(a){return new CKEDITOR.ui.panel(a)}};CKEDITOR.ui.panel.prototype={renderHtml:function(b){var a=[];this.render(b,a);return a.join("")},render:function(b,a){var c=this.id;a.push('<div class="',b.skinClass,'" lang="',b.langCode,'" role="presentation" style="display:none;z-index:'+(b.config.baseFloatZIndex+1)+'"><div id=',c," dir=",b.lang.dir,' role="presentation" class="cke_panel cke_',b.lang.dir);if(this.className){a.push(" ",this.className)}a.push('">');if(this.forceIFrame||this.css.length){a.push('<iframe id="',c,'_frame" frameborder="0" role="application" src="javascript:void(');a.push(CKEDITOR.env.isCustomDomain()?"(function(){document.open();document.domain='"+document.domain+"';document.close();})()":"0");a.push(')"></iframe>')}a.push("</div></div>");return c},getHolderElement:function(){var i=this._.holder;if(!i){if(this.forceIFrame||this.css.length){var c=this.document.getById(this.id+"_frame"),a=c.getParent(),b=a.getAttribute("dir"),g=a.getParent().getAttribute("class"),f=a.getParent().getAttribute("lang"),j=c.getFrameDocument();CKEDITOR.env.iOS&&a.setStyles({overflow:"scroll","-webkit-overflow-scrolling":"touch"});var h=CKEDITOR.tools.addFunction(CKEDITOR.tools.bind(function(k){this.isLoaded=true;if(this.onLoad){this.onLoad()}},this));var d='<!DOCTYPE html><html dir="'+b+'" class="'+g+'_container" lang="'+f+'"><head><style>.'+g+"_container{visibility:hidden}</style>"+CKEDITOR.tools.buildStyleHtml(this.css)+'</head><body class="cke_'+b+" cke_panel_frame "+CKEDITOR.env.cssClass+'" style="margin:0;padding:0" onload="( window.CKEDITOR || window.parent.CKEDITOR ).tools.callFunction('+h+');"></body></html>';j.write(d);var e=j.getWindow();e.$.CKEDITOR=CKEDITOR;j.on("key"+(CKEDITOR.env.opera?"press":"down"),function(k){var m=k.data.getKeystroke(),l=this.document.getById(this.id).getAttribute("dir");if(this._.onKeyDown&&this._.onKeyDown(m)===false){k.data.preventDefault();return}if(m==27||m==(l=="rtl"?39:37)){if(this.onEscape&&this.onEscape(m)===false){k.data.preventDefault()}}},this);i=j.getBody();i.unselectable();CKEDITOR.env.air&&CKEDITOR.tools.callFunction(h)}else{i=this.document.getById(this.id)}this._.holder=i}return i},addBlock:function(a,b){b=this._.blocks[a]=b instanceof CKEDITOR.ui.panel.block?b:new CKEDITOR.ui.panel.block(this.getHolderElement(),b);if(!this._.currentBlock){this.showBlock(a)}return b},getBlock:function(a){return this._.blocks[a]},showBlock:function(a){var e=this._.blocks,d=e[a],c=this._.currentBlock;var b=!this.forceIFrame||CKEDITOR.env.ie?this._.holder:this.document.getById(this.id+"_frame");if(c){b.removeAttributes(c.attributes);c.hide()}this._.currentBlock=d;b.setAttributes(d.attributes);CKEDITOR.fire("ariaWidget",b);d._.focusIndex=-1;this._.onKeyDown=d.onKeyDown&&CKEDITOR.tools.bind(d.onKeyDown,d);d.show();return d},destroy:function(){this.element&&this.element.remove()}};CKEDITOR.ui.panel.block=CKEDITOR.tools.createClass({$:function(a,b){this.element=a.append(a.getDocument().createElement("div",{attributes:{tabIndex:-1,"class":"cke_panel_block",role:"presentation"},styles:{display:"none"}}));if(b){CKEDITOR.tools.extend(this,b)}if(!this.attributes.title){this.attributes.title=this.attributes["aria-label"]}this.keys={};this._.focusIndex=-1;this.element.disableContextMenu()},_:{markItem:function(b){if(b==-1){return}var a=this.element.getElementsByTag("a");var c=a.getItem(this._.focusIndex=b);if(CKEDITOR.env.webkit||CKEDITOR.env.opera){c.getDocument().getWindow().focus()}c.focus();this.onMark&&this.onMark(c)}},proto:{show:function(){this.element.setStyle("display","")},hide:function(){if(!this.onHide||this.onHide.call(this)!==true){this.element.setStyle("display","none")}},onKeyDown:function(e){var c=this.keys[e];switch(c){case"next":var b=this._.focusIndex,a=this.element.getElementsByTag("a"),d;while((d=a.getItem(++b))){if(d.getAttribute("_cke_focus")&&d.$.offsetWidth){this._.focusIndex=b;d.focus();break}}return false;case"prev":b=this._.focusIndex;a=this.element.getElementsByTag("a");while(b>0&&(d=a.getItem(--b))){if(d.getAttribute("_cke_focus")&&d.$.offsetWidth){this._.focusIndex=b;d.focus();break}}return false;case"click":case"mouseup":b=this._.focusIndex;d=b>=0&&this.element.getElementsByTag("a").getItem(b);if(d){d.$[c]?d.$[c]():d.$["on"+c]()}return false}return true}}});