"use strict";
var napkin = {};

(function(napkin) {
	
	napkin.buildFileInfoList = function() {
		var jAt = $(".order-attachment");
		var jAtUi;
		var orderId = +jAt.attr("orderId");
		orderId = !isNaN(orderId) ? orderId : 0;
		var attachmentList;
		var name, size, lastModified;
		
		$.get(orderId + "/order_attachments", function(response) {
//			console.log(response);
			jAt.html("");
			jAt.append("<b>Opmerkingen</b>");
			jAt.append("<ul></ul>")
			jAtUi = jAt.find("ul");
			if(Array.isArray(response)) {
				attachmentList = response;
				for(let x = 0; x < attachmentList.length; ++x) {
					name = attachmentList[x].name;
					size = attachmentList[x].size;
					lastModified = new Date(attachmentList[x].lastModified);
					
					if(size < 1024) {
						size = size + " b";
					} else if (1024 <= size && size <= 1024 * 1024) {
						size = Math.round(size / 1024 * 1000)  / 1000 + " kb";
					} else if (1024 * 1024 <= size && size <= 1024 * 1024 * 1024) {
						size = Math.round(size / (1024 * 1024)* 1000) / 1000 + " mb";
					} else {
						size = " ???";
					}
					
					jAtUi.append(
						"<li>\
							<i class='_attachmentPreview attachment-preview fa fa-eye' data-toggle='modal' data-target='._attachmentModal' fileName='" + name + "'></i>\
							<span class='_removeAttachment remove-icon-align remove-attachment glyphicon glyphicon-remove' fileName='" + name + "'></span>\
							<a href='" + orderId + "/order_attachments/" + name + "' download title='download' class='download-attachment'>" +
								"<i class='icon-align material-icons'>attach_file</i>" + name + "" +
								"<p class='file-info'> - " + size + " - " + _getDateFormatForAttachment(lastModified) + "</p>" +
						"</a>\
						</li>");
				}
				
			} else {
				jAtUi.append("<li>Any attachment is not available!</li>");
			}
			
			$(".order-attachment li").hover(function(e) {
				$(this).find(".remove-attachment").show();
			}, function(e) {
				$(this).find(".remove-attachment").hide();
			});
			$(".order-attachment li ._attachmentPreview").on("click", _previewAttachment);
			$(".order-attachment li span._removeAttachment").on("click", _removeFileItem);
			
			function _previewAttachment() {
				var jThis = $(this);
				jAt.find("._attachmentModal").remove();
				jAt.append(
					'<div class="_attachmentModal modal fade" role="dialog">\
					    <div class="modal-dialog modal-lg">\
					      <div class="modal-content">\
					        <div class="modal-header">\
					          <button type="button" class="close" data-dismiss="modal">&times;</button>\
					          <h4 class="modal-title">' + jThis.attr("fileName") + '</h4>\
					        </div>\
					        <div class="modal-body">\
					          <iframe src="http://docs.google.com/gview?url=http://' + location.host + '/NapkinStudio/orders/1402130001/order_attachments/' + jThis.attr("fileName") + '&embedded=true" style="width:100%; height: 100%; max-height: 700px;" frameborder="0"></iframe>\
					        </div>\
					      </div>\
					    </div>\
					  </div>');
			}
			
			function _removeFileItem() {
				var jThis = $(this);
				var fileName = jThis.attr("fileName");
				$.ajax({
				    url: orderId + "/order_attachments/remove/" + fileName,
				    type: 'DELETE',
				    success: function(result) {
				        console.log("ok");
				        napkin.buildFileInfoList();
				    }
				});
			}
		});
		
		function _getDateFormatForAttachment(date) {
			var monthNames = [
				              "Jan", "Feb", "Mar",
				              "Apr", "May", "Jun", "Jul",
				              "Aug", "Sep", "Oct",
				              "Nov", "Dec"
				            ];

            var day = date.getDate();
            var monthIndex = date.getMonth();
            var year = date.getFullYear();

            var weekday = new Array(7);
            weekday[0]=  "Sunday";
            weekday[1] = "Monday";
            weekday[2] = "Tuesday";
            weekday[3] = "Wednesday";
            weekday[4] = "Thursday";
            weekday[5] = "Friday";
            weekday[6] = "Saturday";

            var today = new Date();
            var dayStr;
//            console.log("");
//            console.log(Math.round(today / (1000 * 60 * 60 * 24)));
//            console.log(Math.round(date / (1000 * 60 * 60 * 24)));
            if(Math.round(today / (1000 * 60 * 60 * 24)) === Math.round(date / (1000 * 60 * 60 * 24))) {
            	dayStr = "Today";
            } else {
            	dayStr = weekday[date.getDay()];
            }
            var minutes = date.getMinutes();
            if(date.getMinutes() < 10) {
            	minutes = "0" + minutes;
            }
            
            return dayStr + " " + monthNames[monthIndex] + " " + day + ". " + date.getHours() + ":" + minutes;
		}
		
	};
	
	napkin.uploadFile = function(){
        var orderId=$('#fileupload').attr("orderId");
       $('#fileupload').fileupload({
            dataType: 'json',
            done: function (e, data) {
            	var jDiv1 = $('<div class="row"  style="display: inline">'+data.result.fileName+' '+data.result.fileSize+'</div>');
            	var jInp = $('<input  value="Delete" name="'+data.result.fileName+'"  type="button" class=" del-file-but btn btn-danger btn-sm">');
            	jDiv1.append(
                        ' <div class="floatRight">' +
                        '<input  value="Delete" name="'+data.result.fileName+'"  type="button" class=" del-file-but btn btn-danger btn-sm">' +
                        '</div>');
            	$('#foruploadedfiles').append(jDiv1);
            	
            	jDiv1.find(".del-file-but").on("click", deleteFile);
               napkin.buildFileInfoList();
            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#progress .bar').css(
                        'width',
                        progress + '%'
                );
            }
        });
        
        function deleteFile(){
            console.log("dqwdqw");
            console.log($(this));
            var thisElement=$(this);
            $.ajax({
                url: '../remove-file/'+orderId+'/'+thisElement.attr("name"),
            success: function(result){
            console.log("dqwdqw"); 
            napkin.buildFileInfoList();
            thisElement.parent().parent().remove();
            }});
       	
        };
	};
	
	
	napkin.comFilApproveSubmit = function(){
		
	}
	
	
	
	
})(napkin);


//function deleteFile(){
//    console.log("dqwdqw");
//    console.log($(this));
//    var thisElement=$(this);
//    $.ajax({
//        url: '../remove-file/'+$('#fileupload').attr("orderId")+'/'+thisElement.attr("name"),
//    success: function(result){
//    console.log("dqwdqw"); 
//    napkin.buildFileInfoList();
//    thisElement.parent().parent().remove();
//    }});
	
//};