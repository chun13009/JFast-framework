(function(){var c=function(o,q){var p=o.document,m=p.getBody();var n=false;var r=function(){n=true};m.on(q,r);(CKEDITOR.env.version>7?p.$:p.$.selection.createRange())["execCommand"](q);m.removeListener(q,r);return n};var h=CKEDITOR.env.ie?function(n,m){return c(n,m)}:function(n,m){try{return n.document.$.execCommand(m,false,null)}catch(o){return false}};var k=function(m){this.type=m;this.canUndo=this.type=="cut";this.startDisabled=true};k.prototype={exec:function(m,n){this.type=="cut"&&i(m);var o=h(m,this.type);if(!o){alert(m.lang.clipboard[this.type+"Error"])}return o}};var g={canUndo:false,exec:CKEDITOR.env.ie?function(m){m.focus();if(!m.document.getBody().fire("beforepaste")&&!c(m,"paste")){m.fire("pasteDialog");return false}}:function(m){try{if(!m.document.getBody().fire("beforepaste")&&!m.document.$.execCommand("Paste",false,null)){throw 0}}catch(n){setTimeout(function(){m.fire("pasteDialog")},0);return false}}};var f=function(o){if(this.mode!="wysiwyg"){return}switch(o.data.keyCode){case CKEDITOR.CTRL+86:case CKEDITOR.SHIFT+45:var m=this.document.getBody();if(CKEDITOR.env.opera||CKEDITOR.env.gecko){m.fire("paste")}return;case CKEDITOR.CTRL+88:case CKEDITOR.SHIFT+46:var n=this;this.fire("saveSnapshot");setTimeout(function(){n.fire("saveSnapshot")},0)}};function l(m){m.cancel()}function j(u,q,v){var s=this.document;if(s.getById("cke_pastebin")){return}if(q=="text"&&u.data&&u.data.$.clipboardData){var t=u.data.$.clipboardData.getData("text/plain");if(t){u.data.preventDefault();v(t);return}}var m=this.getSelection(),p=new CKEDITOR.dom.range(s);var n=new CKEDITOR.dom.element(q=="text"?"textarea":CKEDITOR.env.webkit?"body":"div",s);n.setAttribute("id","cke_pastebin");CKEDITOR.env.webkit&&n.append(s.createText("\xa0"));s.getBody().append(n);n.setStyles({position:"absolute",top:m.getStartElement().getDocumentPosition().y+"px",width:"1px",height:"1px",overflow:"hidden"});n.setStyle(this.config.contentsLangDirection=="ltr"?"left":"right","-1000px");var o=m.createBookmarks();this.on("selectionChange",l,null,null,0);if(q=="text"){n.$.focus()}else{p.setStartAt(n,CKEDITOR.POSITION_AFTER_START);p.setEndAt(n,CKEDITOR.POSITION_BEFORE_END);p.select(true)}var r=this;window.setTimeout(function(){r.document.getBody().focus();r.removeListener("selectionChange",l);if(CKEDITOR.env.ie7Compat){m.selectBookmarks(o);n.remove()}else{n.remove();m.selectBookmarks(o)}var w;n=(CKEDITOR.env.webkit&&(w=n.getFirst())&&(w.is&&w.hasClass("Apple-style-span"))?w:n);v(n["get"+(q=="text"?"Value":"Html")]())},0)}function i(n){if(!CKEDITOR.env.ie||CKEDITOR.env.quirks){return}var o=n.getSelection();var q;if((o.getType()==CKEDITOR.SELECTION_ELEMENT)&&(q=o.getSelectedElement())){var m=o.getRanges()[0];var p=n.document.createText("");p.insertBefore(q);m.setStartBefore(p);m.setEndAfter(q);o.selectRanges([m]);setTimeout(function(){if(q.getParent()){p.remove();o.selectElement(q)}},0)}}var b,d;function a(q,o){var n;if(d&&q in {Paste:1,Cut:1}){return CKEDITOR.TRISTATE_DISABLED}if(q=="Paste"){CKEDITOR.env.ie&&(b=1);try{n=o.document.$.queryCommandEnabled(q)||CKEDITOR.env.webkit}catch(r){}b=0}else{var p=o.getSelection(),m=p&&p.getRanges();n=p&&!(m.length==1&&m[0].collapsed)}return n?CKEDITOR.TRISTATE_OFF:CKEDITOR.TRISTATE_DISABLED}function e(){if(this.mode!="wysiwyg"){return}var m=a("Paste",this);this.getCommand("cut").setState(a("Cut",this));this.getCommand("copy").setState(a("Copy",this));this.getCommand("paste").setState(m);this.fire("pasteState",m)}CKEDITOR.plugins.add("clipboard",{requires:["dialog","htmldataprocessor"],init:function(m){m.on("paste",function(o){var p=o.data;if(p.html){m.insertHtml(p.html)}else{if(p.text){m.insertText(p.text)}}setTimeout(function(){m.fire("afterPaste")},0)},null,null,1000);m.on("pasteDialog",function(o){setTimeout(function(){m.openDialog("paste")},0)});m.on("pasteState",function(o){m.getCommand("paste").setState(o.data)});function n(o,q,s,p){var r=m.lang[q];m.addCommand(q,s);m.ui.addButton(o,{label:r,command:q});if(m.addMenuItems){m.addMenuItem(q,{label:r,command:q,group:"clipboard",order:p})}}n("Cut","cut",new k("cut"),1);n("Copy","copy",new k("copy"),4);n("Paste","paste",g,8);CKEDITOR.dialog.add("paste",CKEDITOR.getUrl(this.path+"dialogs/paste.js"));m.on("key",f,m);m.on("contentDom",function(){var o=m.document.getBody();o.on(!CKEDITOR.env.ie?"paste":"beforepaste",function(p){if(b){return}var q=p.data&&p.data.$;if(CKEDITOR.env.ie&&q&&!q.ctrlKey){return}var r={mode:"html"};m.fire("beforePaste",r);j.call(m,p,r.mode,function(t){if(!(t=CKEDITOR.tools.trim(t.replace(/<span[^>]+data-cke-bookmark[^<]*?<\/span>/ig,"")))){return}var s={};s[r.mode]=t;m.fire("paste",s)})});if(CKEDITOR.env.ie){o.on("contextmenu",function(){b=1;setTimeout(function(){b=0},0)});o.on("paste",function(p){if(!m.document.getById("cke_pastebin")){p.data.preventDefault();b=0;g.exec(m)}})}o.on("beforecut",function(){!b&&i(m)});o.on("mouseup",function(){setTimeout(function(){e.call(m)},0)},m);o.on("keyup",e,m)});m.on("selectionChange",function(o){d=o.data.selection.getRanges()[0].checkReadOnly();e.call(m)});if(m.contextMenu){m.contextMenu.addListener(function(o,p){var q=p.getRanges()[0].checkReadOnly();return{cut:a("Cut",m),copy:a("Copy",m),paste:a("Paste",m)}})}}})})();