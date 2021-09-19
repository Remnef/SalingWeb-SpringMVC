class User {
    constructor(id, name, email, balance, province_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.province_id = province_id;
    }
}

class Province {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
}

let BASE_API_USER = "http://localhost:8080/api/users";
let BASE_API_PROVINCE = "https://vapi.vnappmob.com/api/province";
let BASE_API_PROVINCE_DISTRICT = "https://vapi.vnappmob.com/api/province/district"

let page = {
    urls: {
          getAllUser: BASE_API_USER,
          getUser: BASE_API_USER + '/id/',
          createUser: BASE_API_USER,
          updateUser: BASE_API_USER + '/id/',
          deleteUser: BASE_API_USER + '/id/',

          getAllProvince: BASE_API_PROVINCE,
          getAllDistrict: BASE_API_PROVINCE_DISTRICT + "/id/"
    },
    loadData: {},
    element: {},
    commands: {},
    dialogs: {
      loadData: {},
      element: {},
      commands: {},
      close: {}
    },
    initializeControlEvent: () => {}
}

page.element.showListUser = $(".table tbody");

page.element.btnShowCreateUser = $('#add-btn');

page.dialogs.element.showListProvince = $('.form-group select[name="provinceCre"]');

page.dialogs.element.modalCreateUser = $("#modalCreateUser");

page.dialogs.element.frmCreateUser = $("#frmCreateUser");

page.dialogs.element.name = $('input[name="nameCre"]');
page.dialogs.element.country = $('select[name="provinceCre"]');
page.dialogs.element.email = $('input[name="emailCre"]');
page.dialogs.element.btnCreateUser = $("#btnCreateUser");

let user = new User();
let province = new Province();
let template = $.validator.format($.trim($("#template").val()));
let templateProvince = $.validator.format($.trim($("#templateProvince").val()));

page.commands.addRow = () => {
    page.element.showListUser.prepend($(template(user.id, user.name, user.email, user.province_id, user.balance)));
}

page.dialogs.commands.addOption = () => {
    page.dialogs.element.showListProvince.prepend($(templateProvince(province.id, province.name)));
}

page.loadData.showListUsers = () => {
    return $.ajax({
        url: page.urls.getAllUser,
        method: "GET"
    }).done((data) => {
        page.element.showListUser.empty();

        $.each(data, (index, item) => {
            user = item;
            user.province_id = province.id;
            page.commands.addRow();
        });
    });
}

page.loadData.showListProvinces = () => {
    return $.ajax({
        method : "GET",
        url: page.urls.getAllProvince,

    }).done((data) => {
        page.dialogs.element.showListProvince.empty();

        $.each(data.results, (index, item) => {
            // province = item;
            province.id = item.province_id;
            province.name = item.province_name;

            page.dialogs.commands.addOption();
        });
    });
}

page.commands.showCreateModel = () => {
    page.dialogs.element.modalCreateUser.modal('show');
}

page.dialogs.commands.createUser = () => {
    user.name = page.dialogs.element.name.val();
    user.country = page.dialogs.element.country.find(":selected").val();
    user.email = page.dialogs.element.email.val();

    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: page.urls.createUser,
        data: JSON.stringify(user)
    }).done((data) => {
        user = data;

        page.commands.addRow();

        page.dialogs.element.modalCreateUser.modal('hide');

    });
}

page.initializeControlEvent = () => {
    page.element.btnShowCreateUser.on("click", () => {
        delete user.id;
        delete user.balance;
        page.commands.showCreateModel();
    });

    page.dialogs.element.btnCreateUser.on("click", () => {
        page.dialogs.element.frmCreateUser.submit();
    });
}

$(document).ready(function () {
  page.loadData.showListUsers().then(function () {
      page.initializeControlEvent();
  });
  page.loadData.showListProvinces();
});
