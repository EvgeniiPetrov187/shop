var registerApi = Vue.resource('/shop/register');

Vue.component('register', {
        path: '/register',
        data: function () {
            return {
                id: '',
                login: '',
                password: '',
                passwordRpt: ''
            }
        },
        template:
            '<main class="form-signin w-100 m-auto">' +
            '<form>' +
            '<h1 class="h3 mb-3 fw-normal">Please, enter</h1>' +
            '<div class="form-floating">' +
            '<input type="text" class="form-control" v-model="login" id="floatingPassword" placeholder="Login">' +
            '<label htmlFor="floatingInput">Login</label>' +
            '</div>' +
            '<div class="form-floating">' +
            '<input type="password" class="form-control" v-model="password" id="floatingPassword" placeholder="Password">' +
            '<label htmlFor="floatingPassword">Password</label>' +
            '</div>' +
            '<div class="form-floating">' +
            '<input type="password" class="form-control" v-model="passwordRpt" id="floatingPassword" placeholder="Password confirm">' +
            '<label htmlFor="floatingPassword">Password confirm</label>' +
            '</div>' +
            '<input type="button" class="btn btn-primary w-100 py-2" value="Register" @click="register">' +
            '</form>' +
            '</main>',
        methods: {
            register: function () {
                var user = {login: this.login.trim(), password: this.password.trim(), passwordRpt: this.passwordRpt.trim()};
                var success;
                registerApi.save({}, user).then(result =>
                    result.json().then(data => {
                        debugger
                        success = data.login
                        this.login = '';
                        this.password = '';
                        this.passwordRpt = '';
                    }).then(() => {
                        debugger
                        if (success) {
                           window.location.href='/shop/login.html';
                        }
                    })
                )
            }
        }
    }
)


var registration = new Vue({
    el: '#registration',
    template:
        '<register/>'
});