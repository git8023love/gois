var isInit = true;
var table;
var config = {
    language : {
        "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
        "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
    },
    oAria: {
        "sSortAscending": ": 以升序排列此列",
        "sSortDescending": ": 以降序排列此列"
    }
}
}
$(function() {
    indexHome.initIndexHome();
    /**
     * 回到首页
     * @param obj
     */
    breakIndex = function(obj){
        isInit = true;
        window.location.href='index.ftl?act=10201';
        indexHome.initIndexHome();
    };

    /**
     * 加载菜单
     */
    $.ajax({
        cache: true,
        type: "POST",
        url:'/resource/loadMenu',
        dataType:"json",
        success: function(data) {
            if(data.code == 0){
                var menuList = data.result;
                var html="";
                for(var i=0;i<menuList.length;i++){
                    html+='<li id="addMenu'+menuList[i].id+'"><a onclick= "menu.putChildMenu(this)" data-toggle="collapse" class="collapsed" valueId="'+menuList[i].id+'"></i><span>'+menuList[i].name+'</span><i class="icon-submenu lnr lnr-chevron-left"></i></a></li>'
                }
                $("#loadMenu").append(html);
            }
        }
    });


});

var indexHome = {
    initIndexHome : function(){
        if(isInit) {
            isInit = false;
            var data, options;
            // headline charts
            data = {
                labels: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日'],
                series: [
                    [23, 29, 24, 40, 25, 24, 35],
                    [14, 25, 18, 34, 29, 38, 44],
                ]
            };
            options = {
                height: 300,
                showArea: true,
                showLine: false,
                showPoint: false,
                fullWidth: true,
                axisX: {
                    showGrid: false
                },
                lineSmooth: false,
                chartPadding: {
                    left: 0,
                    right: 0
                }
            };
            new Chartist.Line('#headline-chart', data, options);


            // visits trend charts
            data = {
                labels: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                series: [{
                    name: 'series-real',
                    data: [200, 380, 350, 320, 410, 450, 570, 400, 555, 620, 750, 900],
                }, {
                    name: 'series-projection',
                    data: [240, 350, 360, 380, 400, 450, 480, 523, 555, 600, 700, 800],
                }]
            };
            options = {
                fullWidth: true,
                lineSmooth: false,
                height: "270px",
                low: 0,
                high: 'auto',
                series: {
                    'series-projection': {
                        showArea: true,
                        showPoint: false,
                        showLine: false
                    },
                },
                axisX: {
                    showGrid: false,

                },
                axisY: {
                    showGrid: false,
                    onlyInteger: true,
                    offset: 0,
                },
                chartPadding: {
                    left: 20,
                    right: 25
                }
            };
            new Chartist.Line('#visits-trends-chart', data, options);


            // visits chart
            data = {
                labels: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日'],
                series: [
                    [6384, 6342, 5437, 2764, 3958, 5068, 7654]
                ]
            };
            options = {
                height: 300,
                axisX: {
                    showGrid: false
                },
            };
            new Chartist.Bar('#visits-chart', data, options);


            // real-time pie chart
            var sysLoad = $('#system-load').easyPieChart({
                size: 130,
                barColor: function (percent) {
                    return "rgb(" + Math.round(200 * percent / 100) + ", " + Math.round(200 * (1.1 - percent / 100)) + ", 0)";
                },
                trackColor: 'rgba(245, 245, 245, 0.8)',
                scaleColor: false,
                lineWidth: 5,
                lineCap: "square",
                animate: 800
            });
            var updateInterval = 3000; // in milliseconds
            setInterval(function () {
                var randomVal;
                randomVal = getRandomInt(0, 100);

                //sysLoad.data('easyPieChart').update(randomVal);
                //sysLoad.find('.percent').text(randomVal);
            }, updateInterval);

            function getRandomInt(min, max) {
                return Math.floor(Math.random() * (max - min + 1)) + min;
            }
        }
    }
}

var menu = {
    /**
     * 加载子菜单
     * @param obj
     */
    putChildMenu : function(obj) {
        var value = $(obj).attr('valueId');
        $(obj).attr('href','#menu'+value);
        $.ajax({
            cache: true,
            type: "POST",
            async: false,
            url:'/resource/loadChildMenu?parentId='+value,
            dataType:"json",
            success: function(data) {
                if(data.code == 0){
                    var menuList = data.result;
                    var html='<div id="menu'+value+'" class="collapse"><ul class="nav">';//
                    for(var i=0;i<menuList.length;i++){
                        html+='<li><a href="javascript:void(0)" onclick="menu.loadUserManger(\''+menuList[i].resurl+'\')"  class="">'+menuList[i].name+'</a></li>'
                    }
                    html+='</dl></div>'
                    $("#addMenu"+value).append(html)
                }
            }
        });

    },

    loadUserManger : function(resurl) {
        isInit = false;
        $.ajax({
            url: resurl,
            cache: false,
            async: false,
            success: function (html) {
                $(".main").empty();
                $(".main").append(html);
                initDate.loadDate();
            }
        })
    }
}

var initDate = {
    loadDate : function () {
        if($("#user_maanger").length > 0) {
            userManager.loadUserList();
        }
    }
}

