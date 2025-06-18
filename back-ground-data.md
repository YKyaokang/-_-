资源管理系统接口文档
1. 员工管理接口 (/api/employee)
1.1 注册
接口路径：POST /api/employee/register
请求体：{
    "username": "string",    // 用户名
    "password": "string",    // 密码
    "name": "string",        // 姓名
    "phone": "string",       // 电话
    "department": "string",  // 部门
    "position": "string"     // 职位
}
Apply
响应体：{
    "code": 200,
    "data": {
        "id": "long",
        "username": "string",
        "name": "string",
        "phone": "string",
        "department": "string",
        "position": "string",
        "role": "string",
        "createTime": "datetime",
        "updateTime": "datetime"
    },
    "message": "string"
}
Apply
1.2 登录
接口路径：POST /api/employee/login
请求体：{
    "username": "string",
    "password": "string"
}
Apply
响应体：同注册接口
1.3 登出
接口路径：POST /api/employee/logout
响应体：{
    "code": 200,
    "data": null,
    "message": "string"
}
Apply
1.4 获取当前用户信息
接口路径：GET /api/employee/current
响应体：同注册接口

2. 资源管理接口 (/api/resource)
2.1 获取资源列表
接口路径：GET /api/resource/list
请求参数：
pageNum: 页码（默认1）
pageSize: 每页数量（默认10）
响应体：{
    "code": 200,
    "data": {
        "records": [{
            "id": "long",
            "category": "string",
            "categoryCode": "string",
            "name": "string",
            "quantity": "integer",
            "status": "string",
            "createTime": "datetime",
            "updateTime": "datetime"
        }],
        "total": "long",
        "size": "integer",
        "current": "integer"
    },
    "message": "string"
}
Apply
2.2 获取单个资源
接口路径：GET /api/resource/{id}
响应体：同资源列表中的单个资源对象
2.3 添加资源
接口路径：POST /api/resource
请求体：{
    "category": "string",
    "categoryCode": "string",
    "name": "string",
    "quantity": "integer",
    "status": "string"
}
Apply
响应体：同单个资源对象
2.4 更新资源
接口路径：PUT /api/resource/{id}
请求体：同添加资源
响应体：同单个资源对象
2.5 删除资源
接口路径：DELETE /api/resource/{id}
响应体：{
    "code": 200,
    "data": null,
    "message": "string"
}
Apply
2.6 更新资源数量
接口路径：PUT /api/resource/{id}/quantity
请求参数：
quantity: 新的数量
响应体：{
    "code": 200,
    "data": null,
    "message": "string"
}
Apply
3. 服务评价接口 (/api/evaluation)
3.1 获取评价列表
接口路径：GET /api/evaluation/list
请求参数：
pageNum: 页码（默认1）
pageSize: 每页数量（默认10）
响应体：{
    "code": 200,
    "data": {
        "records": [{
            "id": "long",
            "employeeId": "long",
            "applicationId": "long",
            "rating": "integer",
            "comment": "string",
            "createTime": "datetime",
            "updateTime": "datetime"
        }],
        "total": "long",
        "size": "integer",
        "current": "integer"
    },
    "message": "string"
}
Apply
3.2 获取单个评价
接口路径：GET /api/evaluation/{id}
响应体：同评价列表中的单个评价对象
3.3 创建评价
接口路径：POST /api/evaluation
请求体：{
    "applicationId": "long",
    "rating": "integer",
    "comment": "string"
}
Apply
响应体：同单个评价对象
3.4 更新评价
接口路径：PUT /api/evaluation/{id}
请求体：同创建评价
响应体：同单个评价对象
3.5 删除评价
接口路径：DELETE /api/evaluation/{id}
响应体：{
    "code": 200,
    "data": null,
    "message": "string"
}
Apply
通用说明
所有接口的响应格式统一为：{
    "code": 200,      // 状态码：200表示成功，其他表示失败
    "data": {},       // 响应数据，可能是对象、数组或null
    "message": "string" // 响应消息
}
Apply
所有接口的错误处理：
当发生错误时，code不为200
message字段会包含具体的错误信息
data字段为null
分页接口说明：
pageNum：当前页码，从1开始
pageSize：每页显示数量
total：总记录数
records：当前页的数据列表
时间格式：
所有时间字段均使用ISO 8601格式
示例：2024-03-20T10:30:00
认证说明：
除登录和注册接口外，其他接口都需要在请求头中携带认证信息
认证信息格式：Authorization: Bearer {token}