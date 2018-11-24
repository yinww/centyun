function saveProduct(saveType) {
	if (checkForm()) {
		var arr = {};
		arr.name = $('#name').val();
		arr.code = $('#code').val();
		arr.version = $('#version').val();
		arr.publishTime = $('#publishTime').val();
		arr.productManager = $('#productManager').val();
		arr.note = $('#note').val();
		if(saveType == 2) { // 如果是编辑则需要给id赋值
			arr.id = $('#id').val();
		}
		$.ajax({
			url : '/product/save-product',
			type : 'post',
			dataType : 'json',
			data : arr,
			success : function(data) {
				if (data.status == 200) {
					window.location.href = saveType == 1 ? "/product/add.html" : "/product/index.html";
				} else {
					$('#errMsg').html(data.msg);
				}
			},
			error : function(data) {
				console.log(data);
				$('#errMsg').text(getI18n('saveError'));
			}
		});
	}
}

function checkForm() {
	var username = $('#name').val();
	if (null == username || '' == username) {
		$('#errMsg').html(getI18n('userCantEmpty'));
		return false;
	}

	var code = $('#code').val();
	if (null == code || '' == code) {
		$('#errMsg').html(getI18n('codeCantEmpty'));
		return false;
	}
	return true;
}

function gotopage(page) {
	window.location.href = page;
}

function editProduct() {
	window.location.href="/product/edit.html?id="+$('#id').val();
}
