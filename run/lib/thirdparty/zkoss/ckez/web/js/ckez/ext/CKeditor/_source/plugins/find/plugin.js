CKEDITOR.plugins.add("find",{requires:["dialog"],init:function(b){var a=CKEDITOR.plugins.find;b.ui.addButton("Find",{label:b.lang.findAndReplace.find,command:"find"});var d=b.addCommand("find",new CKEDITOR.dialogCommand("find"));d.canUndo=false;d.readOnly=1;b.ui.addButton("Replace",{label:b.lang.findAndReplace.replace,command:"replace"});var c=b.addCommand("replace",new CKEDITOR.dialogCommand("replace"));c.canUndo=false;CKEDITOR.dialog.add("find",this.path+"dialogs/find.js");CKEDITOR.dialog.add("replace",this.path+"dialogs/find.js")},requires:["styles"]});CKEDITOR.config.find_highlight={element:"span",styles:{"background-color":"#004",color:"#fff"}};