"use strict";
var napkin = napkin || {};

(function(napkin) {


	var _bean = {};
	var mjUpLi = {};
	var jWrapImgPr = $("<div id='proofprevdiv'style='position:relative;  display: none;'></div>");
	var jShowAtBlock = $("<div></div>"),
		jRemoveAllFiles = $('<span class="btn btn-link fileinput-button">\
 								<i class="glyphicon glyphicon-remove"></i>\
 								<span>Remove all</span>\
 								<input type="button">\
 							</span>');
	$("#order-attachment").append(jShowAtBlock);
	
	
	napkin.buildFileAttachmentBlock = function() {
		var jAt = $("#order-new-attachment");
		
		var jAddAtBlock = $("<div></div>"),
			jChoseFiles = $('<span class="glyphicon glyphicon-plus-sign btn-custom fileinput-button">\
								<input class="glyphicon glyphicon-plus-sign" type="file" name="files[]" multiple="">\
                			</span>'),
			jDDZone = $("<div class='drag-n-drop-zone dropzone'></div>");

		jAt.append(jAddAtBlock);// .append(jShowAtBlock);
		var jUpList = $("<ui class='list-group'></ui>"), jUpLi;
		
		jRemoveAllFiles.hide();
		
		jAddAtBlock.append(jDDZone);
		jDDZone.append(jChoseFiles);
		jDDZone.append(jRemoveAllFiles);
		jDDZone.append("<p class='drag-n-drop-file-text'>Voeg bijlagen toe...</p>");
		
		jAddAtBlock.append(jUpList);
		
		var jfileInfo, jPb;
		
		jChoseFiles.fileupload({
			url: location.href + "/save-file-to-tmp/",
			dropZone: jDDZone,
			formAcceptCharset: 'utf-8',
			add: function (e, data) {
	        	jUpLi = $("<li class='list-group-item'></li>");
				jUpList.append(jUpLi);
				jfileInfo = $("<div class='file-info'></div>");
				jUpLi.append(jfileInfo);
				jPb = $("<div class='progress-bar'></div>");
				jUpLi.append(jPb);
				
				var fileName;
				var jIconDeleteFile = $("<span class='remove-icon-align remove-attachment glyphicon glyphicon-remove'></span>"),
					jIconFileStatus = $('<i class="file-upload-status fa fa-refresh fa-spin-custom"></i>');
				
	            $.each(data.files, function (index, file) {
	            	fileName = file.name;
	            	if(!/(\.|\/)(gif|jpe?g|png|svg|txt|docx?)$/i.test(fileName)){
	            		console.log("It's not apropriate file!");
	            	} else {
	            		console.log("File has right extension!");
	            	}
	                console.info('Added file: ' + file.name);
	                jIconDeleteFile.attr("fileName", file.name);
	                jfileInfo.append(jIconDeleteFile);
	                jfileInfo.append("<p class='file-info-text'>" + file.name + "  " + _formatFileSize(file.size) + "  " + _getDateFormatForAttachment(file.lastModifiedDate) + "</p>");
	                jfileInfo.append(jIconFileStatus);
	                mjUpLi[file.name] = jUpLi;
	            });
	            
	            jUpLi.hover(function(e) {
					$(this).find(".remove-attachment").show();
				}, function(e) {
					$(this).find(".remove-attachment").hide();
				});
	            jIconDeleteFile.on("click", _removeTmpFileItem);
	            
	            jPb.css("width", "0%");
	            data.submit()
		            .success(function(result, textStatus, jqXHR) {
		            	jIconFileStatus.removeClass("fa fa-refresh fa-spin-custom").addClass("file-upload-success glyphicon glyphicon-ok-circle");
			        })
			        .error(function (jqXHR, textStatus, errorThrown) {
			        	jIconFileStatus.removeClass("fa fa-refresh fa-spin-custom").addClass("file-upload-error glyphicon glyphicon-remove-circle");
			        })
			        .complete(function (result, textStatus, jqXHR) {
			        	jRemoveAllFiles.show();
			        })
	        },
			start: function(e, data) {
				
				$.each(data.files, function (index, file) {
		            alert('Dropped file: ' + file.name);
		        });
				console.log('Uploads started');
			},
			progress: function (e, data) {
				var progress = parseInt(data.loaded / data.total * 100, 10);
				$.each(data.files, function (index, file) {
					$(mjUpLi[file.name]).find(".progress-bar").css("width", progress + "%");
				});
			}
		});
		
		
		jRemoveAllFiles.on("click", _removeAllTmpAttachments);
		
		function _removeTmpFileItem() {
			var jThis = $(this);
			var fileName = jThis.attr("fileName");
			console.log(fileName);
			$.ajax({
				url: location.href + "/order_attachments/remove_temp/" + fileName,
				type: 'DELETE',
				success: function (result) {
					console.log("ok");
					delete mjUpLi[fileName];
					if($.isEmptyObject(mjUpLi)) {
						jRemoveAllFiles.hide();
					}
				},
				error: function() {
					console.log("error");
					console.log(arguments);
				},
				complited: function(result) {
					console.log("completed");
					napkin.buildFileInfoList();
				}
			});
			console.log("delete " + fileName);
			jThis.closest("li").remove();
		}
		
		function _removeAllTmpAttachments() {
			var files = [];
			for(var key in mjUpLi) {
				files.push(key);
			}
			if(!files.length) {
				console.info("No attachments to delete!");
				return;
			}
			$.ajax({
			    url: location.href + "/order_attachments/remove_all_temps",
			    type: 'POST',
			    data: {
			    	files: files
			    },
			    success: function(result) {
			        console.log("ok");
			        if(Array.isArray(result)) {
			        	for(var x = 0; x < result.length; ++x) {
			        		mjUpLi[result[x]].remove();
			        		delete mjUpLi[result[x]];
			        	}
			        }
			        if($.isEmptyObject()) {
			        	jRemoveAllFiles.hide();
			        }
			        napkin.buildFileInfoList();
			    },
			    error: function() {
			    	console.log("fail");
			    }
			});
		}
	};
	
	napkin.buildFileInfoList = function() {
		var jAt = jShowAtBlock;
		var jAtUi;
		var attachmentList;
		var name, size, lastModified, allowDelete;
		var jDwldAll;
		var jRemBtn;
		
		$.get(location.href + "/order_attachments", function(response) {
			jAt.html("");
			jAt.append("<b>Opmerkingen</b>");
			jDwldAll = $("<button type='button' class='btn btn-link'>Download all</button>");
			jAtUi = $("<ul></ul>");
			if(Array.isArray(response["attachmentList"])) {
				attachmentList = response["attachmentList"];
				if(attachmentList.length) {
					jAt.append(jDwldAll);
					jAt.append(jAtUi);
				} else {
					jAt.append(jAtUi);
					jAtUi.append("<li>Any attachment is not available!</li>");
				}
				for(let x = 0; x < attachmentList.length; ++x) {
					name = attachmentList[x].name;
					size = attachmentList[x].size;
					lastModified = new Date(attachmentList[x].lastModified);
					allowDelete = attachmentList[x].allowDelete;
					if(size < 1024) {
						size = size + " b";
					} else if (1024 <= size && size <= 1024 * 1024) {
						size = Math.round(size / 1024 * 1000)  / 1000 + " kb";
					} else if (1024 * 1024 <= size && size <= 1024 * 1024 * 1024) {
						size = Math.round(size / (1024 * 1024)* 1000) / 1000 + " mb";
					} else {
						size = " ???";
					}
					
					// jAtUi.append(
					// 	"<li>\
					// 		<i class='_attachmentPreview attachment-preview fa fa-eye' data-toggle='modal' data-target='._attachmentModal' fileName='" + name + "'></i>\
					// 		<span class='_removeAttachment remove-icon-align remove-attachment glyphicon glyphicon-remove' fileName='" + name + "'></span>\
					// 		<a href='" + location.href + "/order_attachments/" + name + "' download='" + name + "' title='download' class='_download_attachment download-attachment'>" +
					// 			"<i class='icon-align material-icons'>attach_file</i>" + name + "" +
					// 			"<p class='file-info'> - " + size + " - " + _getDateFormatForAttachment(lastModified) + "</p>" +
					// 		"</a>\
					// 	</li>");

					var jAtUiLi = $("<li></li>");
					jAtUiLi.append("<i class='_attachmentPreview attachment-preview fa fa-eye' data-toggle='modal' data-target='._attachmentModal' fileName='" + name + "'></i>");

					//TODO: Better role check
					if (allowDelete) {
						jAtUiLi.append(jRemBtn = $("<span class='_removeAttachment remove-icon-align remove-attachment glyphicon glyphicon-remove' fileName='" + name + "' data-toggle='confirmation'></span>"));
					}
					jAtUiLi.append("<a href='" + location.href + "/order_attachments/" + name + "' download='" + name + "' title='download' class='_download_attachment download-attachment'>" +
									"<i class='icon-align material-icons'>attach_file</i>" + name + "" +
									"<p class='file-info'> - " + size + " - " + _getDateFormatForAttachment(lastModified) + "</p>" +
								"</a>");
					jAtUi.append(jAtUiLi);
				}
				
			} else {
				jAtUi.append("<p>no data!</p>");
			}
			
			$(".order-attachment li").hover(function(e) {
				$(this).find(".remove-attachment").show();
			}, function(e) {
				$(this).find(".remove-attachment").hide();
			});
			$(".order-attachment li ._attachmentPreview").on("click", _previewAttachment);
			if(jRemBtn) {
				jRemBtn.on("click", _removeFileItem);
			}
			jDwldAll.on("click", _downloadAll);
			
			function _previewAttachment() {
				var jThis = $(this);
				var sPreview;
				var filename = jThis.attr("fileName");
				jAt.find("._attachmentModal").remove();
				var ext = (/[.]/.exec(filename)) ? /[^.]+$/.exec(filename)[0] : undefined;
				console.log(ext);
				if(ext === "jpg" || ext === "png") {
					sPreview = '<img src="' + location.href + "/order_attachments/" + filename + '" style="max-width: 100%; max-height: 100%;">';
				} else if(ext === "pdf") {
					sPreview = '<iframe style="width: 100%; height: 500px;" src = "/NapkinStudio/static/ViewerJS/#../../orders/1402130001/order_attachments/' + jThis.attr("fileName") + '"></iframe>';
				}
				
				jAt.append(
					'<div class="_attachmentModal modal fade" role="dialog">\
					    <div class="modal-dialog modal-lg">\
					      <div class="modal-content">\
					        <div class="modal-header">\
					          <button type="button" class="close" data-dismiss="modal">&times;</button>\
					          <h4 class="modal-title">' + jThis.attr("fileName") + '</h4>\
					        </div>\
					        <div class="modal-body">\
					          ' + sPreview + '\
					        </div>\
					      </div>\
					    </div>\
					  </div>');
			}
			
			function _removeFileItem() {
				var jThis = $(this);
				var fileName = jThis.attr("fileName");
				//TODO: Add confirm dialog.
				$.ajax({
						url: location.href + "/order_attachments/remove/" + fileName,
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
		
		function _downloadAll() {
			$(".order-attachment ._download_attachment p").trigger("click");
		}
		
	};
	 
	function initPrintProof() {
				var printproof = {};
				_bean.printproof = printproof;

					var jPP = $("#printproof");
				var jHeaderText = $('<b class="opmerkingen-wrapper">Drukproef</b>');
				var jSomeText = $("<p>Er is nog geen drukproef toegevoegd</p>");
				var jChoseFile = $('<span class="btn-custom glyphicon glyphicon-plus-sign btn-custom fileinput-button" style="margin-right: 12px;">\
 							</span>'),
					jImgInp = $('<input class="glyphicon glyphicon-plus-sign" type="file" name="files[]" accept=".svg,image/*">');
				var jImgPreview = $("<img style='max-height: 100px;' />");
				var jDltPr = $('<i class="glyphicon glyphicon-remove btn-custom" style="position: absolute; left: 0; top: 0;"></i>');
//				var jWrapImgPr = $("<div style='position:relative;  display: none;'></div>");
				var jImg = $("<img style='max-height: 500px;' />");
				var jDlt = $('<i class="glyphicon glyphicon-remove btn-custom" style="position: absolute; left: 0; top: 0;"></i>');
				var jWrapImg = $("<div style='position:relative;'></div>");


				jPP.css("margin-bottom", "10px");
				jPP.append(jHeaderText);

				if (parseInt($("#maininfotable").attr('st'))==2&&parseInt($("#maininfotable").attr('ur'))==4){
					jPP.append(jSomeText);
					jChoseFile.append(jImgInp);
					jSomeText.prepend(jChoseFile);
					jWrapImgPr.append(jImgPreview);
					jWrapImgPr.append(jDltPr);
					jPP.append(jWrapImgPr);
				}

				jWrapImg.append(jImg);
//				jWrapImg.append(jDlt);
				jPP.append(jWrapImg);
				
				
				jImgInp.fileupload({
					url: location.href + "/save-printproof-to-tmp/",
					dropZone: undefined,
					limitMultiFileUploads: 1,
			        maxFileSize: 999000,
					add: function (e, data) {
						var fileName;
						$.each(data.files, function (index, file) {
							printproof.name = file.name;
							fileName = file.name;
			            });
						
						if(!/(\.|\/)(gif|jpe?g|png|svg)$/i.test(fileName)) {
							data.abort();
							console.log("File extension is bad!");
							jWrapImgPr.hide();
							return;
						} else {
							console.log("File extension is ok!");
						}
						
						data.submit()
			            .success(function(result, textStatus, jqXHR) {
			            	console.log("success");
			            	jWrapImgPr.show();
				        })
				        .error(function (jqXHR, textStatus, errorThrown) {
				        	console.log("error");
				        })
				        .complete(function (result, textStatus, jqXHR) {
				        	console.log("completed");
				        })
		        	}		        	
				});

				function readURL(input) {
					console.log(input.files);
					jImgPreview.show();
			        if (input.files && input.files[0]) {
			            var reader = new FileReader();
			            reader.onload = function (e) {
			            	jImgPreview.attr('src', e.target.result);
			            }
			            reader.readAsDataURL(input.files[0]);
			        }
			    }

				jImgInp.change(function() {
			        readURL(this);
			    });

				jDltPr.on("click", removePPTemp);
//				jDlt.on("click", removePrintProof);


				printproof.showPrintProof = showPrintProof;

				showPrintProof();

				function showPrintProof() {
					jImg.attr("src", location.href + "/printproof");
					jImgPreview.hide();
					printproof.name = undefined;
					$.ajax({
					    url: location.href + "/printproof/exist",
					    type: 'GET',
					    success: function(result) {
					    	if(result === "ok") {
					    		jWrapImg.show();
					    	} else {
					    		jWrapImg.hide();
					    	}
					    }
					});
				}

				function removePPTemp() {
					printproof.name = undefined;
					jWrapImgPr.hide();
				}

				function removePrintProof() {
					printproof.name = "remove";
					jWrapImg.hide();
				}

			}

	function _formatFileSize(size) {
		if(size < 1024) {
			size = size + " b";
		} else if (1024 <= size && size <= 1024 * 1024) {
			size = Math.round(size / 1024 * 1000)  / 1000 + " kb";
		} else if (1024 * 1024 <= size && size <= 1024 * 1024 * 1024) {
			size = Math.round(size / (1024 * 1024)* 1000) / 1000 + " mb";
		} else {
			size = " ???";
		}
		return size;
	}
	
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
	

	function approve() {
			     $('.loading-spinner').show();
			  var urltostatus=$(this).attr('foraction');
			   var st=parseInt($(this).attr('st'));
			   var ur=parseInt($(this).attr('ur'));
			  // var pi=$(this).attr('pi');
			  var commentText=$("#statuscahngecomment").val();
			    //when DTP, printproof required
			  if (st==2&&ur==4&&$('#proofprevdiv').is(":hidden")){
                   $("#printproof p").addClass("custom-not-fielded");
				   $('.loading-spinner').hide();
				   console.log("printproof required");
				   return;
				  } else
				//when rejection comment required
			  if (urltostatus.slice(-1)=="o"&&(parseInt($(this).attr('pi'))!=3||ur!=2)&&!commentText.length){
                  $("#statuscahngecomment").addClass("custom-not-fielded");
				  $('.loading-spinner').hide();
				  console.log("rejection comment required");
				  return;
			  }
			  var files = [];
			  for(var key in mjUpLi) {
				   files.push(key);
				  }
			  if(!files.length&&!_bean.printproof.name) {
				   if ((ur==4&&st==5)||(ur==2&&st==1)){
						console.info("No attachments to approve!");
                        $(".dropzone").addClass("custom-not-fielded");
						$('.loading-spinner').hide();
						return;
				   }else{
						changeStatusAndAddComment(urltostatus,commentText);
				   }
			  }else {
				  console.info(" attachments to approve!");

				  $.ajax({
							    url: location.href + "/approve",
							    type: 'POST',
						    data: {
							    	files: files,
								    	printproof: _bean.printproof.name
							    },
						    success: function(result) {
							    	console.log("files saved");
								     changeStatusAndAddComment(urltostatus, commentText);
							        _bean.printproof.showPrintProof();
							        var atts = result['attachments'];
							        if(Array.isArray(atts)) {
								        	for(var x = 0; x < atts.length; ++x) {
									        		mjUpLi[atts[x]].remove();
									        		delete mjUpLi[atts[x]];
									        	}
								        }
							        if($.isEmptyObject(mjUpLi)) {
								        	jRemoveAllFiles.hide();
								        }
							        napkin.buildFileInfoList();
							    },
						    error: function() {
							    	console.log("fail");
							    	$('.loading-spinner').hide();
							    }
						});
				  }
			 }

			 function changeStatusAndAddComment(urltostatus,commentText) {
				 console.log("changeStatusAndAddComment");
					  $.ajax({
						   url: urltostatus,
						   type: 'POST',
						   data: {
						    commText: commentText
						   },
					   success: function(result) {
						    console.log("status scahnge and comment saved");
						    location.reload();
						   },
					   error: function() {
						    console.log("fail");
						                $('.loading-spinner').hide();
						   }
					  });
					 }

		$(document).ready(function () {
				initPrintProof();

				var jApproveBtn = $(".approve-btn");
				jApproveBtn.on("click", approve);
				if ($("#order-new-attachment")){
				    napkin.buildFileAttachmentBlock();
				   }
				napkin.buildFileInfoList();
			});
})(napkin);