var userManager = {
    checkChild : function (obj) {
        var check = $(obj).prop("checked");
        $(".checkChild").prop("checked", check);
    },

    //封装查询参数
    getQueryCondition : function (data){
        var param = {};
        //组装排序参数
        param.id = $("#id-search").val();//查询条件
        param.username = $("#name-search").val();//查询条件
        param.enable = $("#status-search").val();//查询条件
        //组装分页参数
        param.start = data.start;
        param.length = data.length;
        param.draw = data.draw;
        return param;
    },

    /**
     * 加载列表
     */
    loadUserList : function(){
        $('#dataTable').DataTable({
            "dom": '<"top"i>rt<"bottom"flp><"clear">',
            "searching" : false,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "serverSide": true,
            "processing": true,
            language: config.language,
            oAria: config.oAria,
            destroy: true,
            ajax : function(data, callback, settings) {
                var param = userManager.getQueryCondition(data);
                $.ajax({
                    type: "GET",
                    url: '/user/userList',
                    cache : false,
                    data: param,
                    dataType: "json",
                    success: function(datas) {
                        if(datas.code == 0){
                            var returnData = {};
                            var result = datas.result;
                            returnData.draw = result.draw;
                            returnData.recordsTotal = result.recordsTotal;
                            returnData.recordsFiltered = result.recordsFiltered;
                            returnData.data = result.data;
                            callback(returnData);//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        }
                        },
                        error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert("查询失败");
                    }
                });
            },
            "columns": [
                {
                    "sClass": "text-center",
                    "data": "id",
                    "render": function (data, type, full, meta) {
                        return '<input type="checkbox"  class="checkChild"  value="' + data + '" />';
                    },
                    "bSortable": false
                },
                {"data": "id" },
                {"data": "username" },
                {"data": "enable" },
            ],
            columnDefs:[
                {"orderable": false, "targets": 1 },
                {"orderable": false, "targets": 2 },
                {
                    "orderable": false,
                    "render": function(data, type, row) {
                        if(data==1){
                            return "开启";
                        }else{
                            return "关闭";
                        }
                    },
                    "targets": 3
                },
            ],

        });
    },

    /**
     * 添加用户
     * @returns {*}
     */
    addUser : function () {
        var username = $("#username").val();
        var password = $("#password").val();
        if (username == "" || username == undefined || username == null) {
            return layer.msg('用户名不能为空', function() {
                //关闭后的操作
            });
        }
        if (password.length<6||password.length>=16) {
            return layer.msg('密码长度为6-16', function() {
                //关闭后的操作
            });
        }
        $.ajax({
            cache: true,
            type: "POST",
            url:'/user/addUser',
            data:$('#userForm').serialize(),// 你的formid
            async: false,
            success: function(data) {
                if (data.code == 0) {
                    layer.msg(data.msg);
                    userManager.loadUserList();
                } else {
                    layer.msg(data.msg);
                    $('#addUser').modal('hide')
                }
            }
        });

    },

    /**
     * 删除用户
     */
    delUserByID : function () {
        var id = $(".checkChild:checked").val();
        if ($(".checkChild:checked").length < 1) {
            layer.msg('请选择一条数据');
            return;
        }
        if ($(".checkChild:checked").length > 1) {
            layer.msg('一次只能修改一条数据');
            return;
        }
        layer.confirm('您确定要删除该用户吗？', {
            btn: ['确认','取消'] //按钮
        }, function() {
            $.ajax({
                cache: true,
                type: "POST",
                url:'/user/deleteUser',
                data:{id:id},
                async: false,
                success: function(data) {
                    if (data.code == 0){
                        layer.msg(data.msg);
                        userManager.loadUserList();
                    }else {
                        layer.msg(data.msg);
                    }
                }
            });
        });
    },

    /**
     * 分配加色
     */
    allotRole : function () {
        var id = $(".checkChild:checked").val();
        if ($(".checkChild:checked").length < 1) {
            layer.msg('请选择一条数据');
            return;
        }
        if ($(".checkChild:checked").length > 1) {
            layer.msg('一次只能修改一条数据');
            return;
        }
        $.ajax({
            async:false,
            type : "POST",
            data:{uid:id},
            url: '/role/roleList',
            dataType:'json',
            success: function(data){
               if (data.code == 0){
                   var result = data.result;
                   $("#boxRoleForm").empty();
                   var htm = "<input type='hidden' name='userid' value='"+id+"'>";
                   for(var i=0;i<result.length;i++){
                       htm += "<div class='checkbox'><label><input type='checkbox'class='roleCheckedBox' name='roleid' value='"+result[i].id+"'";
                       if(result[i].available==1){
                           htm += " checked='checked'";
                       }
                       htm +="/>"+result[i].description+"</label></div>";
                   }
                   $("#boxRoleForm").append(htm);
               }
            }
        });

        $('#selectRole').modal();
    },

    /**
     * 保存角色
     */
    saveUserRoles : function () {
        if ($(".roleCheckedBox:checked").length > 1) {
            layer.msg('一个用户只能分配一个角色');
            return;
        }
        $.ajax({
            cache: true,
            type: "POST",
            url:'/user/saveUserRoles',
            data:$('#boxRoleForm').serialize(),// 你的formid
            async: false,
            success: function(data) {
                if (data.code == 0) {
                    layer.msg(data.msg);
                    $('#selectRole').modal('hide');
                } else {
                    layer.msg(data.msg);
                    $('#selectRole').modal('hide');
                }
            }
        })
    }

}
