var validator = new FormValidator('form_saludo', [
{
    name: 'nombre',
    rules: 'required'
},
{
    name: 'dni',
    rules: 'required|integer|exact_length[8]'
}, {
    name: 'correo',
    rules: 'required|valid_email',
    depends: function() {
        return Math.random() > .5;
    }
},
{
    name: 'mensaje',
    rules: 'required|max_length[512]'
}],
function(errors, event) {
    if (errors.length > 0) {
        console.log(errors);
        $("ul#lista_errores").empty();
        for (let i = 0; i < errors.length; i++) {
            let _error = errors[i];
            $("ul#lista_errores").append("<li>" + _error.message + "</li>");
        }
        $("div#errores").show();
         window.setTimeout(() => {$("div#errores").hide()}, 5*1000);
    } else {
        $("ul#lista_errores").empty()
        $("div#errores").hide()
    }
});