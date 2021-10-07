class App {
    static showAlert(icon, title) {
        Swal.fire({
            icon: icon,
            title: title,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1200
        })
    }

    static getSizeCart() {
        $.ajax({
            type: "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: "http://localhost:8080/api/sale/size-cart/"
        }).done((data) => {
            console.log(data)
            $("#sizeCart").html(data);
            // alert("SUCCESS");
        }).fail((jqXHR) => {
            console.log(jqXHR);
        });
    }
}