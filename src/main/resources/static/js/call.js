var nameList = []
var myTime = null;
//JS
layui.use(['element', 'layer', 'util'], function () {
    var element = layui.element
        , layer = layui.layer
        , util = layui.util
        , $ = layui.$;
});
layui.use(['form', 'jquery', 'upload'], function () {
    var upload = layui.upload;
    var form = layui.form, $ = layui.$;
    //动态添加下拉框 同时可以设置默认值
    $.ajax({
        url: '/classes/getAllClasses',
        dataType: 'json',
        //type:'post',
        success: function (data) {
            $.each(data, function (index, item) {
                //option 第一个参数是页面显示的值，第二个参数是传递到后台的值
                $('#classes').append(new Option(item.className, item.classId));//往下拉菜单里添加元素
                //设置value（这个值就可以是在更新的时候后台传递到前台的值）为2的值为默认选中
                $('#classes').val(0);
            })

            form.render(); //更新全部表单内容
            //form.render('select'); //刷新表单select选择框渲染
        }
    });
    form.on('select(classes)', function (data) {
        console.log(data.value)
        $.ajax({
            url: '/student/getNameList',
            dataType: 'json',
            data: {
                classId: function () {
                    return $('#classes').val();
                }
            },
            //type:'post',
            success: function (data) {
                nameList = data.data
            }
        });
    });

    //执行实例
    var uploadInst = upload.render({
        elem: '#addStudent' //绑定元素
        , url: '/student/addStudents' //上传接口
        , data: {
            classId: function () {
                return $('#classes').val();
            }
        }
        , accept: 'file'
        , done: function (res) {
            layer.msg('上传成功', {icon: 1});
        }
        , error: function () {
        }
    });
    $("#call").click(function () {
        var bt = window.document.getElementById("call");
        if (myTime == null) {
            if ($('#classes').val() === null) {
                layer.msg("请选择班级")
            } else {
                if (nameList.length === 0) {
                    layer.msg("此班级没有学生")
                } else {
                    bt.innerHTML = "停止点名";
                    showName()
                }
            }
        } else {
            bt.innerHTML = "开始点名";
            clearTimeout(myTime);
            myTime = null;
        }
    });
    $("#addClass").click(function () {
        layer.open({
            type: 1
            , resize: false
            , shadeClose: true
            , area: '400px'
            , title: '添加班级'
            , content: ['<ul class="layui-form layui-form-pane" style="margin: 20px;">'
                , '<li class="layui-form-item">'
                , '<label class="layui-form-label">班级名称</label>'
                , '<div class="layui-input-block">'
                , '<input class="layui-input" lay-verify="required" name="field1">'
                , '</li>'
                , '<li class="layui-form-item" style="text-align:center;">'
                , '<button type="submit" lay-submit lay-filter="*" class="layui-btn">添加</button>'
                , '</li>'
                , '</ul>'].join('')
            , success: function (layero, index) {
                layero.find('.layui-layer-content').css('overflow', 'visible');

                form.render().on('submit(*)', function (data) {
                    // layer.msg(JSON.stringify(data.field), {icon: 1});
                    //layer.close(index); //关闭层
                    $.ajax({
                        url: '/classes/addClass',
                        dataType: 'json',
                        type: 'post',
                        data: {
                            className: function () {
                                return data.field.field1;
                            }
                        },
                        success: function (data) {
                            if (data.code === -1) {
                                layer.msg("班级已存在", {icon: 2})
                            } else {
                                layer.msg("添加成功!", {icon: 1})
                                layer.close(index);
                            }
                        }
                    });
                });
            }
        });
    });

});

function showName() {
    var box = window.document.getElementById("box");
    var num = Math.floor((Math.random() * 100000)) % nameList.length;
    box.innerHTML = nameList[num];
    myTime = setTimeout("showName()", 10);
}

function sleep(d) {
    for (var t = Date.now(); Date.now() - t <= d;) ;
}