function queryData() {
	var params = $("#searchForm").toJson();
	if (params.groupId == "" && params.dataId == "") {
		$.messager.alert('提示', 'groupId和dataId不能为空!', 'warn');
		return;
	}
	$.ajax({
		url : ctp + '/flow/queryConfiguration',
		type : 'POST',
		dataType : "json",
		data : params,
		success : function(data) {
			if (data.code == 200) {
				$("#deatail_conf").val(data.result.configuration);
			} else {
				$.messager.alert('提示', data.message, 'error');
			}
		}
	});
}

function insertData() {
	var params = $("#addForm").toJson();
	$.ajax({
		url : ctp + '/flow/insertConfiguration',
		type : 'POST',
		dataType : "json",
		data : params,
		success : function(data) {
			if (data.code == 200) {
				$.messager.alert('提示', '新增成功!', 'info');
				$("#addwindow").window("close");
				clearText("addForm");
			} else {
				$.messager.alert('提示', data.message, 'warn');
			}
		}
	});
}

function updateData() {
	var params = $("#searchForm").toJson();
	params.configuration = $("#deatail_conf").val();
	if (params.groupId == "" || params.dataId == "" || params.configuration == "") {
		$.messager.alert('提示', 'groupId&&dataId&&配置详情不能为空!', 'warn');
		return;
	}
	$.ajax({
		url : ctp + '/flow/updateConfiguration',
		type : 'POST',
		dataType : "json",
		data : params,
		success : function(data) {
			if (data.code == 200) {
				$.messager.alert('提示', '修改成功!', 'info');
			} else {
				$.messager.alert('提示', data.message, 'warn');
			}
		}
	});
}

function deleteData() {
	var params = $("#searchForm").toJson();
	if (params.groupId == "" || params.dataId == "") {
		$.messager.alert('提示', 'groupId和dataId不能为空!', 'warn');
		return;
	}
	$.messager.confirm('确认', '警告：确认删除groupId=' + params.groupId + "dataId="
			+ params.dataId + "配置文件吗?", function(r) {
		if (r) {
			$.ajax({
				url : ctp + '/flow/deleteConfiguration',
				type : 'POST',
				dataType : "json",
				data : params,
				success : function(data) {
					if (data.code == 200) {
						$("#deatail_conf").val("");
						$.messager.alert('提示', '删除成功!', 'info');
					} else {
						$.messager.alert('提示', data.message, 'warn');
					}
				}
			});
		}
	});
}

function preInsertData(windowName) {
	$("#" + windowName).window("open");
}

function clearText(formName1, formName2) {
	$("#" + formName1).form("clear");
	$("#" + formName2).form("clear");
}