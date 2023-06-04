var loginApi = Vue.resource('/shop/login');

Vue.component('login', {
        path: '/login',
        data: function () {
            return {
                id: '',
                login: '',
                password: ''
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
            '<input type="button" class="btn btn-primary w-100 py-2" value="Sign In" @click="signin">' +
            '</form>' +
            '</main>',
        methods: {
            signin: function () {
                var user = {login: this.login.trim(), password: this.password.trim()};
                loginApi.save({}, user).then(result =>
                    result.json().then(data => {
                        this.login = '';
                        this.password = '';
                    })
                )
            }
        }
    }
)

var app = new Vue({
    el: '#login',
    template:
        '<login/>'
});

