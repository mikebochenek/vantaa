/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	 config.uiColor = '#FFFFFF';
	 
	    config.toolbar = 'MyToolbar';

	    config.toolbar_MyToolbar =
	      [
	       ['NewPage','Preview'],
	       ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
	       ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	       ['Maximize', 'ShowBlocks', 'Source'],
	       '/',
	       ['Styles','Format','Font','FontSize'],
	       ['TextColor','BGColor'],
	       ['Bold','Italic','Underline','Strike'],
	       ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	       ['JustifyLeft','JustifyCenter','JustifyBlock'],
	       ['Link','Image','Table','Smiley']
	   ];
};
