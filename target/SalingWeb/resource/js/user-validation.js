$(() => {
    page.dialogs.element.frmCreateUser.validate({
        onkeyup: function(element) {$(element).valid()},
        onclick: false,
        rules: {
            nameCre: {
                required: true,
                minlength: 5,
                maxlength: 50
            }
        },
        messages: {
            nameCre: {
                required: "Bắt buộc nhập tên đầy đủ",
                minlength: "Hãy nhập tối thiểu 5 ký tự",
                maxlength: "Hãy nhập tối đa 50 ký tự"
            }
        },
        submitHandler: function() {
            page.dialogs.commands.createUser();
        }
    });

    // $("#frmUpdateCustomer").validate({
    //     onkeyup: function(element) {$(element).valid()},
    //     onclick: false,
    //     rules: {
    //         upFullName: {
    //             required: true,
    //             minlength: 5,
    //             maxlength: 50
    //         }
    //     },
    //     messages: {
    //         upFullName: {
    //             required: "Bắt buộc nhập tên đầy đủ",
    //             minlength: "Hãy nhập tối thiểu 5 ký tự",
    //             maxlength: "Hãy nhập tối đa 50 ký tự"
    //         }
    //     },
    //     submitHandler: function() {
    //         updateCustomer();
    //     }
    // });
});