(function(){var b="a11yhelp",a="a11yHelp";CKEDITOR.plugins.add(b,{requires:["dialog"],availableLangs:{cs:1,cy:1,da:1,de:1,el:1,en:1,eo:1,fa:1,fi:1,fr:1,gu:1,he:1,it:1,mk:1,nb:1,nl:1,no:1,"pt-br":1,ro:1,tr:1,ug:1,vi:1,"zh-cn":1},init:function(c){var d=this;c.addCommand(a,{exec:function(){var e=c.langCode;e=d.availableLangs[e]?e:"en";CKEDITOR.scriptLoader.load(CKEDITOR.getUrl(d.path+"lang/"+e+".js"),function(){CKEDITOR.tools.extend(c.lang,d.langEntries[e]);c.openDialog(a)})},modes:{wysiwyg:1,source:1},readOnly:1,canUndo:false});CKEDITOR.dialog.add(a,this.path+"dialogs/a11yhelp.js")}})})();