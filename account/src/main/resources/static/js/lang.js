/**
 * 国际化js文本处理, 包括相关的属性和方法
 */
var lang = Cookies.get('cent_lang');
var global = {"defaultLanguage" : lang ? lang : "zh_CN"};
function setLanguage(lang) {
	global.defaultLanguage = lang;
	Cookies.set('cent_lang', lang);
}
function getI18n(key) {
	return account.login[global.defaultLanguage][key];
}

var account = {login:[]};
account.login['zh_CN']={
	userCantEmpty: '用户名不能为空',
	passwordCantEmpty: '密码不能为空',
	loginError: '登录出错'
};

account.login['en_US']={
	userCantEmpty: 'User Cannot be Empty',
	passwordCantEmpty: 'Password Cannot be Empty',
	loginError: 'Login Error'
};
