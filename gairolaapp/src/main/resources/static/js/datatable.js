$(document).ready( function () {
    var table = $('#employeesTable').DataTable({
           "sAjaxSource": "/emp/employees",
           "sAjaxDataProp": "",
           "order": [[ 0, "asc" ]],
           "aoColumns": [
               { "mData": "id"},
               { "mData": "firstName" },
                 { "mData": "lastName" },
                 { "mData": "email" }
                 
           ]
    })
});