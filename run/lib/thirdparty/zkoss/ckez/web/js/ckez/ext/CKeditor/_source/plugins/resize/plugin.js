CKEDITOR.plugins.add("resize",{init:function(e){var c=e.config;var k=e.element.getDirection(1);!c.resize_dir&&(c.resize_dir="both");(c.resize_maxWidth==undefined)&&(c.resize_maxWidth=3000);(c.resize_maxHeight==undefined)&&(c.resize_maxHeight=3000);(c.resize_minWidth==undefined)&&(c.resize_minWidth=750);(c.resize_minHeight==undefined)&&(c.resize_minHeight=250);if(c.resize_enabled!==false){var b=null,h,j,f=(c.resize_dir=="both"||c.resize_dir=="horizontal")&&(c.resize_minWidth!=c.resize_maxWidth),a=(c.resize_dir=="both"||c.resize_dir=="vertical")&&(c.resize_minHeight!=c.resize_maxHeight);function d(n){var o=n.data.$.screenX-h.x,m=n.data.$.screenY-h.y,q=j.width,l=j.height,p=q+o*(k=="rtl"?-1:1),r=l+m;if(f){q=Math.max(c.resize_minWidth,Math.min(p,c.resize_maxWidth))}if(a){l=Math.max(c.resize_minHeight,Math.min(r,c.resize_maxHeight))}e.resize(f?q:null,l)}function i(l){CKEDITOR.document.removeListener("mousemove",d);CKEDITOR.document.removeListener("mouseup",i);if(e.document){e.document.removeListener("mousemove",d);e.document.removeListener("mouseup",i)}}var g=CKEDITOR.tools.addFunction(function(l){if(!b){b=e.getResizable()}j={width:b.$.offsetWidth||0,height:b.$.offsetHeight||0};h={x:l.screenX,y:l.screenY};c.resize_minWidth>j.width&&(c.resize_minWidth=j.width);c.resize_minHeight>j.height&&(c.resize_minHeight=j.height);CKEDITOR.document.on("mousemove",d);CKEDITOR.document.on("mouseup",i);if(e.document){e.document.on("mousemove",d);e.document.on("mouseup",i)}});e.on("destroy",function(){CKEDITOR.tools.removeFunction(g)});e.on("themeSpace",function(l){if(l.data.space=="bottom"){var m="";if(f&&!a){m=" cke_resizer_horizontal"}if(!f&&a){m=" cke_resizer_vertical"}var n='<div class="cke_resizer'+m+" cke_resizer_"+k+'" title="'+CKEDITOR.tools.htmlEncode(e.lang.resize)+'" onmousedown="CKEDITOR.tools.callFunction('+g+', event)"></div>';k=="ltr"&&m=="ltr"?l.data.html+=n:l.data.html=n+l.data.html}},e,null,100)}}});