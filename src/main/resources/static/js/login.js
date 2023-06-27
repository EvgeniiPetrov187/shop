var loginApi = Vue.resource('/shop/login');

Vue.component('login', {
        path: '/login',
        props: ['user', 'setUser'],
        data: function () {
            return {
                id: '',
                login: '',
                password: ''
            }
        },
        template:
            '<main class="form-signin w-100 m-auto login-form">' +
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
                var userForSave = {login: this.login.trim(), password: this.password.trim()};
                loginApi.save({}, userForSave)
                    .then(result =>
                        fetch('/shop/user', {
                            method: 'GET',
                            headers: {
                                Authorization: 'Bearer ' + result.data.token
                            }
                        })
                    )
                    .then(data => data.json())
                    .then(data => {
                            this.setUser(data)
                            this.id = ''
                            this.login = ''
                            this.password = ''
                            $('.login-form').css('display', 'none')
                            $('.user-form').css('display', 'block')
                        }
                    )
            }
        }
    }
)

Vue.component('show-user', {
        props: ['id', 'login'],
        template:
            '<main class="form-signin w-100 m-auto user-form" style="display: none">' +
            '<h1 class="h3 mb-3 fw-normal">You are</h1>' +
            '<div class="form-floating">' +
            '<h3 type="text" class="form-control" id="floatingPassword">{{ id }}</h3>' +
            '<label htmlFor="floatingInput">Your ID is</label>' +
            '</div>' +
            '<div class="form-floating">' +
            '<h3 type="text" class="form-control" id="floatingPassword">{{ login }}</h3>' +
            '<label htmlFor="floatingPassword">Your login is</label>' +
            '</div>' +
            '<a href="shop.html" class="shop-link">Go to shop</a>' +
            '<input type="button" class="btn btn-primary w-100 py-2" value="Log out" @click="logout">' +
            '</main>',
        methods: {
            logout: function () {
                fetch('/shop/exit', {
                    method: 'POST',
                }).then(() => {
                        this.id = ''
                        this.login = ''
                        $('.login-form').css('display', 'block')
                        $('.user-form').css('display', 'none')
                    }
                )
            }
        }
    }
)

Vue.component('main-info', {
        props: ['user', 'id', 'login'],
        template:
            '<div>' +
            '<login :user="user" :setUser="setUser"/>' +
            '<show-user :id="id" :login="login"/>' +
            '</div>',
        methods: {
            setUser: function (user) {
                this.id = user.id
                this.login = user.login
            }
        }
    }
)

var app = new Vue({
    el: '#app',
    template:
        '<main-info/>',
    data: {
        user: []
    }
});

