CKEDITOR.plugins.add("popup");CKEDITOR.tools.extend(CKEDITOR.editor.prototype,{popup:function(b,c,i,j){c=c||"80%";i=i||"70%";if(typeof c=="string"&&c.length>1&&c.substr(c.length-1,1)=="%"){c=parseInt(window.screen.width*parseInt(c,10)/100,10)}if(typeof i=="string"&&i.length>1&&i.substr(i.length-1,1)=="%"){i=parseInt(window.screen.height*parseInt(i,10)/100,10)}if(c<640){c=640}if(i<420){i=420}var h=parseInt((window.screen.height-i)/2,10),d=parseInt((window.screen.width-c)/2,10);j=(j||"location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,resizable=yes,scrollbars=yes")+",width="+c+",height="+i+",top="+h+",left="+d;var f=window.open("",null,j,true);if(!f){return false}try{var a=navigator.userAgent.toLowerCase();if(a.indexOf(" chrome/")==-1){f.moveTo(d,h);f.resizeTo(c,i)}f.focus();f.location.href=b}catch(g){f=window.open(b,null,j,true)}return true}});