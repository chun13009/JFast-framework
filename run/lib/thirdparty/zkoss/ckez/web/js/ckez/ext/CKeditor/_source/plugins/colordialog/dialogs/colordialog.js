CKEDITOR.dialog.add("colordialog",function(a){var u=CKEDITOR.dom.element,e=CKEDITOR.document,v=a.lang.colordialog;var q;var f={type:"html",html:"&nbsp;"};var l;function n(){e.getById(m).removeStyle("background-color");q.getContentElement("picker","selectedColor").setValue("");l&&l.removeAttribute("aria-selected");l=null}function o(w){var y=w.data.getTarget(),x;if(y.getName()=="td"&&(x=y.getChild(0).getHtml())){l=y;l.setAttribute("aria-selected",true);q.getContentElement("picker","selectedColor").setValue(x)}}function c(w){w=w.replace(/^#/,"");for(var y=0,x=[];y<=2;y++){x[y]=parseInt(w.substr(y*2,2),16)}var z=(0.2126*x[0])+(0.7152*x[1])+(0.0722*x[2]);return"#"+(z>=165?"000":"fff")}var h,j;function d(y){!y.name&&(y=new CKEDITOR.event(y));var x=!(/mouse/).test(y.name),z=y.data.getTarget(),w;if(z.getName()=="td"&&(w=z.getChild(0).getHtml())){i(y);x?h=z:j=z;if(x){z.setStyle("border-color",c(w));z.setStyle("border-style","dotted")}e.getById(k).setStyle("background-color",w);e.getById(b).setHtml(w)}}function g(){var w=h.getChild(0).getHtml();h.setStyle("border-color",w);h.setStyle("border-style","solid");e.getById(k).removeStyle("background-color");e.getById(b).setHtml("&nbsp;");h=null}function i(y){var x=!(/mouse/).test(y.name),z=x&&h;if(z){var w=z.getChild(0).getHtml();z.setStyle("border-color",w);z.setStyle("border-style","solid")}if(!(h||j)){e.getById(k).removeStyle("background-color");e.getById(b).setHtml("&nbsp;")}}function p(w){var A=w.data;var x=A.getTarget();var z,y;var C=A.getKeystroke(),B=a.lang.dir=="rtl";switch(C){case 38:if((z=x.getParent().getPrevious())){y=z.getChild([x.getIndex()]);y.focus()}A.preventDefault();break;case 40:if((z=x.getParent().getNext())){y=z.getChild([x.getIndex()]);if(y&&y.type==1){y.focus()}}A.preventDefault();break;case 32:case 13:o(w);A.preventDefault();break;case B?37:39:if((y=x.getNext())){if(y.type==1){y.focus();A.preventDefault(true)}}else{if((z=x.getParent().getNext())){y=z.getChild([0]);if(y&&y.type==1){y.focus();A.preventDefault(true)}}}break;case B?39:37:if((y=x.getPrevious())){y.focus();A.preventDefault(true)}else{if((z=x.getParent().getPrevious())){y=z.getLast();y.focus();A.preventDefault(true)}}break;default:return}}function r(){s=CKEDITOR.dom.element.createFromHtml('<table tabIndex="-1" aria-label="'+v.options+'" role="grid" style="border-collapse:separate;" cellspacing="0"><caption class="cke_voice_label">'+v.options+'</caption><tbody role="presentation"></tbody></table>');s.on("mouseover",d);s.on("mouseout",i);var B=["00","33","66","99","cc","ff"];function x(F,E){for(var D=F;D<F+3;D++){var G=new u(s.$.insertRow(-1));G.setAttribute("role","row");for(var C=E;C<E+3;C++){for(var H=0;H<6;H++){z(G.$,"#"+B[C]+B[H]+B[D])}}}}function z(E,D){var C=new u(E.insertCell(-1));C.setAttribute("class","ColorCell");C.setAttribute("tabIndex",-1);C.setAttribute("role","gridcell");C.on("keydown",p);C.on("click",o);C.on("focus",d);C.on("blur",i);C.setStyle("background-color",D);C.setStyle("border","1px solid "+D);C.setStyle("width","14px");C.setStyle("height","14px");var F=t("color_table_cell");C.setAttribute("aria-labelledby",F);C.append(CKEDITOR.dom.element.createFromHtml('<span id="'+F+'" class="cke_voice_label">'+D+"</span>",CKEDITOR.document))}x(0,0);x(3,0);x(0,3);x(3,3);var w=new u(s.$.insertRow(-1));w.setAttribute("role","row");for(var A=0;A<6;A++){z(w.$,"#"+B[A]+B[A]+B[A])}for(var y=0;y<12;y++){z(w.$,"#000000")}}var t=function(w){return CKEDITOR.tools.getNextId()+"_"+w},k=t("hicolor"),b=t("hicolortext"),m=t("selhicolor"),s;r();return{title:v.title,minWidth:360,minHeight:220,onLoad:function(){q=this},onHide:function(){n();g()},contents:[{id:"picker",label:v.title,accessKey:"I",elements:[{type:"hbox",padding:0,widths:["70%","10%","30%"],children:[{type:"html",html:"<div></div>",onLoad:function(){CKEDITOR.document.getById(this.domId).append(s)},focus:function(){(h||this.getElement().getElementsByTag("td").getItem(0)).focus()}},f,{type:"vbox",padding:0,widths:["70%","5%","25%"],children:[{type:"html",html:"<span>"+v.highlight+'</span>												<div id="'+k+'" style="border: 1px solid; height: 74px; width: 74px;"></div>												<div id="'+b+'">&nbsp;</div><span>'+v.selected+'</span>												<div id="'+m+'" style="border: 1px solid; height: 20px; width: 74px;"></div>'},{type:"text",label:v.selected,labelStyle:"display:none",id:"selectedColor",style:"width: 74px",onChange:function(){try{e.getById(m).setStyle("background-color",this.getValue())}catch(w){n()}}},f,{type:"button",id:"clear",style:"margin-top: 5px",label:v.clear,onClick:n}]}]}]}]}});