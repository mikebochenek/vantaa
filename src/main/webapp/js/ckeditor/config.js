CKEDITOR.editorConfig = function(config) {
	config.uiColor = '#FAFAFA';
	config.disableNativeSpellChecker = false;

	config.toolbar = [
			[ 'NewPage', 'Preview' ],
			[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Print', 'SpellChecker', 'Scayt' ],
			[ 'Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll', 'RemoveFormat' ],
			[ 'Maximize', 'ShowBlocks', 'Source' ],
			'/',
			[ 'Styles', 'Format', 'Font', 'FontSize' ],
			[ 'TextColor', 'BGColor' ],
			[ 'Bold', 'Italic', 'Underline', 'Strike' ],
			[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'Blockquote' ],
			[ 'JustifyLeft', 'JustifyCenter', 'JustifyBlock' ],
			[ 'Link', 'Image', 'Table', 'Smiley' ] ];
};
