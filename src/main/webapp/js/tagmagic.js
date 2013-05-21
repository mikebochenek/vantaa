var tm = 
{
	cTagIdx: 0,
	cSentenceIdx: 0,
	kids: null,
	cPage: 0,
    nMaxTags: 12, //TODO this should get increased to 20 or so..
	
    next_t: function (elem) {
    	if (!elem.firstChild.src.match(/_g.png/gi)) {
    		tm.cPage++;
    		tm.cTagIdx = (tm.cPage * tm.nMaxTags);
    		tm.show_t();
    	}
    },
    
    prev_t: function (elem) {
    	if (!elem.firstChild.src.match(/_g.png/gi)) {
    		tm.cPage--;
    		tm.cTagIdx = (tm.cPage * tm.nMaxTags);
    		tm.show_t();
    	}
    },
    
    next_s: function (elem) {
    	if (!elem.firstChild.src.match(/_g.png/gi)) {
    		tm.show_s(tm.cTagIdx, tm.cSentenceIdx + 1);
    	}
    },
    
    prev_s: function (elem) {
    	if (!elem.firstChild.src.match(/_g.png/gi)) {
    		tm.show_s(tm.cTagIdx, tm.cSentenceIdx - 1);
    	}
    },
    
    postload: function() {
    	this.kids = $('#tabledata').children();
    	tm.show_t();
    },

    show_t: function() {
    	$('#table_tag_id').empty();
    	for (i = tm.cTagIdx; i < this.kids.length && i < (tm.cTagIdx + tm.nMaxTags); i++) {
    		tagName = this.kids[i].firstChild.data;
    		tagName = tagName.replace(/^\s+|\s+$/g,'')
    		clickStr = "tm.show_s('" + i + "'); return false;"
    		$('<a href="" onclick="'+clickStr+'">'+tagName+'</a>').appendTo('#table_tag_id');
    		$('<span>&nbsp;&nbsp;&nbsp;</span>').appendTo('#table_tag_id');
    	}
    	
    	tm.update_tag_arrows();
    	tm.show_s(tm.cTagIdx);
    },
    
    show_s: function(tagIdx, sentenceIdx) {
		grandkids = $(tm.kids[tagIdx]).children();
		tm.cTagIdx = tagIdx;
		tm.cSentenceIdx = (sentenceIdx == undefined ? 0 : sentenceIdx);
		newTxt = grandkids[tm.cSentenceIdx].firstChild.data;
		x = ($('#table_sent_id'));
		x.children()[0].innerHTML = (newTxt);
		
		tm.update_sen_arrows();
    },

    update_sen_arrows : function() {
		if (grandkids[tm.cSentenceIdx + 1] != undefined) { //TODO how can we reference grandkids? why does this work?
			$('#nxt_s').attr('src', $('#nxt_s').attr('src').replace(/_g.png/gi, '_b.png'));
		} else {
			$('#nxt_s').attr('src', $('#nxt_s').attr('src').replace(/_..png/gi, '_g.png'));
		}
		
		if (tm.cSentenceIdx > 0) {
			$('#prev_s').attr('src', $('#prev_s').attr('src').replace(/_g.png/gi, '_b.png'));
		} else {
			$('#prev_s').attr('src', $('#prev_s').attr('src').replace(/_..png/gi, '_g.png'));
		}
    },

    update_tag_arrows : function() {
		if (((tm.cPage+1) * tm.nMaxTags) < tm.kids.length) { //TODO?
			$('#nxt_t').attr('src', $('#nxt_t').attr('src').replace(/_g.png/gi, '_b.png'));
		} else {
			$('#nxt_t').attr('src', $('#nxt_t').attr('src').replace(/_..png/gi, '_g.png'));
		}
		
		if (tm.cPage > 0) {
			$('#prev_t').attr('src', $('#prev_t').attr('src').replace(/_g.png/gi, '_b.png'));
		} else {
			$('#prev_t').attr('src', $('#prev_t').attr('src').replace(/_..png/gi, '_g.png'));
		}
    },
    
    highlight : function(elem) {
    	elem.src = elem.src.replace(/_b.png/gi, '_s.png');
    },
    
    unhighlight : function(elem) {
    	elem.src = elem.src.replace(/_s.png/gi, '_b.png');
    },
    
    place : function(elem) {
		grandkids = $(tm.kids[tm.cTagIdx]).children();
		newTxt = '  ' + grandkids[tm.cSentenceIdx].firstChild.data;
    	CKEDITOR.instances[($('#ckeditorparent'))[0].firstChild.id].insertText(newTxt);
    },
    
    highlightplace : function() {
    	$('#placetext').css("color", "#c1257a");
    },
    
    unhighlightplace : function() {
    	$('#placetext').css("color", "#2472c3");
    },

    highlightrandom : function() {
    	$('#generateRandomId').css("color", "#c1257a");
    },
    
    unhighlightrandom : function() {
    	$('#generateRandomId').css("color", "#2472c3");
    },

    generateRandom : function() {
    	var newTxt = '';
    	for (i = 0; i < 6; i++) {
        	var idx = Math.floor(Math.random() * this.kids.length);
    		grandkids = $(tm.kids[idx]).children();
    		newTxt += ((i==0) ? '' : '  ') + grandkids[0].firstChild.data;
    	}
    	CKEDITOR.instances[($('#ckeditorparent'))[0].firstChild.id].insertText(newTxt);
    }
};
