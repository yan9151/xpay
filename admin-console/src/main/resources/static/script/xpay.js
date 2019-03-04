function addTab(title, url) {
    var element = layui.element;

    // 新增加 tab
    element.tabAdd('tab', {
        title: title,
        content: '<iframe id="' + title + '" frameborder="0" height="400px" scrolling="yes" width="100%" src="' + url + '"></iframe>',
        id: title
    });
    //切换tab
    element.tabChange('tab', title);
    element.render('tab');
}

/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 *
 * @param num 数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
            num.substring(num.length - (4 * i + 3));
    return (((sign) ? '' : '-') + num + '.' + cents);
}

/*
    时间戳转日期格式（YYYY-MM-dd HH:mm:ss）
 */
function formatTimestamp(timestamp) {
    var unixtimestamp = new Date(timestamp);
    var year = 1900 + unixtimestamp.getYear();
    var month = "0" + (unixtimestamp.getMonth() + 1);
    var date = "0" + unixtimestamp.getDate();
    var hour = "0" + unixtimestamp.getHours();
    var minute = "0" + unixtimestamp.getMinutes();
    var second = "0" + unixtimestamp.getSeconds();
    return year + "-" + month.substring(month.length - 2, month.length) + "-" + date.substring(date.length - 2, date.length) + " " + hour.substring(hour.length - 2, hour.length) + ":" + minute.substring(minute.length - 2, minute.length) + ":" + second.substring(second.length - 2, second.length);
}

/**
 * 返回日期的年月日
 * @param date
 * @returns {string}
 */
function formatDate(date) {
    var year = date.getFullYear();
    var method = date.getMonth() + 1;
    if (method < 10) {
        method = "0" + method;
    }
    var day = date.getDate();
    if (day < 10) {
        day = "0" + day;
    }

    return year + "-" + method + "-" + day;
}
